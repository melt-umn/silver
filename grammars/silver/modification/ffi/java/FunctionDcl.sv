grammar silver:modification:ffi:java;

import silver:modification:ffi;
import silver:modification:ffi:util;
import silver:translation:java:core;
import silver:translation:java:type;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type;

synthesized attribute ffiTranslationString :: [String] occurs on FFIDef, FFIDefs;

aspect production ffidefsOne
top::FFIDefs ::= one::FFIDef
{
  top.ffiTranslationString = one.ffiTranslationString;
}

aspect production ffidefsMany
top::FFIDefs ::= one::FFIDef more::FFIDefs
{
  top.ffiTranslationString = one.ffiTranslationString ++ more.ffiTranslationString;
}

aspect production ffidef
top::FFIDef ::= name::String_t ':' 'return' code::String_t ';'
{
  top.ffiTranslationString = if name.lexeme == "\"java\"" then [cleanStringLexeme(code.lexeme)] else [];
}

function strictChildAccessor
String ::= ns::NamedSignatureElement
{
  return "((" ++ ns.typerep.transType ++ ")common.Util.demand(c_" ++ ns.elementName ++ "))";
}
function lazyChildAccessor
String ::= ns::NamedSignatureElement
{
  return "c_" ++ ns.elementName;
}

function computeSigTranslation
String ::= str::String sig::NamedSignature
{
  return substituteAll(
    substituteAll(str,
      map(wrapStrictNotation, sig.inputNames),
      map(strictChildAccessor, sig.inputElements)),
    map(wrapLazyNotation, sig.inputNames),
    map(lazyChildAccessor, sig.inputElements));
}


-- TODO: this needs clean up.
-- We should forward either to normal function declaration or special one, rather than IFing EVERYTHING in here!
aspect production functionDclFFI
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}'
{
  top.setupInh := if null(ffidefs.ffiTranslationString)
                  then forward.setupInh
                  else ""; -- hacky hacky!  -- TODO should these be there, or empty?
  top.initProd := if null(ffidefs.ffiTranslationString)
                  then forward.initProd
                  else ""; -- hacky hacky!  -- TODO should these be there, or empty?
  top.initValues := "";
  top.postInit := "";

  top.genFiles := if null(ffidefs.ffiTranslationString)
                    then forward.genFiles
                    else 
                    [pair("P" ++ id.name ++ ".java",
                      generateFunctionClassString(top.grammarName, id.name, namedSig, "return (" ++ namedSig.outputElement.typerep.transType ++ ")" ++ computeSigTranslation(head(ffidefs.ffiTranslationString), namedSig) ++ ";\n")
                    )];

  top.errors <- if length(ffidefs.ffiTranslationString) > 1
                then [err(ffidefs.location, "There is more than one Java translation in this FFI function")]
                else [];
}

