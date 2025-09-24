package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Entities.Produto;

public class ProdutoDAO {

	private static Path path = BaseDAO.diretorio.resolve("produto.csv");
	
	public static List<Produto> listarTodosProdutos() {
		try (Stream<String> resultado = BaseDAO.lerCsv(path)) {
			if (resultado == null) {
				return new ArrayList<>();
			}
			return resultado.map(String::trim)
					.filter(l -> !l.toLowerCase().startsWith("id"))
					.map(ProdutoDAO::parseCsvLine)
					.filter(Optional::isPresent)
					.map(Optional::get)
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Optional<Produto> buscarProdutoById(int id) {
		List<Produto> lista = listarTodosProdutos();
		return lista.stream()
				.filter(p -> p.getId() == id)
				.findFirst();
	}
	
	public static Optional<Produto> buscarProdutoByNome(String nome) {
		List<Produto> lista = listarTodosProdutos();
		return lista.stream()
				.filter(p -> p.getNome().equalsIgnoreCase(nome))
				.findFirst();
	}
 
	public static void incluirProduto(Produto produto) {
		String csv = "";
		
		boolean arquivoExiste = Files.exists(path);
		if (!arquivoExiste) {
			csv = "id,nome,descricao,preco,dataCadastro\n";
		}
		
		csv = csv + toCsvLine(produto);

		BaseDAO.incluirNoCsv(path, csv);
	}

	public static void editarProduto(int id, String nome, String descricao, double preco) {
		Optional<Produto> produtoOptional = buscarProdutoById(id);
		if (produtoOptional.isPresent()) {
			Produto produtoAtualizado = atualizarProduto(produtoOptional.get(), id, nome, descricao, preco);
			List<Produto> lista = listarTodosProdutos();
			
			String csv = "id,nome,descricao,preco,dataCadastro\n";
			for (Produto produto : lista) {
				if (produto.getId() == produtoAtualizado.getId()) {
					csv = csv + toCsvLine(produtoAtualizado) + "\n";
				}
				else {
					csv = csv + toCsvLine(produto) + "\n";
				}
			}
			BaseDAO.escreverNoCsv(path, csv);
		}
	}
	
	private static Produto atualizarProduto(Produto produto, int id, String nome, String descricao, double preco) {
		String nomeAtualizado = nome != null ? nome : produto.getNome();
		String descricaoAtualizada = descricao != null ? descricao : produto.getDescricao();
		double precoAtualizado = preco != 0.0 ? preco : produto.getPreco();
		return new Produto(id, nomeAtualizado, descricaoAtualizada, precoAtualizado, produto.getDataCadastro());
	}
	

	private static String toCsvLine(Produto produto) {
		return String.join(",",
				String.valueOf(produto.getId()),
				BaseDAO.escapeCsv(produto.getNome()), 
				BaseDAO.escapeCsv(produto.getDescricao()),
				String.valueOf(produto.getPreco()),
				BaseDAO.escapeCsv(produto.getDataCadastro().toString()));
	}
	
	private static Optional<Produto> parseCsvLine(String line) {
		String[] parts = line.split(",", -1);
        if (parts.length < 5) {
            return Optional.empty();
        }
        int id = Integer.valueOf(parts[0].trim());
        String nome = BaseDAO.unquote(parts[1].trim());
        String descricao = BaseDAO.unquote(parts[2].trim());
        double preco = Double.valueOf(parts[3].trim());
        String dataCadastroStr = BaseDAO.unquote(parts[4].trim());
        LocalDateTime dataCadastro = LocalDateTime.parse(dataCadastroStr);
        
        return Optional.of(new Produto(id, nome, descricao, preco, dataCadastro));
	}

}
