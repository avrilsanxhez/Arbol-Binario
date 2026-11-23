package com.mycompany.arbolbinaro;

public interface ITree<T> {
    public T getRootData();
    public int getHeight();
    public int getNumberNodes();
    public boolean empty();
    public void clear();
}
