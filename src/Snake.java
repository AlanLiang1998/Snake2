import java.awt.*;

public class Snake {
    Node head = null;
    Node tail = null;
    int length = 0;
    UI ui = null;

    public Snake(UI ui) {
        this.ui = ui;
        Node initNode = new Node(20, 20);
        head = initNode;
        tail = initNode;
        length = 1;
    }

    public void draw(Graphics g) {
        for (Node node = head; node != null; node = node.next) {
            node.draw(g);
        }
    }

    private class Node {
        public static final int WIDTH = UI.BLOCK_SIZE;
        public static final int HEIGHT = UI.BLOCK_SIZE;
        int row;
        int col;
        Node prev = null;
        Node next = null;
        //Direction dir;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.BLACK);
            g.fillOval(col * WIDTH, row * HEIGHT, WIDTH, HEIGHT);
            g.setColor(c);
        }
    }
}
