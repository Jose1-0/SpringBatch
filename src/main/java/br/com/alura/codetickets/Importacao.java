package br.com.alura.codetickets;

import jakarta.persistence.*;
import org.springframework.dao.support.DaoSupport;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
public class Importacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String cliente;
    private LocalDate nacimento;
    private String evento;
    private LocalDate dataEvento;
    private String tipoIngresso;
    private Double valor;
    private LocalDateTime horaImportacao;

    public Importacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getHoraImportacao() {
        return horaImportacao;
    }

    public void setHoraImportacao(LocalDateTime horaImportacao) {
        this.horaImportacao = horaImportacao;
    }

    public String getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(String tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public LocalDate getNacimento() {
        return nacimento;
    }

    public void setNacimento(LocalDate nacimento) {
        this.nacimento = nacimento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
