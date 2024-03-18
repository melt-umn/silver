package common;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Production {
    public String name;
    public int  index;

    public Production(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String toString() {
        if (this.index == 0) {
            return this.name;
        }
        else {
            return this.name + " (" + this.index + ")";
        }
    }
}