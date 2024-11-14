package com.myq.test.cleaningrobot.model.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Final {

    @SerializedName("X")
    @Expose
    private int x;
    @SerializedName("Y")
    @Expose
    private int y;
    @SerializedName("facing")
    @Expose
    private String facing;

    /**
     * No args constructor for use in serialization
     */
    public Final() {
    }

    public Final(int x, int y, String facing) {
        super();
        this.x = x;
        this.y = y;
        this.facing = facing;
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

    public String getFacing() {
        return facing;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Final.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("x");
        sb.append('=');
        sb.append(this.x);
        sb.append(',');
        sb.append("y");
        sb.append('=');
        sb.append(this.y);
        sb.append(',');
        sb.append("facing");
        sb.append('=');
        sb.append(((this.facing == null) ? "<null>" : this.facing));
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
        result = ((result * 31) + ((this.facing == null) ? 0 : this.facing.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Final) == false) {
            return false;
        }
        Final rhs = ((Final) other);
        return (((this.x == rhs.x) && (this.y == rhs.y)) && ((this.facing == rhs.facing) || ((this.facing != null) && this.facing.equals(rhs.facing))));
    }

}