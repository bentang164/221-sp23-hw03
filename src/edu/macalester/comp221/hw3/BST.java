package edu.macalester.comp221.hw3;

//package foxTreeClasses;

import java.util.*;
/*
 * Implements a normal, typical Binary Search Tree.  Really just the insert, delete, and
 * search are different and implemented here.
 */
public class BST {
    protected boolean emptyTree;
    protected int nodeValue;
    protected BST leftChild;
    protected BST rightChild;
    
    /*
     * Makes an empty tree.
     */
    public BST() {
        emptyTree = true;
        nodeValue = 0;
        leftChild = null;
		rightChild = null;
    }

        
    /*
     * Makes an tree with a given node
     */
    public BST(int initialNode) {
        emptyTree = false;
        nodeValue = initialNode;
        leftChild = null;
        rightChild = null;
    }


    /* -------------------------------------------------------------
     * The section that follows has basic tree utility functions, accessors for node values 
     * and children, functions to check the kind of tree and its properties, and to add or modify
     * elements of the tree
     */

    /*
     * Returns True if the tree is empty, and False otherwise.
     */
    public boolean isEmpty() {
        return emptyTree;
    }


    /*
     * Returns the root node's value, or -1 if the tree is empty.
     */
    public int getNodeValue() {
	if ( isEmpty() ) {
	    return -1;
	}
	else {
	    return nodeValue;
	}
    }


    /*
     * Returns the left child subtree
     */
    public BST getLeftChild() {
	return leftChild;
    }


    /*
     * Returns the right child subtree
     */
    public BST getRightChild() {
	return rightChild;
    }


    /*
     * Returns true if the node has no children, false otherwise.
     */
    public boolean isLeaf() {
	return (leftChild == null) && (rightChild == null);
    }


    /*
     * Takes in a new node value, and changes this node's value to the new value.
     */
    public void setNodeValue(int newValue) {
	nodeValue = newValue;
    }


    /*
     * Returns true if the node has a left child, false otherwise.
     */
    public boolean hasLeftChild() {
	return leftChild != null;
    }


    /*
     * Returns true if the node has a right child, false otherwise.
     */
    public boolean hasRightChild() {
	return rightChild != null;
    }

	
    /*
     * Takes a subtree as input.  It adds the input tree as the left child of this node.
     * Passing an empty tree here causes the left child to be removed.
     */
    public void setLeftChild(BST childTree) {
	if ( emptyTree ) {
	    System.out.println("Cannot add child to empty tree.");
	}
	else if ( childTree == null || childTree.isEmpty() ) {
	    leftChild = null;
	}
	else {
	    leftChild = childTree;
	}
    }
		

    /*
     * Takes a subtree as input.  It adds the input tree as the right child of this node.
     * Passing an empty tree here causes the left child to be removed.
     */
    public void setRightChild(BST childTree) {
	if ( emptyTree ) {
	    System.out.println("Cannot add child to empty tree.");
	}
	else if ( childTree == null || childTree.isEmpty() ) {
	    rightChild = null;
	}
	else {
	    rightChild = childTree;
	}
    }

    
    /*-------------------------------------------------------------
     * The section that follows has more complex operations.  Computing the height,
     * traversing the tree in various orders, inserting, searching, and deleting.  The 
     * last three operations are not fully defined here; should be added to in a subclass.
     */

	
    /*
     * Computes and returns the height of the tree.  Note that an empty tree
     * is defined to have a height of -1, a nonempty tree relies on the Node's
     * getHeight method.
     */
    public int getHeight() {
    	if ( isEmpty() ) {
            return -1;	
    	}
	else if (isLeaf()) {
	    return 0;
	}
	else {
	    int leftHeight = 0;
	    int rightHeight = 0;
	    if ( hasLeftChild() ) {
		leftHeight = leftChild.getHeight();
	    }
	    if ( hasRightChild() ) {
		rightHeight = rightChild.getHeight();
	    }
	    return Math.max(leftHeight + 1, rightHeight + 1);
	}
    }

    
    /*
     * Traverses the tree, printing the node values.  This function performs preorder 
     * traversal, meaning the root is touched, then the left child
     * is traversed, and finally the right child is traversed.
     */
    public void preorderTraverse() {
        if ( isEmpty() ) {
            return;
        }
        else {
	    // handle the root node's value
	    System.out.println(nodeValue);
	    // recur on left and right children, if they exist
	    if ( hasLeftChild() ) {
		leftChild.preorderTraverse();
	    }
	    if ( hasRightChild() ) {
		rightChild.preorderTraverse();
	    }
	}
    }


    /*
     * Traverses the tree, printing the node values. This function performs postorder 
     * traversal, meaning first the left child is traversed, then the right child is
     * traversed, and finally the root is touched.
     */
    public void postorderTraverse() {
        if ( isEmpty() ) {
            return;
        }
        else {
	    // recur on left and right children, if they exist
	    if ( hasLeftChild() ) {
		leftChild.postorderTraverse();
	    }
	    if ( hasRightChild() ) {
		rightChild.postorderTraverse();
	    }
	    // handle the root node's value
	    System.out.println(nodeValue);
        }
    }

    
    
    /*
     * Traverses the tree, printing the node values.  This function performs inorder traversal, 
     * meaning the left child is traversed, then the root is touched, and finally the right 
     * child is traversed.
     */
    public void inorderTraverse() {
        if ( isEmpty() ) {
            return;
        }
        else {
	    // recur on left and right children, if they exist
	    if ( hasLeftChild() ) {
		leftChild.inorderTraverse();
	    }

	    // handle the root node's value
	    System.out.println(nodeValue);

	    if ( hasRightChild() ) {
		rightChild.inorderTraverse();
	    }
        }
    }
        

    /*
     * Prints the tree using indentation for levels of the tree
     */
    public void printTree() {
    	if ( isEmpty() ) {
            System.out.println("Tree is empty");
    	}
        else {
            printRecur(0);
        }
    }
        

    /*
     * Takes in an indentation depth, and prints out the tree, first the root
     * then the left subtree, and then the right subtree
     */
    protected void printRecur(int depth) {
	char indentSpaces[] = new char[depth];
	for ( int i = 0; i < depth; i++ ) {
	    indentSpaces[i] = ' ';
	}
	String indStr = new String(indentSpaces);
	
	if ( isLeaf() ) {
	    System.out.println(indStr + "Leaf: " + nodeValue);
	}
	else {
	    System.out.println(indStr + "Node: " + nodeValue);
	    if ( hasLeftChild() ) {
		System.out.println(indStr + "     LEFT:");
		leftChild.printRecur(depth + 5);
	    }
	    else {
		System.out.println(indStr + "     LEFT:  no left child");
	    }
	    if ( hasRightChild() ) {
		System.out.println(indStr + "     RIGHT:");
		rightChild.printRecur(depth + 5);
	    }
	    else {
		System.out.println(indStr + "     RIGHT:  no right child");
	    }
	}
    }
     


    /*
     * Takes in a value and handles the empty tree case, passing everything else off to the Node
     * class's search method.  Return true if it finds the value and false otherwise.
     */
    public boolean search(int value) {
    	if ( isEmpty() ) {
    		return false;
    	}
    	else if ( value == nodeValue ) {
            return true;
        }
        else if ( (value < nodeValue) && hasLeftChild() ) {
            return leftChild.search(value);
        }
        else if ( (value >= nodeValue) && hasRightChild() ) {
            return rightChild.search(value);
        }
        else {
            return false;
        }
    }


    /*
     * Takes in a new value and handles the empty tree case.  Performs classic BST insertion.
     */
    public void insert(int newValue) {
        if (isEmpty()) {
            emptyTree = false;
            nodeValue = newValue;
            return;
        }

        if (newValue > nodeValue && hasRightChild()) {
            rightChild.insert(newValue);
        } else if (!hasRightChild()) {
            setRightChild(new BST(newValue));
            return;
        }

        if (newValue <= nodeValue && hasLeftChild()) {
            leftChild.insert(newValue);
        } else if (!hasLeftChild()) {
            setLeftChild(new BST(newValue));
            return;
        }
    }       
     
    /*
     *  Takes in a value and checks for the empty tree case. Passes everything else off to the Node
     *  class's delete method.
     */
    public void delete(int value) {
    	if ( isEmpty() ) {
    		System.out.println("Cannot remove value from an empty tree");
    	}
    	else {
            BST result = deleteRecur(value);
	    if ( result != null ) {
		// when root of tree changes, must handle specially
		System.out.println("CHANGING ROOT!");
		if ( result.isEmpty() ) {
		    emptyTree = true;
		    nodeValue = 0;
		    leftChild = null;
		    rightChild = null;
		}
		else {
		    setNodeValue(result.getNodeValue());
		    setLeftChild(result.getLeftChild());
		    setRightChild(result.getRightChild());
		}
	    }
	}
    }


    /*
     * Takes in a value and removes an occurrence of the value. It returns the changed node, if this
     * changed, and uses that value to update the parent node. It returns null if the current node
     * didn't change, and no update of the parent is required.
     */
    protected BST deleteRecur(int value) {
        if ( value == nodeValue ) {            // this is the node to delete
            if ( isLeaf() ) {
                return new BST();                         // this node is going away, return empty tree to trigger removal
            }
            else if ( !hasLeftChild() ) {
                return rightChild;                   // right child is taking this node's place
            }
            else if ( !hasRightChild() ) {
                return leftChild;                    // left child is taking this node's place
            }
            else {                            // Node has two children, most complicated case
                int replaceValue = leftChild.findMaxValue();
                setNodeValue(replaceValue);
                BST result = leftChild.deleteRecur(replaceValue);     // delete from left child
		if ( result != null ) {                      // if immediate child changed...
		    setLeftChild(result);
		}
                return null;                           // this node stays, so return null
            }
        }
        else if ( value < nodeValue ) {
            if ( hasLeftChild() ) {
            	BST result = leftChild.deleteRecur(value); 
		if ( result != null ) {
		    setLeftChild(result);
		}
            }
            return null;                                    // this node stays, so return null
        }
        else {  // value >= nodeValue
            if ( hasRightChild() ) {
            	BST result = rightChild.deleteRecur(value);     // update this node's right child
		if ( result != null ) {
		    setRightChild(result);
		}
	    }
            return null;                                   //this node stays, so return null
        }
    }

    /*
     * Finds the maximum value in this subtree.  If there is a right child, then it 
     * follows the path down to the right child.  If no right child, then this node is the
     * maximum.
     */
    protected int findMaxValue() {
        if ( isLeaf() ||  !hasRightChild() ) {
            return nodeValue;
        }
        else {
        	return rightChild.findMaxValue();
        }
    }       


    public static void main(String[] args) {
	BST tree = new BST();
	System.out.println(tree.isEmpty());
	System.out.println("Height of tree: " + tree.getHeight());
	tree.printTree();
	Integer foo = new Integer(3);
	tree.insert(foo);
	tree.printTree();
	System.out.println("Height of tree: " + tree.getHeight());
	BST t2 = new BST(1234);
	tree.setLeftChild(t2);
	tree.setRightChild(new BST(101));
	System.out.println("Height of tree: " + tree.getHeight());
	tree.preorderTraverse();
	tree.inorderTraverse();
	tree.postorderTraverse();

	BST newT = new BST();
	List<Integer> valList = new ArrayList<Integer>();
	for ( int i = 0; i < 20; i++ ) {
	    int val = (int)(Math.random() * 100);	
	    System.out.println("Inserting value " + val);
	    valList.add(new Integer(val));	
	    newT.insert(val);
		System.out.println("    new Height = " + newT.getHeight());
	}
	newT.printTree();	

	List<Integer> extraList = new ArrayList<Integer>();
	for ( int j = 0; j < 5; j++ ) {
	    int val = (int)(Math.random() * 100);
	    extraList.add(val);
	}
	Collections.shuffle(valList);
	System.out.println("------------------Searching for values that are in tree:");
	for ( int i = 0; i < 20; i++ ) {
	    int v = valList.get(i);
	    System.out.println("Searching for value " + v);
	    System.out.println(newT.search(v));
	}
	System.out.println("------------------Searching for values that may not be there:");
	for ( int i = 0; i < 5; i++ ) {
	    int v = extraList.get(i);
	    System.out.println("Searching for value " + v);
	    System.out.println(newT.search(v));
	}

	Collections.shuffle(valList);
	for ( int i = 0; i < 20; i++ ) {
	    int v = valList.get(i);
	    System.out.println("Deleting value " + v);
	    newT.delete(v);
	    newT.printTree();
	}
    }
};
