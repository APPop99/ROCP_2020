package egQueue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
//import java.util.PriorityQueue;
import java.util.Queue;
//import java.util.function.Consumer;

public class DemoQueueMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Queue li=new LinkedList();
		
		li.add("one");
		li.add(1211111);
		li.add(true);
		li.add('d');
		li.add(1233.4455f);
		System.out.println("The content of the queue is: "+li);

		//Left hand side should be the interface and right hand side should be its implementing class
		Queue<Integer> list1=new LinkedList<>();
		list1.add(123);
		list1.add(123);
		list1.add(121);
		list1.add(null);
		list1.add(null);
		list1.add(44);
		list1.add(12);
		list1.add(123);
		list1.add(123);
		list1.add(null);
		list1.add(55);							//inserting at the end
		System.out.println("Queue 1 contains: "+list1);
		list1.add(66); 							//Queue inserts ALWAYS at the end
		System.out.println("Queue 1 : "+list1);
//		list1.set(3, 77);						//Queue does not allow updating
//		System.out.println("Queue 1 : "+list1);
		
		//test if the queue is empty
		System.out.println("Queue 1 is empty : "+list1.isEmpty());
		
		//returning the size of the queue (no of elements)
		System.out.println("Queue 1 contains: "+list1.size()+" elements");
		System.out.println("Queue 1 size is: "+list1.size()+"\n"); //no of elements in the list

		//Removing elements
		int position=3;
		list1.remove(position); 						//index(position)
		System.out.println("Removing the element from position "+position+"\nQueue 1 contains now: "+list1+"\n");
		
		Integer i=123;
		list1.remove(i); 						//removes first occurrence of that object value
		System.out.println("Removing first occurence of "+i+"\nQueue 1 contains now: "+list1+"\n");
		
		while(list1.remove(i)) {} 				//remove all the occurrences of that object value
		System.out.println("Removing all occurences of element "+i+"\nQueue 1 contains now: "+list1+"\n");
		
		System.out.println("list1.getClass() : "+list1.getClass()); //returns the class type of the queue
		
		System.out.println("Queue 1 contains '100' : "+list1.contains(100));
		System.out.println("Queue 1 contains 'null' : "+list1.contains(null));
		
		
//		System.out.println("Iterating using normal for loop");
//		for (Consumer<? super Integer> j = 0; j < list1.size(); j++) 
//		{
//			System.out.println(list1.forEach(j));
//		}
		
		System.out.println("Iterating using a foreach loop");
		for(Integer i2:list1) 
		{
			System.out.println(i2);
		}
	}
}
