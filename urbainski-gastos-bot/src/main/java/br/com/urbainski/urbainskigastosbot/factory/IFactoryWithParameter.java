package br.com.urbainski.urbainskigastosbot.factory;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public interface IFactoryWithParameter<T, P> extends IFactory {

    T getService(P param);

}
