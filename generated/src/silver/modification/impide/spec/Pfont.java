
package silver.modification.impide.spec;

// top::Font ::= color::Color isBold::Boolean isItalic::Boolean 
public final class Pfont extends silver.modification.impide.spec.NFont {

	public static final int i_color = 0;
	public static final int i_isBold = 1;
	public static final int i_isItalic = 2;


	public static final Class<?> childTypes[] = {silver.modification.impide.spec.NColor.class,Boolean.class,Boolean.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_font;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.spec.NFont.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.spec.NFont.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_color] = new common.Lazy[silver.modification.impide.spec.NColor.num_inh_attrs];

	}

	public Pfont(final Object c_color, final Object c_isBold, final Object c_isItalic) {
		super();
		this.child_color = c_color;
		this.child_isBold = c_isBold;
		this.child_isItalic = c_isItalic;

	}

	private Object child_color;
	public final silver.modification.impide.spec.NColor getChild_color() {
		return (silver.modification.impide.spec.NColor) (child_color = common.Util.demand(child_color));
	}

	private Object child_isBold;
	public final Boolean getChild_isBold() {
		return (Boolean) (child_isBold = common.Util.demand(child_isBold));
	}

	private Object child_isItalic;
	public final Boolean getChild_isItalic() {
		return (Boolean) (child_isItalic = common.Util.demand(child_isItalic));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_color: return getChild_color();
			case i_isBold: return getChild_isBold();
			case i_isItalic: return getChild_isItalic();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_color: return child_color;
			case i_isBold: return child_isBold;
			case i_isItalic: return child_isItalic;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:spec:font erroneously claimed to forward");
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
		return "silver:modification:impide:spec:font";
	}

	static void initProductionAttributeDefinitions() {
		// top.getTextAttribute = "TextAttributeProvider.getAttribute(display, " ++ toString(color.r) ++ ", " ++ toString(color.g) ++ ", " ++ toString(color.b) ++ ", " ++ boolToString(isBold) ++ ", " ++ boolToString(isItalic) ++ ")"
		silver.modification.impide.spec.Pfont.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_getTextAttribute__ON__silver_modification_impide_spec_Font] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("TextAttributeProvider.getAttribute(display, ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childDecorated(silver.modification.impide.spec.Pfont.i_color).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_r__ON__silver_modification_impide_spec_Color)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childDecorated(silver.modification.impide.spec.Pfont.i_color).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_g__ON__silver_modification_impide_spec_Color)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childDecorated(silver.modification.impide.spec.Pfont.i_color).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_b__ON__silver_modification_impide_spec_Color)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.impide.spec.PboolToString.invoke(context.childAsIsLazy(silver.modification.impide.spec.Pfont.i_isBold))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.impide.spec.PboolToString.invoke(context.childAsIsLazy(silver.modification.impide.spec.Pfont.i_isItalic))), (common.StringCatter)(new common.StringCatter(")")))))))))))); } };

	}

	public static final common.NodeFactory<Pfont> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pfont> {

		@Override
		public Pfont invoke(final Object[] children, final Object[] annotations) {
			return new Pfont(children[0], children[1], children[2]);
		}
	};

}
