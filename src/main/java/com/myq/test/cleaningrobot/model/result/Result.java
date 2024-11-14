package com.myq.test.cleaningrobot.model.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("jsonschema2pojo")
public class Result {

    @SerializedName("visited")
    @Expose
    private List<Visited> visited = new ArrayList<Visited>();
    @SerializedName("cleaned")
    @Expose
    private List<Cleaned> cleaned = new ArrayList<Cleaned>();
    @SerializedName("final")
    @Expose
    private Final _final;
    @SerializedName("battery")
    @Expose
    private int battery;

    /**
     * No args constructor for use in serialization
     */
    public Result() {
    }

    public Result(List<Visited> visited, List<Cleaned> cleaned, Final _final, int battery) {
        super();
        this.visited = visited;
        this.cleaned = cleaned;
        this._final = _final;
        this.battery = battery;
    }

    public List<Visited> getVisited() {
        return visited;
    }

    public void setVisited(List<Visited> visited) {
        this.visited = visited;
    }

    public List<Cleaned> getCleaned() {
        return cleaned;
    }

    public void setCleaned(List<Cleaned> cleaned) {
        this.cleaned = cleaned;
    }

    public Final getFinal() {
        return _final;
    }

    public void setFinal(Final _final) {
        this._final = _final;
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
        sb.append(Result.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("visited");
        sb.append('=');
        sb.append(((this.visited == null) ? "<null>" : this.visited));
        sb.append(',');
        sb.append("cleaned");
        sb.append('=');
        sb.append(((this.cleaned == null) ? "<null>" : this.cleaned));
        sb.append(',');
        sb.append("_final");
        sb.append('=');
        sb.append(((this._final == null) ? "<null>" : this._final));
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
        result = ((result * 31) + ((this.visited == null) ? 0 : this.visited.hashCode()));
        result = ((result * 31) + ((this.cleaned == null) ? 0 : this.cleaned.hashCode()));
        result = ((result * 31) + ((this._final == null) ? 0 : this._final.hashCode()));
        result = ((result * 31) + this.battery);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return (((((this.visited == rhs.visited) || ((this.visited != null) && this.visited.equals(rhs.visited))) && ((this.cleaned == rhs.cleaned) || ((this.cleaned != null) && this.cleaned.equals(rhs.cleaned)))) && ((this._final == rhs._final) || ((this._final != null) && this._final.equals(rhs._final)))) && (this.battery == rhs.battery));
    }

}