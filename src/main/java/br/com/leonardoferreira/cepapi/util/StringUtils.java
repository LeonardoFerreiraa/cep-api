package br.com.leonardoferreira.cepapi.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/4/18 2:31 PM
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils extends org.springframework.util.StringUtils {

    public static String trim(final String s) {
        return s.replaceAll(Character.toString((char) 160), "").trim();
    }

    public static String addHyphen(final String zipCode) {
        return zipCode.substring(0, 5) + "-" + zipCode.substring(5, 8);
    }

}
