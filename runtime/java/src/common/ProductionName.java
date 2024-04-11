package common;

public class ProductionName {
    public String name;
    public int  index;

    public ProductionName(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // Default constructor
    public ProductionName() {
        this.name = "";
        this.index = -1;
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