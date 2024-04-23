// Auto uptade view and eq as we move 

//needed to run: ./silver-compile --force-origins --clean
package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
        //When Debugg is run runingDebug is called and does most the work
        debug.runingDebug(tree);
        return tree;
    }

    public void runingDebug(DecoratedNode tree) {
        //IO variables
        Scanner inp = new Scanner(System.in);
        String userInput; 
        String[] userInputList;

        //Variables for termainal displays
        this.toggleNameDisplay = false;
        this.toggleCStackDisplay = true;
        this.toggleHeadlessAttributes = false;
        this.toggleChoices = new String[] {"nameDisplay", "cStackDisplay", "fullAttributeNames"};

        //A few Place holder variable
        DecoratedNode childNode;
        this.root = tree;
        this.currentNode = tree;

        //This stack is used for the undo command
        this.nodeStack = new Stack<DecoratedNode>();

        // These stacks are important for the file visualization
        this.cStack = new FileContextVisualization("context.txt", "********************************");
        cStack.push(currentNode);
        this.contextStack = (ContextStack)cStack.getContextStack();
        this.sStack = new SimplifiedContextStack(contextStack);
        sStack.generateHTMLFile();

        //Start of display
        System.out.println("Enter characters, and 'q' to quit.");
        if(toggleCStackDisplay){
            cStack.show();
        }
        if(toggleNameDisplay){
            printName(currentNode);
        }

        //Main Control loop
        loop: do { 
            //After each command is done the user is prompted again
            System.out.print(">DEBUGGER-PROMPT$");
            userInput = inp.nextLine();
            userInputList = userInput.split(" ");

            //The basic structure is we check if the user input matches any expected input
            //If so we call the corresponding function where the work is done
            //Most IO is done in this function and most logic is done in the auxiliary functions
            switch (userInputList[0]) {

                //used for going to a parent of the current node
                case "up": case "u": 

                    //up should not have any arguments
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: up");
                        break;
                    }

                    //If so we can go to that node
                    if (up() == -1)
                        break;

                    //Update the json and html and terminal to the new node
                    updateDisplay();
                    break;

                //Used to navigate to children
                case "down": case "d":  
                    String childName = "";

                    //If the user does not provide a child we should list them out
                    if (userInputList.length == 1) {
                        String currentProduction = currentNode.undecorate().getProdleton().getTypeUnparse();
                        String[] listCurrentProduction = currentProduction.split("\\s+");
                        String[] childNames = Arrays.copyOfRange(listCurrentProduction, 2, listCurrentProduction.length);
                        System.out.println("Which child?");
                        displayArray(childNames);
                        System.out.print(">DEBUGGER-PROMPT$");
                        childName = inp.nextLine();
                    }

                    //Otherwise they should have typed one in
                    else if(userInputList.length == 2){
                        childName = userInputList[1];
                    }
                    
                    //We do not expect more than 2 arguments for down
                    else{
                        System.out.println("invalid, correct usage: down <node name>");
                        break;
                    }

                    //Now that we have the childname we can try to go to it
                    try{
                        if(down(childName) == -1)
                            System.out.println("invalid child");
                    }catch(NullPointerException e){
                        System.out.println("Null pointer");
                        break;
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("Index out of bound");
                        break;
                    }    

                    //Update the json and html and terminal to the new node
                    updateDisplay();
                    break;

                //used to go back to the last node traked on nodeStack
                case "undo":
                    //undo should not have any arguments
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: undo");
                        break;
                    }

                    if(undo() == -1)
                        break;

                    //Update the json and html and terminal to the new node
                    updateDisplay();
                    break;

                //used to access forward nodes
                case "forwards": case "f":
                    //forwards should not have any arguments
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: forwards<>");
                        break;
                    }

                    //try to move forward
                    if(forwards() == -1)
                        break;

                    //Update the json and html and terminal to the new node
                    updateDisplay();
                    break;

                //used to access parents that forwarded to this node
                case "backtrack": case "backwards": case "b":
                    //backtrack should not have any arguments
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: backtrack<>");
                        break;
                    }

                    //try to move backwards
                    if(backtrack() == -1)
                        break;

                    //Update the json and html and terminal to the new node
                    updateDisplay();
                    break;

                //Used to specify what displays the user want to see
                case "toggle":
                    String toggleInput = "";

                    //If the user does not provide a child we should list them out
                    if (userInputList.length == 1) {
                        System.out.println("Which toggle?");
                        displayArray(toggleChoices);
                        System.out.print(">DEBUGGER-PROMPT$");
                        toggleInput = inp.nextLine();
                    }
                    
                    //Otherwise they should have typed one in
                    else if (userInputList.length == 2){
                        toggleInput = userInputList[1];
                    } 

                    //We do not expect more than 2 arguments for toggle 
                    else{
                        System.out.println("invalid, correct usage: toggle <toggle name>");
                        break;
                    }

                    //Activate the toggle
                    toggle(toggleInput);
                    break;

                //used to display the production name on the terminal
                case "name": 
                    
                    //name should not have any arguments
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: name");
                        break;
                    }

                    //Print the name
                    printName(currentNode);
                    break;

                //used to jump to a specific equation
                case "equation": case "eq": 
                    String attributeNameInput = ""; 

                    //If the user does not provide a attribute we should list them out
                    if (userInputList.length == 1) {
                        List<String> attributeListView = allAttributesList(currentNode);
                        String[] attributeArrayView = attributeListView.toArray(new String[attributeListView.size()]);
                        System.out.println("Which attribute?");
                        displayArray(attributeArrayView);
                        System.out.print(">DEBUGGER-PROMPT$");
                        attributeNameInput = inp.nextLine();
                    }
                    
                    //Otherwise they should have typed one in
                    else if(userInputList.length == 2){
                        attributeNameInput = userInputList[1];
                    }

                    //We do not expect more than 2 arguments for eq
                    else{
                        System.out.println("invalid, correct usage: eq <node name>");
                        break;
                    }

                    try{
                        if(equationHelper(attributeNameInput) == -1)
                            System.out.println("invalid attribute");
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("Index out of bounds");
                        break;
                    }
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
                    printAttrFromName(currentNode, attributeName);
                    attributeDataHTML(currentNode, attributeName);
                    break;

                case "algoDebugg": case "a": 
                    attributeName = ""; 
                    attributeNum = 0;
                    attributeList = allAttributesList(currentNode);
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
                    algorithmicDebugg(currentNode, attributeName, inp);
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

                //TODO: known bug, don't know how to represent higher order nodes as decoratedNodes
                //TODO: Implemeting this funtion could be done in future work
                // case "into":
                //     //A bit reptative right now but when I get a idea on how to list only the higer order nodes It will be better
                //     String attributeNameinto = ""; 
                //     Integer attributeNuminto = 0;
                //     List<String> attributeListinto = allAttributesList(currentNode);
                //     if (userInputList.length == 1) {
                //         System.out.println("Which attribute?");
                //         String[] attriburteArrayinto =  attributeListinto.toArray(new String[attributeListinto.size()]);
                //         attributeNuminto = chooseFormList(inp, attriburteArrayinto);
                //         if(attributeNuminto == -1){
                //             break;
                //         }else if(attributeNuminto >= attributeListinto.size()){
                //             System.out.println("Invaild attribute number");
                //             break;
                //         }else{
                //             attributeNameinto = attributeListinto.get(attributeNuminto);
                //         }
                //     }else if(userInputList.length == 2){
                //             try{
                //             attributeNuminto = Integer.parseInt(userInputList[1]);
                //             attributeNameinto = attributeListinto.get(attributeNuminto);
                //         }catch (NumberFormatException e) {
                //             System.out.println("invalid, correct usage: view <node #>");
                //             break;
                //         }catch (IndexOutOfBoundsException e){
                //             System.out.println("Index out of bounds");
                //             break;
                //         }
                //     }else{
                //         System.out.println("invalid, correct usage: into <node #>");
                //         break;
                //     }
                //     childNode = into(currentNode, attributeNameinto);
                //     if(childNode == null){
                //         System.out.println("invalid input");
                //     }
                //     else{
                //         System.out.println("going into");
                //         currentNode = childNode;
                //         // if(toggleNameDisplay){
                //         //     printName(currentNode);
                //         // }
                //         // // if we navigate to a forward, push it on to the stack
                //         // cStack.push(currentNode);
                //         // // when we push, update and show the context
                //         // cStack.show();
                //     }
                //     break;

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
    private FileContextVisualization cStack;
    private ContextStack contextStack;
    private SimplifiedContextStack sStack;
    HashMap<Integer, StringObjectPair> currentNodeSynthAttrs;
    HashMap<Integer, StringObjectPair> currentNodeInhAttrs;
    HashMap<Integer, StringObjectPair> currentNodeLocalAttrs;
    private int currentLine;
    private int currentColumn;
    private boolean toggleNameDisplay;
    private boolean toggleCStackDisplay;
    private boolean toggleHeadlessAttributes;
    private String[] toggleChoices;

    public void setCurrentNode(DecoratedNode node)
    {
        currentNodeSynthAttrs = null;
        currentNodeInhAttrs = null;
        currentNodeLocalAttrs = null;
        currentNode = node;
    }

    //Replaces currentNode with its parent
    public Integer up()
    {

        //Make sure there is a parent to go up to 
        if (currentNode.isRoot()){
            System.out.println("Root Node has no parent");
            return -1;
        }

        //NodeStack contains past nodes not current ones
        nodeStack.push(currentNode);

        //Updates curretnNode
        currentNode = (DecoratedNode) currentNode.getParent();
        
        //Update the various other stacks
        cStack.pop();
        sStack.generateHTMLFile();
        return 1;
    }

    //HACK: This function currently get the file line by going through the first attribute
    //This means it will not work if there is no attribute. For future work it would be nice  
    //if nodes could access the file and have there own file line.
    public void updateDisplay()
    {
        //First we want to update the json to point at the top of the current production
        try{
            List<String> attributeList = allAttributesList(currentNode);
            Map<String, Lazy> lazyMap = allAttributesLazyMap(currentNode);
            Lazy attributeLazy = lazyMap.get(attributeList.get(0));
            //FIXME: Known bug breaks after we forward
            NLocation loc = attributeLazy.getSourceLocation();
            if(loc != null) {
                String file = loc.synthesized(silver.core.Init.silver_core_filename__ON__silver_core_Location).toString();
                int attributeLine = (Integer)loc.synthesized(silver.core.Init.silver_core_line__ON__silver_core_Location);
                int currentLineNumber = 1;
                int productionLineNum = 0;
                    
                try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                    String line;
                    while ((line = reader.readLine()) != null) {
                        //HACK:Relies on the the fact that "::=" is only and always used in production declarations
                        if (line.contains("::=")) {
                            productionLineNum = currentLineNumber; 
                        }
                        if (currentLineNumber >= attributeLine) {
                            break;
                        }
                        currentLineNumber++;
                    }

                    writeTojson(file, productionLineNum, productionLineNum);
                    // add a client server here, when it called send 1
                    sendMessageToExtension("1");
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }  
        }
        catch(NullPointerException e)
        {
            System.out.println("Failed to update json");
        }
        

        //Next we want to update the html file with the attribute values
        Map<String, Object> attributeMap = allAttributesThunkMap(currentNode);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attribute_values.html"))) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<body>\n");
            writer.write("<pre>\n");
            for (Map.Entry<String, Object> entry : attributeMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if(value instanceof Thunk){
                    writer.write(key + ": THUNKING...");
                }else{
                    writer.write(key + ": " + Util.genericShow(value));
                }
                writer.newLine();
            }
            writer.write("</pre>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
        }catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        //Finally Print all information the user wants
        if(toggleNameDisplay){
            printName(currentNode);
        }
        if(toggleCStackDisplay){
            cStack.show();
        }
    }

    //Given a child name (or prefix) updates the currentNode to that child
    public Integer down(String childName)
    {
        //Find the index of the given childName
        String currentProduction = currentNode.undecorate().getProdleton().getTypeUnparse();
        String[] listCurrentProduction = currentProduction.split("\\s+");
        String[] childNames = Arrays.copyOfRange(listCurrentProduction, 2, listCurrentProduction.length);
        int childNum = Arrays.binarySearch(childNames, childName);

        //If the child was not found we check if the input was a prefix
        if(childNum < 0){
            childNum = prefixSearch(childNames, childName);
        }

        //Try to return the child at the corresponding index
        String childProductions[] = currentNode.undecorate().getProdleton().getChildTypes();
        if(childProductions[childNum].equals("null")){ 
            return -1;
        }
        nodeStack.push(currentNode);
        currentNode = currentNode.childDecorated(childNum);

        // if we navigate down to a child, push it on to the stacks
        cStack.push(currentNode);
        sStack.generateHTMLFile();
        return 1;
    }

    //helper for finding a element with a specific prefix 
    public Integer prefixSearch(String[] array, String prefix)
    {
         for (int i = 0; i < array.length; i++) {
            if (array[i].startsWith(prefix)) {
                return i; 
            }
        }
        return -1;
    }

    //will go back in the stack
    public Integer undo(){
        //Can't undo if no nodes have been visited 
        if(nodeStack.empty()){
            System.out.println("invalid no node to undo");
            return -1;
        } 

        //Now we can undo by just calling on the stack we tracked for this purpose
        DecoratedNode newNode = nodeStack.pop();
        currentNode = newNode;
        
        //Update all stacks
        cStack.pop();
        sStack.generateHTMLFile();

        return 1;
    }

    //updates the currentNode to its forward child
    public Integer forwards()
    {
        if (currentNode.getNode().hasForward()){
            nodeStack.push(currentNode);
            currentNode = currentNode.forward();

            // if we navigate to a forward, push it on to the stack
            cStack.push(currentNode);
            sStack.generateHTMLFile();
            return 1;
        }
        System.out.println("invalid no node to forward");
        return -1;
    }

    //updates the currentNode to its backwards parent
    public Integer backtrack()
    { 
        DecoratedNode nextNode = currentNode.getForwardParent();
        if(nextNode == null){
            System.out.println("invalid no node to backtrack to");
            return -1;
        }
        nodeStack.push(currentNode);
        currentNode = nextNode;

        // if we navigate backwards, pop (?)
        cStack.pop();
        sStack.generateHTMLFile();

        return 1;
    }

    //Just an organizational function, turns toggles on/off
    public Integer toggle(String toggleInput){
        String toggleChoice = "";

        //Given the toggleInput we can find the toggleChoice
        if(Arrays.asList(toggleChoices).contains(toggleInput)){
            toggleChoice = toggleInput;
        }else{
            int toggleNum = prefixSearch(toggleChoices, toggleInput);
            if(toggleNum > -1){
                toggleChoice = toggleChoices[toggleNum];
            }else{
                System.out.println("No such toggle");
                return -1;
            }
        }

        //finally we can update the corresponding toggle
        if(toggleChoice.equals("nameDisplay")){
            if(toggleNameDisplay){
                System.out.println("Production Display off");
                toggleNameDisplay = false;
            }else{
                System.out.println("Production Display on");
                toggleNameDisplay = true;
            }
        }else if(toggleChoice.equals("fullAttributeNames")){
            if(toggleHeadlessAttributes){
                System.out.println("Headless Attributes off");
                toggleHeadlessAttributes = false;
            }else{
                System.out.println("Headless Attributes on");
                toggleHeadlessAttributes = true;
            }
        }else if(toggleChoice.equals("cStackDisplay")){
            if(toggleCStackDisplay){
                System.out.println("cStack Display off");
                toggleCStackDisplay = false;
            }else{
                System.out.println("cStack Display on");
                toggleCStackDisplay = true;
            }
        }else{
            System.out.println("legal toggles: nameDisplay, fullAttributeNames, cStackDisplay");
        }
        return 1;
    }
    
    //User friendly print
    public void printName(DecoratedNode node)
    {
        String parentProduction = node.undecorate().getProdleton().getTypeUnparse();
        System.out.println(parentProduction);
    }

    //calls 
    public Integer equationHelper(String attributeName)
    {
        //Find the index of the given childName
        List<String> attributeList = allAttributesList(currentNode);
        String[] attributeArray = attributeList.toArray(new String[attributeList.size()]);
        int attributeNum = Arrays.binarySearch(attributeArray, attributeName);

        //If the attribute was not found we check if the input was a prefix
        if(attributeNum < 0){
            attributeNum = prefixSearch(attributeArray, attributeName);
        }

        //If the attribute was still not found we check if the input was a suffix
        if(attributeNum < 0){
            attributeNum = suffixSearch(attributeArray, ":" + attributeName);
        }

        //Try to display the attribute at the corresponding index
        displayEquation(currentNode, attributeArray[attributeNum]) ;
        attributeDataHTML(currentNode, "");
        return 1;
    }

    //helper for finding a element with a specific suffix 
    public static Integer suffixSearch(String[] array, String suffix) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].endsWith(suffix)) {
                return i; 
            }
        }
        return -1; 
    }


    private void sendMessageToExtension(String message) {
        String host = "127.0.0.1"; // Host of the VS Code extension server
        int port = 19387; 
    
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
    
            out.println(message);
            System.out.println("Message sent to extension: " + message);
    
        } catch (IOException e) {
            System.err.println("Couldn't connect to the extension server at " + host + ":" + port);
            System.err.println(e.getMessage());
        }
    }
    
    // makes html of the production containing the inputed attribute name
    // the specific attribute is highlighted
    public void displayEquation(DecoratedNode node, String attriburteName)
    {
        Map<String, Lazy> lazyMap = allAttributesLazyMap(node);
        if (lazyMap.containsKey(attriburteName)) {
            Lazy attributeLazy = lazyMap.get(attriburteName);
            NLocation loc = attributeLazy.getSourceLocation();
            if(loc != null) {
                String file = loc.synthesized(silver.core.Init.silver_core_filename__ON__silver_core_Location).toString();
                int line = (Integer)loc.synthesized(silver.core.Init.silver_core_line__ON__silver_core_Location);
                int endline = (Integer)loc.synthesized(silver.core.Init.silver_core_endLine__ON__silver_core_Location);
                
                equationHTML(file, line, endline);
                writeTojson(file, line, endline);
                // add a client server here, when it called send 1
                sendMessageToExtension("1");
            }
        }
    }

    // makes html of the production containing the inputed attribute name
    // the specific attribute is highlighted
    public void writeTojson(String filename, int lineNumber, int endline)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".debugger_communicator.json"))) {
            String currentDirectory = System.getProperty("user.dir");
            int lastIndex = filename.lastIndexOf("/");
            String fileEnd = filename.substring(lastIndex + 1);
            writer.write("{\"file_path\": \"" + currentDirectory + "/" + fileEnd + "\", \"line_begin\": " + lineNumber + ", \"line_end\": " + endline+ "}");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    //Helper for displayEquation
    public static void equationHTML(String filename, int lineNumber, int endline) {
        //System.out.println("in print content");
        try (BufferedReader br1 = new BufferedReader(new FileReader(filename));
            BufferedWriter writer = new BufferedWriter(new FileWriter("current_production.html"))) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<body>\n");
            String line;
            int currentLineNumber = 1;
            int productionLineNum = 0;
            while ((line = br1.readLine()) != null) {
                //HACK:Relies on the the fact that "::=" is only and always used in production declarations
                if (line.contains("::=")) {
                    productionLineNum = currentLineNumber; 
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
                if(currentLineNumber == lineNumber){
                    writer.write("<span style=\"color: red;\"><strong>");
                }
                if (currentLineNumber >= productionLineNum) {
                    writer.write(line);
                    if(currentLineNumber == endline){
                        writer.write("</strong></span>");
                        writer.newLine();
                    }
                    writer.newLine();
                }
                //HACK:Relies on the the fact that the line "}" is only and always used in production ends
                if (currentLineNumber >= productionLineNum && line.trim().equals("}")) {
                    break; 
                }
                currentLineNumber++;
            }
            writer.write("</pre>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Old function not sure if we still want it
    public int listSynth(DecoratedNode node)
    {
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        Set<String> synAttrSet = nonterminalton.getAllSynth();
        int numAttr = 0;

        for (String synAttr : synAttrSet)
        {
            System.out.println("Attribute = " + synAttr);
            numAttr++;
        }
        return numAttr;
    }

    //Old function not sure if we still want it
    public int listInher(DecoratedNode node)
    {
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        Set<String> inhAttrSet = nonterminalton.getAllInh();
        int numAttr = 0;

        for (String inhAttr : inhAttrSet)
        {
            System.out.println("Attribute = " + inhAttr);
            numAttr++;
        }
        return numAttr;
    }   

    //Prints all attributes using allAttributesList
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
        Map<String, Object> attributeMap = allAttributesThunkMap(node);
        @SuppressWarnings("unchecked")
        Object finalThunk = attributeMap.get(printAttribute);
        System.out.println(Util.genericShow(Util.demand(finalThunk)));
    }

    //Higlights the data of the specified attribute
    public void attributeDataHTML(DecoratedNode node, String printAttribute){
        Map<String, Object> attributeMap = allAttributesThunkMap(node);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attribute_values.html"))) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<body>\n");
            writer.write("<pre>\n");
            for (Map.Entry<String, Object> entry : attributeMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.equals(printAttribute)){
                    writer.write("<mark>");
                    writer.write(key + ": " + Util.genericShow(Util.demand(value)));
                    writer.write("</mark>");
                }else{
                    if(value instanceof Thunk){
                        writer.write(key + ": THUNKING...");
                    }else{
                        writer.write(key + ": " + Util.genericShow(value));
                    }
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

    //HACK: this entire prossess is based on string meddling
    //A better way to do this would be to have each attribute know what other attributes generate it
    //This way we would not need to rely on specific string formatting
    //Should probaly not be pushed to 
    public int algorithmicDebugg(DecoratedNode node, String attriburteName, Scanner inp)
    {
        //Gets the equation we are on
        String equationString = "";
        Map<String, Lazy> lazyMap = allAttributesLazyMap(node);
        if (lazyMap.containsKey(attriburteName)) {
            Lazy attributeLazy = lazyMap.get(attriburteName);
            NLocation loc = attributeLazy.getSourceLocation();
            if(loc != null) {
                String filePath = loc.synthesized(silver.core.Init.silver_core_filename__ON__silver_core_Location).toString();
                int startLine = (Integer)loc.synthesized(silver.core.Init.silver_core_line__ON__silver_core_Location);
                int endLine = (Integer)loc.synthesized(silver.core.Init.silver_core_endLine__ON__silver_core_Location);

                System.out.println("Equation:");
                 try {
                    equationString = getLines(filePath, startLine, endLine);
                    System.out.println();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Next we want to get the LHS of the equation
        System.out.println("Data:");
        Map<String, Object> attributeMap = allAttributesThunkMap(node);

        String partentProduction = node.undecorate().getProdleton().getTypeUnparse();
        int index1 = partentProduction.indexOf("::");
        int index2 = attriburteName.indexOf(":");
        String parentNameInEquation = partentProduction.substring(0, index1) + "." + attriburteName.substring(index2+1);
        System.out.println(parentNameInEquation + ": " + Util.genericShow(Util.demand(attributeMap.get(attriburteName))));

        //This generates a list of all children of the production and splits them 
        //into the attribute front name (ex. ds) and bakc name (ex. DeclList)
        String currentProduction = node.undecorate().getProdleton().getTypeUnparse();
        String[] listCurrentProduction = currentProduction.split("\\s+");
        String[] childFullNames = Arrays.copyOfRange(listCurrentProduction, 2, listCurrentProduction.length);
        String[] childFrontNames = new String[childFullNames.length];
        String[] childBackNames = new String[childFullNames.length];
        for (int i = 0; i < childFullNames.length; i++) {
            // System.out.println("childFullNames[i]: " + childFullNames[i]);
            index1 = childFullNames[i].indexOf("::");
            // System.out.println(childFullNames[i].substring(0, index1)+ ".");
            childFrontNames[i] = childFullNames[i].substring(0, index1) + ".";
            index2 = childFullNames[i].indexOf(":");
            // System.out.println(childFullNames[i].substring(index2+2));
            childBackNames[i] = childFullNames[i].substring(index2+2);
        }

        //Here we are getting the RHS of the equation (all attributes / variables)
        //These should fallow the form <childfrontname>.attribute (ex. ds.pp)
        List<String> dependentAttributes = new ArrayList<>();
        String[] equationComponents = equationString.split("\\s+");
        for (String component : equationComponents) {
            // Check if the word starts with any element from the array
            for (String childFront : childFrontNames) {
                if (component.startsWith(childFront)) {
                    dependentAttributes.add(component);
                    break;
                }
            }
        }
        // for (String attribute : dependentAttributes) {
        //     System.out.println(attribute);
        // }

        //Next the user will pick which of these variables they want to further investigate 
        //We split this into 2 parts index 0 is the Front name (ex. ds) 
        //the second is the attribute  (ex. pp)
        String[] dependentAttributesArray = dependentAttributes.toArray(new String[0]);
        int inputInt = -1;
        if(dependentAttributesArray.length > 0){
            System.out.println();
            System.out.println("Pick the next node to investigate");
            inputInt = chooseFormList(inp, dependentAttributesArray);
        }else{
            return 0;
        }
        if(inputInt == -1)
            return -1;
        String chosenAttribute = dependentAttributesArray[inputInt];
        String[] chosenAttributeComponents = chosenAttribute.split("\\.");

        //Baed on what the user chose we can solve for the child they want to travle to
        //Becaues it will have the same Front name as the variable (ex. ds.pp -> ds::DeclList)
        String nextChildName = "";
        for (String fullName : childFullNames){
            if (fullName.startsWith(chosenAttributeComponents[0] + "::")) {
                nextChildName = fullName;
            }
        }
        System.out.println(nextChildName);

        //Now that we know the child we can travel their
        // Integer nextChildNum = Arrays.binarySearch(childFullNames, nextChildName);
        //Wierd bug, we can't go up after going down in alogdebugg
        if (down(nextChildName) == -1){
            System.out.println("invalid child");
        }

        // System.out.println();
        // System.out.println("currentNode:");
        // System.out.println();

        //We also know what attribute they want to investigate
        //it should have the same end as the chosen attribute
        List<String> attributeList = allAttributesList(currentNode);
        String nextAttributeName = "";
        //list should be list of attributes
        for (String element : attributeList) {
            String[] parts = element.split(":");
            if (parts.length == 2 && chosenAttributeComponents[1].startsWith(parts[1])) {
                nextAttributeName = element;
            }
        }
        System.out.println(nextAttributeName);

        //recursive time
        if(nextChildName != ""){
            algorithmicDebugg(currentNode, nextAttributeName, inp);
        }
        return -1;
    }

    //helper foralgorithmicDebugg
    public static String getLines(String filePath, int startLine, int endLine) throws IOException {
        String returnString = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLine = 1;

            // Read lines until reaching the start line
            while ((line = reader.readLine()) != null && currentLine < startLine) {
                currentLine++;
            }

            // Print lines from startLine to endLine
            while (line != null && currentLine <= endLine) {
                System.out.println(line); //Comment this out
                returnString += line;
                line = reader.readLine();
                currentLine++;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return returnString;
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
    //Translation attribute or Decorated, locals only locals should all be decorated
    public DecoratedNode into(DecoratedNode node, String attriburteName){
        Map<String, Object> attributeMap = allAttributesObjectMap(node);
        if (attributeMap.containsKey(attriburteName)) {
            System.out.println("In into function");
            Object attributeObject = attributeMap.get(attriburteName);
            return (DecoratedNode) attributeObject; //Does not work class translator.Pprogram cannot be cast to class common.DecoratedNode
        }
        return null;
    }
    

    //Helper for printing Attributes
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

    //Deprecated: please use allAttributesThunkMap
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

    //Creates a map of attribute names to there thunks that can be demanded to get the values of the attributes
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

    //maps attributes names to there lazy
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

    //Should be in util?
    public static void displayArray(String[] array){
        for (String element : array){
            System.out.println(element);
        } 
    }

    //For letting users type tab to autofill with an element of the input list
    public static String autofill(String[] options, Scanner inp){
        while (true) {
            String input = inp.nextLine();
            if (input.isEmpty()) {
                continue;
            }
            if (input.endsWith("\t")) { // Check if Tab key is pressed
                String prefix = input.substring(0, input.lastIndexOf("\t"));
                return autoComplete(prefix, options);
            } else {
                // Handle regular input
                return input;
            }
        }
    }

    //helper for autofill
    private static String autoComplete(String prefix, String[] options) {
        for (String option : options) {
            if (option.startsWith(prefix)) {
                return option;
            }
        }
        return "";
    }

    //Should be in util?
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
                if (stopper.equals("q")){
                    continueLoop = false;
                }else{
                    System.out.println("Please choose an integer or q to exit");
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
