package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Core of the debugging contextualization implementation.
// A NodeContextMessage records all debugging contextualization
// information we wish to keep about a navigated-to node.

// A stack of these makes up a "full" or verbose ContextStack.
// The ContextStack is then compressed to yield a better 
// SimplifiedContextStack.

// Section 1. (HEADERS). Headers are either TRANSLATION-X (for rewrite rules) or HIGHER-ORDER 
// (for higher-order attributes). They represent the cumulative nesting of these constructs 
// a current node has relative to the program root. Headers are dependent on a node's labels and
// its ancestors' labels.

// Section 2. (CONCRETE SYNTAX). Concrete syntax representation of the current node. 
// Should hold parsed concrete syntax from the source file if headers are empty 
// (no rewrite rules or higher-order attributes traversed yet), or the node's
// pretty print attribute otherwise.

// Section 3. (PRODUCTION). Store the production name (we currently keep 
// file lines as well. But they are not needed for the current version of 
// the SimplifiedContextStack). 

// TODO. Perhaps show file lines in the SimplifiedContextStack as well, at least
// for "Tree Order" 0 nodes.

// SECTION 4. (labels). Labels represents whether the current node is involved with
// horizontal edges. Current possible labels are is-contractuma and is-redex 
// (for forwarding relationship) and is-attribute-root (for higher-order attribute subtree roots).
// Labels are currently extracted from DecoratedNode itself. 

// TODO. Add a new contextualization label/header framework for REFERENCE ATTRIBUTES.  

public class NodeContextMessage {

    // String representations for each section.
    public String GetSection0() {
        return Integer.toString(this.num_index);
    }

    public String GetSection1() {
        String res = "";
        boolean first_set = false;
        if (this.translation_x > 0) {
            res += "TRANSLATION-" + this.translation_x;
            first_set = true;
        }
        if (this.higher_order_y > 0) {
            if (first_set) {
                res += " & ";
            }
            res += "HIGHER-ORDER-" + this.higher_order_y;
        }
        return res;
    }

    public String GetSection2() {
        return this.text_repr;
    }

    public String GetSection3() {
        String res = "prod: " + this.prod_name + "\n" + 
            this.filename + " lines: " + this.fc_start.getRow() + 
            ":" + this.fc_start.getCol() + " -> " + this.fc_end.getRow() + 
            ":" + this.fc_end.getCol(); 
        return res;
    }

    // Not mutually exclusive labels
    public String GetSection4() {
        String res = "";
        if (this.is_redex) {
            res += "*is-redex\n";
        }
        if (this.is_contractum) {
            res += "*is-contractum of " + this.contractum_of + "\n";
        }
        if (this.is_attribute_root) {
            res += "*is-attribute_root\n";
        }
        return res;
    }

    // Constructor for NodeContextMessage
    public NodeContextMessage(DecoratedNode node, int num_index) {
        
        // Set the current index. Keep track of it in the stack
        // to make decrementing on pop() easy
        this.num_index = num_index;

        // Necessary order.
    
        // Section 2
        this.prod_name = node.getNode().getName();
        this.fill_in_rows_and_cols(node);

        // Section 4
        this.set_labels(node);
        
        // Section 1--headers depend on label attributes
        this.initialize_headers(node);

        // Section 3. Determine file lines last
        //  because they depend on computing boolean attributes
        if ((this.translation_x > 0) || (this.higher_order_y > 0) ||
            this.is_attribute_root || this.is_contractum) {
            // If headers are represent (non-"order" 0 node), its pretty print is used. 
            this.pretty_print(node);
        }
        else {
            // Otherwise file times are used for the text/concrete syntax representation.
            this.pull_filelines();
        }
    }

    // Set translation_x and translation_x bools here
    private void initialize_headers(DecoratedNode node) {

        this.translation_x = node.getIsTranslation();
        this.higher_order_y = node.getIsAttribute();
    }

    // Use file location method written in DecoratedNode
    private void fill_in_rows_and_cols(DecoratedNode node){
        this.fc_start = node.getStartCoordinates();
        this.fc_end = node.getEndCoordinates();
        this.filename = node.getFilename();
    }

    // Labels currently only regard forwarding and higher-order attributes.
    private void set_labels(DecoratedNode node) {
       
        this.is_redex = node.getIsRedex();
        this.is_contractum = node.getIsContractum();
        this.is_attribute_root = node.getIsAttributeRoot();

        // Only relevant if these attributes are true
        this.contractum_of = this.num_index - 1;
        this.attribute_of = this.num_index - 1;
    }

    // access pp attribute if present  
    private void pretty_print(DecoratedNode node) {
        
        this.text_repr = node.getPrettyPrint();
    }

    // Basically extract file lines from row x col y to 
    // row x' to y' for the two FileCoordinates this class stores.
    private void pull_filelines() {
       
        // Currently not the most efficient but should get the job done for now
       try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            
            int row = 1;
            int col = 1;
            String res = "";
            for (; row < this.fc_start.getRow(); row++) {
                // Skip these rows.
                String line = br.readLine();
            }
            // Advance to starting char
            for (; col < this.fc_start.getCol(); col++) {
                //Single char read
                // System.out.println("skipping col: " + col);
                int c = br.read(); 
            }

            // Now in correct row to start actually noting down file contents

            // Get whole lines here
            for (; row < this.fc_end.getRow(); row++) {
                // System.out.println("reading row: " + row);
                res += br.readLine();
                res += '\n'; // Since not added with readLine()
            }
            // Now row = row.end
            for (; col <= this.fc_end.getCol(); col++) {
                // System.out.println("reading col: " + col);
                char c = (char)br.read();
                res += Character.toString(c);
            }
            // Done. Can close file now
            br.close();

            // Just set filebytes (text_repr) to res
            this.text_repr = res;
        } 
        catch (IOException e) {
            System.out.println("ERROR READING FROM FILE " + this.filename);
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.GetSection0() + "\n" + 
            this.GetSection1() + "\n" + 
            this.GetSection2() + "\n" + 
            this.GetSection3() + "\n" + 
            this.GetSection4();
    }


    // Section 0. Every context box will have a numeric index label
    private int num_index;
    // private static int next_index = 0;

    // Section 1. Header will contain TRANSLATION and/or HIGHER-ORDER 
    public int getTranslationX() {
        return this.translation_x;
    }
    public int getHigherOrderY() {
        return this.higher_order_y;
    }
    private int translation_x;
    private int higher_order_y;
    
    // Section 2. Actual text representation 
    // (either copied from file or prity print (pp) represenation)
    public String getTextRepr() {
        return this.text_repr;
    }
    private String text_repr;
    
    // Section 3. Production name and file lines
    public String getProdName() {
        return this.prod_name;
    }
    private String prod_name;
    
    public String getFilenmae() {
        return this.filename;
    }
    private String filename;

    public FileCoordinate getFileCoordianteStart() {
        return this.fc_start;
    }
    private FileCoordinate fc_start;
    
    public FileCoordinate getFileCoordianteEnd() {
        return this.fc_end;
    }
    private FileCoordinate fc_end;

    // Section 4. Labels and associated info
    private boolean is_redex;
    private boolean is_contractum;
    private int contractum_of;
    private boolean is_attribute_root;
    private int attribute_of;

    public boolean isRedex() {
        return is_redex;
    }

    public boolean isContractum() {
        return is_contractum;
    }

    public int getContractumOf() {
        return contractum_of;
    }

    public boolean isAttributeRoot() {
        return is_attribute_root;
    }

    public int getAttributeOf() {
        return attribute_of;
    }
}