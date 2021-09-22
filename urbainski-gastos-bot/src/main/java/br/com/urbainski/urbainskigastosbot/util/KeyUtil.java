package br.com.urbainski.urbainskigastosbot.util;

import java.time.LocalDate;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public abstract class KeyUtil {

    public static String getKey(LocalDate date) {

        return String.format("%s%s", date.getMonth().ordinal(), date.getYear());
    }

}
