
package core;

// top::Pair<a b> ::= f::a s::b 
public final class Ppair extends core.NPair {

	public static final int i_f = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = {Object.class,Object.class};

	public static final int num_local_attrs = Init.count_local__ON__core_pair;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NPair.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NPair.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Ppair(final Object c_f, final Object c_s) {
		super();
		this.child_f = c_f;
		this.child_s = c_s;

	}

	private Object child_f;
	public final Object getChild_f() {
		return (Object) (child_f = common.Util.demand(child_f));
	}

	private Object child_s;
	public final Object getChild_s() {
		return (Object) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production core:pair erroneously claimed to forward");
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
		return "core:pair";
	}

	static void initProductionAttributeDefinitions() {
		// top.fst = f
		core.Ppair.synthesizedAttributes[core.Init.core_fst__ON__core_Pair] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(core.Ppair.i_f)); } };
		// top.snd = s
		core.Ppair.synthesizedAttributes[core.Init.core_snd__ON__core_Pair] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(core.Ppair.i_s)); } };

	}

	public static final common.NodeFactory<Ppair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Ppair> {

		@Override
		public Ppair invoke(final Object[] children, final Object[] annotations) {
			return new Ppair(children[0], children[1]);
		}
	};

}
