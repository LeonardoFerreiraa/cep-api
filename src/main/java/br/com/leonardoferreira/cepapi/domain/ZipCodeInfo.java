package br.com.leonardoferreira.cepapi.domain;

import lombok.Data;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 * $Id: $
 * @since 10/5/17 4:59 PM
 */
@Data
public class ZipCodeInfo {

    private String city;

    private String address;

    private String neighborhood;

    private String federationUnity;

    private String zipCode;

}
