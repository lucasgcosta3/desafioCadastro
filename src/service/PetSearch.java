package service;

import model.Pet;
import model.PetType;

import java.util.List;

public class PetSearch {
    public List<Pet> searchPets(List<String> filters, List<Pet> pets) {
        PetType type = PetType.fromDescription(filters.get(0));
        String filter1 = filters.get(1);
        String filter2 = filters.size() > 2 ? filters.get(2) : null;

        return pets.stream()
                .filter(p -> p.getPetType() == type)
                .filter(p -> applyFilter(p, filter1))
                .filter(p -> applyFilter(p, filter2))
                .toList();
    }

    private boolean applyFilter(Pet pet, String filter) {
        if (filter == null) return true;

        String[] parts = filter.split("=", 2);
        if (parts.length < 2) return true;

        String field = parts[0];
        String value = parts[1].trim().toLowerCase();

        String address = pet.getPetAddress().getStreet() + "," +
                pet.getPetAddress().getNumber() + "," +
                pet.getPetAddress().getCity();

        return switch (field) {
            case "name" -> pet.getPetName().toLowerCase().contains(value);
            case "sex" -> pet.getPetSex().getDescription().equalsIgnoreCase(value);
            case "age" -> pet.getPetAge() == Double.parseDouble(value);
            case "weight" -> pet.getPetWeight() == Double.parseDouble(value);
            case "breed" -> pet.getPetBreed().toLowerCase().contains(value);
            case "address" -> address.toLowerCase().contains(value);
            default -> true;
        };
    }
}
