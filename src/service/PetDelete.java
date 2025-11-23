package service;

import model.Pet;

import java.util.List;
import java.util.Scanner;

public class PetDelete {
    public Pet deletePet(List<Pet> results, Scanner sc) {
        int index;
        while (true) {
            try {
                index = Integer.parseInt(sc.nextLine());
                if (index < 0 || index > results.size()) {
                    System.err.println("Número fora da lista. Tente novamente.");
                } else {
                    break; // índice válido, sai do loop
                }
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida. Digite um número.");
            }
        }

        while (true) {
            System.out.println("Tem certeza que deseja excluir o Pet (sim/nao)?");
            String input = sc.nextLine().toUpperCase().trim();

            if (input.equals("SIM")) {
                return results.get(index - 1);
            } else if (input.equals("NAO")) {
                return null;
            } else {
                System.err.println("Resposta inválida. Digite 'sim' ou 'nao'.");
            }
        }
    }
}
