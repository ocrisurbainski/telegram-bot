package br.com.urbainski.urbainskigastosbot.process;

import java.math.BigDecimal;

import com.pengrad.telegrambot.model.Update;

import br.com.urbainski.urbainskigastosbot.domain.MessageType;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public interface IMessageTypeProcess {

    MessageType getMessageType();

    void process(Update update, BigDecimal valor);

    default boolean accpet(MessageType messageType) {

        return getMessageType().equals(messageType);
    }

}
