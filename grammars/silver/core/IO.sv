grammar silver:core;

nonterminal IO<a> with stateIn<IOToken>, stateOut<IOToken>, stateVal<a>;

abstract production bindIO
top::IO<b> ::= st::IO<a> fn::(IO<b> ::= a)
{
  st.stateIn = top.stateIn;
  local newState::IO<b> = fn(st.stateVal);
  newState.stateIn = st.stateOut;
  local stateOut::IOToken = newState.stateOut;
  local stateVal::b = newState.stateVal;
  -- Using unsafeTrace here to demand st is evaluated before evaluating fn
  top.stateOut = unsafeTrace(stateOut, st.stateOut);
  top.stateVal = unsafeTrace(stateVal, st.stateOut);
}


abstract production returnIO
top::IO<a> ::= x::a
{
  top.stateOut = top.stateIn;
  top.stateVal = x;
}

instance Functor IO {
  map = liftM1;
}

instance Apply IO {
  ap = apM;
}

instance Applicative IO {
  pure = returnIO;
}

instance Bind IO {
  bind = bindIO;
}

instance Monad IO {}

function runIO
IOToken ::= st::IO<a> ioIn::IOToken
{
  return evalIO(st, ioIn).io;
}

function evalIO
IOVal<a> ::= st::IO<a> ioIn::IOToken
{
  st.stateIn = ioIn;
  return ioval(st.stateOut, st.stateVal);
}

function unsafeEvalIO
a ::= st::IO<a>
{
  return evalIO(st, unsafeIO()).iovalue;
}

-- Monadic IO wrappers
abstract production print
top::IO<Unit> ::= s::String
{
  top.stateOut = printT(s, top.stateIn);
  top.stateVal = unit();
}

abstract production readLineStdin
top::IO<Maybe<String>> ::=
{
  local res::IOVal<Maybe<String>> = readLineStdinT(top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

-- Having a polymorphic return type lets us write code like:
--
--   if !null(errs) {
--     print(showErrs(errs));
--     exit(1);
--   } else {
--     return value;
--   }
abstract production exit
top::IO<a> ::= val::Integer
{
  top.stateOut = exitT(val, top.stateIn);
  top.stateVal = error("stateOut should've been evaluated first?");
}

abstract production mkdir
top::IO<Boolean> ::= s::String
{
  local res::IOVal<Boolean> = mkdirT(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production system
top::IO<Integer> ::= s::String
{
  local res::IOVal<Integer> = systemT(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production writeFile
top::IO<Unit> ::= file::String contents::String
{
  top.stateOut = writeFileT(file, contents, top.stateIn);
  top.stateVal = unit();
}

abstract production writeBinaryFile
top::IO<Unit> ::= file::String contents::ByteArray
{
  top.stateOut = writeBinaryFileT(file, contents, top.stateIn);
  top.stateVal = unit();
}

abstract production appendFile
top::IO<Unit> ::= file::String contents::String
{
  top.stateOut = appendFileT(file, contents, top.stateIn);
  top.stateVal = unit();
}

abstract production fileTime
top::IO<Integer> ::= s::String
{
  local res::IOVal<Integer> = fileTimeT(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production isFile
top::IO<Boolean> ::= s::String
{
  local res::IOVal<Boolean> = isFileT(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production isDirectory
top::IO<Boolean> ::= s::String
{
  local res::IOVal<Boolean> = isDirectoryT(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production readFile
top::IO<String> ::= s::String
{
  local res::IOVal<String> = readFileT(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production readBinaryFile
top::IO<ByteArray> ::= s::String
{
  local res::IOVal<ByteArray> = readBinaryFileT(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production cwd
top::IO<String> ::=
{
  local res::IOVal<String> = cwdT(top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production envVar
top::IO<String> ::= s::String
{
  local res::IOVal<String> = envVarT(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production listContents
top::IO<[String]> ::= s::String
{
  local res::IOVal<[String]> = listContentsT(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production deleteFile
top::IO<Boolean> ::= s::String
{
  local res::IOVal<Boolean> = deleteFileT(s, top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

abstract production deleteTree
top::IO<Unit> ::= s::String
{
  top.stateOut = deleteTreeT(s, top.stateIn);
  top.stateVal = unit();
}

abstract production copyFile
top::IO<Unit> ::= src::String dst::String
{
  top.stateOut = copyFileT(src, dst, top.stateIn);
  top.stateVal = unit();
}

abstract production touchFile
top::IO<Unit> ::= file::String
{
  top.stateOut = touchFileT(file, top.stateIn);
  top.stateVal = unit();
}
