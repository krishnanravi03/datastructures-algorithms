package datastructures;

/**
 * Project #1
 * Part 2
 * Stack implementation & Symbol Algorithm demostration
 * By: Krishnan Ravichandran - KXR200008
 */


import java.util.ArrayList;

public class MyStack<AnyType> {

    private int theSize = 0;
    private ArrayList<AnyType> stack = new ArrayList<>();


    public MyStack(){
        doclear();
    }
    private void doclear(){
        stack.clear();
        theSize = 0;
    }

    public AnyType pop(){
    if(size() == 0){
        throw new IndexOutOfBoundsException("The stack is empty!");
    } 
    AnyType poppedElement =  stack.remove(theSize-1);
    theSize--;
    return poppedElement;
    }

    public void push(AnyType e){
        stack.add(e);
        theSize++;
    }

    public AnyType top(){
        return stack.get(theSize - 1);
    }

    public int size(){
        return theSize;
    }

    public void clear(){
        doclear();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(AnyType element : stack){
            sb.append(element + " ");
        }
        sb.append("]");
        return new String(sb);
    }

}
