package examples;
import datastructures.BinarySearchTree;

public final class BSTDemostrator {
    public static void run()
    {
        BinarySearchTree<Integer> tree1 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> tree2 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> tree3 = new BinarySearchTree<>( );

        tree1.insert(5);
        tree1.insert(3);
        tree1.insert(8);
        tree1.insert(1);
        tree1.insert(4);
        tree1.insert(6);
        tree1.insert(10);

        tree2.insert(10);
        tree2.insert(5);
        tree2.insert(15);
        tree2.insert(7);
        tree2.insert(2);

        tree3.insert(5);
        tree3.insert(3);
        tree3.insert(8);
        tree3.insert(1);
        tree3.insert(4);
        

        //a
        System.out.println("Node Count: " + tree1.nodeCount());
        //b
        System.out.println("Is Full: " + tree1.isFull());
        //c
        System.out.println("Comparing structures: " + tree1.compareStructure(tree2));
        System.out.println("Comparing structure of another tree: " + tree3.compareStructure(tree2));
        //d
        System.out.println("Is Equal: " + tree1.equals(tree2));
        //e
        BinarySearchTree<Integer> copiedTree = tree1.copy();
        System.out.println("Copied Tree:");
        copiedTree.printLevels();
        System.out.println();
        //d
        System.out.println("Is Equal: " + tree1.equals(copiedTree));
        //f
        BinarySearchTree<Integer> mirroredTree = tree1.mirror();
        System.out.println("Mirrored Tree:");
        mirroredTree.printLevels();
        System.out.println();
        //g
        System.out.println("Is mirror: " +tree1.isMirror(mirroredTree));
        System.out.println("Is mirror of itself: " +tree1.isMirror(tree1));
        //h
        tree1.rotateRight(5);
        System.out.println("Right rotated:");
        tree1.printLevels();
        System.out.println();
        //i
        tree1.rotateLeft(3);
        System.out.println("Left rotated:");
        tree1.printLevels();
        System.out.println();

        //j
        System.out.println("Printing level-by-level:");
        tree1.printLevels();
        System.out.println();

    }
}
