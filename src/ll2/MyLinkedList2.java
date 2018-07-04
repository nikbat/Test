package ll2;

import java.util.LinkedList;

import ll.LNode;

public class MyLinkedList2<T> {
	
	private L2Node<T> root;
	private L2Node<T> last;
	int size;
	
	public MyLinkedList2(){
		
	}
	
	public void add(T t) {
		
		L2Node<T> temp = new L2Node<T>(t);
		
		if(root == null){
			root = last = temp;
		}else{			
			last.setNext(temp);
			last = temp;
		}		
		size++;
	}
	
	public void addFront(T t){
		L2Node<T> temp = new L2Node<T>(t);
		temp.setNext(root);
		root = temp;
		
	}
	
	public String print(){
		L2Node<T> temp = root;
		StringBuilder sb = new StringBuilder();		
		while(temp != null ){
			sb.append(temp.getData().toString() + "->");
			temp = temp.getNext();
		}
		return sb.toString();
	}

	
	
	public boolean isCyclic(){
		
		L2Node<T> temp1 = root;
		L2Node<T> temp2 = root;
		
		while(temp2.getNext() != null){
			temp1 = temp1.getNext(); 
			temp2 = temp2.getNext().getNext();
			
			if(temp2 == null){
				return false; //there is no cyclic if we reach to end of 
			}
			
			if(temp1.getData().toString().equals(temp2.getData().toString())){
				break; //we have a cyclic Linked List
			}
		}
		
		//Now make temp 1 as root
		temp1 = root;
		
		while (! temp1.getData().toString().equals(temp2.getData().toString())){
			temp1 = temp1.getNext();
			temp2 = temp2.getNext();
		}
		
		System.out.println("cyclic node is :"+temp1.getData().toString() );
		
		return false;
	}
	
	public void partitionList(MyLinkedList2<Integer> ll,  int i){
		L2Node<Integer> left = null;
		L2Node<Integer> right = null;
		L2Node<Integer> leftStart = null;
		L2Node<Integer> rightStart = null;
		
		L2Node<Integer> current = ll.root;
		
		while(current != null ){
			if(current.getData() < i){
				if(left == null){
					left = new L2Node<>(current.getData());
					leftStart = left;					
				}else{
					L2Node<Integer> temp = new L2Node(current.getData());
					left.setNext(temp);
					left = temp;					
				}
			}else{
				if(right == null){
					right = new L2Node<>(current.getData());
					rightStart = right;
				}else{
					L2Node<Integer> temp = new L2Node(current.getData());
					right.setNext(temp);
					right = temp;
				}
			}
			
			current = current.getNext();
		}
		
		if(leftStart != null){
			left.setNext(rightStart);
		}
		
		MyLinkedList2<Integer> l = new MyLinkedList2<>();
		l.root = leftStart;
		System.out.println(l.print());
		
		
	
		
				
	}
	
	public L2Node<Integer> s(L2Node<Integer> la, L2Node<Integer> lb, int carry){
		L2Node<Integer> n = null;
		if(la == null && lb == null){
			return null;
		}else{
			
			int data = carry;
			if(la != null){
				data = data+la.getData();
			}
			if(lb != null){
				data = data+lb.getData();
			}
			n = new L2Node(data%10);
			n.setNext(s(la.getNext(), lb.getNext(), data/10));
			return n;
		}
	}
	
	public String printList(L2Node<String> n){
		if(n == null){
			return "";
		}else{
			return printList(n.getNext()) + n.getData();
		}
	}

	//https://practice.geeksforgeeks.org/problems/merge-two-sorted-linked-lists/1
	static L2Node<Integer> mergeList(L2Node<Integer> r1, L2Node<Integer> r2){

		if(r1 == null){
			return r2;
		}

		if(r2 == null){
			return r1;
		}

		L2Node<Integer> root = null;
		L2Node<Integer> runner = null;


		if(r1.getData() < r2.getData()){
			root = r1;
			r1 = r1.getNext();
		}else{
			root = r2;
			r2 = r2.getNext();
		}
		runner = root;

		while(true){
			if(r1 == null || r2 == null){
				break;
			}

			if(r1.getData() < r2.getData()){
				runner.setNext(r1);
				r1 = r1.getNext();
				runner = runner.getNext();
			} else if(r2.getData() < r1.getData()){
				runner.setNext(r2);
				r2 = r2.getNext();
				runner = runner.getNext();
			}
		}

		if(r1 != null){
			runner.setNext(r1);
		}

		if(r2 != null){
			runner.setNext(r2);
		}

		return root;

	}
	
	
	
	public static void main(String[] args){
		MyLinkedList2<String> ll = new MyLinkedList2<String>();
		ll.add("A");
		ll.add("B");
		ll.add("C");
		ll.add("D");
		ll.add("E");
		ll.add("B");
		ll.add("F");
		ll.add("G");
		ll.add("H");
		ll.add("A");
		ll.add("I");
		ll.add("J");
		ll.add("K");
		ll.add("D");
		
		System.out.println(ll.printList(ll.root));
		
		System.out.println(ll.print());
		ll.isCyclic();
		
		MyLinkedList2<Integer> l1 = new MyLinkedList2<Integer>();
		l1.add(3);
		l1.add(5);
		l1.add(8);
		l1.add(5);
		l1.add(10);
		l1.add(2);
		l1.add(1);
		
		l1.partitionList(l1, 5);
		
		MyLinkedList2<Integer> la = new MyLinkedList2<>();
		la.add(7);
		la.add(1);
		la.add(6);
		MyLinkedList2<Integer> lb = new MyLinkedList2<>();
		lb.add(5);
		lb.add(9);
		lb.add(2);
		L2Node<Integer> n = l1.s(la.root, lb.root, 0);
		System.out.println(n);

		MyLinkedList2<Integer> lm1 = new MyLinkedList2<Integer>();
		lm1.add(1);
		lm1.add(3);
		lm1.add(5);
		lm1.add(7);
		lm1.add(9);

		MyLinkedList2<Integer> lm2 = new MyLinkedList2<Integer>();
		lm2.add(2);
		lm2.add(4);
		lm2.add(6);
		lm2.add(8);
		lm2.add(12);

		L2Node<Integer> nm = MyLinkedList2.mergeList(lm1.root, lm2.root);
		System.out.println(nm);
		
	}
}

class L2Node<T> {	
	
	private T data;
	private L2Node<T> next;
	
	public L2Node(T data) {
		super();
		this.data = data;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public L2Node<T> getNext() {
		return next;
	}
	public void setNext(L2Node<T> next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return data.toString();
	}
	
	

}

