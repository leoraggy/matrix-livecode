import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SalamanderSearch {
    public static void main(String[] args) {
        char[][] enclosure1 = {
            {'.','.','.','.','.','.'},
            {'W','.','W','W','.','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','s','.','.'},
        };

        char[][] enclosure2 = {
            {'.','.','.','.','.','.'},
            {'W','W','W','W','s','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','.','.','.'},
        };
    }

    /**
     * Returns whether a salamander can reach the food in an enclosure.
     * 
     * The enclosure is represented by a rectangular char[][] that contains
     * ONLY the following characters:
     * 
     * 's': represents the starting location of the salamander
     * 'f': represents the location of the food
     * 'W': represents a wall
     * '.': represents an empty space the salamander can walk through
     * 
     * The salamander can move one square at a time: up, down, left, or right.
     * It CANNOT move diagonally.
     * It CANNOT move off the edge of the enclosure.
     * It CANNOT move onto a wall.
     * 
     * This method should return true if there is any sequence of steps that
     * the salamander could take to reach food.
     * 
     * @param enclosure
     * @return whether the salamander can reach the food
     * @throws IllegalArgumentException if the enclosure does not contain a salamander
     */
    public static boolean canReach(char[][] enclosure) {
        int[] startLocation = salamanderLocation(enclosure);
        boolean[][] visited = new boolean[enclosure.length][enclosure[0].length];
        return canReach(startLocation, enclosure, visited);
    }

    private static boolean canReach(int[] currentLoc, char[][] enclosure, boolean[][] visited){
        int row = currentLoc[0];
        int col = currentLoc[1];

        if(enclosure[row][col] == 'f'){
            return true;
        }

        if(visited[row][col]){
            return false;
        }

        visited[row][col] = true;

        for(int[] move : possibleMoves(enclosure, currentLoc)){
            if(canReach(move, enclosure, visited)){
                return true;
            }
        }

        return false;
    }

    public static List<int[]> possibleMoves(char[][] enclosure, int[] location) {
        int curR = location[0];
        int curC = location[1];

        List<int[]> validLocs = new ArrayList<>();


        // UP
        int newR = curR - 1;     
        int newC = curC;  

        if(newR >= 0 && enclosure[newR][newC] != 'W'){
            validLocs.add( new int[]{newR, newC});
        }

        // DOWN
        newR = curR + 1;     
        newC = curC;
        if(newR < enclosure.length && enclosure[newR][newC] != 'W'){
            validLocs.add( new int[]{newR, newC});
        }

        // LEFT
        newR = curR;     
        newC = curC - 1;
        if(newC >= 0 && enclosure[newR][newC] != 'W'){
            validLocs.add( new int[]{newR, newC});
        }   

        // RIGHT
        newR = curR;     
        newC = curC + 1;
        if(newC < enclosure[0].length && enclosure[newR][newC] != 'W'){
            validLocs.add( new int[]{newR, newC});
        }

        return validLocs;
    }

    /*
    return the [row, col] location of the salamader.
    throw IllegalArgumentException if there is no salamander.
     */

    public static int[] salamanderLocation(char[][] enclosure){
        for(int row = 0; row < enclosure.length; row++){
            for(int col = 0; col < enclosure[0].length; col++){
                if(enclosure[row][col] == 's'){
                    return new int[]{row, col};
                }
            }
        }
        throw new IllegalArgumentException("No salamander present");
    }
}
