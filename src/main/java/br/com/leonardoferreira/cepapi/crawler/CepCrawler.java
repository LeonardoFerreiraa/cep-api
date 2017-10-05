package br.com.leonardoferreira.cepapi.crawler;

import java.text.Normalizer;

import br.com.leonardoferreira.cepapi.domain.vo.CepVO;
import br.com.leonardoferreira.cepapi.exception.CepNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 3:49 PM
 */
@Component
public class CepCrawler {

    @Value("${correios.base.url}")
    private String baseUrl;

    public CepVO cepInformations(final String cep) {
        try {
            Document body = Jsoup.connect(baseUrl)
                    .data("relaxation", cep)
                    .data("Metodo", "listaLogradouro")
                    .data("TipoConsulta", "relaxation")
                    .data("StartRow", "1")
                    .data("EndRow", "10")
                    .userAgent("Mozilla")
                    .post();

            Elements trs = body.select(".tmptabela tr");

            if (trs == null || trs.size() == 1) {
                throw new CepNotFoundException();
            }

            Elements headers = trs.get(0).select("tr > th");

            CepVO cepVO = new CepVO();

            for (int i = 1; i < trs.size(); i++) {
                final Element tr = trs.get(i);
                Elements tds = tr.select("tr > td");
                for (int j = 0; j < tds.size(); j++) {
                    String header = headers.get(j).text();
                    String text = tds.get(j).text();

                    if ("Logradouro/Nome:".equals(header)) {
                        String[] split = text.split(" - ", 2);
                        String[] logradouro = split[0].split(" ", 2);
                        String fullLog = split[0];
                        cepVO.setLogradouro(trim(fullLog));
                        cepVO.setTipoLogradouro(trim(logradouro[0]));

                        cepVO.setLogradouroNormalizado(stripAccents(cepVO.getLogradouro()));
                        cepVO.setTipoLogradouroNormalizado(stripAccents(cepVO.getTipoLogradouro()));
                    } else if ("Bairro/Distrito:".equals(header)) {
                        cepVO.setBairro(trim(text));
                        cepVO.setBairroNormalizado(stripAccents(cepVO.getBairro()));
                    } else if ("Localidade/UF:".equals(header)) {
                        String[] localidadeUf = text.split("/");
                        cepVO.setCidade(trim(localidadeUf[0]));
                        cepVO.setUf(trim(localidadeUf[1]));

                        cepVO.setCidadeNormalizada(stripAccents(cepVO.getCidade()));
                    } else if ("CEP:".equals(header)) {
                        cepVO.setCep(trim(text));
                    }
                }

                if (cepVO.getCep().equals(addHifen(cep))) {
                    return cepVO;
                }
            }

            throw new CepNotFoundException();
        } catch (Exception e) {
            e.printStackTrace();
            return new CepVO(true, addHifen(cep));
        }
    }

    private String addHifen(final String cep) {
        return cep.substring(0, 5) + "-" + cep.substring(5, 8);
    }

    private String stripAccents(final String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toLowerCase();
    }

    private String trim(final String s) {
        return s.replaceAll(Character.toString((char) 160), "").trim();
    }

}
