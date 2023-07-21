grammar silver:compiler:translation:java:core;

import silver:compiler:definition:flow:ast;

{--
 - The java translation of the *input parameters* signature.
 -}
synthesized attribute javaSignature :: String occurs on NamedSignature;
synthesized attribute refInvokeTrans :: String occurs on NamedSignature;
-- "final ContextName d_contextname"
synthesized attribute contextSigElems :: [String] occurs on Contexts;
synthesized attribute contextSigElem :: String occurs on Context;
-- Track what children with inh occurs-on contexts need to have indices generated
monoid attribute contextInhOccurs :: [(Type, String)] occurs on NamedSignature, Contexts, Context;
inherited attribute sigInhOccurs :: [(Type, String)] occurs on NamedSignatureElements, NamedSignatureElement;
synthesized attribute inhOccursContextTypes :: [Type] occurs on NamedSignature;
monoid attribute inhOccursIndexDecls :: String occurs on NamedSignature, Contexts, Context;
-- Track what children can be used to resolve contexts at runtime
monoid attribute typeChildren :: [(Type, String)] occurs on NamedSignatureElements, NamedSignatureElement;
inherited attribute typeChildrenIn :: [(Type, String)] occurs on Contexts, Context;
monoid attribute contextRuntimeResolve :: String occurs on NamedSignature, Contexts, Context;
synthesized attribute contextRuntimeResolveFailure :: String occurs on Context;
-- "final Object c_signame"
synthesized attribute childSigElems :: [String] occurs on NamedSignatureElements;
synthesized attribute annoSigElems :: [String] occurs on NamedSignatureElements;
synthesized attribute childSigElem :: String occurs on NamedSignatureElement;
synthesized attribute annoSigElem :: String occurs on NamedSignatureElement;
-- "d_contextname"
synthesized attribute contextRefElems :: [String] occurs on NamedSignature, Contexts;
synthesized attribute contextRefElem :: String occurs on Context;
-- "c_signame"
synthesized attribute childRefElems :: [String] occurs on NamedSignatureElements;
synthesized attribute childRefElem :: String occurs on NamedSignatureElement;
synthesized attribute annoRefElems :: [String] occurs on NamedSignatureElements;
synthesized attribute annoRefElem :: String occurs on NamedSignatureElement;
-- "type_a" or "-1"
synthesized attribute childTypeVarElems :: [String] occurs on NamedSignature, NamedSignatureElements;
synthesized attribute childTypeVarElem :: String occurs on NamedSignatureElement;
-- "inhs[c_signame] = new lazy[]"
synthesized attribute childStaticElem :: String occurs on NamedSignatureElement;
synthesized attribute childStatic :: String occurs on NamedSignature, NamedSignatureElements;
-- "private Object child_signame..."
synthesized attribute childDeclElem :: String occurs on NamedSignatureElement;
synthesized attribute annoDeclElem :: String occurs on NamedSignatureElement;
synthesized attribute childDecls :: String occurs on NamedSignature, NamedSignatureElements;
-- \"sig:name\"
synthesized attribute annoNameElem :: String occurs on NamedSignatureElement;
-- getAnno_signame()
synthesized attribute annoAccessorElem :: String occurs on NamedSignatureElement;
-- "if (name.equals("signame")) { return getAnno_signame(); }"
synthesized attribute annoLookupElem :: String occurs on NamedSignatureElement;

propagate sigInhOccurs on NamedSignatureElements, NamedSignatureElement;

aspect production namedSignature
top::NamedSignature ::= fn::String ctxs::Contexts ie::NamedSignatureElements oe::NamedSignatureElement np::NamedSignatureElements
{
  top.javaSignature = implode(", ", ctxs.contextSigElems ++ ie.childSigElems ++ np.annoSigElems);
  top.refInvokeTrans = implode(", ", ctxs.contextRefElems ++ ie.childRefElems ++ np.annoRefElems);
  top.contextRefElems = ctxs.contextRefElems;
  top.childTypeVarElems = ie.childTypeVarElems;
  top.childStatic = ie.childStatic;
  top.childDecls = ie.childDecls;
  
  top.inhOccursContextTypes = nubBy(typeNameEq, map(fst, ctxs.contextInhOccurs));
  top.inhOccursIndexDecls :=
    flatMap(
      \ t::Type -> s"\tpublic static final int type_${transTypeNameWith(t, top.freeVariables)} = ${toString(positionOfBy(typeNameEq, t, top.inhOccursContextTypes))};\n",
      top.inhOccursContextTypes) ++
    flatMap(
      \ t::Type -> s"\tpublic static int count_inh__ON__${transTypeNameWith(t, top.freeVariables)} = 0;\n",
      top.inhOccursContextTypes) ++
    ctxs.inhOccursIndexDecls;
  top.contextInhOccurs := ctxs.contextInhOccurs;
  ie.sigInhOccurs = ctxs.contextInhOccurs;
  
  ctxs.typeChildrenIn = ie.typeChildren;
  propagate contextRuntimeResolve;
}

aspect production globalSignature
top::NamedSignature ::= fn::String ctxs::Contexts ty::Type
{
  top.javaSignature = error("Translation shouldn't be demanded from global signature");
  top.refInvokeTrans = error("Translation shouldn't be demanded from global signature");
  top.contextRuntimeResolve := error("Translation shouldn't be demanded from global signature");
  top.contextRefElems = error("Translation shouldn't be demanded from global signature");
  top.childTypeVarElems = error("Translation shouldn't be demanded from global signature");
  top.childStatic = error("Translation shouldn't be demanded from global signature");
  top.childDecls = error("Translation shouldn't be demanded from global signature");

  top.inhOccursContextTypes = error("Translation shouldn't be demanded from global signature");
  top.inhOccursIndexDecls := error("Translation shouldn't be demanded from global signature");
  top.contextInhOccurs := error("Translation shouldn't be demanded from global signature");
}

propagate contextInhOccurs, inhOccursIndexDecls on Contexts, Context;
propagate typeChildrenIn, contextRuntimeResolve on Contexts;

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

aspect default production
top::Context ::=
{
  top.contextRuntimeResolveFailure = s"""
			if (true) throw new common.exceptions.SilverError("Can't construct production " + getName() + " because context ${prettyContext(top)} cannot be resolved at runtime");
			final ${top.transType} ${top.transContextMemberName} = ${top.transContextDummyInit};
""";
  top.contextRuntimeResolve := top.contextRuntimeResolveFailure;
}

aspect production instContext
top::Context ::= fn::String t::Type
{
  top.contextSigElem = s"final ${top.transType} ${makeConstraintDictName(fn, t, top.boundVariables)}";
  top.contextRefElem = makeConstraintDictName(fn, t, top.boundVariables);
}

aspect production inhOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.contextSigElem = s"final int ${makeConstraintDictName(attr, ntty, top.boundVariables)}";
  top.contextRefElem = makeConstraintDictName(attr, ntty, top.boundVariables);
  top.contextInhOccurs <- [(ntty, attr)];
  top.inhOccursIndexDecls <-
    s"\tpublic static final int ${makeIdName(attr)}__ON__${ntty.transTypeName} = count_inh__ON__${ntty.transTypeName}++;\n";
  top.contextRuntimeResolve :=
    case lookupBy(typeNameEq, ntty, top.typeChildrenIn) of
    | just(child) -> s"""
			final common.RTTIManager.Nonterminalton ${makeConstraintDictName(attr, ntty, top.boundVariables)}_nt = common.RTTIManager.getNonterminalton(common.Reflection.getType(${child}).typeName());
			if (${makeConstraintDictName(attr, ntty, top.boundVariables)}_nt == null) {
				throw new common.exceptions.SilverError(common.Reflection.getType(${child}) + " is not a nonterminal.");
			}
			final int ${makeConstraintDictName(attr, ntty, top.boundVariables)} = ${makeConstraintDictName(attr, ntty, top.boundVariables)}_nt.getOccursIndex("${attr}");
"""
    | nothing() -> top.contextRuntimeResolveFailure
    end;
}

aspect production synOccursContext
top::Context ::= attr::String args::[Type] atty::Type inhs::Type ntty::Type
{
  top.contextSigElem = s"final int ${makeConstraintDictName(attr, ntty, top.boundVariables)}";
  top.contextRefElem = makeConstraintDictName(attr, ntty, top.boundVariables);
  top.contextRuntimeResolve :=
    case lookupBy(typeNameEq, ntty, top.typeChildrenIn) of
    | just(child) -> s"""
			final common.RTTIManager.Nonterminalton ${makeConstraintDictName(attr, ntty, top.boundVariables)}_nt = common.RTTIManager.getNonterminalton(common.Reflection.getType(${child}).typeName());
			if (${makeConstraintDictName(attr, ntty, top.boundVariables)}_nt == null) {
				throw new common.exceptions.SilverError(common.Reflection.getType(${child}) + " is not a nonterminal.");
			}
			final int ${makeConstraintDictName(attr, ntty, top.boundVariables)} = ${makeConstraintDictName(attr, ntty, top.boundVariables)}_nt.getOccursIndex("${attr}");
"""
    | nothing() -> top.contextRuntimeResolveFailure
    end;
}

aspect production annoOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.contextSigElem = s"final ${top.transType} ${makeConstraintDictName(attr, ntty, top.boundVariables)}";
  top.contextRefElem = makeConstraintDictName(attr, ntty, top.boundVariables);
  top.contextRuntimeResolve :=
    case lookupBy(typeNameEq, ntty, top.typeChildrenIn) of
    | just(child) -> s"""
			if (!${child} instanceof makeAnnoName(${attr})) {
				throw new common.exceptions.SilverError(common.Reflection.getType(${child}) + " does not have annotation ${attr}.");
			}
			final ${top.transType} ${makeConstraintDictName(attr, ntty, top.boundVariables)} = null;
"""
    | nothing() -> top.contextRuntimeResolveFailure
    end;
}

aspect production typeableContext
top::Context ::= t::Type
{
  top.contextSigElem = s"final ${top.transType} ${makeTypeableName(t, top.boundVariables)}";
  top.contextRefElem = makeTypeableName(t, top.boundVariables);
  top.contextRuntimeResolve :=
    case lookupBy(typeNameEq, t, top.typeChildrenIn) of
    | just(child) -> s"\t\t\tfinal ${top.transType} ${makeTypeableName(t, top.boundVariables)} = common.Reflection.getType(${child});\n"
    | nothing() -> top.contextRuntimeResolveFailure
    end;
}

aspect production inhSubsetContext
top::Context ::= i1::Type i2::Type
{
  top.contextSigElem = s"final ${top.transType} ${makeInhSubsetName(i1, i2, top.boundVariables)}";
  top.contextRefElem = makeInhSubsetName(i1, i2, top.boundVariables);
  top.contextRuntimeResolve := s"final ${top.transType} ${makeInhSubsetName(i1, i2, top.boundVariables)} = null;";
}

aspect production typeErrorContext
top::Context ::= _
{
  top.contextSigElem = error("Translation demanded in the presence of errors");
  top.contextRefElem = error("Translation demanded in the presence of errors");
  top.contextRuntimeResolve := error("Translation demanded in the presence of errors");
}

propagate typeChildren on NamedSignatureElements;

aspect production consNamedSignatureElement
top::NamedSignatureElements ::= h::NamedSignatureElement t::NamedSignatureElements
{
  top.childSigElems = h.childSigElem :: t.childSigElems;
  top.annoSigElems = h.annoSigElem :: t.annoSigElems;
  top.childRefElems = h.childRefElem :: t.childRefElems;
  top.annoRefElems = h.annoRefElem :: t.annoRefElems;
  top.childTypeVarElems = h.childTypeVarElem :: t.childTypeVarElems;
  top.childStatic = h.childStaticElem ++ t.childStatic;
  top.childDecls = h.childDeclElem ++ t.childDecls;
}

aspect production nilNamedSignatureElement
top::NamedSignatureElements ::=
{
  top.childSigElems = [];
  top.annoSigElems = [];
  top.childRefElems = [];
  top.annoRefElems = [];
  top.childTypeVarElems = [];
  top.childStatic = "";
  top.childDecls = "";
}


-- TODO: It'd be nice to maybe split these into the ordered parameters and the annotations
aspect production namedSignatureElement
top::NamedSignatureElement ::= n::String ty::Type
{
  top.childSigElem = "final Object c_" ++ n;
  top.childRefElem = "c_" ++ n;
  top.childDeclElem =
s"""private Object child_${n};
  public final ${ty.transType} getChild_${n}() {
    final ${ty.transType} result = common.Util.<${ty.transType}>demand(child_${n});
    child_${n} = result;
    return result;
  }
""";

  local ntType::Type = if ty.isDecorated then ty.decoratedType else ty;
  ntType.boundVariables = ty.boundVariables;

  top.childTypeVarElem =
    if lookupBy(typeNameEq, ty, top.sigInhOccurs).isJust
    then s"type_${ntType.transTypeName}"
    else "-1";
  
  top.childStaticElem =
    if lookupBy(typeNameEq, ty, top.sigInhOccurs).isJust
    then s"\t\tchildInheritedAttributes[i_${n}] = new common.Lazy[count_inh__ON__${ntType.transTypeName}];\n"
    else if ty.isNonterminal && !ty.isData || ty.isUniqueDecorated && ntType.isNonterminal
    then s"\t\tchildInheritedAttributes[i_${n}] = new common.Lazy[${makeNTName(ntType.typeName)}.num_inh_attrs];\n"
    else "";

  top.typeChildren := [(ty, top.childRefElem)];
  
  -- annos are full names:
  
  local fn :: String = makeIdName(n);
  
  top.annoSigElem = "final Object a_" ++ fn;
  top.annoRefElem = "a_" ++ fn;
  top.annoDeclElem =
s"""	protected Object anno_${fn};
	@Override
	public final ${ty.transType} getAnno_${fn}() {
		final ${ty.transType} result = common.Util.<${ty.transType}>demand(anno_${fn});
		anno_${fn} = result;
		return result;
	}

""";
  top.annoAccessorElem = s"getAnno_${fn}()";

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
function makeChildDecSiteAccessCase
String ::= env::Decorated Env flowEnv::FlowEnv lhsNtName::String prodName::String n::NamedSignatureElement
{
  return
    case lookupUniqueRefs(prodName, n.elementName, flowEnv), lookupRefDecSite(prodName, n.elementName, flowEnv) of
    | [u], [v] -> s"\t\t\tcase i_${n.elementName}: return (context) -> ${refAccessTranslation(env, flowEnv, u.sourceGrammar, lhsNtName, v)};\n"
    | _, _ -> ""
    end;
}
function refAccessTranslation
String ::= env::Decorated Env flowEnv::FlowEnv grammarName::String ntName::String v::VertexType
{
  return
    case v of
    | lhsVertexType_real() -> error("lhs can't be a ref decoration site")
    | rhsVertexType(sigName) -> error("child can't be a ref decoration site")
    | localVertexType(fName) ->
      case getValueDcl(fName, env) of
      | dcl :: _ -> s"context.localDecorated(${dcl.attrOccursIndex})"
      | [] -> error("Couldn't find decl for local " ++ fName)
      end
    | transAttrVertexType(lhsVertexType_real(), transAttr) ->
      let transIndexName::String = s"${makeName(grammarName)}.Init.${makeIdName(transAttr)}__ON__${makeIdName(ntName)}"
      in s"context.translation(${transIndexName}, ${transIndexName}_inhs, ${transIndexName}_dec_site)"
      end
    | transAttrVertexType(_, transAttr) -> error("trans attr on non-lhs can't be a ref decoration site")
    | forwardVertexType_real() -> s"context.forward()"
    | anonVertexType(_) -> error("dec site projection shouldn't happen with anon decorate")
    | subtermVertexType(parent, prodName, sigName) ->
      s"${refAccessTranslation(env, flowEnv, grammarName, ntName, parent)}.childDecorated(${makeProdName(prodName)}.i_${sigName})"
    end;
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

function makeInhOccursContextAccess
String ::= bv::[TyVar] sigInhOccurs::[(Type, String)] typeVarArray::String inhArray::String t::Type
{
  t.boundVariables = bv;
  local inhs::[String] = lookupAllBy(typeNameEq, t, sigInhOccurs);
  return s"""		if (${typeVarArray}[key] == type_${t.transTypeName}) {
			${if null(inhs) then "return null;" else
s"""common.Lazy[] res = new common.Lazy[${foldr1(\ i1::String i2::String -> s"Math.max(${i1}, ${i2})", map(makeConstraintDictName(_, t, bv), inhs))} + 1];
${flatMap(\ inh::String -> s"\t\t\tres[${makeConstraintDictName(inh, t, bv)}] = ${inhArray}[key][${makeIdName(inh)}__ON__${t.transTypeName}];\n", inhs)}
			return res;"""}
		}
""";
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
			if (!common.TypeRep.unify(${transFreshTypeRep(n.typerep)}, common.Reflection.getType(getChild_${n.elementName}()))) {
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
        ${n.childRefElem} = common.Reflection.reify(rules, ${transFreshTypeRep(n.typerep)}, childASTs[i_${n.elementName}]);
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
      ${n.annoRefElem} = common.Reflection.reify(rules, ${transFreshTypeRep(n.typerep)}, annotationASTs[i${n.annoRefElem}]);
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

function typeNameEq
Boolean ::= t1::Type t2::Type
{ return t1.typeName == t2.typeName; }

