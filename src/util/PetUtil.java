package util;

import model.Pet;
import model.PetAddress;
import model.PetSex;
import model.PetType;

import java.util.List;

public class PetUtil {

    public static PetAddress parseAddress(String value) {
        String[] parts = value.split(",");
        return new PetAddress(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    public static double parseAge(String value) {
        if (value.equalsIgnoreCase("NÃO INFORMADO")) return 0;
        return Double.parseDouble(value.replace("anos", "").trim());
    }

    public static double parseWeight(String value) {
        if (value.equalsIgnoreCase("NÃO INFORMADO")) return 0;
        return Double.parseDouble(value.replace("kg", "").trim());
    }

    public static Pet buildPet(List<String> petData) {
        return new Pet(
                petData.get(0),
                PetType.fromDescription(petData.get(1)),
                PetSex.fromDescription(petData.get(2)),
                parseAddress(petData.get(3)),
                parseAge(petData.get(4)),
                parseWeight(petData.get(5)),
                petData.get(6)
        );
    }

    public static String capitalizeText(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
