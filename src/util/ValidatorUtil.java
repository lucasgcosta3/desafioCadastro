package util;

import model.PetAddress;
import model.PetSex;
import model.PetType;

import java.util.Scanner;

public class ValidatorUtil {
    public String validateName(String answer) {
        if (answer.isBlank()) return ConstantUtil.NAO_INFORMADO;
        if (answer.matches("^[A-Za-z]+(?: [A-Za-z]+)+$")) {
            return answer;
        }
        throw new IllegalArgumentException("Nome inválido. O pet deve conter nome e sobrenome, use apenas letras.");
    }

    public PetType validateType(String answer) {
        if (answer.equalsIgnoreCase(PetType.DOG.getDescription())) return PetType.DOG;
        if (answer.equalsIgnoreCase(PetType.CAT.getDescription())) return PetType.CAT;
        throw new IllegalArgumentException("Tipo inválido. Digite 'Cachorro' ou 'Gato'");
    }

    public PetSex validateSex(String answer) {
        if (answer.equalsIgnoreCase(PetSex.MALE.getDescription())) return PetSex.MALE;
        if (answer.equalsIgnoreCase(PetSex.FEMALE.getDescription())) return PetSex.FEMALE;
        throw new IllegalArgumentException("Sexo inválido. Digite 'Macho' ou 'Femea'");
    }

    public PetAddress validateAddress(String street, String number, String city) {
        if (!street.matches("^[A-Za-z0-9 ]+$")) {
            throw new IllegalArgumentException("Rua inválida");
        }
        if (number.isBlank()) {
            number = ConstantUtil.NAO_INFORMADO;
        } else if (!number.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("Número inválido");
        }
        if (!city.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
            throw new IllegalArgumentException("Cidade inválida");
        }
        return new PetAddress(street, number, city);
    }

    public PetAddress askAddress(Scanner sc) {
        System.out.print("Rua: ");
        String street = sc.nextLine();
        System.out.print("Número da casa: ");
        String number = sc.nextLine();
        System.out.print("Cidade: ");
        String city = sc.nextLine();

        return validateAddress(street, number, city);
    }

    public double validateAge(String answer) {
        if (answer.isBlank()) return 0; // será salvo no arquivo como NÃO INFORMADO
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

    public double validateWeight(String answer) {
        if (answer.isBlank()) return 0; // será salvo no arquivo como NÃO INFORMADO
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

    public String validateBreed(String answer) {
        if (answer.isBlank()) return ConstantUtil.NAO_INFORMADO;
        if (answer.matches("^[A-Za-z]+( [A-Za-z]{2,})?$")) return answer;
        throw new IllegalArgumentException("Raça inválida. Deve conter apenas letras");
    }
}
