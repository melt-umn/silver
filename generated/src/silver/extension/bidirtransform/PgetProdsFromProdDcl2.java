
package silver.extension.bidirtransform;

public final class PgetProdsFromProdDcl2 extends common.FunctionNode {

	public static final int i_fnnt = 0;
	public static final int i_d = 1;
	public static final int i_dfs = 2;
	public static final int i_ns = 3;
	public static final int i_skipGrammar = 4;


	public static final Class<?> childTypes[] = { common.StringCatter.class,silver.definition.env.NDef.class,silver.definition.env.NDefs.class,common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_getProdsFromProdDcl2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDef.num_inh_attrs];
	childInheritedAttributes[i_dfs] = new common.Lazy[silver.definition.env.NDefs.num_inh_attrs];

	}

	public PgetProdsFromProdDcl2(final Object c_fnnt, final Object c_d, final Object c_dfs, final Object c_ns, final Object c_skipGrammar) {
		this.child_fnnt = c_fnnt;
		this.child_d = c_d;
		this.child_dfs = c_dfs;
		this.child_ns = c_ns;
		this.child_skipGrammar = c_skipGrammar;

	}

	private Object child_fnnt;
	public final common.StringCatter getChild_fnnt() {
		return (common.StringCatter) (child_fnnt = common.Util.demand(child_fnnt));
	}

	private Object child_d;
	public final silver.definition.env.NDef getChild_d() {
		return (silver.definition.env.NDef) (child_d = common.Util.demand(child_d));
	}

	private Object child_dfs;
	public final silver.definition.env.NDefs getChild_dfs() {
		return (silver.definition.env.NDefs) (child_dfs = common.Util.demand(child_dfs));
	}

	private Object child_ns;
	public final common.DecoratedNode getChild_ns() {
		return (common.DecoratedNode) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_skipGrammar;
	public final common.StringCatter getChild_skipGrammar() {
		return (common.StringCatter) (child_skipGrammar = common.Util.demand(child_skipGrammar));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fnnt: return getChild_fnnt();
			case i_d: return getChild_d();
			case i_dfs: return getChild_dfs();
			case i_ns: return getChild_ns();
			case i_skipGrammar: return getChild_skipGrammar();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fnnt: return child_fnnt;
			case i_d: return child_d;
			case i_dfs: return child_dfs;
			case i_ns: return child_ns;
			case i_skipGrammar: return child_skipGrammar;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return "silver:extension:bidirtransform:getProdsFromProdDcl2";
	}

	public static common.ConsCell invoke(final Object c_fnnt, final Object c_d, final Object c_dfs, final Object c_ns, final Object c_skipGrammar) {
		try {
		final common.DecoratedNode context = new PgetProdsFromProdDcl2(c_fnnt, c_d, c_dfs, c_ns, c_skipGrammar).decorate();
		//if nsMatchesStr(ns, fnnt) then [ d.dcl ] ++ getProdsFromDefs(fnnt, dfs, skipGrammar) else getProdsFromDefs2(fnnt, dfs, skipGrammar)
		return (common.ConsCell)((((Boolean)silver.extension.bidirtransform.PnsMatchesStr.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromProdDcl2.i_ns), context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromProdDcl2.i_fnnt))) ? ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PgetProdsFromProdDcl2.i_d, silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_Def), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PgetProdsFromDefs.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromProdDcl2.i_fnnt), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PgetProdsFromProdDcl2.i_dfs)), context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromProdDcl2.i_skipGrammar))); } })) : ((common.ConsCell)silver.extension.bidirtransform.PgetProdsFromDefs2.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromProdDcl2.i_fnnt), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PgetProdsFromProdDcl2.i_dfs)), context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromProdDcl2.i_skipGrammar)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:getProdsFromProdDcl2", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetProdsFromProdDcl2.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}