package Lab1;

import java.util.Random;

public class Vertex {
    private int name;
    private int x;
    private int y;

    public Vertex() {
        Random rand = new Random();
        this.name = 0;
        this.x = rand.nextInt(680)+10;
        this.y = rand.nextInt(480)+10;
    }

    public Vertex(int name) {
        Random rand = new Random();
        this.name = name;
        this.x = rand.nextInt(680)+10;
        this.y = rand.nextInt(480)+10;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
