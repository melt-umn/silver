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

    public ContextStack getContextStack() {
        return this.contextStack;
    }
}
