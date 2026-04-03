package com.flab;

/**
 * Контейнер для хранения произвольного количества целых чисел.
 * Использует динамический массив для хранения элементов.
 * Встроенные коллекции не используются.
 * 
 * @author Vlad
 * @version 1.0
 */
public class IntContainer {
    private int[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор по умолчанию. Инициализирует контейнер с начальной емкостью 10.
     */
    public IntContainer() {
        this.elements = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Конструктор с указанной начальной емкостью.
     * 
     * @param initialCapacity начальная емкость контейнера
     * @throws IllegalArgumentException если initialCapacity меньше 1
     */
    public IntContainer(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Емкость должна быть больше 0");
        }
        this.elements = new int[initialCapacity];
        this.size = 0;
    }

    /**
     * Добавляет целое число в конец контейнера.
     * При необходимости автоматически расширяет емкость.
     * 
     * @param value значение для добавления
     */
    public void add(int value) {
        if (size == elements.length) {
            expandCapacity();
        }
        elements[size] = value;
        size++;
    }

    /**
     * Получает элемент по индексу.
     * 
     * @param index индекс элемента (0-based)
     * @return значение элемента
     * @throws IndexOutOfBoundsException если индекс вне границ [0, size)
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне границ: " + index + 
                ", размер: " + size);
        }
        return elements[index];
    }

    /**
     * Удаляет элемент по индексу и сдвигает остальные элементы влево.
     * При необходимости автоматически сокращает емкость.
     * 
     * @param index индекс удаляемого элемента
     * @return удаленное значение
     * @throws IndexOutOfBoundsException если индекс вне границ [0, size)
     */
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне границ: " + index + 
                ", размер: " + size);
        }
        
        int removedValue = elements[index];
        
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        
        size--;
        
        if (size > 0 && size <= elements.length / 4) {
            shrinkCapacity();
        }
        
        return removedValue;
    }

    /**
     * Возвращает текущее количество элементов в контейнере.
     * 
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли контейнер.
     * 
     * @return true если контейнер пуст, false иначе
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Очищает контейнер, удаляя все элементы и восстанавливая емкость по умолчанию.
     */
    public void clear() {
        size = 0;
        elements = new int[DEFAULT_CAPACITY];
    }

    /**
     * Возвращает текущую емкость контейнера (количество выделенных ячеек).
     * 
     * @return емкость контейнера
     */
    public int getCapacity() {
        return elements.length;
    }

    private void expandCapacity() {
        int[] newElements = new int[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }


    private void shrinkCapacity() {
        int newCapacity = Math.max(DEFAULT_CAPACITY, elements.length / 2);
        int[] newElements = new int[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }


    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
