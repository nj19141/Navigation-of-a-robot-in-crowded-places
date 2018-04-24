package robonavigation;

public class human {

    float xcoord;
    float ycoord;
    float speed;
    float c1;
    float c2;
    float delta1;
    float delta2;

    public human(float xcoord, float ycoord, float speed, float c1, float c2, float delta1, float delta2) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.speed = speed;
        this.c1 = c1;
        this.c2 = c2;
        this.delta1 = delta1;
        this.delta2 = delta2;
    }

    public float getXcoord() {
        return xcoord;
    }

    public void setXcoord(float xcoord) {
        this.xcoord = xcoord;
    }

    public float getYcoord() {
        return ycoord;
    }

    public void setYcoord(float ycoord) {
        this.ycoord = ycoord;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getC1() {
        return c1;
    }

    public void setC1(float c1) {
        this.c1 = c1;
    }

    public float getC2() {
        return c2;
    }

    public void setC2(float c2) {
        this.c2 = c2;
    }

    public float getDelta1() {
        return delta1;
    }

    public void setDelta1(float delta1) {
        this.delta1 = delta1;
    }

    public float getDelta2() {
        return delta2;
    }

    public void setDelta2(float delta2) {
        this.delta2 = delta2;
    }

}
