package common;

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
            return this.base_prod + ": " + this.label + 
            " (" + this.sep + " " + this.target + ")";
        } 
        else {
            return this.base_prod + ": " + this.label;
        }
    }
}
