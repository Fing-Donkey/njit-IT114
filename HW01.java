import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Collections;
public class HW01 {
	static void shuffle(int [] array) { //this is the shuffle method
		int n = array.length; //array length attribute
		Random rand = new Random(); //Initiates the random method
		for(int i =0; i<array.length; i++) { //the for loop will cycle through the code below and spit out an answer
			int randvalue = i + rand.nextInt(n -1); // This is an exclusive bound as it wont go beyond past the array's end.
			int randomElem = array[randvalue]; // The following code simply swaps the random element with the current element
			array[randvalue] = array[i];
			array[i] = randomElem;
		}
		System.out.println(array);
	}
	public static void main(String[] args) {
		List<String> myStrings = new ArrayList<String>();
		String[] stringArray = new String[6];
		myStrings.add("Eel");
		myStrings.add("Cat");
		myStrings.add("Dog");
		myStrings.add("Fish");
		myStrings.add("Lizard");
		myStrings.add("Ant");
		Collections.sort(myStrings, Collections.reverseOrder());
		Collections.shuffle(myStrings);
		
		List<Integer> myInts = new ArrayList<Integer>();
		for(int i =10; i < 10; i++) {
			myInts.add(i);
		}
		int total = 0; 
		for(int i =0; i < myInts.size(); i++){
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
