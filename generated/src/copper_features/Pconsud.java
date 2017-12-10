
package copper_features;

// top::UseDcls ::= h::UseDcl t::UseDcls 
public final class Pconsud extends copper_features.NUseDcls {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {copper_features.NUseDcl.class,copper_features.NUseDcls.class};

	public static final int num_local_attrs = Init.count_local__ON__copper_features_consud;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[copper_features.NUseDcls.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[copper_features.NUseDcls.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[copper_features.NUseDcl.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[copper_features.NUseDcls.num_inh_attrs];

	}

	public Pconsud(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final copper_features.NUseDcl getChild_h() {
		return (copper_features.NUseDcl) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final copper_features.NUseDcls getChild_t() {
		return (copper_features.NUseDcls) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production copper_features:consud erroneously claimed to forward");
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
		return "copper_features:consud";
	}

	static void initProductionAttributeDefinitions() {
		// top.unknownsused = h.unknownsused ++ t.unknownsused
		copper_features.Pconsud.synthesizedAttributes[copper_features.Init.copper_features_unknownsused__ON__copper_features_UseDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(copper_features.Pconsud.i_h, copper_features.Init.copper_features_unknownsused__ON__copper_features_UseDcl), context.childDecoratedSynthesizedLazy(copper_features.Pconsud.i_t, copper_features.Init.copper_features_unknownsused__ON__copper_features_UseDcls))); } };

	}

	public static final common.NodeFactory<Pconsud> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pconsud> {

		@Override
		public Pconsud invoke(final Object[] children, final Object[] annotations) {
			return new Pconsud(children[0], children[1]);
		}
	};

}
