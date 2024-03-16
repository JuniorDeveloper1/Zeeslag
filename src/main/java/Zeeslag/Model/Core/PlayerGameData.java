package Zeeslag.Model.Core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class PlayerGameData {
    /**
     * Saving a player in the playergamedata.txt.
     * With the format name;turns;date
     * turns == attacks
     * @param playerName
     * @param numberOfTurns
     */
    public static void save(String playerName, int numberOfTurns){
        String currentDate = String.valueOf(LocalDate.now());

        String format = playerName + ";"+numberOfTurns+";"+currentDate;

        Path path = Paths.get("resources/gamedata/playergamedata.txt");

        try {
            if(!Files.exists(path)) {
                Files.createFile(path);
            }

            List<String> lines = Files.readAllLines(path);

            lines.add(format);

                Files.write(path, lines);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
