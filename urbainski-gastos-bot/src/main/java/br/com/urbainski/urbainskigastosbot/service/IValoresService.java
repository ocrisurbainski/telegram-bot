package br.com.urbainski.urbainskigastosbot.service;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public interface IValoresService {

    void putSalario(LocalDate date, BigDecimal valor);

    void putGastos(BigDecimal valor);

    BigDecimal getResultadoMes();

}
