package br.com.urbainski.urbainskigastosbot.factory.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.pengrad.telegrambot.TelegramBot;

import br.com.urbainski.urbainskigastosbot.action.ITelegramAction;
import br.com.urbainski.urbainskigastosbot.action.impl.ChatActionTelegramActionImpl;
import br.com.urbainski.urbainskigastosbot.action.impl.SendMessageTelegramActionImpl;
import br.com.urbainski.urbainskigastosbot.domain.TelegramActionType;
import br.com.urbainski.urbainskigastosbot.factory.IFactoryWithParameter;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public class TelegramActionFactoryImpl implements IFactoryWithParameter<ITelegramAction, TelegramActionType> {

    private static TelegramActionFactoryImpl instance;
    private final TelegramBot bot;
    private final Map<TelegramActionType, ITelegramAction> cache = new HashMap<>();

    public TelegramActionFactoryImpl(TelegramBot bot) {

        this.bot = bot;
    }

    @Override
    public ITelegramAction getService(TelegramActionType telegramActionType) {

        if (cache.containsKey(telegramActionType)) {

            return cache.get(telegramActionType);
        }

        ITelegramAction action = null;

        switch (telegramActionType) {
            case CHAT_ACTION -> action = new ChatActionTelegramActionImpl(bot);
            case SEND_MESSAGE_ACTION -> action = new SendMessageTelegramActionImpl(bot);
        }

        cache.put(telegramActionType, action);

        return action;
    }

    public ChatActionTelegramActionImpl getChatActionTelegramAction() {

        return (ChatActionTelegramActionImpl) getService(TelegramActionType.CHAT_ACTION);
    }

    public SendMessageTelegramActionImpl getSendMessageTelegramAction() {

        return (SendMessageTelegramActionImpl) getService(TelegramActionType.SEND_MESSAGE_ACTION);
    }

    public static TelegramActionFactoryImpl getInstance(TelegramBot bot) {

        if (Objects.isNull(instance)) {

            instance = new TelegramActionFactoryImpl(bot);
        }

        return instance;
    }

    public static TelegramActionFactoryImpl getInstance() {

        return instance;
    }

}
