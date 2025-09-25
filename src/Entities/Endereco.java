package Entities;

import Enum.EstadoEnum;

public class Endereco {

	private String logradouro;
	private String bairro;
	private int numero;
	private String complemento;
	private String cidade;
	private String cep;
	private EstadoEnum estado;
	
	
	public Endereco(String logradouro, String bairro, int numero, String complemento, String cidade, String cep,
	        EstadoEnum estado) {
	    setLogradouro(logradouro);
	    setBairro(bairro);
	    setNumero(numero);
	    setComplemento(complemento);
	    setCidade(cidade);
	    setCep(cep);
	    setEstadoEnum(estado);
	}

	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		if(logradouro == null || logradouro.trim().isEmpty()) {
			throw new IllegalArgumentException("Endereço de preenchimento obrigatório.");
		}
		this.logradouro = logradouro;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		if(bairro == null || bairro.trim().isEmpty()) {
			throw new IllegalArgumentException("Bairro de preenchimento obrigatório.");
		}
		this.bairro = bairro;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		if(numero < 0) {
			throw new IllegalArgumentException("Número não pode ser negativo.");
		}
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		if(cidade == null || cidade.trim().isEmpty()) {
			throw new IllegalArgumentException("Cidade de preenchimento obrigatório.");
		}
		this.cidade = cidade;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		if(cep == null || cep.trim().isEmpty()) {
			throw new IllegalArgumentException("Cep de preenchimento obrigatório.");
		}
		if(!cep.matches("^\\d{5}-\\d{3}$")) {
			throw new IllegalArgumentException("CEP inválido. Use o formato 00000-000.");
		}
		this.cep = cep;
	}


	public EstadoEnum getEstado() {
		return estado;
	}


	public void setEstadoEnum(EstadoEnum estado) {
		if(estado == null) {
			throw new IllegalArgumentException("Estado de preenchimento obrigatório.");
		}
		this.estado = estado;
	}


	@Override
	public String toString() {
	    return String.format(
	        "Logradouro: %s, \nBairro: %s, \nNumero: %d, \nComplemento: %s, \nCidade: %s, \nCEP: %s, \nEstado: %s",
	        logradouro, bairro, numero, complemento, cidade, cep, estado
	    );
	}

	
}
