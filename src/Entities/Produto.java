package Entities;

import java.time.LocalDateTime;

import dao.ProdutoDAO;

public class Produto {
	private static int contadorId = 1; 

    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private LocalDateTime dataCadastro;

    public Produto(String nome, String descricao, double preco) {
        this.id = gerarIdUnico();
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.dataCadastro = LocalDateTime.now();
    }

    public Produto(int id, String nome, String descricao, double preco, LocalDateTime dataCadastro) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.dataCadastro = dataCadastro;
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

    public static int gerarIdUnico() {
        while (ProdutoDAO.buscarProdutoById(contadorId).isPresent()) {
            contadorId++;
        }
        return contadorId++;
    }
    
    @Override
    public String toString() {
        return String.format(
            "ID: %d%nNome: %s%nDescrição: %s%nPreço: R$ %.2f%nData de Cadastro: %s",
            id, nome, descricao, preco, dataCadastro
        );
    }

}