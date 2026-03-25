package com.felipe.habito.model;


import jakarta.persistence.*;

@Entity
@Table(name = "habitos")
public class Habito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false, length = 100)
    private String frequencia;

    private Boolean ativo = true;

    @PrePersist
    public void prePersist() {
        if (ativo == null) {
            ativo = true;
        }
    }

    public Habito() {
    }

    public Habito(Long id, String nome, String descricao, String frequencia, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.frequencia = frequencia;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
