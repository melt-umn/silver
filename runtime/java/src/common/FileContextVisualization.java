package common;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;
import java.io.IOException;

public class FileContextVisualization extends CMDContextVisualization {
    protected final String filename; 

    public FileContextVisualization(String filename, String border) {
        super(border);

        this.filename = filename;
    }

    @Override
    public void show() {
        
        File file = new File(this.filename);


        try{
            FileWriter myWriter = new FileWriter(this.filename);
            Iterator<NodeContextMessage> iterator = this.contextStack.iterator();
            while (iterator.hasNext()) {
                NodeContextMessage nodeContextMessage = iterator.next();
                myWriter.write(this.border + "\n" + nodeContextMessage.toString() + "\n" + this.border);
            }
            myWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
