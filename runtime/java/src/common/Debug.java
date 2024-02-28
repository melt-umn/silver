//TODO: Error handling is not good right now
//Thing to remeber: ./silver_compile --forced-origins --clean

package common;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;


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
        boolean toggleProdDisplay = true;
        boolean toggleCStackDisplay = true;
        boolean toggleHeadlessAttributes = false;
        DecoratedNode childNode;
        this.root = tree;
        this.currentNode = tree;
        this.nodeStack = new Stack<DecoratedNode>();

        if(toggleProdDisplay){
            printProduction(currentNode);
        }

        // creating a context stack when we run the debugger
        CMDContextVisualization cStack = new CMDContextVisualization("********************************");
        // if we want a file visualization:
        // FileContextVisualization cStack = new FileContextVisualization();
        // if we want an HTML visualization:
        // HTMLContextVisualization cStack = new HTMLContextVisualization();
        cStack.push(currentNode);
        if(toggleCStackDisplay){
            cStack.show();
        }

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
                            if(toggleProdDisplay){
                                printProduction(currentNode);
                            }
                            // if we navigate up to a parent, push it on to the stack (?)
                            cStack.pop();
                            // when we push, update and show the context
                            if(toggleCStackDisplay){
                                cStack.show();
                            }
                        }
                    }
                    break;

                case "down": case "d":  
                    int childNum = -1; 
                    if (userInputList.length == 1) {
                        System.out.println("Which child?");
                        printChildren(currentNode);
                        childNum = inputInt(inp);
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
                        if(toggleProdDisplay){
                            printProduction(currentNode);
                        }
                        // if we navigate down to a child, push it on to the stack
                        cStack.push(currentNode);
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
                            if(toggleProdDisplay){
                                printProduction(currentNode);
                            }
                            // remove from the stack
                            cStack.pop();
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
                            if(toggleProdDisplay){
                                printProduction(currentNode);
                            }
                            // if we navigate to a forward, push it on to the stack
                            cStack.push(currentNode);
                            // when we push, update and show the context
                            if(toggleCStackDisplay){
                                cStack.show();
                            }
                        }
                    }
                    break;

                
                //TODO: make a test case
                case "into":
                    if (userInputList.length != 2) {
                        System.out.println("invalid, correct usage: forwards <attribute>");
                    }else{
                        childNode = into(currentNode, userInputList[1]);
                        if(childNode == null){
                            System.out.println("invalid input");
                        }
                        else{
                            System.out.println("going inot");
                            currentNode = childNode;
                            // if(toggleProdDisplay){
                            //     printProduction(currentNode);
                            // }
                            // // if we navigate to a forward, push it on to the stack
                            // cStack.push(currentNode);
                            // // when we push, update and show the context
                            // cStack.show();
                        }
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
                            if(toggleProdDisplay){
                                printProduction(currentNode);
                            }
                            // if we navigate backwards, push it on to the stack (?)
                            cStack.push(currentNode);
                            // when we push, update and show the context
                            if(toggleCStackDisplay){
                                cStack.show();
                            }
                        }
                    }
                    break;

                case "toggle":
                    if (userInputList.length != 2) {
                        System.out.println("please indicate what you want to toggle ex: 'toggle prodDisplay'");
                    }else{
                        if(userInputList[1].equals("prodDisplay")){
                            if(toggleProdDisplay){
                                System.out.println("Production Display off");
                                toggleProdDisplay = false;
                            }else{
                                System.out.println("Production Display on");
                                 toggleProdDisplay = true;
                            }
                        }
                        if(userInputList[1].equals("fullAttributeNames")){
                            if(toggleHeadlessAttributes){
                                System.out.println("Headless Attributes off");
                                toggleHeadlessAttributes = false;
                            }else{
                                System.out.println("Headless Attributes on");
                                toggleHeadlessAttributes = true;
                            }
                        }if(userInputList[1].equals("cStackDisplay")){
                            if(toggleCStackDisplay){
                                System.out.println("cStack Display off");
                                toggleCStackDisplay = false;
                            }else{
                                System.out.println("cStack Display on");
                                toggleCStackDisplay = true;
                            }
                        }
                        else{
                            System.out.println("legal toggles: prodDisplay, fullAttributeNames");
                        }
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
                
                //TODO:Implement this - use genericShow?
                case "eq": 
                    if (userInputList.length != 1 && userInputList.length != 2) {
                        System.out.println("invalid, correct usage: eq<attr?>");
                    }else{
                        System.out.println("do the eq stuff");

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
                        System.out.println("invalid, correct usage: listInher<node?>");
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
                        printAttributes(currentNode, toggleHeadlessAttributes);  
                        attributeNum = inputInt(inp);
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
                        System.out.println("invalid, correct usage: down <node #>");
                        break;
                    }
                    printAttrFromName(currentNode, attributeName);
                    break;


                case "local":
                    listLocalAttrs(currentNode);
                    break;

                case "help": 
                    System.out.println("up");
                    System.out.println("down <node>");
                    System.out.println("view <attr>");
                    System.out.println("forwards");
                    System.out.println("backtrack");
                    System.out.println("prod");
                    System.out.println("eq");
                    System.out.println("listSynth");
                    System.out.println("listInher");
                    System.out.println("list");
                    System.out.println("exit");
                    break;

                //Many ways to leave
                case "exit": 
                case "q": 
                case "quit": 
                    System.out.println("debugger out");
                    break loop;
                default: 
                    System.out.println("invalid input call help for legal inputs");
                    System.out.println(userInput);
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
        //System.out.println("Inside of Down function");
        try{
            System.out.println("null name: " + child_productions[child]);
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

    public static int inputInt(Scanner inp){
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
        String child_productions[] = node.undecorate().getProdleton().getChildTypes();
        System.out.print(partent_production + " ");
        for (int i = 0; i < child_productions.length; i++){
            System.out.print(child_productions[i] + " ");
        }
        System.out.print("\n");
    }
    
    // Prints out the equation of the specified attr.
    // If attr is not specified, prints out the equations for all the attributes on the current node.
    // via eric we can just add equations as an attribute within our AG
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
        Map<String, Object> attributeMap = allAttributesObjectMap(node);
        System.out.println(Util.genericShow(attributeMap.get(printAttribute)));
    }

    //List of all and only local attributes
    public static List<String> listLocalAttrs(DecoratedNode node)
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
    public DecoratedNode into(DecoratedNode node, String attriburteName){
        Map<String, Object> attributeMap = allAttributesObjectMap(node);
        if (attributeMap.containsKey(attriburteName)) {
            System.out.println("In into function");
            Object attributeObject = attributeMap.get(attriburteName);
            return (DecoratedNode) attributeObject; //Does not work 
        }
        return null;
    }
    

    //Helper for printAttrFromName 
    public static List<String> allAttributesList(DecoratedNode node)
    {
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        List<String> attributeList = nonterminalton.alphabeticalAttributes();
        List<String> localAttributeList = listLocalAttrs(node);

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
                Lazy synthAttribute = node.getNode().getSynthesized(index); //scuffed
                Object o = synthAttribute.eval(node);
                attributeMap.put(attribute, o);
            }else if(nonterminalton.getInhOccursIndices().keySet().contains(attribute)){
                //System.out.println("Inherited!!!");
                Integer index = nonterminalton.getInhOccursIndex(attribute);
                Object o = node.evalInhSomehowButPublic(index);
                attributeMap.put(attribute, o);
            }else{ //Should be local
                //System.out.println("local!!!");
                List<String> listLocals = listLocalAttrs(node);
                Integer index = listLocals.indexOf(attribute);
                Lazy localAttribute = node.getNode().getLocal(index);
                Object o = localAttribute.eval(node);
                attributeMap.put(attribute, o);
            }
        }
        return attributeMap;
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