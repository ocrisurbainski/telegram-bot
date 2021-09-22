package br.com.urbainski.urbainskigastosbot.factory.impl;

import java.util.Objects;

import br.com.urbainski.urbainskigastosbot.factory.IFactoryWithoutParameter;
import br.com.urbainski.urbainskigastosbot.service.IValoresService;
import br.com.urbainski.urbainskigastosbot.service.impl.ValoresServiceImpl;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public class ValoresServiceFactoryImpl implements IFactoryWithoutParameter<IValoresService> {

    private static ValoresServiceFactoryImpl instance;
    private IValoresService service;

    @Override
    public IValoresService getService() {

        if (Objects.isNull(service)) {

            service = new ValoresServiceImpl();
        }

        return service;
    }

    public static ValoresServiceFactoryImpl getInstance() {

        if (Objects.isNull(instance)) {

            instance = new ValoresServiceFactoryImpl();
        }

        return instance;
    }

}
