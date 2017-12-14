
package silver.extension.treegen;

public final class PzipCheckEqCalls extends common.FunctionNode {

	public static final int i_t = 0;
	public static final int i_l = 1;
	public static final int i_r = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_zipCheckEqCalls;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PzipCheckEqCalls(final Object c_t, final Object c_l, final Object c_r) {
		this.child_t = c_t;
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_t;
	public final common.ConsCell getChild_t() {
		return (common.ConsCell) (child_t = common.Util.demand(child_t));
	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final common.ConsCell getChild_r() {
		return (common.ConsCell) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i_l: return child_l;
			case i_r: return child_r;

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
		return "silver:extension:treegen:zipCheckEqCalls";
	}

	public static silver.definition.core.NExpr invoke(final Object c_t, final Object c_l, final Object c_r) {
		try {
		final common.DecoratedNode context = new PzipCheckEqCalls(c_t, c_l, c_r).decorate();
		//if null(t) then trueConst('true',location=loc("<generated>", -1, -1, -1, -1, -1, -1)) else if null(tail(t)) then c else and(c, '&&', zipCheckEqCalls(tail(t), tail(l), tail(r)),location=head(l).location)
		return (silver.definition.core.NExpr)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.treegen.PzipCheckEqCalls.i_t))) ? ((silver.definition.core.NExpr)new silver.definition.core.PtrueConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TTrue_kwd((new common.StringCatter("true")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)new core.Ploc((new common.StringCatter("<generated>")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1))); } })) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.PzipCheckEqCalls.i_t))); } })) ? context.localDecorated(silver.extension.treegen.Init.c__ON__silver_extension_treegen_zipCheckEqCalls).undecorate() : ((silver.definition.core.NExpr)new silver.definition.core.Pand(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.c__ON__silver_extension_treegen_zipCheckEqCalls)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TAnd_t((new common.StringCatter("&&")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.extension.treegen.PzipCheckEqCalls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.PzipCheckEqCalls.i_t))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.PzipCheckEqCalls.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.PzipCheckEqCalls.i_r))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.PzipCheckEqCalls.i_l))).getAnno_core_location()); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:zipCheckEqCalls", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PzipCheckEqCalls.invoke(children[0], children[1], children[2]);
		}
	};
}