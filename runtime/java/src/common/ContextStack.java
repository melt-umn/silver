package common;

// Wrapped around built-in Java Stack class
// No error handling in current version

import java.util.Iterator;
import java.util.Stack;

public class ContextStack {

    public void push(DecoratedNode n) {
        this.height++;
        NodeContextMessage ncm = new NodeContextMessage(n);
        this.stack.push(ncm);
    }

    public NodeContextMessage pop() {
        this.height--;
        return this.stack.pop();
    }    

    public NodeContextMessage peak() {
        return this.stack.peek();
    }

    public int get_height() {
        return this.height;
    }

    public Iterator<NodeContextMessage> iterator() {
        return this.stack.iterator();
    }

    private Stack<NodeContextMessage> stack = new Stack<NodeContextMessage>();
    private int height = 0;
}

// public class ContextStack {

//     public void push(Integer n) {
//         this.height++;
//         this.stack.push(n);
//     }

//     public Integer pop() {
//         this.height--;
//         return this.stack.pop();
//     }    

//     public Integer peak() {
//         return this.stack.peek();
//     }

//     public int get_height() {
//         return this.height;
//     }

//     public Iterator<Integer> iterator() {
//         return this.stack.iterator();
//     }

//     private Stack<Integer> stack = new Stack<Integer>();
//     private int height = 0;
// }
