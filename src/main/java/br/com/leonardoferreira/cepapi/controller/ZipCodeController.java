package br.com.leonardoferreira.cepapi.controller;

import br.com.leonardoferreira.cepapi.domain.ZipCodeInfo;
import br.com.leonardoferreira.cepapi.service.ZipCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 3:48 PM
 */
@RestController
@RequestMapping("/zip-codes")
public class ZipCodeController {

    @Autowired
    private ZipCodeService zipCodeService;

    @GetMapping("/{zipCode}")
    public ZipCodeInfo searchCep(@PathVariable final String zipCode) {
        return zipCodeService.retrieveInformation(zipCode);
    }

}
