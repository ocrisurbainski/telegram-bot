package br.com.urbainski.urbainskigastosbot.action.impl;

import java.util.logging.Logger;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.response.BaseResponse;

import br.com.urbainski.urbainskigastosbot.action.AbstractTelegramAction;
import br.com.urbainski.urbainskigastosbot.action.ITelegramAction;
import br.com.urbainski.urbainskigastosbot.util.UpdateUtil;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public class ChatActionTelegramActionImpl extends AbstractTelegramAction<ChatAction> implements
        ITelegramAction<ChatAction> {

    private static final Logger logger = Logger.getLogger(ChatActionTelegramActionImpl.class.getName());

    public ChatActionTelegramActionImpl(TelegramBot bot) {

        super(bot);
    }

    @Override
    public void sendAction(Update update, ChatAction chatAction) {

        final Message message = UpdateUtil.getMessage(update);

        final BaseResponse baseResponse = bot.execute(new SendChatAction(message.chat().id(), chatAction.name()));

        logger.info(String.format("Chat action result: %s", baseResponse.isOk()));
    }

}
