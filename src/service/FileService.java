package service;

import model.Pet;
import model.PetAddress;
import util.ConstantUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DecimalFormat df = new DecimalFormat("#.##");
        String age = pet.getPetAge() == 0 ? ConstantUtil.NAO_INFORMADO : df.format(pet.getPetAge());
        String weight = pet.getPetWeight() == 0 ? ConstantUtil.NAO_INFORMADO : df.format(pet.getPetWeight());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archive))) {
            bw.write("1 - " + pet.getPetName());
            bw.newLine();
            bw.write("2 - " + pet.getPetType().getDescription());
            bw.newLine();
            bw.write("3 - " + pet.getPetSex().getDescription());
            bw.newLine();
            bw.write("4 - " + address.getStreet() + ", " + address.getNumber() + ", " + address.getCity());
            bw.newLine();
            bw.write("5 - " + age + " anos");
            bw.newLine();
            bw.write("6 - " + weight + "kg");
            bw.newLine();
            bw.write("7 - " + pet.getPetBreed());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
