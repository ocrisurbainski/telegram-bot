package br.com.urbainski.urbainskigastosbot.util;

import java.util.Objects;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public abstract class UpdateUtil {

    public static Message getMessage(Update update) {

        if (Objects.isNull(update)) {

            return null;
        }

        Message message = update.message();

        if (message == null) {

            message = update.editedMessage();
        }

        return message;
    }

    public static boolean hasMessage(Update update) {

        boolean hasUpdate = Objects.nonNull(update);
        boolean hasMessage = hasUpdate && Objects.nonNull(update.message());
        boolean hasEditedMessage = hasUpdate && Objects.nonNull(update.editedMessage());
        boolean hasMessageText = (hasMessage || hasEditedMessage) && StringUtil.isNotEmpty(getMessage(update).text());
        return hasMessageText;
    }

    public static boolean noMessage(Update update) {

        return !hasMessage(update);
    }

}
