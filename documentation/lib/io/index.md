---
layout: sv_wiki
title: IO
---

> _**Note:**_ Silver now has support for Monads, so using these IO functions directly is now depricated.  See [here](/documentation/concepts/monads/index.md) for more details.  

# Input and Output

There are no real values of type _`IO`_, instead, this is a "token" value
that is passed around in order to preserve the order of execution of
imperative IO actions.  You can think of _`IO`_ values as being the entire
universe, and the _`IO`_ input to a function is the state before the action
and the _`IO`_ output of a function is the state after the action.

Since many IO functions will want to return more than the resulting _`IO`_
token, there is a standard type called _`IOVal<a>`_ that IO functions use
to return values of other types in addition.

_`IOVal`_ has only one constructor, _`ioval`_.

```
abstract production ioval
top::IOVal<a> ::= i::IO v::a
```

The first parameter is the IO token, and the second is the value to wrap up.

> _**Note:**_ There is an unofficial convention for Silver standard library functions to place the "primary" value last.  _`ioval`_ arguably violates this convention, for legacy reasons.

> _**Example:**_
```
function main
IOVal<Integer> ::= args::[String] ioin::IO
{
  return ioval(print("Hi\n", ioin), 0);
}
```
> is a Silver program that prints _`"Hi"`_, and exits with a successful error code.

## Important IO functions

### print
```
function print
IO ::= s::String i::IO
```

Displays a string on standard out. Newlines are NOT automatically added.

> _**Example:**_
```
print("world!\n", print("Hello, ", ioin))
```
> will print _`"Hello, world!"`_.

### readFile
```
function readFile
IOVal<String> ::= s::String i::IO
```

Read the entire contents of a file.  All instances of "\r\n" are replaced by "\n"
to work around issues on windows.  If the read fails, an uncatchable error
is thrown.

> _**Example:**_
```
local attribute contents :: IOVal<String>;
contents = readFile(head(args), ioin);
```
> will store the contents of the first argument into _`contents`_.

### writeFile
```
function writeFile
IO ::= file::String contents::String i::IO
```
Write a string to a file, replacing whatever is there already. If the write fails, an uncatchable error is thrown.

> _**Example:**_
```
writeFile("output.txt", tree.pp, ioin)
```
> will write _`tree`_'s pretty print to _`"output.txt"`_.

### readLineStdin
```
function readFile
IOVal<String> ::= i::IO
```

Reads a line from standard input.  If the read fails, an uncatchable error is thrown.

> _**Example:**_
```
local attribute text :: IOVal<String>;
contents = readLineStdIn(ioin);
```
> will store one line from stdin into _`text`_.

## Other IO functions

### exit
```
function exit
IO ::= val::Integer i::IO
```

Terminates immediately with the specified error code.

> _**Example:**_
```
print("Hi", exit(-1, ioin))
```
> will terminate with an error before ever printing the string.

### mkdir
```
function mkdir
IOVal<Boolean> ::= s::String i::IO
```

Creates a directory, including any parents that need to be created along the way.
Similar to 'mkdir -p'. If it fails, it may create only some of them.

Returns true if successful, otherwise returns false.

> _**Example:**_
```
mkdir("a/sub/directory", ioin)
```
> will create _`./a/sub/directory/`_.

### system
```
function system
IOVal<Integer> ::= s::String i::IO
```

Executes a shell command.  Specifically executes _`bash -c`_. (And thus, may not
work on windows, unless executing under cygwin or similar.)

Avoid using this if possible.  If you need to perform some ordinary form of IO Silver does not yet support, please request it on the \texttt{melt-help@cs.umn.edu} mailing list.

Access to command's output is not directly available, but it is run in a shell. You can redirect to a file and read that.

> _**Example:**_
```
system("silver some:grammar && ant > stdout.txt 2> stderr.txt", ioin)
```
> will run the commands, and leave their output in some files.

### appendFile
```
function appendFile
IO ::= file::String contents::String i::IO
```

Unlike [writeFile](Reference_IO#writeFile.md), appends
the string to the end of the file instead of truncating the file first.

> _**Example:**_
```
appendFile("log.txt", "Oh, my.\n", ioin)
```
> add _`"Oh, my."`_ to the end of _`log.txt`_.

### fileTime
```
function fileTime
IOVal<Integer> ::= s::String i::IO
```

The time, in seconds since 1970, when this file (or directory) was last modified.
0 if the file is not found.

> _**Example:**_
```
fileTime("log.txt", ioin)
```
> returns the time the file was last modified (wrapped.)

### isFile
```
function isFile
IOVal<Boolean> ::= s::String i::IO
```

Checks if a file is an ordinary file.  (non-directory, non-special)

> _**Example:**_
```
isFile("/etc/passwd", ioin)
```
> returns is true (wrapped). On linux, anyway.

### isDirectory
```
function isDirectory
IOVal<Boolean> ::= s::String i::IO
```

Checks if a path is a directory.

> _**Example:**_
```
isDirectory("/etc/passwd", ioin)
```
> returns is false (wrapped.)

### cwd
```
function cwd
IOVal<String> ::= i::IO
```

Return the current working directory. (There is no way to change it.)

> _**Example:**_
```
cwd(ioin)
```
> returns the directory the program was run from (wrapped.)

### envVar
```
function envVar
IOVal<String> ::= s::String i::IO
```

Obtains the value of an environment variable. (There is no way to set them.)

> _**Example:**_
```
envVar("GRAMMAR_PATH", ioin)
```
> returns the value of the grammar path environment variable (wrapped.)

### listContents
```
function listContents
IOVal<[String]> ::= s::String i::IO
```

List the contents of a directory. Returns the empty list if not a directory or
other IO error.

> _**Example:**_
```
listContents(".", ioin)
```
> returns a list of the files (or directories) in the current directory (wrapped.)

### deleteFile
```
function deleteFile
IOVal<Boolean> ::= s::String i::IO
```

Delete a file, or an empty directory. Returns false if an error occurs, or the
file does not exist (or the directory is non-empty.)

> _**Example:**_
```
deleteFile("doesNotExist.txt", ioin)
```
> will return false (wrapped), or delete that file.


## Circumventing the IO token

Sometimes, it is necessary to take some action in a place where IO is not available. This occurs most often when some internal error occurs, or when attempting to debug an attribute grammar specification.

### error
```
function error
a ::= msg::String
```

Die with the stated error message and a stack trace when evaluated. Note that Silver stacks may be hard to read (it's a lazy language.)

Note that _`IO`_ is not involved at all.

> _**Example:**_
```
error("Not yet implemented!")
```
> terminates with the error message.

### genInt
```
function genInt
Integer ::= 
```

Generate an integer unique to this run of this process.  (Starts from 0 and just counts up each call.)  This violates the "purity" of Silver code, but it's incredibly convenient! :)

> _**Example:**_
```
abstract production foo
top::Expr ::=
{
  production attribute myid::String;
  myid = "foo#" ++ toString(genInt());
}
```
> assigns a unique (to this run of the process) id for each instance of the production _`foo`_.

### unsafeTrace / unsafeIO
```
function unsafeTrace
a ::= val::a act::IO

function unsafeIO
IO ::= 
```

When an expression is evaluated by the runtime system, executes the actions associated with the IO token _`act`_ (a sequence that should be started by calling _`unsafeIO()`_), and then simply returns the value _`val`_.

> _**Example:**_
```
unsafeTrace(foo.pp, print("foo's pp is " ++ foo.pp, unsafeIO()))
```
> prints out _`foo`_'s pretty print, before returning it.

> _**Example:**_ This usage is STRONGLY DISCOURAGED, but may sometimes be necessary during debugging or testing.
```
readFile("something.txt", unsafeIO()).iovalue
```
> evaluates to the contents of a file, without having any IO tokens around.

# TODO

Some of this page should be in a Concept\_IO, and the rest is probably just library documentation, but move it here for now.
