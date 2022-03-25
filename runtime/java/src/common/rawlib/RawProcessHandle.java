package common.rawlib;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

import common.IOToken;
import common.ConsCell;
import common.javainterop.ConsCellCollection;
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

    //The underlying process with which we are communicating
    private Process proc;
    //Used for reading stdout from proc
    private BufferedReader our_stdout = null;
    //Used for reading stderr from proc
    private BufferedReader our_stderr = null;


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


    /*Check if stdout has been initialized and initialize it*/
    private void checkInitializeStdout() {
        if (our_stdout == null) {
            InputStream instream = proc.getInputStream();
            InputStreamReader r = new InputStreamReader(instream);
            our_stdout = new BufferedReader(r);
        }
    }

    /*Check if stderr has been initialized and initialize it*/
    private void checkInitializeStderr() {
        if (our_stderr == null) {
            InputStream instream = proc.getErrorStream();
            InputStreamReader r = new InputStreamReader(instream);
            our_stderr = new BufferedReader(r);
        }
    }


    /*Read everything available from a given reader*/
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

    /*Read everything from a given reader until it ends with ending*/
    private String readUntil(BufferedReader reader, String ending) {
        String output = "";
        char last_char;
        try {
            while (!output.endsWith(ending)) {
                last_char = (char) reader.read();
                output = output + last_char;
            }
            return output;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /*Read a line from stdout of the process*/
    private String readLineStdout() {
        try {
            checkInitializeStdout();
            return our_stdout.readLine();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*Read everything available from the process (empty string if nothing is available)*/
    private String readAllStdout() {
        try {
            checkInitializeStdout();
            return readAll(our_stdout);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*Read everything from stdout until the read string ends with ending*/
    private String readUntilStdout(String ending) {
        try {
            checkInitializeStdout();
            return readUntil(our_stdout, ending);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /*Read a line from stderr of the process*/
    private String readLineStderr() {
        try {
            checkInitializeStderr();
            return our_stderr.readLine();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*Read everything available in stderr from the process (empty string if nothing is available)*/
    private String readAllStderr() {
        try {
            checkInitializeStderr();
            return readAll(our_stderr);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*Read everything from stderr until the read string ends with ending*/
    private String readUntilStderr(String ending) {
        try {
            checkInitializeStderr();
            return readUntil(our_stderr, ending);
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
        List<String> full_cmd = new ArrayList<String>();
        full_cmd.add(cmd.toString());
        //Convert all the args to regular Strings instead of StringCatter
        for (StringCatter arg : new ConsCellCollection<StringCatter>(args)) {
            full_cmd.add(arg.toString());
        }
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
    public NIOVal readUntilFromProcess(IOToken io, StringCatter s) {
        return io.wrap(new StringCatter(this.readUntilStdout(s.toString())));
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
     * <pre>IOVal<String> ::= p::ProcessHandle io::IO</pre>
     */
    public NIOVal readErrUntilFromProcess(IOToken io, StringCatter s) {
        return io.wrap(new StringCatter(this.readUntilStdout(s.toString())));
    }

    /**
     * <pre>IO ::= p::ProcessHandle io::IO</pre>
     */
    public IOToken waitForProcess(IOToken io) {
        this.waitOnEnd();
        return io;
    }
}

