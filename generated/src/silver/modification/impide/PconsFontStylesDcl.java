
package silver.modification.impide;

// top::FontStyles ::= h::FontStyle t::FontStyles 
public final class PconsFontStylesDcl extends silver.modification.impide.NFontStyles {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.modification.impide.NFontStyle.class,silver.modification.impide.NFontStyles.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_consFontStylesDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NFontStyles.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NFontStyles.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.modification.impide.NFontStyle.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.modification.impide.NFontStyles.num_inh_attrs];

	}

	public PconsFontStylesDcl(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.modification.impide.NFontStyle getChild_h() {
		return (silver.modification.impide.NFontStyle) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.modification.impide.NFontStyles getChild_t() {
		return (silver.modification.impide.NFontStyles) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:consFontStylesDcl erroneously claimed to forward");
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
		return "silver:modification:impide:consFontStylesDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ t.pp
		silver.modification.impide.PconsFontStylesDcl.synthesizedAttributes[silver.modification.impide.Init.silver_definition_env_pp__ON__silver_modification_impide_FontStyles] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PconsFontStylesDcl.i_h).synthesized(silver.modification.impide.Init.silver_definition_env_pp__ON__silver_modification_impide_FontStyle)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PconsFontStylesDcl.i_t).synthesized(silver.modification.impide.Init.silver_definition_env_pp__ON__silver_modification_impide_FontStyles))); } };
		// top.isBold = h.isBold || t.isBold
		silver.modification.impide.PconsFontStylesDcl.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_isBold__ON__silver_modification_impide_FontStyles] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.modification.impide.PconsFontStylesDcl.i_h).synthesized(silver.modification.impide.Init.silver_modification_impide_isBold__ON__silver_modification_impide_FontStyle)) || ((Boolean)context.childDecorated(silver.modification.impide.PconsFontStylesDcl.i_t).synthesized(silver.modification.impide.Init.silver_modification_impide_isBold__ON__silver_modification_impide_FontStyles))); } };
		// top.isItalic = h.isItalic || t.isItalic
		silver.modification.impide.PconsFontStylesDcl.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_isItalic__ON__silver_modification_impide_FontStyles] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.modification.impide.PconsFontStylesDcl.i_h).synthesized(silver.modification.impide.Init.silver_modification_impide_isItalic__ON__silver_modification_impide_FontStyle)) || ((Boolean)context.childDecorated(silver.modification.impide.PconsFontStylesDcl.i_t).synthesized(silver.modification.impide.Init.silver_modification_impide_isItalic__ON__silver_modification_impide_FontStyles))); } };

	}

	public static final common.NodeFactory<PconsFontStylesDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsFontStylesDcl> {

		@Override
		public PconsFontStylesDcl invoke(final Object[] children, final Object[] annotations) {
			return new PconsFontStylesDcl(children[0], children[1]);
		}
	};

}
