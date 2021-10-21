package com.company;

import com.company.entity.BinaryTreeNode;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TreeService service = new TreeService();
        System.out.print("Input size a tree. Size:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        if (size == 0) {
            System.out.println("Input size is 0");
            return;
        }

        ToFormTree(size, service);

        service.printTreeDeep();
        service.PrintBinaryTreeWide();
        System.out.println(service.root.toString());

        int exit = 1;

        while (exit != 0) {
            PrintOperations();
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    exit = 0;
                    break;

                case 1:
                    System.out.println("Введите значение для добавления: ");
                    int insertValue = scanner.nextInt();
                    service.insert(insertValue);
                    service.PrintBinaryTreeWide();
                    break;

                case 2:
                    System.out.println("Введите значение для поиска: ");
                    int searchValue = scanner.nextInt();
                    BinaryTreeNode foundValue = service.search(searchValue);
                    if(foundValue == null){
                        System.out.println("Значение не найдено");
                    }else{
                        System.out.println("Value found " + foundValue.getValue());
                    }
                    break;

                case 3:
                    System.out.println("Введите значение для удаления: ");
                    int deleteValue = scanner.nextInt();
                    service.delete(deleteValue);
                    service.PrintBinaryTreeWide();
                    break;

                case 4:
                    service.printTreeDeep();
                    service.PrintBinaryTreeWide();
                    System.out.println(service.root.toString());
                    break;

                default:
                    System.out.println("Нет такой операции");
                    break;
            }
        }
    }

    private static void PrintOperations() {
        System.out.println("Выберите операцию.");
        System.out.println("0 - Выход");
        System.out.println("1 - Добавить значение");
        System.out.println("2 - Поиск значения");
        System.out.println("3 - Удаление");
        System.out.println("4 - Вывод всего дерева");
    }

    public static BinaryTreeNode ToFormTree(int size, TreeService service) {
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            boolean isSuccessfule = service.insert(random.nextInt(99));
            if (!isSuccessfule) {
                size += 1;
            }
        }

        return service.root;
    }
}
