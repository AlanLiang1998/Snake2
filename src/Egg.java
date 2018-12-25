import java.awt.*;
import java.util.Random;

public class Egg {
    public static final int WIDTH = UI.BLOCK_SIZE;
    public static final int HEIGHT = UI.BLOCK_SIZE;
    static Random rand = new Random();
    int row;
    int col;
    UI ui = null;

    public Egg(UI ui) {
        this.ui = ui;
        row = rand.nextInt(UI.ROWS - 2) + 2;
        col = rand.nextInt(UI.COLS - 2) + 2;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(col * WIDTH, row * HEIGHT, WIDTH, HEIGHT);
        g.setColor(c);
    }

    public Rectangle getRect() {
        return new Rectangle(col * WIDTH, row * HEIGHT, WIDTH, HEIGHT);
    }

    public void refresh() {
        int row = rand.nextInt(UI.ROWS - 2) + 2;
        int col = rand.nextInt(UI.COLS - 2) + 2;
        while (ui.s.eggRecoverBody(row, col)) {
            row = rand.nextInt(UI.ROWS - 2) + 2;
            col = rand.nextInt(UI.COLS - 2) + 2;
        }
        this.row = row;
        this.col = col;
    }
}
