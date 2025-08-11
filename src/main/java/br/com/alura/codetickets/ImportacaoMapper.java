package br.com.alura.codetickets;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ImportacaoMapper  implements FieldSetMapper<Importacao> {
    @Override
    public Importacao mapFieldSet(FieldSet fieldSet) throws BindException {
        return null;
    }
}
