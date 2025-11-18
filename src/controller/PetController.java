package controller;

import model.Pet;
import service.FileService;
import service.PetService;

import java.util.Scanner;

public class PetController {
    private final PetService petService = new PetService();
    private final FileService fileService = new FileService();

    public void register(Scanner sc) {
        System.out.println("--- Cadastrar Pet ---");
        Pet pet = petService.registerPet(sc);
        fileService.savePetFile(pet);
        System.out.println("Pet cadastrado e salvo com sucesso!");
    }
}
