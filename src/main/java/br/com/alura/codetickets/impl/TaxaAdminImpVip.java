package br.com.alura.codetickets.impl;

public class TaxaAdminImpVip implements TaxaAdminstracaoStrategy {

    @Override
    public Double execute(Importacao item) {
        if(item.getTipoIngresso() == null){
            throw new NullPointerException("Tipo de ingresso não pode ser nulo");
        } else if(item.getTipoIngresso().equalsIgnoreCase("vip")){
            item.setTaxaAdmin(130.00);
        }
        return item.getTaxaAdmin();
    }
}
