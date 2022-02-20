package datastructures;

import java.util.LinkedList;
import java.util.Queue;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Krishnan Ravichandran & Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     * @throws Exception
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new ArithmeticException("Underflow exception");
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     * @throws Exception
     */
    public AnyType findMax( ) 
    {
        if( isEmpty( ) )
            throw new ArithmeticException("Underflow exception");
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }

    public int nodeCount(){
        if(isEmpty()) return 0;
        return countNodes(this.root);
    }

    private int countNodes(BinaryNode<AnyType> t){
        if(t == null) return 0;
        return (1 + countNodes(t.left) + countNodes(t.right)); 

    }

    public boolean isFull(){
        if(isEmpty()) return true; // considering empty tree to be full since it does not have any partially filled nodes
        return isFull(root);
    }

    private boolean isFull(BinaryNode<AnyType> t){
        if(t == null) return true;
        if((t.left == null && t.right == null)) return true;
        if(t.left != null && t.right != null)
            return isFull(t.left) && isFull(t.right);
        else
            return false;

    }

    public boolean compareStructure(BinarySearchTree<AnyType> tree){
        return compareStructure(root, tree.root);
    }

    private boolean compareStructure(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2){
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        else return (compareStructure(t1.right, t2.right) && compareStructure(t1.right, t2.right));
    }

    public boolean equals(BinarySearchTree<AnyType> tree){
        return equals(root, tree.root);
    }
    private boolean equals(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2){
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        if(t1.element.compareTo(t2.element) == 0) return (equals(t1.left, t2.left) && equals(t1.right, t2.right)); 
        else return false;
    }

    public BinarySearchTree<AnyType> copy(){
        if(isEmpty()) return new BinarySearchTree<>();
        BinarySearchTree<AnyType> newTree = new BinarySearchTree<>( );
        BinaryNode<AnyType> newroot = copy(root);
        newTree.root = newroot;
        return newTree;
    }

    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t){
        if(t == null) return null;
        BinaryNode<AnyType> node = new BinaryNode<>(t.element);
        node.left = copy(t.left);
        node.right = copy(t.right);
        return node;
    }

    public BinarySearchTree<AnyType> mirror(){
        if(isEmpty()) return new BinarySearchTree<>();
        BinarySearchTree<AnyType> newTree = new BinarySearchTree<>( );
        BinaryNode<AnyType> newroot = mirror(root);
        newTree.root = newroot;
        return newTree;
    }

    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t){
        if(t == null) return null;
        BinaryNode<AnyType> node = new BinaryNode<>(t.element);
        node.left = mirror(t.right);
        node.right = mirror(t.left);
        return node;

    }

    public boolean isMirror(BinarySearchTree<AnyType> tree){
        return isMirror(root, tree.root);
    }

    private boolean isMirror(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2){
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        if(t1.element.compareTo(t2.element) == 0) return (isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left));
        return false;
    }

    private BinaryNode<AnyType> getParentNode(BinaryNode<AnyType> t, AnyType element){
        if(t == null) return null;
        int compareResult = element.compareTo(t.element);
        if(t.right == null && t.left == null ) return null;
        if((t.right != null && element.compareTo(t.right.element) == 0 )|| (t.left != null && element.compareTo(t.left.element) == 0)) return t;
        if(compareResult < 0) {
            return getParentNode(t.left, element);
        }
        else{
            return getParentNode(t.right, element);
        }
    }
   
    public void rotateRight(AnyType element){
        if(root.element.compareTo(element) == 0){
            BinaryNode<AnyType> node = rotateRight(root);
            if(node != null)
                root = node;
        }
        else{
            BinaryNode<AnyType> parent = getParentNode(root, element);
            if(parent!=null){
                //check which child
                if(parent.right !=null && parent.right.element.compareTo(element) == 0){
                    BinaryNode<AnyType> node = rotateRight(parent.right);
                    if(node != null) parent.right = node;
                }
                else{
                    BinaryNode<AnyType> node = rotateRight(parent.left);
                    if(node != null) parent.left = node;
                }
            }
            
        }
    }

    public void rotateLeft(AnyType element){
        //check if root
        if(root.element.compareTo(element) == 0){
            BinaryNode<AnyType> node = rotateLeft(root);
            if(node != null)
                root = node;
        }
        else{
            BinaryNode<AnyType> parent = getParentNode(root, element);
            if(parent!=null){
                //check which child
                if(parent.right !=null && parent.right.element.compareTo(element) == 0){
                    BinaryNode<AnyType> node = rotateLeft(parent.right);
                    if(node != null) parent.right = node;
                }
                else{
                    BinaryNode<AnyType> node = rotateLeft(parent.left);
                    if(node != null) parent.left = node;
                }
            }
            
        }
    }


    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> t){
        BinaryNode<AnyType> left = t.left;
        if(left != null){
            t.left = left.right;
            left.right = t; 
            return left;
        }
        return null;
       
    }

    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> t){
        BinaryNode<AnyType> right = t.right;
        if(right != null){
            t.right = right.left;
            right.left = t; 
            return right;
        }
        return null;   
    }


    public void printLevels(){
        if(isEmpty()) return;
        printLevels(root);
    }

    private void printLevels(BinaryNode<AnyType> t){
       Queue<BinaryNode<AnyType>> q = new LinkedList<>();
       //add root
       q.add(t);
       while(!q.isEmpty()){
           int currlevel = q.size();
           while(currlevel > 0){
            BinaryNode<AnyType> currNode = q.poll();
            System.out.print(currNode.element + " ");
            if(currNode.left != null) q.add(currNode.left);
            if(currNode.right != null) q.add(currNode.right);
            currlevel--;
           }
           System.out.println();
       }
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    
}