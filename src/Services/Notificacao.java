package Services;

public class Notificacao {

    private String emailCliente;

    public Notificacao(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    private void enviarEmail(String assunto, String mensagem) {
       
        System.out.println("Enviando e-mail para: " + emailCliente);
        System.out.println("Assunto: " + assunto);
        System.out.println("Mensagem: " + mensagem);
        System.out.println("E-mail enviado com sucesso.\n");
    }

    public void notificarFinalizacaoPedido(int pedidoId) {
        String assunto = "Pedido Finalizado";
        String mensagem = "Seu pedido #" + pedidoId + " foi finalizado e está aguardando pagamento.";
        enviarEmail(assunto, mensagem);
    }

    public void notificarPagamentoRealizado(int pedidoId) {
        String assunto = "Pagamento Confirmado";
        String mensagem = "O pagamento do pedido #" + pedidoId + " foi confirmado. Obrigado pela compra!";
        enviarEmail(assunto, mensagem);
    }

    public void notificarEntregaRealizada(int pedidoId) {
        String assunto = "Pedido Entregue";
        String mensagem = "Seu pedido #" + pedidoId + " foi entregue com sucesso. Esperamos que goste!";
        enviarEmail(assunto, mensagem);
    }
}
