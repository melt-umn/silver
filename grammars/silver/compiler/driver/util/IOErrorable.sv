grammar silver:compiler:driver:util;

annotation code::Integer;
annotation errMsg::String;

data RunError = runError
  with code, errMsg;

-- A common return type for IO functions. Does IO and returns error or whatever.
type IOErrorable<a> = EitherT<RunError IO a>;

fun throwRunError IOErrorable<a> ::= c::Integer m::String = throwError(runError(code=c, errMsg=m));
