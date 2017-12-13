
package silver.extension.templating;

// top::Expr ::= Template_kwd t::TemplateString 
public final class PtemplateExpr extends silver.definition.core.NExpr {

	public static final int i__G_1 = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.extension.templating.TTemplate_kwd.class,silver.extension.templating.syntax.NTemplateString.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_templating_templateExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.extension.templating.syntax.NTemplateString.num_inh_attrs];

	}

	public PtemplateExpr(final Object c__G_1, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child__G_1 = c__G_1;
		this.child_t = c_t;

	}

	private Object child__G_1;
	public final silver.extension.templating.TTemplate_kwd getChild__G_1() {
		return (silver.extension.templating.TTemplate_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_t;
	public final silver.extension.templating.syntax.NTemplateString getChild_t() {
		return (silver.extension.templating.syntax.NTemplateString) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NExpr)silver.extension.templating.Pinfold.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.core.PplusPlus.factory.invokePartial(new int[]{1}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TPlusPlus_t((new common.StringCatter("++")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }}).invokeNamedPartial(null, new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }}); } }, context.childDecoratedSynthesizedLazy(silver.extension.templating.PtemplateExpr.i_t, silver.extension.templating.Init.silver_extension_templating_stringTemplate__ON__silver_extension_templating_syntax_TemplateString)));
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
		return "silver:extension:templating:templateExpr";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PtemplateExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtemplateExpr> {

		@Override
		public PtemplateExpr invoke(final Object[] children, final Object[] annotations) {
			return new PtemplateExpr(children[0], children[1], annotations[0]);
		}
	};

}
