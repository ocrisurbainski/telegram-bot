package br.com.urbainski.urbainskigastosbot.util;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public abstract class StringUtil {

    public static boolean isEmpty(CharSequence value) {

        return value == null || value.isEmpty();
    }

    public static boolean isNotEmpty(CharSequence value) {

        return !isEmpty(value);
    }

}
