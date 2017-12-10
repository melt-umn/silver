
package silver.modification.impide.spec;

public final class PgetTokenClassifier extends common.FunctionNode {

	public static final int i_pkgName = 0;
	public static final int i_fontList = 1;
	public static final int i_termFontPairList = 2;
	public static final int i_parserName = 3;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_getTokenClassifier;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetTokenClassifier(final Object c_pkgName, final Object c_fontList, final Object c_termFontPairList, final Object c_parserName) {
		this.child_pkgName = c_pkgName;
		this.child_fontList = c_fontList;
		this.child_termFontPairList = c_termFontPairList;
		this.child_parserName = c_parserName;

	}

	private Object child_pkgName;
	public final common.StringCatter getChild_pkgName() {
		return (common.StringCatter) (child_pkgName = common.Util.demand(child_pkgName));
	}

	private Object child_fontList;
	public final common.ConsCell getChild_fontList() {
		return (common.ConsCell) (child_fontList = common.Util.demand(child_fontList));
	}

	private Object child_termFontPairList;
	public final common.ConsCell getChild_termFontPairList() {
		return (common.ConsCell) (child_termFontPairList = common.Util.demand(child_termFontPairList));
	}

	private Object child_parserName;
	public final common.StringCatter getChild_parserName() {
		return (common.StringCatter) (child_parserName = common.Util.demand(child_parserName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pkgName: return getChild_pkgName();
			case i_fontList: return getChild_fontList();
			case i_termFontPairList: return getChild_termFontPairList();
			case i_parserName: return getChild_parserName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pkgName: return child_pkgName;
			case i_fontList: return child_fontList;
			case i_termFontPairList: return child_termFontPairList;
			case i_parserName: return child_parserName;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:modification:impide:spec:getTokenClassifier";
	}

	public static common.StringCatter invoke(final Object c_pkgName, final Object c_fontList, final Object c_termFontPairList, final Object c_parserName) {
		try {
		final common.DecoratedNode context = new PgetTokenClassifier(c_pkgName, c_fontList, c_termFontPairList, c_parserName).decorate();
		//"\npackage " ++ pkgName ++ ".imp.coloring;\n\nimport java.util.HashMap;\n\nimport edu.umn.cs.melt.ide.copper.coloring.ITokenClassifier;\nimport edu.umn.cs.melt.ide.copper.coloring.TextAttributeProvider;\nimport org.eclipse.jface.text.TextAttribute;\nimport org.eclipse.swt.widgets.Display;\n\npublic class " ++ parserName ++ "_TokenClassifier implements ITokenClassifier {\n\tprivate static final HashMap<String, Integer> map = new HashMap<String, Integer>();\n\n\tpublic final static class TokenType {\n\t\tpublic static final int DEFAULT = 0; \n" ++ getConstantDeclarations(1, fontList) ++ "\n\t\tpublic static final int TOTAL = " ++ toString(length(fontList) + 1) ++ "; \n\t}\n\n\tstatic {\n\t\t" ++ implode("\n\t\t", map(getPutNameFontPairIntoMap, termFontPairList)) ++ "\n\t}\n\n\tpublic static int getKind(String symbolName) {\n\t\tif(symbolName == null || \"\".equals(symbolName)) {\n\t\t\treturn TokenType.DEFAULT;\n\t\t}\n\n\t\tInteger kind = map.get(symbolName);\n\n\t\tif(kind == null) {\n\t\t\treturn TokenType.DEFAULT;\n\t\t}\n\n\t\treturn kind;\n\t}\n\n\tprivate static final TextAttribute[] attributes = new TextAttribute[TokenType.TOTAL];\n\t\n\tstatic {\n\t\tDisplay display = Display.getDefault();\n\t\t" ++ implode("\n\t\t", map(getTextAttributeInit, fontList)) ++ "\n\t}\n\t\n\t@Override\n\tpublic TextAttribute getColoring(common.Terminal token) {\n    // TODO: check kind by getLexerClasses()\n\t\treturn attributes[getKind(token.getName())];\n\t}\n}\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\npackage ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PgetTokenClassifier.i_pkgName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".imp.coloring;\n\nimport java.util.HashMap;\n\nimport edu.umn.cs.melt.ide.copper.coloring.ITokenClassifier;\nimport edu.umn.cs.melt.ide.copper.coloring.TextAttributeProvider;\nimport org.eclipse.jface.text.TextAttribute;\nimport org.eclipse.swt.widgets.Display;\n\npublic class ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PgetTokenClassifier.i_parserName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_TokenClassifier implements ITokenClassifier {\n\tprivate static final HashMap<String, Integer> map = new HashMap<String, Integer>();\n\n\tpublic final static class TokenType {\n\t\tpublic static final int DEFAULT = 0; \n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.impide.spec.PgetConstantDeclarations.invoke(Integer.valueOf((int)1), context.childAsIsLazy(silver.modification.impide.spec.PgetTokenClassifier.i_fontList))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\t\tpublic static final int TOTAL = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(Integer.valueOf(((Integer)core.PlistLength.invoke(context.childAsIsLazy(silver.modification.impide.spec.PgetTokenClassifier.i_fontList))) + Integer.valueOf((int)1)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("; \n\t}\n\n\tstatic {\n\t\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n\t\t")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.modification.impide.spec.PgetPutNameFontPairIntoMap.factory, context.childAsIsLazy(silver.modification.impide.spec.PgetTokenClassifier.i_termFontPairList))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\t}\n\n\tpublic static int getKind(String symbolName) {\n\t\tif(symbolName == null || \"\".equals(symbolName)) {\n\t\t\treturn TokenType.DEFAULT;\n\t\t}\n\n\t\tInteger kind = map.get(symbolName);\n\n\t\tif(kind == null) {\n\t\t\treturn TokenType.DEFAULT;\n\t\t}\n\n\t\treturn kind;\n\t}\n\n\tprivate static final TextAttribute[] attributes = new TextAttribute[TokenType.TOTAL];\n\t\n\tstatic {\n\t\tDisplay display = Display.getDefault();\n\t\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n\t\t")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.modification.impide.spec.PgetTextAttributeInit.factory, context.childAsIsLazy(silver.modification.impide.spec.PgetTokenClassifier.i_fontList))); } })), (common.StringCatter)(new common.StringCatter("\n\t}\n\t\n\t@Override\n\tpublic TextAttribute getColoring(common.Terminal token) {\n    // TODO: check kind by getLexerClasses()\n\t\treturn attributes[getKind(token.getName())];\n\t}\n}\n")))))))))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:spec:getTokenClassifier", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetTokenClassifier.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}