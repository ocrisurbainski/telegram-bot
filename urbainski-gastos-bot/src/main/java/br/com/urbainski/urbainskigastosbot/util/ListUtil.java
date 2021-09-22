package br.com.urbainski.urbainskigastosbot.util;

import java.util.List;
import java.util.Objects;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public abstract class ListUtil {

    public static boolean isEmpty(List<?> list) {

        return Objects.isNull(list) || list.size() == 0;
    }

}
