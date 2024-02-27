package common;

import java.util.Iterator;

public class CMDContextVisualization extends ContextVisualization {
    private final String printFormat = "%s\n%s\n%s\n";
    private String border;

    public CMDContextVisualization(String border) {
        super();

        this.border = border;
    }

    @Override
    public void show() {
        // for(NodeContextMessage nodeContextMessage : this.contextStack.iterator()) {
        //     System.out.printf(this.printFormat, this.border, nodeContextMessage.toString(), this.border);
        // }

        Iterator<NodeContextMessage> iterator = this.contextStack.iterator();
        while (iterator.hasNext()) {
            NodeContextMessage nodeContextMessage = iterator.next();
            System.out.printf(this.printFormat, this.border, nodeContextMessage.toString(), this.border);
        }
    }
}

