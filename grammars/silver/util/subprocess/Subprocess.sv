grammar silver:util:subprocess;


type ProcessHandle foreign = "common.rawlib.RawProcessHandle";


@{--
 - Start a subprocess to run in the background with which to communicate.
 - To run `cmd a1 a2`, call `spawnProcess("cmd", ["a1", "a2"])`
 -
 - @param cmd  The command to run
 - @param args  The arguments to pass to the command (separate from the command)
 - @param i  The IO token
 - @return  The handle to communicate with the process
-}
function spawnProcess
IOVal<ProcessHandle> ::= cmd::String args::[String] i::IOToken
{
  return error("Not Yet Implemented: spawnProcess");
} foreign {
  "java" : return "common.rawlib.RawProcessHandle.spawnProcess(%cmd%, %args%, %i%)";
}


@{--
 - Send a string message to a subprocess
 -
 - @param p  The process to which to send the message
 - @param msg  The message to send to the subprocess
 - @param i  The IO token
 - @return  The IO token
-}
function sendToProcess
IO ::= p::ProcessHandle msg::String i::IOToken
{
  return error("Not Yet Implemented:  sendToProcess");
} foreign {
  "java" : return "%p%.sendToProcess(%msg%, %i%)";
}


@{--
 - Read a line of output from a subprocess
 -
 - @param p  The process from which to read
 - @param i  The IO token
 - @return  The line which was read
-}
function readLineFromProcess
IOVal<String> ::= p::ProcessHandle i::IOToken
{
  return error("Not Yet Implemented:  readLineFromProcess");
} foreign {
  "java" : return "%p%.readLineFromProcess(%i%)";
}


@{--
 - Read everything available in the output from a subprocess.
 - Returns an empty string if nothing is available.
 -
 - @param p  The process from which to read
 - @param i  The IO token
 - @return  The line which was read
-}
function readAllFromProcess
IOVal<String> ::= p::ProcessHandle i::IOToken
{
  return error("Not Yet Implemented:  readLineFromProcess");
} foreign {
  "java" : return "%p%.readAllFromProcess(%i%)";
}


@{--
 - Read everything in the stdout from a subprocess until reaching the string "ending".
 - The ending string is included in the output.  If the process never outputs the
 - ending string to stdout, this function never returns.
 -
 - @param p  The process from which to read
 - @param ending  The string to end on
 - @param i  The IO token
 - @return  The line which was read
-}
function readUntilFromProcess
IOVal<String> ::= p::ProcessHandle ending::String i::IOToken
{
  return error("Not Yet Implemented:  readLineFromProcess");
} foreign {
  "java" : return "%p%.readUntilFromProcess(%i%, %ending%)";
}


@{--
 - Read a line of output from stderr of a subprocess
 -
 - @param p  The process from which to read
 - @param i  The IO token
 - @return  The line which was read
-}
function readErrLineFromProcess
IOVal<String> ::= p::ProcessHandle i::IOToken
{
  return error("Not Yet Implemented:  readLineFromProcess");
} foreign {
  "java" : return "%p%.readErrLineFromProcess(%i%)";
}


@{--
 - Read everything available in the stderr from a subprocess.
 - Returns an empty string if nothing is available.
 -
 - @param p  The process from which to read
 - @param i  The IO token
 - @return  The line which was read
-}
function readErrAllFromProcess
IOVal<String> ::= p::ProcessHandle i::IOToken
{
  return error("Not Yet Implemented:  readLineFromProcess");
} foreign {
  "java" : return "%p%.readErrAllFromProcess(%i%)";
}


@{--
 - Read everything in the stderr from a subprocess until reaching the string "ending".
 - The ending string is included in the output.  If the process never outputs the
 - ending string to stderr, this function never returns.
 -
 - @param p  The process from which to read
 - @param ending  The string to end on
 - @param i  The IO token
 - @return  The line which was read
-}
function readErrUntilFromProcess
IOVal<String> ::= p::ProcessHandle ending::String i::IOToken
{
  return error("Not Yet Implemented:  readLineFromProcess");
} foreign {
  "java" : return "%p%.readErrUntilFromProcess(%i%, %ending%)";
}


@{--
 - Wait for a running subprocess to end.  There should be a reason to
 - expect it to end; this does not kill it.
 -
 - @param p  The process for which to wait
 - @param i  The IO token
 - @return  The IO token
-}
function waitForProcess
IO ::= p::ProcessHandle i::IOToken
{
  return error("Not Yet Implemented:  waitForProcess");
} foreign {
  "java" : return "%p%.waitForProcess(%i%)";
}

