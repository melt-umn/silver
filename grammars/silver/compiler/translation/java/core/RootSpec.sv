grammar silver:compiler:translation:java:core;

import silver:compiler:driver:util;

attribute genFiles occurs on RootSpec;
attribute genBinaryFiles occurs on RootSpec;

aspect production interfaceRootSpec
top::RootSpec ::= _ _ _
{
  top.genFiles := [];
  top.genBinaryFiles := [];
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  top.genFiles := [];
  top.genBinaryFiles := [];
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _ _ _ _ _
{
  top.genBinaryFiles := [
    ("Silver.svi", serInterface)
  ];

  top.genFiles := g.genFiles;

  local package::String = makeName(g.declaredName);

  top.genFiles <- [("Init.java", s"""
package ${package};

public class Init{
	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;
${makeOthers(top.allGrammarDependencies, "initAllStatics")}
	}

	public static void init(){
		if(init) return;
		init = true;
		setupInheritedAttributes();
${makeOthers(top.allGrammarDependencies, "init")}
		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;
${makeOthers(top.allGrammarDependencies, "postInit")}
${g.postInit}
	}

	private static void setupInheritedAttributes(){
${g.setupInh}
	}

	private static void initProductionAttributeDefinitions(){
${g.initProd}
	}

${g.initWeaving}
${g.valueWeaving}
	final static common.DecoratedNode context = common.TopNode.singleton; // For globals
${g.initValues}
}
""")];

  local hasMain::Boolean = !null(getValueDcl("main", g.env));
  local isIOValReturn::Boolean =
	case getValueDcl("main", g.env) of
	| dcl :: _ -> dcl.typeScheme.typerep.outputType.typeName == "silver:core:IOVal"
	| _ -> false
	end;

  -- Code used if main function return type is IOVal<Integer>
  local invocationIOVal::String = package ++ 
    ".Pmain.invoke(common.OriginContext.ENTRY_CONTEXT, cvargs(args), common.IOToken.singleton)";
	
  -- Code used if main function return type is IO<Integer>
  local invocationEvalIO::String = 
    "silver.core.PevalIO.invoke(common.OriginContext.ENTRY_CONTEXT, " ++ package ++ 
    ".Pmain.invoke(common.OriginContext.ENTRY_CONTEXT, cvargs(args)), common.IOToken.singleton)";

  local mainFunctionBody::String = 
    if hasMain
	  then s"""
		common.Util.init();
		${package}.Init.initAllStatics();
		${package}.Init.init();
		${package}.Init.postInit();

		try {
			silver.core.NIOVal rv = ${if isIOValReturn then invocationIOVal else invocationEvalIO};
			rv.synthesized(silver.core.Init.silver_core_io__ON__silver_core_IOVal); // demand the io token
			System.exit( (Integer)rv.synthesized(silver.core.Init.silver_core_iovalue__ON__silver_core_IOVal) );
		} catch(Throwable t) {
			Throwable rt = common.exceptions.SilverException.getRootCause(t);
			if(rt instanceof common.exceptions.SilverExit)
				System.exit(((common.exceptions.SilverExit)rt).getExitCode());
			common.Util.printStackCauses(t);
		}"""
	  else s"""
		System.err.println("No main function defined in main grammar ${g.declaredName}");
		System.exit(1);""";

  local hasCodeProberParse::Boolean = !null(getValueDcl("codeProberParse", g.env));

  local codeProberParseFn::String =
	if hasCodeProberParse then s"""
	public static common.DecoratedNode CodeProber_parse(String[] args) {
		common.Util.init();
		${package}.Init.initAllStatics();
		${package}.Init.init();
		${package}.Init.postInit();

		silver.core.NIOVal rv = silver.core.PevalIO.invoke(common.OriginContext.ENTRY_CONTEXT, ${package}.PcodeProberParse.invoke(common.OriginContext.ENTRY_CONTEXT, cvargs(args)), common.IOToken.singleton);
		rv.synthesized(silver.core.Init.silver_core_io__ON__silver_core_IOVal); // demand the io token
		return rv.synthesized(silver.core.Init.silver_core_iovalue__ON__silver_core_IOVal);
	}""" else "";

  top.genFiles <- if hasMain || hasCodeProberParse then [("Main.java", s"""
package ${package};

import silver.core.*;

public class Main {
	public static void main(String[] args) {
${mainFunctionBody}
	}
${codeProberParseFn}
	public static common.ConsCell cvargs(String[] args) {
		common.ConsCell result = common.ConsCell.nil;
		for(int i = args.length - 1; i >= 0; i--) {
			result = new common.ConsCell(new common.StringCatter(args[i]), result);
		}
		return result;
	}
}""")] else [];
}

fun makeOthers String ::= others::[String] nme::String =
  if null(others) then "" else s"\t\t${makeName(head(others))}.Init.${nme}();\n${makeOthers(tail(others),nme)}";

