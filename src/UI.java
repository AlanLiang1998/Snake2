import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UI extends Frame {
    public static final int ROWS = 30;
    public static final int COLS = 30;
    public static final int BLOCK_SIZE = 30;
    public static final int WIDTH = COLS * BLOCK_SIZE;
    public static final int HEIGHT = ROWS * BLOCK_SIZE;

    public void launch() {
        setTitle("贪吃蛇大战");
        setLocation(100, 100);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setBackground(Color.GREEN);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public void drawGrid(Graphics g) {
        for (int i = 1; i < ROWS; i++) {
            g.drawLine(0, BLOCK_SIZE * i, WIDTH, BLOCK_SIZE * i);
        }
        for (int i = 1; i < COLS; i++) {
            g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, HEIGHT);
        }
    }

    public void paint(Graphics g) {
        drawGrid(g);
    }

    public static void main(String[] args) {
        new UI().launch();
    }
}
