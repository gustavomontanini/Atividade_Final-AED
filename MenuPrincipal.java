import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaDinamica fila = new FilaDinamica();
        int opcao = -1;

        do {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o elemento a inserir: ");
                        String elementoInserir = scanner.nextLine();
                        try {
                            fila.inserirElemento(Integer.parseInt(elementoInserir));
                        } catch (NumberFormatException e) {
                            fila.inserirElemento(elementoInserir);
                        }
                        break;
                    case 2:
                        System.out.print("Digite os elementos separados por vírgula (ex: A,B,C ou 1,2,3): ");
                        String sequenciaStr = scanner.nextLine();
                        String[] elementosArrayStr = sequenciaStr.split(",");
                        Object[] elementosInserir = new Object[elementosArrayStr.length];
                        for(int i=0; i < elementosArrayStr.length; i++) {
                            try {
                                elementosInserir[i] = Integer.parseInt(elementosArrayStr[i].trim());
                            } catch (NumberFormatException e) {
                                elementosInserir[i] = elementosArrayStr[i].trim();
                            }
                        }
                        fila.inserirSequencia(elementosInserir);
                        break;
                    case 3:
                        System.out.print("Digite o elemento a remover: ");
                        String elementoRemoverStr = scanner.nextLine();
                        Object elementoRemover;
                        try {
                            elementoRemover = Integer.parseInt(elementoRemoverStr);
                        } catch (NumberFormatException e) {
                            elementoRemover = elementoRemoverStr;
                        }
                        if (fila.removerElemento(elementoRemover)) {
                            System.out.println("Elemento removido com sucesso.");
                        } else {
                            System.out.println("Elemento não encontrado ou fila vazia.");
                        }
                        break;
                    case 4:
                        System.out.print("Digite os elementos da sequência a remover, separados por vírgula: ");
                        String seqRemoverStr = scanner.nextLine();
                        String[] elementosRemoverArrayStr = seqRemoverStr.split(",");
                        Object[] elementosRemoverSeq = new Object[elementosRemoverArrayStr.length];
                        for(int i=0; i < elementosRemoverArrayStr.length; i++) {
                            try {
                                elementosRemoverSeq[i] = Integer.parseInt(elementosRemoverArrayStr[i].trim());
                            } catch (NumberFormatException e) {
                                elementosRemoverSeq[i] = elementosRemoverArrayStr[i].trim();
                            }
                        }
                        fila.removerSequencia(elementosRemoverSeq);
                        System.out.println("Tentativa de remoção da sequência concluída.");
                        break;
                    case 5:
                        System.out.print("Digite o elemento para remover todas as ocorrências: ");
                        String elementoRemoverTodosStr = scanner.nextLine();
                        Object elementoRemoverTodos;
                        try {
                            elementoRemoverTodos = Integer.parseInt(elementoRemoverTodosStr);
                        } catch (NumberFormatException e) {
                            elementoRemoverTodos = elementoRemoverTodosStr;
                        }
                        fila.removerTodasOcorrencias(elementoRemoverTodos);
                        System.out.println("Remoção de todas as ocorrências concluída.");
                        break;
                    case 6:
                        System.out.println("A fila está cheia? " + fila.estaCheia());
                        break;
                    case 7:
                        System.out.println("A fila está vazia? " + fila.estaVazia());
                        break;
                    case 8:
                        System.out.print("Digite o elemento a buscar: ");
                        String elementoBuscarStr = scanner.nextLine();
                        Object elementoBuscar;
                        try {
                            elementoBuscar = Integer.parseInt(elementoBuscarStr);
                        } catch (NumberFormatException e) {
                            elementoBuscar = elementoBuscarStr;
                        }
                        if (fila.buscarElemento(elementoBuscar)) {
                            System.out.println("Elemento encontrado na fila.");
                        } else {
                            System.out.println("Elemento não encontrado.");
                        }
                        break;
                    case 9:
                        fila.ordenarCrescente();
                        break;
                    case 10:
                        fila.ordenarDecrescente();
                        break;
                    case 11:
                        System.out.println("Quantidade de elementos: " + fila.quantidadeElementos());
                        break;
                    case 12:
                        System.out.print("Digite o elemento antigo: ");
                        String antigoStr = scanner.nextLine();
                        Object antigo;
                        try { antigo = Integer.parseInt(antigoStr); } catch (NumberFormatException e) { antigo = antigoStr; }

                        System.out.print("Digite o novo elemento: ");
                        String novoStr = scanner.nextLine();
                        Object novo;
                        try { novo = Integer.parseInt(novoStr); } catch (NumberFormatException e) { novo = novoStr; }

                        fila.editarElemento(antigo, novo);
                        break;
                    case 13:
                        fila.limpar();
                        break;
                    case 14:
                        fila.exibir();
                        break;
                    case 15:
                        No primeiro = fila.obterPrimeiroElemento();
                        System.out.println("Primeiro elemento (nó): " + ((primeiro != null) ? primeiro.getConteudo() : "Nenhum (fila vazia)"));
                        break;
                    case 16:
                        No ultimo = fila.obterUltimoElemento();
                        System.out.println("Último elemento (nó): " + ((ultimo != null) ? ultimo.getConteudo() : "Nenhum (fila vazia)"));
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite um número válido para a opção.");
                scanner.nextLine();
                opcao = -1;
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 0);

        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n--- Menu Fila Dinâmica ---");
        System.out.println("1. Inserir Elemento");
        System.out.println("2. Inserir Sequência (separada por vírgula)");
        System.out.println("3. Remover Elemento (primeira ocorrência)");
        System.out.println("4. Remover Sequência (elementos separados por vírgula)");
        System.out.println("5. Remover Todas Ocorrências de um Elemento");
        System.out.println("6. Verificar se está Cheia");
        System.out.println("7. Verificar se está Vazia");
        System.out.println("8. Buscar Elemento");
        System.out.println("9. Ordenar Crescente (elementos devem ser comparáveis)");
        System.out.println("10. Ordenar Decrescente (elementos devem ser comparáveis)");
        System.out.println("11. Quantidade de Elementos");
        System.out.println("12. Editar Elemento (primeira ocorrência)");
        System.out.println("13. Limpar Fila");
        System.out.println("14. Exibir Fila");
        System.out.println("15. Obter Primeiro Elemento");
        System.out.println("16. Obter Último Elemento");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
}