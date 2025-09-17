package Entities;

public class Cliente {
	private String cpf;
	private String nome;
	private String email;
	private Endereco endereco;
	
	public Cliente(String cpf, String nome, String email, Endereco endereco) {
	    setCpf(cpf);
	    setNome(nome);
	    setEmail(email);
	    setEndereco(endereco);
	}


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (!isCpfValido(cpf)) {
	        throw new IllegalArgumentException("CPF inválido.");
	    }
	    this.cpf = cpf.replaceAll("\\D", "");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome preenchimento obrigatório.");
		}
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
		    throw new IllegalArgumentException("E-mail inválido.");
		}
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		if (endereco == null) {
		    throw new IllegalArgumentException("Endereço de preenchimento obrigatório.");
		}
		this.endereco = endereco;
	}
	
	public static boolean isCpfValido(String cpf) {
	    if (cpf == null) return false;

	    cpf = cpf.replaceAll("\\D", "");

	    if (cpf.length() != 11) return false;

	    if (cpf.matches("(\\d)\\1{10}")) return false;

	    try {
	        int soma = 0;
	        for (int i = 0; i < 9; i++) {
	            soma += (cpf.charAt(i) - '0') * (10 - i);
	        }
	        int digito1 = 11 - (soma % 11);
	        digito1 = (digito1 > 9) ? 0 : digito1;

	        soma = 0;
	        for (int i = 0; i < 10; i++) {
	            soma += (cpf.charAt(i) - '0') * (11 - i);
	        }
	        int digito2 = 11 - (soma % 11);
	        digito2 = (digito2 > 9) ? 0 : digito2;

	        return digito1 == (cpf.charAt(9) - '0') && digito2 == (cpf.charAt(10) - '0');
	    } catch (Exception e) {
	        return false;
	    }
	}

	@Override
	public String toString() {
	    return String.format(
	        "Cliente [nome=%s, cpf=%s, email=%s, endereco=%s]",
	        nome, cpf, email, endereco
	    );
	}

}
