package com.company;

import com.company.entity.BinaryTreeNode;
import com.company.exceptions.TreeAppException;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
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

            WorkWithTree(service, scanner, exit);
        } catch (InputMismatchException | TreeAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void WorkWithTree(TreeService service, Scanner scanner, int exit) throws TreeAppException {
        while (exit != 0) {
            PrintOperations();
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    exit = 0;
                    break;

                case 1:
                    System.out.println("Enter the value to add: ");
                    int insertValue = scanner.nextInt();
                    service.insert(insertValue);
                    service.PrintBinaryTreeWide();
                    break;

                case 2:
                    System.out.println("Enter the value to search: ");
                    int searchValue = scanner.nextInt();
                    BinaryTreeNode foundValue = service.search(searchValue);
                    if (foundValue == null) {
                        System.out.println("Value not found");
                    } else {
                        System.out.println("Value found " + foundValue.getValue());
                    }
                    break;

                case 3:
                    System.out.println("Enter the value to delete: ");
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
                    throw new TreeAppException("Operation does not exist");
            }
        }
    }

    private static void PrintOperations() {
        System.out.println("Выберите операцию.");
        System.out.println("0 - Exit");
        System.out.println("1 - Add value");
        System.out.println("2 - Search value");
        System.out.println("3 - Delete value");
        System.out.println("4 - Displaying the entire tree");
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
