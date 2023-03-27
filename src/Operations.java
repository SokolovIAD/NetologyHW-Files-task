import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Operations {
    public static StringBuilder SB = new StringBuilder();
    public static String[] DIRECTORIES = {"C:/Games/src/main", "C:/Games/src/test",
            "C:/Games/res/drawables", "C:/Games/res/vectors",
            "C:/Games/res/icons", "C:/Games/savegames",
            "C:/Games/temp"};
    public static String[] FILES = {"C:/Games/src/main/Main.java",
            "C:/Games/src/main/Utils.java", "C:/Games/temp/temp.txt"};
    public static List<String> SAVESLIST = new ArrayList<>();

    public static void makeDir(String nameDir) {
        File dir = new File(nameDir);
        dir.mkdirs();
        SB.append("Каталог " + dir + " успешно создан.\n");
    }

    public static void makeFile(String nameFile) {
        File file = new File(nameFile);
        try {
            file.createNewFile();
            SB.append("Файл " + file + " успешно создан.\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateLog(StringBuilder sb) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Games/temp/temp.txt"))) {
            bw.write(SB.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveGame(GameProgress gameProgress, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(path))) {
            oos.writeObject(gameProgress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        SAVESLIST.add(path);
    }

    public static void zipFiles(String dirToSave, List<String> filesToZip) {
        {
            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dirToSave))) {
                for (int i = 0; i < filesToZip.size(); i++) {
                    FileInputStream fis = new FileInputStream(filesToZip.get(i));
                    ZipEntry entry = new ZipEntry("savegame" + (i + 1) + ".dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    fis.close();
                    zout.closeEntry();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deleteFiles(List<String> filesToDelite) {
        for (String save : filesToDelite) {
            File file = new File(save);
            if (file.delete()) {
                System.out.println("Файл " + save + " удален успешно.");
            }
        }
    }

    public static void openZip(String zip, String dirToSave) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zip))) {
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                try (FileOutputStream fos = new FileOutputStream(dirToSave + "/" + name)) {
                    while (zis.read() != -1){
                        fos.write(zis.read());
                    }
                    fos.flush();
                    zis.closeEntry();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static GameProgress openProgress(List<String> filesToPrint) {
        for (String file : filesToPrint) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (GameProgress) ois.readObject();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
