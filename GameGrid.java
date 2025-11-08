// GameGrid.java
//
// This class will outline the grid for the game Snake
//
// 0 means the space is empty, 1 means the space is part of the snake,
// and 2 means the space is the food for the snake

import java.util.Random;

public class GameGrid {
	private int[][] grid;
	private Random gen;

	public GameGrid() {
		initializeGrid();
	}

	private void initializeGrid() {
		grid = new int[40][40];
		gen = new Random();

		int foodGen = gen.nextInt(40 * 40);
		int snakeGen = gen.nextInt(40 * 40);
		while (snakeGen == foodGen) {
			snakeGen = gen.nextInt(40 * 40);
		}

		int row = foodGen / 40;
		int col = foodGen % 40;
		grid[row][col] = 2;

		row = snakeGen / 40;
		col = snakeGen % 40;
		grid[row][col] = 1;
	}

	public int getSnakeSize() {
		int count = 0;
		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 40; j++) {
				if (grid[i][j] == 1) {
					count++;
				}
			}
		}
		return count;
	}
	
}
