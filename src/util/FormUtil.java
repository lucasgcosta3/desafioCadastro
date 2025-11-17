package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FormUtil {
    public static void createForm() {
        File file = new File("formulario.txt");
        if (file.exists()) return;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("1 - Qual o nome e sobrenome do pet?");
            bw.newLine();
            bw.write("2 - Qual o tipo do pet (Cachorro/Gato)?");
            bw.newLine();
            bw.write("3 - Qual o sexo do animal?");
            bw.newLine();
            bw.write("4 - Qual endereço e bairro que ele foi encontrado?");
            bw.newLine();
            bw.write("5 - Qual a idade aproximada do pet?");
            bw.newLine();
            bw.write("6 - Qual o peso aproximado do pet?");
            bw.newLine();
            bw.write("7 - Qual a raça do pet?");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar arquivo: " + e.getMessage());
        }
    }

    public static List<String> readForm() {
        List<String> perguntas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("formulario.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                perguntas.add(linha);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler arquivo: " + e.getMessage());
        }
        return perguntas;
    }
}
