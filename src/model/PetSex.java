package model;

public enum PetSex {
    MALE("Macho"),
    FEMALE("Femea");

    private final String description;

    PetSex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static PetSex fromDescription(String desc) {
        for (PetSex sex : PetSex.values()) {
            if (sex.getDescription().equalsIgnoreCase(desc.trim())) {
                return sex;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + desc);
    }
}
