
package silver_features.defs;

// top::Foo ::= a::Foo b::Decorated Foo e::Integer 
public final class PgoodEqs extends silver_features.defs.NFoo {

	public static final int i_a = 0;
	public static final int i_b = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = {silver_features.defs.NFoo.class,common.DecoratedNode.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_defs_goodEqs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.defs.NFoo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.defs.NFoo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_a] = new common.Lazy[silver_features.defs.NFoo.num_inh_attrs];

	}

	public PgoodEqs(final Object c_a, final Object c_b, final Object c_e) {
		super();
		this.child_a = c_a;
		this.child_b = c_b;
		this.child_e = c_e;

	}

	private Object child_a;
	public final silver_features.defs.NFoo getChild_a() {
		return (silver_features.defs.NFoo) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final common.DecoratedNode getChild_b() {
		return (common.DecoratedNode) (child_b = common.Util.demand(child_b));
	}

	private Object child_e;
	public final Integer getChild_e() {
		return (Integer) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_b: return getChild_b();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_b: return child_b;
			case i_e: return child_e;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:defs:goodEqs erroneously claimed to forward");
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
		return "silver_features:defs:goodEqs";
	}

	static void initProductionAttributeDefinitions() {
		// a.bar = ""
		silver_features.defs.PgoodEqs.childInheritedAttributes[silver_features.defs.PgoodEqs.i_a][silver_features.defs.Init.silver_features_defs_bar__ON__silver_features_defs_Foo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// c = a
		silver_features.defs.PgoodEqs.localAttributes[silver_features.defs.Init.c__ON__silver_features_defs_goodEqs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver_features.defs.PgoodEqs.i_a).undecorate(); } };
		// c.bar = ""
		silver_features.defs.PgoodEqs.localInheritedAttributes[silver_features.defs.Init.c__ON__silver_features_defs_goodEqs][silver_features.defs.Init.silver_features_defs_bar__ON__silver_features_defs_Foo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// d = b
		silver_features.defs.PgoodEqs.localAttributes[silver_features.defs.Init.d__ON__silver_features_defs_goodEqs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(silver_features.defs.PgoodEqs.i_b)); } };

	}

	public static final common.NodeFactory<PgoodEqs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgoodEqs> {

		@Override
		public PgoodEqs invoke(final Object[] children, final Object[] annotations) {
			return new PgoodEqs(children[0], children[1], children[2]);
		}
	};

}
