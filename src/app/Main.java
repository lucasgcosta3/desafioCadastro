package app;

import controller.PetController;

import java.util.Scanner;

import static util.MenuUtil.showMenu;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PetController controller = new PetController();

        while (true) {
            int opcao = showMenu(sc);

            switch (opcao) {
                case 1 -> controller.register(sc);
                case 2 -> System.out.println("alterando");
                case 3 -> System.out.println("deletando");
                case 4 -> controller.list();
                case 5 -> controller.search(sc);
                case 6 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Digite um número de 1 a 6");
            }
        }
    }
}
