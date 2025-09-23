package Entities;

import java.time.LocalDateTime;

public class Produto {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private LocalDateTime dataCadastro;

    public Produto(String nome, String descricao, double preco) {
        this.id = contadorId++;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.dataCadastro = LocalDateTime.now();
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Descrição: " + descricao +
                ", Preço: R$" + preco + ", Data de Cadastro: " + dataCadastro;
    }
}