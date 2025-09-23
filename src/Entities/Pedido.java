package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Enum.Status;

public class Pedido {
    private Long id; 
    private Cliente cliente;
    private LocalDateTime dataCriacao;
    private Status status;
    private List<ItemPedido> itens;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.dataCriacao = LocalDateTime.now();
        this.status = Status.ABERTO;
        this.itens = new ArrayList<>();
    }


    public void adicionarItem(Produto produto, int quantidade) {
        if (status != Status.ABERTO) {
            throw new IllegalStateException("Só se pode adicionar itens em pedidos ABERTOS");
        }
        for (ItemPedido ip : itens) {
        	if (Objects.equals(ip.getProduto().getId(), produto.getId())) {
        	    ip.setQuantidade(ip.getQuantidade() + quantidade);
        	    return;
        	}

        }
        ItemPedido novo = new ItemPedido(produto, quantidade);
        itens.add(novo);
    }

    public void removerItem(Produto produto) {
        if (status != Status.ABERTO) {
            throw new IllegalStateException("Só se pode remover itens em pedidos ABERTOS");
        }
        itens.removeIf(ip -> Objects.equals(ip.getProduto().getId(), produto.getId()));
    }

    public void alterarQuantidade(Produto produto, int novaQuantidade) {
        if (status != Status.ABERTO) {
            throw new IllegalStateException("Só se pode alterar quantidade em pedidos ABERTOS");
        }
        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser > 0");
        }
        for (ItemPedido ip : itens) {
            if (Objects.equals(ip.getProduto().getId(), produto.getId())) {
                ip.setQuantidade(novaQuantidade);
                return;
            }
        }
        throw new IllegalArgumentException("Produto não encontrado no pedido");
    }

    public double calcularTotal() {
        double soma = 0;
        for (ItemPedido ip : itens) {
            soma += ip.getSubtotal();
        }
        return soma;
    }

    public void realizarPagamento() {
        if (status != Status.ABERTO) {
            throw new IllegalStateException("Pagamento só permitido se pedido estiver ABERTO");
        }
        if (itens.isEmpty()) {
            throw new IllegalStateException("Não pode pagar um pedido sem itens");
        }
        if (calcularTotal() <= 0) {
            throw new IllegalStateException("Valor total deve ser maior que zero");
        }
        status = Status.PAGO;
    }

    public void realizarEntrega() {
        if (status != Status.PAGO) {
            throw new IllegalStateException("Entrega só permitido se pagamento estiver realizado");
        }
        status = Status.ENTREGUE;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public List<ItemPedido> getItens() {
		return itens;
	}


	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

}
