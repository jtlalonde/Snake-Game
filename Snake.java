// Snake.java
//
// This class is a representation of the snake on the grid

import java.util.LinkedList;

public class Snake {
    private LinkedList<Pair> queue;
    private String direction;

    public Snake() {
        queue = new LinkedList<>();
        direction = "";
    }

    public LinkedList<Pair> getQueue() {
        return queue;
    }

    public void setHead(Pair snakeHead) {
        queue.add(snakeHead);
    }

    public int getSize() {
        return queue.size();
    }

    public Pair getHead() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Snake has no head!");
        }
        return queue.getLast();
    }

    public Pair getTail() {
        return queue.peek();
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Pair move(boolean grow) {
        if (direction.isEmpty()) return queue.getLast();

        Pair head = getHead();
        int newRow = head.getRow();
        int newCol = head.getCol();

        switch (direction) {
            case "left" -> newCol--;
            case "right" -> newCol++;
            case "up" -> newRow--;
            case "down" -> newRow++;
        }

        Pair newHead = new Pair(newRow, newCol);
        queue.add(newHead);

        if (!grow) {
            queue.remove();
        }

        return newHead;
    }

    public boolean hasCollided() {
        Pair head = getHead();
        for (int i = 0; i < queue.size() - 1; i++) {
            if (queue.get(i).equals(head)) return true;
        }
        return false;
    }
}