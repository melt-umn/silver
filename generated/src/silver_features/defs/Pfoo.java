
package silver_features.defs;

// f1::Foo ::= f2::Foo 
public final class Pfoo extends silver_features.defs.NFoo {

	public static final int i_f2 = 0;


	public static final Class<?> childTypes[] = {silver_features.defs.NFoo.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_defs_foo;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.defs.NFoo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.defs.NFoo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_f2] = new common.Lazy[silver_features.defs.NFoo.num_inh_attrs];

	}

	public Pfoo(final Object c_f2) {
		super();
		this.child_f2 = c_f2;

	}

	private Object child_f2;
	public final silver_features.defs.NFoo getChild_f2() {
		return (silver_features.defs.NFoo) (child_f2 = common.Util.demand(child_f2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f2: return getChild_f2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f2: return child_f2;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:defs:foo erroneously claimed to forward");
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
		return "silver_features:defs:foo";
	}

	static void initProductionAttributeDefinitions() {
		// f1.foo = "Hi"
		silver_features.defs.Pfoo.synthesizedAttributes[silver_features.defs.Init.silver_features_defs_foo__ON__silver_features_defs_Foo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Hi")); } };
		// f2.bar = "asdf"
		silver_features.defs.Pfoo.childInheritedAttributes[silver_features.defs.Pfoo.i_f2][silver_features.defs.Init.silver_features_defs_bar__ON__silver_features_defs_Foo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("asdf")); } };
		// f3 = foo(f1,)
		silver_features.defs.Pfoo.localAttributes[silver_features.defs.Init.f3__ON__silver_features_defs_foo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver_features.defs.NFoo)new silver_features.defs.Pfoo(context.undecorate())); } };
		// f3.bar = "lkjkj"
		silver_features.defs.Pfoo.localInheritedAttributes[silver_features.defs.Init.f3__ON__silver_features_defs_foo][silver_features.defs.Init.silver_features_defs_bar__ON__silver_features_defs_Foo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("lkjkj")); } };

	}

	public static final common.NodeFactory<Pfoo> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pfoo> {

		@Override
		public Pfoo invoke(final Object[] children, final Object[] annotations) {
			return new Pfoo(children[0]);
		}
	};

}
