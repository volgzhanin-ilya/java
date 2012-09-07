package ru.spbstu.telematics.vector;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Vector<T> implements IVector<T>, Iterable<T> {

		private int current_size = 0; 	// ���������� ��������� ���������
		private T[] mass; 				// ������ ��� ������ � ����������
		private int maxSizeVector = 0;	// ������������ ������ �������
		int n; 		
		
		public Vector (int size, int maxSizeVector){
			mass = (T[]) new Object[size];
			this.maxSizeVector = maxSizeVector;
		}
		
		// ��������� ������ � ��������� 
		public void add (T obj){
			// ��������� ������� �� ��������� �������
			mass[current_size] = obj;
			current_size++;
			System.out.println("Add element '" + obj + "' in vector");
			checkSizeCollectionsFull();
		}
		
		// ��������� ������ � ��������� �� ��������� �������
		public void add (T obj, int index){
			if (index > (current_size + 1))
				index = current_size + 1;
			int i = current_size;
			while (i > index){
				mass[i] = mass[i - 1];
				i--;
			}
			mass[index] = obj;
			current_size++;
			System.out.println("Add element '" + obj + "' in vector with index " + index);
			checkSizeCollectionsFull();
		}
		
		
		// ������� ������ �� ���������, ����������� �� ��������� �������
		public boolean remove (int index){
			System.out.println("Delete elemet vector with index: " + index);
			if (index < 0 || index > current_size || current_size == 0) 
				return false;
			int i = current_size;
			while (i > index){
				mass[i] = mass[i - 1];
				i--;
			}
			current_size--;
			checkSizeCollectionsEmpty();
			return true;
		}
		
		// ����� ������� �� �����
		public void print(){
			if (current_size < 0)
				System.out.println("\nVector empty!");																	
			else {
				System.out.println("\nVector:");
				for (int i = 0;  i < current_size; i++)
					System.out.println("mass[" + i + "] = " + mass[i] + " ");
			}
			System.out.println("\nThe number of vector: " + current_size + "(" + mass.length + ")\n");
		} 
		
		// ���������� ������, ����������� �� ������������ �������
		public T get(int index) {
			System.out.print("\nGets the element at position " + index + "\n");
			if((index > current_size) || (index <= 0)){
				throw new IndexOutOfBoundsException();
			}
			return mass[(index-1)];
		}

		
		//  ���������� ������ �������, ���� ����� ���� � �������. ���� ������ ���, �� -1.
		public int indexOf(T obj) {
			System.out.print("\nSearch index element vector " + obj.toString() + "\n");
			for(int i = 0; i < current_size; i++){
				if(obj == mass[i])
					return (i + 1);
			}
			System.out.print("No such element in vector ");
			return -1;
		}
		
		
		// ������ ������ � ����������������� ����������
		// ��� ���������� ��������� - ����������� �����, ��� ��������� �� 3/4 - ��������� �����
		// ����� ��������� �� ���� ����������� - ������������� ����������� �� ������� ���������
		// ��� ��� ���������� - ������� ����� ������
		public void checkSizeCollectionsFull(){
			if(mass.length == current_size) {
				System.out.print("\nVector is full! The size will be increased");
				@SuppressWarnings("unchecked")
				T[] newMass = (T[]) new Object[mass.length*2];
				for(int i = 0; i < current_size; i++)
					newMass[i] = mass[i];
				mass = newMass;
				System.out.println("\nVector size: " + newMass.length);
			}
			else if (mass.length >= maxSizeVector){
				System.out.print("Maximum size of the collection! Create a new array");
				T[] newMass = (T[]) new Object[mass.length];
				mass = newMass;
			}
			
		}
		
		public void checkSizeCollectionsEmpty() {
			if (current_size <= (mass.length/4)){
				System.out.print("\nVektor almost empty. The vector size is reduced\n");
				T[] newMass = (T[]) new Object[(mass.length/2)];
				for(int i = 0; i < current_size; i++)
					newMass[i] = mass[i];
				mass = newMass;
				System.out.println("\nVector size: " + newMass.length);
			}
		}
		
		// ��������� ��������
		private class Iter implements Iterator<T> {
			// ����� hasNext ��������� ����������, �������� �� ��� �� ������������ �������� � ���������
			@Override
			public boolean hasNext() {
				if (n < current_size)
					return true;
				else 
					return false;
			}

			// ����� next() ���������� ��������� ������� ���������
			@Override
			public T next() {
				if (n == current_size)
					throw new NoSuchElementException();
				else {
					T result = mass[n];
					n++;
					return result;
				}	
			}
		
			// ����� remove ������� �������, ����� ������������ next()
			@Override
			public void remove() {
				Vector.this.remove(n);
			}	
		}
		
		public Iterator<T> iterator() {
				return new Iter();
		}



}
