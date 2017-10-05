package br.com.leonardoferreira.cepapi.util;

/**
 * @author s2it_leferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 6:08 PM
 */

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 6:08 PM
 */
@Component
public class WebUtils {

    @Autowired
    private HttpServletRequest request;

    public String getClientIp() {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

}
