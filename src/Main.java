import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(10, 2, 2, 2.5);
        GameProgress gameProgress2 = new GameProgress(40, 4, 3, 3.8);
        GameProgress gameProgress3 = new GameProgress(60, 5, 4, 4.2);

        List<String> fileName = Arrays.asList ("C:\\Games\\savegames\\data1.dat","C:\\Games\\savegames\\data2.dat", "C:\\Games\\savegames\\data3.dat");

        gameProgress1.saveGame(fileName.get(0),gameProgress1);
        gameProgress2.saveGame(fileName.get(1),gameProgress2);
        gameProgress3.saveGame(fileName.get(2),gameProgress3);

        gameProgress1.zipFiles("C:\\Games\\savegames\\zip.zip", fileName);

    }
}