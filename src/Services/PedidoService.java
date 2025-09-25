package Services;

import java.util.List;

import Entities.Cliente;
import Entities.Pedido;
import Entities.Produto;
import dao.PedidoDAO;

public class PedidoService {
    private PedidoDAO pedidoDAO;

    public PedidoService(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public Pedido criarPedido(Cliente cliente) {
        Pedido p = new Pedido(cliente);
        pedidoDAO.salvar(p);

        return p;
    }

    public void adicionarItem(Pedido pedido, Produto produto, int quantidade) {
        pedido.adicionarItem(produto, quantidade);
        pedidoDAO.atualizar(pedido);
        Notificacao notificacao = new Notificacao(pedido.getCliente().getEmail());
        notificacao.notificarFinalizacaoPedido(pedido.getId().intValue());
    }

    public void alterarQuantidade(Pedido pedido, Produto produto, int quantidade) {
        pedido.alterarQuantidade(produto, quantidade);
        pedidoDAO.atualizar(pedido);
    }

    public void removerItem(Pedido pedido, Produto produto) {
        pedido.removerItem(produto);
        pedidoDAO.atualizar(pedido);
    }

    public void realizarPagamento(Pedido pedido) {
        pedido.realizarPagamento();
        pedidoDAO.atualizar(pedido);
        Notificacao notificacao = new Notificacao(pedido.getCliente().getEmail());
        notificacao.notificarPagamentoRealizado(pedido.getId().intValue());

    }

    public void realizarEntrega(Pedido pedido) {
        pedido.realizarEntrega();
        pedidoDAO.atualizar(pedido);
        Notificacao notificacao = new Notificacao(pedido.getCliente().getEmail());
        notificacao.notificarEntregaRealizada(pedido.getId().intValue());

    }

    public List<Pedido> listarPedidos() {
        return pedidoDAO.listarTodos();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoDAO.buscarPorId(id);
    }
}
