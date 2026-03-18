import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        int quantidadeCamiseta = 0;
        int quantidadeCalca = 0;
        int quantidadeMoletom = 0;

        double precoCamiseta = 49.90;
        double precoCalca = 89.90;
        double precoMoletom = 119.90;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Quantas camisetas deseja adicionar? ");
                    quantidadeCamiseta += scanner.nextInt();
                    System.out.println("Camiseta adicionada ao pedido.\n");
                    break;

                case 2:
                    System.out.print("Quantas calças deseja adicionar? ");
                    quantidadeCalca += scanner.nextInt();
                    System.out.println("Calça adicionada ao pedido.\n");
                    break;

                case 3:
                    System.out.print("Quantos moletons deseja adicionar? ");
                    quantidadeMoletom += scanner.nextInt();
                    System.out.println("Moletom adicionado ao pedido.\n");
                    break;

                case 4:
                    mostrarResumo(
                            quantidadeCamiseta, quantidadeCalca, quantidadeMoletom,
                            precoCamiseta, precoCalca, precoMoletom
                    );
                    break;

                case 5:
                    finalizarPedido(
                            quantidadeCamiseta, quantidadeCalca, quantidadeMoletom,
                            precoCamiseta, precoCalca, precoMoletom
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
        System.out.println("==== LOJA DE ROUPAS ====");
        System.out.println("1 - Adicionar Camiseta (R$ 49,90)");
        System.out.println("2 - Adicionar Calça (R$ 89,90)");
        System.out.println("3 - Adicionar Moletom (R$ 119,90)");
        System.out.println("4 - Ver resumo do pedido");
        System.out.println("5 - Finalizar pedido");
        System.out.println("0 - Sair");
    }

    public static double calcularTotal(
            int quantidadeCamiseta, int quantidadeCalca, int quantidadeMoletom,
            double precoCamiseta, double precoCalca, double precoMoletom
    ) {
        return (quantidadeCamiseta * precoCamiseta) +
                (quantidadeCalca * precoCalca) +
                (quantidadeMoletom * precoMoletom);
    }

    public static double aplicarDesconto(double total) {
        if (total >= 200) {
            return total * 0.90; // 10% de desconto
        } else {
            return total;
        }
    }

    public static void mostrarResumo(
            int quantidadeCamiseta, int quantidadeCalca, int quantidadeMoletom,
            double precoCamiseta, double precoCalca, double precoMoletom
    ) {
        double total = calcularTotal(
                quantidadeCamiseta, quantidadeCalca, quantidadeMoletom,
                precoCamiseta, precoCalca, precoMoletom
        );

        System.out.println("\n==== RESUMO DO PEDIDO ====");
        System.out.println("Camisetas: " + quantidadeCamiseta);
        System.out.println("Calças: " + quantidadeCalca);
        System.out.println("Moletons: " + quantidadeMoletom);
        System.out.printf("Subtotal: R$ %.2f%n", total);

        if (total >= 200) {
            System.out.println("Desconto disponível: 10%");
            System.out.printf("Total com desconto: R$ %.2f%n", aplicarDesconto(total));
        } else {
            System.out.println("Desconto não disponível.");
        }

        System.out.println();
    }

    public static void finalizarPedido(
            int quantidadeCamiseta, int quantidadeCalca, int quantidadeMoletom,
            double precoCamiseta, double precoCalca, double precoMoletom
    ) {
        double total = calcularTotal(
                quantidadeCamiseta, quantidadeCalca, quantidadeMoletom,
                precoCamiseta, precoCalca, precoMoletom
        );

        double totalFinal = aplicarDesconto(total);

        System.out.println("\n==== PEDIDO FINALIZADO ====");
        System.out.println("Itens comprados:");
        System.out.println("Camisetas: " + quantidadeCamiseta);
        System.out.println("Calças: " + quantidadeCalca);
        System.out.println("Moletons: " + quantidadeMoletom);
        System.out.printf("Valor final: R$ %.2f%n", totalFinal);
        System.out.println("Obrigado pela compra!\n");
    }
}