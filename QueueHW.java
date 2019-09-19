import java.util.Queue;
import java.util.LinkedList;
public class QueueHW {
	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i =0; i<5; i++) {
			q.add(new Integer(i, "A value"));
		}
		System.out.println("Elements of queue-"+q); 
		
		int size = q.size();
		System.out.println("Size of queue" + size); 
		
		Integer head = q.peek();
		System.out.println("head of queue-" + head);
		
		Integer Value =  (Integer) q.remove();
		System.out.println("Pulled value: "+ Value);
		System.out.println("Show altered queue: " + q);
		
		Integer peek = q.peek();
		System.out.println("Show queue" + q);
		
		Integer removedele = q.remove();
		System.out.println("Removed element: " + removedele);
		
	}
}
class Integer{
	public int key;
	public String value;
	public Integer(int k, String v) {
		this.key = k;
		this.value = v;
	}
	public String toString() {
		return "{'key':'" + this.key + "', 'value':'" + this.value + "'}"; //2
	}
}