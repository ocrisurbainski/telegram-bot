package br.com.urbainski.urbainskigastosbot.action;

import com.pengrad.telegrambot.model.Update;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public interface ITelegramAction<T> {

    void sendAction(Update update, T value);

}
