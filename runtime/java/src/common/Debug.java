package common;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

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
        // Do interactive debug stuff
        Scanner inp = new Scanner(System.in);
  
        System.out.println("Enter characters, "
                           + " and 'q' to quit.");
        String userInput; 
        String[] userInputList;
        this.root = tree;
        this.currentNode = tree;

        //COntrol loop
        loop: do { 
            userInput = inp.nextLine();
            userInputList = userInput.split(" ");
            switch (userInputList[0]) {
                case "up": 
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: up<>");
                    }else{
                        if (currentNode.getParent().getParent() instanceof TopNode || currentNode.getParent() == null){
                            System.out.println("Root Node has no parent");
                        }else if (currentNode.getParent() == null){
                            System.out.println("Null parent");
                        }else{
                            currentNode = currentNode.getParent();
                            System.out.println("going to parent");
                        }
                    }
                    break;
                case "down":  
                    if (userInputList.length != 2) {
                        System.out.println("invalid, correct usage: down<node>");
                    }else{
                        DecoratedNode childNode = down(Integer.parseInt(userInputList[1]));
                        if(childNode == null){
                            System.out.println("invalid child number");
                        } 
                        else{
                            System.out.println("going down");
                            currentNode = childNode;
                        }
                    }
                    break;
                case "forwards": 
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: forwards<>");
                    }else{
                        DecoratedNode childNode = forwards(currentNode);
                        if(childNode == null){
                            System.out.println("invalid no node to forward");
                        } 
                        else{
                            System.out.println("going forward");
                            currentNode = childNode;
                        }
                    }
                    break;
                case "backtrack": 
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: backtrack<>");
                    }else{

                        DecoratedNode childNode = forwards(currentNode);
                        if(childNode == null){
                            System.out.println("invalid no node to backtrack to");
                        } 
                        else{
                            System.out.println("going backwrds");
                            currentNode = childNode;
                        }
                    }
                    break;
                //Display the production
                case "prod": 
                    if (userInputList.length != 1) {
                        System.out.println("invalid, correct usage: prod<>");
                    }else{
                        String partent_type = currentNode.undecorate().getProdleton().getName();
                        String child_types[] = currentNode.undecorate().getProdleton().getChildTypes();
                        System.out.println(partent_type);
                        for (int i = 0; i < child_types.length; i++){
                            System.out.println(child_types[i]);
                        }
                    }
                    break;
                //TODO:Implement this - use genericShow
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
                case "list":
                    if (userInputList.length != 1 && userInputList.length != 2) {
                        System.out.println("invalid, correct usage: list<node?>");
                    }else{
                        if(listSynth(currentNode) + listInher(currentNode) == 0){
                            System.out.println("no attributes");
                        }
                    }
                    break;
                //TODO: Implement this
                case "view": 
                    if (userInputList.length != 2) {
                        System.out.println("invalid, correct usage: view<attr>");
                    }else{
                        // System.out.println("do the view list");
                        viewAttr(currentNode, userInputList[1]);
                    }
                    break;
                case "help": 
                    System.out.println("up<>");
                    System.out.println("down<node>");
                    System.out.println("view<attr>");
                    System.out.println("forwards<>");
                    System.out.println("backtrack<>");
                    System.out.println("prod<>");
                    System.out.println("eq<attr?>");
                    System.out.println("listSynth<node?>");
                    System.out.println("listInher<node?>");
                    System.out.println("list<node?>");
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
                    break;
            }
        } while(true); 
    }
    private DecoratedNode root;
    private DecoratedNode currentNode;
    HashMap<Integer, StringObjectPair> currentNodeSynthAttrs;
    HashMap<Integer, StringObjectPair> currentNodeInhAttrs;
    HashMap<Integer, StringObjectPair> currentNodeLocalAttrs;
    private int currentLine;
    private int currentColumn;
    // public Debugger(DecoratedNode root)
    // {
    //     this.root = root;
    //     this.currentNode = root;
    //     this.currentLine = 0;
    //     this.currentColumn = 0;
    // }
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
        if (currentNode.getNode().getNumberOfChildren() > child)
        {
            DecoratedNode childNode = currentNode.childDecorated(child);
            return childNode;
        }
        return null;
    }
    public DecoratedNode forwards(DecoratedNode Node)
    {
        if (Node.getNode().hasForward()){
            currentNode = Node.forward();
            return currentNode;
        }
        return null;
    }
    public DecoratedNode backtrack(DecoratedNode Node)
    {
        currentNode = Node.getForwardParent();
        return currentNode;

    }
    // ask erik how this works again
    public void printProduction()
    {
        currentNode.getNode().getProdleton().getName();
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
        // Node undecorated = node.undecorate();
        // int count = undecorated.getNumberOfSynAttrs();
        // HashMap<Integer, StringObjectPair> hash = new HashMap<Integer, StringObjectPair>();
        // for(int i = 0; i < count; i++)
        // {
        //     Lazy attribute = node.getSynthesized(i);
        //     // do whatever printing, no method getSynAttrValue
        //     hash.put(i, new StringObjectPair(currentNode.getNameOfSynAttr(i), currentNode.evalSyn(i)));
        // }
        // currentNodeSynthAttrs = hash;
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
        // Node undecorated = node.undecorate();
        // int count = undecorated.getNumberOfInhAttrs();
        // HashMap<Integer, StringObjectPair> hash = new HashMap<Integer, StringObjectPair>();
        // for(int i = 0; i < count; i++)
        // {
        //     // no getInherited method;
        //     // Lazy attribute = node.getInherited(i);
        //     Lazy attribute = node.inherited(i);
        // }
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
    /* 
    public void listLocalAttrs(DecoratedNode node)
    {
        // Node undecorated = node.undecorate();
        // int count = undecorated.getNumberOfLocalAttrs();
        // HashMap<Integer, StringObjectPair> hash = new HashMap<Integer, StringObjectPair>();
        // for(int i = 0; i < count; i++)
        // {
        //     Lazy attribute = node.getLocal(i);
        //     // do whatever printing
        //     hash.put(i, new StringObjectPair(currentNode.getNameOfLocalAttr(i), currentNode.evalLocalDecorated(i)));
        //     System.out.println("Attribute = " + entry.getKey() + 
        //                      ", Index = " + entry.getValue())
        // }
        // currentNodeLocalAttrs = hash;
        int count = node.getNumberOfLocalAttrs();
        for(int i = 0; i < count; i++)
        {
            Lazy attribute = node.getLocal(i);
            // do whatever printing
            System.out.println("Attribute = " + currentNode.getNameOfLocalAttr(i) + 
                             ", Index = " + Integer.toString(i));
        }
    }
     */
     
    // may want to rethink this such that view prints representation of attribute on current node, 
    // or allow an int to be passed in as the index of the child whose attribute you'd like to print info for
    public int viewSynth(DecoratedNode node, String attribute)
    {
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        int index = 0;
        if(nonterminalton.getSynOccursIndices().keySet().contains(attribute)){
            index = nonterminalton.getSynOccursIndex(attribute);
        }else{
            System.out.println("Bad input, legal inputs:");
            Set<String> synAttrSet = nonterminalton.getAllSynth();
            System.out.println("Keys: " + synAttrSet);
            return -1;
        }
        Lazy synthAttribute = node.getNode().getSynthesized(index);
        Object o = synthAttribute.eval(node);
        System.out.println(Util.genericShow(o));
        return 1;
    }

    public int viewInher(DecoratedNode node, String attribute)
    {
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        int index = 0;
        if(nonterminalton.getInhOccursIndices().keySet().contains(attribute)){
            index = nonterminalton.getInhOccursIndex(attribute);
        }else{
            System.out.println("Bad input, legal inputs:");
            Set<String> inhAttrSet = nonterminalton.getAllInh();
            System.out.println("Keys: " + inhAttrSet);
            return -1;
        }
        Object o = node.evalInhSomehowButPublic(index);
        System.out.println(Util.genericShow(o));
        return 1;   
    }

    public int viewAttr(DecoratedNode node, String attribute)
    {
        RTTIManager.Prodleton<?> prodleton = node.getNode().getProdleton();
        RTTIManager.Nonterminalton<?> nonterminalton = prodleton.getNonterminalton();
        int index = 0;
        boolean isSynth = false;
        if(nonterminalton.getSynOccursIndices().keySet().contains(attribute)){
            index = nonterminalton.getSynOccursIndex(attribute);
            isSynth = true;
        }else if(nonterminalton.getInhOccursIndices().keySet().contains(attribute)){
            index = nonterminalton.getInhOccursIndex(attribute);
        }else{
            System.out.println("Bad input, legal inputs:");
            Set<String> allInputs = new HashSet<String>();
            Set<String> synAttrSet = nonterminalton.getAllSynth();
            Set<String> inhAttrSet = nonterminalton.getAllInh();
            allInputs.addAll(synAttrSet);
            allInputs.addAll(inhAttrSet);
            System.out.println("Keys: " + allInputs);
            return -1;
        }
        if(isSynth){
            Lazy synthAttribute = node.getNode().getSynthesized(index);
            Object o = synthAttribute.eval(node);
            System.out.println(Util.genericShow(o));
        }else{
            Object o = node.evalInhSomehowButPublic(index);
            System.out.println(Util.genericShow(o));
        }
        return 1;
    }
    
    public void viewLocals(DecoratedNode node, int attribute)
    {

    }
    public boolean isContractum()
    {
        return false;
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