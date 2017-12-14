
package silver.modification.impide;

// top::AGDcl ::= 'temp_imp_ide_font' id::Name 'color' '(' r::Int_t ',' g::Int_t ',' b::Int_t ')' fontStyles::FontStyles ';' 
public final class PfontDecl extends silver.definition.core.NAGDcl {

	public static final int i__G_11 = 0;
	public static final int i_id = 1;
	public static final int i__G_9 = 2;
	public static final int i__G_8 = 3;
	public static final int i_r = 4;
	public static final int i__G_6 = 5;
	public static final int i_g = 6;
	public static final int i__G_4 = 7;
	public static final int i_b = 8;
	public static final int i__G_2 = 9;
	public static final int i_fontStyles = 10;
	public static final int i__G_0 = 11;


	public static final Class<?> childTypes[] = {silver.modification.impide.TImpFont_t.class,silver.definition.core.NName.class,silver.modification.impide.TColor_kwd.class,silver.definition.core.TLParen_t.class,silver.definition.core.TInt_t.class,silver.definition.core.TComma_t.class,silver.definition.core.TInt_t.class,silver.definition.core.TComma_t.class,silver.definition.core.TInt_t.class,silver.definition.core.TRParen_t.class,silver.modification.impide.NFontStyles.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_fontDecl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[12][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_fontStyles] = new common.Lazy[silver.modification.impide.NFontStyles.num_inh_attrs];

	}

	public PfontDecl(final Object c__G_11, final Object c_id, final Object c__G_9, final Object c__G_8, final Object c_r, final Object c__G_6, final Object c_g, final Object c__G_4, final Object c_b, final Object c__G_2, final Object c_fontStyles, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_11 = c__G_11;
		this.child_id = c_id;
		this.child__G_9 = c__G_9;
		this.child__G_8 = c__G_8;
		this.child_r = c_r;
		this.child__G_6 = c__G_6;
		this.child_g = c_g;
		this.child__G_4 = c__G_4;
		this.child_b = c_b;
		this.child__G_2 = c__G_2;
		this.child_fontStyles = c_fontStyles;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_11;
	public final silver.modification.impide.TImpFont_t getChild__G_11() {
		return (silver.modification.impide.TImpFont_t) (child__G_11 = common.Util.demand(child__G_11));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child__G_9;
	public final silver.modification.impide.TColor_kwd getChild__G_9() {
		return (silver.modification.impide.TColor_kwd) (child__G_9 = common.Util.demand(child__G_9));
	}

	private Object child__G_8;
	public final silver.definition.core.TLParen_t getChild__G_8() {
		return (silver.definition.core.TLParen_t) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child_r;
	public final silver.definition.core.TInt_t getChild_r() {
		return (silver.definition.core.TInt_t) (child_r = common.Util.demand(child_r));
	}

	private Object child__G_6;
	public final silver.definition.core.TComma_t getChild__G_6() {
		return (silver.definition.core.TComma_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_g;
	public final silver.definition.core.TInt_t getChild_g() {
		return (silver.definition.core.TInt_t) (child_g = common.Util.demand(child_g));
	}

	private Object child__G_4;
	public final silver.definition.core.TComma_t getChild__G_4() {
		return (silver.definition.core.TComma_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_b;
	public final silver.definition.core.TInt_t getChild_b() {
		return (silver.definition.core.TInt_t) (child_b = common.Util.demand(child_b));
	}

	private Object child__G_2;
	public final silver.definition.core.TRParen_t getChild__G_2() {
		return (silver.definition.core.TRParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_fontStyles;
	public final silver.modification.impide.NFontStyles getChild_fontStyles() {
		return (silver.modification.impide.NFontStyles) (child_fontStyles = common.Util.demand(child_fontStyles));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_11: return getChild__G_11();
			case i_id: return getChild_id();
			case i__G_9: return getChild__G_9();
			case i__G_8: return getChild__G_8();
			case i_r: return getChild_r();
			case i__G_6: return getChild__G_6();
			case i_g: return getChild_g();
			case i__G_4: return getChild__G_4();
			case i_b: return getChild_b();
			case i__G_2: return getChild__G_2();
			case i_fontStyles: return getChild_fontStyles();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_11: return child__G_11;
			case i_id: return child_id;
			case i__G_9: return child__G_9;
			case i__G_8: return child__G_8;
			case i_r: return child_r;
			case i__G_6: return child__G_6;
			case i_g: return child_g;
			case i__G_4: return child__G_4;
			case i_b: return child_b;
			case i__G_2: return child__G_2;
			case i_fontStyles: return child_fontStyles;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 12;
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
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:impide:fontDecl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "temp_imp_ide_font " ++ id.name ++ " color(" ++ r.lexeme ++ ", " ++ g.lexeme ++ ", " ++ b.lexeme ++ ")" ++ fontStyles.pp ++ ";\n"
		silver.modification.impide.PfontDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("temp_imp_ide_font ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PfontDecl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" color(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.core.TInt_t)context.childAsIs(silver.modification.impide.PfontDecl.i_r)).lexeme), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.core.TInt_t)context.childAsIs(silver.modification.impide.PfontDecl.i_g)).lexeme), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.core.TInt_t)context.childAsIs(silver.modification.impide.PfontDecl.i_b)).lexeme), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PfontDecl.i_fontStyles).synthesized(silver.modification.impide.Init.silver_definition_env_pp__ON__silver_modification_impide_FontStyles)), (common.StringCatter)(new common.StringCatter(";\n")))))))))))); } };
		// fName = top.grammarName ++ ":" ++ id.name
		silver.modification.impide.PfontDecl.localAttributes[silver.modification.impide.Init.fName__ON__silver_modification_impide_fontDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PfontDecl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// top.defs = [ fontDef(top.grammarName, top.location, fName) ]
		silver.modification.impide.PfontDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.impide.PfontDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.impide.Init.fName__ON__silver_modification_impide_fontDecl))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := if length(getFontDcl(fName, top.env)) > 1 then [ err(id.location, "Font style '" ++ fName ++ "' is already bound.") ] else []
		if(silver.modification.impide.PfontDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.impide.PfontDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.impide.PfontDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.modification.impide.PgetFontDcl.invoke(context.localAsIsLazy(silver.modification.impide.Init.fName__ON__silver_modification_impide_fontDecl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.impide.PfontDecl.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Font style '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.impide.Init.fName__ON__silver_modification_impide_fontDecl)), (common.StringCatter)(new common.StringCatter("' is already bound.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.syntaxAst = [ syntaxFont(fName, font(makeColor(toInt(r.lexeme), toInt(g.lexeme), toInt(b.lexeme)), fontStyles.isBold, fontStyles.isItalic)) ]
		silver.modification.impide.PfontDecl.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.modification.impide.cstast.PsyntaxFont(context.localAsIsLazy(silver.modification.impide.Init.fName__ON__silver_modification_impide_fontDecl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.impide.spec.NFont)new silver.modification.impide.spec.Pfont(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.impide.spec.NColor)new silver.modification.impide.spec.PmakeColor(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((silver.definition.core.TInt_t)context.childAsIs(silver.modification.impide.PfontDecl.i_r)).lexeme).toString()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((silver.definition.core.TInt_t)context.childAsIs(silver.modification.impide.PfontDecl.i_g)).lexeme).toString()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((silver.definition.core.TInt_t)context.childAsIs(silver.modification.impide.PfontDecl.i_b)).lexeme).toString()); } })); } }, context.childDecoratedSynthesizedLazy(silver.modification.impide.PfontDecl.i_fontStyles, silver.modification.impide.Init.silver_modification_impide_isBold__ON__silver_modification_impide_FontStyles), context.childDecoratedSynthesizedLazy(silver.modification.impide.PfontDecl.i_fontStyles, silver.modification.impide.Init.silver_modification_impide_isItalic__ON__silver_modification_impide_FontStyles))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PfontDecl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfontDecl> {

		@Override
		public PfontDecl invoke(final Object[] children, final Object[] annotations) {
			return new PfontDecl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], children[9], children[10], children[11], annotations[0]);
		}
	};

}
