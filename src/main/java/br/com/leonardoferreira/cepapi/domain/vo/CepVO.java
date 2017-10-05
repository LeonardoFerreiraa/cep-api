package br.com.leonardoferreira.cepapi.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 4:59 PM
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CepVO {

   private String cidade;

   private String logradouro;

   private String tipoLogradouro;

   private String logradouroNormalizado;

   private String tipoLogradouroNormalizado;

   private String bairro;

   private String bairroNormalizado;

   private String cidadeNormalizada;

   private String uf;

   private Boolean naoEncontrado;

   private String cep;

    public CepVO (final Boolean naoEncontrado, final String cep) {
        this.naoEncontrado = naoEncontrado;
        this.cep = cep;
    }
}
