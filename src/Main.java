public class Main extends Operations {
    public static void main(String[] args) {
        for (String directory : DIRECTORIES) {
            makeDir(directory);
        }
        for (String file : FILES) {
            makeFile(file);
        }
        generateLog(SB);
        saveGame(new GameProgress(100,2,5,2.0), "C:/Games/savegames/savegame1.dat");
        saveGame(new GameProgress(190,7,12,14.0), "C:/Games/savegames/savegame2.dat");
        saveGame(new GameProgress(666,33,123,222.0), "C:/Games/savegames/savegame3.dat");
        zipFiles("C:/Games/savegames/zip.zip", SAVESLIST);
        deleteFiles(SAVESLIST);
        openZip("C:/Games/savegames/zip.zip", "C:/Games/savegames");
        openProgress(SAVESLIST);
    }
}