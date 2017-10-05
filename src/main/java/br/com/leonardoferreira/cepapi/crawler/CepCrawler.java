package br.com.leonardoferreira.cepapi.crawler;

import java.text.Normalizer;

import br.com.leonardoferreira.cepapi.domain.vo.CepVO;
import br.com.leonardoferreira.cepapi.exception.CepNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

    public CepVO cepInformations(String cep) {
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
            Elements firstTr = trs.get(1).select("tr > td");

            CepVO cepVO = new CepVO();

            for (int j = 0; j < firstTr.size(); j++) {
                String header = headers.get(j).text();
                String text = firstTr.get(j).text();

                if ("Logradouro/Nome:".equals(header)) {
                    String[] split = text.split(" - ", 2);
                    String[] logradouro = split[0].split(" ", 2);
                    cepVO.setLogradouro(split[0]);
                    cepVO.setTipoLogradouro(logradouro[0]);

                    cepVO.setLogradouroNormalizado(stripAccents(cepVO.getLogradouro()));
                    cepVO.setTipoLogradouroNormalizado(stripAccents(cepVO.getTipoLogradouro()));
                } else if ("Bairro/Distrito:".equals(header)) {
                    cepVO.setBairro(text.trim());
                    cepVO.setBairroNormalizado(stripAccents(cepVO.getBairro()));
                } else if ("Localidade/UF:".equals(header)) {
                    String[] localidadeUf = text.split("/");
                    cepVO.setCidade(localidadeUf[0]);
                    cepVO.setUf(localidadeUf[1]);

                    cepVO.setCidadeNormalizada(stripAccents(cepVO.getCidade()));
                } else if ("CEP:".equals(header)) {
                    cepVO.setCep(text);
                }
            }

            return cepVO;
        } catch (Exception e) {
            e.printStackTrace();
            return new CepVO(true, cep);
        }
    }

    private String stripAccents(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toLowerCase();
    }

}
