package common;

public abstract class ContextVisualization {
    protected ContextStack contextStack;

    public ContextVisualization() {
        this.contextStack = new ContextStack();
    }

    public void push(DecoratedNode decoratedNode) {
        this.contextStack.push(decoratedNode);
    }  

    public NodeContextMessage pop() {
        return this.contextStack.pop();
    }

    public abstract void show();
}


// public abstract class ContextVisualization {
//     protected ContextStack contextStack;

//     public ContextVisualization() {
//         this.contextStack = new ContextStack();
//     }

//     public void push(Integer decoratedNode) {
//         this.contextStack.push(decoratedNode);
//     }  

//     public Integer pop() {
//         return this.contextStack.pop();
//     }

//     public abstract void show();
// }
