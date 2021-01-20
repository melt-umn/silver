package common;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * This class represents the `<code>ProcessHandle</code>` type in Silver for
 * working with subprocesses.
 * 
 * <p>Interactions with processes in Silver are handled by the `IOToken` class.
 * None of the functions here should be available directly in Silver.
 * 
 * @author RandomActsOfGrammar
 */
public class ProcessHandle {

    private Process proc;

    public ProcessHandle(Process p) {
        proc = p;
    }

    public ProcessHandle(List<String> cmd_args) {
        ProcessBuilder pb = new ProcessBuilder(cmd_args);
        pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
        pb.redirectInput(ProcessBuilder.Redirect.PIPE);
        pb.redirectError(ProcessBuilder.Redirect.PIPE);
        try {
            proc = pb.start();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Used for reading stdout from the process
    private BufferedReader our_stdout = null;
    /*Read a line from stdout of the process*/
    public StringCatter readLineStdout() {
        try {
            if (our_stdout == null) {
                InputStream instream = proc.getInputStream();
                InputStreamReader r = new InputStreamReader(instream);
                our_stdout = new BufferedReader(r);
            }
            return new StringCatter(our_stdout.readLine());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Used for reading stderr from the process
    private BufferedReader our_stderr = null;
    /*Read a line from stderr of the process*/
    public StringCatter readLineStderr() {
        try {
            if (our_stderr == null) {
                InputStream instream = proc.getErrorStream();
                InputStreamReader r = new InputStreamReader(instream);
                our_stderr = new BufferedReader(r);
            }
            return new StringCatter(our_stderr.readLine());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*Write a message to stdin of the process*/
    public void writeString(String msg) {
        OutputStream ostream = proc.getOutputStream();
        try {
            ostream.write(msg.getBytes());
            ostream.flush();
            return;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*Wait for the process to exit*/
    public void waitOnEnd() {
        proc.waitFor();
        return;
    }
}

