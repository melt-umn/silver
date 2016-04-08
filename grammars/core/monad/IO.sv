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

abstract production printM
top::IOMonad<a> ::= s::String
{
  top.stateOut = print(s, top.stateIn);
  top.stateVal = error("Unit value"); -- TODO
}

abstract production readLineStdinM
top::IOMonad<String> ::=
{
  local res::IOVal<String> = readLineStdin(top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production exitM
top::IOMonad<a> ::= val::Integer
{
  top.stateOut = exit(val, top.stateIn);
  top.stateVal = error("Unit value"); -- TODO
}

abstract production mkdirM
top::IOMonad<Boolean> ::= s::String
{
  local res::IOVal<Boolean> = mkdir(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production systemM
top::IOMonad<Integer> ::= s::String
{
  local res::IOVal<Integer> = system(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production writeFileM
top::IOMonad<a> ::= file::String contents::String
{
  top.stateOut = writeFile(file, contents, top.stateIn);
  top.stateVal = error("Unit value"); -- TODO
}

abstract production appendFileM
top::IOMonad<a> ::= file::String contents::String
{
  top.stateOut = appendFile(file, contents, top.stateIn);
  top.stateVal = error("Unit value"); -- TODO
}

abstract production fileTimeM
top::IOMonad<Integer> ::= s::String
{
  local res::IOVal<Integer> = fileTime(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production isFileM
top::IOMonad<Boolean> ::= s::String
{
  local res::IOVal<Boolean> = isFile(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production isDirectoryM
top::IOMonad<Boolean> ::= s::String
{
  local res::IOVal<Boolean> = isDirectory(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production readFileM
top::IOMonad<String> ::= s::String
{
  local res::IOVal<String> = readFile(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production cwdM
top::IOMonad<String> ::= 
{
  local res::IOVal<String> = cwd(top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production envVarM
top::IOMonad<String> ::= s::String
{
  local res::IOVal<String> = envVar(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production listContentsM
top::IOMonad<[String]> ::= s::String
{
  local res::IOVal<[String]> = listContents(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production deleteFileM
top::IOMonad<Boolean> ::= s::String
{
  local res::IOVal<Boolean> = deleteFile(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production deleteTreeM
top::IOMonad<a> ::= s::String
{
  top.stateOut = deleteTree(s, top.stateIn);
  top.stateVal = error("Unit value"); -- TODO
}

abstract production copyFileM
top::IOMonad<a> ::= src::String dst::String
{
  top.stateOut = copyFile(src, dst, top.stateIn);
  top.stateVal = error("Unit value"); -- TODO
}

abstract production touchFileM
top::IOMonad<a> ::= file::String
{
  top.stateOut = touchFile(file, top.stateIn);
  top.stateVal = error("Unit value"); -- TODO
}