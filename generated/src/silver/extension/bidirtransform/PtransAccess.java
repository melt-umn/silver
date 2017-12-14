
package silver.extension.bidirtransform;

// top::Expr ::= tName::String rules::[Decorated TransformRule] lhs::String 
public final class PtransAccess extends silver.definition.core.NExpr {

	public static final int i_tName = 0;
	public static final int i_rules = 1;
	public static final int i_lhs = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_transAccess;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtransAccess(final Object c_tName, final Object c_rules, final Object c_lhs, final Object a_core_location) {
		super(a_core_location);
		this.child_tName = c_tName;
		this.child_rules = c_rules;
		this.child_lhs = c_lhs;

	}

	private Object child_tName;
	public final common.StringCatter getChild_tName() {
		return (common.StringCatter) (child_tName = common.Util.demand(child_tName));
	}

	private Object child_rules;
	public final common.ConsCell getChild_rules() {
		return (common.ConsCell) (child_rules = common.Util.demand(child_rules));
	}

	private Object child_lhs;
	public final common.StringCatter getChild_lhs() {
		return (common.StringCatter) (child_lhs = common.Util.demand(child_lhs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tName: return getChild_tName();
			case i_rules: return getChild_rules();
			case i_lhs: return getChild_lhs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tName: return child_tName;
			case i_rules: return child_rules;
			case i_lhs: return child_lhs;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return (((Integer)core.PlistLength.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PtransAccess.i_rules))).equals(Integer.valueOf((int)1)) ? ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PexprAccess(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PtransAccess.i_tName)), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((common.DecoratedNode)context.localAsIs(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_transAccess)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_trIndex__ON__silver_extension_bidirtransform_TransformRule))))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PtransAccess.i_lhs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PqAccess(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PtransAccess.i_tName)), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((common.DecoratedNode)context.localAsIs(silver.extension.bidirtransform.Init.snd__ON__silver_extension_bidirtransform_transAccess)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_trIndex__ON__silver_extension_bidirtransform_TransformRule))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PtransAccess(context.childAsIsLazy(silver.extension.bidirtransform.PtransAccess.i_tName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PtransAccess.i_rules))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PtransAccess.i_lhs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })));
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:extension:bidirtransform:transAccess";
	}

	static void initProductionAttributeDefinitions() {
		// hd = head(rules)
		silver.extension.bidirtransform.PtransAccess.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_transAccess] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PtransAccess.i_rules))); } };
		// snd = head(tail(rules))
		silver.extension.bidirtransform.PtransAccess.localAttributes[silver.extension.bidirtransform.Init.snd__ON__silver_extension_bidirtransform_transAccess] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PtransAccess.i_rules))); } })); } };

	}

	public static final common.NodeFactory<PtransAccess> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtransAccess> {

		@Override
		public PtransAccess invoke(final Object[] children, final Object[] annotations) {
			return new PtransAccess(children[0], children[1], children[2], annotations[0]);
		}
	};

}
