package br.com.alura.codetickets.impl;

import org.springframework.batch.item.ItemProcessor;

public class ImportacaoProcessor implements ItemProcessor<Importacao, Importacao> {
    @Override
    public Importacao process(Importacao item) throws Exception {
        if(item != null){
            throw new NullPointerException("Importação não pode ser nula");
        } else {
           // getTaxaStrategy.execute(item);
        }
        return item;
    }
}
