package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HTMLContextVisualization extends FileContextVisualization {
    private final String documentFormat = "<!DOCTYPE html>\n<html>\n<head>\n<title>Context Visualization</title>\n</head>\n<body>\n<p>%s</p>\n</body>\n</html>";

    public HTMLContextVisualization(String filename, String border) {
        super(filename, border);
    }

    public HTMLContextVisualization(String border) {
        super("context.html", border);
    }
    
    @Override
    public void show() {
        super.show();
        
        try {
            String content = new Scanner(new File(this.filename)).useDelimiter("\\Z").next();
            
            File file = new File(this.filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(String.format(this.documentFormat, content));

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}