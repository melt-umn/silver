
package silver.definition.core;

// top::AGDcl ::= 'global' id::Name '::' t::TypeExpr '=' e::Expr ';' 
public final class PglobalValueDclConcrete extends silver.definition.core.NAGDcl {

	public static final int i__G_6 = 0;
	public static final int i_id = 1;
	public static final int i__G_4 = 2;
	public static final int i_t = 3;
	public static final int i__G_2 = 4;
	public static final int i_e = 5;
	public static final int i__G_0 = 6;


	public static final Class<?> childTypes[] = {silver.definition.core.TGlobal_kwd.class,silver.definition.core.NName.class,silver.definition.core.TColonColon_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TEqual_t.class,silver.definition.core.NExpr.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_globalValueDclConcrete;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PglobalValueDclConcrete(final Object c__G_6, final Object c_id, final Object c__G_4, final Object c_t, final Object c__G_2, final Object c_e, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_6 = c__G_6;
		this.child_id = c_id;
		this.child__G_4 = c__G_4;
		this.child_t = c_t;
		this.child__G_2 = c__G_2;
		this.child_e = c_e;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_6;
	public final silver.definition.core.TGlobal_kwd getChild__G_6() {
		return (silver.definition.core.TGlobal_kwd) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child__G_4;
	public final silver.definition.core.TColonColon_t getChild__G_4() {
		return (silver.definition.core.TColonColon_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_2;
	public final silver.definition.core.TEqual_t getChild__G_2() {
		return (silver.definition.core.TEqual_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_6: return getChild__G_6();
			case i_id: return getChild_id();
			case i__G_4: return getChild__G_4();
			case i_t: return getChild_t();
			case i__G_2: return getChild__G_2();
			case i_e: return getChild_e();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_6: return child__G_6;
			case i_id: return child_id;
			case i__G_4: return child__G_4;
			case i_t: return child_t;
			case i__G_2: return child__G_2;
			case i_e: return child_e;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:globalValueDclConcrete erroneously claimed to forward");
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
		return "silver:definition:core:globalValueDclConcrete";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "global " ++ id.pp ++ " :: " ++ t.pp ++ " = " ++ e.pp ++ "\n"
		silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("global ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PglobalValueDclConcrete.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" :: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PglobalValueDclConcrete.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PglobalValueDclConcrete.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter("\n")))))))); } };
		// top.errors := t.errors ++ e.errors
		if(silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PglobalValueDclConcrete.i_t, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr), context.childDecoratedSynthesizedLazy(silver.definition.core.PglobalValueDclConcrete.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr))); } });
		// fName = top.grammarName ++ ":" ++ id.name
		silver.definition.core.PglobalValueDclConcrete.localAttributes[silver.definition.core.Init.fName__ON__silver_definition_core_globalValueDclConcrete] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PglobalValueDclConcrete.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// top.defs = [ globalDef(top.grammarName, id.location, fName, t.typerep) ]
		silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PglobalDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PglobalValueDclConcrete.i_id).undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.definition.core.Init.fName__ON__silver_definition_core_globalValueDclConcrete), context.childDecoratedSynthesizedLazy(silver.definition.core.PglobalValueDclConcrete.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors <- if length(getValueDclAll(fName, top.env)) > 1 then [ err(id.location, "Value '" ++ fName ++ "' is already bound.") ] else []
		if(silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDclAll.invoke(context.localAsIsLazy(silver.definition.core.Init.fName__ON__silver_definition_core_globalValueDclConcrete), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PglobalValueDclConcrete.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Value '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.core.Init.fName__ON__silver_definition_core_globalValueDclConcrete)), (common.StringCatter)(new common.StringCatter("' is already bound.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// e.frame = globalExprContext()
		silver.definition.core.PglobalValueDclConcrete.childInheritedAttributes[silver.definition.core.PglobalValueDclConcrete.i_e][silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)new silver.definition.core.PglobalExprContext()); } };

	}

	public static final common.NodeFactory<PglobalValueDclConcrete> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PglobalValueDclConcrete> {

		@Override
		public PglobalValueDclConcrete invoke(final Object[] children, final Object[] annotations) {
			return new PglobalValueDclConcrete(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
