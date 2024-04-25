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


// SECTION 4. (labels). Labels represents whether the current node is involved with
// horizontal edges. Current possible labels are is-contractuma and is-redex 
// (for forwarding relationship) and is-attribute-root (for higher-order attribute subtree roots).
// Labels are currently extracted from DecoratedNode itself. 

// If showing intermediate full stack representation (ContextStack), then use GetSection_().
// If accessing stored information for a 

// TODO. Add a new contextualization label/header framework for REFERENCE ATTRIBUTES.  

// TODO. Simplify this class. Remove all GetSection_() methods and just use a stack of 
// NodeContextMessages as a store of information to make SimplifiedContextBoxes
//  directly in SimplifiedContextStack


public class NodeContextMessage {

    // String representations for each section.
    public String GetSection0() {
        return Integer.toString(this.numIndex);
    }

    public String GetSection1() {
        String res = "";
        boolean firstSet = false;
        if (this.translationX > 0) {
            res += "TRANSLATION-" + this.translationX;
            firstSet = true;
        }
        if (this.higherOrderY > 0) {
            if (firstSet) {
                res += " & ";
            }
            res += "HIGHER-ORDER-" + this.higherOrderY;
        }
        return res;
    }

    public String GetSection2() {
        return this.textRepr;
    }

    public String GetSection3() {
        
        String res = "prod: " + this.prodName + "\n" + 
            this.filename + " lines: " + this.fcStart.getRow() + 
            ":" + this.fcStart.getCol() + " -> " + this.fcEnd.getRow() + 
            ":" + this.fcEnd.getCol(); 
        return res;
    }

    // Not mutually exclusive labels
    public String GetSection4() {
        
        String res = "";
        if (this.isRedex) {
            res += "*is-redex\n";
        }
        if (this.isContractum) {
            res += "*is-contractum of " + this.contractumOf + "\n";
        }
        if (this.isAttributeRoot) {
            res += "*is-attribute_root\n";
        }
        return res;
    }

    // Constructor for NodeContextMessage
    public NodeContextMessage(DecoratedNode node, int numIndex) {
        
        // Set the current index. Keep track of it in the stack
        // to make decrementing on pop() easy
        this.numIndex = numIndex;

        // Necessary order.
    
        // Section 2
        this.prodName = node.getNode().getName();
        this.fillInRowsAndCols(node);

        // Section 4
        this.setLabels(node);
        
        // Section 1--headers depend on label attributes
        this.initializeHeaders(node);

        // Section 3. Determine file lines last
        //  because they depend on computing boolean attributes
        if ((this.translationX > 0) || (this.higherOrderY > 0) ||
            this.isAttributeRoot || this.isContractum) {
            // If headers are represent (non-"order" 0 node), its pretty print is used. 
            this.prettyPrint(node);
        }
        else {
            // Otherwise file times are used for the text/concrete syntax representation.
            this.pullFilelines();
        }
    }

    // Set translationX and higherOrderY bools here
    private void initializeHeaders(DecoratedNode node) {

        this.translationX = node.getIsTranslation();
        this.higherOrderY = node.getIsAttribute();
    }

    // Use file location method written in DecoratedNode
    private void fillInRowsAndCols(DecoratedNode node){
        
        this.fcStart = node.getStartCoordinates();
        this.fcEnd = node.getEndCoordinates();
        this.filename = node.getFilename();
    }

    // Labels currently only regard forwarding and higher-order attributes.
    private void setLabels(DecoratedNode node) {
       
        this.isRedex = node.getIsRedex();
        this.isContractum = node.getIsContractum();
        this.isAttributeRoot = node.getIsAttributeRoot();

        // Only relevant if these attributes are true
        this.contractumOf = this.numIndex - 1;
        this.attributeOf = this.numIndex - 1;
    }

    // access pp attribute if present  
    private void prettyPrint(DecoratedNode node) {
        
        this.textRepr = node.getPrettyPrint();
    }

    // Basically extract file lines from row x col y to 
    // row x' to y' for the two FileCoordinates this class stores.
    private void pullFilelines() {
       
        // Currently not the most efficient but should get the job done for now
       try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            
            int row = 1;
            int col = 1;
            String res = "";
            for (; row < this.fcStart.getRow(); row++) {
                // Skip these rows.
                String line = br.readLine();
            }
            // Advance to starting char
            for (; col < this.fcStart.getCol(); col++) {
                //Single char read
                // System.out.println("skipping col: " + col);
                int c = br.read(); 
            }

            // Now in correct row to start actually noting down file contents

            // Get whole lines here
            for (; row < this.fcEnd.getRow(); row++) {
                // System.out.println("reading row: " + row);
                res += br.readLine();
                res += '\n'; // Since not added with readLine()
            }
            // Now row = row.end
            for (; col <= this.fcEnd.getCol(); col++) {
                // System.out.println("reading col: " + col);
                char c = (char)br.read();
                res += Character.toString(c);
            }
            // Done. Can close file now
            br.close();

            // Just set filebytes (textRepr) to res
            this.textRepr = res;
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
    private int numIndex;

    // Section 1. Header will contain TRANSLATION and/or HIGHER-ORDER 
    public int getTranslationX() {
        return this.translationX;
    }
    private int translationX;

    public int getHigherOrderY() {
        return this.higherOrderY;
    }
    private int higherOrderY;
    
    // Section 2. Actual text representation 
    // (either copied from file or prity print (pp) represenation)
    public String getTextRepr() {
        return this.textRepr;
    }
    private String textRepr;
    
    // Section 3. Production name and file lines
    public String getProdName() {
        return this.prodName;
    }
    private String prodName;
    
    public String getFilenmae() {
        return this.filename;
    }
    private String filename;

    public FileCoordinate getFileCoordianteStart() {
        return this.fcStart;
    }
    private FileCoordinate fcStart;
    
    public FileCoordinate getFileCoordianteEnd() {
        return this.fcEnd;
    }
    private FileCoordinate fcEnd;


    // Section 4. Labels and associated info
    public boolean isRedex() {
        return isRedex;
    }
    private boolean isRedex;

    public boolean isContractum() {
        return isContractum;
    }
    private boolean isContractum;

    public int getContractumOf() {
        return contractumOf;
    }
    private int contractumOf;
    
    public boolean isAttributeRoot() {
        return isAttributeRoot;
    }
    private boolean isAttributeRoot;

    public int getAttributeOf() {
        return attributeOf;
    }
    private int attributeOf;

}