package br.com.leonardoferreira.cepapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 * $Id: $
 * @since 10/5/17 5:58 PM
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ZipCodeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 8170122757534225514L;

    public ZipCodeNotFoundException(final String zipCode) {
        super(String.format("ZipCode Not Found [%s]", zipCode));
    }

    public ZipCodeNotFoundException(final String zipCode, final Throwable throwable) {
        super(String.format("ZipCode Not Found [%s]", zipCode), throwable);
    }
}
