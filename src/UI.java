import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UI extends Frame {
    public static final int ROWS = 30;
    public static final int COLS = 30;
    public static final int BLOCK_SIZE = 30;
    public static final int WIDTH = COLS * BLOCK_SIZE;
    public static final int HEIGHT = ROWS * BLOCK_SIZE;

    Snake s = new Snake(this);
    Image offScreenImage = null;
    Egg e = new Egg(this);
    boolean gameOver = false;
    PaintThread pt = new PaintThread();
    int rank = 1;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

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
        addKeyListener(new KeyMonitor());
        new Thread(pt).start();
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

    public void drawGameOverFont(Graphics g) {
        Color c = g.getColor();
        Font f = g.getFont();
        g.setColor(Color.RED);
        g.setFont(new Font("华文彩云", Font.BOLD, 80));
        g.drawString("GAME OVER", 210, 450);
        g.setColor(c);
        g.setFont(f);
    }

    public void paint(Graphics g) {
        drawGrid(g);
        if (gameOver) {
            drawGameOverFont(g);
            pt.over();
        }
        s.draw(g);
        e.draw(g);
        s.eat(e);
    }

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(WIDTH, HEIGHT);
        }
        Graphics gOffSreen = offScreenImage.getGraphics();
        Color c = gOffSreen.getColor();
        gOffSreen.setColor(Color.GREEN);
        gOffSreen.fillRect(0, 0, WIDTH, HEIGHT);
        gOffSreen.setColor(c);
        paint(gOffSreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class PaintThread implements Runnable {
        boolean running = true;
        long repaintTime = 100;

        @Override
        public void run() {
            while (running) {
                repaint();
                try {
                    Thread.sleep(repaintTime - getRank());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void over() {
            running = false;
        }
    }

    private class KeyMonitor extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            s.keyPressed(e);
        }
    }

    public static void main(String[] args) {
        new UI().launch();
    }
}
