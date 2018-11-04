package br.com.leonardoferreira.cepapi.service;

import br.com.leonardoferreira.cepapi.crawler.ZipCodeCrawler;
import br.com.leonardoferreira.cepapi.domain.ZipCodeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 * $Id: $
 * @since 10/5/17 6:02 PM
 */
@Slf4j
@Service
public class ZipCodeService {

    @Autowired
    private ZipCodeCrawler zipCodeCrawler;

    public ZipCodeInfo retrieveInformation(final String zipCode) {
        log.info("Method=retrieveInformation, zipCode={}", zipCode);
        return zipCodeCrawler.retrieveInformation(zipCode);
    }
}
