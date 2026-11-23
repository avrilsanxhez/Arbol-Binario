package com.mycompany.arbolbinaro;

public class BinaryTreeComun<T> implements ITree<T> {

    NodoArbolBinario<T> raiz;
    
    class NodoArbolBinario<T>{
        T dato;
        NodoArbolBinario<T> hijoIzq;
        NodoArbolBinario<T> hijoDer;
        
        public NodoArbolBinario(T dato){
            this.dato= dato;
        }
    }
    
    @Override
    public T getRootData() {
        if (raiz == null){
            return null;
        }
        return raiz.dato;
    }

    @Override
    public int getHeight() {
        return getHeight(raiz);
    }
        
    protected int getHeight(NodoArbolBinario<T> nodo) {
        int height = 0;
        if (nodo != null) {
            height = 1 + Math.max(getHeight(nodo.hijoIzq), getHeight(nodo.hijoDer));
        }
        return height;
    }
    
    @Override
    public int getNumberNodes() {
        return getNumberNodes(raiz);
    }
    
    private int getNumberNodes(NodoArbolBinario<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        int numNodosIzq = getNumberNodes(nodo.hijoIzq);
        int numNodosDer = getNumberNodes(nodo.hijoDer);
        return 1 + numNodosIzq + numNodosDer;
    }

    @Override
    public boolean empty() {
        return raiz == null;
    }

    @Override
    public void clear() {
        raiz=null;
    }
    
}
