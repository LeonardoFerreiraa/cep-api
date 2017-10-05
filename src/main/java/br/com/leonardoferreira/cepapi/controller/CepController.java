package br.com.leonardoferreira.cepapi.controller;

import br.com.leonardoferreira.cepapi.domain.vo.CepVO;
import br.com.leonardoferreira.cepapi.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 3:48 PM
 */
@RestController
@CrossOrigin("*")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/{cep}")
    public CepVO searchCep(@PathVariable String cep, @RequestParam(value = "tkn", required = false) String tkn) {
        return cepService.cepInformations(cep, tkn);
    }

}