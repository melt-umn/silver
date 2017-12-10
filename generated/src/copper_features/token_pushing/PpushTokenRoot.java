
package copper_features.token_pushing;

// top::PushTokenRoot ::= bottom::Contents 
public final class PpushTokenRoot extends copper_features.token_pushing.NPushTokenRoot {

	public static final int i_bottom = 0;


	public static final Class<?> childTypes[] = {copper_features.token_pushing.NContents.class};

	public static final int num_local_attrs = Init.count_local__ON__copper_features_token_pushing_pushTokenRoot;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[copper_features.token_pushing.NPushTokenRoot.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[copper_features.token_pushing.NPushTokenRoot.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_bottom] = new common.Lazy[copper_features.token_pushing.NContents.num_inh_attrs];

	}

	public PpushTokenRoot(final Object c_bottom) {
		super();
		this.child_bottom = c_bottom;

	}

	private Object child_bottom;
	public final copper_features.token_pushing.NContents getChild_bottom() {
		return (copper_features.token_pushing.NContents) (child_bottom = common.Util.demand(child_bottom));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_bottom: return getChild_bottom();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_bottom: return child_bottom;

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
		throw new common.exceptions.SilverInternalError("Production copper_features:token_pushing:pushTokenRoot erroneously claimed to forward");
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
		return "copper_features:token_pushing:pushTokenRoot";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PpushTokenRoot> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpushTokenRoot> {

		@Override
		public PpushTokenRoot invoke(final Object[] children, final Object[] annotations) {
			return new PpushTokenRoot(children[0]);
		}
	};

}
