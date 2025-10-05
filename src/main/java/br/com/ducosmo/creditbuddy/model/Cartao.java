package br.com.ducosmo.creditbuddy.model;

public class Cartao {
    private int id;
    private String nomeCartao;
    private String bandeira;
    private int diaVencimento;
    private int diaFechamento;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomeCartao() { return nomeCartao; }
    public void setNomeCartao(String nomeCartao) { this.nomeCartao = nomeCartao; }
    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }
    public int getDiaVencimento() { return diaVencimento; }
    public void setDiaVencimento(int diaVencimento) { this.diaVencimento = diaVencimento; }
    public int getDiaFechamento() { return diaFechamento; }
    public void setDiaFechamento(int diaFechamento) { this.diaFechamento = diaFechamento; }

    // Útil para exibir em ComboBoxes
    @Override
    public String toString() {
        return getNomeCartao();
    }
}