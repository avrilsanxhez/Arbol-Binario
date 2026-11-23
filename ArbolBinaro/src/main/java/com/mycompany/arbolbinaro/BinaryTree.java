package com.mycompany.arbolbinaro;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T> extends BinaryTreeComun<T> implements ITreeIterator<T> {

    
    class InorderIterator implements Iterator<T>{

        private Stack<NodoArbolBinario<T>> pilaNodos;
        private NodoArbolBinario<T> nodoActual;
        
        public InorderIterator(){
            pilaNodos = new Stack<>();
            nodoActual = raiz;
        }
        
        @Override
        public boolean hasNext() {
            return !pilaNodos.empty() || (nodoActual != null);
        }

        @Override
        public T next() {
            NodoArbolBinario<T> nodoSig= null;
            while(nodoActual != null) {
                pilaNodos.push(nodoActual);
                nodoActual = nodoActual.hijoIzq;
            }

            if(!pilaNodos.empty()) {
                nodoSig = pilaNodos.pop();
                nodoActual = nodoSig.hijoDer;
            }
            else throw new NoSuchElementException();
            return nodoSig.dato;
        }
        
    }
    
    @Override
    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    }

    @Override
    public Iterator<T> getPreorderIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<T> getPostordenIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public BinaryTree(){
        raiz= null;
    }
    
    public BinaryTree(T datoRaiz){
        raiz= new NodoArbolBinario(datoRaiz);
    }
    
    public BinaryTree(T datoRaiz, BinaryTree<T> arbolDer, BinaryTree<T> arbolIzq){
        setTree(datoRaiz, arbolIzq, arbolDer);
    }
    
    private void setTree(T datoRaiz, BinaryTree<T> arbolIzq, BinaryTree<T> arbolDer){
        raiz = new NodoArbolBinario(datoRaiz);
        if((arbolIzq != null) && !arbolIzq.empty()){
            raiz.hijoIzq = copy(arbolIzq.raiz);
        }
        if ((arbolDer != null) && !arbolDer.empty()){
            raiz.hijoDer = copy(arbolDer.raiz);
        }
    }
    
    private NodoArbolBinario<T> copy(NodoArbolBinario<T> nodo){
        NodoArbolBinario<T> nuevoNodo = new NodoArbolBinario<>(nodo.dato);
        if(nodo.hijoIzq != null){
            nuevoNodo.hijoIzq = copy(nodo.hijoIzq);
        }
        if (nodo.hijoDer != null){
            nuevoNodo.hijoDer = copy(nodo.hijoDer);
        }
        return nuevoNodo;
    }
    
}
