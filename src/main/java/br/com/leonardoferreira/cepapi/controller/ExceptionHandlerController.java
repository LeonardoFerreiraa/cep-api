package br.com.leonardoferreira.cepapi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import br.com.leonardoferreira.cepapi.exception.MaxRequestExceeded;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 6:19 PM
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MaxRequestExceeded.class)
    public void handleMaxRequestExceeded(final HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.BAD_REQUEST.value(),
                "Você atingiu o máximo de requisições por dia.");
    }
}
