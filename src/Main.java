import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        List<String> fileName = Arrays.asList("C:\\Games\\savegames\\data1.dat"
                , "C:\\Games\\savegames\\data2.dat"
                , "C:\\Games\\savegames\\data3.dat");

        saveGame(fileName.get(0), new GameProgress(10, 2, 2, 2.5));
        saveGame(fileName.get(1), new GameProgress(40, 4, 3, 3.8));
        saveGame(fileName.get(2), new GameProgress(60, 5, 4, 4.2));

        zipFiles("C:\\Games\\savegames\\zip.zip", fileName);
    }

    public static void saveGame(String dirSave, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(dirSave); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // запишем экземпляр класса в файл
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String dirZip, List<String> fileName) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dirZip))) {

            for (String flt : fileName) {
                int lastPath = flt.lastIndexOf(File.separator);
                String name = flt.substring(lastPath + 1);

                try (FileInputStream fis = new FileInputStream(flt)) {

                    ZipEntry entry = new ZipEntry(name);
                    zout.putNextEntry(entry);

                    byte[] buffer = new byte[fis.available()];

                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        for (String flt : fileName) {
            File deleteFile = new File(flt);
            if (deleteFile.exists() || deleteFile.isFile()) {
                deleteFile.delete();
            }
        }
    }
}