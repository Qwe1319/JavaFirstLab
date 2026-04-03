package com.flab;

import java.util.Scanner;


public class Main {
    private static IntContainer container;
    private static Scanner scanner;

    public static void main(String[] args) {
        container = new IntContainer();
        scanner = new Scanner(System.in);

        System.out.println("Контейнер для целых чисел\n");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Выберите операцию: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addElement();
                    break;
                case "2":
                    getElement();
                    break;
                case "3":
                    removeElement();
                    break;
                case "4":
                    printContainer();
                    break;
                case "5":
                    printInfo();
                    break;
                case "6":
                    clearContainer();
                    break;
                case "7":
                    running = false;
                    System.out.println("Работа завершена");
                    break;
                default:
                    System.out.println("Неверная команда. Попробуйте еще раз.\n");
            }
        }

        scanner.close();
    }

    private static void printMenu() {

        System.out.println("1. Добавить элемент");
        System.out.println("2. Получить элемент по индексу");
        System.out.println("3. Удалить элемент по индексу");
        System.out.println("4. Показать все элементы");
        System.out.println("5. Информация о контейнере");
        System.out.println("6. Очистить контейнер");
        System.out.println("7. Выход");
    }

    private static void addElement() {
        try {
            System.out.print("Введите целое число: ");
            String input = scanner.nextLine().trim();
            int value = Integer.parseInt(input);
            container.add(value);
            System.out.println("Элемент " + value + " успешно добавлен!\n");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректное целое число.\n");
        }
    }

    private static void getElement() {
        try {
            System.out.print("Введите индекс (0 до " + (container.size() - 1) + "): ");
            String input = scanner.nextLine().trim();
            int index = Integer.parseInt(input);
            int value = container.get(index);
            System.out.println("Элемент с индексом " + index + " имеет значение: " + value + "\n");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректный индекс.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    private static void removeElement() {
        try {
            System.out.print("Введите индекс для удаления (0 до " + (container.size() - 1) + "): ");
            String input = scanner.nextLine().trim();
            int index = Integer.parseInt(input);
            int removed = container.remove(index);
            System.out.println("Элемент с индексом " + index + " (значение " + removed + 
                ") успешно удален!\n");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректный индекс.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    private static void printContainer() {
        System.out.println("\nСодержимое контейнера: " + container);
        System.out.println("Размер: " + container.size());
        if (container.isEmpty()) {
            System.out.println("Контейнер пуст.\n");
        } else {
            System.out.println("Элементы:");
            for (int i = 0; i < container.size(); i++) {
                System.out.println("  [" + i + "] = " + container.get(i));
            }
            System.out.println();
        }
    }

    private static void printInfo() {
        System.out.println("\nИнформация о контейнере");
        System.out.println("Размер: " + container.size());
        System.out.println("Емкость: " + container.getCapacity());
        System.out.println("Пуст ли: " + (container.isEmpty() ? "Да" : "Нет"));
        System.out.println();
    }

    private static void clearContainer() {
        System.out.print("Уверены? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y") || confirm.equals("yes")) {
            container.clear();
            System.out.println("Контейнер очищен!\n");
        } else {
            System.out.println("Отменено.\n");
        }
    }
}
