
package silver.modification.copper_mda;

public final class PmdaBuildSpecTarget extends common.FunctionNode {

	public static final int i_spec = 0;


	public static final Class<?> childTypes[] = { silver.modification.copper_mda.NMdaSpec.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_mda_mdaBuildSpecTarget;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_spec] = new common.Lazy[silver.modification.copper_mda.NMdaSpec.num_inh_attrs];

	}

	public PmdaBuildSpecTarget(final Object c_spec) {
		this.child_spec = c_spec;

	}

	private Object child_spec;
	public final silver.modification.copper_mda.NMdaSpec getChild_spec() {
		return (silver.modification.copper_mda.NMdaSpec) (child_spec = common.Util.demand(child_spec));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_spec: return getChild_spec();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_spec: return child_spec;

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
		return "silver:modification:copper_mda:mdaBuildSpecTarget";
	}

	public static common.StringCatter invoke(final Object c_spec) {
		try {
		final common.DecoratedNode context = new PmdaBuildSpecTarget(c_spec).decorate();
		//"    <copper useSkin='XML' runMDA='true' warnUselessNTs='false'>\n" ++ "      <inputs file='${src}/" ++ grammarToPath(spec.sourceGrammar) ++ makeParserName(spec.fullName) ++ ".copper'/>\n    </copper>\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("    <copper useSkin='XML' runMDA='true' warnUselessNTs='false'>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("      <inputs file='${src}/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.driver.util.PgrammarToPath.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PmdaBuildSpecTarget.i_spec, silver.modification.copper_mda.Init.silver_definition_env_sourceGrammar__ON__silver_modification_copper_mda_MdaSpec))), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeParserName.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PmdaBuildSpecTarget.i_spec, silver.modification.copper_mda.Init.silver_definition_env_fullName__ON__silver_modification_copper_mda_MdaSpec))), (common.StringCatter)(new common.StringCatter(".copper'/>\n    </copper>\n")))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:copper_mda:mdaBuildSpecTarget", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmdaBuildSpecTarget.invoke(children[0]);
		}
	};
}