package com.mycompany.arbolbinaro;

public class ArbolBinario {

    public static void main(String[] args) {
        
        BinaryTree<String> abIzq= new BinaryTree("mama");
        BinaryTree<String> abIzq2= new BinaryTree("mama");
        
        BinaryTree<String> arbolitoIzq= new BinaryTree("mama", abIzq, abIzq2);
        BinaryTree<String> arbolitoDer= new BinaryTree("papa");
        
        BinaryTree<String> arbolitoLol = new BinaryTree("yop", arbolitoIzq, arbolitoDer);
    }
    
}
