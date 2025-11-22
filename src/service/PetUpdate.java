package service;

import model.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetUpdate {
    public List<Pet> updatePet(List<Pet> results, PetRegister petRegister, Scanner sc) {
        List<Pet> pets = new ArrayList<>();

        while (true) {
            try {
                int index = Integer.parseInt(sc.nextLine()) - 1;
                Pet oldPet = results.get(index);

                Pet updatedPet = petRegister.registerPet(sc, true);
                updatedPet.setPetType(oldPet.getPetType()); // manter o mesmo tipo para o pet
                updatedPet.setPetSex(oldPet.getPetSex()); // manter o mesmo sexo para o pet

                pets.add(oldPet);
                pets.add(updatedPet);
                return pets;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.err.println("Entrada inválida. Tente novamente.");
            }
        }
    }
}
