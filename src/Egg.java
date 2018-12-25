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
        row = rand.nextInt(UI.ROWS);
        col = rand.nextInt(UI.COLS);
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(col * WIDTH, row * HEIGHT, WIDTH, HEIGHT);
        g.setColor(c);
    }
}
