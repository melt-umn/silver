
package silver.extension.bidirtransform;

// top::ProductionStmts ::= stmts::[ProductionStmt] 
public final class PprdStmtList extends silver.definition.core.NProductionStmts {

	public static final int i_stmts = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_prdStmtList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmts.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmts.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PprdStmtList(final Object c_stmts, final Object a_core_location) {
		super(a_core_location);
		this.child_stmts = c_stmts;

	}

	private Object child_stmts;
	public final common.ConsCell getChild_stmts() {
		return (common.ConsCell) (child_stmts = common.Util.demand(child_stmts));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_stmts: return getChild_stmts();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_stmts: return child_stmts;

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
		return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PprdStmtList.i_stmts))) ? ((silver.definition.core.NProductionStmts)new silver.definition.core.PproductionStmtsNil(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmts)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NProductionStmts)new silver.definition.core.PproductionStmtsSnoc(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmts)new silver.extension.bidirtransform.PprdStmtList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PprdStmtList.i_stmts))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmts)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PprdStmtList.i_stmts))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmts)context.undecorate()).getAnno_core_location()); } })));
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
		return "silver:extension:bidirtransform:prdStmtList";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PprdStmtList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprdStmtList> {

		@Override
		public PprdStmtList invoke(final Object[] children, final Object[] annotations) {
			return new PprdStmtList(children[0], annotations[0]);
		}
	};

}
