
package silver.extension.bidirtransform;

// opt::OptRHSType ::= Vbar_kwd t::TypeExpr Vbar_kwd 
public final class PoneRHSType extends silver.extension.bidirtransform.NOptRHSType {

	public static final int i__G_2 = 0;
	public static final int i_t = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.TVbar_kwd.class,silver.definition.type.syntax.NTypeExpr.class,silver.extension.patternmatching.TVbar_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_oneRHSType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NOptRHSType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NOptRHSType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	}

	public PoneRHSType(final Object c__G_2, final Object c_t, final Object c__G_0) {
		super();
		this.child__G_2 = c__G_2;
		this.child_t = c_t;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.extension.patternmatching.TVbar_kwd getChild__G_2() {
		return (silver.extension.patternmatching.TVbar_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_0;
	public final silver.extension.patternmatching.TVbar_kwd getChild__G_0() {
		return (silver.extension.patternmatching.TVbar_kwd) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_t: return getChild_t();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_t: return child_t;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:oneRHSType erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:oneRHSType";
	}

	static void initProductionAttributeDefinitions() {
		// opt.rhsType = just(t)
		silver.extension.bidirtransform.PoneRHSType.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rhsType__ON__silver_extension_bidirtransform_OptRHSType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PoneRHSType.i_t)))); } };

	}

	public static final common.NodeFactory<PoneRHSType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneRHSType> {

		@Override
		public PoneRHSType invoke(final Object[] children, final Object[] annotations) {
			return new PoneRHSType(children[0], children[1], children[2]);
		}
	};

}
