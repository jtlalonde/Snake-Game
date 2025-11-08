import java.util.Scanner;

public class CLIMain {
    public static void main(String[] args) {
        GameGrid grid = new GameGrid();
        Snake snake = grid.getSnake();
        Scanner scan = new Scanner(System.in);

        System.out.println("This is a simple CLI Snake game!\n");

        boolean gameOver = false;
        while (!gameOver) {
            System.out.println(grid);
            System.out.print("Move (u/d/l/r): ");
            String move = scan.next().toLowerCase();

            switch (move) {
                case "u" -> snake.setDirection("up");
                case "d" -> snake.setDirection("down");
                case "l" -> snake.setDirection("left");
                case "r" -> snake.setDirection("right");
            }

            Pair newHead = snake.move(false);

            if (!grid.inBounds(newHead.getRow(), newHead.getCol()) || snake.hasCollided()) {
                System.out.println("Game Over!");
                gameOver = true;
                break;
            }

            boolean ateFood = grid.isFoodEaten(newHead);
            if (ateFood) {
                snake.move(true); // grow the snake
                grid.regenerateFood();
            }

            grid.updateGrid();
        }

        System.out.println("Final Snake Size: " + snake.getSize());
        System.out.println(grid);
    }
}