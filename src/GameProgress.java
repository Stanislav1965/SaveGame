import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }


    public void saveGame(String dirSave, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(dirSave); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // запишем экземпляр класса в файл
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void zipFiles(String dirZip, List<String> fileName) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dirZip))) {

            for (String flt : fileName) {
                try (FileInputStream fis = new FileInputStream(flt)) {

                    ZipEntry entry = new ZipEntry(flt);
                    zout.putNextEntry(entry);

                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception e) {
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

    @Override
    public String toString() {
        return "GameProgress{" + "health=" + health + ", weapons=" + weapons + ", lvl=" + lvl + ", distance=" + distance + '}';
    }
}

