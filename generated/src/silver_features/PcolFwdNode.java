
package silver_features;

// top::ColNT ::= c::ColNT 
public final class PcolFwdNode extends silver_features.NColNT {

	public static final int i_c = 0;


	public static final Class<?> childTypes[] = {silver_features.NColNT.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_colFwdNode;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NColNT.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NColNT.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_c] = new common.Lazy[silver_features.NColNT.num_inh_attrs];

	}

	public PcolFwdNode(final Object c_c) {
		super();
		this.child_c = c_c;

	}

	private Object child_c;
	public final silver_features.NColNT getChild_c() {
		return (silver_features.NColNT) (child_c = common.Util.demand(child_c));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_c: return getChild_c();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_c: return child_c;

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
		return ((silver_features.NColNT)new silver_features.PcolNode(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver_features.PcolFwdNode.i_c)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver_features.PcolFwdNode.i_c))));
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
		return "silver_features:colFwdNode";
	}

	static void initProductionAttributeDefinitions() {
		// top.colSyn <- " q "
		if(silver_features.PcolFwdNode.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] == null)
			silver_features.PcolFwdNode.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] = new silver_features.CAcolSyn(silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolFwdNode.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter(" q ")); } });

	}

	public static final common.NodeFactory<PcolFwdNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcolFwdNode> {

		@Override
		public PcolFwdNode invoke(final Object[] children, final Object[] annotations) {
			return new PcolFwdNode(children[0]);
		}
	};

}
