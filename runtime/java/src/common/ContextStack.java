package common;

// Key intermediate structure of debugging contextualization.
// We push/pop nodes while navigating in Debug.java into this stack.
// A ContextStack is then a member of a SimplifiedContextStack, which
// produces the actual contextualization.

// ContextStack is fundementally a stack of NodeContextMessage objects.
// Each of these objects represents a record of contextualization
// information we store about a single visited node (only maintain
// those on the path from root to the current node, like a DFS traversal).

// Wrapped around built-in Java Stack class
// No error handling in current version

import java.util.Iterator;
import java.util.Stack;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

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

    public NodeContextMessage get(int stackIndex) {
        // Index 0 is the bottom of the stack
        if (stackIndex >= 0 && stackIndex < this.stack.size()) {
            return this.stack.get(stackIndex);
        } else {
            throw new IndexOutOfBoundsException("Index " + stackIndex + " is out of bounds.");
        }
    }

    private Stack<NodeContextMessage> stack = new Stack<NodeContextMessage>();
    private int height = 0;
    // Used for indices
    private int next_index = 1;
}
