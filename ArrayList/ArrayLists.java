package ArrayList;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayLists<E> {

	private Object elementsArray[];
	private final int INITIAL_CAPACITY=10;
	private int lastElementCounter=0;
	
	private void ensureCapacity()
	{
		if(this.elementsArray.length-1==this.lastElementCounter)
		{
			int newSize=this.elementsArray.length*2;
			this.elementsArray=Arrays.copyOf(this.elementsArray, newSize);
		}
	}
	public ArrayLists()
	{
		this.elementsArray=new Object[this.INITIAL_CAPACITY];
	}
	public int size()
	{
		return lastElementCounter;
	}
	public void print()
	{
		int i=0;
		for (Object object : elementsArray) {
			System.out.println("("+i+") => "+object);
			if(++i==lastElementCounter)
				break;
			
		}
	}
	public int indexOf(E element)
	{
		int i=0;
		for (Object elem : elementsArray) {
			if(elem.equals(element))
				return i;
			if(++i==lastElementCounter)
				break;
		}
		return -1;
	}
	public E get(int index)
	{
		if(index<0 || index>=lastElementCounter)
		{
			throw new IndexOutOfBoundsException("Index "+ index+" out of bound !!");
		}
		return (E) this.elementsArray[index];
	}
	public void add(E element)
	{
		this.ensureCapacity();
		this.elementsArray[this.lastElementCounter++]=element;
	}
	public void remove(E element)
	{
		int indexOf = indexOf(element);
		if(indexOf==-1)
		{
			throw new NoSuchElementException("Element "+element+"not found !!"); 
		}
		else if(indexOf==lastElementCounter-1)
		{
			removeLast();
		}
		else if(indexOf==0)
		{
			removeFirst();
		}
		else
		{
			Object tempRight[]=new Object[this.lastElementCounter-indexOf-1];  //  index=3 then tempRight=3,4,5,6,...,n
			System.arraycopy(elementsArray, indexOf+1, tempRight, 0,lastElementCounter-indexOf-1);
			System.arraycopy(tempRight, 0,this.elementsArray , indexOf,lastElementCounter-indexOf-1);
			removeLast();
		}
		
	}
	public void removeFirst()
	{
		if(lastElementCounter>1)
			System.arraycopy(this.elementsArray, 1,this.elementsArray ,0,lastElementCounter-1);
		removeLast();
	}
	public void removeLast()
	{
		if(lastElementCounter>1)
			this.elementsArray[--lastElementCounter]=null;
	}
	public void addFirst(E element)
	{
		if(lastElementCounter==0)
			add(element);
		else if(lastElementCounter>1)
		{
			Object tempRight[]=new Object[this.lastElementCounter-1]; 
			System.arraycopy(elementsArray, 0, tempRight, 0,lastElementCounter-1);
			this.elementsArray[0]=element;
			System.arraycopy(tempRight, 0,this.elementsArray , 1,lastElementCounter-1);
			lastElementCounter++;
		}
		else if(lastElementCounter==1)
		{
			Object temp=this.elementsArray[0];
			this.elementsArray[0]=element;
			this.elementsArray[1]=temp;
			lastElementCounter++;
		}
	}
	
	public void add(E element,int index)
	{
		if(index<0)
		{
			throw new IndexOutOfBoundsException("Index "+ index+" out of bound !!");
		}
		else if(index>=lastElementCounter)
		{
			index=lastElementCounter;
			add(element);
		}
		else if(index==0)
		{
			addFirst(element);
		}
		else
		{
			this.ensureCapacity();
			Object tempRight[]=new Object[this.lastElementCounter-index];  //  index=3 then tempRight=3,4,5,6,...,n
			System.arraycopy(elementsArray, index, tempRight, 0,lastElementCounter-index);
			this.elementsArray[index]=element;
			System.arraycopy(tempRight, 0,this.elementsArray , index+1,lastElementCounter-index);
			this.lastElementCounter++;
		}
		
		
	}
}
