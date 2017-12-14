
package silver.util.deque;

// top::Deque<a> ::= ln::Integer l::[a] rn::Integer r::[a] 
public final class Pdeque extends silver.util.deque.NDeque {

	public static final int i_ln = 0;
	public static final int i_l = 1;
	public static final int i_rn = 2;
	public static final int i_r = 3;


	public static final Class<?> childTypes[] = {Integer.class,common.DecoratedNode.class,Integer.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_util_deque_deque;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.deque.NDeque.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.deque.NDeque.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pdeque(final Object c_ln, final Object c_l, final Object c_rn, final Object c_r) {
		super();
		this.child_ln = c_ln;
		this.child_l = c_l;
		this.child_rn = c_rn;
		this.child_r = c_r;

	}

	private Object child_ln;
	public final Integer getChild_ln() {
		return (Integer) (child_ln = common.Util.demand(child_ln));
	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_rn;
	public final Integer getChild_rn() {
		return (Integer) (child_rn = common.Util.demand(child_rn));
	}

	private Object child_r;
	public final common.ConsCell getChild_r() {
		return (common.ConsCell) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ln: return getChild_ln();
			case i_l: return getChild_l();
			case i_rn: return getChild_rn();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ln: return child_ln;
			case i_l: return child_l;
			case i_rn: return child_rn;
			case i_r: return child_r;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:util:deque:deque erroneously claimed to forward");
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
		return "silver:util:deque:deque";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<Pdeque> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pdeque> {

		@Override
		public Pdeque invoke(final Object[] children, final Object[] annotations) {
			return new Pdeque(children[0], children[1], children[2], children[3]);
		}
	};

}
