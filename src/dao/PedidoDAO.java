package dao;

import Entities.Pedido;
import Entities.Cliente;
import Enum.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PedidoDAO {

    private List<Pedido> pedidos = new ArrayList<>();
    private long contadorId = 1; 

    public void salvar(Pedido p) {
        if (p.getId() == null) {
            p.setId(contadorId++);
        }
        pedidos.add(p);
    }

    public void atualizar(Pedido p) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (Objects.equals(pedidos.get(i).getId(), p.getId())) {
                pedidos.set(i, p);
                return;
            }
        }
        throw new IllegalArgumentException("Pedido com id " + p.getId() + " não encontrado");
    }

    public Pedido buscarPorId(Long id) {
        for (Pedido p : pedidos) {
            if (Objects.equals(p.getId(), id)) {
                return p;
            }
        }
        return null;
    }

    public List<Pedido> listarTodos() {
        return new ArrayList<>(pedidos);
    }

    public List<Pedido> listarPorCliente(Cliente cliente) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (Objects.equals(p.getCliente().getCpf(), cliente.getCpf())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Pedido> listarPorStatus(Status status) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getStatus() == status) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
