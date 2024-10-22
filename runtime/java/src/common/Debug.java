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

        //Variables for terminal displays
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

                    //Now that we have the child name we can try to go to it
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

                //used to go back to the last node tracked on nodeStack
                //FIXME: has some unexpected errors with forwarding nodes
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

                //used to list all attributes on the terminal
                case "update": case "l":
                    //update should not have any arguments
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: list");
                        break;
                    }

                    //Do the update
                    updateDisplay();
                    break;

                //used to evaluate specific attributes
                case "view": case "v": 
                    String attributeInput = ""; 

                    //If the user does not provide a child we should list them out
                    if (userInputList.length == 1) {
                        List<String> attributeList = allAttributesList(currentNode);
                        String[] attributeArray =  attributeList.toArray(new String[attributeList.size()]);
                        System.out.println("Which attribute?");
                        displayArray(attributeArray);
                        System.out.print(">DEBUGGER-PROMPT$");
                        attributeInput = inp.nextLine();
                    }

                    //Otherwise they should have typed one in
                    else if(userInputList.length == 2){
                        attributeInput = userInputList[1];
                    }
                    
                    //We do not expect more than 2 arguments for down
                    else{
                        System.out.println("invalid, correct usage: view <attribute name>");
                        break;
                    }

                    //Now that we have the attribute name we can try to go to it
                    if (attributeDataHTML(currentNode, attributeInput) == -1){
                        System.out.println("invalid input");
                    }
                    break;

                //Proof of concept function starts a algorithmic debugging session with a given attribute
                case "algoDebug": case "a": 
                    String attributeName = ""; 

                    //If the user does not provide a attribute we should list them out
                    if (userInputList.length == 1) {
                        List<String> attributeList = allAttributesList(currentNode);
                        String[] attributeArray =  attributeList.toArray(new String[attributeList.size()]);
                        System.out.println("Which attribute?");
                        displayArray(attributeArray);
                        System.out.print(">DEBUGGER-PROMPT$");
                        attributeName = inp.nextLine();
                    }
                    
                    
                    //Otherwise they should have typed one in
                    else if(userInputList.length == 2){
                        attributeName = userInputList[1];
                    }
                    
                    //We do not expect more than 2 arguments for down
                    else{
                        System.out.println("invalid, correct usage: view <node #>");
                        break;
                    }

                    algorithmicDebug(currentNode, attributeName, inp);
                    break;

                //TODO: known bug, don't know how to represent higher order nodes as decoratedNodes
                //TODO: Implementing this function could be done in future work
                // case "into":
                //     String attributeNameInto = "";

                //     //If the user does not provide a child we should list them out
                //     if (userInputList.length == 1) {
                //         List<String> attributeListInto = allAttributesList(currentNode);
                //         String[] attributeArrayInto =  attributeNameInto.toArray(new String[attributeListinto.size()]);
                //         System.out.println("Which attribute?");
                //         displayArray(attributeArrayInto);
                //         System.out.print(">DEBUGGER-PROMPT$");
                //         childName = inp.nextLine();
                //     }

                //     //Otherwise they should have typed one in
                //     else if(userInputList.length == 2){
                //         childName = userInputList[1];
                //     }
                    
                //     //We do not expect more than 2 arguments for into
                //     else{
                //         System.out.println("invalid, correct usage: down <node name>");
                //         break;
                //     }

                //     if (into(currentNode, attributeNameInto) == -1){
                //         System.out.println("invalid input");
                //         break;
                //     }
                //     if(childNode == null){
                //         System.out.println("invalid input");
                //     }
                //     else{
                //         System.out.println("going into");
                //         currentNode = childNode;
                //         if(toggleNameDisplay){
                //             printName(currentNode);
                //         }
                //         // if we navigate to a forward, push it on to the stack
                //         cStack.push(currentNode);
                //         // when we push, update and show the context
                //         cStack.show();
                //     }
                //     break;

                //Display legal operations
                case "help": 

                    //If called alone give operation names
                    if (userInputList.length != 2) {
                        System.out.println("call help with one of these keywords to see its functionality:");
                        System.out.println("toggle <feature>");
                        System.out.println("up");
                        System.out.println("down <node>");
                        System.out.println("view <attr>");
                        System.out.println("forwards");
                        System.out.println("backtrack");
                        System.out.println("prod");
                        System.out.println("eq");
                        System.out.println("update");
                        System.out.println("exit");
                    }
                    
                    //More detailed information about specific calls
                    else if(userInputList.length == 2){
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
                        }else if(userInputList[1].equals("update")){
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
                    }
                    break;

                //Many ways to leave
                case "exit": case "q": case "quit": 
                    System.out.println("debugger out");
                    break loop;

                //If the user call anything illegal
                default: 
                    System.out.println("invalid input call help for legal inputs");
                    break;
            }
        } while(true); 
    }

    //variable declarations
    private DecoratedNode root;
    private DecoratedNode currentNode;
    private Stack<DecoratedNode> nodeStack;
    private FileContextVisualization cStack;
    private ContextStack contextStack;
    private SimplifiedContextStack sStack;
    private int currentLine;
    private int currentColumn;
    private boolean toggleNameDisplay;
    private boolean toggleCStackDisplay;
    private boolean toggleHeadlessAttributes;
    private String[] toggleChoices;

    private boolean isRoot(DecoratedNode dn) {
		return 
            dn.getParent() == null || 
            dn.getParent() instanceof TopNode ||
            dn.getParent().getParent() == null ||
            dn.getParent().getParent() instanceof TopNode;
	}

    //Replaces currentNode with its parent
    public Integer up()
    {
        

        //Make sure there is a parent to go up to 
        if (this.isRoot(currentNode)){
            System.out.println("Root Node has no parent");
            return -1;
        }

        //NodeStack contains past nodes not current ones
        nodeStack.push(currentNode);

        //Updates currentNode
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

                    writeToJson(file, productionLineNum, productionLineNum);
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
        if (isContractum(currentNode)){
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

    //Finds the corresponding equation updates the display 
    public Integer equationHelper(String attributeInput)
    {
        //Gets the attribute name from a user input 
        List<String> attributeList = allAttributesList(currentNode);
        String[] attributeArray = attributeList.toArray(new String[attributeList.size()]);
        String attributeName = inputArrayFinder(attributeArray, attributeInput);
        if(attributeName.equals("")){
            return -1;
        }

        //Try to display the attribute at the corresponding index
        displayEquation(currentNode, attributeName) ;
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

    //Helper for communicating with VS Code extension server
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
    
    // Popup the file and line where the attribute is defined
    public void displayEquation(DecoratedNode node, String attributeName)
    {
        Map<String, Lazy> lazyMap = allAttributesLazyMap(node);
        if (lazyMap.containsKey(attributeName)) {
            Lazy attributeLazy = lazyMap.get(attributeName);
            NLocation loc = attributeLazy.getSourceLocation();
            if(loc != null) {
                //Get the data from the NLocation
                String file = loc.synthesized(silver.core.Init.silver_core_filename__ON__silver_core_Location).toString();
                int line = (Integer)loc.synthesized(silver.core.Init.silver_core_line__ON__silver_core_Location);
                int endline = (Integer)loc.synthesized(silver.core.Init.silver_core_endLine__ON__silver_core_Location);
                
                //write this data to a Json and tell the server to refresh
                writeToJson(file, line, endline);
                sendMessageToExtension("1");
            }
        }
    }

    // Helper for displayEquation
    public void writeToJson(String filename, int lineNumber, int endline)
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

    //Highlights the data of the specified attribute
    public Integer attributeDataHTML(DecoratedNode node, String attributeName){
        //Gets the attribute name from a user input 
        List<String> attributeList = allAttributesList(currentNode);
        String[] attributeArray = attributeList.toArray(new String[attributeList.size()]);
        String highlightAttribute = inputArrayFinder(attributeArray, attributeName);
        if(attributeName.equals("")){
            return -1;
        }

        Map<String, Object> attributeMap = allAttributesThunkMap(node);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attribute_values.html"))) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<body>\n");
            writer.write("<pre>\n");
            for (Map.Entry<String, Object> entry : attributeMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.equals(highlightAttribute)){
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
        return 0;
    }

    //Given a user input search an array for something that looks close enough 
    //should be in util?
    public String inputArrayFinder(String[] array, String input){
        int index = Arrays.binarySearch(array, input);
        String returnString = "";

        //If the string was not found we check if the input was a prefix
        if(index < 0){
            index = prefixSearch(array, input);
        }

        //If the string was still not found we check if the input was a suffix
        if(index < 0){
            index = suffixSearch(array, ":" + input);
        }

        //The string is not in the array
        if(index < 0){
            return "";
        }

        return array[index];
    }

    //HACK: this entire process is based on string meddling
    //A better way to do this would be to have each attribute know what other attributes generate it
    //This way we would not need to rely on specific string formatting
    public int algorithmicDebug(DecoratedNode node, String attributeInput, Scanner inp)
    {
        //Gets the attribute name from a user input 
        List<String> attributeList = allAttributesList(currentNode);
        String[] attributeArray = attributeList.toArray(new String[attributeList.size()]);
        String attributeName = inputArrayFinder(attributeArray, attributeInput);
        if(attributeName.equals("")){
            System.out.print("invalid input");
            return -1;
        }

        //Print out the equation for the given attribute 
        String equationString = "";
        Map<String, Lazy> lazyMap = allAttributesLazyMap(node);
        if (lazyMap.containsKey(attributeName)) {
            Lazy attributeLazy = lazyMap.get(attributeName);
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
        int index2 = attributeName.indexOf(":");
        String parentNameInEquation = partentProduction.substring(0, index1) + "." + attributeName.substring(index2+1);
        System.out.println(parentNameInEquation + ": " + Util.genericShow(Util.demand(attributeMap.get(attributeName))));

        //This generates a list of all children of the production and splits them 
        //into the attribute front name (ex. l) and back name (ex. value)
        String currentProduction = node.undecorate().getProdleton().getTypeUnparse();
        String[] listCurrentProduction = currentProduction.split("\\s+");
        String[] childFullNames = Arrays.copyOfRange(listCurrentProduction, 2, listCurrentProduction.length);
        String[] childFrontNames = new String[childFullNames.length];
        String[] childBackNames = new String[childFullNames.length];
        for (int i = 0; i < childFullNames.length; i++) {
            index1 = childFullNames[i].indexOf("::");
            childFrontNames[i] = childFullNames[i].substring(0, index1) + ".";
            index2 = childFullNames[i].indexOf(":");
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

        //For each of these relevant component we want to print to data at that component
        for (String attribute : dependentAttributes) { 
            String[] attributeComponents = attribute.split("\\.");
            String childName = "";
            for (int i = 0; i < childFullNames.length; i++){
                if (childFullNames[i].startsWith(attributeComponents[0] + "::")) {
                    DecoratedNode childNode = currentNode.childDecorated(i);
                    Map<String, Object> childMap = allAttributesThunkMap(childNode);
                    for (Map.Entry<String, Object> entry : childMap.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (key.endsWith(":" + attributeComponents[1])){
                            System.out.println(attribute + ": " + Util.genericShow(Util.demand(value)));
                        }
                    }
                }
            }
        }

        //Next the user will pick which of these variables they want to further investigate 
        //We split this into 2 parts index 0 is the Front name (ex. ds) 
        //the second is the attribute  (ex. pp)
        String[] dependentAttributesArray = dependentAttributes.toArray(new String[0]);
        int inputInt = -1;
        String chosenAttributeInput = "";
        if(dependentAttributesArray.length > 0){
            System.out.println();
            System.out.println("Pick the next node to investigate");
            displayArray(dependentAttributesArray);
            System.out.print(">DEBUGGER-PROMPT$");
            chosenAttributeInput = inp.nextLine();
        }else{
            return 0;
        }

        String chosenAttribute = inputArrayFinder(dependentAttributesArray, chosenAttributeInput);
        if(chosenAttribute.equals("")){
            System.out.print("invalid input");
            return -1;
        }
        String[] chosenAttributeComponents = chosenAttribute.split("\\.");

        //Based on what the user chose we can solve for the child they want to travel to
        //Because it will have the same Front name as the variable (ex. ds.pp -> ds::DeclList)
        String nextChildName = "";
        for (String fullName : childFullNames){
            if (fullName.startsWith(chosenAttributeComponents[0] + "::")) {
                nextChildName = fullName;
            }
        }
        System.out.println(nextChildName);

        //Now that we know the child we can travel their
        //Integer nextChildNum = Arrays.binarySearch(childFullNames, nextChildName);
        if (down(nextChildName) == -1){
            System.out.println("invalid child");
        }

        //We also know what attribute they want to investigate
        //it should have the same end as the chosen attribute
        String nextAttributeName = "";
        for (String element : attributeList) {
            String[] parts = element.split(":");
            if (parts.length == 2 && chosenAttributeComponents[1].startsWith(parts[1])) {
                nextAttributeName = element;
            }
        }
        System.out.println(nextAttributeName);

        //recursive time
        if(nextChildName != ""){
            algorithmicDebug(currentNode, nextAttributeName, inp);
        }
        return -1;
    }

    //helper for algorithmicDebugg
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
    // public Integer into(DecoratedNode node, String attributeInput){
    //     String attributeName = inputArrayFinder(attributeArray, attributeInput);
    //     if(attributeName.equals("")){
    //         return -1;
    //     }

    //     Map<String, Object> attributeMap = allAttributesThunkMap(node); // will have to eval the thunk
    //     if (attributeMap.containsKey(attributeName)) {
    //         Object attributeObject = attributeMap.get(attributeName);
    //         currentNode = (DecoratedNode) attributeObject.demand(); //Does not work class translator.Pprogram cannot be cast to class common.DecoratedNode
    //     }
    //     return 0;
    // }
    

    //Creates a list of all Attributes
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

    //Creates a map of attribute names to there thunks that can be demanded to get the values of the attributes
    public static Map<String, Object> allAttributesThunkMap(DecoratedNode node)
    {
        List<String> attributeList = allAttributesList(node);

        //Thunks are found in a nodes Nonterminalton
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        Map<String, Object> attributeMap = new HashMap<>();

        //Find the corresponding thunk for each attribute
        for(String attribute : attributeList)
        {
            //Synthesized attributes
            if(nonterminalton.getSynOccursIndices().keySet().contains(attribute)){
                Integer index = nonterminalton.getSynOccursIndex(attribute);
                Object o = node.contextSynthesizedLazy(index); 
                attributeMap.put(attribute, o);
            }
            
            //Inherited attributes
            else if(nonterminalton.getInhOccursIndices().keySet().contains(attribute)){
                Integer index = nonterminalton.getInhOccursIndex(attribute);
                Object o = node.contextInheritedLazy(index); 
                attributeMap.put(attribute, o);
            }
            
            //Local attributes
            else{
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

        //Lazy are found in a nodes Nonterminalton
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        Map<String, Lazy> attributeMap = new HashMap<>();

        //Find the corresponding Lazy for each attribute
        for(String attribute : attributeList)
        {
            //Synthesized attributes
            if(nonterminalton.getSynOccursIndices().keySet().contains(attribute)){
                Integer index = nonterminalton.getSynOccursIndex(attribute);
                Lazy synthAttribute = node.getNode().getSynthesized(index); //breaks for forwarded nodes
                attributeMap.put(attribute, synthAttribute);
            }
            
            //Inherited attributes
            else if(nonterminalton.getInhOccursIndices().keySet().contains(attribute)){
                Integer index = nonterminalton.getInhOccursIndex(attribute);
                Lazy inheritedAttribute = node.getInheritedAttribute(index);
                attributeMap.put(attribute, inheritedAttribute);
            }
            
            //Local attributes
            else{
                List<String> listLocals = getLocalAttrs(node);
                Integer index = listLocals.indexOf(attribute);
                Lazy localAttribute = node.getNode().getLocal(index);
                attributeMap.put(attribute, localAttribute);
            }
        }
        return attributeMap;
    }

    //Prints a array to the terminal
    //Should be in util?
    public static void displayArray(String[] array){
        for (String element : array){
            System.out.println(element);
        } 
    }
     
    //Returns true if the given node has a forward child
    public boolean isContractum(DecoratedNode node)
    {
        return node.getNode().hasForward();
    }
}