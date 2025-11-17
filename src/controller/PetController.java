package controller;

import service.PetService;

import java.util.Scanner;

public class PetController {
    private final PetService petService = new PetService();

    public void register(Scanner sc) {
        System.out.println("--- Cadastrar Pet ---");
        petService.registerPet(sc);
        System.out.println("Pet cadastrado com sucesso!");
    }
}
