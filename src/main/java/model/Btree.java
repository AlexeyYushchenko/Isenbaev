package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//It took 2.40 to finish this task.

public class Btree {
    List<Player> players = new ArrayList<>();

    public Btree(){
        initialize();
    }

    private void initialize() {
        try{
            List<String> lines = Files.readAllLines(Path.of("input.txt"));
            for(String line : lines){
                String[] split = line.split(" ");
                List<Player> teammates = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    Player player = findPlayerByName(split[i]);
                    if (player == null){
                        Player newPlayer = new Player(split[i]);
                        teammates.add(newPlayer);
                    }else {
                        teammates.add(player);
                    }
                }

                for (int i = 0; i < teammates.size(); i++) {
                    for (int j = 0; j < teammates.size(); j++) {
                        if (!teammates.get(i).equals(teammates.get(j))){
                            setPlayedTogether(teammates.get(i), teammates.get(j));
                        }
                    }
                }

                for (Player player : teammates){
                    if (!players.contains(player)){
                        players.add(player);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player findPlayerByName(String name){
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findAny()
                .orElse(new Player(name));
    }

    public void setPlayedTogether(Player p1, Player p2){
        if (!p1.equals(p2)) {
            p1.setPlayedWith(p2);
            p2.setPlayedWith(p1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        players.stream()
                .sorted(Comparator.comparing(Player::getName))
                .forEach(
                        player -> sb.append(player)
                                .append(" ")
                                .append(player.getNumber())
                                .append("\n"));
        return sb.toString();
    }
}















