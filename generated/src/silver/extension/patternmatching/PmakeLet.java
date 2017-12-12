
package silver.extension.patternmatching;

public final class PmakeLet extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_s = 1;
	public static final int i_t = 2;
	public static final int i_e = 3;
	public static final int i_o = 4;


	public static final Class<?> childTypes[] = { core.NLocation.class,common.StringCatter.class,silver.definition.type.NType.class,silver.definition.core.NExpr.class,silver.definition.core.NExpr.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_makeLet;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_o] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PmakeLet(final Object c_l, final Object c_s, final Object c_t, final Object c_e, final Object c_o) {
		this.child_l = c_l;
		this.child_s = c_s;
		this.child_t = c_t;
		this.child_e = c_e;
		this.child_o = c_o;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_t;
	public final silver.definition.type.NType getChild_t() {
		return (silver.definition.type.NType) (child_t = common.Util.demand(child_t));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child_o;
	public final silver.definition.core.NExpr getChild_o() {
		return (silver.definition.core.NExpr) (child_o = common.Util.demand(child_o));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_s: return getChild_s();
			case i_t: return getChild_t();
			case i_e: return getChild_e();
			case i_o: return getChild_o();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_s: return child_s;
			case i_t: return child_t;
			case i_e: return child_e;
			case i_o: return child_o;

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
		return "silver:extension:patternmatching:makeLet";
	}

	public static silver.definition.core.NExpr invoke(final Object c_l, final Object c_s, final Object c_t, final Object c_e, final Object c_o) {
		try {
		final common.DecoratedNode context = new PmakeLet(c_l, c_s, c_t, c_e, c_o).decorate();
		//letp(assignExpr(name(s, l), '::', typerepTypeExpr(t,location=l), '=', e,location=l), o,location=l)
		return (silver.definition.core.NExpr)(((silver.definition.core.NExpr)new silver.modification.let_fix.Pletp(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.let_fix.NAssignExpr)new silver.modification.let_fix.PassignExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke(context.childAsIsLazy(silver.extension.patternmatching.PmakeLet.i_s), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PmakeLet.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TColonColon_t((new common.StringCatter("::")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.definition.type.syntax.PtyperepTypeExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PmakeLet.i_t)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PmakeLet.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TEqual_t((new common.StringCatter("=")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PmakeLet.i_e)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PmakeLet.i_l)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PmakeLet.i_o)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PmakeLet.i_l)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:patternmatching:makeLet", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeLet.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}