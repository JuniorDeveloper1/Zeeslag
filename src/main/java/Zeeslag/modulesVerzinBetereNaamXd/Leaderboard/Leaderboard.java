package Zeeslag.modulesVerzinBetereNaamXd.Leaderboard;

import Zeeslag.modulesVerzinBetereNaamXd.Player.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Leaderboard {
    private List<Player> players;
    private final String filePath = "Zeeslag\resources\leaderboard\leaderboard.txt";
    public Leaderboard() {
        this.players = new ArrayList<>();

    }

    public void loadLeaderBoardFromFile() throws IOException {
        File file = new File(getFilePath());
        if(!file.exists()) {
            throw  new IOException("File doesn't exist!");
        }

        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if(parts.length == 2) {
                //Player == parts[0]
                //Wins == [1]
                //players.add(players....)
            }
        }
        scanner.close();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getFilePath() {
        return filePath;
    }
}
