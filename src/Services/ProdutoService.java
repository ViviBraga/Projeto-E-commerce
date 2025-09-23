package Entidades;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();

    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public void atualizarProduto(Produto produto, String novaDescricao, double novoPreco) {
        produto.setDescricao(novaDescricao);
        produto.setPreco(novoPreco);
    }
}