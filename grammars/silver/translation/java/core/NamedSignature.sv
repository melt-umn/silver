grammar silver:translation:java:core;

{--
 - The java translation of the *input parameters* signature.
 -}
synthesized attribute javaSignature :: String occurs on NamedSignature;
synthesized attribute reifyTrans :: String occurs on NamedSignature;
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
-- "signame"
synthesized attribute annoNameElem :: String occurs on NamedSignatureElement;
-- "if (name.equals("signame")) { return getAnno_signame(); }"
synthesized attribute annoLookupElem :: String occurs on NamedSignatureElement;
-- "common.Reflection.reify(child_transTypeRep, childASTs.get(i_child));"
synthesized attribute childReifyElem :: String occurs on NamedSignatureElement;
-- "common.Reflection.reify(anno_transTypeRep, annotationASTs.get("signame"));"
synthesized attribute annoReifyElem :: String occurs on NamedSignatureElement;

aspect production namedSignature
top::NamedSignature ::= fn::String ie::[NamedSignatureElement] oe::NamedSignatureElement np::[NamedSignatureElement]
{
  top.javaSignature = implode(", ", map((.childSigElem), ie) ++ map((.annoSigElem), np));
  top.reifyTrans = implode(", ", map((.childReifyElem), ie) ++ map((.annoReifyElem), np));
}

-- TODO: It'd be nice to maybe split these into the ordered parameters and the annotations
aspect production namedSignatureElement
top::NamedSignatureElement ::= n::String ty::Type
{
  top.childSigElem = "final Object c_" ++ n;
  top.childRefElem = "c_" ++ n;
  top.childDeclElem =
s"""	private Object child_${n};
	public final ${ty.transType} getChild_${n}() {
		return (${ty.transType}) (child_${n} = common.Util.demand(child_${n}));
	}

""";
  
  top.childStaticElem =
    if !ty.isDecorable then ""
    else s"\tchildInheritedAttributes[i_${n}] = new common.Lazy[${makeNTClassName(ty.typeName)}.num_inh_attrs];\n";
  
  top.childReifyElem = s"common.Reflection.reify(${ty.transTypeRep}, childASTs.get(i_${n}))";
  
  -- annos are full names:
  
  local fn :: String = makeIdName(n);
  
  top.annoSigElem = "final Object a_" ++ fn;
  top.annoRefElem = "a_" ++ fn;
  top.annoDeclElem =
s"""	private Object anno_${fn};
	@Override
	public final ${ty.transType} getAnno_${fn}() {
		return (${ty.transType}) (anno_${fn} = common.Util.demand(anno_${fn}));
	}

""";

  top.annoNameElem = s"\"${n}\"";
  top.annoLookupElem =
s"""if (name.equals("${n}")) {
			return getAnno_${fn}();
		} else """;
  
  top.annoReifyElem = s"common.Reflection.reify(${ty.transTypeRep}, annotationASTs.get(\"${n}\"))";
}

function makeIndexDcls
String ::= i::Integer s::[NamedSignatureElement]
{
  return if null(s) then ""
  else s"\tpublic static final int i_${head(s).elementName} = ${toString(i)};\n" ++ makeIndexDcls(i+1, tail(s));
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
  else (s"children[${toString(i)}]") :: unpackChildren(i + 1, tail(ns));
}
function unpackAnnotations
[String] ::= i::Integer  ns::[NamedSignatureElement]
{
  return if null(ns) then []
  else (s"annotations[${toString(i)}]") :: unpackAnnotations(i + 1, tail(ns));
}

function makeChildAccessCase
String ::= n::NamedSignatureElement
{
  return s"\t\t\tcase i_${n.elementName}: return getChild_${n.elementName}();\n";
}
function makeChildAccessCaseLazy
String ::= n::NamedSignatureElement
{
  return s"\t\t\tcase i_${n.elementName}: return child_${n.elementName};\n";
}

function makeAnnoAssign
String ::= n::NamedSignatureElement
{
  local fn :: String = makeIdName(n.elementName);
  return s"\t\tthis.anno_${fn} = a_${fn};\n";
}
function makeChildAssign
String ::= n::NamedSignatureElement
{
  return s"\t\tthis.child_${n.elementName} = c_${n.elementName};\n";
}
function makeChildUnify
String ::= fn::String n::NamedSignatureElement
{
  return
s"""if (((Object)getChild_${n.elementName}()) instanceof common.Typed) {
			if (!${n.typerep.transTypeRep}.unify(((common.Typed)child_${n.elementName}).getType(), false)) {
				throw new common.exceptions.SilverInternalError("Unification failed while constructing type for production ${fn} child ${n.elementName}");
			}
		} else {
			throw new common.exceptions.SilverError("Runtime type checking of production ${fn} expected child ${n.elementName} to be Typed, but found class " + child_${n.elementName}.getClass().getName() + ".");
		}
""";
}


