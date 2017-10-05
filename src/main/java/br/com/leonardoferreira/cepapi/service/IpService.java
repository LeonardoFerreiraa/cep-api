package br.com.leonardoferreira.cepapi.service;

import br.com.leonardoferreira.cepapi.domain.Ip;
import br.com.leonardoferreira.cepapi.exception.MaxRequestExceeded;
import br.com.leonardoferreira.cepapi.repository.IpRepository;
import br.com.leonardoferreira.cepapi.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 6:08 PM
 */
@Service
public class IpService {

    @Autowired
    private WebUtils webUtils;

    @Autowired
    private IpRepository ipRepository;

    @Value("${maxRequests}")
    private Long maxRequests;

    @Value("${unlimited.access.token}")
    private String token;

    @Transactional(noRollbackFor = MaxRequestExceeded.class)
    public void validate (final String tkn) {
        if (token.equals(tkn)) {
            return;
        }

        String ipStr = webUtils.getClientIp();

        Ip ip = ipRepository.findOne(ipStr);

        if (ip == null) {
            ipRepository.save(new Ip(ipStr, 1L));
        } else {
            ip.setQuantity(ip.getQuantity() + 1L);

            if (maxRequests.compareTo(ip.getQuantity()) <= 0) {
                ipRepository.save(ip);
                throw new MaxRequestExceeded();
            }
        }
    }

    public void cleanIp () {
        ipRepository.deleteAll();
    }
}
