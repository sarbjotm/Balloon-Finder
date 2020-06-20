package ca.sfu.cmpt_276_a3.Model;

public class Location {

    private int x;
    private int y;
    private int scanValue;

    public Location(){ }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getScanValue() {
        return this.scanValue;
    }

    public void setScanValue(int scanValue) {
        this.scanValue = scanValue;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }
}
