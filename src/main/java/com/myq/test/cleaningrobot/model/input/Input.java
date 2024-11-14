package com.myq.test.cleaningrobot.model.input;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("jsonschema2pojo")
public class Input {

    @SerializedName("map")
    @Expose
    private List<List<String>> map = new ArrayList<List<String>>();
    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("commands")
    @Expose
    private List<String> commands = new ArrayList<String>();
    @SerializedName("battery")
    @Expose
    private int battery;

    /**
     * No args constructor for use in serialization
     */
    public Input() {
    }

    public Input(List<List<String>> map, Start start, List<String> commands, int battery) {
        super();
        this.map = map;
        this.start = start;
        this.commands = commands;
        this.battery = battery;
    }

    public List<List<String>> getMap() {
        return map;
    }

    public void setMap(List<List<String>> map) {
        this.map = map;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Input.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("map");
        sb.append('=');
        sb.append(((this.map == null) ? "<null>" : this.map));
        sb.append(',');
        sb.append("start");
        sb.append('=');
        sb.append(((this.start == null) ? "<null>" : this.start));
        sb.append(',');
        sb.append("commands");
        sb.append('=');
        sb.append(((this.commands == null) ? "<null>" : this.commands));
        sb.append(',');
        sb.append("battery");
        sb.append('=');
        sb.append(this.battery);
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
        result = ((result * 31) + ((this.start == null) ? 0 : this.start.hashCode()));
        result = ((result * 31) + this.battery);
        result = ((result * 31) + ((this.map == null) ? 0 : this.map.hashCode()));
        result = ((result * 31) + ((this.commands == null) ? 0 : this.commands.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Input) == false) {
            return false;
        }
        Input rhs = ((Input) other);
        return (((((this.start == rhs.start) || ((this.start != null) && this.start.equals(rhs.start))) && (this.battery == rhs.battery)) && ((this.map == rhs.map) || ((this.map != null) && this.map.equals(rhs.map)))) && ((this.commands == rhs.commands) || ((this.commands != null) && this.commands.equals(rhs.commands))));
    }

}