package com.mycompany.arbolbinaro;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T> extends BinaryTreeComun<T> implements ITreeIterator<T> {

    class InorderIterator implements Iterator<T> {

        private Stack<NodoArbolBinario<T>> pilaNodos;
        private NodoArbolBinario<T> nodoActual;

        public InorderIterator() {
            pilaNodos = new Stack<>();
            nodoActual = raiz;
        }

        @Override
        public boolean hasNext() {
            return !pilaNodos.empty() || (nodoActual != null);
        }

        @Override
        public T next() {
            NodoArbolBinario<T> nodoSig = null;
            while (nodoActual != null) {
                pilaNodos.push(nodoActual);
                nodoActual = nodoActual.hijoIzq;
            }
            if (!pilaNodos.empty()) {
                nodoSig = pilaNodos.pop();
                nodoActual = nodoSig.hijoDer;
            } else {
                throw new NoSuchElementException();
            }
            return nodoSig.dato;
        }
    }

    //raíz - nodo izq - nodo derecho
    class PreorderIterator implements Iterator<T> {

        private Deque<NodoArbolBinario<T>> pilaNodos;

        public PreorderIterator() {
            pilaNodos = new ArrayDeque<>();
            if (raiz != null) {
                pilaNodos.push(raiz);
            }
        }

        @Override
        public boolean hasNext() {
            return !pilaNodos.isEmpty();
        }

        @Override
        public T next() {
            if (pilaNodos.isEmpty()) {
                throw new NoSuchElementException();
            }
            NodoArbolBinario<T> actual = pilaNodos.pop();
            if (actual.hijoDer != null) {
                pilaNodos.push(actual.hijoDer);
            }
            if (actual.hijoIzq != null) {
                pilaNodos.push(actual.hijoIzq);
            }
            return actual.dato;
        }
    }

    //nodo izquierdo - nodo derecgo - raiz
    class PostordenIterator implements Iterator<T> {

        private Deque<NodoArbolBinario<T>> pilaNodos;
        private NodoArbolBinario<T> ultimoVisitado;

        public PostordenIterator() {
            pilaNodos = new ArrayDeque<>();
            ultimoVisitado = null;
            if (raiz != null) {
                pilaNodos.push(raiz);
            }
        }

        @Override
        public boolean hasNext() {
            return !pilaNodos.isEmpty();
        }

        @Override
        public T next() {
            while (!pilaNodos.isEmpty()) {
                NodoArbolBinario<T> actual = pilaNodos.peek();

                if (ultimoVisitado == null
                        || ultimoVisitado.hijoIzq == actual
                        || ultimoVisitado.hijoDer == actual) {
                    if (actual.hijoIzq != null) {
                        pilaNodos.push(actual.hijoIzq);
                    } else if (actual.hijoDer != null) {
                        pilaNodos.push(actual.hijoDer);
                    } else {
                        pilaNodos.pop();
                        ultimoVisitado = actual;
                        return actual.dato;
                    }
                } else if (actual.hijoIzq == ultimoVisitado) {
                    if (actual.hijoDer != null) {
                        pilaNodos.push(actual.hijoDer);
                    } else {
                        pilaNodos.pop();
                        ultimoVisitado = actual;
                        return actual.dato;
                    }
                } else {
                    pilaNodos.pop();
                    ultimoVisitado = actual;
                    return actual.dato;
                }
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    }

    @Override
    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator();
    }

    @Override
    public Iterator<T> getPostordenIterator() {
        return new PostordenIterator();
    }

    public BinaryTree() {
        raiz = null;
    }

    public BinaryTree(T datoRaiz) {
        raiz = new NodoArbolBinario(datoRaiz);
    }

    public BinaryTree(T datoRaiz, BinaryTree<T> arbolDer, BinaryTree<T> arbolIzq) {
        setTree(datoRaiz, arbolIzq, arbolDer);
    }

    private void setTree(T datoRaiz, BinaryTree<T> arbolIzq, BinaryTree<T> arbolDer) {
        raiz = new NodoArbolBinario(datoRaiz);
        if ((arbolIzq != null) && !arbolIzq.empty()) {
            raiz.hijoIzq = copy(arbolIzq.raiz);
        }
        if ((arbolDer != null) && !arbolDer.empty()) {
            raiz.hijoDer = copy(arbolDer.raiz);
        }
    }

    private NodoArbolBinario<T> copy(NodoArbolBinario<T> nodo) {
        NodoArbolBinario<T> nuevoNodo = new NodoArbolBinario<>(nodo.dato);
        if (nodo.hijoIzq != null) {
            nuevoNodo.hijoIzq = copy(nodo.hijoIzq);
        }
        if (nodo.hijoDer != null) {
            nuevoNodo.hijoDer = copy(nodo.hijoDer);
        }
        return nuevoNodo;
    }
}
