
package silver.extension.bidirtransform;

// top::BracketedOptTypeExprs ::= te::TypeExpr 
public final class PbotlOneTyExpr extends silver.definition.type.syntax.NBracketedOptTypeExprs {

	public static final int i_te = 0;


	public static final Class<?> childTypes[] = {silver.definition.type.syntax.NTypeExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_botlOneTyExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	}

	public PbotlOneTyExpr(final Object c_te, final Object a_core_location) {
		super(a_core_location);
		this.child_te = c_te;

	}

	private Object child_te;
	public final silver.definition.type.syntax.NTypeExpr getChild_te() {
		return (silver.definition.type.syntax.NTypeExpr) (child_te = common.Util.demand(child_te));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_te: return getChild_te();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_te: return child_te;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return ((silver.definition.type.syntax.NBracketedOptTypeExprs)new silver.definition.type.syntax.PbotlSome(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLT_t((new common.StringCatter("<")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExprs)new silver.definition.type.syntax.PtypeListSingle(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PbotlOneTyExpr.i_te)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NBracketedOptTypeExprs)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TGT_t((new common.StringCatter(">")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NBracketedOptTypeExprs)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:botlOneTyExpr";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PbotlOneTyExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbotlOneTyExpr> {

		@Override
		public PbotlOneTyExpr invoke(final Object[] children, final Object[] annotations) {
			return new PbotlOneTyExpr(children[0], annotations[0]);
		}
	};

}
