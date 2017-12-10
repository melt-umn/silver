
package silver_features;

// top::ColNT ::= 
public final class PcolLeaf extends silver_features.NColNT {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_colLeaf;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NColNT.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NColNT.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PcolLeaf() {
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
		throw new common.exceptions.SilverInternalError("Production silver_features:colLeaf erroneously claimed to forward");
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
		return "silver_features:colLeaf";
	}

	static void initProductionAttributeDefinitions() {
		// top.colSyn := "(" ++ top.colInh ++ ")"
		if(silver_features.PcolLeaf.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] == null)
			silver_features.PcolLeaf.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] = new silver_features.CAcolSyn(silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolLeaf.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver_features.Init.silver_features_colInh__ON__silver_features_ColNT)), (common.StringCatter)(new common.StringCatter(")")))); } });

	}

	public static final common.NodeFactory<PcolLeaf> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcolLeaf> {

		@Override
		public PcolLeaf invoke(final Object[] children, final Object[] annotations) {
			return new PcolLeaf();
		}
	};

}
