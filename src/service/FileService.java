package service;

import model.Pet;
import model.PetAddress;
import util.ConstantUtil;
import util.PetUtil;

import java.io.*;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    File folder = new File("petsCadastrados");

    public void savePetFile(Pet pet) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyddMM'T'HHmm");
        String archiveName = now.format(fmt) + "-" + pet.getPetName()
                .replace(" ", "")
                .toUpperCase() + ".txt";

        if (!folder.exists()) {
            folder.mkdir();
        }
        File archive = new File(folder, archiveName);

        PetAddress address = pet.getPetAddress();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        String age = pet.getPetAge() == 0 ? ConstantUtil.NAO_INFORMADO : df.format(pet.getPetAge()) + " anos";
        String weight = pet.getPetWeight() == 0 ? ConstantUtil.NAO_INFORMADO : df.format(pet.getPetWeight()) + "kg";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archive))) {
            bw.write("1 - " + PetUtil.capitalizeText(pet.getPetName()));
            bw.newLine();
            bw.write("2 - " + pet.getPetType().getDescription());
            bw.newLine();
            bw.write("3 - " + pet.getPetSex().getDescription());
            bw.newLine();
            bw.write("4 - " + PetUtil.capitalizeText(address.getStreet()) + ", " + address.getNumber() + ", " + PetUtil.capitalizeText(address.getCity()));
            bw.newLine();
            bw.write("5 - " + age);
            bw.newLine();
            bw.write("6 - " + weight);
            bw.newLine();
            bw.write("7 - " + PetUtil.capitalizeText(pet.getPetBreed()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pet> readPetFile() {
        List<Pet> pets = new ArrayList<>();

        for (File archive : folder.listFiles()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archive))) {
                List<String> petData = new ArrayList<>();
                String line;

                while ((line = br.readLine()) != null) {
                    String value = line.split("-")[1].trim();
                    petData.add(value);
                }

                Pet pet = PetUtil.buildPet(petData);
                pets.add(pet);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao ler arquivo " + archive.getName() + ":" + e.getMessage());
            }
        }
        return pets;
    }

    public void updatePetFile(List<Pet> pets) {
        Pet oldPet = pets.getFirst();
        Pet updatedPet = pets.get(1);
        String oldPetName = oldPet.getPetName().replace(" ", "").toUpperCase();
        String oldPetAddress = oldPet.getPetAddress().getNumber().toUpperCase();

        for (File archive : folder.listFiles()) {
            String archiveName = archive.getName().replace("-", ".").split("\\.")[1];

            if (archiveName.equals(oldPetName)) {
                try {
                    List<String> linhas = Files.readAllLines(archive.toPath());
                    if (linhas.size() >= 4) {
                        String linhaUpper = linhas.get(3).toUpperCase();
                        if (linhaUpper.contains(oldPetAddress)) {
                            Files.delete(archive.toPath());
                            savePetFile(updatedPet);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Erro ao atualizar: " + e.getMessage());
                }
            }
        }
    }

    public void deletePetFile(Pet pet) {
        if (pet == null) return;
        String petName = pet.getPetName().replace(" ", "").toUpperCase();
        String petAddress = pet.getPetAddress().getNumber().toUpperCase();

        for (File archive : folder.listFiles()) {
            String archiveName = archive.getName().replace("-", ".").split("\\.")[1];

            if (archiveName.equals(petName)) {
                try {
                    List<String> linhas = Files.readAllLines(archive.toPath());
                    if (linhas.size() >= 4) {
                        String linhaUpper = linhas.get(3).toUpperCase();
                        if (linhaUpper.contains(petAddress)) {
                            Files.delete(archive.toPath());
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Erro ao deletar: " + e.getMessage());
                }
            }
        }
    }
}
