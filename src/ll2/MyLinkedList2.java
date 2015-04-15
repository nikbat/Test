package ll2;

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
	
	public static void main(String[] args){
		MyLinkedList2<String> ll = new MyLinkedList2<String>();
		ll.add("A");
		ll.add("B");
		ll.add("C");
		ll.add("D");
		ll.add("E");
		ll.add("F");
		ll.add("G");
		ll.add("H");
		ll.add("I");
		ll.add("J");
		ll.add("K");
		ll.add("D");
		
		System.out.println(ll.print());
		ll.isCyclic();
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

