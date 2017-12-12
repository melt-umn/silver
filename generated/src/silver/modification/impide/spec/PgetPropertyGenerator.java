
package silver.modification.impide.spec;

public final class PgetPropertyGenerator extends common.FunctionNode {

	public static final int i_pkgName = 0;
	public static final int i_propDcls = 1;
	public static final int i_pkgFinalPart = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_getPropertyGenerator;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetPropertyGenerator(final Object c_pkgName, final Object c_propDcls, final Object c_pkgFinalPart) {
		this.child_pkgName = c_pkgName;
		this.child_propDcls = c_propDcls;
		this.child_pkgFinalPart = c_pkgFinalPart;

	}

	private Object child_pkgName;
	public final common.StringCatter getChild_pkgName() {
		return (common.StringCatter) (child_pkgName = common.Util.demand(child_pkgName));
	}

	private Object child_propDcls;
	public final common.ConsCell getChild_propDcls() {
		return (common.ConsCell) (child_propDcls = common.Util.demand(child_propDcls));
	}

	private Object child_pkgFinalPart;
	public final common.StringCatter getChild_pkgFinalPart() {
		return (common.StringCatter) (child_pkgFinalPart = common.Util.demand(child_pkgFinalPart));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pkgName: return getChild_pkgName();
			case i_propDcls: return getChild_propDcls();
			case i_pkgFinalPart: return getChild_pkgFinalPart();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pkgName: return child_pkgName;
			case i_propDcls: return child_propDcls;
			case i_pkgFinalPart: return child_pkgFinalPart;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:modification:impide:spec:getPropertyGenerator";
	}

	public static common.StringCatter invoke(final Object c_pkgName, final Object c_propDcls, final Object c_pkgFinalPart) {
		try {
		final common.DecoratedNode context = new PgetPropertyGenerator(c_pkgName, c_propDcls, c_pkgFinalPart).decorate();
		//"\npackage " ++ pkgName ++ ".eclipse.wizard" ++ pkgPart ++ ";\n\nimport java.util.ArrayList;\nimport java.util.List;\n\npublic class PropertyGenerator {\n    \n    private static String properties = null;\n    \n    public static String getAll() {\n        if(properties==null) {\n            StringBuilder sb = new StringBuilder();\n    \n" ++ sflatMap((.generatorJavaTranslation), propDcls) ++ "\n    \n            properties = sb.toString();\n        }\n    \n        return properties;\n    }\n    \n\n    private static String escape(String str) {\n        char[] orig = str.toCharArray();\n        List<Character> list = new ArrayList<Character>();\n        for(char c : orig) {\n            if(c == '=' || c == '#' || c == '\\\\' || c == ':') {\n               list.add('\\\\');\n            }\n            list.add(c);\n        }\n        \n        //Convert to a char array\n        char[] mod = new char[list.size()];\n        for(int i = 0; i < mod.length; i++) {\n            mod[i] = list.get(i);\n        }\n            \n        return new String(mod);\n    }\n}\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\npackage ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PgetPropertyGenerator.i_pkgName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".eclipse.wizard")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.impide.spec.Init.pkgPart__ON__silver_modification_impide_spec_getPropertyGenerator)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";\n\nimport java.util.ArrayList;\nimport java.util.List;\n\npublic class PropertyGenerator {\n    \n    private static String properties = null;\n    \n    public static String getAll() {\n        if(properties==null) {\n            StringBuilder sb = new StringBuilder();\n    \n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.PsflatMap.invoke(new common.AttributeSection.Undecorated(silver.modification.impide.spec.Init.silver_modification_impide_spec_generatorJavaTranslation__ON__silver_modification_impide_spec_IdeProperty, context), context.childAsIsLazy(silver.modification.impide.spec.PgetPropertyGenerator.i_propDcls))), (common.StringCatter)(new common.StringCatter("\n    \n            properties = sb.toString();\n        }\n    \n        return properties;\n    }\n    \n\n    private static String escape(String str) {\n        char[] orig = str.toCharArray();\n        List<Character> list = new ArrayList<Character>();\n        for(char c : orig) {\n            if(c == '=' || c == '#' || c == '\\\\' || c == ':') {\n               list.add('\\\\');\n            }\n            list.add(c);\n        }\n        \n        //Convert to a char array\n        char[] mod = new char[list.size()];\n        for(int i = 0; i < mod.length; i++) {\n            mod[i] = list.get(i);\n        }\n            \n        return new String(mod);\n    }\n}\n")))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:spec:getPropertyGenerator", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetPropertyGenerator.invoke(children[0], children[1], children[2]);
		}
	};
}