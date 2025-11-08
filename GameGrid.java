// GameGrid.java
//
// This class will outline the grid for the game Snake
//
// 0 means the space is empty, 1 means the space is part of the snake,
// and 2 means the space is the food for the snake

import java.util.Random;

public class GameGrid {
    private int[][] grid;
    private Snake snake;
    private Random gen;
    private static final int GRID_SIZE = 20;

    public GameGrid() {
        snake = new Snake();
        gen = new Random();
        grid = new int[GRID_SIZE][GRID_SIZE];
        initializeGrid();
    }

    private void initializeGrid() {
        int foodGen = gen.nextInt(GRID_SIZE * GRID_SIZE);
        int snakeGen = gen.nextInt(GRID_SIZE * GRID_SIZE);
        while (snakeGen == foodGen) {
            snakeGen = gen.nextInt(GRID_SIZE * GRID_SIZE);
        }

        int row = snakeGen / GRID_SIZE;
        int col = snakeGen % GRID_SIZE;
        snake.setHead(new Pair(row, col));
        grid[row][col] = 1;

        row = foodGen / GRID_SIZE;
        col = foodGen % GRID_SIZE;
        grid[row][col] = 2;
    }

    public Snake getSnake() {
        return snake;
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
    }

    public Pair getFoodLocation() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == 2) return new Pair(i, j);
            }
        }
        return null;
    }

    public boolean isFoodEaten(Pair head) {
        Pair food = getFoodLocation();
        return food != null && head.equals(food);
    }

    public void regenerateFood() {
        int foodGen = gen.nextInt(GRID_SIZE * GRID_SIZE);
        int row = foodGen / GRID_SIZE;
        int col = foodGen % GRID_SIZE;
        Pair newPair = new Pair(row, col);

        while (snake.getQueue().contains(newPair)) {
            foodGen = gen.nextInt(GRID_SIZE * GRID_SIZE);
            row = foodGen / GRID_SIZE;
            col = foodGen % GRID_SIZE;
            newPair = new Pair(row, col);
        }

        grid[row][col] = 2;
    }

    public void updateGrid() {
        // Clear previous snake positions
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == 1) grid[i][j] = 0;
            }
        }

        // Draw snake
        for (Pair pair : snake.getQueue()) {
            grid[pair.getRow()][pair.getCol()] = 1;
        }

        // Make sure food remains
        Pair food = getFoodLocation();
        if (food != null) {
            grid[food.getRow()][food.getCol()] = 2;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                switch (grid[i][j]) {
                    case 0 -> sb.append(". ");
                    case 1 -> sb.append("S ");
                    case 2 -> sb.append("F ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}