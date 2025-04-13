public class No implements INo {
    private Object conteudo;
    private No proximo;
    private No anterior; // OBS 6

    public No(Object conteudo) {
        this.conteudo = conteudo;
        this.proximo = null;
        this.anterior = null;
    }

    @Override
    public No getProx() {
        return proximo;
    }

    @Override
    public void setProx(No prox) {
        this.proximo = prox;
    }

    @Override
    public Object getConteudo() {
        return conteudo;
    }

    @Override
    public void setConteudo(Object conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public No getAnterior() {
        return anterior;
    }

    @Override
    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }

    @Override
    public String toString() {
        return (conteudo != null) ? conteudo.toString() : "null";
    }
}