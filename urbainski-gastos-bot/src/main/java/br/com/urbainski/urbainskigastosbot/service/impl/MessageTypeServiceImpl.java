package br.com.urbainski.urbainskigastosbot.service.impl;

import java.util.HashMap;
import java.util.Map;

import br.com.urbainski.urbainskigastosbot.domain.MessageType;
import br.com.urbainski.urbainskigastosbot.factory.impl.MessageTypeProcessFactoryImpl;
import br.com.urbainski.urbainskigastosbot.process.IMessageTypeProcess;
import br.com.urbainski.urbainskigastosbot.service.IMessageTypeService;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public class MessageTypeServiceImpl implements IMessageTypeService {

    private final Map<MessageType, IMessageTypeProcess> cache = new HashMap<>();

    @Override
    public IMessageTypeProcess getProcess(MessageType messageType) {

        return MessageTypeProcessFactoryImpl.getInstance().getService(messageType);
    }

}
