package br.com.alura.codetickets.impl;

public class TaxaAdminImplGenerico implements TaxaAdminstracaoStrategy {
    @Override
    public Double execute(Importacao item) {
        if(item.getTipoIngresso() == null){
            throw new NullPointerException("Tipo de ingresso não pode ser nulo");
        } else {
            item.setTaxaAdmin(50.00);
        }
        return item.getTaxaAdmin();
    }
}
