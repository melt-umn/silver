package common;

// The Feature class is a helper class for debugging
// contextualization. 

// It stores an individual label
// to be used in a SimplifiedContextBox. These possible
// labels are indended to be redex, contractum, and higher-order
// attribute-root. It stores one in a label. 

// The baseProd is the production name of the node
// this label is associated with.

// The target is "other" the node that may be associated
// with a label (e.g., the redex of a contractum)/

// toString is the main functionality Feature offers when
// writing labels to a file as part of a SimplifiedContextStack.


public class Feature {
    public String baseProd;
    public String label;
    public String target;
    public String sep;

    public Feature(String baseProd, String label)  {
        this.baseProd = baseProd;
        this.label = label;
        this.target = "";
        this.sep = "";
    }

    public Feature(String baseProd, String label, String target)  {
        this.baseProd = baseProd;
        this.label = label;
        this.target = target;
        this.sep = "";
    }

    private void setSep() {
        if (this.label.contains("redex") && ! this.target.equals("")) {
            this.sep = "to";
        }
        else if (this.label.contains("contractum")) {
            this.sep = "of";
        }
        else if (this.label.contains("attribute")) {
            this.sep = "of";
        }
        else {
            this.sep = "INVALID LABEL";
        }
    }

    public String toString() {
        this.setSep();
        if (! this.target.equals("")) {
            return this.baseProd + ": " + this.label + 
            " (" + this.sep + " " + this.target + ")";
        } 
        else {
            return this.baseProd + ": " + this.label;
        }
    }
}
