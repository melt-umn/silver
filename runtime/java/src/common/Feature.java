package common;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Feature {
    public String base_prod;
    public String label;
    public String target;
    public String sep;

    public Feature(String base_prod, String label)  {
        this.base_prod = base_prod;
        this.label = label;
        this.target = "";
        this.sep = "";
    }

    public Feature(String base_prod, String label, String target)  {
        this.base_prod = base_prod;
        this.label = label;
        this.target = target;
        this.sep = "";
    }

    public void setSep() {
        if (this.label.contains("redex")) {
            this.sep = "to";
        }
        else if (this.label.contains("contractum")) {
            this.sep = "of";
        }
        else if (this.label.contains("higher-order")) {
            this.sep = "";
        }
        else if (this.label.contains("new")) {
            this.sep = "";
        }
        else {
            this.sep = "INVALID LABEL";
        }
    }

    public String toString() {
        if (! this.label.contains("new")) {
            return this.base_prod + ": " + this.label + 
            " (" + this.sep + " " + this.target + ")";
        } 
        else {
            return this.base_prod + ": " + this.label;
        }
    }
}
