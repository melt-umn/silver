
package silver.modification.copper;

public final class PbuildAntParserPart extends common.FunctionNode {

	public static final int i_p = 0;
	public static final int i_a = 1;


	public static final Class<?> childTypes[] = { silver.definition.concrete_syntax.NParserSpec.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_buildAntParserPart;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_p] = new common.Lazy[silver.definition.concrete_syntax.NParserSpec.num_inh_attrs];

	}

	public PbuildAntParserPart(final Object c_p, final Object c_a) {
		this.child_p = c_p;
		this.child_a = c_a;

	}

	private Object child_p;
	public final silver.definition.concrete_syntax.NParserSpec getChild_p() {
		return (silver.definition.concrete_syntax.NParserSpec) (child_p = common.Util.demand(child_p));
	}

	private Object child_a;
	public final common.DecoratedNode getChild_a() {
		return (common.DecoratedNode) (child_a = common.Util.demand(child_a));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();
			case i_a: return getChild_a();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;
			case i_a: return child_a;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:modification:copper:buildAntParserPart";
	}

	public static common.StringCatter invoke(final Object c_p, final Object c_a) {
		try {
		final common.DecoratedNode context = new PbuildAntParserPart(c_p, c_a).decorate();
		//"\n    <copper packageName='" ++ makeName(p.sourceGrammar) ++ "' parserName='" ++ parserName ++ "' outputFile='${src}/" ++ packagepath ++ parserName ++ ".java' useSkin='XML' warnUselessNTs='false' " ++ varyingopts ++ " dumpFormat='HTML' dumpFile='" ++ parserName ++ ".copperdump.html'>\n      <inputs file='${src}/" ++ packagepath ++ parserName ++ ".copper'/>\n    </copper>\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\n    <copper packageName='")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeName.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PbuildAntParserPart.i_p, silver.definition.concrete_syntax.Init.silver_definition_env_sourceGrammar__ON__silver_definition_concrete_syntax_ParserSpec))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("' parserName='")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.parserName__ON__silver_modification_copper_buildAntParserPart)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("' outputFile='${src}/")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.packagepath__ON__silver_modification_copper_buildAntParserPart)), (common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.parserName__ON__silver_modification_copper_buildAntParserPart))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".java' useSkin='XML' warnUselessNTs='false' ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.varyingopts__ON__silver_modification_copper_buildAntParserPart)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" dumpFormat='HTML' dumpFile='")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.parserName__ON__silver_modification_copper_buildAntParserPart)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".copperdump.html'>\n      <inputs file='${src}/")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.packagepath__ON__silver_modification_copper_buildAntParserPart)), (common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.parserName__ON__silver_modification_copper_buildAntParserPart))), (common.StringCatter)(new common.StringCatter(".copper'/>\n    </copper>\n")))))))))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:copper:buildAntParserPart", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbuildAntParserPart.invoke(children[0], children[1]);
		}
	};
}