package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Tree {
    private final List<Player> players = new ArrayList<>();

    public Tree() {
        initialize();
    }

    private void initialize() {
        try {
            List<String> lines = Files.readAllLines(Path.of("input.txt"));
            int numberOfTeams = Integer.parseInt(lines.get(0));
            for (int i = 1; i <= numberOfTeams; i++) {
                String[] split = lines.get(i).split(" ");
                List<Player> team = new ArrayList<>();
                for (String name : split) {
                    Player player = getPlayer(name);
                    if (!players.contains(player)) {
                        players.add(player);
                    }
                    team.add(player);
                }
                setPlayedTogether(team);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElse(new Player(name));
    }

    public void setPlayedTogether(List<Player> team) {
        for (int i = 0; i < team.size() - 1; i++) {
            for (int j = i + 1; j < team.size(); j++) {
                team.get(i).setPlayedWith(team.get(j));
            }
        }
    }

    /**
     * Breadth-first-search algorithm helps us determine if one player is related to another.
     * If so, we get the number of handshakes from one player to another.
     * @param s - start node;
     * @param e - end node;
     * @return length of nodes between s and e and list of these nodes.
     */

    public String bfs(Player s, Player e) { //s - start node, e - end node
        Player[] prev = solve(s);
        return reconstruct(s, e, prev);
    }

    /**
     * First part of the Bfs algorithm where we get an array of connections between players,
     * so that index of the current player leads to the previous player it played with.
     * Example: prev[index of the current player] = previous player.
     * @param s - start node (player).
     * @return 'prev' (previous) array - array of players where prev[current player index] = previous player.
     */

    public Player[] solve(Player s) {
        Queue<Player> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[players.size()];

        queue.add(s);
        visited[players.indexOf(s)] = true;

        Player[] prev = new Player[players.size()];
        while (!queue.isEmpty()) {
            Player player = queue.poll();
            Set<Player> mates = player.getTeammates();
            for (Player next : mates) {
                if (!visited[players.indexOf(next)]) {
                    queue.add(next);
                    visited[players.indexOf(next)] = true;
                    prev[players.indexOf(next)] = player;
                }
            }
        }
        return prev;
    }

    /**
     * Second part of the Bfs algorithm where we reconstruct the path from 'e' (end) player to 's' (start) player.
     * If relation exists, then we return number of handshakes between two players,
     * 0 handshakes mean 's' and 'e' is the same player,
     * 1 handshake means 's' player has played with the 'e' player in the same team.
     * 2 handshakes mean 's' player has played with the player who played with the 'e' player in the same team, etc.
     * "undefined" means there's no relation between two players was found.
     * @param s 'start' player.
     * @param e 'end' player.
     * @param prev ('previous') array of players where prev[current player index] = previous player.
     * @return number of handshakes between 's' player and 'e' player and the path between them if relation exists.
     */

    public String reconstruct(Player s, Player e, Player[] prev) {
        if (s.equals(e)) return "0";

        List<Player> teammates = new ArrayList<>();
        teammates.add(e);
        for (Player player = prev[players.indexOf(e)]; player != null; player = prev[players.indexOf(player)]) {
            teammates.add(0, player);
        }
        if (teammates.get(0).equals(s)){
            return String.format("%s [%s]", teammates.size()-1, teammates.stream().map(Player::getName).collect(Collectors.joining("-")));
        }
        return "undefined";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        players.stream()
                .sorted(Comparator.comparing(Player::getName))
                .forEach(
                        player -> sb.append(player)
                                .append(" ")
                                .append(player.getNumberOfHandshakes())
                                .append("\n"));
        return sb.toString();
    }

    /**
     * prints path from each player to (e)nd player.
     * @param e - end player.
     */

    public void printBfs(Player e){
        StringBuilder sb = new StringBuilder();
        players.stream()
                .sorted(Comparator.comparing(Player::getName))
                .forEach(player -> sb.append(player.getName())
                        .append(" = ")
                        .append(bfs(player, e))
                        .append("\n"));
        System.out.println(sb);
    }
}















