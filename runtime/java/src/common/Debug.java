//Thing to remeber: ./silver-compile --force-origins --clean

package common;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import silver.core.NLocation;
import silver.core.NMaybe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import common.Util.*;

import org.w3c.dom.Node;

import common.RTTIManager.Nonterminalton;

public class Debug {
    public static DecoratedNode runDebug(DecoratedNode tree)
    {
        Debug debug = new Debug();
        debug.runingDebug(tree);
        return tree;
    }

    public void runingDebug(DecoratedNode tree) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter characters, and 'q' to quit.");
        String userInput; 
        String[] userInputList;
        boolean toggleNameDisplay = false;
        boolean toggleCStackDisplay = true;
        boolean toggleHeadlessAttributes = false;
        String[] toggleChoices = {"prodDisplay", "cStackDisplay", "fullAttributeNames"};
        DecoratedNode childNode;
        this.root = tree;
        this.currentNode = tree;
        this.nodeStack = new Stack<DecoratedNode>();

        if(toggleNameDisplay){
            //printNames(currentNode);
            printName(currentNode);
        }

        // creating a context stack when we run the debugger
        // CMDContextVisualization cStack = new CMDContextVisualization("********************************");
        // if we want a file visualization:
        FileContextVisualization cStack = new FileContextVisualization("context.txt", "********************************");
        // if we want an HTML visualization:
        // HTMLContextVisualization cStack = new HTMLContextVisualization("********************************");
        cStack.push(currentNode);
        if(toggleCStackDisplay){
            cStack.show();
        }
    
        // Fine to call this
        ContextStack contextStack = (ContextStack)cStack.getContextStack();
        // System.out.println(cStack.getContextStack());

        // Need to debug why first line causes NullPointerException before SimplifiedContextStack constructor called
        SimplifiedContextStack sStack = new SimplifiedContextStack(contextStack);
        sStack.show();

        //Control loop
        loop: do { 
            System.out.print(">DEBUGGER-PROMPT$");
            userInput = inp.nextLine();
            userInputList = userInput.split(" ");

            //Each case has a set of conditionals to make everything is in order befor running
            //in the final case they call a helper function that does most of the work
            switch (userInputList[0]) {

                case "up": case "u": 
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: up<>");
                    }else{
                        if (currentNode.getParent().getParent() instanceof TopNode || currentNode.getParent() == null){
                            System.out.println("Root Node has no parent");
                        }else if (currentNode.getParent() == null){
                            System.out.println("Null parent");
                        }else{
                            nodeStack.push(currentNode);
                            currentNode = currentNode.getParent();
                            //System.out.println("going to parent");
                            if(toggleNameDisplay){
                                printName(currentNode);
                            }
                            // if we navigate up to a parent, push it on to the stack (?)
                            cStack.pop();
                            sStack.show();
                            // when we push, update and show the context
                            if(toggleCStackDisplay){
                                cStack.show();
                            }
                        }
                    }
                    break;

                case "down": case "d":  
                    int childNum = -1; 
                    if(currentNode.getNode().hasForward()){
                        System.out.println("can't go down on a forwarding node");
                        break;
                    }
                    else if (userInputList.length == 1) {
                        System.out.println("Which child?");
                        String currentProduction = currentNode.undecorate().getProdleton().getTypeUnparse();
                        String[] listCurrentProduction = currentProduction.split("\\s+");
                        String[] childNames =  Arrays.copyOfRange(listCurrentProduction, 2, listCurrentProduction.length);
                        childNum = chooseFormList(inp, childNames);
                        if(childNum == -1){
                            break;
                        }
                    }else if(userInputList.length == 2){
                        try{
                            childNum = Integer.parseInt(userInputList[1]);
                        }catch (NumberFormatException e) {
                            System.out.println("invalid, correct usage: down <node #>");
                            break;
                        }
                    }else{
                        System.out.println("invalid, correct usage: down <node #>");
                        break;
                    }

                    childNode = down(childNum);

                    if(childNode == null){
                        System.out.println("invalid child number");
                        break;
                    } 
                    else{
                        nodeStack.push(currentNode);
                        currentNode = childNode;
                        if(toggleNameDisplay){
                            printName(currentNode);
                        }
                        // if we navigate down to a child, push it on to the stack
                        cStack.push(currentNode);
                        sStack.show();
                        // when we push, update and show the context
                        if(toggleCStackDisplay){
                            cStack.show();
                        }
                    }
                    break;

                case "undo":
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: undo<>");
                    }else{
                        if(nodeStack.empty()){
                            System.out.println("invalid no node to undo");
                        } 
                        else{
                            DecoratedNode newNode = nodeStack.pop();
                            currentNode = newNode;
                            //System.out.println("undoing last movement");
                            if(toggleNameDisplay){
                                printName(currentNode);
                            }
                            // remove from the stack
                            cStack.pop();
                            sStack.show();
                            if(toggleCStackDisplay){
                                cStack.show();
                            }
                        }
                    }
                    break;

                //TODO: Works but known bug view is weird a forward nodes 
                //bug update: looks like the problem is with getSyhthized called in allAttributesObjectMap not sure how to fix
                case "forwards": 
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: forwards<>");
                    }else{
                        childNode = forwards(currentNode);
                        if(childNode == null){
                            System.out.println("invalid no node to forward");
                        }
                        else{
                            System.out.println("going forward");
                            currentNode = childNode;
                            if(toggleNameDisplay){
                                printName(currentNode);
                            }
                            // if we navigate to a forward, push it on to the stack
                            cStack.push(currentNode);
                            sStack.show();
                            // when we push, update and show the context
                            if(toggleCStackDisplay){
                                cStack.show();
                            }
                        }
                    }
                    break;

                
                //TODO: known bug, don't know how to represent higher order nodes as decoratedNodes
                case "into":
                    //A bit reptative right now but when I get a idea on how to list only the higer order nodes It will be better
                    String attributeNameinto = ""; 
                    Integer attributeNuminto = 0;
                    List<String> attributeListinto = allAttributesList(currentNode);
                    if (userInputList.length == 1) {
                        System.out.println("Which attribute?");
                        String[] attriburteArrayinto =  attributeListinto.toArray(new String[attributeListinto.size()]);
                        attributeNuminto = chooseFormList(inp, attriburteArrayinto);
                        if(attributeNuminto == -1){
                            break;
                        }else if(attributeNuminto >= attributeListinto.size()){
                            System.out.println("Invaild attribute number");
                            break;
                        }else{
                            attributeNameinto = attributeListinto.get(attributeNuminto);
                        }
                    }else if(userInputList.length == 2){
                            try{
                            attributeNuminto = Integer.parseInt(userInputList[1]);
                            attributeNameinto = attributeListinto.get(attributeNuminto);
                        }catch (NumberFormatException e) {
                            System.out.println("invalid, correct usage: view <node #>");
                            break;
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Index out of bounds");
                            break;
                        }
                    }else{
                        System.out.println("invalid, correct usage: into <node #>");
                        break;
                    }
                    childNode = into(currentNode, attributeNameinto);
                    if(childNode == null){
                        System.out.println("invalid input");
                    }
                    else{
                        System.out.println("going into");
                        currentNode = childNode;
                        // if(toggleNameDisplay){
                        //     printName(currentNode);
                        // }
                        // // if we navigate to a forward, push it on to the stack
                        // cStack.push(currentNode);
                        // // when we push, update and show the context
                        // cStack.show();
                    }
                    break;

                case "backtrack": 
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: backtrack<>");
                    }else{
                        childNode = backtrack(currentNode);
                        if(childNode == null){
                            System.out.println("invalid no node to backtrack to");
                        } 
                        else{
                            System.out.println("going backwrds");
                            currentNode = childNode;
                            if(toggleNameDisplay){
                                printName(currentNode);
                            }
                            // if we navigate backwards, pop (?)
                            cStack.pop();
                            sStack.show();
                            // when we push, update and show the context
                            if(toggleCStackDisplay){
                                cStack.show();
                            }
                        }
                    }
                    break;

                case "toggle":
                    String toggelChoice = "";
                    if (userInputList.length == 1) {
                        toggelChoice = toggleChoices[chooseFormList(inp, toggleChoices)];
                    }else if (userInputList.length == 2){
                        toggelChoice = userInputList[1];
                    } 
                    if(toggelChoice.equals("prodDisplay")){
                        if(toggleNameDisplay){
                            System.out.println("Production Display off");
                            toggleNameDisplay = false;
                        }else{
                            System.out.println("Production Display on");
                            toggleNameDisplay = true;
                        }
                    }else if(toggelChoice.equals("fullAttributeNames")){
                        if(toggleHeadlessAttributes){
                           System.out.println("Headless Attributes off");
                            toggleHeadlessAttributes = false;
                        }else{
                            System.out.println("Headless Attributes on");
                            toggleHeadlessAttributes = true;
                        }
                    }else if(toggelChoice.equals("cStackDisplay")){
                        if(toggleCStackDisplay){
                            System.out.println("cStack Display off");
                            toggleCStackDisplay = false;
                        }else{
                            System.out.println("cStack Display on");
                            toggleCStackDisplay = true;
                        }
                    }else{
                        System.out.println("legal toggles: prodDisplay, fullAttributeNames, cStackDisplay");
                    }
                    break;


                //Display the production
                case "prod": 
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: prod<>");
                    }else{
                        printProduction(currentNode);
                    }
                    break;
                
                //TODO:Implement this - use genericShow?, see ProductionDcl in grammer/silver/compiler/translation/java/core
                //Somehow this needs to create a new function for RTTI manager but current attempt does not work
                //We want - user defined names (ex: left right), attribute equations (ex: left = parent + 1) and nearby code (filename and linenumber?)
                case "eq": 
                    //A bit reptative right now but when I get a idea on how to list only the higer order nodes It will be better
                    String attributeNameView = ""; 
                    Integer attributeNumView = 0;
                    List<String> attributeListView = allAttributesList(currentNode);
                    if (userInputList.length == 1) {
                        System.out.println("Which attribute?");
                        String[] attriburteArrayView =  attributeListView.toArray(new String[attributeListView.size()]);
                        attributeNumView = chooseFormList(inp, attriburteArrayView);
                        if(attributeNumView == -1){
                            break;
                        }else if(attributeNumView >= attributeListView.size()){
                            System.out.println("Invaild attribute number");
                            break;
                        }else{
                            attributeNameView = attributeListView.get(attributeNumView);
                        }
                    }else if(userInputList.length == 2){
                            try{
                            attributeNumView = Integer.parseInt(userInputList[1]);
                            attributeNameView = attributeListView.get(attributeNumView);
                        }catch (NumberFormatException e) {
                            System.out.println("invalid, correct usage: view <node #>");
                            break;
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Index out of bounds");
                            break;
                        }
                    }else{
                        System.out.println("invalid, correct usage: into <node #>");
                        break;
                    }
                    printEquation(currentNode, attributeNameView);
                    // if(childNode == null){
                    //     System.out.println("invalid input");
                    // }
                    // else{
                    //     System.out.println("going into");
                    //     currentNode = childNode;
                    //     // if(toggleNameDisplay){
                    //     //     printName(currentNode);
                    //     // }
                    //     // // if we navigate to a forward, push it on to the stack
                    //     // cStack.push(currentNode);
                    //     // // when we push, update and show the context
                    //     // cStack.show();
                    // }
                    break;

                //List synthesized attributes
                case "listSynth": 
                    if (userInputList.length != 1 && userInputList.length != 2) {
                        System.out.println("invalid, correct usage: listSynth<node?>");
                    }else{
                        if(listSynth(currentNode) == 0){
                            System.out.println("no synthesized attributes");
                        }
                    }
                    break;
                
                //List inherited attributes
                case "listInher": 
                    if (userInputList.length != 1 && userInputList.length != 2) {
                        System.out.println("invalid, correct usage: listInher <node?>");
                    }else{
                        if(listInher(currentNode) == 0){
                            System.out.println("no inherited attributes");
                        }
                    }
                    break;

                //list all attributes
                case "list": case "l":
                    if (userInputList.length != 1 && userInputList.length != 2) {
                        System.out.println("invalid, correct usage: list<node?>");
                    }else{
                        // if(listSynth(currentNode) + listInher(currentNode) == 0){
                        //     System.out.println("no attributes");
                        // }
                        printAttributes(currentNode, toggleHeadlessAttributes);
                    }
                    break;

                //Show the values of a specific attribute
                //Clear the prefix that is identical
                //Print names of children not just types -- HARD!
                case "view": case "v": 
                    String attributeName = ""; 
                    Integer attributeNum = 0;
                    List<String> attributeList = allAttributesList(currentNode);
                    if (userInputList.length == 1) {
                        System.out.println("Which attribute?");
                        String[] attriburteArray =  attributeList.toArray(new String[attributeList.size()]);
                        attributeNum = chooseFormList(inp, attriburteArray);
                        if(attributeNum == -1){
                            break;
                        }else if(attributeNum >= attributeList.size()){
                            System.out.println("Invaild attribute number");
                            break;
                        }else{
                            attributeName = attributeList.get(attributeNum);
                        }
                    }else if(userInputList.length == 2){
                        try{
                            attributeNum = Integer.parseInt(userInputList[1]);
                            attributeName = attributeList.get(attributeNum);
                        }catch (NumberFormatException e) {
                            System.out.println("invalid, correct usage: view <node #>");
                            break;
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Index out of bounds");
                            break;
                        }
                    }else{
                        System.out.println("invalid, correct usage: view <node #>");
                        break;
                    }
                    //printAttrFromName(currentNode, attributeName);
                    printAllAttributeData(currentNode, attributeName);
                    break;


                case "local":
                    if (userInputList.length != 1 && userInputList.length != 2) {
                        System.out.println("invalid, correct usage: local <node?>");
                    }else{
                        List<String> listLocals = getLocalAttrs(currentNode);
                        if(listLocals.size() == 0){
                            System.out.println("no inherited attributes");
                        }else{
                            for (String localAttribute : listLocals){
                                System.out.println("Attribute = " + localAttribute);
                            }
                        }
                    }
                    break;

                case "help": 
                    if (userInputList.length == 1) {
                        System.out.println("call help with one of these keywords to see its functionality:");
                        System.out.println("toggle <feature>");
                        System.out.println("up");
                        System.out.println("down <node>");
                        System.out.println("view <attr>");
                        System.out.println("forwards");
                        System.out.println("backtrack");
                        System.out.println("prod");
                        System.out.println("eq");
                        System.out.println("listSynth");
                        System.out.println("listInher");
                        System.out.println("local");
                        System.out.println("list");
                        System.out.println("into");
                        System.out.println("exit");
                    }else if(userInputList.length == 2){
                        if(userInputList[1].equals("up")){
                            System.out.println("The current node changes to its the parent");
                        }else if(userInputList[1].equals("down")){
                            System.out.println("The current node changes to its child");
                            System.out.println("One optional input is the child number you want to travel to");
                            System.out.println("If no input is provided you will be prompted with a choice of child");
                            System.out.println("You cn call this function with \"d\"");
                        }else if(userInputList[1].equals("view")){
                            System.out.println("look at the value of an attribute in the current node");
                            System.out.println("One optional input is the attribute number you want to view");
                            System.out.println("If no input is provided you will be prompted with a choice of attribute");
                            System.out.println("You can call this function with \"v\"");
                        }else if(userInputList[1].equals("forwards")){
                            System.out.println("The current node changes to its forward");
                        }else if(userInputList[1].equals("backtrack")){
                            System.out.println("The current node changes to its backtrack");
                        }else if(userInputList[1].equals("prod")){
                            System.out.println("prints the production of the current node");
                        }else if(userInputList[1].equals("eq")){
                            System.out.println("prints the equation of the current node");
                        }else if(userInputList[1].equals("listSynth")){
                            System.out.println("prints the Synthisized attributes of the current node");
                        }else if(userInputList[1].equals("listInher")){
                            System.out.println("prints the inherited attributes of the current node");
                        }else if(userInputList[1].equals("list")){
                            System.out.println("prints the attributes of the current node");
                        }else if(userInputList[1].equals("local")){
                            System.out.println("prints the local attributes of the current node");
                        }else if(userInputList[1].equals("into")){
                            System.out.println("The current node changes to its higer order attribute");
                            System.out.println("One optional input is the attribute number you want to go into");
                            System.out.println("If no input is provided you will be prompted with a choice of attribute");
                        }else if(userInputList[1].equals("toggle")){
                            System.out.println("Activate or disactivate a feature");
                            System.out.println("One input is the feature number you want to toggle");
                            System.out.println("If no input is provided you will be prompted with a choice of toggles");
                        }else{
                            System.out.println("try just calling help");
                        }
                    }else{
                        System.out.println("try just calling help");

                    }
                    break;

                //Many ways to leave
                case "exit": 
                case "q": 
                case "quit": 
                    System.out.println("debugger out");
                    break loop;
                default: 
                    System.out.println("invalid input call help for legal inputs");
                    break;
            }
        } while(true); 
    }
    private DecoratedNode root;
    private DecoratedNode currentNode;
    private Stack<DecoratedNode> nodeStack;
    HashMap<Integer, StringObjectPair> currentNodeSynthAttrs;
    HashMap<Integer, StringObjectPair> currentNodeInhAttrs;
    HashMap<Integer, StringObjectPair> currentNodeLocalAttrs;
    private int currentLine;
    private int currentColumn;

    public void setCurrentNode(DecoratedNode node)
    {
        currentNodeSynthAttrs = null;
        currentNodeInhAttrs = null;
        currentNodeLocalAttrs = null;
        currentNode = node;
    }

    public DecoratedNode up()
    {
        if (currentNode.getParent() != null)
        {
            currentNode = (DecoratedNode) currentNode.getParent();
            return currentNode;
        }
        return null;
    }

    public DecoratedNode down(int child)
    {
        String child_productions[] = currentNode.undecorate().getProdleton().getChildTypes();
        try{
            if(child_productions[child].equals("null")){ 
                return null;
            }
            DecoratedNode childNode = currentNode.childDecorated(child);
            return childNode;
        }catch(NullPointerException e){
            System.out.println("Null pointer");
            return null;
        }catch(IndexOutOfBoundsException e){
            System.out.println("Index out of bound");
            return null;
        }
        //if (currentNode.getNode().getNumberOfChildren() > child && child >= 0){
            //Might be a bandaid fix not sure I understand why the problem exists
        //     DecoratedNode childNode = currentNode.childDecorated(child);
        //     return childNode;
        // //}
        // return null;
    }

    public void printChildren(DecoratedNode node)
    {
        String child_productions[] = node.undecorate().getProdleton().getChildTypes();
        for (int i = 0; i < child_productions.length; i++){
            System.out.println(Integer.toString(i) + ": " + child_productions[i] + " ");
        } 
    }

    public DecoratedNode forwards(DecoratedNode node)
    {
        if (node.getNode().hasForward()){
            currentNode = node.forward();
            return currentNode;
        }
        return null;
    }
    public DecoratedNode backtrack(DecoratedNode node)
    {
        currentNode = node.getForwardParent();
        return currentNode;

    }
    
    public void printProduction(DecoratedNode node)
    {
        String partent_production = node.undecorate().getProdleton().getTypeUnparse();
        // String child_productions[] = node.undecorate().getProdleton().getChildTypes();
        System.out.print(partent_production + "\n");
        // for (int i = 0; i < child_productions.length; i++){
        //     System.out.print(child_productions[i] + " ");
        // }
        // System.out.print("\n");
    }
    
    public void printName(DecoratedNode node)
    {
        String name = node.undecorate().getProdleton().getName();
        System.out.println(name);
    }

    // public void printNames(DecoratedNode node)
    // {
    //     String partent_production = node.undecorate().getProdleton().getName();
    //     String child_productions[] = node.undecorate().getProdleton().getChildNames();
    //     System.out.print(partent_production + " ");
    //     for (int i = 0; i < child_productions.length; i++){
    //         System.out.print(child_productions[i] + " ");
    //     }
    //     System.out.print("\n");
    // }

    //TODO: file for the production you are on
    //Also a file with list of attribute values
    //Optional: Replace variables with values )_)
    //ask lucas for endline

    // Prints out the equation of the specified attr.
    // If attr is not specified, prints out the equations for all the attributes on the current node.
    // via eric we can just add equations as an attribute within our AG
    public void printEquation(DecoratedNode node, String attriburteName)
    {
        Map<String, Lazy> lazyMap = allAttributesLazyMap(node);
        if (lazyMap.containsKey(attriburteName)) {
            // System.out.println("In print Equation function");
            Lazy attributeLazy = lazyMap.get(attriburteName);
            NLocation loc = attributeLazy.getSourceLocation();
            String notes = "";
            String qualifier = Integer.toHexString(System.identityHashCode(this));
            if(loc != null) {
                // Object filenameObj = loc.synthesized(silver.core.Init.silver_core_filename__ON__silver_core_Location);
                // Class<?> synthesizedClass = filenameObj.getClass();
                // System.out.println("Type of synthesized object: " + synthesizedClass.getName());

                String file = loc.synthesized(silver.core.Init.silver_core_filename__ON__silver_core_Location).toString();

                int line = (Integer)loc.synthesized(silver.core.Init.silver_core_line__ON__silver_core_Location);
                int col = (Integer)loc.synthesized(silver.core.Init.silver_core_column__ON__silver_core_Location);
                // System.out.println("our line, col: " + line + "," + col);
                // System.out.println("our filename: " + file);
                printContent(file, line, col);
                //qualifier += ", " + file + ":" + Integer.toString(line) + ":" + Integer.toString(col);
            }
            if(!notes.isEmpty()) {
                qualifier += ", " + notes;
            }
            //Lazy attributeObject = attributeMap.get(attriburteName);
        }
        //System.out.println("made it here at least");
    }

    public static void printContent(String filename, int lineNumber, int col) {
        //System.out.println("in print content");
        try (BufferedReader br1 = new BufferedReader(new FileReader(filename));
            BufferedWriter writer = new BufferedWriter(new FileWriter("current_production.html"))) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<body>\n");
            // System.out.println("in try");
            String line;
            int currentLineNumber = 1;
            int productionLineNum = 0;
            while ((line = br1.readLine()) != null) {
                if (line.contains("::=")) {
                    productionLineNum = currentLineNumber; // Stop printing when encountering the line containing "}"
                }
                if (currentLineNumber >= lineNumber ) {
                    break;
                }
                currentLineNumber++;
            }
            currentLineNumber = 1;
            br1.close();
            BufferedReader br2 = new BufferedReader(new FileReader(filename));
            writer.write("<pre>\n");
            while ((line = br2.readLine()) != null) {
                if (currentLineNumber >= productionLineNum && currentLineNumber != lineNumber) {
                    writer.write(line);
                    writer.newLine();
                }else if(currentLineNumber == lineNumber){
                    writer.write("<span style=\"color: red;\"><strong>");
                    writer.write(line);
                    writer.write("</strong></span>");
                    writer.newLine();
                }
                if (currentLineNumber >= productionLineNum && line.trim().equals("}")) {
                    break; // Stop printing when encountering the line exactly containing "}"
                }
                currentLineNumber++;
            }
            writer.write("</pre>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
        }catch (IOException e) {
            System.out.println("in catch");
            e.printStackTrace();
        }
    }

    public void eqSynth(int attribute)
    {

    }

    public void eqInher(int attribute)
    {

    }

    // no optional params in java, could use overloading or just pass in null
    public int listSynth(DecoratedNode node)
    {
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        Set<String> synAttrSet = nonterminalton.getAllSynth();
        int num_attr = 0;

        for (String synAttr : synAttrSet)
        {
            System.out.println("Attribute = " + synAttr);
            num_attr++;
        }
        return num_attr;
    }

    public int listInher(DecoratedNode node)
    {
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        Set<String> inhAttrSet = nonterminalton.getAllInh();
        int num_attr = 0;

        for (String inhAttr : inhAttrSet)
        {
            System.out.println("Attribute = " + inhAttr);
            num_attr++;
        }
        return num_attr;
    }   

    public void printAttributes(DecoratedNode node, boolean toggleHeadlessAttributes){
        List<String> attributeList = allAttributesList(node);
        if(toggleHeadlessAttributes){
            attributeList = removeHeaders(allAttributesList(node));
        }
        int i = 0;

        for (String attribute : attributeList)
        {
            System.out.println(Integer.toString(i) + ": " + attribute);
            i++;
        }
    }

    //Should this be in Util?
    public List<String> removeHeaders(List<String> stringList){
        List<String> headlessList = new ArrayList<>();
        for (String element : stringList){
            int lastIndex = element.lastIndexOf(":");
            if(lastIndex == -1){
                headlessList.add(element);
            }else{
                headlessList.add(element.substring(lastIndex + 1));
            }
        }
        return headlessList;
    }

    public void printAttrFromName(DecoratedNode node, String printAttribute){
        //Map<String, Object> attributeMap = allAttributesThunkMap(node);
        Map<String, Object> attributeMap = allAttributesObjectMap(node);
        // @SuppressWarnings("unchecked")
        //TODO: fix known bug can't access the same thunk twice
        // Thunk finalTunk = (Thunk<Object>) attributeMap.get(printAttribute);
        // System.out.println(Util.genericShow(finalTunk.eval()));

        System.out.println(Util.genericShow(attributeMap.get(printAttribute)));
    }

    public void printAllAttributeData(DecoratedNode node, String printAttribute){
        //Map<String, Object> attributeMap = allAttributesThunkMap(node);
        Map<String, Object> attributeMap = allAttributesObjectMap(node);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attribute_values.html"))) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<body>\n");
            writer.write("<pre>\n");
            for (Map.Entry<String, Object> entry : attributeMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.equals(printAttribute)){
                    writer.write("<span style=\"color: red;\"><strong>");
                }
                writer.write(key + ": " + Util.genericShow(value));
                if (key.equals(printAttribute)){
                    writer.write("</strong></span>");
                }
                writer.newLine();
            }
            writer.write("</pre>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");

        }catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    //List of all and only local attributes
    public static List<String> getLocalAttrs(DecoratedNode node)
    {
        int count = node.getNode().getNumberOfLocalAttrs();
        List<String> listLocals = new ArrayList<>();

        for(int i = 0; i < count; i++)
        {
            Lazy attribute = node.getNode().getLocal(i);
            Object o = attribute.eval(node);
            listLocals.add(node.getNode().getNameOfLocalAttr(i));
        }
        return listLocals;
    }

    //TODO: Add access to higher order attriburte
    // Translation attribute or Decorated, locals only locals should all be decorated
    public DecoratedNode into(DecoratedNode node, String attriburteName){
        Map<String, Object> attributeMap = allAttributesObjectMap(node);
        if (attributeMap.containsKey(attriburteName)) {
            System.out.println("In into function");
            Object attributeObject = attributeMap.get(attriburteName);
            return (DecoratedNode) attributeObject; //Does not work class translator.Pprogram cannot be cast to class common.DecoratedNode
        }
        return null;
    }
    

    //Helper for printAttrFromName 
    public static List<String> allAttributesList(DecoratedNode node)
    {
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        List<String> attributeList = nonterminalton.alphabeticalAttributes();
        List<String> localAttributeList = getLocalAttrs(node);

        attributeList.addAll(localAttributeList);
        attributeList.sort(null);

        return attributeList;
    }

    // Another Helper 
    public static Map<String, Object> allAttributesObjectMap(DecoratedNode node)
    {
        List<String> attributeList = allAttributesList(node);
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        Map<String, Object> attributeMap = new HashMap<>();

        for(String attribute : attributeList)
        {
            if(nonterminalton.getSynOccursIndices().keySet().contains(attribute)){
                //System.out.println("Synthisized!!! \"" + attribute + "\"");
                Integer index = nonterminalton.getSynOccursIndex(attribute);
                Lazy synthAttribute = node.getNode().getSynthesized(index); //breaks for forwarded nodes
                Object o = synthAttribute.eval(node); //.sythisized() found in Decorated node add thunks (.sythisizedlazy() then eval )
                attributeMap.put(attribute, o);
            }else if(nonterminalton.getInhOccursIndices().keySet().contains(attribute)){
                //System.out.println("Inherited!!!");
                Integer index = nonterminalton.getInhOccursIndex(attribute);
                Object o = node.evalInhSomehowButPublic(index);
                attributeMap.put(attribute, o);
            }else{ //Should be local
                //System.out.println("local!!!");
                List<String> listLocals = getLocalAttrs(node);
                Integer index = listLocals.indexOf(attribute);
                Lazy localAttribute = node.getNode().getLocal(index);
                Object o = localAttribute.eval(node);
                attributeMap.put(attribute, o);
            }
        }
        return attributeMap;
    }

    //TODO: replace all uses of allAttributesObjectMap with this once it works (see known bug on printAttrFromName)
    public static Map<String, Object> allAttributesThunkMap(DecoratedNode node)
    {
        List<String> attributeList = allAttributesList(node);
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        Map<String, Object> attributeMap = new HashMap<>();

        for(String attribute : attributeList)
        {
            if(nonterminalton.getSynOccursIndices().keySet().contains(attribute)){
                Integer index = nonterminalton.getSynOccursIndex(attribute);
                Object o = node.contextSynthesizedLazy(index); 
                attributeMap.put(attribute, o);
            }else if(nonterminalton.getInhOccursIndices().keySet().contains(attribute)){
                Integer index = nonterminalton.getInhOccursIndex(attribute);
                Object o = node.contextInheritedLazy(index); 
                attributeMap.put(attribute, o);
            }else{ //Should be local
                List<String> listLocals = getLocalAttrs(node);
                Integer index = listLocals.indexOf(attribute);
                Object o = node.localLazy(index);
                attributeMap.put(attribute, o);
            }
        }
        return attributeMap;
    }

    
    public static Map<String, Lazy> allAttributesLazyMap(DecoratedNode node)
    {
        List<String> attributeList = allAttributesList(node);
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        Map<String, Lazy> attributeMap = new HashMap<>();

        for(String attribute : attributeList)
        {
            if(nonterminalton.getSynOccursIndices().keySet().contains(attribute)){
                Integer index = nonterminalton.getSynOccursIndex(attribute);
                Lazy synthAttribute = node.getNode().getSynthesized(index); //breaks for forwarded nodes
                attributeMap.put(attribute, synthAttribute);
            }else if(nonterminalton.getInhOccursIndices().keySet().contains(attribute)){
                Integer index = nonterminalton.getInhOccursIndex(attribute);
                Lazy inheritedAttribute = node.getInheritedAttribute(index);
                attributeMap.put(attribute, inheritedAttribute);
            }else{ //Should be local
                List<String> listLocals = getLocalAttrs(node);
                Integer index = listLocals.indexOf(attribute);
                Lazy localAttribute = node.getNode().getLocal(index);
                attributeMap.put(attribute, localAttribute);
            }
        }
        return attributeMap;
    }


    public static Integer chooseFormList(Scanner inp, String[] list){
        for (int i = 0; i < list.length; i++){
            System.out.println(Integer.toString(i) + ": " + list[i]);
        } 

        boolean continueLoop = true;
        int returnInt = -1;
        String stopper = "";
        while(continueLoop){
            System.out.print(">DEBUGGER-PROMPT$");
            if (inp.hasNextInt()) {
                returnInt = inp.nextInt();
                inp.nextLine();
                continueLoop = false;
            }else{
                stopper = inp.nextLine();
                if (stopper.equals("e")){
                    continueLoop = false;
                }else{
                    System.out.println("Please choose an integer or e to exit");
                }
            }
        }
        return returnInt;

    }
     
    public boolean isContractum(DecoratedNode node)
    {
        return node.getNode().hasForward();
    }

    public static class StringObjectPair {
        private String stringValue;
        private Object objectValue;

        public StringObjectPair(String stringValue, Object objectValue) {
            this.stringValue = stringValue;
            this.objectValue = objectValue;
        }

        public String getString() {
            return stringValue;
        }

        public Object getObject() {
            return objectValue;
        }
    }
}
