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
public class SalarioProcessImpl implements IMessageTypeProcess {

    @Override
    public MessageType getMessageType() {

        return MessageType.SALARIO;
    }

    @Override
    public void process(Update update, BigDecimal valor) {

        LocalDate date = LocalDate.now();

        String message = "Recebido o salário no valor de: %s para o mês: %s de %s";
        message = String.format(message, valor.toString(), Util.getMonthName(date.getMonth()), date.getYear());

        ValoresServiceFactoryImpl.getInstance().getService().putSalario(date, valor);

        TelegramActionFactoryImpl.getInstance().getSendMessageTelegramAction().sendAction(update, message);
    }

}
