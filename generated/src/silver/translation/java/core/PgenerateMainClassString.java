
package silver.translation.java.core;

public final class PgenerateMainClassString extends common.FunctionNode {

	public static final int i_whatGrammar = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_generateMainClassString;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgenerateMainClassString(final Object c_whatGrammar) {
		this.child_whatGrammar = c_whatGrammar;

	}

	private Object child_whatGrammar;
	public final common.StringCatter getChild_whatGrammar() {
		return (common.StringCatter) (child_whatGrammar = common.Util.demand(child_whatGrammar));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_whatGrammar: return getChild_whatGrammar();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_whatGrammar: return child_whatGrammar;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:translation:java:core:generateMainClassString";
	}

	public static common.StringCatter invoke(final Object c_whatGrammar) {
		try {
		final common.DecoratedNode context = new PgenerateMainClassString(c_whatGrammar).decorate();
		//"\npackage " ++ package ++ ";\n\npublic class Main {\n\tpublic static void main(String[] args) {\n\t\t" ++ package ++ ".Init.initAllStatics();\n\t\t" ++ package ++ ".Init.init();\n\t\t" ++ package ++ ".Init.postInit();\n\t\ttry {\n\t\t\tcommon.Node rv = (common.Node) " ++ package ++ ".Pmain.invoke(cvargs(args), common.IOToken.singleton);\n\t\t\tcommon.DecoratedNode drv = rv.decorate(common.TopNode.singleton, (common.Lazy[])null);\n\t\t\tdrv.synthesized(core.Init.core_io__ON__core_IOVal); // demand the io token\n\t\t\tSystem.exit( (Integer)drv.synthesized(core.Init.core_iovalue__ON__core_IOVal) );\n\t\t} catch(Throwable t) {\n\t\t\tThrowable rt = common.exceptions.SilverException.getRootCause(t);\n\t\t\tif(rt instanceof common.exceptions.SilverExit)\n\t\t\t\tSystem.exit(((common.exceptions.SilverExit)rt).getExitCode());\n\t\t\tcommon.Util.printStackCauses(t);\n\t\t}\n\t}\n\tpublic static common.ConsCell cvargs(String[] args) {\n\t\tcommon.ConsCell result = common.ConsCell.nil;\n\t\tfor(int i = args.length - 1; i >= 0; i--) {\n\t\t\tresult = new common.ConsCell(new common.StringCatter(args[i]), result);\n\t\t}\n\t\treturn result;\n\t}\n}"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\npackage ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.translation.java.core.Init.package__ON__silver_translation_java_core_generateMainClassString)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";\n\npublic class Main {\n\tpublic static void main(String[] args) {\n\t\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.translation.java.core.Init.package__ON__silver_translation_java_core_generateMainClassString)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".Init.initAllStatics();\n\t\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.translation.java.core.Init.package__ON__silver_translation_java_core_generateMainClassString)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".Init.init();\n\t\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.translation.java.core.Init.package__ON__silver_translation_java_core_generateMainClassString)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".Init.postInit();\n\t\ttry {\n\t\t\tcommon.Node rv = (common.Node) ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.translation.java.core.Init.package__ON__silver_translation_java_core_generateMainClassString)), (common.StringCatter)(new common.StringCatter(".Pmain.invoke(cvargs(args), common.IOToken.singleton);\n\t\t\tcommon.DecoratedNode drv = rv.decorate(common.TopNode.singleton, (common.Lazy[])null);\n\t\t\tdrv.synthesized(core.Init.core_io__ON__core_IOVal); // demand the io token\n\t\t\tSystem.exit( (Integer)drv.synthesized(core.Init.core_iovalue__ON__core_IOVal) );\n\t\t} catch(Throwable t) {\n\t\t\tThrowable rt = common.exceptions.SilverException.getRootCause(t);\n\t\t\tif(rt instanceof common.exceptions.SilverExit)\n\t\t\t\tSystem.exit(((common.exceptions.SilverExit)rt).getExitCode());\n\t\t\tcommon.Util.printStackCauses(t);\n\t\t}\n\t}\n\tpublic static common.ConsCell cvargs(String[] args) {\n\t\tcommon.ConsCell result = common.ConsCell.nil;\n\t\tfor(int i = args.length - 1; i >= 0; i--) {\n\t\t\tresult = new common.ConsCell(new common.StringCatter(args[i]), result);\n\t\t}\n\t\treturn result;\n\t}\n}")))))))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:generateMainClassString", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgenerateMainClassString.invoke(children[0]);
		}
	};
}