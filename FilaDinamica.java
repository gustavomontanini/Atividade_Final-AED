import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class FilaDinamica implements IEstruturaDinamica {

    private No inicio;
    private No fim;
    private int tamanho;

    public FilaDinamica() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    @Override
    public void inserirElemento(Object elemento) {
        if (elemento == null) {
            System.out.println("Aviso: Não é permitido inserir elemento nulo.");
            return;
        }
        No novoNo = new No(elemento);
        if (estaVazia()) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            fim.setProx(novoNo);
            novoNo.setAnterior(fim);
            fim = novoNo;
        }
        tamanho++;
    }

    @Override
    public void inserirSequencia(Object elementos) {
        if (elementos instanceof Object[]) {
            Object[] arrayElementos = (Object[]) elementos;
            for (Object elemento : arrayElementos) {
                inserirElemento(elemento);
            }
        } else {
            System.out.println("Erro: O parâmetro 'elementos' deve ser um array de Object (Object[]).");
        }
    }

    @Override
    public boolean removerElemento(Object elemento) {
        if (estaVazia() || elemento == null) {
            return false;
        }

        No atual = inicio;
        while (atual != null) {
            if (Objects.equals(atual.getConteudo(), elemento)) {
                No anterior = atual.getAnterior();
                No proximo = atual.getProx();

                if (anterior == null) { // Removendo o início da fila
                    inicio = proximo;
                    if (inicio != null) {
                        inicio.setAnterior(null);
                    } else {
                        fim = null;
                    }
                } else { // Removendo do meio ou fim
                    anterior.setProx(proximo);
                    if (proximo != null) {
                        proximo.setAnterior(anterior);
                    } else {
                        fim = anterior; // Removendo o último elemento, atualiza o fim
                    }
                }

                atual.setProx(null);
                atual.setAnterior(null);

                tamanho--;
                return true;
            }
            atual = atual.getProx();
        }
        return false;
    }

    @Override
    public void removerSequencia(Object elementos) {
        if (elementos instanceof Object[]) {
            Object[] arrayElementos = (Object[]) elementos;
            for (Object elemento : arrayElementos) {
                removerElemento(elemento);
            }
        } else {
            System.out.println("Erro: O parâmetro 'elementos' deve ser um array de Object (Object[]).");
        }
    }

    @Override
    public void removerTodasOcorrencias(Object elemento) {
        if (estaVazia() || elemento == null) {
            return;
        }

        No atual = inicio;
        while (atual != null) {
            No proximo = atual.getProx();
            if (Objects.equals(atual.getConteudo(), elemento)) {
                No anterior = atual.getAnterior();

                if (anterior == null) { // Removendo o início
                    inicio = proximo;
                    if (inicio != null) {
                        inicio.setAnterior(null);
                    } else {
                        fim = null;
                    }
                } else { // Removendo do meio ou fim
                    anterior.setProx(proximo);
                    if (proximo != null) {
                        proximo.setAnterior(anterior);
                    } else {
                        fim = anterior; // Removendo o último
                    }
                }
                atual.setProx(null);
                atual.setAnterior(null);
                tamanho--;
            }
            atual = proximo;
        }
    }


    @Override
    public boolean estaCheia() {
        return false;
    }

    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }

    @Override
    public boolean buscarElemento(Object elemento) {
        if (elemento == null) return false;
        No atual = inicio;
        while (atual != null) {
            if (Objects.equals(atual.getConteudo(), elemento)) {
                return true;
            }
            atual = atual.getProx();
        }
        return false;
    }

    private void ordenar(Comparator<Object> comparador) {
        if (tamanho < 2) {
            return;
        }

        boolean trocou;
        do {
            trocou = false;
            No atual = inicio;
            while (atual != null && atual.getProx() != null) {
                No proximo = atual.getProx();
                try {
                    if (comparador.compare(atual.getConteudo(), proximo.getConteudo()) > 0) {
                        Object temp = atual.getConteudo();
                        atual.setConteudo(proximo.getConteudo());
                        proximo.setConteudo(temp);
                        trocou = true;
                    }
                } catch (ClassCastException e) {
                    System.err.println("Erro de ordenação: Elementos não são comparáveis ou de tipos incompatíveis.");
                    return;
                } catch (NullPointerException e) {
                    System.err.println("Erro de ordenação: Tentativa de comparar elemento nulo.");
                    return;
                }
                atual = atual.getProx();
            }
        } while (trocou);
    }


    @Override
    public void ordenarCrescente() {
        // Usa um Comparator que assume que os objetos são Comparable
        ordenar((o1, o2) -> ((Comparable<Object>) o1).compareTo(o2));
        System.out.println("Fila ordenada em ordem crescente (baseado no conteúdo dos nós).");
    }

    @Override
    public void ordenarDecrescente() {
        ordenar((o1, o2) -> ((Comparable<Object>) o2).compareTo(o1));
        System.out.println("Fila ordenada em ordem decrescente (baseado no conteúdo dos nós).");
    }


    @Override
    public int quantidadeElementos() {
        return tamanho;
    }

    @Override
    public void editarElemento(Object elementoAntigo, Object elementoNovo) {
        if (elementoAntigo == null || elementoNovo == null) {
            System.out.println("Aviso: Elemento antigo e novo não podem ser nulos para edição.");
            return;
        }
        No atual = inicio;
        boolean editado = false;
        while (atual != null) {
            if (Objects.equals(atual.getConteudo(), elementoAntigo)) {
                atual.setConteudo(elementoNovo);
                editado = true;
                break;
            }
            atual = atual.getProx();
        }
        if (editado) {
            System.out.println("Elemento '" + elementoAntigo + "' editado para '" + elementoNovo + "'.");
        } else {
            System.out.println("Elemento '" + elementoAntigo + "' não encontrado para edição.");
        }
    }

    @Override
    public void limpar() {
        No atual = inicio;
        while (atual != null) {
            No proximo = atual.getProx();
            atual.setConteudo(null);
            atual.setProx(null);
            atual.setAnterior(null);
            atual = proximo;
        }
        inicio = null;
        fim = null;
        tamanho = 0;
        System.out.println("Fila limpa.");
    }

    @Override
    public void exibir() {
        if (estaVazia()) {
            System.out.println("Fila Vazia.");
            return;
        }
        System.out.print("Fila: [INICIO] ");
        No atual = inicio;
        while (atual != null) {
            System.out.print(atual.getConteudo());
            if (atual.getProx() != null) {
                System.out.print(" -> ");
            }
            atual = atual.getProx();
        }
        System.out.println(" [FIM]");
    }

    @Override
    public No obterPrimeiroElemento() {
        if (estaVazia()) {
            System.out.println("A fila está vazia, não há primeiro elemento.");
            return null;
        }
        return inicio;
    }

    @Override
    public No obterUltimoElemento() {
        if (estaVazia()) {
            System.out.println("A fila está vazia, não há último elemento.");
            return null;
        }
        return fim;
    }
}