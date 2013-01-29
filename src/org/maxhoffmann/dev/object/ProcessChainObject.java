package org.maxhoffmann.dev.object;

public class ProcessChainObject implements Comparable<ProcessChainObject> {

	private String processChain;
	private int number;
	private int countSubDiff;

	public ProcessChainObject() {
	}

	public ProcessChainObject(String processChain, int number, int countSubDiff) {
		this.processChain = processChain;
		this.number = number;
		this.countSubDiff = countSubDiff;
	}

	public String getProcessChain() {
		return processChain;
	}

	public void setProcessChain(String processChain) {
		this.processChain = processChain;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getCountSubDiff() {
		return countSubDiff;
	}
	
	public void setCountSubDiff(int countSubDiff) {
		this.countSubDiff = countSubDiff;
	}

	/**
	 * Determines the comparison of the current object to the passed one.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ProcessChainObject o) {
		int i = 0;
		if (this.countSubDiff > o.countSubDiff)
			i = -1;
		else 
			i = 1;
		return i;
	}

}