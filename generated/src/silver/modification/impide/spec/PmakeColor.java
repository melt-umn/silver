
package silver.modification.impide.spec;

// top::Color ::= r::Integer g::Integer b::Integer 
public final class PmakeColor extends silver.modification.impide.spec.NColor {

	public static final int i_r = 0;
	public static final int i_g = 1;
	public static final int i_b = 2;


	public static final Class<?> childTypes[] = {Integer.class,Integer.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_makeColor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.spec.NColor.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.spec.NColor.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PmakeColor(final Object c_r, final Object c_g, final Object c_b) {
		super();
		this.child_r = c_r;
		this.child_g = c_g;
		this.child_b = c_b;

	}

	private Object child_r;
	public final Integer getChild_r() {
		return (Integer) (child_r = common.Util.demand(child_r));
	}

	private Object child_g;
	public final Integer getChild_g() {
		return (Integer) (child_g = common.Util.demand(child_g));
	}

	private Object child_b;
	public final Integer getChild_b() {
		return (Integer) (child_b = common.Util.demand(child_b));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r: return getChild_r();
			case i_g: return getChild_g();
			case i_b: return getChild_b();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r: return child_r;
			case i_g: return child_g;
			case i_b: return child_b;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:spec:makeColor erroneously claimed to forward");
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
		return "silver:modification:impide:spec:makeColor";
	}

	static void initProductionAttributeDefinitions() {
		// top.r = r
		silver.modification.impide.spec.PmakeColor.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_r__ON__silver_modification_impide_spec_Color] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.modification.impide.spec.PmakeColor.i_r)); } };
		// top.g = g
		silver.modification.impide.spec.PmakeColor.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_g__ON__silver_modification_impide_spec_Color] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.modification.impide.spec.PmakeColor.i_g)); } };
		// top.b = b
		silver.modification.impide.spec.PmakeColor.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_b__ON__silver_modification_impide_spec_Color] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.modification.impide.spec.PmakeColor.i_b)); } };

	}

	public static final common.NodeFactory<PmakeColor> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmakeColor> {

		@Override
		public PmakeColor invoke(final Object[] children, final Object[] annotations) {
			return new PmakeColor(children[0], children[1], children[2]);
		}
	};

}
