package control;
import java.util.*;

public class ShortestPath {

    private static final int[][] DIRECTIONS = {
            {0, 1},  // Droite
            {1, 0},  // Bas
            {0, -1}, // Gauche
            {-1, 0}  // Haut
    };

    public static List<int[]> findShortestPath(int[][] matrix, int startX, int startY, int endX, int endY) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startX, startY, null));

        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Si nous atteignons la destination
            if (current.x == endX && current.y == endY) {
                return reconstructPath(current);
            }

            // Explore les directions possibles
            for (int[] direction : DIRECTIONS) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];

                if (isValid(matrix, newX, newY, visited)) {
                    visited[newX][newY] = true;
                    queue.offer(new Node(newX, newY, current));
                }
            }
        }

        // Aucun chemin trouvé
        return null;
    }

    private static boolean isValid(int[][] matrix, int x, int y, boolean[][] visited) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        return x >= 0 && x < rows && y >= 0 && y < cols && matrix[x][y] == 1 && !visited[x][y];
    }

    private static List<int[]> reconstructPath(Node node) {
        List<int[]> path = new LinkedList<>();
        while (node != null) {
            path.add(0, new int[]{node.x, node.y}); // Ajoute au début de la liste
            node = node.parent;
        }
        return path;
    }

    // Classe pour représenter un noeud dans le BFS
    static class Node {
        int x, y;
        Node parent;

        Node(int x, int y, Node parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }
    }


}
