import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nomeCliente;
        int opcao;
        int quantidadeCamiseta = 0;
        int quantidadeCalca = 0;
        int quantidadeMoletom = 0;

        final double PRECO_CAMISETA = 49.90;
        final double PRECO_CALCA = 89.90;
        final double PRECO_MOLETOM = 119.90;

        System.out.println("==== SISTEMA DE LOJA DE ROUPAS ====");
        System.out.print("Digite o nome do cliente: ");
        nomeCliente = scanner.nextLine();

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Quantas camisetas deseja adicionar? ");
                    int qtdCamiseta = scanner.nextInt();

                    if (qtdCamiseta > 0) {
                        quantidadeCamiseta += qtdCamiseta;
                        System.out.println("Camiseta adicionada ao pedido.\n");
                    } else {
                        System.out.println("Quantidade inválida.\n");
                    }
                    break;

                case 2:
                    System.out.print("Quantas calças deseja adicionar? ");
                    int qtdCalca = scanner.nextInt();

                    if (qtdCalca > 0) {
                        quantidadeCalca += qtdCalca;
                        System.out.println("Calça adicionada ao pedido.\n");
                    } else {
                        System.out.println("Quantidade inválida.\n");
                    }
                    break;

                case 3:
                    System.out.print("Quantos moletons deseja adicionar? ");
                    int qtdMoletom = scanner.nextInt();

                    if (qtdMoletom > 0) {
                        quantidadeMoletom += qtdMoletom;
                        System.out.println("Moletom adicionado ao pedido.\n");
                    } else {
                        System.out.println("Quantidade inválida.\n");
                    }
                    break;

                case 4:
                    mostrarResumo(
                            nomeCliente,
                            quantidadeCamiseta,
                            quantidadeCalca,
                            quantidadeMoletom,
                            PRECO_CAMISETA,
                            PRECO_CALCA,
                            PRECO_MOLETOM
                    );
                    break;

                case 5:
                    finalizarPedido(
                            scanner,
                            nomeCliente,
                            quantidadeCamiseta,
                            quantidadeCalca,
                            quantidadeMoletom,
                            PRECO_CAMISETA,
                            PRECO_CALCA,
                            PRECO_MOLETOM
                    );
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.\n");
            }

        } while (opcao != 0);

        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("==== MENU ====");
        System.out.println("1 - Adicionar Camiseta (R$ 49,90)");
        System.out.println("2 - Adicionar Calça (R$ 89,90)");
        System.out.println("3 - Adicionar Moletom (R$ 119,90)");
        System.out.println("4 - Ver resumo do pedido");
        System.out.println("5 - Finalizar pedido");
        System.out.println("0 - Sair");
    }

    public static double calcularTotal(
            int quantidadeCamiseta,
            int quantidadeCalca,
            int quantidadeMoletom,
            double precoCamiseta,
            double precoCalca,
            double precoMoletom
    ) {
        return (quantidadeCamiseta * precoCamiseta)
                + (quantidadeCalca * precoCalca)
                + (quantidadeMoletom * precoMoletom);
    }

    public static double aplicarDesconto(double total) {
        if (total >= 200) {
            return total * 0.90;
        } else {
            return total;
        }
    }

    public static void mostrarResumo(
            String nomeCliente,
            int quantidadeCamiseta,
            int quantidadeCalca,
            int quantidadeMoletom,
            double precoCamiseta,
            double precoCalca,
            double precoMoletom
    ) {
        double total = calcularTotal(
                quantidadeCamiseta,
                quantidadeCalca,
                quantidadeMoletom,
                precoCamiseta,
                precoCalca,
                precoMoletom
        );

        double totalComDesconto = aplicarDesconto(total);

        System.out.println("\n==== RESUMO DO PEDIDO ====");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Camisetas: " + quantidadeCamiseta);
        System.out.println("Calças: " + quantidadeCalca);
        System.out.println("Moletons: " + quantidadeMoletom);
        System.out.printf("Subtotal: R$ %.2f%n", total);

        if (total >= 200) {
            System.out.println("Desconto aplicado: 10%");
            System.out.printf("Total com desconto: R$ %.2f%n", totalComDesconto);
        } else {
            System.out.println("Desconto não disponível.");
            System.out.printf("Total final: R$ %.2f%n", totalComDesconto);
        }

        System.out.println();
    }

    public static void finalizarPedido(
            Scanner scanner,
            String nomeCliente,
            int quantidadeCamiseta,
            int quantidadeCalca,
            int quantidadeMoletom,
            double precoCamiseta,
            double precoCalca,
            double precoMoletom
    ) {
        double total = calcularTotal(
                quantidadeCamiseta,
                quantidadeCalca,
                quantidadeMoletom,
                precoCamiseta,
                precoCalca,
                precoMoletom
        );

        double totalFinal = aplicarDesconto(total);

        if (totalFinal == 0) {
            System.out.println("\nNenhum item foi adicionado ao pedido.\n");
            return;
        }

        System.out.println("\n==== FINALIZAÇÃO DO PEDIDO ====");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("1 - Dinheiro");
        System.out.println("2 - Cartão");
        System.out.print("Escolha a forma de pagamento: ");
        int formaPagamento = scanner.nextInt();

        switch (formaPagamento) {
            case 1:
                System.out.printf("Total a pagar: R$ %.2f%n", totalFinal);
                System.out.print("Digite o valor pago: R$ ");
                double valorPago = scanner.nextDouble();

                if (valorPago < totalFinal) {
                    System.out.println("Valor insuficiente. Pedido não finalizado.\n");
                } else {
                    double troco = valorPago - totalFinal;

                    System.out.println("\n==== PEDIDO FINALIZADO ====");
                    System.out.println("Cliente: " + nomeCliente);
                    System.out.println("Forma de pagamento: Dinheiro");
                    System.out.printf("Valor final: R$ %.2f%n", totalFinal);
                    System.out.printf("Troco: R$ %.2f%n", troco);
                    System.out.println("Obrigado pela compra!\n");
                }
                break;

            case 2:
                System.out.println("\n==== PEDIDO FINALIZADO ====");
                System.out.println("Cliente: " + nomeCliente);
                System.out.println("Forma de pagamento: Cartão");
                System.out.printf("Valor final: R$ %.2f%n", totalFinal);
                System.out.println("Obrigado pela compra!\n");
                break;

            default:
                System.out.println("Forma de pagamento inválida.\n");
        }
    }
}