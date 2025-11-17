package service;

import model.Pet;
import model.PetAddress;
import model.PetSex;
import model.PetType;

import java.util.List;
import java.util.Scanner;

import static util.FormUtil.readForm;

public class PetService {
    public Pet registerPet(Scanner sc) {
        Pet pet = new Pet();
        List<String> questions = readForm();

        for (String q : questions) {
            while (true) {
                System.out.println(q);
                try {
                    if (q.contains("endereço")) { // evitar ENTER desnecessário ao mostrar pergunta 4
                        pet.setPetAddress(askAddress(sc));
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
            case 1 -> pet.setPetName(validateName(answer));
            case 2 -> pet.setPetType(validateType(answer));
            case 3 -> pet.setPetSex(validateSex(answer));
            case 5 -> pet.setPetAge(validateAge(answer));
            case 6 -> pet.setPetWeight(validateWeight(answer));
            case 7 -> pet.setPetBreed(validateBreed(answer));
        }
    }

    private String validateName(String answer) {
        if (answer.matches("^[A-Za-z]+ [A-Za-z]{2,}$")) {
            return answer;
        }
        throw new IllegalArgumentException("Nome inválido. O pet deve conter nome e sobrenome, use apenas letras.");
    }

    private PetType validateType(String answer) {
        if (answer.equalsIgnoreCase(PetType.DOG.getDescription())) return PetType.DOG;
        if (answer.equalsIgnoreCase(PetType.CAT.getDescription())) return PetType.CAT;
        throw new IllegalArgumentException("Tipo inválido. Digite 'Cachorro' ou 'Gato'");
    }

    private PetSex validateSex(String answer) {
        if (answer.equalsIgnoreCase(PetSex.MALE.getDescription())) return PetSex.MALE;
        if (answer.equalsIgnoreCase(PetSex.FEMALE.getDescription())) return PetSex.FEMALE;
        throw new IllegalArgumentException("Sexo inválido. Digite 'Macho' ou 'Femea'");
    }

    private PetAddress validateAddress(String street, String number, String city) {
        if (!street.matches("^[A-Za-z0-9 ]+$")) {
            throw new IllegalArgumentException("Rua inválida");
        }
        if (!number.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("Número inválido");
        }
        if (!city.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
            throw new IllegalArgumentException("Cidade inválida");
        }
        return new PetAddress(street, number, city);
    }

    private PetAddress askAddress(Scanner sc) {
        System.out.print("Rua: ");
        String street = sc.nextLine();
        System.out.print("Número da casa: ");
        String number = sc.nextLine();
        System.out.print("Cidade: ");
        String city = sc.nextLine();

        return validateAddress(street, number, city);
    }

    private double validateAge(String answer) {
        String ageFmt = answer.replace(",", ".");
        try {
            double age = Double.parseDouble(ageFmt);
            if (age < 0 || age > 20) {
                throw new IllegalArgumentException("Idade inválida. Deve estar entre 0.1 e 20");
            }
            return age;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private double validateWeight(String answer) {
        String weightFmt = answer.replace(",", ".");
        try {
            double weight = Double.parseDouble(weightFmt);
            if (weight > 60 || weight < 0.5) {
                throw new IllegalArgumentException("Peso inválido. Deve estar entre 0.5kg e 60kg");
            }
            return weight;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private String validateBreed(String answer) {
        if (answer.matches("^[A-Za-z]+( [A-Za-z]{2,})?$")) return answer;
        throw new IllegalArgumentException("Raça inválida. Deve conter apenas letras");
    }
}


