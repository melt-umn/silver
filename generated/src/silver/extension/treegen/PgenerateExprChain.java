
package silver.extension.treegen;

public final class PgenerateExprChain extends common.FunctionNode {

	public static final int i_index = 0;
	public static final int i_lst = 1;
	public static final int i_total = 2;
	public static final int i_l = 3;


	public static final Class<?> childTypes[] = { Integer.class,common.DecoratedNode.class,Integer.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_generateExprChain;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PgenerateExprChain(final Object c_index, final Object c_lst, final Object c_total, final Object c_l) {
		this.child_index = c_index;
		this.child_lst = c_lst;
		this.child_total = c_total;
		this.child_l = c_l;

	}

	private Object child_index;
	public final Integer getChild_index() {
		return (Integer) (child_index = common.Util.demand(child_index));
	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}

	private Object child_total;
	public final Integer getChild_total() {
		return (Integer) (child_total = common.Util.demand(child_total));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_index: return getChild_index();
			case i_lst: return getChild_lst();
			case i_total: return getChild_total();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_index: return child_index;
			case i_lst: return child_lst;
			case i_total: return child_total;
			case i_l: return child_l;

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
		return "silver:extension:treegen:generateExprChain";
	}

	public static silver.definition.core.NExpr invoke(final Object c_index, final Object c_lst, final Object c_total, final Object c_l) {
		try {
		final common.DecoratedNode context = new PgenerateExprChain(c_index, c_lst, c_total, c_l).decorate();
		//if null(lst) then error("no productions for nonterminal at " ++ l.filename ++ ":" ++ toString(l.line) ++ "." ++ toString(l.column)) else if null(tail(lst)) then deriveGenerateOn(head(lst), l) else ifThenElse('if', lt(baseExpr(qName(l, "pval"),location=l), '<', floatConst(terminal(Float_t, toString(toFloat(index + 1) / toFloat(total)), core:loc("??", -1, -1, -1, -1, -1, -1)),location=l),location=l), 'then', deriveGenerateOn(head(lst), l), 'else', generateExprChain(index + 1, tail(lst), total, l),location=l)
		return (silver.definition.core.NExpr)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.treegen.PgenerateExprChain.i_lst))) ? ((silver.definition.core.NExpr)core.Perror.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("no productions for nonterminal at ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.treegen.PgenerateExprChain.i_l).synthesized(core.Init.core_filename__ON__core_Location)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childDecorated(silver.extension.treegen.PgenerateExprChain.i_l).synthesized(core.Init.core_line__ON__core_Location)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childDecorated(silver.extension.treegen.PgenerateExprChain.i_l).synthesized(core.Init.core_column__ON__core_Location))))))))); } })) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.PgenerateExprChain.i_lst))); } })) ? ((silver.definition.core.NExpr)silver.extension.treegen.PderiveGenerateOn.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.PgenerateExprChain.i_lst))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateExprChain.i_l)))) : ((silver.definition.core.NExpr)new silver.definition.core.PifThenElse(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TIf_kwd((new common.StringCatter("if")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.Plt(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateExprChain.i_l)), (new common.StringCatter("pval")))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateExprChain.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLT_t((new common.StringCatter("<")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PfloatConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TFloat_t(new common.StringCatter(String.valueOf(Float.valueOf(Float.valueOf(((Integer)Integer.valueOf(((Integer)context.childAsIs(silver.extension.treegen.PgenerateExprChain.i_index)) + Integer.valueOf((int)1))).floatValue()) / Float.valueOf(((Integer)((Integer)context.childAsIs(silver.extension.treegen.PgenerateExprChain.i_total))).floatValue())))), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateExprChain.i_l)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateExprChain.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TThen_kwd((new common.StringCatter("then")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.extension.treegen.PderiveGenerateOn.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.PgenerateExprChain.i_lst))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateExprChain.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TElse_kwd((new common.StringCatter("else")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.extension.treegen.PgenerateExprChain.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.extension.treegen.PgenerateExprChain.i_index)) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.PgenerateExprChain.i_lst))); } }, context.childAsIsLazy(silver.extension.treegen.PgenerateExprChain.i_total), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateExprChain.i_l)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateExprChain.i_l)))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:generateExprChain", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgenerateExprChain.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}