package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Entities.Cliente;
import Entities.Endereco;
import Enum.EstadoEnum;

public class ClienteDAO {

	private static Path path = BaseDAO.diretorio.resolve("cliente.csv");
	
	public static List<Cliente> listarTodosClientes() {
		try (Stream<String> resultado = BaseDAO.lerCsv(path)) {
			if (resultado == null) {
				return new ArrayList<>();
			}
			return resultado.map(String::trim)
					.filter(l -> !l.toLowerCase().startsWith("cpf"))
					.map(ClienteDAO::parseCsvLine)
					.filter(Optional::isPresent)
					.map(Optional::get)
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Optional<Cliente> buscarClienteByCpf(String cpf) {
		List<Cliente> lista = listarTodosClientes();
		return lista.stream()
				.filter(c -> c.getCpf().equalsIgnoreCase(cpf))
				.findFirst();
	}
	
	public static Optional<Cliente> buscarClienteByNome(String nome) {
		List<Cliente> lista = listarTodosClientes();
		return lista.stream()
				.filter(c -> c.getNome().equalsIgnoreCase(nome))
				.findFirst();
	}
 
	public static void incluirCliente(Cliente cliente) {
		String csv = "";
		
		boolean arquivoExiste = Files.exists(path);
		if (!arquivoExiste) {
			csv = "cpf,nome,email,logradouro,bairro,numero,complemento,cidade,cep,estado\n";
		}
		
		csv = csv + toCsvLine(cliente);

		BaseDAO.incluirNoCsv(path, csv);
	}

	public static void editarCliente(String cpf, String nome, String email, Endereco endereco) {
		Optional<Cliente> clienteOptional = buscarClienteByCpf(cpf);
		if (clienteOptional.isPresent()) {
			Cliente clienteAtualizado = atualizarCliente(clienteOptional.get(), cpf, nome, email, endereco);
			List<Cliente> lista = listarTodosClientes();
			
			String csv = "cpf,nome,email,logradouro,bairro,numero,complemento,cidade,cep,estado\n";
			for (Cliente cliente : lista) {
				if (cliente.getCpf().equals(clienteAtualizado.getCpf())) {
					csv = csv + toCsvLine(clienteAtualizado) + "\n";
				}
				else {
					csv = csv + toCsvLine(cliente) + "\n";
				}
			}
			BaseDAO.escreverNoCsv(path, csv);
		}
	}
	
	private static Cliente atualizarCliente(Cliente cliente, String cpf, String nome, String email, Endereco endereco) {
		String nomeAtualizado = nome != null ? nome : cliente.getNome();
		String emailAtualizado = email != null ? email : cliente.getEmail();
		Endereco enderecoAtualizado = endereco != null ? endereco : cliente.getEndereco();
		return new Cliente(cpf, nomeAtualizado, emailAtualizado, enderecoAtualizado);
	}
	

	private static String toCsvLine(Cliente cliente) {
		return String.join(",", 
				BaseDAO.escapeCsv(cliente.getCpf()), 
				BaseDAO.escapeCsv(cliente.getNome()),
				BaseDAO.escapeCsv(cliente.getEmail()),
				BaseDAO.escapeCsv(cliente.getEndereco().getLogradouro()),
				BaseDAO.escapeCsv(cliente.getEndereco().getBairro()),
				String.valueOf(cliente.getEndereco().getNumero()),
				BaseDAO.escapeCsv(cliente.getEndereco().getComplemento()),
				BaseDAO.escapeCsv(cliente.getEndereco().getCidade()),
				BaseDAO.escapeCsv(cliente.getEndereco().getCep()),
				BaseDAO.escapeCsv(cliente.getEndereco().getEstado().toString()));
	}
	
	private static Optional<Cliente> parseCsvLine(String line) {
		String[] parts = line.split(",", -1);
        if (parts.length < 10) {
            return Optional.empty();
        }
        String cpf = BaseDAO.unquote(parts[0].trim());
        String nome = BaseDAO.unquote(parts[1].trim());
        String email = BaseDAO.unquote(parts[2].trim());
        String logradouro = BaseDAO.unquote(parts[3].trim());
        String bairro = BaseDAO.unquote(parts[4].trim());
        int numero = Integer.valueOf(parts[5].trim());
        String complemento = BaseDAO.unquote(parts[6].trim());
        String cidade = BaseDAO.unquote(parts[7].trim());
        String cep = BaseDAO.unquote(parts[8].trim());
        String estadoStr = BaseDAO.unquote(parts[9].trim().toUpperCase());
        EstadoEnum estado = EstadoEnum.valueOf(estadoStr);
        
        Endereco endereco = new Endereco(logradouro, bairro, numero, complemento, cidade, cep, estado);
        return Optional.of(new Cliente(cpf, nome, email, endereco));
	}
}
