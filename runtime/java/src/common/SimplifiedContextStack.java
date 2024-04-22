package common;

import java.util.Iterator;
import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;
import java.io.IOException;
import java.io.BufferedWriter;

// Core of contextualization for debugging:
// The SimplifiedContextStack is basically a heavyweight decorator
//  over a ContextStack to generate a simplified (one node per tree order/
// horizontal-edge encountered) stack.

// Its ContextStack member full_stack is dynamically updated while debugging occurs.
// Then, extracting a contextualization from a SimplifiedContextStack requires
// calling updateSimplifiedStack() to create an updated SimplifiedContextStack 
// (of SimplifiedConextBox objects) based on the current  state of the ContextStack full_stack. 

// TODO. Move full_stack updating directly into here, 
// i.e., push/pop nodes into the simplifiedStack directly. 

// TODO. Find better than O(n^2) time complexity for production name assignment. 

// It currently can print some primitive text representations to a text file, or generate
// a better HTML file. (Filenames can be specified upin SimplifiedContextStack construction).

public class SimplifiedContextStack {

    public SimplifiedContextStack(ContextStack full_stack) {
        this.full_stack = full_stack;
        this.partition = new int[full_stack.get_height()];
        this.filename = "simpleDebugContext.txt";
        this.filename_html = "simpleDebugContext.html";
    }

    public SimplifiedContextStack(ContextStack full_stack, String fn) {
        this.full_stack = full_stack;
        this.partition = new int[full_stack.get_height()];
        this.filename = fn;
        this.filename_html = "simpleDebugContext.html";
    }

    public SimplifiedContextStack(ContextStack full_stack, String fn, String fn_html) {
        this.full_stack = full_stack;
        this.partition = new int[full_stack.get_height()];
        this.filename = fn;
        this.filename_html = fn_html;
    }

    // KEY function called immediately when generateHTMLFile() or show() are called.
    // Updates this.simple_stack based on any changes to this.full_stack
    private void updateSimplifiedStack() {
        this.need_set_all_prods = true;
        this.makeSimplifiedStack();
    }

    // Create a basic text representation
    public void show(){
        this.updateSimplifiedStack();
        String border = "*******************";
        
        try{
            FileWriter myWriter = new FileWriter(this.filename);
            // Want to go backwards through stack. Render it from top down in the file
            // java stack iterator doesn't support going backwards
            for (int i = this.simple_stack.size() - 1; i >= 0; i--) {
                SimplifiedContextBox sbox = this.simple_stack.get(i);
                myWriter.write(border + "\n" + sbox.toString() + "\n" + border);
            }
            myWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Main function with extract a contextualization out. Must push to/pop to the separate ContextStack
    // that was used to create 
    public void generateHTMLFile() {
        this.updateSimplifiedStack();
    
        try (FileWriter myWriter = new FileWriter(this.filename_html)) {
            myWriter.write("<html><head><title>Simplified Context Stack</title></head><body>\n");
    
            // Iterate through the stack in reverse order
            for (int i = this.simple_stack.size() - 1; i >= 0; i--) {
                SimplifiedContextBox sbox = this.simple_stack.get(i);
                
                myWriter.write(sbox.getHTMLBox());
    
                // Add a border between SimplifiedContextBoxes
                myWriter.write("<hr>");
            }
    
            myWriter.write("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Recreate the internal simplified stack based on an updated full ContextStack 
    private void makeSimplifiedStack() {
        
        // Clear previous simplified stack to get a brand new one
        this.simple_stack = new Stack<SimplifiedContextBox>();
        
        this.fillInPartition();
        
        // Now make one box per partition
        int prev_partition_index = 0; 
        int start = 0;
        int end = -1;

        // System.out.println(this.partition[0]);
        for (int i = 0; i < this.partition.length; i++) {
            
            if (this.partition[i] > prev_partition_index) {
                // Make box on existing start and end
                SimplifiedContextBox sbox = this.makeSimplifiedBox(start, end);
                this.simple_stack.push(sbox);
                start = i;
                end = start;
                prev_partition_index++;
            }
            else {
                end++;
            }
        }
        // Last one
        SimplifiedContextBox sbox = this.makeSimplifiedBox(start, end);
        this.simple_stack.push(sbox);
    }


    // Make a single box after partitioning has been done. 
    // Compress full stack nodes at (inclusive) indices i..j
    // into a single SimplifiedContextBox. 
    private SimplifiedContextBox makeSimplifiedBox(int i, int j) {
        
        if (i > j) {
            System.out.println("Invalid Partition Indices: " + i + ", " + j);
            return null;
        }
        
        // 4 sections to fill in 
        SimplifiedContextBox sbox = new SimplifiedContextBox();

        NodeContextMessage first = this.full_stack.get(i);
        NodeContextMessage last = this.full_stack.get(j);
        
        // 1. Tree Order Trivial
        sbox.translation_x = first.getTranslationX();
        sbox.higher_order_y = first.getHigherOrderY();

        // 2. Text Syntax (highlight later when rendering)
        sbox.text_syntax = first.getTextRepr();
        sbox.syntax_to_highlight = last.getTextRepr();

        // 3. Need some counting logic to keep track of unique indices
        this.SetAllProds();
        sbox.prods_visited = Arrays.copyOfRange(this.productions, i, j + 1);

        // Make features list now (list, not array, since unknown length)
        sbox.features = new ArrayList<Feature>();
        this.fillInFeaturesList(sbox, i, j);
        
        return sbox;
    }

    // Helper to iterate through a sequence of NodeContextMessages within this.full_stack,
    // extract their labels, and create a Feature list.
    private void fillInFeaturesList(SimplifiedContextBox sbox, int i, int j) {
        
        for (int k = i; k <= j; k++) {
            NodeContextMessage node = this.full_stack.get(k);

            if (node.isRedex()) {
                String nodeName = productions[k].toString();
                String targetName = "";
                if (k < j) {
                    targetName = productions[k + 1].toString();
                }
                Feature f = new Feature(nodeName, "redex", targetName);
                sbox.features.add(f);
            }
            if (node.isContractum()) {
                String nodeName = productions[k].toString();
                String targetName = "";
                if (k > 0) {
                    targetName = productions[k - 1].toString();
                }
                Feature f = new Feature(nodeName, "contractum", targetName);
                sbox.features.add(f);
            }
            if (node.isAttributeRoot()) {
                String nodeName = productions[k].toString();
                String targetName = "";
                if (k > 0) {
                    targetName = productions[k - 1].toString();
                }
                Feature f = new Feature(nodeName, "attribute root", targetName);
                sbox.features.add(f);
            }
        }
    }

    // Determine all production names before partitioning to give repeated names 
    // new indices. 
    private void SetAllProds() {
        
        if (! this.need_set_all_prods) {
            return;
        }
        // Only do this once 
        // Not worried about multiple instances of SimplifiedContextStack;
        // rather, don't want to do this for every SimplifiedContextBox created

        ProductionName all_prods[] = new ProductionName[this.full_stack.get_height()];

        for (int index = 0; index < this.full_stack.get_height(); index++) {
            ProductionName pn = new ProductionName(this.full_stack.get(index).getProdName(), -1);
            all_prods[index] = pn;
        }


        // Going to be fine with O(n^2) worst case time complexity here for now
        // Simply a prototype implementation of simplified stack

        // Unique prod names get the 0 index (not to be displayed)
        // Non-unique prod names are numbered from 1..n, so need extra
        // scan afterwards to separate unique 1 -> 0 from actual 1 
        for (int prod_index = 0; prod_index < all_prods.length; prod_index++) {
            
            int seq_num = 1;
            String name = all_prods[prod_index].name;
            
            for (int visit_index = 0; visit_index < prod_index; visit_index++) {
                String cur_name = all_prods[visit_index].name;
                if (cur_name.compareTo(name) == 0) {
                    seq_num++;
                }
            }
            all_prods[prod_index].index = seq_num;
        }

        // All prod indices >= 1. Find if need to differentiate 1 into 0 now
        // if truly unique and not start of 1..n sequence of indices
        for (int prod_index = 0; prod_index < all_prods.length; prod_index++) {
            
            if (all_prods[prod_index].index == 1) {
                boolean is_unique = true;
                for (int k = 0; k < all_prods.length; k++) {
                    if (k != prod_index && 
                        (all_prods[k].name.compareTo(all_prods[prod_index].name) == 0)) {
                            is_unique = false;
                            break;
                        }
                }
                if (is_unique) {
                    all_prods[prod_index].index = 0;
                }
            }
        }

        this.productions = all_prods;
        this.need_set_all_prods = false;

        return;
    }

    // Determine partition of this.full_stack based on different headers.
    // All nodes of the same header go into one partition element.
    // Denote the partition by different sequential indices in the this.partition array.
    private void fillInPartition() {

        this.partition = new int[full_stack.get_height()];

        int previous_x = 0;
        int previous_y = 0;
        int partition_index = 0;
        for (int i = 0; i < this.full_stack.get_height(); i++) {
            NodeContextMessage node = this.full_stack.get(i);
            int cur_x = node.getTranslationX();
            int cur_y = node.getHigherOrderY();
            if (cur_x == previous_x && cur_y == previous_y) {
                // Within same set of partition
                this.partition[i] = partition_index;
            }
            else {
                previous_x = cur_x;
                previous_y = cur_y;
                // Increment partition index
                partition_index++;
                this.partition[i] = partition_index;
            }
        }
    }

    // The "full"/verbose stack holding the complete path of navigated-to nodes
    // from root to the current node in a stack (basically maintain DFS traversal).
    // Currently, nodes must be pushed/popped separately to full_stack first.
    // TODO. Merge full_stack navigation into this stack.
    private ContextStack full_stack;

    private String filename;
    private String filename_html;

    // Actual contextualization comes from file rendering of the simple_stack.
    private Stack<SimplifiedContextBox> simple_stack = 
        new Stack<SimplifiedContextBox>();
    
    private boolean need_set_all_prods = true;
    private ProductionName productions[];

    // Put 0 for each respective element in the first 
    // stack partition, then 1, etc. Index 0 into stack is bottom
    private int[] partition;
    
}




