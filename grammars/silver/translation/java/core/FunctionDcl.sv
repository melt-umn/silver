grammar silver:translation:java:core;

import silver:modification:ffi only ioForeignType; -- for main type check only
import silver:util;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.setupInh := body.setupInh;
  top.initProd := s"\t\t//FUNCTION ${id.name} ${ns.unparse}\n" ++ body.translation;

  local localVar :: String = "count_local__ON__" ++ makeIdName(fName);

  top.initWeaving := s"\tpublic static int ${localVar} = 0;\n";
  top.valueWeaving := body.valueWeaving;

  local argsAccess :: String =
    implode(", ", map((.childRefElem), namedSig.inputElements));

  local funBody :: String =
s"""			final common.DecoratedNode context = new P${id.name}(${argsAccess}).decorate();
			//${head(body.uniqueSignificantExpression).unparse}
			return (${namedSig.outputElement.typerep.transType})(${head(body.uniqueSignificantExpression).translation});
""";

  top.genFiles :=
    [pair(s"P${id.name}.java", generateFunctionClassString(top.grammarName, id.name, namedSig, funBody))] ++
    if id.name == "main" then [pair("Main.java", generateMainClassString(top.grammarName))]
    else [];

  -- main function signature check TODO: this should probably be elsewhere!
  top.errors <-
    if id.name == "main" &&
       unify(namedSig.typerep,
         functionType(nonterminalType("core:IOVal", [intType()]), [
           decoratedType(nonterminalType("core:List", [stringType()])),
           ioForeignType], [])).failure
    then [err(top.location, "main function must have type signature (IOVal<Integer> ::= [String] IO). Instead it has type " ++ prettyType(namedSig.typerep))]
    else [];
}

function generateFunctionClassString
String ::= whatGrammar::String whatName::String whatSig::NamedSignature whatResult::String
{
  local className :: String = "P" ++ whatName;

  local localVar :: String = 
    s"count_local__ON__${makeIdName(whatGrammar)}_${whatName}";

  return s"""
package ${makeName(whatGrammar)};

public final class ${className} extends common.FunctionNode {

${makeIndexDcls(0, whatSig.inputElements)}

	public static final Class<?> childTypes[] = { ${implode(",", map(makeChildTypes, whatSig.inputElements))} };

	public static final int num_local_attrs = Init.${localVar};
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[${toString(length(whatSig.inputElements))}][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
${implode("", map((.childStaticElem), whatSig.inputElements))}
	}

	public ${className}(${whatSig.javaSignature}) {
${implode("", map(makeChildAssign, whatSig.inputElements))}
	}

${implode("", map((.childDeclElem), whatSig.inputElements))}

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
		return localInheritedAttributes[key];
	}

	@Override
	public common.Lazy[] getChildInheritedAttributes(final int key) {
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

	public static ${whatSig.outputElement.typerep.transType} invoke(${whatSig.javaSignature}) {
		try {
${whatResult}
		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function ${whatSig.fullName}", t);
		}
	}

	// Use of ? to permit casting to more specific types
	public static final common.NodeFactory<? extends ${whatSig.outputElement.typerep.transType}> factory = new Factory();

	public static final class Factory extends common.NodeFactory<${whatSig.outputElement.typerep.transType}> {
		@Override
		public final ${whatSig.outputElement.typerep.transType} invoke(final Object[] children, final Object[] namedNotApplicable) {
			return ${className}.invoke(${implode(", ", unpackChildren(0, whatSig.inputElements))});
		}
		
		@Override
		public final common.FunctionTypeRep getType() {
${makeTyVarDecls(3, whatSig.typerep.freeVariables)}
			return ${whatSig.typerep.transFreshTypeRep};
		}
		
		@Override
		public final String toString() {
			return "${whatGrammar}:${whatName}";
		}
	};
}""";
}

function generateMainClassString
String ::= whatGrammar::String
{
  local attribute package :: String;
  package = makeName(whatGrammar);

  return s"""
package ${package};

public class Main {
	public static void main(String[] args) {
		${package}.Init.initAllStatics();
		${package}.Init.init();
		${package}.Init.postInit();
		try {
			common.Node rv = (common.Node) ${package}.Pmain.invoke(cvargs(args), common.IOToken.singleton);
			common.DecoratedNode drv = rv.decorate(common.TopNode.singleton, (common.Lazy[])null);
			drv.synthesized(core.Init.core_io__ON__core_IOVal); // demand the io token
			System.exit( (Integer)drv.synthesized(core.Init.core_iovalue__ON__core_IOVal) );
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

