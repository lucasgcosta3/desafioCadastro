package service;

import model.Pet;
import model.PetAddress;
import util.ConstantUtil;
import util.PetUtil;
import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public void savePetFile(Pet pet) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyddMM'T'HHmm");
        String archiveName = now.format(fmt) + "-" + pet.getPetName()
                .replace(" ", "")
                .toUpperCase() + ".txt";

        File folder = new File("petsCadastrados");
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
        File folder = new File("petsCadastrados");

        for (File archive : folder.listFiles()) {
            try(BufferedReader br = new BufferedReader(new FileReader(archive))) {
                List<String> petData = new ArrayList<>();
                String line;

                while((line = br.readLine()) != null) {
                    String value = line.split("-")[1].trim();
                    petData.add(value);
                }

                Pet pet = PetUtil.buildPet(petData);
                pets.add(pet);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return pets;
    }
}
