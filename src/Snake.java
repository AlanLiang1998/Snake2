import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake {

    Node head = null;
    Node tail = null;
    int length = 0;
    UI ui = null;

    public Snake(UI ui) {
        this.ui = ui;
        Node initNode = new Node(20, 20, Direction.LEFT);
        head = initNode;
        tail = initNode;
        length = 1;
    }

    public void draw(Graphics g) {
        move();
        for (Node node = head; node != null; node = node.next) {
            node.draw(g);
        }
    }

    public void move() {
        if (head.row < 2 || head.col < 0 || head.row >= UI.ROWS || head.col >= UI.COLS) {
            ui.setGameOver(true);
            return;
        }
        for (Node node = head.next; node != null; node = node.next) {
            if (head.row == node.row && head.col == node.col) {
                ui.setGameOver(true);
                return;
            }
        }
        addToHead();
        deleteTail();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_A:
                if (head.dir != Direction.RIGHT)
                    head.dir = Direction.LEFT;
                break;
            case KeyEvent.VK_W:
                if (head.dir != Direction.DOWN)
                    head.dir = Direction.UP;
                break;
            case KeyEvent.VK_D:
                if (head.dir != Direction.LEFT)
                    head.dir = Direction.RIGHT;
                break;
            case KeyEvent.VK_S:
                if (head.dir != Direction.UP)
                    head.dir = Direction.DOWN;
                break;
        }
    }

    public Rectangle getRect() {
        return new Rectangle(head.col * Node.WIDTH, head.row * Node.HEIGHT, Node.WIDTH, Node.HEIGHT);
    }

    public void eat(Egg e) {
        if (this.getRect().intersects(e.getRect())) {
            addToHead();
            e.refresh();
        }
    }

    public void addToTail() {
        Node newNode = null;
        switch (tail.dir) {
            case LEFT:
                newNode = new Node(tail.row, tail.col + 1, tail.dir);
                break;
            case UP:
                newNode = new Node(tail.row + 1, tail.col, tail.dir);
                break;
            case RIGHT:
                newNode = new Node(tail.row, tail.col - 1, tail.dir);
                break;
            case DOWN:
                newNode = new Node(tail.row - 1, tail.col, tail.dir);
                break;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        length++;
    }

    public void addToHead() {
        Node newNode = null;
        switch (head.dir) {
            case LEFT:
                newNode = new Node(head.row, head.col - 1, head.dir);
                break;
            case UP:
                newNode = new Node(head.row - 1, head.col, head.dir);
                break;
            case RIGHT:
                newNode = new Node(head.row, head.col + 1, head.dir);
                break;
            case DOWN:
                newNode = new Node(head.row + 1, head.col, head.dir);
                break;
        }
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
        length++;
    }

    public void deleteTail() {
        if (length <= 0)
            return;
        tail = tail.prev;
        tail.next = null;
        length--;
    }

    private class Node {
        public static final int WIDTH = UI.BLOCK_SIZE;
        public static final int HEIGHT = UI.BLOCK_SIZE;
        int row;
        int col;
        Node prev = null;
        Node next = null;
        Direction dir;

        public Node(int row, int col, Direction dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        public void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.BLACK);
            g.fillOval(col * WIDTH, row * HEIGHT, WIDTH, HEIGHT);
            g.setColor(c);
        }
    }

}
