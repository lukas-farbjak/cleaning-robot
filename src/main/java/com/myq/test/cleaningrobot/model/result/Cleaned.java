package com.myq.test.cleaningrobot.model.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Cleaned {

    @SerializedName("X")
    @Expose
    private int x;
    @SerializedName("Y")
    @Expose
    private int y;

    /**
     * No args constructor for use in serialization
     */
    public Cleaned() {
    }

    public Cleaned(int x, int y) {
        super();
        this.x = x;
        this.y = y;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Cleaned.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("x");
        sb.append('=');
        sb.append(this.x);
        sb.append(',');
        sb.append("y");
        sb.append('=');
        sb.append(this.y);
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + this.x);
        result = ((result * 31) + this.y);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Cleaned) == false) {
            return false;
        }
        Cleaned rhs = ((Cleaned) other);
        return ((this.x == rhs.x) && (this.y == rhs.y));
    }

}