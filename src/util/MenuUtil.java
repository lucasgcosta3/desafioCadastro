package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUtil {
    public static int showMenu(Scanner sc) {
        System.out.println("""
                -----------------------------------------------------
                1. Cadastrar um novo pet
                2. Alterar os dados do pet cadastrado
                3. Deletar um pet cadastrado
                4. Listar todos os pets cadastrados
                5. Listar pets por algum critério (idade, nome, raça)
                6. Sair""");
        System.out.print("Opção: ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static List<String> showSearchMenu(Scanner sc) {
        ValidatorUtil validator = new ValidatorUtil();
        List<String> filters = new ArrayList<>();

        while (true) {
            try {
                System.out.println("Tipo do pet: ");
                String type = sc.nextLine().trim();
                filters.add(validator.validateType(type).getDescription());
                break;
            } catch (RuntimeException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }

        String filter = null;
        while (filter == null) {
            showFilterMenu();
            filter = readFilter(sc);
            if (filter == null) {
                System.err.println("Opção inválida. Digite um número de 1 a 6.");
            }
        }
        System.out.println("Digite o valor para o filtro 1: ");
        String val = sc.nextLine().trim();
        filters.add(filter + "=" + val);

        System.out.println("Deseja selecionar outro critério (s/n)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("s")) {
            filter = null;
            while (filter == null) {
                showFilterMenu();
                filter = readFilter(sc);
                if (filter == null) {
                    System.err.println("Opção inválida. Digite um número de 1 a 6.");
                }
            }
            System.out.println("Digite o valor para o filtro 2: ");
            val = sc.nextLine().trim();
            filters.add(filter + "=" + val);
        }
        return filters;
    }

    private static void showFilterMenu() {
        System.out.println("""
                -----------------------------------------------------
                Escolha um critério:
                1. Nome ou sobrenome
                2. Sexo
                3. Idade
                4. Peso
                5. Raça
                6. Endereço""");
        System.out.print("Opção: ");
    }

    private static String readFilter(Scanner sc) {
        try {
            int option = Integer.parseInt(sc.nextLine());
            return switch (option) {
                case 1 -> "name";
                case 2 -> "sex";
                case 3 -> "age";
                case 4 -> "weight";
                case 5 -> "breed";
                case 6 -> "address";
                default -> null;
            };
        } catch (NumberFormatException e) {
            System.err.print("Erro: ");
            return null;
        }
    }
}
