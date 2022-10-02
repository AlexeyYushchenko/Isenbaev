package program;

import model.Player;
import model.Tree;

public class Program {
    public static void main(String[] args) {
        Tree tree = new Tree("input.txt");
        System.out.println("SIMPLE OUTPUT:");
        System.out.println(tree);
        System.out.println("USING THE BFS ALGORITHM AND DISPLAYING THE PATH FROM ONE PLAYER TO ANOTHER.");
        System.out.println();
        tree.printBfs(new Player("Isenbaev"));
    }
}
