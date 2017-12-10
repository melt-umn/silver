
package performance;

// top::Q ::= bot::Q 
public final class Pq1 extends performance.NQ {

	public static final int i_bot = 0;


	public static final Class<?> childTypes[] = {performance.NQ.class};

	public static final int num_local_attrs = Init.count_local__ON__performance_q1;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[performance.NQ.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[performance.NQ.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_bot] = new common.Lazy[performance.NQ.num_inh_attrs];

	}

	public Pq1(final Object c_bot) {
		super();
		this.child_bot = c_bot;

	}

	private Object child_bot;
	public final performance.NQ getChild_bot() {
		return (performance.NQ) (child_bot = common.Util.demand(child_bot));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_bot: return getChild_bot();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_bot: return child_bot;

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
		throw new common.exceptions.SilverInternalError("Production performance:q1 erroneously claimed to forward");
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
		return "performance:q1";
	}

	static void initProductionAttributeDefinitions() {
		// top.a_q = bot
		performance.Pq1.synthesizedAttributes[performance.Init.performance_a_q__ON__performance_Q] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(performance.Pq1.i_bot).undecorate(); } };
		// top.str = "q"
		performance.Pq1.synthesizedAttributes[performance.Init.performance_str__ON__performance_Q] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("q")); } };

	}

	public static final common.NodeFactory<Pq1> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pq1> {

		@Override
		public Pq1 invoke(final Object[] children, final Object[] annotations) {
			return new Pq1(children[0]);
		}
	};

}
