package br.com.leonardoferreira.cepapi.crawler;

import br.com.leonardoferreira.cepapi.domain.ZipCodeInfo;
import br.com.leonardoferreira.cepapi.exception.ZipCodeNotFoundException;
import br.com.leonardoferreira.cepapi.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 * $Id: $
 * @since 10/5/17 3:49 PM
 */
@Slf4j
@Component
public class ZipCodeCrawler {

    @Value("${correios.base.url}")
    private String baseUrl;

    public ZipCodeInfo retrieveInformation(final String originalZipCode) {
        try {
            String zipCode = StringUtils.trimAllWhitespace(originalZipCode)
                    .replaceAll("[^\\d.]", "");

            if (StringUtils.isEmpty(zipCode)) {
                throw new ZipCodeNotFoundException(zipCode);
            }

            Document body = Jsoup.connect(baseUrl)
                    .data("relaxation", zipCode)
                    .data("Metodo", "listaLogradouro")
                    .data("TipoConsulta", "relaxation")
                    .data("StartRow", "1")
                    .data("EndRow", "10")
                    .userAgent("Mozilla")
                    .post();

            Elements trs = body.select(".tmptabela tr");

            if (trs == null || trs.size() == 1) {
                throw new ZipCodeNotFoundException(zipCode);
            }

            Elements headers = trs.get(0).select("tr > th");

            ZipCodeInfo zipCodeInfo = new ZipCodeInfo();

            for (int i = 1; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.select("tr > td");

                for (int j = 0; j < tds.size(); j++) {
                    String header = headers.get(j).text();
                    String text = tds.get(j).text();

                    if ("Logradouro/Nome:".equals(header)) {
                        String[] split = text.split(" - ", 2);
                        String fullLog = split[0];

                        zipCodeInfo.setAddress(StringUtils.trim(fullLog));
                    } else if ("Bairro/Distrito:".equals(header)) {
                        zipCodeInfo.setNeighborhood(StringUtils.trim(text));
                    } else if ("Localidade/UF:".equals(header)) {
                        String[] localidadeUf = text.split("/");

                        zipCodeInfo.setCity(StringUtils.trim(localidadeUf[0]));
                        zipCodeInfo.setFederationUnity(StringUtils.trim(localidadeUf[1]));
                    } else if ("CEP:".equals(header)) {
                        zipCodeInfo.setZipCode(StringUtils.trim(text));
                    }
                }

                if (zipCodeInfo.getZipCode().equals(StringUtils.addHyphen(zipCode))) {
                    return zipCodeInfo;
                }
            }

            throw new ZipCodeNotFoundException(zipCode);
        } catch (Exception e) {
            log.error("Error=Erro ao consultar zipCode, zipCode={}, e={}", originalZipCode, e.getMessage(), e);
            throw new ZipCodeNotFoundException(originalZipCode, e);
        }
    }

}
