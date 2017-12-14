
package silver.modification.typedecl;

// top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs '=' te::TypeExpr ';' 
public final class PtypeDecl extends silver.definition.core.NAGDcl {

	public static final int i__G_5 = 0;
	public static final int i_id = 1;
	public static final int i_tl = 2;
	public static final int i__G_2 = 3;
	public static final int i_te = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.modification.typedecl.TType_t.class,silver.definition.core.NName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class,silver.definition.core.TEqual_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_typedecl_typeDecl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_tl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	}

	public PtypeDecl(final Object c__G_5, final Object c_id, final Object c_tl, final Object c__G_2, final Object c_te, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child_id = c_id;
		this.child_tl = c_tl;
		this.child__G_2 = c__G_2;
		this.child_te = c_te;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final silver.modification.typedecl.TType_t getChild__G_5() {
		return (silver.modification.typedecl.TType_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_tl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_tl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_tl = common.Util.demand(child_tl));
	}

	private Object child__G_2;
	public final silver.definition.core.TEqual_t getChild__G_2() {
		return (silver.definition.core.TEqual_t) (child__G_2 = common.Util.demand(child__G_2));
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
			case i__G_5: return getChild__G_5();
			case i_id: return getChild_id();
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
			case i__G_5: return child__G_5;
			case i_id: return child_id;
			case i_tl: return child_tl;
			case i__G_2: return child__G_2;
			case i_te: return child_te;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:typedecl:typeDecl erroneously claimed to forward");
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
		return "silver:modification:typedecl:typeDecl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "type " ++ id.pp ++ tl.pp ++ "=" ++ te.pp ++ ";"
		silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.typedecl.PtypeDecl.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.typedecl.PtypeDecl.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("=")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.typedecl.PtypeDecl.i_te).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)(new common.StringCatter(";"))))))); } };
		// fName = top.grammarName ++ ":" ++ id.name
		silver.modification.typedecl.PtypeDecl.localAttributes[silver.modification.typedecl.Init.fName__ON__silver_modification_typedecl_typeDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.typedecl.PtypeDecl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// top.defs = [ typeAliasDef(top.grammarName, id.location, fName, tl.freeVariables, te.typerep) ]
		silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.typedecl.PtypeAliasDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.typedecl.PtypeDecl.i_id).undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.typedecl.Init.fName__ON__silver_modification_typedecl_typeDecl), context.childDecoratedSynthesizedLazy(silver.modification.typedecl.PtypeDecl.i_tl, silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs), context.childDecoratedSynthesizedLazy(silver.modification.typedecl.PtypeDecl.i_te, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := tl.errors ++ te.errors ++ tl.errorsTyVars
		if(silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.typedecl.PtypeDecl.i_tl, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.typedecl.PtypeDecl.i_te, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr), context.childDecoratedSynthesizedLazy(silver.modification.typedecl.PtypeDecl.i_tl, silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } })); } });
		// tl.initialEnv = top.env
		silver.modification.typedecl.PtypeDecl.childInheritedAttributes[silver.modification.typedecl.PtypeDecl.i_tl][silver.definition.type.syntax.Init.silver_definition_type_syntax_initialEnv__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl)); } };
		// tl.env = tl.envBindingTyVars
		silver.modification.typedecl.PtypeDecl.childInheritedAttributes[silver.modification.typedecl.PtypeDecl.i_tl][silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.modification.typedecl.PtypeDecl.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_envBindingTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs)); } };
		// te.env = tl.envBindingTyVars
		silver.modification.typedecl.PtypeDecl.childInheritedAttributes[silver.modification.typedecl.PtypeDecl.i_te][silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_TypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.modification.typedecl.PtypeDecl.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_envBindingTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs)); } };
		// top.errors <- if length(getTypeDclAll(fName, top.env)) > 1 then [ err(id.location, "Type '" ++ fName ++ "' is already bound.") ] else []
		if(silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetTypeDclAll.invoke(context.localAsIsLazy(silver.modification.typedecl.Init.fName__ON__silver_modification_typedecl_typeDecl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.typedecl.PtypeDecl.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Type '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.typedecl.Init.fName__ON__silver_modification_typedecl_typeDecl)), (common.StringCatter)(new common.StringCatter("' is already bound.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if isLower(substring(0, 1, id.name)) then [ err(id.location, "Types must be capitalized. Invalid nonterminal name " ++ id.name) ] else []
		if(silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.typedecl.PtypeDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.PisLower.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), Integer.valueOf((int)1), context.childDecoratedSynthesizedLazy(silver.modification.typedecl.PtypeDecl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))); } })) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.typedecl.PtypeDecl.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Types must be capitalized. Invalid nonterminal name ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.typedecl.PtypeDecl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PtypeDecl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtypeDecl> {

		@Override
		public PtypeDecl invoke(final Object[] children, final Object[] annotations) {
			return new PtypeDecl(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
