package Enum;


public enum Status {
    ABERTO("Aberto"),
    AGUARDANDO_PAGAMENTO("Aguardando pagamento"),
    PAGO("Pago"),
    ENTREGUE("Entregue");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
