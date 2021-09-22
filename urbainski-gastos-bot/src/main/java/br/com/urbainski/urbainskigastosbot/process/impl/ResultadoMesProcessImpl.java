package br.com.urbainski.urbainskigastosbot.process.impl;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.pengrad.telegrambot.model.Update;

import br.com.urbainski.urbainskigastosbot.domain.MessageType;
import br.com.urbainski.urbainskigastosbot.factory.impl.TelegramActionFactoryImpl;
import br.com.urbainski.urbainskigastosbot.factory.impl.ValoresServiceFactoryImpl;
import br.com.urbainski.urbainskigastosbot.process.IMessageTypeProcess;
import br.com.urbainski.urbainskigastosbot.util.Util;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public class ResultadoMesProcessImpl implements IMessageTypeProcess {

    @Override
    public MessageType getMessageType() {

        return MessageType.RESULTADO_MES;
    }

    @Override
    public void process(Update update, BigDecimal valor) {

        final LocalDate date = LocalDate.now();

        final String mes = Util.getMonthName(date.getMonth());

        final BigDecimal valorResultadoMes = ValoresServiceFactoryImpl.getInstance().getService().getResultadoMes();

        String message = String.format("Valor do resultado do mês: %s - %s - %s", valorResultadoMes, mes,
                date.getYear());

        TelegramActionFactoryImpl.getInstance().getSendMessageTelegramAction().sendAction(update, message);
    }

}
