package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Entities.Cliente;
import Entities.Endereco;

public class ClienteService {

	private List<Cliente> lista;

	public ClienteService() {
		lista = new ArrayList<Cliente>();
	}
	
	public void adicionarCliente(Cliente cliente) {
		boolean existe = lista.stream().anyMatch(c -> c.getCpf().equals(cliente.getCpf()));
	
		if(existe) {
			throw new IllegalArgumentException("Cliente já cadastrado com esse cpf.");
		}
		lista.add(cliente);
	}
	
	public Cliente buscarClientePorCpf(String cpf) {
		return lista.stream()
				.filter(c -> c.getCpf().equals(cpf))
				.findFirst()
				.orElse(null);
	}
	
	public Cliente buscarClientePorNome(String nome) {
		return (Cliente) lista.stream()
				.filter(c -> c.getNome().equalsIgnoreCase(nome))
				.collect(Collectors.toList());
	}
	
	public List<Cliente> listarClientes() {
		return lista;
	}
	
	public void editarCliente(String cpf, String novoNome, String novoEmail, Endereco endereco) {
		Cliente cliente = buscarClientePorCpf(cpf);
		if(cliente == null) {
			throw new IllegalArgumentException("Cliente não localizado.");
		}
		cliente.setNome(novoNome);
		cliente.setEmail(novoEmail);
		cliente.setEndereco(endereco);
		
	}
}
