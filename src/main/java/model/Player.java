package model;

import java.util.*;

public class Player {
    private final String name;
    private List<Player> playedWith = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean setPlayedWith(Player player){
        if (!this.playedWith.contains(player)){
            this.playedWith.add(player);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getNumber(){
        List<Player> visited = new ArrayList<>();
        int counter1 = 1;
        int layer = 0;
        int counter2 = 0;
        Queue<Player> queue = new ArrayDeque<>();
        queue.add(this);
        visited.add(this);
        while (!queue.isEmpty()) {
            if (counter1 == 0) {
                layer++;
                counter1 = counter2;
                counter2 = 0;
            }
            Player next = queue.poll();
            counter1--;
            if (next.getName().equalsIgnoreCase("Isenbaev")) return String.valueOf(layer);
            for (Player teammate : next.playedWith){
                if (!visited.contains(teammate)){
                    queue.add(teammate);
                    visited.add(teammate);
                    counter2++;
                }
            }
        }
        return "undefined";
    }
}
