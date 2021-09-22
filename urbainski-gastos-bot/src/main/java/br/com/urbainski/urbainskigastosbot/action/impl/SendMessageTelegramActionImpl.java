package br.com.urbainski.urbainskigastosbot.action.impl;

import java.util.logging.Logger;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.urbainski.urbainskigastosbot.action.AbstractTelegramAction;
import br.com.urbainski.urbainskigastosbot.action.ITelegramAction;
import br.com.urbainski.urbainskigastosbot.factory.impl.TelegramActionFactoryImpl;
import br.com.urbainski.urbainskigastosbot.util.UpdateUtil;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public class SendMessageTelegramActionImpl extends AbstractTelegramAction<String> implements ITelegramAction<String> {

    private static final Logger logger = Logger.getLogger(SendMessageTelegramActionImpl.class.getName());

    public SendMessageTelegramActionImpl(TelegramBot bot) {

        super(bot);
    }

    @Override
    public void sendAction(Update update, String stringMessage) {

        TelegramActionFactoryImpl.getInstance().getChatActionTelegramAction().sendAction(update, ChatAction.typing);

        final Message message = UpdateUtil.getMessage(update);

        final SendResponse sendResponse = bot.execute(new SendMessage(message.chat().id(), stringMessage));

        logger.info(String.format("Envio de mensagem result: %s", sendResponse.isOk()));
    }

}
