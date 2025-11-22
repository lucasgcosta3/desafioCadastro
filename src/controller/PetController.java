package controller;

import model.Pet;
import service.FileService;
import service.PetRegister;
import service.PetSearch;
import service.PetUpdate;

import java.util.List;
import java.util.Scanner;

import static util.MenuUtil.showSearchMenu;

public class PetController {
    private final PetRegister petRegister = new PetRegister();
    private final PetSearch petSearch = new PetSearch();
    private final PetUpdate petUpdate = new PetUpdate();
    private final FileService fileService = new FileService();

    public void register(Scanner sc) {
        System.out.println("--- Cadastrar Pet ---");
        Pet pet = petRegister.registerPet(sc, false);
        fileService.savePetFile(pet);
        System.out.println("Pet cadastrado e salvo com sucesso✅!");
    }

    public void list() {
        System.out.println("--- Pets cadastrados ---");
        List<Pet> pets = fileService.readPetFile();
        if (!pets.isEmpty()) {
            int i = 1;
            for (Pet pet : pets) {
                System.out.println(i++ + ". " + pet);
            }
        } else {
            System.out.println("Nenhum pet cadastrado.");
        }
    }

    public List<Pet> search(Scanner sc) {
        System.out.println("--- Buscar Pets ---");

        List<String> filters = showSearchMenu(sc);
        List<Pet> pets = fileService.readPetFile();

        System.out.println("--- Resultados ---");
        List<Pet> results = petSearch.searchPets(filters, pets);
        if (!results.isEmpty()) {
            int i = 1;
            for (Pet pet : results) {
                System.out.println(i++ + ". " + pet);
            }
        } else {
            System.out.println("Nenhum pet encontrado.");
        }
        return results;
    }

    public void update(Scanner sc) {
        List<Pet> results = search(sc);
        if (!results.isEmpty()) {
            System.out.println("--- Alterar Pet ---");
            System.out.println("Escolha o número do pet que quer alterar: ");

            List<Pet> pets = petUpdate.updatePet(results, petRegister, sc);
            fileService.updatePetFile(pets);

            System.out.println("Pet alterado com sucesso✅");
        }
    }
}
