package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GenericsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGenerics() {
//		Generics gen = new Generics();
		
		Integer integer = 5;
		int i = 2;
		Object intObj = 3;
		Object integerObj = new Integer(6); 
		
		List<Integer> intList = new ArrayList<Integer>();
//		gen.setIntegerList(intList);
		intList.add(i);
//		intList.add(intObj);
//		intList.add(integerObj);
		intList.add(integer);
		
		int iFromList = intList.get(0);
		Integer integerFromList = intList.get(0);
		Object intObjFromList = intList.get(0);
		Object integerObjFromList = intList.get(0);
		
		List<?> list = intList;
//		list.add(i);
//		list.add(intObj);
//		list.add(integerObj);
//		list.add(integer);
		
//		iFromList = list.get(0);
//		integerFromList = list.get(0);
		intObjFromList = list.get(0);
		integerObjFromList = list.get(0);
		
		System.out.println(integerFromList+", "+iFromList+", "+intObjFromList+", "+integerObjFromList);
		
		System.out.println(integer+", "+i+", "+intObj+", "+integerObj);
		
		System.out.println(intList);
		System.out.println(list);
		assertTrue(true);
		
	}
	

}
