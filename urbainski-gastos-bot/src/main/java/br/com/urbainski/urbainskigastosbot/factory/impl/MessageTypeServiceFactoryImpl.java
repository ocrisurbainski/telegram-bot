package br.com.urbainski.urbainskigastosbot.factory.impl;

import java.util.Objects;

import br.com.urbainski.urbainskigastosbot.factory.IFactoryWithoutParameter;
import br.com.urbainski.urbainskigastosbot.service.IMessageTypeService;
import br.com.urbainski.urbainskigastosbot.service.impl.MessageTypeServiceImpl;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public class MessageTypeServiceFactoryImpl implements IFactoryWithoutParameter<IMessageTypeService> {

    private static MessageTypeServiceFactoryImpl instance;
    private IMessageTypeService service;

    @Override
    public IMessageTypeService getService() {

        if (Objects.isNull(service)) {

            service = new MessageTypeServiceImpl();
        }

        return service;
    }

    public static MessageTypeServiceFactoryImpl getInstance() {

        if (Objects.isNull(instance)) {

            instance = new MessageTypeServiceFactoryImpl();
        }

        return instance;
    }

}
