grammar core:monad;

type IOMonad<a> = State<IO a>;

abstract production bindIO
top::IOMonad<b> ::= st::IOMonad<a> fn::(IOMonad<b> ::= a)
{
  forwards to bindState(st, fn);
}

abstract production returnIO
top::IOMonad<a> ::= x::a
{
  forwards to returnState(x);
}

function runIO
IO ::= st::IOMonad<a> ioIn::IO
{
  return evalIO(st, ioIn).io;
}

function evalIO
IOVal<a> ::= st::IOMonad<a> ioIn::IO
{
  local res::Pair<IO a> = runState(st, ioIn);
  return ioval(res.fst, res.snd);
}

function unsafeEvalIO
a ::= st::IOMonad<a>
{
  return evalIO(st, unsafeIO()).iovalue;
}

-- Monadic IO wrappers
-- Some of these have no arguments and could be monad actions (i.e. globals instead of functions)
-- but everything is a function for the sake of consistancy. 
function printM
IOMonad<UnitT> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    setState(print(s, io));
  };
}

function readLineStdinM
IOMonad<String> ::=
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<String> = readLineStdin(io);
    setState(result.io);
    return result.iovalue;
  };
}

function exitM
IOMonad<UnitT> ::= val::Integer
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    setState(exit(val, io));
  };
}

function mkdirM
IOMonad<Boolean> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<Boolean> = mkdir(s, io);
    setState(result.io);
    return result.iovalue;
  };
}

function systemM
IOMonad<Integer> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<Integer> = system(s, io);
    setState(result.io);
    return result.iovalue;
  };
}

function writeFileM
IOMonad<UnitT> ::= file::String contents::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    setState(writeFile(file, contents, io));
  };
}

function appendFileM
IOMonad<UnitT> ::= file::String contents::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    setState(appendFile(file, contents, io));
  };
}

function fileTimeM
IOMonad<Integer> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<Integer> = fileTime(s, io);
    setState(result.io);
    return result.iovalue;
  };
}

function isFileM
IOMonad<Boolean> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<Boolean> = isFile(s, io);
    setState(result.io);
    return result.iovalue;
  };
}

function isDirectoryM
IOMonad<Boolean> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<Boolean> = isDirectory(s, io);
    setState(result.io);
    return result.iovalue;
  };
}

function readFileM
IOMonad<String> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<String> = readFile(s, io);
    setState(result.io);
    return result.iovalue;
  };
}

function cwdM
IOMonad<String> ::= 
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<String> = cwd(io);
    setState(result.io);
    return result.iovalue;
  };
}

function envVarM
IOMonad<String> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<String> = envVar(s, io);
    setState(result.io);
    return result.iovalue;
  };
}

function listContentsM
IOMonad<[String]> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<[String]> = listContents(s, io);
    setState(result.io);
    return result.iovalue;
  };
}

function deleteFileM
IOMonad<Boolean> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    result::IOVal<Boolean> = deleteFile(s, io);
    setState(result.io);
    return result.iovalue;
  };
}

function deleteTreeM
IOMonad<UnitT> ::= s::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    setState(deleteTree(s, io));
  };
}

function copyFileM
IOMonad<UnitT> ::= src::String dst::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    setState(copyFile(src, dst, io));
  };
}

function touchFileM
IOMonad<UnitT> ::= file::String
{
  return do (bindIO, returnIO) {
    io::IO <- getState();
    setState(touchFile(file, io));
  };
}