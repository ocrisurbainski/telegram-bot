package br.com.urbainski.urbainskigastosbot.process.impl;

import java.math.BigDecimal;

import com.pengrad.telegrambot.model.Update;

import br.com.urbainski.urbainskigastosbot.domain.MessageType;
import br.com.urbainski.urbainskigastosbot.factory.impl.ValoresServiceFactoryImpl;
import br.com.urbainski.urbainskigastosbot.process.IMessageTypeProcess;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public class GastoProcessImpl implements IMessageTypeProcess {

    @Override
    public MessageType getMessageType() {

        return MessageType.GASTO;
    }

    @Override
    public void process(Update update, BigDecimal valor) {

        ValoresServiceFactoryImpl.getInstance().getService().putGastos(valor);
    }

}