grammar silver:translation:java:core;

{--
 - The java translation of the *input parameters* signature.
 -}
synthesized attribute javaSignature :: String occurs on NamedSignature;
-- "final Object c_signame"
synthesized attribute childSigElem :: String occurs on NamedSignatureElement;
synthesized attribute annoSigElem :: String occurs on NamedSignatureElement;
-- "c_signame"
synthesized attribute childRefElem :: String occurs on NamedSignatureElement;
synthesized attribute annoRefElem :: String occurs on NamedSignatureElement;
-- "inhs[c_signame] = new lazy[]"
synthesized attribute childStaticElem :: String occurs on NamedSignatureElement;
-- "private Object child_signame..."
synthesized attribute childDeclElem :: String occurs on NamedSignatureElement;
synthesized attribute annoDeclElem :: String occurs on NamedSignatureElement;

aspect production namedSignature
top::NamedSignature ::= fn::String ie::[NamedSignatureElement] oe::NamedSignatureElement np::[NamedSignatureElement]
{
  top.javaSignature = implode(", ", map((.childSigElem), ie) ++ map((.annoSigElem), np));
}

-- TODO: It'd be nice to maybe split these into the ordered parameters and the annotations
aspect production namedSignatureElement
top::NamedSignatureElement ::= n::String ty::TypeExp
{
  top.childSigElem = "final Object c_" ++ n;
  top.childRefElem = "c_" ++ n;
  top.childDeclElem =
    "\tprivate Object child_" ++ n ++ ";\n" ++
    "\tpublic final " ++ ty.transType ++ " getChild_" ++ n ++ "() {\n" ++
    "\t\treturn (" ++ ty.transType ++ ") (child_" ++ n ++ " = common.Util.demand(child_" ++ n ++ "));\n" ++
    "\t}\n\n";
  
  top.childStaticElem =
    if !ty.isDecorable then ""
    else "\tchildInheritedAttributes[i_" ++ n ++ "] = " ++ 
           "new common.Lazy[" ++ makeNTClassName(ty.typeName) ++ ".num_inh_attrs];\n";
  
  -- annos are full names:
  
  local fn :: String = makeIdName(n);
  
  top.annoSigElem = "final Object a_" ++ fn;
  top.annoRefElem = "a_" ++ fn;
  top.annoDeclElem =
    "\tprivate Object anno_" ++ fn ++ ";\n" ++
    "\t@Override\n" ++
    "\tpublic final " ++ ty.transType ++ " getAnno_" ++ fn ++ "() {\n" ++
    "\t\treturn (" ++ ty.transType ++ ") (anno_" ++ fn ++ " = common.Util.demand(anno_" ++ fn ++ "));\n" ++
    "\t}\n\n";
}

function makeIndexDcls
String ::= i::Integer s::[NamedSignatureElement]
{
  return if null(s) then ""
  else "\tpublic static final int i_" ++ head(s).elementName ++ " = " ++ toString(i) ++ ";\n" ++ makeIndexDcls(i+1, tail(s));
}

-- TODO I'd really like to just get rid of this.
function makeChildTypes
String ::= ns::NamedSignatureElement
{ return ns.typerep.transClassType ++ ".class";
}

function unpackChildren
[String] ::= i::Integer  ns::[NamedSignatureElement]
{
  return if null(ns) then []
  else ("children[" ++ toString(i) ++ "]") :: unpackChildren(i + 1, tail(ns));
}
function unpackAnnotations
[String] ::= i::Integer  ns::[NamedSignatureElement]
{
  return if null(ns) then []
  else ("annotations[" ++ toString(i) ++ "]") :: unpackAnnotations(i + 1, tail(ns));
}

function makeChildAccessCase
String ::= n::NamedSignatureElement
{
  return "\t\t\tcase i_" ++ n.elementName ++ ": return getChild_" ++ n.elementName ++ "();\n";
}
function makeChildAccessCaseLazy
String ::= n::NamedSignatureElement
{
  return "\t\t\tcase i_" ++ n.elementName ++ ": return child_" ++ n.elementName ++ ";\n";
}

function makeAnnoAssign
String ::= n::NamedSignatureElement
{
  local fn :: String = makeIdName(n.elementName);
  return "\t\tthis.anno_" ++ fn ++ " = a_" ++ fn ++ ";\n";
}
function makeChildAssign
String ::= n::NamedSignatureElement
{
  return "\t\tthis.child_" ++ n.elementName ++ " = c_" ++ n.elementName ++ ";\n";
}

