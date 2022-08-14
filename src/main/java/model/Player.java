package model;

import java.util.*;

public class Player {
    private final String name;
    private final Set<Player> teammates = new HashSet<>();
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPlayedWith(Player player){
            this.teammates.add(player);
            player.teammates.add(this);
    }

    public Set<Player> getTeammates() {
        return teammates;
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

    public String getNumberOfHandshakes(){
        int layer = 0;
        int nodesToNextLayer = 1;
        int nextLayerNodes = 0;
        List<Player> visited = new ArrayList<>();
        Queue<Player> queue = new ArrayDeque<>();

        queue.add(this);
        visited.add(this);
        while (!queue.isEmpty()) {
            if (nodesToNextLayer == 0) {
                layer++;
                nodesToNextLayer = nextLayerNodes;
                nextLayerNodes = 0;
            }

            Player next = queue.poll();
            if (next.getName().equalsIgnoreCase("Isenbaev")) return String.valueOf(layer);

            for (Player teammate : next.teammates){
                if (!visited.contains(teammate)){
                    queue.add(teammate);
                    visited.add(teammate);
                    nextLayerNodes++;
                }
            }
            nodesToNextLayer--;
        }
        return "undefined";
    }
}
