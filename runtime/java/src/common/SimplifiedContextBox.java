package common;

import java.util.ArrayList;
import java.util.List;

// The SimplifiedContextBox maintains all information needed for 
// an individual element as part of simplified debugging contextualization.

// They are the elements of a SimplifiedContextStack.

// It fully represents a path that has no horizontal edges.
    // (forwarding/translation
    //  or higher-order attribute entry links)

// The SimplifiedContextStack creates one of these boxes each time
// it encounters a horizontal edge (plus the original started at 
// the program root).

// Tree Order represents how many horizontal edges 
// have been navigated across.

// Text Syntax represents the current path through concrete syntax.
// text_syntax should store parsed concrete syntax when (x, y) from tree order
// are both 0. Otherwise, it will be the pretty print representation. This is for 
// the first production associated with a SimplifiedContextBox (widest-spanning)

// syntax_to_highlight should be highlighted within text_syntax. It represents 
// the deepest (least-spanning) navigated-to node within the path of productions 
// such a box represents. 

// TODO: some extra information while doing tree traversal 
// will be needed to make highlighting unique
// if there are mulitple instances of syntax_to_highlight within text_syntax.

// Productions Visited. Just a list of production names this box's abstract 
// syntax tree path represents. They should be added with increasing tree depth.

// Interesting Features. Records which nodes are associated with horizontal edges
// themselves. This info comes from NodeContextMessage objects stored in the 
// basic ContextStack from which a SimplifiedContextStack is built from.

// There are currently HTML and toString representations of an individual box.
// When adding/generating HTML, the headers are added within SimplifiedContextStack. 



public class SimplifiedContextBox {

    // 4 sections

    // 1. Tree Order
    public int translation_x;
    public int higher_order_y;

    // 2. Text Syntax
    public String text_syntax;
    public String syntax_to_highlight;

    // 3. Productions Visited
    public ProductionName prods_visited[];

    // 4. Interesting Features
    public List<Feature> features;

    private String sectionSep = "--------------------\n";

    // For HTML: no labels added
    public String getTreeOrderHTML() {
        if (this.translation_x == 0 && this.higher_order_y == 0) {
            return "0";
        }
        String res = "";
        if (this.translation_x > 0) {
            res += "TRANSLATION-" + this.translation_x;
        }
        if (this.higher_order_y > 0) {
            res = ", HIGHER-ORDER-" + this.higher_order_y;
        }
        return res;
    }

    public String getAllSyntaxHTML() {
        return this.text_syntax;
    }

    public String getsyntax_to_highlightHTML() {
        return this.syntax_to_highlight;
    }

    public String getProductionsVisitedHTML() {
        String res = "";
        for (int i = 0; i < this.prods_visited.length; i++) {
            res += this.prods_visited[i].toString() + "; ";
        }
        return res;
    }

    public String getFeaturesHTML() {
        if (this.features.size() == 0) {
            return "";
        }
        String res = "";
        for (Feature feature: this.features) {
            res += feature.toString() + "; ";
        }
        return res;
    }


    public String getHTMLBox() {
        
        // Section 1: Tree Order
        String res = "";
        res += "<div><h2>Tree Order:</h2>";
        res += "<p>" + this.getTreeOrderHTML() + "</p></div>";
        
        // Section 2: Syntax
        // FIXME: ACTUALLY HIGHLIGHT WITHIN ALL SYNTAX
        res += "<div><h2>ALL SYNTAX</h2>";
        res += "<p>" + this.getAllSyntaxHTML() + "</p></div>";

        res += "<div><h2>TO HIGHLIGHT</h2>";
        res += "<p>" + this.getsyntax_to_highlightHTML() + "</p></div>";

        // Section 3: Productions Visited
        res += "<div><h2>Productions Visited:</h2>";
        res += "<p>" + this.getProductionsVisitedHTML() + "</p></div>";

        // Section 4: Features
        res += "<div><h2>Features:</h2>";
        res += "<p>" + this.getFeaturesHTML() + "</p></div>";

        return res;
    }

    public String getSection1Str() {
        
        String top = "Tree Order: ";
        if (this.translation_x == 0 && this.higher_order_y == 0) {
            return top + "0\n";
        }
        top += "\n";
        String trans = "";
        if (this.translation_x > 0) {
            trans = "TRANSLATION-" + this.translation_x + "\n";
        }
        String higher = "";
        if (this.higher_order_y > 0) {
            higher = "HIGHER-ORDER-" + this.higher_order_y + "\n";
        }
        return top + trans + higher;
    }

    public String getSection2Str() {
        String header1 = "^^^^^ALL SYNTAX^^^^^\n";
        String header2 = "\n^^^^^TO HIGHLIGHT^^^^^\n";
        return header1 + this.text_syntax + header2 + this.syntax_to_highlight + "\n";
    }

    public String getSection3Str() {
        String res = "Productions Visited: \n";
        for (int i = 0; i < this.prods_visited.length; i++) {
            res += "\t" + this.prods_visited[i].toString() + "\n";
        }
        return res;
    }

    public String getSection4Str() {
        if (this.features.size() == 0) {
            return "";
        }
        String res = "Features: \n";
        for (Feature feature: this.features) {
            res += "\t" + feature.toString() + "\n";
        }
        return res;
    }

    public String toString() {
        return 
            getSection1Str() + 
            this.sectionSep + 
            getSection2Str() + 
            this.sectionSep + 
            getSection3Str() + 
            this.sectionSep + 
            getSection4Str();
    }
}