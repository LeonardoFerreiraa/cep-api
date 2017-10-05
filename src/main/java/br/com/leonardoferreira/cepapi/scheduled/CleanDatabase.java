package br.com.leonardoferreira.cepapi.scheduled;

import br.com.leonardoferreira.cepapi.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 6:29 PM
 */
@Component
public class CleanDatabase {

    @Autowired
    private IpService ipService;

    @Scheduled(cron = "0 0 3 1/1 * ?")
    public void cleanIp() {
        ipService.cleanIp();
    }
}
