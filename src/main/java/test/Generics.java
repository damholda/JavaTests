package test;

import java.util.List;

public class Generics {
	
	private List<?> unknownList;
	private List<Integer> integerList;
	
	public Generics() {
//		setUnknownList(new ArrayList<>());
//		setIntegerList(new ArrayList<Integer>());
	}

	public List<?> getUnknownList() {
		return unknownList;
	}

	public void setUnknownList(List<?> unknownList) {
		this.unknownList = unknownList;
	}

	public List<Integer> getIntegerList() {
		return integerList;
	}

	public void setIntegerList(List<Integer> integerList) {
		this.integerList = integerList;
	}
	
}
