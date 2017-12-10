
package silver.modification.impide;

// top::FontStyle ::= 'bold' 
public final class PfontStyleBoldDcl extends silver.modification.impide.NFontStyle {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.modification.impide.TBold_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_fontStyleBoldDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NFontStyle.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NFontStyle.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PfontStyleBoldDcl(final Object c__G_0) {
		super();
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.modification.impide.TBold_kwd getChild__G_0() {
		return (silver.modification.impide.TBold_kwd) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:fontStyleBoldDcl erroneously claimed to forward");
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
		return "silver:modification:impide:fontStyleBoldDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = " bold"
		silver.modification.impide.PfontStyleBoldDcl.synthesizedAttributes[silver.modification.impide.Init.silver_definition_env_pp__ON__silver_modification_impide_FontStyle] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter(" bold")); } };
		// top.isBold = true
		silver.modification.impide.PfontStyleBoldDcl.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_isBold__ON__silver_modification_impide_FontStyle] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.isItalic = false
		silver.modification.impide.PfontStyleBoldDcl.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_isItalic__ON__silver_modification_impide_FontStyle] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };

	}

	public static final common.NodeFactory<PfontStyleBoldDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfontStyleBoldDcl> {

		@Override
		public PfontStyleBoldDcl invoke(final Object[] children, final Object[] annotations) {
			return new PfontStyleBoldDcl(children[0]);
		}
	};

}
