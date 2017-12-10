
package silver_features;

// top::Scope ::= rightCodeScoping::String 
public final class PrightCodeNaming extends silver_features.NScope {

	public static final int i_rightCodeScoping = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_rightCodeNaming;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NScope.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NScope.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PrightCodeNaming(final Object c_rightCodeScoping) {
		super();
		this.child_rightCodeScoping = c_rightCodeScoping;

	}

	private Object child_rightCodeScoping;
	public final common.StringCatter getChild_rightCodeScoping() {
		return (common.StringCatter) (child_rightCodeScoping = common.Util.demand(child_rightCodeScoping));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rightCodeScoping: return getChild_rightCodeScoping();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rightCodeScoping: return child_rightCodeScoping;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver_features:rightCodeNaming erroneously claimed to forward");
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
		return "silver_features:rightCodeNaming";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PrightCodeNaming> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrightCodeNaming> {

		@Override
		public PrightCodeNaming invoke(final Object[] children, final Object[] annotations) {
			return new PrightCodeNaming(children[0]);
		}
	};

}
