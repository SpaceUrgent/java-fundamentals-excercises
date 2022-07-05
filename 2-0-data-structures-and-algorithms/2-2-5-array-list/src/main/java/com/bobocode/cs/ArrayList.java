package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @author Serhii Hryhus
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size;


    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        // todo: implement this method
        if (initCapacity <= 0){
            throw new IllegalArgumentException();
        }
        elements = new Object[initCapacity];
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        // todo: implement this method
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {
        // todo: implement this method
        ArrayList<T> arrayList = new ArrayList<>(elements.length);
        arrayList.elements = Arrays.copyOf(elements, elements.length);
        arrayList.size = elements.length;
        return arrayList;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        // todo: implement this method
        increaseArrayCapacityIfFull();
        elements[size] = element;
        size++;
    }

    private void increaseArrayCapacityIfFull() {
        if (elements.length == size){
            elements = Arrays.copyOf(elements, size * 2);
        }
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        // todo: implement this method

        increaseArrayCapacityIfFull();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    public T get(int index) {
        // todo: implement this method
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        // todo: implement this method
        if (isEmpty())
            throw new NoSuchElementException();
        return (T) elements[0];
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        // todo: implement this method
        if (isEmpty())
            throw new NoSuchElementException();
        return (T) elements[size - 1];
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
       // todo: implement this method
        Objects.checkIndex(index, size);
        elements[index] = element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        // todo: implement this method
        Objects.checkIndex(index, size);
        T removed = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removed;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
        // todo: implement this method
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (elements[i] == element){
                result = true;
            }
        }
        return result;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        // todo: implement this method
        return size == 0;
    }

    /**
     * @return amount of saved elements
     */
    @Override
    public int size() {
        // todo: implement this method
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        // todo: implement this method
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }
}
