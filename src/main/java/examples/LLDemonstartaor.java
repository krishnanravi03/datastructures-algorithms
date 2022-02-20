package examples;

import datastructures.MyLinkedList;

public final class LLDemonstartaor {
    public static void run()
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )
                lst.add( i );
        System.out.println( lst );

        //Part 1 
        //(e) Demonstrating all the methods in part1

        //(a)
        lst.swap(0,2);
        System.out.println(lst);

        //(b)
        lst.shift(2);
        System.out.println( lst );
        lst.shift(-1);
        System.out.println( lst );

        //(c)
        lst.erase(1, 2);
        System.out.println( lst );

        //(d)
        MyLinkedList<Integer> lst2 = new MyLinkedList<>();
        for(int i=20; i<26; i++) lst2.add(i);
        lst.insertList(4, lst2);
        System.out.println( lst );

        //lst.erase(100, 6);
        //lst.insertList(100, lst2);
        //lst.swap(0, 100);

    }
}
