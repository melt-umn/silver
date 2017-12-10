
package silver_features;

// top::ColNT ::= c1::ColNT c2::ColNT 
public final class PcolNode extends silver_features.NColNT {

	public static final int i_c1 = 0;
	public static final int i_c2 = 1;


	public static final Class<?> childTypes[] = {silver_features.NColNT.class,silver_features.NColNT.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_colNode;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NColNT.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NColNT.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_c1] = new common.Lazy[silver_features.NColNT.num_inh_attrs];
	childInheritedAttributes[i_c2] = new common.Lazy[silver_features.NColNT.num_inh_attrs];

	}

	public PcolNode(final Object c_c1, final Object c_c2) {
		super();
		this.child_c1 = c_c1;
		this.child_c2 = c_c2;

	}

	private Object child_c1;
	public final silver_features.NColNT getChild_c1() {
		return (silver_features.NColNT) (child_c1 = common.Util.demand(child_c1));
	}

	private Object child_c2;
	public final silver_features.NColNT getChild_c2() {
		return (silver_features.NColNT) (child_c2 = common.Util.demand(child_c2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_c1: return getChild_c1();
			case i_c2: return getChild_c2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_c1: return child_c1;
			case i_c2: return child_c2;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver_features:colNode erroneously claimed to forward");
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
		return "silver_features:colNode";
	}

	static void initProductionAttributeDefinitions() {
		// top.colSyn := c1.colSyn ++ c2.colSyn
		if(silver_features.PcolNode.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] == null)
			silver_features.PcolNode.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] = new silver_features.CAcolSyn(silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolNode.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver_features.PcolNode.i_c1).synthesized(silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver_features.PcolNode.i_c2).synthesized(silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT))); } });
		// c1.colInh := " a "
		if(silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c1][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] == null)
			silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c1][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] = new silver_features.CAcolInh();
		((common.CollectionAttribute)silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c1][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter(" a ")); } });
		// c2.colInh := " d "
		if(silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c2][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] == null)
			silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c2][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] = new silver_features.CAcolInh();
		((common.CollectionAttribute)silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c2][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter(" d ")); } });

	}

	public static final common.NodeFactory<PcolNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcolNode> {

		@Override
		public PcolNode invoke(final Object[] children, final Object[] annotations) {
			return new PcolNode(children[0], children[1]);
		}
	};

}
