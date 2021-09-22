package br.com.urbainski.urbainskigastosbot.util;

import java.math.BigDecimal;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public abstract class BigDecimalUtil {

    public static BigDecimal convert(String value) {

        if (value.contains(",")) {

            value = value.replace(",", ".");
        }

        try {

            return new BigDecimal(value);
        } catch (NumberFormatException e) {

            e.printStackTrace();

            return null;
        }
    }

}
