package service;

import model.Pet;
import model.PetAddress;
import model.PetSex;
import model.PetType;
import util.ConstantUtil;
import util.ValidatorUtil;

import java.util.List;
import java.util.Scanner;

import static util.FormUtil.readForm;

public class PetRegister {
    private final ValidatorUtil validator = new ValidatorUtil();

    public Pet registerPet(Scanner sc, boolean isUpdate) {
        Pet pet = new Pet();
        List<String> questions = readForm();

        for (String q : questions) {
            if (isUpdate && (q.contains("sexo") || q.contains("tipo"))) {
                continue;
            }

            while (true) {
                System.out.println(q);
                try {
                    if (q.contains("endereço")) { // evitar ENTER desnecessário ao mostrar pergunta 4
                        pet.setPetAddress(validator.askAddress(sc));
                        break;
                    }
                        String answer = sc.nextLine();
                        validateAnswer(q, answer, pet);
                        break;
                } catch (Exception e) {
                    System.err.println("Erro: " + e.getMessage());
                }
            }
        }
        return pet;
    }

    private void validateAnswer(String question, String answer, Pet pet) {
        int numQuestion = Integer.parseInt(question.substring(0, 1));
        switch (numQuestion) {
            case 1 -> pet.setPetName(validator.validateName(answer));
            case 2 -> pet.setPetType(validator.validateType(answer));
            case 3 -> pet.setPetSex(validator.validateSex(answer));
            case 5 -> pet.setPetAge(validator.validateAge(answer));
            case 6 -> pet.setPetWeight(validator.validateWeight(answer));
            case 7 -> pet.setPetBreed(validator.validateBreed(answer));
        }
    }
}


