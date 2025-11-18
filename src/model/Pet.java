package model;

import java.text.DecimalFormat;

public class Pet {
    private String petName;
    private PetType petType;
    private PetSex petSex;
    private PetAddress petAddress;
    private double petAge;
    private double petWeight;
    private String petBreed;

    public Pet() {}

    public Pet(String petName, PetType petType, PetSex petSex, PetAddress petAddress, double petAge, double petWeight, String petBreed) {
        this.petName = petName;
        this.petType = petType;
        this.petSex = petSex;
        this.petAddress = petAddress;
        this.petAge = petAge;
        this.petWeight = petWeight;
        this.petBreed = petBreed;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public PetSex getPetSex() {
        return petSex;
    }

    public void setPetSex(PetSex petSex) {
        this.petSex = petSex;
    }

    public PetAddress getPetAddress() {
        return petAddress;
    }

    public void setPetAddress(PetAddress petAddress) {
        this.petAddress = petAddress;
    }

    public double getPetAge() {
        return petAge;
    }

    public void setPetAge(double petAge) {
        this.petAge = petAge;
    }

    public double getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(double petWeight) {
        this.petWeight = petWeight;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.format("%s - %s - %s - %s - %s anos - %skg - %s",
                petName,
                petType.getDescription(),
                petSex.getDescription(),
                petAddress,
                df.format(petAge),
                df.format(petWeight),
                petBreed
        );
    }
}
