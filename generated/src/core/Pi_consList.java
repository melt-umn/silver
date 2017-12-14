
package core;

// l::List<a> ::= h::a t::Decorated List<a> 
public final class Pi_consList extends core.NList {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {Object.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__core_i_consList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pi_consList(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final Object getChild_h() {
		return (Object) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final common.DecoratedNode getChild_t() {
		return (common.DecoratedNode) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production core:i_consList erroneously claimed to forward");
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
		return "core:i_consList";
	}

	static void initProductionAttributeDefinitions() {
		// l.i_emptyList = false
		core.Pi_consList.synthesizedAttributes[core.Init.core_i_emptyList__ON__core_List] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// l.i_lengthList = t.i_lengthList + 1
		core.Pi_consList.synthesizedAttributes[core.Init.core_i_lengthList__ON__core_List] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)((common.DecoratedNode)context.childAsIs(core.Pi_consList.i_t)).synthesized(core.Init.core_i_lengthList__ON__core_List)) + Integer.valueOf((int)1)); } };
		// l.i_headList = h
		core.Pi_consList.synthesizedAttributes[core.Init.core_i_headList__ON__core_List] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(core.Pi_consList.i_h)); } };
		// l.i_tailList = t
		core.Pi_consList.synthesizedAttributes[core.Init.core_i_tailList__ON__core_List] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(core.Pi_consList.i_t)); } };

	}

	public static final common.NodeFactory<Pi_consList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pi_consList> {

		@Override
		public Pi_consList invoke(final Object[] children, final Object[] annotations) {
			return new Pi_consList(children[0], children[1]);
		}
	};

}
