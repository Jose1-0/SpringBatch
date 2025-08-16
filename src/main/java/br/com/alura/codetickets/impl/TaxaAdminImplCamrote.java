package br.com.alura.codetickets.impl;

public class TaxaAdminImplCamrote implements TaxaAdminstracaoStrategy {
    @Override
    public Double execute(Importacao item) {
        if(item.getTipoIngresso() == null){
            throw new NullPointerException("Tipo de ingresso não pode ser nulo");
        } else if(item.getTipoIngresso().equalsIgnoreCase("camarote")){
            item.setTaxaAdmin(130.00);
        }
        return item.getTaxaAdmin();
    }
}
