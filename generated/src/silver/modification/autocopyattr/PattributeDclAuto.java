
package silver.modification.autocopyattr;

// top::AGDcl ::= 'autocopy' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';' 
public final class PattributeDclAuto extends silver.definition.core.NAGDcl {

	public static final int i__G_6 = 0;
	public static final int i__G_5 = 1;
	public static final int i_a = 2;
	public static final int i_tl = 3;
	public static final int i__G_2 = 4;
	public static final int i_te = 5;
	public static final int i__G_0 = 6;


	public static final Class<?> childTypes[] = {silver.modification.autocopyattr.TAutoCopy_kwd.class,silver.definition.core.TAttribute_kwd.class,silver.definition.core.NName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class,silver.definition.core.TColonColon_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_autocopyattr_attributeDclAuto;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_a] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_tl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	}

	public PattributeDclAuto(final Object c__G_6, final Object c__G_5, final Object c_a, final Object c_tl, final Object c__G_2, final Object c_te, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_6 = c__G_6;
		this.child__G_5 = c__G_5;
		this.child_a = c_a;
		this.child_tl = c_tl;
		this.child__G_2 = c__G_2;
		this.child_te = c_te;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_6;
	public final silver.modification.autocopyattr.TAutoCopy_kwd getChild__G_6() {
		return (silver.modification.autocopyattr.TAutoCopy_kwd) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child__G_5;
	public final silver.definition.core.TAttribute_kwd getChild__G_5() {
		return (silver.definition.core.TAttribute_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_a;
	public final silver.definition.core.NName getChild_a() {
		return (silver.definition.core.NName) (child_a = common.Util.demand(child_a));
	}

	private Object child_tl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_tl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_tl = common.Util.demand(child_tl));
	}

	private Object child__G_2;
	public final silver.definition.core.TColonColon_t getChild__G_2() {
		return (silver.definition.core.TColonColon_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_te;
	public final silver.definition.type.syntax.NTypeExpr getChild_te() {
		return (silver.definition.type.syntax.NTypeExpr) (child_te = common.Util.demand(child_te));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_6: return getChild__G_6();
			case i__G_5: return getChild__G_5();
			case i_a: return getChild_a();
			case i_tl: return getChild_tl();
			case i__G_2: return getChild__G_2();
			case i_te: return getChild_te();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_6: return child__G_6;
			case i__G_5: return child__G_5;
			case i_a: return child_a;
			case i_tl: return child_tl;
			case i__G_2: return child__G_2;
			case i_te: return child_te;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PattributeDclInh(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TInherited_kwd((new common.StringCatter("inherited")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.childAsIsLazy(silver.modification.autocopyattr.PattributeDclAuto.i__G_5), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.autocopyattr.PattributeDclAuto.i_a)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.autocopyattr.PattributeDclAuto.i_tl)), context.childAsIsLazy(silver.modification.autocopyattr.PattributeDclAuto.i__G_2), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.autocopyattr.PattributeDclAuto.i_te)), context.childAsIsLazy(silver.modification.autocopyattr.PattributeDclAuto.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:autocopyattr:attributeDclAuto";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "autocopy attribute " ++ a.pp ++ tl.pp ++ " :: " ++ te.pp ++ ";"
		silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("autocopy attribute ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.autocopyattr.PattributeDclAuto.i_a).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.autocopyattr.PattributeDclAuto.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" :: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.autocopyattr.PattributeDclAuto.i_te).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)(new common.StringCatter(";"))))))); } };
		// fName = top.grammarName ++ ":" ++ a.name
		silver.modification.autocopyattr.PattributeDclAuto.localAttributes[silver.modification.autocopyattr.Init.fName__ON__silver_modification_autocopyattr_attributeDclAuto] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.autocopyattr.PattributeDclAuto.i_a).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// top.defs = [ autocopyDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep) ]
		silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.autocopyattr.PautocopyDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.autocopyattr.PattributeDclAuto.i_a).undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.autocopyattr.Init.fName__ON__silver_modification_autocopyattr_attributeDclAuto), context.childDecoratedSynthesizedLazy(silver.modification.autocopyattr.PattributeDclAuto.i_tl, silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs), context.childDecoratedSynthesizedLazy(silver.modification.autocopyattr.PattributeDclAuto.i_te, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// tl.initialEnv = top.env
		silver.modification.autocopyattr.PattributeDclAuto.childInheritedAttributes[silver.modification.autocopyattr.PattributeDclAuto.i_tl][silver.definition.type.syntax.Init.silver_definition_type_syntax_initialEnv__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl)); } };
		// tl.env = tl.envBindingTyVars
		silver.modification.autocopyattr.PattributeDclAuto.childInheritedAttributes[silver.modification.autocopyattr.PattributeDclAuto.i_tl][silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.modification.autocopyattr.PattributeDclAuto.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_envBindingTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs)); } };
		// te.env = tl.envBindingTyVars
		silver.modification.autocopyattr.PattributeDclAuto.childInheritedAttributes[silver.modification.autocopyattr.PattributeDclAuto.i_te][silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.modification.autocopyattr.PattributeDclAuto.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_envBindingTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs)); } };
		// top.errors := tl.errors ++ te.errors ++ tl.errorsTyVars
		if(silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.autocopyattr.PattributeDclAuto.i_tl, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.autocopyattr.PattributeDclAuto.i_te, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr), context.childDecoratedSynthesizedLazy(silver.modification.autocopyattr.PattributeDclAuto.i_tl, silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } })); } });
		// top.errors <- if length(getAttrDclAll(fName, top.env)) > 1 then [ err(a.location, "Attribute '" ++ fName ++ "' is already bound.") ] else []
		if(silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetAttrDclAll.invoke(context.localAsIsLazy(silver.modification.autocopyattr.Init.fName__ON__silver_modification_autocopyattr_attributeDclAuto), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.autocopyattr.PattributeDclAuto.i_a).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Attribute '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.autocopyattr.Init.fName__ON__silver_modification_autocopyattr_attributeDclAuto)), (common.StringCatter)(new common.StringCatter("' is already bound.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if ! null(tl.types) then [ err(tl.location, "Autocopy attributes cannot be parameterized by type variables!") ] else []
		if(silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.modification.autocopyattr.PattributeDclAuto.i_tl, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs)))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NBracketedOptTypeExprs)context.childDecorated(silver.modification.autocopyattr.PattributeDclAuto.i_tl).undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Autocopy attributes cannot be parameterized by type variables!")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PattributeDclAuto> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PattributeDclAuto> {

		@Override
		public PattributeDclAuto invoke(final Object[] children, final Object[] annotations) {
			return new PattributeDclAuto(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
