package br.com.leonardoferreira.cepapi.service;

import br.com.leonardoferreira.cepapi.crawler.CepCrawler;
import br.com.leonardoferreira.cepapi.domain.vo.CepVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 6:02 PM
 */
@Service
public class CepService {

    @Autowired
    private CepCrawler cepCrawler;

    @Autowired
    private IpService ipService;

    public CepVO cepInformations(final String cep, final String tkn) {
        ipService.validate(tkn);
        return cepCrawler.cepInformations(cep);
    }
}
