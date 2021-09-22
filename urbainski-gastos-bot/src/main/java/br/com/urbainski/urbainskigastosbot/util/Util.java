package br.com.urbainski.urbainskigastosbot.util;

import java.time.Month;
import java.util.Objects;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public abstract class Util {

    public static String getMonthName(Month month) {

        if (Objects.isNull(month)) {

            return "null";
        }

        switch (month) {
            case JANUARY -> {
                return "Janeiro";
            }
            case FEBRUARY -> {
                return "Fevereiro";
            }
            case MARCH -> {
                return "Março";
            }
            case APRIL -> {
                return "Abril";
            }
            case MAY -> {
                return "Maio";
            }
            case JUNE -> {
                return "Junho";
            }
            case JULY -> {
                return "Julho";
            }
            case AUGUST -> {
                return "Agosto";
            }
            case SEPTEMBER -> {
                return "Setembro";
            }
            case OCTOBER -> {
                return "Outubro";
            }
            case NOVEMBER -> {
                return "Novembro";
            }
            case DECEMBER -> {
                return "Dezembro";
            }
            default -> {
                return "não identificado";
            }
        }
    }

}
