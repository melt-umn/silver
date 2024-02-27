package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Context implementation.

// Headers are either TRANSLATION (for contractum) or HIGHER-ORDER
// Uses current label rules of is-contractum, is-redex, is-new, and is-attribute

public class NodeContextMessage {

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
        if (this.is_new) {
            res += "*is-new\n";
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

        // Section 0
        // this.set_index();

        // Try to do all this within java given a node n
    
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
            this.is_attribute_root || this.is_contractum || this.is_new) {
            this.pretty_print(node);
        }
        else {
            this.pull_filelines();
        }

        this.has_been_initialized = true;  
    }

    // private void set_index() {
    //     next_index++;
    //     this.num_index = next_index;
    // }

    // Set translation_x and translation_x bools here
    // Is there a way to know something is an attribute 
    // just from its node values?
    private void initialize_headers(DecoratedNode node) {

        this.translation_x = node.getIsTranslation();
        this.higher_order_y = node.getIsAttribute();
    }

    // Use file location method I wrote in DecoratedNode
    private void fill_in_rows_and_cols(DecoratedNode node){
        this.fc_start = node.getStartCoordinates();
        this.fc_end = node.getEndCoordinates();
        this.filename = node.getFilename();
    }

    // Big reduction semantics logic goes here
    // Simplified now by only allowing forwards
    // to be only reduction semantic
    private void set_labels(DecoratedNode node) {
       
        this.is_redex = node.getIsRedex();
        this.is_contractum = node.getIsContractum();
        // Will always work for forwarding. Only use this value if is_contractum
        this.contractum_of = node.getDebuggingIndex() - 1;
        this.is_new = node.getIsNew();
        this.is_attribute_root = node.getIsAttributeRoot();
        this.attribute_of = node.getDebuggingIndex() - 1;
    }

    // access pp attribute if present  
    private void pretty_print(DecoratedNode node) {
        
        this.text_repr = node.getPrettyPrint();
    }

    // probably need some file I/O, but might find it
    // in java runtime code. Basically extract file lines
    // from row x col y to row x' to y'.
    private void pull_filelines() {
       
        // Currently not the most efficient but should get the job done for now
       try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            
            int row = 1;
            int col = 1;
            String res = "";
            for (; row < this.fc_start.getRow(); row++) {
                // System.out.println("skipping row: " + row);
                String line = br.readLine();
            }
            // Advance to starting char
            for (; col < this.fc_start.getCol(); col++) {
                //Single char read
                // System.out.println("skipping col: " + col);
                int c = br.read(); 
            }
            // reset col to 0 for last read
            // col = 1;
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
        return this.GetSection0() + "\n" + this.GetSection1() + "\n" + this.GetSection2() + "\n" + this.GetSection3() + "\n" + this.GetSection4();
    }

    // @Override
    // protected void finalize() throws Throwable {
    //     next_index--;
    //     super.finalize();
    // }

    // Only perform attribute setting once
    private boolean has_been_initialized = false; 

    // Section 0. Every context box will have a numeric index label
    private int num_index;
    // private static int next_index = 0;

    // Section 1. Header will contain TRANSLATION and/or HIGHER-ORDER 
    private int translation_x;
    private int higher_order_y;
    
    // Section 2. Actual text representation 
    // (either copied from file or prity print (pp) represenation)
    private String text_repr;
    
    // Section 3. Production name and file lines
    private String prod_name;
    private String filename;
    private FileCoordinate fc_start;
    private FileCoordinate fc_end;

    // Section 4. Labels and associated info
    private boolean is_redex;
    private boolean is_contractum;
    private int contractum_of;
    private boolean is_new;
    private boolean is_attribute_root;
    private int attribute_of;
}