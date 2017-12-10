
package silver_features;

// top::ColNT ::= 
public final class PcolProdLeaf extends silver_features.NColNT {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_colProdLeaf;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NColNT.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NColNT.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PcolProdLeaf() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver_features:colProdLeaf erroneously claimed to forward");
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
		return "silver_features:colProdLeaf";
	}

	static void initProductionAttributeDefinitions() {
		// lo := " j "
		((common.CollectionAttribute)silver_features.PcolProdLeaf.localAttributes[silver_features.Init.lo__ON__silver_features_colProdLeaf]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter(" j ")); } });
		// top.colSyn := lo
		if(silver_features.PcolProdLeaf.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] == null)
			silver_features.PcolProdLeaf.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] = new silver_features.CAcolSyn(silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolProdLeaf.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localAsIs(silver_features.Init.lo__ON__silver_features_colProdLeaf)); } });

	}

	public static final common.NodeFactory<PcolProdLeaf> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcolProdLeaf> {

		@Override
		public PcolProdLeaf invoke(final Object[] children, final Object[] annotations) {
			return new PcolProdLeaf();
		}
	};

}
