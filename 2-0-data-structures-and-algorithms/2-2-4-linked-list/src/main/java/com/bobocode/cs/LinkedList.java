package com.bobocode.cs;


import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Objects;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    private static class Node<T>{
        T element;
        Node<T> next;

        Node(T element){
            this.element = element;
        }
    }


    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        // todo: implement this method
        Node<T> newNode = new Node<>(element);
        if (first == null){
            first = last = newNode;
        }
        last.next = newNode;
        last = newNode;
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        // todo: implement this method
        Objects.checkIndex(index, size);
        if (index == 0) addAsHead(element);
        if (index == size) addAsLast(element);
        addByIndex(index, element);
    }



    private void addAsHead(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.next = first;
        first = newNode;
        size++;
    }

    private void addAsLast(T element) {
        Node<T> newNode = new Node<>(element);
        last.next = newNode;
        last = newNode;
        size++;
    }

    private void addByIndex(int index, T element){
        Node<T> current = first;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Node<T> newNode = new Node<>(element);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        Node<T> node = findNodeByIndex(index);
        node.element = element;
    }

    private Node<T> findNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        if (index == size - 1) {
            return last;
        } else {
            return nodeAt(index);
        }
    }

    private Node<T> nodeAt(int index) {
        Node<T> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {

        return findNodeByIndex(index).element;
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
        return findNodeByIndex(0).element;
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
        return findNodeByIndex(size - 1).element;
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

    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }
}
