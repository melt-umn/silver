package common;

// Wrapped around built-in Java Stack class
// No error handling in current version

import java.util.Iterator;
import java.util.Stack;

public class ContextStack {

    public void push(DecoratedNode n) {
        this.height++;
        NodeContextMessage ncm = new NodeContextMessage(n, this.next_index++);
        this.stack.push(ncm);
    }

    public NodeContextMessage pop() {
        this.height--;
        this.next_index--;
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

    public NodeContextMessage get(int index) {
        // Index 0 is the bottom of the stack
        int stackIndex = this.stack.size() - index - 1;
        if (stackIndex >= 0 && stackIndex < this.stack.size()) {
            return this.stack.get(stackIndex);
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
    }

    private Stack<NodeContextMessage> stack = new Stack<NodeContextMessage>();
    private int height = 0;
    // Used for indices
    private int next_index = 1;
}
