package main;

import java.util.List;
import java.util.Scanner;

import Entities.Cliente;
import Entities.Endereco;
import Entities.Pedido;
import Entities.Produto;
import Enum.EstadoEnum;
import Services.ClienteService;
import Services.PedidoService;
import Services.ProdutoService;
import dao.PedidoDAO;

public class Principal {
	
	private static ClienteService clienteService = new ClienteService();
	private static ProdutoService produtoService = new ProdutoService();
	private static PedidoService pedidoService = new PedidoService(new PedidoDAO());

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		boolean executando = true;
		
		while (executando) {
			System.out.print(Menu.gerarMenuPrincipal());
			int opcao = entrada.nextInt();
			entrada.nextLine();
			switch (opcao) {
				case 1:
					menuCliente(entrada);
					break;
				case 2:
					menuProduto(entrada);
					break;
				case 3:
					menuPedidos(entrada);
					break;
				case 4:
					executando = false;
					break;
			}
		}
		
		entrada.close();
	}
	
	private static void menuCliente(Scanner entrada) {
		boolean executando = true;
		while (executando) {
			System.out.print(Menu.gerarMenuCliente());
			int opcao = entrada.nextInt();
			entrada.nextLine();
			switch (opcao) {
				case 1:
					Cliente cliente = lerCliente(entrada);
					clienteService.adicionarCliente(cliente);
					break;
				case 2:
					List<Cliente> clientes = clienteService.listarClientes();
					clientes.forEach(System.out::println);
					break;
				case 3:
					editarCliente(entrada);
					break;
				case 4:
					executando = false;
					break;
			}
		}
	}
	
	private static void menuProduto(Scanner entrada) {
		boolean executando = true;
		while (executando) {
			System.out.print(Menu.gerarMenuProduto());
			int opcao = entrada.nextInt();
			entrada.nextLine();
			switch (opcao) {
				case 1:
					Produto produto = lerProduto(entrada);
					produtoService.cadastrarProduto(produto);
					break;
				case 2:
					List<Produto> produtos = produtoService.listarProdutos();
					produtos.forEach(System.out::println);
					break;
				case 3:
					editarProduto(entrada);
					break;
				case 4:
					executando = false;
					break;
			}
		}
	}
	
	private static void menuPedidos(Scanner entrada) {
		boolean executando = true;
		while (executando) {
			System.out.print(Menu.gerarMenuPedidos());
			int opcao = entrada.nextInt();
			entrada.nextLine();
			switch (opcao) {
				case 1:
					menuPedido(entrada, null);
					break;
				case 2:
					System.out.print("Digite o id do pedido que deseja acessar: ");
					Long id = entrada.nextLong();
					Pedido pedido = pedidoService.buscarPorId(id);
					menuPedido(entrada, pedido);
					break;
				case 3:
					List<Pedido> pedidos = pedidoService.listarPedidos();
					pedidos.forEach(System.out::println);
					break;
				case 4:
					System.out.print("Digite o id do pedido que deseja pagar: ");
					Long idPagar = entrada.nextLong();
					Pedido pedidoPagar = pedidoService.buscarPorId(idPagar);

					if (pedidoPagar == null) {
					    System.out.println("Pedido não encontrado com id " + idPagar);
					} else {
					    try {
					        pedidoService.realizarPagamento(pedidoPagar);
					        System.out.println("Pagamento realizado com sucesso!");
					    } catch (IllegalStateException e) {
					        System.out.println("Erro: " + e.getMessage());
					    }
					}
					
					pedidoService.realizarEntrega(pedidoPagar);
					break;
				case 5:
					executando = false;
					break;
			}
		}
	}
	
	private static void menuPedido(Scanner entrada, Pedido pedidoTemp) {
		boolean executando = true;
		Pedido pedido;
		if (pedidoTemp == null) {
			System.out.print("Digite o seu cpf: ");
			String cpf = entrada.nextLine();
			Cliente cliente = clienteService.buscarClientePorCpf(cpf);
			pedido = pedidoService.criarPedido(cliente);
		} else {
			pedido = pedidoTemp;
		}
		while (executando) {
			System.out.print(Menu.gerarMenuPedido());
			int opcao = entrada.nextInt();
			entrada.nextLine();
			switch (opcao) {
				case 1:
					System.out.print("Digite o seu nome do produto: ");
					String nomeProdutoAdicionar = entrada.nextLine();
					System.out.print("Digite a quantidade do produto: ");
					int qtdAdicionar = entrada.nextInt();
					Produto produtoAdicionar = produtoService.buscarPorNome(nomeProdutoAdicionar);
					pedidoService.adicionarItem(pedido, produtoAdicionar, qtdAdicionar);
					break;
				case 2:
					System.out.print("Digite o seu nome do produto: ");
					String nomeProdutoAtualizar = entrada.nextLine();
					System.out.print("Digite a quantidade do produto: ");
					int qtdAtualizar = entrada.nextInt();
					Produto produtoAtualizar = produtoService.buscarPorNome(nomeProdutoAtualizar);
					pedidoService.alterarQuantidade(pedido, produtoAtualizar, qtdAtualizar);
					break;
				case 3:
					System.out.print("Digite o seu nome do produto: ");
					String nomeProdutoRemover = entrada.nextLine();
					Produto produtoRemover = produtoService.buscarPorNome(nomeProdutoRemover);
					pedidoService.removerItem(pedido, produtoRemover);
					break;
				case 4:
					executando = false;
					break;
			}
		}
	}
	
	private static Cliente lerCliente(Scanner entrada) {
		System.out.println("Digite o nome do cliente: ");
		String nome = entrada.nextLine();
		System.out.println("Digite o cpf do cliente: ");
		String cpf = entrada.nextLine();
		System.out.println("Digite o email do cliente: ");
		String email = entrada.nextLine();
		System.out.println("Digite o logradouro do endereço do cliente: ");
		String logradouro = entrada.nextLine();
		System.out.println("Digite o bairro do endereço do cliente: ");
		String bairro = entrada.nextLine();
		System.out.println("Digite o número do endereço do cliente: ");
		int numero = entrada.nextInt();
		entrada.nextLine();
		System.out.println("Digite o complemento do endereço do cliente: ");
		String complemento = entrada.nextLine();
		System.out.println("Digite a cidade do endereço do cliente: ");
		String cidade = entrada.nextLine();
		System.out.println("Digite o cep do endereço do cliente: 00000-000 ");
		String cep = entrada.nextLine();
		System.out.println("Digite a sigla do estado do endereço do cliente: ");
		String estado = entrada.nextLine().toUpperCase();
		
		Endereco endereco = new Endereco(logradouro, bairro, numero, complemento, cidade, cep, EstadoEnum.valueOf(estado));
		return new Cliente(cpf, nome, email, endereco);
	}
	
	private static void editarCliente(Scanner entrada) {
		String escolha;
		
		System.out.print("Digite o cpf do cliente que deseja atualizar: ");
		String cpf = entrada.nextLine();
		System.out.print("Deseja atualizar o nome? (S/N) ");
		escolha = entrada.nextLine();
		String nome;
		if (escolha.toLowerCase().equals("n")) {
			nome = null;
		} else {
			System.out.print("Digite o novo nome: ");
			nome = entrada.nextLine();
		}
		System.out.print("Deseja atualizar o email? (S/N) ");
		escolha = entrada.nextLine();
		String email;
		if (escolha.toLowerCase().equals("n")) {
			email = null;
		} else {
			System.out.print("Digite o novo email: ");
			email = entrada.nextLine();
		}
		System.out.print("Deseja atualizar o endereco? (S/N) ");
		escolha = entrada.nextLine();
		Endereco endereco;
		if (escolha.toLowerCase().equals("n")) {
			endereco = null;
		} else {
			System.out.print("Digite o novo logradouro do cliente: ");
			String logradouro = entrada.nextLine();
			System.out.print("Digite o novo bairro do cliente: ");
			String bairro = entrada.nextLine();
			System.out.print("Digite o novo número do cliente: ");
			int numero = entrada.nextInt();
			entrada.nextLine();
			System.out.print("Digite o novo complemento do cliente: ");
			String complemento = entrada.nextLine();
			System.out.print("Digite a nova cidade do cliente: ");
			String cidade = entrada.nextLine();
			System.out.print("Digite o novo cep do cliente: ");
			String cep = entrada.nextLine();
			System.out.print("Digite a nova sigla do estado do cliente: ");
			String estado = entrada.nextLine().toUpperCase();
			
			endereco = new Endereco(logradouro, bairro, numero, complemento, cidade, cep, EstadoEnum.valueOf(estado));
		}
		
		clienteService.editarCliente(cpf, nome, email, endereco);
	}
	
	private static Produto lerProduto(Scanner entrada) {
		System.out.print("Digite o nome do produto: ");
		String nome = entrada.nextLine();
		System.out.print("Digite a descrição do produto: ");
		String descricao = entrada.nextLine();
		System.out.print("Digite o preço do produto: ");
		double preco = entrada.nextDouble();
		
		return new Produto(nome, descricao, preco);
	}
	
	private static void editarProduto(Scanner entrada) {
		String escolha;
		
		System.out.print("Digite o nome do produto que deseja atualizar: ");
		String nome = entrada.nextLine();
		System.out.print("Deseja atualizar a descricao? (S/N) ");
		escolha = entrada.nextLine();
		String descricao;
		if (escolha.toLowerCase().equals("n")) {
			descricao = null;
		} else {
			System.out.print("Digite a nova descricao: ");
			descricao = entrada.nextLine();
		}
		System.out.print("Deseja atualizar a preco? (S/N) ");
		escolha = entrada.nextLine();
		double preco;
		if (escolha.toLowerCase().equals("n")) {
			preco = 0.0;
		} else {
			System.out.print("Digite o novo preco: ");
			preco = entrada.nextDouble();
		}
		
		produtoService.atualizarProduto(produtoService.buscarPorNome(nome), descricao, preco);
	}
}