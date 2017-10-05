package br.com.leonardoferreira.cepapi.features;

import br.com.leonardoferreira.cepapi.domain.vo.CepVO;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 6:45 PM
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchCep {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<CepVO> response;

    @Quando("^for solicitada a busca pelo cep \"([^\"]*)\"$")
    public void forSolicitadaABuscaPeloCep(final String cep) throws Throwable {
        response = restTemplate.getForEntity("/" + cep, CepVO.class);
    }

    @Entao("^a resposta deve contar os seguintes"
            + " \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void aRespostaDeveContarOsSeguintesE(final String cep, final String logradouro,
            final String bairro, final String cidade, final String uf) throws Throwable {
        CepVO body = response.getBody();
        Assertions.assertThat(body.getNaoEncontrado()).isNull();
        Assertions.assertThat(body.getCep()).isEqualTo(cep);
        Assertions.assertThat(body.getLogradouro()).isEqualTo(logradouro);
        Assertions.assertThat(body.getBairro()).isEqualTo(bairro);
        Assertions.assertThat(body.getCidade()).isEqualTo(cidade);
        Assertions.assertThat(uf).isEqualTo(uf);
    }

    @Entao("^a resposta deve dizer que o cep não foi encontrado$")
    public void aRespostaDeveDizerQueOCepNaoFoiEncontrado() throws Throwable {
        CepVO body = response.getBody();
        Assertions.assertThat(body.getNaoEncontrado()).isTrue();
    }
}
