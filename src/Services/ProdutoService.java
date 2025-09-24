package Services;

import java.util.List;
import java.util.Optional;

import Entities.Produto;
import dao.ProdutoDAO;

public class ProdutoService {

    public void cadastrarProduto(Produto produto) {
        boolean existe = ProdutoDAO.buscarProdutoById(produto.getId()).isPresent();
        
        if (existe) {
        	throw new IllegalArgumentException("Produto já cadastrado com este id.");
        }
        ProdutoDAO.incluirProduto(produto);
    }

    public List<Produto> listarProdutos() {
        return ProdutoDAO.listarTodosProdutos();
    }

    public Produto buscarPorNome(String nome) {
    	Optional<Produto> produtoOptional = ProdutoDAO.buscarProdutoByNome(nome);
        if (produtoOptional.isEmpty()) {
        	return null;
        }
        return produtoOptional.get();
    }

    public void atualizarProduto(Produto produto, String novaDescricao, double novoPreco) {
    	Optional<Produto> produtoOptional = ProdutoDAO.buscarProdutoById(produto.getId());
        if (produtoOptional.isEmpty()) {
        	throw new IllegalArgumentException("Produto não localizado.");
        }
        ProdutoDAO.editarProduto(produto.getId(), produto.getNome(), novaDescricao, novoPreco);
    }
}