package model;

public enum PetType {
    DOG("Cachorro"),
    CAT("Gato");

    private final String description;

    PetType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static PetType fromDescription(String desc) {
        for (PetType type : PetType.values()) {
            if (type.getDescription().equalsIgnoreCase(desc.trim())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + desc);
    }

}
