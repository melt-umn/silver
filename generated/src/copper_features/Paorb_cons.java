
package copper_features;

// top::AOrBs ::= h::AOrB t::AOrBs 
public final class Paorb_cons extends copper_features.NAOrBs {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {copper_features.NAOrB.class,copper_features.NAOrBs.class};

	public static final int num_local_attrs = Init.count_local__ON__copper_features_aorb_cons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[copper_features.NAOrBs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[copper_features.NAOrBs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[copper_features.NAOrB.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[copper_features.NAOrBs.num_inh_attrs];

	}

	public Paorb_cons(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final copper_features.NAOrB getChild_h() {
		return (copper_features.NAOrB) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final copper_features.NAOrBs getChild_t() {
		return (copper_features.NAOrBs) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production copper_features:aorb_cons erroneously claimed to forward");
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
		return "copper_features:aorb_cons";
	}

	static void initProductionAttributeDefinitions() {
		// top.semResult = h.semResult ++ t.semResult
		copper_features.Paorb_cons.synthesizedAttributes[copper_features.Init.copper_features_semResult__ON__copper_features_AOrBs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(copper_features.Paorb_cons.i_h).synthesized(copper_features.Init.copper_features_semResult__ON__copper_features_AOrB)), (common.StringCatter)((common.StringCatter)context.childDecorated(copper_features.Paorb_cons.i_t).synthesized(copper_features.Init.copper_features_semResult__ON__copper_features_AOrBs))); } };

	}

	public static final common.NodeFactory<Paorb_cons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Paorb_cons> {

		@Override
		public Paorb_cons invoke(final Object[] children, final Object[] annotations) {
			return new Paorb_cons(children[0], children[1]);
		}
	};

}
