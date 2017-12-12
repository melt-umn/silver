
package core;

// top::IOVal<a> ::= i::IO v::a 
public final class Pioval extends core.NIOVal {

	public static final int i_i = 0;
	public static final int i_v = 1;


	public static final Class<?> childTypes[] = {Object.class,Object.class};

	public static final int num_local_attrs = Init.count_local__ON__core_ioval;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NIOVal.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NIOVal.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pioval(final Object c_i, final Object c_v) {
		super();
		this.child_i = c_i;
		this.child_v = c_v;

	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}

	private Object child_v;
	public final Object getChild_v() {
		return (Object) (child_v = common.Util.demand(child_v));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();
			case i_v: return getChild_v();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;
			case i_v: return child_v;

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
		throw new common.exceptions.SilverInternalError("Production core:ioval erroneously claimed to forward");
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
		return "core:ioval";
	}

	static void initProductionAttributeDefinitions() {
		// top.io = i
		core.Pioval.synthesizedAttributes[core.Init.core_io__ON__core_IOVal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(core.Pioval.i_i)); } };
		// top.iovalue = v
		core.Pioval.synthesizedAttributes[core.Init.core_iovalue__ON__core_IOVal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(core.Pioval.i_v)); } };

	}

	public static final common.NodeFactory<Pioval> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pioval> {

		@Override
		public Pioval invoke(final Object[] children, final Object[] annotations) {
			return new Pioval(children[0], children[1]);
		}
	};

}
