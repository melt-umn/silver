
package silver.analysis.warnings.exporting;

public final class PmakeDotArrow extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_exporting_makeDotArrow;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmakeDotArrow(final Object c_f, final Object c_t) {
		this.child_f = c_f;
		this.child_t = c_t;

	}

	private Object child_f;
	public final common.StringCatter getChild_f() {
		return (common.StringCatter) (child_f = common.Util.demand(child_f));
	}

	private Object child_t;
	public final common.StringCatter getChild_t() {
		return (common.StringCatter) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_t: return child_t;

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
		return "silver:analysis:warnings:exporting:makeDotArrow";
	}

	public static common.StringCatter invoke(final Object c_f, final Object c_t) {
		try {
		final common.DecoratedNode context = new PmakeDotArrow(c_f, c_t).decorate();
		//if t == "core" then "" else "\"" ++ f ++ "\" -> \"" ++ t ++ "\";\n"
		return (common.StringCatter)((((common.StringCatter)context.childAsIs(silver.analysis.warnings.exporting.PmakeDotArrow.i_t)).equals((new common.StringCatter("core"))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.analysis.warnings.exporting.PmakeDotArrow.i_f)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" -> \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.analysis.warnings.exporting.PmakeDotArrow.i_t)), (common.StringCatter)(new common.StringCatter("\";\n"))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:exporting:makeDotArrow", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeDotArrow.invoke(children[0], children[1]);
		}
	};
}