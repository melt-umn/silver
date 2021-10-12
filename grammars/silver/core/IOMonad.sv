grammar silver:core;

nonterminal IOMonad<a> with stateIn<IO>, stateOut<IO>, stateVal<a>;

abstract production bindIO
top::IOMonad<b> ::= st::IOMonad<a> fn::(IOMonad<b> ::= a)
{
  st.stateIn = top.stateIn;
  local newState::IOMonad<b> = fn(st.stateVal);
  newState.stateIn = st.stateOut;
  local stateOut::IO = newState.stateOut;
  local stateVal::b = newState.stateVal;
  
  -- Using unsafeTrace here to demand st is evaluated before evaluating fn
  top.stateOut = unsafeTrace(stateOut, st.stateOut);
  top.stateVal = unsafeTrace(stateVal, st.stateOut);
}

abstract production returnIO
top::IOMonad<a> ::= x::a
{
  top.stateOut = top.stateIn;
  top.stateVal = x;
}

instance Functor IOMonad {
  map = liftM1;
}

instance Apply IOMonad {
  ap = apM;
}

instance Applicative IOMonad {
  pure = returnIO;
}

instance Bind IOMonad {
  bind = bindIO;
}

instance Monad IOMonad {}

function runIO
IO ::= st::IOMonad<a> ioIn::IO
{
  return evalIO(st, ioIn).io;
}

function evalIO
IOVal<a> ::= st::IOMonad<a> ioIn::IO
{
  st.stateIn = ioIn;
  return ioval(st.stateOut, st.stateVal);
}

function unsafeEvalIO
a ::= st::IOMonad<a>
{
  return evalIO(st, unsafeIO()).iovalue;
}

-- Monadic IO wrappers
abstract production printM
top::IOMonad<Unit> ::= s::String
{
  top.stateOut = print(s, top.stateIn);
  top.stateVal = unit();
}

abstract production readLineStdinM
top::IOMonad<Maybe<String>> ::=
{
  local res::IOVal<Maybe<String>> = readLineStdin(top.stateIn);
  top.stateOut = res.io;
  top.stateVal = res.iovalue;
}

-- Having a polymorphic return type lets us write code like:
--
--   if !null(errs) {
--     printM(showErrs(errs));
--     exitM(1);
--   } else {
--     return value;
--   }
abstract production exitM
top::IOMonad<a> ::= val::Integer
{
  top.stateOut = exit(val, top.stateIn);
  top.stateVal = error("stateOut should've been evaluated first?");
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
top::IOMonad<Unit> ::= file::String contents::String
{
  top.stateOut = writeFile(file, contents, top.stateIn);
  top.stateVal = unit();
}

abstract production writeBinaryFileM
top::IOMonad<Unit> ::= file::String contents::ByteArray
{
  top.stateOut = writeBinaryFile(file, contents, top.stateIn);
  top.stateVal = unit();
}

abstract production appendFileM
top::IOMonad<Unit> ::= file::String contents::String
{
  top.stateOut = appendFile(file, contents, top.stateIn);
  top.stateVal = unit();
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

abstract production readBinaryFileM
top::IOMonad<ByteArray> ::= s::String
{
  local res::IOVal<ByteArray> = readBinaryFile(s, top.stateIn);
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
top::IOMonad<Unit> ::= s::String
{
  top.stateOut = deleteTree(s, top.stateIn);
  top.stateVal = unit();
}

abstract production copyFileM
top::IOMonad<Unit> ::= src::String dst::String
{
  top.stateOut = copyFile(src, dst, top.stateIn);
  top.stateVal = unit();
}

abstract production touchFileM
top::IOMonad<Unit> ::= file::String
{
  top.stateOut = touchFile(file, top.stateIn);
  top.stateVal = unit();
}
