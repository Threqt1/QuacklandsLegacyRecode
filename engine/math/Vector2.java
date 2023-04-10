package engine.math;

public class Vector2 {
    public static final Vector2 IDENTITY = new Vector2(0, 0);
    public static final Vector2 UP = new Vector2(0, -1);
    public static final Vector2 DOWN = new Vector2(0, 1);
    public static final Vector2 LEFT = new Vector2(-1, 0);
    public static final Vector2 RIGHT = new Vector2(1, 0);
    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(other.getX() + this.getX(), other.getY() + this.getY());
    }

    public Vector2 sub(Vector2 other) {
        return new Vector2(other.getX() - this.getX(), other.getY() - this.getY());
    }

    public Vector2 scale(int factor) {
        return new Vector2(this.getX() * factor, this.getY() * factor);
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

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
