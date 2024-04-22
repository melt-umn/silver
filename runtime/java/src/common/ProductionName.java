package common;

// The Production name class is a helper class for debugging
// contextualization. 

// It simply associated an index with a production name, which i
// is to be used when there are multiple productions with the same 
// name encountered in a SimplifiedContextStack.

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
        this.index = 0;
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