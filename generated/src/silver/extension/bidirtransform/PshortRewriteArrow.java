
package silver.extension.bidirtransform;

// arr::RewriteArrow ::= '->' opt::OptRHSType 
public final class PshortRewriteArrow extends silver.extension.bidirtransform.NRewriteArrow {

	public static final int i__G_1 = 0;
	public static final int i_opt = 1;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.TArrow_kwd.class,silver.extension.bidirtransform.NOptRHSType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_shortRewriteArrow;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteArrow.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteArrow.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_opt] = new common.Lazy[silver.extension.bidirtransform.NOptRHSType.num_inh_attrs];

	}

	public PshortRewriteArrow(final Object c__G_1, final Object c_opt) {
		super();
		this.child__G_1 = c__G_1;
		this.child_opt = c_opt;

	}

	private Object child__G_1;
	public final silver.extension.patternmatching.TArrow_kwd getChild__G_1() {
		return (silver.extension.patternmatching.TArrow_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_opt;
	public final silver.extension.bidirtransform.NOptRHSType getChild_opt() {
		return (silver.extension.bidirtransform.NOptRHSType) (child_opt = common.Util.demand(child_opt));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i_opt: return getChild_opt();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i_opt: return child_opt;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:shortRewriteArrow erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:shortRewriteArrow";
	}

	static void initProductionAttributeDefinitions() {
		// arr.shouldRestore = false
		silver.extension.bidirtransform.PshortRewriteArrow.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_shouldRestore__ON__silver_extension_bidirtransform_RewriteArrow] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// arr.rhsType = opt.rhsType
		silver.extension.bidirtransform.PshortRewriteArrow.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rhsType__ON__silver_extension_bidirtransform_RewriteArrow] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)context.childDecorated(silver.extension.bidirtransform.PshortRewriteArrow.i_opt).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rhsType__ON__silver_extension_bidirtransform_OptRHSType)); } };

	}

	public static final common.NodeFactory<PshortRewriteArrow> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PshortRewriteArrow> {

		@Override
		public PshortRewriteArrow invoke(final Object[] children, final Object[] annotations) {
			return new PshortRewriteArrow(children[0], children[1]);
		}
	};

}
