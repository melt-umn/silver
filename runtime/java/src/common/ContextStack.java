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
