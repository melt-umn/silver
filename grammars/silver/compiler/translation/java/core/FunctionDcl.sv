grammar silver:compiler:translation:java:core;

import silver:compiler:modification:ffi only ioForeignType; -- for main type check only
import silver:compiler:modification:list only listCtrType;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.setupInh := body.setupInh;
  top.initProd := s"\t\t//FUNCTION ${id.name} ${ns.unparse}\n" ++ body.translation;

  local localVar :: String = "count_local__ON__" ++ makeIdName(fName);

  top.initWeaving := s"\tpublic static int ${localVar} = 0;\n";
  top.valueWeaving := body.valueWeaving;

  local argsAccess :: String =
    implode(", ", map(\ c::Context -> decorate c with {boundVariables = namedSig.freeVariables;}.contextRefElem, namedSig.contexts) ++ map((.childRefElem), namedSig.inputElements));

  local funBody :: String =
s"""			final common.DecoratedNode context = new P${id.name}(${argsAccess}).decorate(originCtx);
			//${head(body.uniqueSignificantExpression).unparse}
			return (${namedSig.outputElement.typerep.transType})(${head(body.uniqueSignificantExpression).translation});
""";

  top.genFiles :=
    [pair(s"P${id.name}.java", generateFunctionClassString(top.grammarName, id.name, namedSig, funBody))] ++
    if id.name == "main" then [pair("Main.java", generateMainClassString(top.grammarName, !mainTypeOne))]
    else [];

	local attribute mainTypeOne::Boolean = unify(namedSig.typerep, -- main functions which return IOVal<Integer>
         appTypes(
           functionType(2, []),
           [appType(listCtrType(), stringType()),
            ioForeignType,
            appType(nonterminalType("silver:core:IOVal", [starKind()], false), intType())])).failure;

	local attribute mainTypeTwo::Boolean = unify(namedSig.typerep, -- main functions which return IO<Integer>
         appTypes(
           functionType(1, []),
           [appType(listCtrType(), stringType()),
            appType(nonterminalType("silver:core:IO", [starKind()], false), intType())])).failure;

  -- main function signature check TODO: this should probably be elsewhere!
  top.errors <- 
    if id.name == "main" && mainTypeOne && mainTypeTwo
    then [err(top.location, "main function must have type signature (IOVal<Integer> ::= [String] IOToken) or (IO<Integer> ::= [String]). Instead it has type " ++ prettyType(namedSig.typerep))]
    else [];
}

function generateFunctionClassString
String ::= whatGrammar::String whatName::String whatSig::NamedSignature whatResult::String
{
  local className :: String = "P" ++ whatName;

  local localVar :: String = 
    s"count_local__ON__${makeIdName(whatGrammar)}_${whatName}";

  local commaIfArgs :: String = if length(whatSig.contexts) + length(whatSig.inputElements) != 0 then "," else "";
  
  local contexts::Contexts = foldContexts(whatSig.contexts);
  contexts.boundVariables = whatSig.freeVariables;

  return s"""
package ${makeName(whatGrammar)};

import silver.core.NOriginInfo;

public final class ${className} extends common.FunctionNode {

${makeIndexDcls(0, whatSig.inputElements)}

	public static final int num_local_attrs = Init.${localVar};
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[${toString(length(whatSig.inputElements))}][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

${whatSig.inhOccursIndexDecls}

	public static final int[] childInhContextTypeVars = {${implode(",", whatSig.childTypeVarElems)}};
	public static final int[] localInhContextTypeVars = new int[num_local_attrs];

	static {
${whatSig.childStatic}
	}

	public ${className}(${whatSig.javaSignature}) {
${implode("", map(makeChildAssign, whatSig.inputElements))}
${contexts.contextInitTrans}
	}

${whatSig.childDecls}

${contexts.contextMemberDeclTrans}

	@Override
	public Object getChild(final int index) {
		switch(index) {
${implode("", map(makeChildAccessCase, whatSig.inputElements))}
			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
${implode("", map(makeChildAccessCaseLazy, whatSig.inputElements))}
			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return ${toString(length(whatSig.inputElements))};
	}

	@Override
	public common.Lazy[] getLocalInheritedAttributes(final int key) {
${flatMap(makeInhOccursContextAccess(whatSig.freeVariables, whatSig.contextInhOccurs, "localInhContextTypeVars", "localInheritedAttributes", _), whatSig.inhOccursContextTypes)}
		return localInheritedAttributes[key];
	}

	@Override
	public common.Lazy[] getChildInheritedAttributes(final int key) {
${flatMap(makeInhOccursContextAccess(whatSig.freeVariables, whatSig.contextInhOccurs, "childInhContextTypeVars", "childInheritedAttributes", _), whatSig.inhOccursContextTypes)}
		return childInheritedAttributes[key];
	}

	@Override
	public common.Lazy getLocal(final int key) {
		return localAttributes[key];
	}

	@Override
	public final int getNumberOfLocalAttrs() {
		return num_local_attrs;
	}

	@Override
	public final String getNameOfLocalAttr(final int index) {
		return occurs_local[index];
	}

	@Override
	public String getName() {
		return "${whatSig.fullName}";
	}

	public static ${whatSig.outputElement.typerep.transType} invoke(final common.OriginContext originCtx ${commaIfArgs} ${whatSig.javaSignature}) {
		try {
${whatResult}
		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function ${whatSig.fullName}", t);
		}
	}

${if null(whatSig.contexts) -- Can only use a singleton when there aren't contexts.
  then s"""
	public static final common.NodeFactory<${whatSig.outputElement.typerep.transCovariantType}> factory = new Factory();
""" else s"""
	public static final common.NodeFactory<${whatSig.outputElement.typerep.transCovariantType}> getFactory(${contexts.contextParamTrans}) {
		return new Factory(${implode(", ", map(\ c::Context -> decorate c with {boundVariables = whatSig.freeVariables;}.contextRefElem, whatSig.contexts))});
	}
"""}

	public static final class Factory extends common.NodeFactory<${whatSig.outputElement.typerep.transType}> {
${contexts.contextMemberDeclTrans}

		public Factory(${contexts.contextParamTrans}) {
${contexts.contextInitTrans}
		}

		@Override
		public final ${whatSig.outputElement.typerep.transType} invoke(final common.OriginContext originCtx, final Object[] children, final Object[] namedNotApplicable) {
			return ${className}.invoke(${implode(", ", ["originCtx"] ++ map(\ c::Context -> decorate c with {boundVariables = whatSig.freeVariables;}.contextRefElem, whatSig.contexts) ++ unpackChildren(0, whatSig.inputElements))});
		}
		
		@Override
		public final common.AppTypeRep getType() {
${makeTyVarDecls(3, whatSig.typerep.freeVariables)}
			return ${transFreshTypeRep(whatSig.typerep)};
		}
		
		@Override
		public final String toString() {
			return "${whatGrammar}:${whatName}";
		}
	};
}""";
}

function generateMainClassString
String ::= whatGrammar::String whatSignature::Boolean
{
  local attribute package :: String;
  package = makeName(whatGrammar);

	local attribute mainInvocation::String = package ++ ".Pmain.invoke(common.OriginContext.ENTRY_CONTEXT, cvargs(args), common.IOToken.singleton)";
	local attribute evalIOInvocation::String = "silver.core.PevalIO.invoke(common.OriginContext.ENTRY_CONTEXT, " ++ package ++ ".Pmain.invoke(common.OriginContext.ENTRY_CONTEXT, cvargs(args)), common.IOToken.singleton)";

  return s"""
package ${package};

import silver.core.*;

public class Main {
	public static void main(String[] args) {
		common.Util.init();
		${package}.Init.initAllStatics();
		${package}.Init.init();
		${package}.Init.postInit();

		try {
			common.Node rv = (common.Node) ${if whatSignature then mainInvocation else evalIOInvocation};
			common.DecoratedNode drv = rv.decorate(common.TopNode.singleton, (common.Lazy[])null);
			drv.synthesized(silver.core.Init.silver_core_io__ON__silver_core_IOVal); // demand the io token
			System.exit( (Integer)drv.synthesized(silver.core.Init.silver_core_iovalue__ON__silver_core_IOVal) );
		} catch(Throwable t) {
			Throwable rt = common.exceptions.SilverException.getRootCause(t);
			if(rt instanceof common.exceptions.SilverExit)
				System.exit(((common.exceptions.SilverExit)rt).getExitCode());
			common.Util.printStackCauses(t);
		}
	}
	public static common.ConsCell cvargs(String[] args) {
		common.ConsCell result = common.ConsCell.nil;
		for(int i = args.length - 1; i >= 0; i--) {
			result = new common.ConsCell(new common.StringCatter(args[i]), result);
		}
		return result;
	}
}""";
}

