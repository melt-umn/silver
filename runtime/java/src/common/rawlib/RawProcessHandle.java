package common.rawlib;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import common.IOToken;
import common.ConsCell;
import common.StringCatter;
import silver.core.NIOVal;

/**
 * This class represents the `<code>ProcessHandle</code>` type in Silver for
 * working with subprocesses.
 * 
 * <p>Interactions with processes in Silver are handled by the `IOToken` class.
 * None of the functions here should be available directly in Silver.
 * 
 * @author RandomActsOfGrammar
 */
public class RawProcessHandle {

    private Process proc;

    public RawProcessHandle(Process p) {
        proc = p;
    }

    public RawProcessHandle(List<String> cmd_args) {
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


    private String readAll(BufferedReader reader) {
        String output = "";
        char last_char;
        try {
            while (reader.ready()) {
                last_char = (char) reader.read();
                output = output + last_char;
            }
            return output;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Used for reading stdout from the process
    private BufferedReader our_stdout = null;
    /*Read a line from stdout of the process*/
    private String readLineStdout() {
        try {
            if (our_stdout == null) {
                InputStream instream = proc.getInputStream();
                InputStreamReader r = new InputStreamReader(instream);
                our_stdout = new BufferedReader(r);
            }
            return our_stdout.readLine();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*Read everything available from the process (empty string if nothing is available)*/
    private String readAllStdout() {
        try {
            if (our_stdout == null) {
                InputStream instream = proc.getInputStream();
                InputStreamReader r = new InputStreamReader(instream);
                our_stdout = new BufferedReader(r);
            }
            return readAll(our_stdout);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Used for reading stderr from the process
    private BufferedReader our_stderr = null;
    /*Read a line from stderr of the process*/
    private String readLineStderr() {
        try {
            if (our_stderr == null) {
                InputStream instream = proc.getErrorStream();
                InputStreamReader r = new InputStreamReader(instream);
                our_stderr = new BufferedReader(r);
            }
            return our_stderr.readLine();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*Read everything available in stderr from the process (empty string if nothing is available)*/
    private String readAllStderr() {
        try {
            if (our_stdout == null) {
                InputStream instream = proc.getInputStream();
                InputStreamReader r = new InputStreamReader(instream);
                our_stdout = new BufferedReader(r);
            }
            return readAll(our_stderr);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /*Write a message to stdin of the process*/
    private void writeString(String msg) {
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
    private void waitOnEnd() {
        try {
            proc.waitFor();
            return;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /*
      These are the public functions which use the Silver types and
      call the private functions that actually take care of the
      details.
     */

    /**
     * <pre>IOVal<ProcessHandle> ::= cmd::String args::[String] io::IO</pre>
     */
    public static NIOVal spawnProcess(StringCatter cmd, ConsCell args, IOToken io) {
        List<String> full_cmd = ConsCell.toList(args);
        full_cmd.add(0, cmd.toString());
        return io.wrap(new RawProcessHandle(full_cmd));
    }

    /**
     * <pre>IO ::= p::ProcessHandle msg::String io::IO</pre>
     */
    public IOToken sendToProcess(StringCatter msg, IOToken io) {
        this.writeString(msg.toString());
        return io;
    }

    /**
     * <pre>IOVal<String> ::= p::ProcessHandle io::IO</pre>
     */
    public NIOVal readLineFromProcess(IOToken io) {
        return io.wrap(new StringCatter(this.readLineStdout()));
    }

    /**
     * <pre>IOVal<String> ::= p::ProcessHandle io::IO</pre>
     */
    public NIOVal readAllFromProcess(IOToken io) {
        return io.wrap(new StringCatter(this.readAllStdout()));
    }

    /**
     * <pre>IOVal<String> ::= p::ProcessHandle io::IO</pre>
     */
    public NIOVal readErrLineFromProcess(IOToken io) {
        return io.wrap(new StringCatter(this.readLineStderr()));
    }

    /**
     * <pre>IOVal<String> ::= p::ProcessHandle io::IO</pre>
     */
    public NIOVal readErrAllFromProcess(IOToken io) {
        return io.wrap(new StringCatter(this.readAllStderr()));
    }

    /**
     * <pre>IO ::= p::ProcessHandle io::IO</pre>
     */
    public IOToken waitForProcess(IOToken io) {
        this.waitOnEnd();
        return io;
    }
}

