package common;

import java.util.ArrayList;
import java.util.List;

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