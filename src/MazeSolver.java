/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.*;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        MazeCell currentCell = maze.getEndCell();
        MazeCell firstCell = maze.getStartCell();
        Stack<MazeCell> reverseOrder = new Stack<MazeCell>();
        while (!currentCell.equals(firstCell)) {
            reverseOrder.push(currentCell);
            currentCell = currentCell.getParent();
        }
        ArrayList<MazeCell> order = new ArrayList<MazeCell>();
        while (!reverseOrder.empty()) {
            order.add(reverseOrder.pop());
        }
        // Should be from start to end cells
        return order;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell currentCell = maze.getStartCell();
        Stack<MazeCell> explore = new Stack<MazeCell>();
        MazeCell endCell = maze.getEndCell();
        while (!currentCell.equals(endCell)) {
            int currentRow = currentCell.getRow();
            int currentCol = currentCell.getCol();
            if (maze.isValidCell(currentRow - 1, currentCol)) {
                explore.add(maze.getCell(currentRow - 1, currentCol));
                maze.getCell(currentRow - 1, currentCol).setExplored(true);
                maze.getCell(currentRow - 1, currentCol).setParent(currentCell);
            }
            if (maze.isValidCell(currentRow, currentCol + 1)) {
                explore.add(maze.getCell(currentRow, currentCol + 1));
                maze.getCell(currentRow, currentCol + 1).setExplored(true);
                maze.getCell(currentRow, currentCol + 1).setParent(currentCell);
            }
            if (maze.isValidCell(currentRow + 1, currentCol)) {
                explore.add(maze.getCell(currentRow + 1, currentCol));
                maze.getCell(currentRow + 1, currentCol).setExplored(true);
                maze.getCell(currentRow + 1, currentCol).setParent(currentCell);
            }
            if (maze.isValidCell(currentRow, currentCol - 1)) {
                explore.add(maze.getCell(currentRow, currentCol - 1));
                maze.getCell(currentRow, currentCol - 1).setExplored(true);
                maze.getCell(currentRow, currentCol - 1).setParent(currentCell);
            }
            currentCell = explore.pop();
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell currentCell = maze.getStartCell();
        Queue<MazeCell> explore = new LinkedList<MazeCell>();
        MazeCell endCell = maze.getEndCell();
        while (!currentCell.equals(endCell)) {
            int currentRow = currentCell.getRow();
            int currentCol = currentCell.getCol();
            if (maze.isValidCell(currentRow - 1, currentCol)) {
                explore.add(maze.getCell(currentRow - 1, currentCol));
                maze.getCell(currentRow - 1, currentCol).setExplored(true);
                maze.getCell(currentRow - 1, currentCol).setParent(currentCell);
            }
            if (maze.isValidCell(currentRow, currentCol + 1)) {
                explore.add(maze.getCell(currentRow, currentCol + 1));
                maze.getCell(currentRow, currentCol + 1).setExplored(true);
                maze.getCell(currentRow, currentCol + 1).setParent(currentCell);
            }
            if (maze.isValidCell(currentRow + 1, currentCol)) {
                explore.add(maze.getCell(currentRow + 1, currentCol));
                maze.getCell(currentRow + 1, currentCol).setExplored(true);
                maze.getCell(currentRow + 1, currentCol).setParent(currentCell);
            }
            if (maze.isValidCell(currentRow, currentCol - 1)) {
                explore.add(maze.getCell(currentRow, currentCol - 1));
                maze.getCell(currentRow, currentCol - 1).setExplored(true);
                maze.getCell(currentRow, currentCol - 1).setParent(currentCell);
            }
            currentCell = explore.remove();
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze2.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        maze.printSolution(sol);
    }
}
