package br.com.alura.codetickets.impl;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImportacaoMapper  implements FieldSetMapper<Importacao> {

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public Importacao mapFieldSet(FieldSet fieldSet) throws BindException {
        Importacao importacao = new Importacao();
        importacao.setCpf(fieldSet.readString("cpf"));
        importacao.setCliente(fieldSet.readString("cliente"));
        importacao.setEvento(fieldSet.readString("evento"));
        importacao.setId(Long.parseLong(fieldSet.readString("id")));
        importacao.setDataEvento(LocalDate.parse("dataEvento", dateTimeFormatter));
        importacao.setNacimento(LocalDate.parse("nacimento", dateFormatter));
        importacao.setHoraImportacao(LocalDateTime.now());
        importacao.setValor(fieldSet.readDouble("valor"));
        importacao.setTipoIngresso(fieldSet.readString("tipoIngresso"));

        return null;
    }
}
