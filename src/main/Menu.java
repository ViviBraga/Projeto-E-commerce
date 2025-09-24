package main;

public class Menu {
	
	public static String gerarMenuPrincipal() {
		String menuPrincipal = "E-Commerce - Menu Principal\n" + 
				"1. Menu Cliente\n" +
				"2. Menu Produto\n" +
				"3. Menu Pedidos\n" +
				"4. Sair\n\n" +
				"Digite o número do menu que deseja acessar: ";
		
		return menuPrincipal;
	}
	
	public static String gerarMenuCliente() {
		String menuCliente = "E-Commerce - Menu Cliente\n" + 
				"1. Cadastra cliente\n" +
				"2. Listar clientes\n" +
				"3. Atualizar cliente\n" +
				"4. Voltar\n\n" +
				"Digite o número da opção que deseja fazer: ";
		
		return menuCliente;
	}
	
	public static String gerarMenuProduto() {
		String menuProduto = "E-Commerce - Menu Produto\n" + 
				"1. Cadastra produto\n" +
				"2. Listar produtos\n" +
				"3. Atualizar produto\n" +
				"4. Voltar\n\n" +
				"Digite o número da opção que deseja fazer: ";
		
		return menuProduto;
	}
	
	public static String gerarMenuPedidos() {
		String menuPedidos = "E-Commerce - Menu Pedidos\n" + 
				"1. Criar pedido\n" +
				"2. Acessar pedido\n" +
				"3. Listar pedidos\n" +
				"4. Realizar pagamento\n" +
				"5. Voltar\n\n" +
				"Digite o número da opção que deseja fazer: ";
		
		return menuPedidos;
	}
	
	public static String gerarMenuPedido() {
		String menuPedido = "E-Commerce - Menu Pedidos\n" + 
				"1. Adicionar item\n" +
				"2. Alterar quantidade\n" +
				"3. Remover item\n" +
				"4. Voltar\n\n" +
				"Digite o número da opção que deseja fazer: ";
		
		return menuPedido;
	}

}
