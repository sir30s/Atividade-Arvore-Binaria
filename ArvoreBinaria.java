class No {
    int valor;
    No esquerda;
    No direita;

    public No(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
}

class ArvoreBinaria {
    No raiz;

    public void inserirNo(int valor) {
        raiz = inserirLindamente(raiz, valor);
    }

    public No inserirLindamente(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }
        if (valor < atual.valor) {
            atual.esquerda = inserirLindamente(atual.esquerda, valor);
        } else {
            atual.direita = inserirLindamente(atual.direita, valor);
        }
        return atual;
    }

    public boolean buscarElemento(int valorBuscando) {
        return buscarElementoLindamente(raiz, valorBuscando);
    }

    public boolean buscarElementoLindamente(No atual, int valorBuscando) {
        if (atual == null) {
            return false;
        } else if (atual.valor == valorBuscando) {
            return true;
        } else if (valorBuscando < atual.valor) {
            return buscarElementoLindamente(atual.esquerda, valorBuscando);
        } else {
            return buscarElementoLindamente(atual.direita, valorBuscando); // essa tal de recursividade, ein...
        }
    }

    public No encontrarMenor(No no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }

    public void removerElemento(int valor) {
        raiz = removerElementoLindamente(raiz, valor);
    }

    public No removerElementoLindamente(No atual, int valor) {
        if (atual == null) {
            System.out.println("Elemento não encontrado!");
            return null;
        }
        if (valor < atual.valor) {
            atual.esquerda = removerElementoLindamente(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = removerElementoLindamente(atual.direita, valor);
        } else {
            if (atual.esquerda == null) {
                return atual.direita;
            }
            if (atual.direita == null) {
                return atual.esquerda;
            }
            No proximo = encontrarMenor(atual.direita);
            atual.valor = proximo.valor;
            atual.direita = removerElementoLindamente(atual.direita, proximo.valor);
        }
        return atual;
    }

    // percurso em ordem, só para conseguirmos ver o estado da árvore nos exemplos
    public void imprimirEmOrdem() {
        imprimirEmOrdem(raiz);
        System.out.println();
    }

    private void imprimirEmOrdem(No atual) {
        if (atual == null) {
            return;
        }
        imprimirEmOrdem(atual.esquerda);
        System.out.print(atual.valor + " ");
        imprimirEmOrdem(atual.direita);
    }
}

class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();

        // --- Exemplo de adição ---
        int[] valores = {10, 5, 15, 2, 7, 12, 20};
        for (int valor : valores) {
            arvoreBinaria.inserirNo(valor);
        }
        System.out.print("Árvore após inserções: ");
        arvoreBinaria.imprimirEmOrdem();

        // --- Exemplo de busca ---
        int buscado1 = 7;
        int buscado2 = 99;
        System.out.println("Buscando " + buscado1 + ": " + arvoreBinaria.buscarElemento(buscado1));
        System.out.println("Buscando " + buscado2 + ": " + arvoreBinaria.buscarElemento(buscado2));

        // --- Exemplo de remoção ---
        System.out.println("Removendo 5 (nó com dois filhos)...");
        arvoreBinaria.removerElemento(5);
        System.out.print("Árvore após remoção: ");
        arvoreBinaria.imprimirEmOrdem();

        System.out.println("Removendo 99 (nó inexistente)...");
        arvoreBinaria.removerElemento(99);
    }
}
