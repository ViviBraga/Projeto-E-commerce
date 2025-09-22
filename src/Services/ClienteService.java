package Services;

import java.util.List;
import java.util.Optional;

import Entities.Cliente;
import Entities.Endereco;
import dao.ClienteDAO;

public class ClienteService {
	
	public void adicionarCliente(Cliente cliente) {
		boolean existe = ClienteDAO.buscarClienteByCpf(cliente.getCpf()).isPresent();
	
		if(existe) {
			throw new IllegalArgumentException("Cliente já cadastrado com esse cpf.");
		}
		ClienteDAO.incluirCliente(cliente);
	}
	
	public Cliente buscarClientePorCpf(String cpf) {
		Optional<Cliente> clienteOptional = ClienteDAO.buscarClienteByCpf(cpf);
		if (clienteOptional.isEmpty()) {
			return null;
		}
		return clienteOptional.get();
	}
	
	public Cliente buscarClientePorNome(String nome) {
		Optional<Cliente> clienteOptional = ClienteDAO.buscarClienteByNome(nome);
		if (clienteOptional.isEmpty()) {
			return null;
		}
		return clienteOptional.get();
	}
	
	public List<Cliente> listarClientes() {
		return ClienteDAO.listarTodosClientes();
	}
	
	public void editarCliente(String cpf, String novoNome, String novoEmail, Endereco endereco) {
		Optional<Cliente> clienteOptional = ClienteDAO.buscarClienteByCpf(cpf);
		if (clienteOptional.isEmpty()) {
			throw new IllegalArgumentException("Cliente não localizado.");
		}
		ClienteDAO.editarCliente(cpf, novoNome, novoEmail, endereco);
	}
}
