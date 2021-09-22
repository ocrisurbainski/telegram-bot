package br.com.urbainski.urbainskigastosbot.action;

import com.pengrad.telegrambot.TelegramBot;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public abstract class AbstractTelegramAction<T> implements ITelegramAction<T>{

    protected final TelegramBot bot;

    protected AbstractTelegramAction(TelegramBot bot) {

        this.bot = bot;
    }

}
