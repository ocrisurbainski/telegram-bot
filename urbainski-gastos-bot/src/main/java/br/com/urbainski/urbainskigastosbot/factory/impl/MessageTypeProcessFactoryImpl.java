package br.com.urbainski.urbainskigastosbot.factory.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import br.com.urbainski.urbainskigastosbot.domain.MessageType;
import br.com.urbainski.urbainskigastosbot.factory.IFactoryWithParameter;
import br.com.urbainski.urbainskigastosbot.process.IMessageTypeProcess;
import br.com.urbainski.urbainskigastosbot.process.impl.GastoProcessImpl;
import br.com.urbainski.urbainskigastosbot.process.impl.ResultadoMesProcessImpl;
import br.com.urbainski.urbainskigastosbot.process.impl.SalarioProcessImpl;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public class MessageTypeProcessFactoryImpl implements IFactoryWithParameter<IMessageTypeProcess, MessageType> {

    private static MessageTypeProcessFactoryImpl instance;
    private final Map<MessageType, IMessageTypeProcess> cache = new HashMap<>();

    @Override
    public IMessageTypeProcess getService(MessageType messageType) {

        if (cache.containsKey(messageType)) {

            return cache.get(messageType);
        }

        IMessageTypeProcess process = null;

        switch (messageType) {
            case GASTO -> process = new GastoProcessImpl();
            case SALARIO -> process = new SalarioProcessImpl();
            case RESULTADO_MES -> process = new ResultadoMesProcessImpl();
        }

        cache.put(messageType, process);

        return process;
    }

    public static MessageTypeProcessFactoryImpl getInstance() {

        if (Objects.isNull(instance)) {

            instance = new MessageTypeProcessFactoryImpl();
        }

        return instance;
    }

}
