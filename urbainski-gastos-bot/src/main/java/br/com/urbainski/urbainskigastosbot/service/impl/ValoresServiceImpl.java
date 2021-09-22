package br.com.urbainski.urbainskigastosbot.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import br.com.urbainski.urbainskigastosbot.service.IValoresService;
import br.com.urbainski.urbainskigastosbot.util.KeyUtil;

/**
 * @author Cristian Urbainski<cristian.urbainski@ciss.com.br>
 * @version 1.0
 * @since 21/09/2021
 */
public final class ValoresServiceImpl implements IValoresService {

    private static ValoresServiceImpl instance;
    private final Map<String, BigDecimal> salarioMes = new HashMap<>();
    private final Map<String, BigDecimal> resultadoMes = new HashMap<>();
    private final Map<String, BigDecimal> totalGastosMes = new HashMap<>();

    @Override
    public void putSalario(LocalDate date, BigDecimal valor) {

        final String key = KeyUtil.getKey(date);

        this.salarioMes.put(key, valor);

        BigDecimal totalGastosMes = getTotalGastoMes(key);

        this.totalGastosMes.put(key, totalGastosMes);

        calcularResultadoMes(key);
    }

    @Override
    public void putGastos(BigDecimal valor) {

        final LocalDate date = LocalDate.now();

        final String key = KeyUtil.getKey(date);

        BigDecimal totalGastosMes = getTotalGastoMes(key).add(valor);

        this.totalGastosMes.put(key, totalGastosMes);

        calcularResultadoMes(key);
    }

    @Override
    public BigDecimal getResultadoMes() {

        final LocalDate date = LocalDate.now();

        final String key = KeyUtil.getKey(date);

        return this.resultadoMes.get(key);
    }

    private void calcularResultadoMes(String key) {

        BigDecimal valorSalarioMes = this.salarioMes.get(key);

        BigDecimal valorTotalGastoMes = getTotalGastoMes(key);

        this.resultadoMes.put(key, valorSalarioMes.subtract(valorTotalGastoMes));
    }

    private BigDecimal getTotalGastoMes(String key) {

        BigDecimal totalGastoMes = this.totalGastosMes.get(key);

        if (Objects.isNull(totalGastoMes)) {

            totalGastoMes = BigDecimal.ZERO;
        }

        return totalGastoMes;
    }

}
