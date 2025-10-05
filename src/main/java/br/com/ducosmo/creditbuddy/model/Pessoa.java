package br.com.ducosmo.creditbuddy.model;

public class Pessoa {
    private int id;
    private String nome;
    private String celular;

    // Construtor vazio
    public Pessoa() {}

    // Construtor para facilitar a criação de objetos
    public Pessoa(int id, String nome, String celular) {
        this.id = id;
        this.nome = nome;
        this.celular = celular;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    // Isso é útil para exibir o nome da pessoa em ComboBoxes (listas) no futuro
    @Override
    public String toString() {
        return getNome();
    }
}