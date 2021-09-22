package br.com.urbainski.urbainskigastosbot.domain;

import java.util.Arrays;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public enum MessageType {
    SALARIO(true, new String[] {"/salario","/salario@UrbainskiGastosBot"}),
    GASTO(true, new String[] {"/gasto", "/gasto@UrbainskiGastosBot"}),
    RESULTADO_MES(false, new String[] {"/resultadomes", "/resultadomes@UrbainskiGastosBot"});

    MessageType(boolean valorRequired, String[] actions) {

        this.valorRequired = valorRequired;
        this.actions = actions;
    }

    private final boolean valorRequired;
    private final String[] actions;

    public boolean isValorRequired() {

        return valorRequired;
    }

    public String[] getActions() {

        return actions;
    }

    public static MessageType fromString(String action) {

        if (action == null || action.isEmpty()) {

            return null;
        }

        for (MessageType type : MessageType.values()) {

            if (Arrays.stream(type.getActions()).anyMatch(typeAction -> typeAction.equals(action))) {

                return type;
            }
        }

        return null;
    }

}