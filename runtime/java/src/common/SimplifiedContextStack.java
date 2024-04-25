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

// Its ContextStack member fullStack is dynamically updated while debugging occurs.
// Then, extracting a contextualization from a SimplifiedContextStack requires
// calling updateSimplifiedStack() to create an updated SimplifiedContextStack 
// (of SimplifiedConextBox objects) based on the current  state of the ContextStack fullStack. 

// TODO. Move fullStack updating directly into here, 
// i.e., push/pop nodes into the simplified stack directly. 

// TODO. Find better than O(n^2) time complexity for production name assignment. 

// It currently can print some primitive text representations to a text file, or generate
// a better HTML file. (Filenames can be specified upin SimplifiedContextStack construction).

public class SimplifiedContextStack {

    public SimplifiedContextStack(ContextStack fullStack) {
        this.fullStack = fullStack;
        this.partition = new int[fullStack.getHeight()];
        this.filename = "simpleDebugContext.txt";
        this.filenameHTML = "simpleDebugContext.html";
    }

    public SimplifiedContextStack(ContextStack fullStack, String fn) {
        this.fullStack = fullStack;
        this.partition = new int[fullStack.getHeight()];
        this.filename = fn;
        this.filenameHTML = "simpleDebugContext.html";
    }

    public SimplifiedContextStack(ContextStack fullStack, String fn, String fnHTML) {
        this.fullStack = fullStack;
        this.partition = new int[fullStack.getHeight()];
        this.filename = fn;
        this.filenameHTML = fnHTML;
    }

    
    // KEY function called immediately when generateHTMLFile() or show() are called.
    // Updates this.simpleStack based on any changes to this.fullStack
    private void updateSimplifiedStack() {
        this.needSetAllProds = true;
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
            for (int i = this.simpleStack.size() - 1; i >= 0; i--) {
                SimplifiedContextBox sbox = this.simpleStack.get(i);
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
    
        try (FileWriter myWriter = new FileWriter(this.filenameHTML)) {
            myWriter.write("<html><head><title>Simplified Context Stack</title></head><body>\n");
    
            // Iterate through the stack in reverse order
            for (int i = this.simpleStack.size() - 1; i >= 0; i--) {
                SimplifiedContextBox sbox = this.simpleStack.get(i);
                
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
        this.simpleStack = new Stack<SimplifiedContextBox>();
        
        this.fillInPartition();
        
        // Now make one box per partition
        int prevPartitionIndex = 0; 
        int start = 0;
        int end = -1;

        // System.out.println(this.partition[0]);
        for (int i = 0; i < this.partition.length; i++) {
            
            if (this.partition[i] > prevPartitionIndex) {
                // Make box on existing start and end
                SimplifiedContextBox sbox = this.makeSimplifiedBox(start, end);
                this.simpleStack.push(sbox);
                start = i;
                end = start;
                prevPartitionIndex++;
            }
            else {
                end++;
            }
        }
        // Last one
        SimplifiedContextBox sbox = this.makeSimplifiedBox(start, end);
        this.simpleStack.push(sbox);
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

        NodeContextMessage first = this.fullStack.get(i);
        NodeContextMessage last = this.fullStack.get(j);
        
        // 1. Tree Order Trivial
        sbox.translationX = first.getTranslationX();
        sbox.higherOrderY = first.getHigherOrderY();

        // 2. Text Syntax (highlight later when rendering)
        sbox.textSyntax = first.getTextRepr();
        sbox.syntaxToHighlight = last.getTextRepr();

        // 3. Need some counting logic to keep track of unique indices
        this.SetAllProds();
        sbox.prodsVisited = Arrays.copyOfRange(this.productions, i, j + 1);

        // Make features list now (list, not array, since unknown length)
        sbox.features = new ArrayList<Feature>();
        this.fillInFeaturesList(sbox, i, j);
        
        return sbox;
    }


    // Helper to iterate through a sequence of NodeContextMessages within this.fullStack,
    // extract their labels, and create a Feature list.
    private void fillInFeaturesList(SimplifiedContextBox sbox, int i, int j) {
        
        for (int k = i; k <= j; k++) {
            NodeContextMessage node = this.fullStack.get(k);

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
        
        if (! this.needSetAllProds) {
            return;
        }
        // Only do this once 
        // Not worried about multiple instances of SimplifiedContextStack;
        // rather, don't want to do this for every SimplifiedContextBox created

        ProductionName allProds[] = new ProductionName[this.fullStack.getHeight()];

        for (int index = 0; index < this.fullStack.getHeight(); index++) {
            ProductionName pn = new ProductionName(this.fullStack.get(index).getProdName(), -1);
            allProds[index] = pn;
        }


        // Going to be fine with O(n^2) worst case time complexity here for now
        // Simply a prototype implementation of simplified stack

        // Unique prod names get the 0 index (not to be displayed)
        // Non-unique prod names are numbered from 1..n, so need extra
        // scan afterwards to separate unique 1 -> 0 from actual 1 
        for (int prodIndex = 0; prodIndex < allProds.length; prodIndex++) {
            
            int seqNum = 1;
            String name = allProds[prodIndex].name;
            
            for (int visitIndex = 0; visitIndex < prodIndex; visitIndex++) {
                String curName = allProds[visitIndex].name;
                if (curName.compareTo(name) == 0) {
                    seqNum++;
                }
            }
            allProds[prodIndex].index = seqNum;
        }

        // All prod indices >= 1. Find if need to differentiate 1 into 0 now
        // if truly unique and not start of 1..n sequence of indices
        for (int prodIndex = 0; prodIndex < allProds.length; prodIndex++) {
            
            if (allProds[prodIndex].index == 1) {
                boolean isUnique = true;
                for (int k = 0; k < allProds.length; k++) {
                    if (k != prodIndex && 
                        (allProds[k].name.compareTo(allProds[prodIndex].name) == 0)) {
                            isUnique = false;
                            break;
                        }
                }
                if (isUnique) {
                    allProds[prodIndex].index = 0;
                }
            }
        }

        this.productions = allProds;
        this.needSetAllProds = false;

        return;
    }


    // Determine partition of this.fullStack based on different headers.
    // All nodes of the same header go into one partition element.
    // Denote the partition by different sequential indices in the this.partition array.
    private void fillInPartition() {

        this.partition = new int[fullStack.getHeight()];

        int previousX = 0;
        int previousY = 0;
        int partitionIndex = 0;
        for (int i = 0; i < this.fullStack.getHeight(); i++) {
            NodeContextMessage node = this.fullStack.get(i);
            int curX = node.getTranslationX();
            int curY = node.getHigherOrderY();
            if (curX == previousX && curY == previousY) {
                // Within same set of partition
                this.partition[i] = partitionIndex;
            }
            else {
                previousX = curX;
                previousY = curY;
                // Increment partition index
                partitionIndex++;
                this.partition[i] = partitionIndex;
            }
        }
    }


    // The "full"/verbose stack holding the complete path of navigated-to nodes
    // from root to the current node in a stack (basically maintain DFS traversal).
    // Currently, nodes must be pushed/popped separately to fullStack first.
    // TODO. Merge fullStack navigation into this stack.
    private ContextStack fullStack;

    private String filename;
    private String filenameHTML;

    // Actual contextualization comes from file rendering of the simpleStack.
    private Stack<SimplifiedContextBox> simpleStack = 
        new Stack<SimplifiedContextBox>();
    
    private boolean needSetAllProds = true;
    private ProductionName productions[];

    // Put 0 for each respective element in the first 
    // stack partition, then 1, etc. Index 0 into stack is bottom
    private int[] partition;
    
}




