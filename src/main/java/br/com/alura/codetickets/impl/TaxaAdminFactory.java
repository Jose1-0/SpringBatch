package br.com.alura.codetickets.impl;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TaxaAdminFactory {

    private final Map<String, TaxaAdminstracaoStrategy> strategyMap = Map.of(
            "vip", new TaxaAdminImpVip(),
            "generico", new TaxaAdminImplGenerico(),
            "camarote", new TaxaAdminImplCamrote()
    );

    public TaxaAdminstracaoStrategy getStrategy(String tipoIngresso) {
        TaxaAdminstracaoStrategy strategy = strategyMap.get(tipoIngresso.toLowerCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de ingresso inválido: " + tipoIngresso);
        }
        return strategy;
    }
}
