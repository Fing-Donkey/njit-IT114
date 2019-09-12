import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class Test {
	public static void main(String[] args) {
		List<String> myStrings = new ArrayList<String>();
		String[] stringArray = new String[3];
		myStrings.add("Hi");
		myStrings.add("Bye");
		myStrings.add("Hi again");
		myStrings.add("I'm here again");
		myStrings.add("and tomorrow");
		
		/*int myInt =1;
		String testString =myInt+"";
		System.out.println(testString);
		if(testString.equals(myInt)){
			System.out.println("testString =myInt");
		}
		else {
			System.out.println("testString != myInt");
		}
		*/
		Collections.sort(myStrings, Collections.reverseOrder());
		Collections.shuffle(myStrings);
		List<Integer> myInts = new ArrayList<Integer>();
		for(int i=0; i <10; i++) {
			myInts.add(i);
		}
		int total =0;
		for(int i = 0; i < myInts.size(); i++){		
			total += myInts.get(i);
		}
		System.out.println("My total" + total);
		
		for(int i=0; i <10; i++) {
			if(i % 2 ==0 ) {
				System.out.println(i + "is even");
			}
			else {
				System.out.println(i + "is odd");
			}
		}
		
		int size = myStrings.size();
		for(int i =0; i< size; i++) {
			System.out.println("Index[" + i+"] =>" + myStrings.get(i));
			
		}
	}
}
