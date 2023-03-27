import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class makeDirsAndFiles {
    public static StringBuilder SB = new StringBuilder();
    public static String[] DIRECTORIES = {"C:/Games/src/main", "C:/Games/src/test",
            "C:/Games/res/drawables", "C:/Games/res/vectors",
            "C:/Games/res/icons", "C:/Games/savegames",
            "C:/Games/temp"};
    public static String[] FILES = {"C:/Games/src/main/Main.java",
            "C:/Games/src/main/Utils.java", "C:/Games/temp/temp.txt"};

    public static File makeDir(String nameDir) {
        File dir = new File(nameDir);
        dir.mkdirs();
        SB.append("Каталог " + dir + " успешно создан.\n");
        return dir;
    }

    public static File makeFile(String nameFile) {
        File file = new File(nameFile);
        try {
            file.createNewFile();
            SB.append("Файл " + file + " успешно создан.\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public static void generateLog(StringBuilder sb) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Games/temp/temp.txt"))) {
            bw.write(SB.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
