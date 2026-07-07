package Assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReader {
    private String randomfile = "src/random.txt";
    private String semifile = "src/semi_ordered.txt";
    private final int DATA_SIZE = 1000000;
    private final String outputFolder = "outputs";

    public FileReader() {
        createOutputFolder();
    }

    private void createOutputFolder() {
        File directory = new File(outputFolder);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public int[] readRandomFile() {
        return readArrayFromFile(this.randomfile);
    }

    public int[] readSemiOrderedFile() {
        return readArrayFromFile(this.semifile);
    }

    private int[] readArrayFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Hata: Dosya bulunamadı - " + filename);
            return null;
        }

        int[] arr = new int[DATA_SIZE];

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            int i = 0;

            while ((line = br.readLine()) != null && i < DATA_SIZE) {
                arr[i++] = Integer.parseInt(line.trim());
            }

            if (i < DATA_SIZE) {
                int[] resized = new int[i];
                System.arraycopy(arr, 0, resized, 0, i);
                return resized;
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Hata: Dosya okunurken sorun oluştu - " + filename);
            return null;
        }

        return arr;
    }

    public void writeArrayToFile(int[] arr, String filename) {
        String fullPath = outputFolder + File.separator + filename;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fullPath))) {
            for (int num : arr) {
                bw.write(Integer.toString(num));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Hata: Dosyaya yazılamadı - " + fullPath);
        }
    }

    public int[] generateIncreasingArray() {
        int[] arr = new int[DATA_SIZE];
        for (int i = 0; i < DATA_SIZE; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public int[] generateDecreasingArray() {
        int[] arr = new int[DATA_SIZE];
        for (int i = 0; i < DATA_SIZE; i++) {
            arr[i] = DATA_SIZE - i;
        }
        return arr;
    }
}