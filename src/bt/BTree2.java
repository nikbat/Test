package bt;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.DelayQueue;

import org.apache.commons.lang.ArrayUtils;

/*
 *                                           100
 *                            50							150
 *               		25			75				125					175
 *               								110
 */

public class BTree2<T extends Comparable<T>> {
	
	BNode<T> root;
	
	public BTree2() {
		super();		
	}
	
	public void treeInsert(T t){
		BNode<T> node = new BNode<T>(t);
		
		if(root == null){
			root = node;
		}else{
			BNode<T> runner = root; 
			while(true){
				if(runner.getData().compareTo(node.getData()) >= 0){ 
					if(runner.left == null){
						runner.left = node;
						node.parent = runner;
						break;
					}else{
						runner = runner.left;
						continue;
					}
				}else{
					if(runner.right == null){
						runner.right = node;
						node.parent = runner;
						break;
					}else{
						runner = runner.right;
						continue;
					}
				}
				
			}
		}
	}
	
	
	
	// build tree from postorder post order inorder in order  
	private BNode<T> buildTree(List<T> ioList, List<T> poList, int inOrderLower, int inOrderUpper,int postOrderLower, int postOrderUpper){
		
		if(inOrderLower > inOrderUpper || postOrderLower > postOrderUpper){
			return null;
		}
		
		if(inOrderLower == inOrderUpper ) return new BNode<T>(ioList.get(inOrderLower));
		
		if(postOrderLower == postOrderUpper ) return new BNode<T>(poList.get(postOrderLower));
		
		T t = poList.get(postOrderLower);
		int index = ioList.indexOf(t);
		
		if(index == -1){
			return null;
		}
		
		BNode<T> root = new BNode<T>(t); 
		
		root.left = buildTree(ioList, poList, inOrderLower, index-1, postOrderLower+1, index - (inOrderLower+postOrderLower));
		root.right = buildTree(ioList, poList, index+1, inOrderUpper, index - (inOrderLower+postOrderLower), postOrderUpper);
		
		return null;
		
	}
	
	
	
	List<T> io = new ArrayList<T>();
	
	public void inOrderTraversal(BNode<T> node){
		if(node != null){
			inOrderTraversal(node.left);
			io.add(node.getData());
			node.show();			
			inOrderTraversal(node.right);
		}
	}
	
	List<T> po = new ArrayList<T>();
	
	public void preOrderTraversal(BNode<T> node){
		if(node == null){
			return;
		}
		po.add(node.getData());
		node.show();
		preOrderTraversal(node.left);
		preOrderTraversal(node.right);
		
	}
	
	public void preOrderTraversalReccursive(BNode<T> node){
		//Create a stack whis is LIFO
		Stack<BNode<T>> stack = new Stack<BNode<T>>();
		if(node != null){
			// if node is not null put node in a stack
			stack.push(node);
		}
		
		// Now look at the preOrder in recursion, first root node, left node and after that right node, that means right node is going in a memory stack, that what we will do with out stack
		while(!stack.isEmpty()){
			BNode<T> temp = stack.pop();
			temp.show();
			if(temp.getRight() != null){
				stack.push(temp.right);				
			}
			
			if(temp.left != null){
				stack.push(temp.left);
			}
		}
		
	}
	
	public int findHeight(BNode<T> node){
		if(node == null){
			return 0;
		}
		return 1 + Math.max(findHeight(node.left), findHeight(node.right));
	}
	
	public boolean treeContains(T t){
		boolean contains = false;
		if(root != null){
			BNode<T> runnerNode = root;
			
			while(runnerNode != null){
				if(runnerNode.getData().equals(t)){
					contains = true;
					break;
				}else{
					if(runnerNode.getData().compareTo(t) >= 0){
						runnerNode = runnerNode.right;
					}else{
						runnerNode = runnerNode.left;
					}
						
				}
			}
		}
		return contains;
	}
	
	public BNode<T> find(T t){
		BNode<T> node = null;
		if(root != null){
			BNode<T> runnerNode = root;
			
			while(runnerNode != null){
				if(runnerNode.getData().equals(t)){
					node = runnerNode;
					break;
				}else{
					if(runnerNode.getData().compareTo(t) >= 0){
						runnerNode = runnerNode.left;
					}else{
						runnerNode = runnerNode.right;
					}
						
				}
			}
		}
		return node;
	}
	
	
	private BNode<T> mostCommonAncestor(T t1, T t2){
		BNode<T> runner = null;
		BNode<T> mca = null;
		if (root == null){
			return null;
		}else{
			runner = root;
		}
		
		while(runner != null){
			if(t1.compareTo(runner.getData()) < 0 && t2.compareTo(runner.getData()) < 0){
				//both are less so lets go to left
				if(runner.left != null && (runner.left.getData().equals(t1) || runner.left.getData().equals(t2))){
					mca = runner;
					break;
				}
				runner = runner.left;
			}else if(t1.compareTo(runner.getData()) >= 0 && t2.compareTo(runner.getData()) >= 0 ){
				//both values are greater so go to right
				if(runner.right != null && (runner.right.getData().equals(t1) || runner.right.getData().equals(t2))){
					mca = runner;
					break;
				}
				runner = runner.right;
				
			}else{
				mca = runner;
				break;
			}
		}
		return mca;	
	}
	
	private int traverse(BNode<T> node, int count, List<? super BNode<T>> list){
		if(node == null){
			return count;
		}
		if(list != null){
			list.add(node); 
		}
		count++;
		count = traverse(node.left, count, list);
		count = traverse(node.right, count, list);
		return count;
	}
	
	private List<LinkedList<BNode<T>>> getLAtlevel(BNode<T> root){
		List<LinkedList<BNode<T>>> list = new ArrayList<LinkedList<BNode<T>>>();
		if(root == null){
			return list;
		}
		LinkedList<BNode<T>> ll = new LinkedList<BNode<T>>();
		ll.add(root);
		int level = 0;
		
		while (true){
			ll = list.get(level);
			LinkedList<BNode<T>> t1 = new LinkedList<BNode<T>>();
			for(int i=0; i<ll.size();i++){
				if(ll.get(i).getLeft() != null){
					t1.add(ll.get(i).left);
				}
				if(ll.get(i).right != null){
					t1.add(ll.get(i).right);
				}
			}
			if(!t1.isEmpty()){
				list.add(level++, t1);
			}else{
				break;
			}
		}
		
		return list;
	}
	
	private List<LinkedList<BNode<T>>> getLLAtLevel(BNode<T> node){
		
		List<LinkedList<BNode<T>>> list = new ArrayList<LinkedList<BNode<T>>>();
		
		if(node == null){
			return list;
		}
		
		LinkedList<BNode<T>> ll = new LinkedList<BNode<T>>();
		ll.add(node);
		list.add(ll);
		int level = 0;
		
		outerloop:
		while(true){
			
			ll = new LinkedList<BNode<T>>();
			for(BNode<T> n : list.get(level)){
				if(n.left != null ){
					ll.add(n.left);
				}
				if(n.right != null){
					ll.add(n.right);
				}
			}
				
			if(ll.size() > 0){
				list.add(level++,ll);
				continue;
			}else{
				break outerloop;
			}			
		}
		
		return list;
		
		
	}
	
	private void build(BTree2<T> tree, BNode<T>[] na, int start, int length){
		
		if(tree == null){
			return;
		}
		
		if(length < start){
			return;
		}
		
		int mid = (start+length)/2;
		tree.treeInsert(na[mid].getData());
		build(tree, na, start, mid-1);
		build(tree, na, mid+1, length);
	}
	
	public boolean mirrorImage(BNode<T> n1, BNode<T> n2){		
		if(n1 == null && n2 == null){
			return true;
		}else if(n1 == null || n2 == null){
			return false;
		}else if(n1.equals(n2)){
			return (mirrorImage(n1.left, n2.right) && mirrorImage(n1.right, n2.left));
		}else{
			return false;
		}
	}
	
	//2 large binary trees check if T1 is a subtree of T2
	public boolean subTree(BNode<T> t1, BNode<T> t2){
		if(t2 == null){
			//sub tree is null which is always a subtree of t1
			return true;
		}else if(t1.equals(t2)){
			matchTree(t1, t2);
		}else{
			return (subTree(t1.left, t2) || subTree(t1.right, t2));
		}
		
		return false;
	}
	
	public boolean matchTree(BNode<T> n1, BNode<T> n2){
		if(n1 == null && n2 == null){
			return true;
		}else if(n1 == null || n2 == null){
			return false;
		}else if(n1.equals(n2)){
			return (matchTree(n1.left, n2.left) && matchTree(n1.right, n2.right));
		}
		return false;
	}
	
	private BNode<T> mostCommonAncestorOfBinaryTreeNotBST(BNode<T> root, BNode<T> n1, BNode<T> n2){
		if(covers(root.left, n1) && covers(root.left, n2)){
			mostCommonAncestorOfBinaryTreeNotBST(root.left, n1, n2);			
		}else if (covers(root.left,n1) && covers(root.right, n2)){
			mostCommonAncestorOfBinaryTreeNotBST(root.right, n1, n2);
		}
		return root;
	}
	
	private boolean covers(BNode<T> root, BNode<T> n){
		if(root == null){
			return false;
		}
		if(root == n){
			return true;
		}
		
		return covers(root.left, n) || covers(root.right, n);
		
	}
	
	//http://www.youtube.com/watch?v=jSZ4e3cmh2A
	private BNode<T> inOrderSuccessor(BNode<T> node){		
		if(node.right != null){
			BNode<T> n = node.right;
			while(n != null){
				n = n.left;
			}
			return n;
		}else{
			BNode<T> p = node.parent;
			while(p != null && node.getData() == p.right.getData()){
				p = p.parent;
				node = p;				
			}			
			return p;
		}
		
	}
	
	private BNode<T> preOrderSucc(BNode<T> node) {
		if (node.left != null)
			return node.left;
		if (node.right != null)
			return node.right;

		// if node is right child of its parent
		// go up till parent becomes left child.
		// If that does not happen, return -1
		if (node == node.parent.right) {
			while ((node.parent != null) && (node == node.parent.right)) {
				node = node.parent;
			}
		}

		if (node == null)
			return null;

		// if we are here, that means node is now left child of its parent
		// if node is left child of its parent
		BNode<T> temp = node.parent;
		while (temp != null) {
			if (temp.right != null)
				return temp.right;
			temp = temp.parent;
		}
		return null;
	}
	
	
	
	
	public static void main(String[] args){
		BTree2<Integer> tree = new BTree2<Integer>();
		tree.treeInsert(100);
		tree.treeInsert(50);
		tree.treeInsert(150);
		tree.treeInsert(25);
		tree.treeInsert(75);
		tree.treeInsert(125);
		tree.treeInsert(175);
		tree.treeInsert(110);
		
		
		
		tree.preOrderTraversal(tree.root);
		System.out.println();
		tree.inOrderTraversal(tree.root);
		System.out.println("-----");
		tree.preOrderTraversalReccursive(tree.root);
		
		BNode<Integer> n = tree.find(75);
		n = tree.inOrderSuccessor(n);
		n.show();
		
		//System.out.println("tree Height is :"+tree.findHeight(tree.root));
		/*System.out.println("tree contains data 100 :"+tree.treeContains(100));
		System.out.println("tree contains data 110 :"+tree.treeContains(110));
		System.out.println("tree contains data 175 :"+tree.treeContains(175));
		System.out.println("tree contains data 199 :"+tree.treeContains(199));
		
		System.out.println("mca :"+tree.mostCommonAncestor(125, 110));
		
		//BNode[] aBNode = new BNode[tree.traverse(tree.root, 0, null)];
		
		List<BNode<Integer>> nodeList = new ArrayList<BNode<Integer>>();
		
		tree.traverse(tree.root, 0, nodeList);
		Collections.sort(nodeList,new MyBTreeNodeComparator());
		BNode[] na = new BNode[nodeList.size()];
		nodeList.toArray(na);
		System.out.println(ArrayUtils.toString(na));
		
		BTree2<Integer> t2 = new BTree2<Integer>();
		tree.build(t2, na, 0, na.length-1);
		
		t2.preOrderTraversal(t2.root);
		
		
		
		//System.out.println("Nodes :"+);
		
		/*System.out.println(ArrayUtils.toString(aBNode));
		Arrays.sort(aBNode, new MyBTreeNodeComparator());
		
		System.out.println(ArrayUtils.toString(aBNode));*/
	}
	
	class Test{
		int someData = 1;
		private void someMethod(){
			System.out.println(root);
		}
	}
	
	static class Test2{
		private void someMethod(){
			// root cannot be accedd from here 
		}
	}
	
	
}

class BNode<T extends Comparable<? super T> > implements Comparable<BNode<? extends T>>{
	T data;
	BNode<T> left;
	BNode<T> right;
	BNode<T> parent;
	
	public BNode() {
		
	}
	
	public BNode(T data) {
		super();
		this.data = data;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BNode<T> getLeft() {
		return left;
	}
	public void setLeft(BNode<T> left) {
		this.left = left;
	}
	public BNode<T> getRight() {
		return right;
	}
	public void setRight(BNode<T> right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
	
	public void show(){
		System.out.print(data);
		System.out.print(",");
		
	}

	@Override
	public int compareTo(BNode<? extends T> o) {
		// TODO Auto-generated method stub
		return data.compareTo(o.getData());
	}	
}

class MyBTreeNodeComparator<T extends Comparable<T>> implements Comparator<T>{

//class MyBTreeNodeComparator<T extends Comparable<T>> implements Comparator<T>{

	@Override
	public int compare(T t1, T t2) {		
		return t1.compareTo(t2);
	}
}



class B2Node<T extends Comparable<? super T>>{
	B2Node<T> left;
	B2Node<T> right;
	T t;
	
	public B2Node<T> getLeft() {
		return left;
	}
	public void setLeft(B2Node<T> left) {
		this.left = left;
	}
	public B2Node<T> getRight() {
		return right;
	}
	public void setRight(B2Node<T> right) {
		this.right = right;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime * result + ((t == null) ? 0 : t.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		B2Node other = (B2Node) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (t == null) {
			if (other.t != null)
				return false;
		} else if (!t.equals(other.t))
			return false;
		return true;
	}
	
	
	
	
	
	
}

