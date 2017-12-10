
package silver_features;

// t::ForwardKeyword ::= 
public final class Pbar2 extends silver_features.NForwardKeyword {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_bar2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NForwardKeyword.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NForwardKeyword.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pbar2() {
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
		throw new common.exceptions.SilverInternalError("Production silver_features:bar2 erroneously claimed to forward");
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
		return "silver_features:bar2";
	}

	static void initProductionAttributeDefinitions() {
		// t.fkSyn1 = t.fkInh1
		silver_features.Pbar2.synthesizedAttributes[silver_features.Init.silver_features_fkSyn1__ON__silver_features_ForwardKeyword] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver_features.Init.silver_features_fkInh1__ON__silver_features_ForwardKeyword)); } };
		// t.fkSyn2 = t.fkInh2
		silver_features.Pbar2.synthesizedAttributes[silver_features.Init.silver_features_fkSyn2__ON__silver_features_ForwardKeyword] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver_features.Init.silver_features_fkInh2__ON__silver_features_ForwardKeyword)); } };

	}

	public static final common.NodeFactory<Pbar2> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pbar2> {

		@Override
		public Pbar2 invoke(final Object[] children, final Object[] annotations) {
			return new Pbar2();
		}
	};

}
