package datatest;

import java.util.ArrayList;
import java.util.List;

public class IterationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			List<Object> integers = new ArrayList<>();
			integers.add(1);
			integers.add(2);
			integers.add(3);
			integers.add(4);
			integers.add(5);
			integers.add(6);
			integers.add("riya");
				
			for(Object iny: integers){
				System.out.println(iny);
			}
			
	}

}
