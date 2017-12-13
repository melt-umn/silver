
package silver.modification.copper_mda;

public final class PfindSpec extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_mda_findSpec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfindSpec(final Object c_n, final Object c_s) {
		this.child_n = c_n;
		this.child_s = c_s;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_s;
	public final common.ConsCell getChild_s() {
		return (common.ConsCell) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_s: return child_s;

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
		return "silver:modification:copper_mda:findSpec";
	}

	public static common.ConsCell invoke(final Object c_n, final Object c_s) {
		try {
		final common.DecoratedNode context = new PfindSpec(c_n, c_s).decorate();
		//if null(s) then [] else if n == head(s).fullName then [ head(s) ] else findSpec(n, tail(s))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.copper_mda.PfindSpec.i_s))) ? ((common.ConsCell)core.Pnil.invoke()) : (((common.StringCatter)context.childAsIs(silver.modification.copper_mda.PfindSpec.i_n)).equals(((common.StringCatter)((silver.definition.concrete_syntax.NParserSpec)core.Phead.invoke(context.childAsIsLazy(silver.modification.copper_mda.PfindSpec.i_s))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_fullName__ON__silver_definition_concrete_syntax_ParserSpec))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.NParserSpec)core.Phead.invoke(context.childAsIsLazy(silver.modification.copper_mda.PfindSpec.i_s))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)silver.modification.copper_mda.PfindSpec.invoke(context.childAsIsLazy(silver.modification.copper_mda.PfindSpec.i_n), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.modification.copper_mda.PfindSpec.i_s))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:copper_mda:findSpec", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindSpec.invoke(children[0], children[1]);
		}
	};
}