
package copper_features.token_pushing;

// top::Contents ::= 'A' 'B' 'C' 
public final class Pp_copper_features_token_pushing_TokenPush_sv_32_0 extends copper_features.token_pushing.NContents {

	public static final int i__G_2 = 0;
	public static final int i__G_1 = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {copper_features.token_pushing.TA.class,copper_features.token_pushing.TB.class,copper_features.token_pushing.TC.class};

	public static final int num_local_attrs = Init.count_local__ON__copper_features_token_pushing_p_copper_features_token_pushing_TokenPush_sv_32_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[copper_features.token_pushing.NContents.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[copper_features.token_pushing.NContents.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pp_copper_features_token_pushing_TokenPush_sv_32_0(final Object c__G_2, final Object c__G_1, final Object c__G_0) {
		super();
		this.child__G_2 = c__G_2;
		this.child__G_1 = c__G_1;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final copper_features.token_pushing.TA getChild__G_2() {
		return (copper_features.token_pushing.TA) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child__G_1;
	public final copper_features.token_pushing.TB getChild__G_1() {
		return (copper_features.token_pushing.TB) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child__G_0;
	public final copper_features.token_pushing.TC getChild__G_0() {
		return (copper_features.token_pushing.TC) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i__G_1: return getChild__G_1();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i__G_1: return child__G_1;
			case i__G_0: return child__G_0;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production copper_features:token_pushing:p_copper_features_token_pushing_TokenPush_sv_32_0 erroneously claimed to forward");
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
		return "copper_features:token_pushing:p_copper_features_token_pushing_TokenPush_sv_32_0";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<Pp_copper_features_token_pushing_TokenPush_sv_32_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_copper_features_token_pushing_TokenPush_sv_32_0> {

		@Override
		public Pp_copper_features_token_pushing_TokenPush_sv_32_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_copper_features_token_pushing_TokenPush_sv_32_0(children[0], children[1], children[2]);
		}
	};

}
