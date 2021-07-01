grammar silver:compiler:translation:java:core;

{--
 - The java translation of the *input parameters* signature.
 -}
synthesized attribute javaSignature :: String occurs on NamedSignature;
synthesized attribute refInvokeTrans :: String occurs on NamedSignature;
-- "final ContextName d_contextname"
synthesized attribute contextSigElems :: [String] occurs on Contexts;
synthesized attribute contextSigElem :: String occurs on Context;
-- "final Object c_signame"
synthesized attribute childSigElems :: [String] occurs on NamedSignatureElements;
synthesized attribute annoSigElems :: [String] occurs on NamedSignatureElements;
synthesized attribute childSigElem :: String occurs on NamedSignatureElement;
synthesized attribute annoSigElem :: String occurs on NamedSignatureElement;
-- "d_contextname"
synthesized attribute contextRefElems :: [String] occurs on Contexts;
synthesized attribute contextRefElem :: String occurs on Context;
-- "c_signame"
synthesized attribute childRefElems :: [String] occurs on NamedSignatureElements;
synthesized attribute childRefElem :: String occurs on NamedSignatureElement;
synthesized attribute annoRefElems :: [String] occurs on NamedSignatureElements;
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

aspect production namedSignature
top::NamedSignature ::= fn::String ctxs::Contexts ie::NamedSignatureElements oe::NamedSignatureElement np::NamedSignatureElements
{
  top.javaSignature = implode(", ", ctxs.contextSigElems ++ ie.childSigElems ++ np.annoSigElems);
  top.refInvokeTrans = implode(", ", ctxs.contextRefElems ++ ie.childRefElems ++ np.annoRefElems);
}

aspect production consContext
top::Contexts ::= h::Context t::Contexts
{
  top.contextSigElems = h.contextSigElem :: t.contextSigElems;
  top.contextRefElems = h.contextRefElem :: t.contextRefElems;
}
aspect production nilContext
top::Contexts ::=
{
  top.contextSigElems = [];
  top.contextRefElems = [];
}

aspect production instContext
top::Context ::= fn::String t::Type
{
  top.contextSigElem = s"final ${top.transType} ${makeConstraintDictName(fn, t, top.boundVariables)}";
  top.contextRefElem = makeConstraintDictName(fn, t, top.boundVariables);
}

aspect production typeableContext
top::Context ::= t::Type
{
  top.contextSigElem = s"final ${top.transType} ${makeTypeableName(t, top.boundVariables)}";
  top.contextRefElem = makeTypeableName(t, top.boundVariables);
}

aspect production inhSubsetContext
top::Context ::= i1::Type i2::Type
{
  top.contextSigElem = s"final ${top.transType} ${makeInhSubsetName(i1, i2, top.boundVariables)}";
  top.contextRefElem = makeInhSubsetName(i1, i2, top.boundVariables);
}

aspect production consNamedSignatureElement
top::NamedSignatureElements ::= h::NamedSignatureElement t::NamedSignatureElements
{
  top.childSigElems = h.childSigElem :: t.childSigElems;
  top.annoSigElems = h.annoSigElem :: t.annoSigElems;
  top.childRefElems = h.childRefElem :: t.childRefElems;
  top.annoRefElems = h.annoRefElem :: t.annoRefElems;
}

aspect production nilNamedSignatureElement
top::NamedSignatureElements ::=
{
  top.childSigElems = [];
  top.annoSigElems = [];
  top.childRefElems = [];
  top.annoRefElems = [];
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
    else s"\tchildInheritedAttributes[i_${n}] = new common.Lazy[${makeNTName(ty.typeName)}.num_inh_attrs];\n";
  
  -- annos are full names:
  
  local fn :: String = makeIdName(n);
  
  top.annoSigElem = "final Object a_" ++ fn;
  top.annoRefElem = "a_" ++ fn;
  top.annoDeclElem =
s"""	protected Object anno_${fn};
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
}

function makeIndexDcls
String ::= i::Integer s::[NamedSignatureElement]
{
  return if null(s) then ""
  else s"\tpublic static final int i_${head(s).elementName} = ${toString(i)};\n" ++ makeIndexDcls(i+1, tail(s));
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

function makeTyVarDecls
String ::= indent::Integer vars::[TyVar]
{
  return
    implode(
      "\n",
      map(
        \ tv::TyVar ->
          s"${concat(repeat("\t", indent))}common.VarTypeRep freshTypeVar_${toString(tv.extractTyVarRep)} = new common.VarTypeRep();",
          vars));
    
}
function makeAnnoIndexDcls
String ::= i::Integer s::[NamedSignatureElement]
{
  return if null(s) then ""
  else s"\t\tfinal int i${head(s).annoRefElem} = ${toString(i)};\n" ++ makeAnnoIndexDcls(i+1, tail(s));
}
function makeChildUnify
String ::= fn::String n::NamedSignatureElement
{
  return
s"""try {
			if (!common.TypeRep.unify(${n.typerep.transFreshTypeRep}, common.Reflection.getType(getChild_${n.elementName}()))) {
				throw new common.exceptions.SilverInternalError("Unification failed.");
			}
		} catch (common.exceptions.SilverException e) {
			throw new common.exceptions.TraceException("While constructing type of child '${n.elementName}' of production '${fn}'", e);
		}
""";
}
function makeChildReify
String ::= fn::String numChildren::Integer n::NamedSignatureElement
{
  return
s"""Object ${n.childRefElem} = null;
    try {
      ${n.childRefElem} = common.Reflection.reify(rules, ${n.typerep.transFreshTypeRep}, childASTs[i_${n.elementName}]);
    } catch (common.exceptions.SilverException e) {
      throw new common.exceptions.ChildReifyTraceException("${fn}", "${n.elementName}", ${toString(numChildren)}, i_${n.elementName}, e);
    }
""";
}
function makeAnnoReify
String ::= fn::String n::NamedSignatureElement
{
  return
s"""Object ${n.annoRefElem} = null;
    try {
      ${n.annoRefElem} = common.Reflection.reify(rules, ${n.typerep.transFreshTypeRep}, annotationASTs[i${n.annoRefElem}]);
    } catch (common.exceptions.SilverException e) {
      throw new common.exceptions.AnnotationReifyTraceException("${fn}", "${n.elementName}", e);
    }
""";
}

function makeConstructDirectChildren
String ::= x::NamedSignatureElement
{
  return
s"""Object ${x.childRefElem} = children[counter];
    counter++;
""";
}
function makeConstructDirectAnno
String ::= x::NamedSignatureElement
{
  return
s"""Object ${x.annoRefElem} = annos[counter];
    counter++;
""";
}


