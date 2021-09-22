package br.com.urbainski.urbainskigastosbot.service;

import br.com.urbainski.urbainskigastosbot.domain.MessageType;
import br.com.urbainski.urbainskigastosbot.process.IMessageTypeProcess;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public interface IMessageTypeService {

    IMessageTypeProcess getProcess(MessageType messageType);

}
