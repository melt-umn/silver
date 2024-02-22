package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class FileContextVisualization extends CMDContextVisualization {
    protected final String filename; 

    public FileContextVisualization(String filename, String border) {
        super(border);

        this.filename = filename;
    }

    @Override
    public void show() {
        File file = new File(this.filename);

        try(PrintStream replacementStream = new PrintStream(file)) {
            System.setOut(replacementStream);
            super.show();
            replacementStream.close();

            System.setOut(System.out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
