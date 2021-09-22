package br.com.urbainski.urbainskigastosbot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import br.com.urbainski.urbainskigastosbot.domain.MessageType;
import br.com.urbainski.urbainskigastosbot.factory.impl.MessageTypeServiceFactoryImpl;
import br.com.urbainski.urbainskigastosbot.factory.impl.TelegramActionFactoryImpl;
import br.com.urbainski.urbainskigastosbot.factory.impl.ValoresServiceFactoryImpl;
import br.com.urbainski.urbainskigastosbot.process.IMessageTypeProcess;
import br.com.urbainski.urbainskigastosbot.service.impl.ValoresServiceImpl;
import br.com.urbainski.urbainskigastosbot.util.BigDecimalUtil;
import br.com.urbainski.urbainskigastosbot.util.ListUtil;
import br.com.urbainski.urbainskigastosbot.util.UpdateUtil;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String TOKEN_ARGUMENT = "token";
    private static final int LIMIT_UPDATES = 100;
    private static final String TOKEN_SEPARATED_COMMAND = " ";
    private static final String TOKEN_COMMAND_START = "/";
    private TelegramBot bot;
    private int offset = 0;

    public void start(String tokenBot) {

        initBot(tokenBot);

        ValoresServiceFactoryImpl.getInstance().getService();

        TelegramActionFactoryImpl.getInstance(bot);

        while (true) {

            GetUpdatesResponse updatesResponse = getLastUpdates();

            processUpdates(updatesResponse.updates());
        }
    }

    private void initBot(String tokenBot) {

        bot = new TelegramBot(tokenBot);
    }

    private GetUpdatesResponse getLastUpdates() {

        return bot.execute(new GetUpdates().limit(LIMIT_UPDATES).offset(offset));
    }

    private void processUpdates(List<Update> updates) {

        if (ListUtil.isEmpty(updates)) {

            return;
        }

        updates.forEach(this::processUpdate);
    }

    private void processUpdate(Update update) {

        updateOffset(update);

        if (UpdateUtil.noMessage(update)) {

            logger.info("Sem mensagem");

            return;
        }

        logger.info(String.format("Mensagem recebida: %s", UpdateUtil.getMessage(update).text()));

        final List<MessageType> messageTypeList = getMensagemTypeList(update);

        if (ListUtil.isEmpty(messageTypeList)) {

            sendMessage(update, "Não entendi sua mensagem...");

            return;
        }

        BigDecimal valor = getValor(update);

        processMessageTypeList(update, messageTypeList, valor);
    }

    private void processMessageTypeList(Update update, List<MessageType> messageTypeList, BigDecimal valor) {

        if (ListUtil.isEmpty(messageTypeList)) {

            return;
        }

        messageTypeList.forEach(messageType -> this.processMessageType(update, messageType, valor));
    }

    private void processMessageType(Update update, MessageType messageType, BigDecimal valor) {

        if (Objects.isNull(messageType)) {

            return;
        }

        if (messageType.isValorRequired() && Objects.isNull(valor)) {

            sendMessage(update, "Valor do lançamento não está presente...");

            return;
        }

        final IMessageTypeProcess process = MessageTypeServiceFactoryImpl.getInstance().getService()
                .getProcess(messageType);

        if (process == null) {

            return;
        }

        process.process(update, valor);
    }

    private void sendMessage(Update update, String message) {

        TelegramActionFactoryImpl.getInstance().getSendMessageTelegramAction().sendAction(update, message);
    }

    private BigDecimal getValor(Update update) {

        final String[] conteudo = getConteudoMessage(update);

        if (conteudo.length > 1) {

            String valor = conteudo[conteudo.length - 1];

            return BigDecimalUtil.convert(valor);
        }

        return null;
    }

    private List<MessageType> getMensagemTypeList(Update update) {

        final List<MessageType> messageTypeList = new ArrayList<>();

        final String[] conteudo = getConteudoMessage(update);

        for (int i = 0; i < conteudo.length; i++) {

            String valor = conteudo[i];

            if (!valor.startsWith(TOKEN_COMMAND_START)) {

                continue;
            }

            final MessageType type = MessageType.fromString(valor);

            if (type != null) {

                messageTypeList.add(type);
            }
        }

        return messageTypeList;
    }

    private String[] getConteudoMessage(Update update) {

        final String message = UpdateUtil.getMessage(update).text();

        return message.split(TOKEN_SEPARATED_COMMAND);
    }

    private void updateOffset(Update update) {

        offset = update.updateId() + 1;
    }

    public static void main(String[] args) {

        String tokenBot = System.getProperty(TOKEN_ARGUMENT);

        if (Objects.isNull(tokenBot)) {

            throw new IllegalArgumentException("Não foi informado o argumento \"-t=token\"");
        }

        new Main().start(tokenBot);
    }

}
