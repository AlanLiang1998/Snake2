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
        switch (head.dir) {
            case LEFT:
                head.col--;
                break;
            case UP:
                head.row--;
                break;
            case RIGHT:
                head.col++;
                break;
            case DOWN:
                head.row++;
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                head.dir = Direction.LEFT;
                break;
            case KeyEvent.VK_UP:
                head.dir = Direction.UP;
                break;
            case KeyEvent.VK_RIGHT:
                head.dir = Direction.RIGHT;
                break;
            case KeyEvent.VK_DOWN:
                head.dir = Direction.DOWN;
                break;
        }
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
