package com.mycompany.arbolbinaro;

import java.util.Iterator;

public class ArbolBinario {

    public static void main(String[] args) {

        //generación de los abuelitos
        Familiar abueloPaterno = new Familiar("Armando Sánchez", 52, "Musico", true, "Sonora");
        Familiar abuelaPaterna = new Familiar("Tita", 80, "Pensionada", false, "Sinaloa");
        Familiar abueloMaterno = new Familiar("no hay", 3, "sepa la bola", true, "Sonora");
        Familiar abuelaMaterna = new Familiar("Maria Carreras", 70, "Pensionada", false, "Sonora");

        Familiar papa = new Familiar("Edgar Sanchez", 54, "Empleado", false, "Sonora");
        Familiar mama = new Familiar("Julia Salazar", 45, "Docente", false, "Sonora");

        Familiar yopo = new Familiar("Avril Sanchez Salazar", 19, "Estudiante", false, "navoyork");

        BinaryTree<Familiar> arbolAbueloP = new BinaryTree<>(abueloPaterno);
        BinaryTree<Familiar> arbolAbuelaP = new BinaryTree<>(abuelaPaterna);
        BinaryTree<Familiar> arbolAbueloM = new BinaryTree<>(abueloMaterno);
        BinaryTree<Familiar> arbolAbuelaM = new BinaryTree<>(abuelaMaterna);

        BinaryTree<Familiar> arbolPapa = new BinaryTree<>(papa, arbolAbuelaP, arbolAbueloP);
        BinaryTree<Familiar> arbolMama = new BinaryTree<>(mama, arbolAbuelaM, arbolAbueloM);

        BinaryTree<Familiar> arbolFamiliar = new BinaryTree<>(yopo, arbolPapa, arbolMama);

        System.out.println("Raiz: " + arbolFamiliar.getRootData().getNombre());
        System.out.println("Altura: " + arbolFamiliar.getHeight());
        System.out.println("Nodos: " + arbolFamiliar.getNumberNodes());

        System.out.println("\nPreorden (raiz, nodo izquierdo y nodo derecho)");
        imprimir(arbolFamiliar.getPreorderIterator());

        System.out.println("\nPostorden (nodo izquierdo, nodo derecho y raiz)");
        imprimir(arbolFamiliar.getPostordenIterator());

        System.out.println("\nEn orden :)");
        imprimir(arbolFamiliar.getInorderIterator());
    }

    private static void imprimir(Iterator<Familiar> it) {
        while (it.hasNext()) {
            System.out.println(" - " + it.next());
        }
    }
}
