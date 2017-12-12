
package silver.definition.core;

// top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';' 
public final class PattributionDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_7 = 0;
	public static final int i_at = 1;
	public static final int i_attl = 2;
	public static final int i__G_4 = 3;
	public static final int i__G_3 = 4;
	public static final int i_nt = 5;
	public static final int i_nttl = 6;
	public static final int i__G_0 = 7;


	public static final Class<?> childTypes[] = {silver.definition.core.TAttribute_kwd.class,silver.definition.core.NQName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class,silver.definition.core.TOccurs_kwd.class,silver.definition.core.TOn_kwd.class,silver.definition.core.NQName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_attributionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_at] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_attl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];
	childInheritedAttributes[i_nt] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_nttl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];

	}

	public PattributionDcl(final Object c__G_7, final Object c_at, final Object c_attl, final Object c__G_4, final Object c__G_3, final Object c_nt, final Object c_nttl, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_7 = c__G_7;
		this.child_at = c_at;
		this.child_attl = c_attl;
		this.child__G_4 = c__G_4;
		this.child__G_3 = c__G_3;
		this.child_nt = c_nt;
		this.child_nttl = c_nttl;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_7;
	public final silver.definition.core.TAttribute_kwd getChild__G_7() {
		return (silver.definition.core.TAttribute_kwd) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child_at;
	public final silver.definition.core.NQName getChild_at() {
		return (silver.definition.core.NQName) (child_at = common.Util.demand(child_at));
	}

	private Object child_attl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_attl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_attl = common.Util.demand(child_attl));
	}

	private Object child__G_4;
	public final silver.definition.core.TOccurs_kwd getChild__G_4() {
		return (silver.definition.core.TOccurs_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child__G_3;
	public final silver.definition.core.TOn_kwd getChild__G_3() {
		return (silver.definition.core.TOn_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_nt;
	public final silver.definition.core.NQName getChild_nt() {
		return (silver.definition.core.NQName) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_nttl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_nttl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_nttl = common.Util.demand(child_nttl));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_7: return getChild__G_7();
			case i_at: return getChild_at();
			case i_attl: return getChild_attl();
			case i__G_4: return getChild__G_4();
			case i__G_3: return getChild__G_3();
			case i_nt: return getChild_nt();
			case i_nttl: return getChild_nttl();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_7: return child__G_7;
			case i_at: return child_at;
			case i_attl: return child_attl;
			case i__G_4: return child__G_4;
			case i__G_3: return child__G_3;
			case i_nt: return child_nt;
			case i_nttl: return child_nttl;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 8;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:attributionDcl erroneously claimed to forward");
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
		return "silver:definition:core:attributionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "attribute " ++ at.pp ++ attl.pp ++ " occurs on " ++ nt.pp ++ nttl.pp ++ ";"
		silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("attribute ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributionDcl.i_attl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" occurs on ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributionDcl.i_nttl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs)), (common.StringCatter)(new common.StringCatter(";")))))))); } };
		// top.defs = [ oDef((if ! null(at.lookupAttribute.errors) || ! at.lookupAttribute.dcl.isAnnotation then occursDcl else annoInstanceDcl)(top.grammarName, at.location, nt.lookupType.fullName, at.lookupAttribute.fullName, protontty, protoatty)) ]
		silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)new silver.definition.env.PoDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)(((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }))) || (!((Boolean)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_isAnnotation__ON__silver_definition_env_DclInfo)))) ? silver.definition.env.PoccursDcl.factory : silver.definition.env.PannoInstanceDcl.factory).invoke(new Object[]{context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PattributionDcl.i_at).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.protontty__ON__silver_definition_core_attributionDcl)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.protoatty__ON__silver_definition_core_attributionDcl))}, null)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := at.lookupAttribute.errors ++ nt.lookupType.errors ++ attl.errors ++ nttl.errors ++ nttl.errorsTyVars
		if(silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_attl, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_nttl, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs), context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_nttl, silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } })); } })); } })); } });
		// nttl.initialEnv = top.env
		silver.definition.core.PattributionDcl.childInheritedAttributes[silver.definition.core.PattributionDcl.i_nttl][silver.definition.type.syntax.Init.silver_definition_type_syntax_initialEnv__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl)); } };
		// attl.env = nttl.envBindingTyVars
		silver.definition.core.PattributionDcl.childInheritedAttributes[silver.definition.core.PattributionDcl.i_attl][silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nttl).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_envBindingTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs)); } };
		// nttl.env = nttl.envBindingTyVars
		silver.definition.core.PattributionDcl.childInheritedAttributes[silver.definition.core.PattributionDcl.i_nttl][silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nttl).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_envBindingTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs)); } };
		// top.errors <- if length(nt.lookupType.dclBoundVars) != length(nttl.types) then [ err(nt.location, nt.pp ++ " expects " ++ toString(length(nt.lookupType.dclBoundVars)) ++ " type variables, but " ++ toString(length(nttl.types)) ++ " were provided.") ] else []
		if(silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup)); } })).equals(((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_nttl, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs)))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" expects ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup)); } })))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" type variables, but ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_nttl, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs))))), (common.StringCatter)(new common.StringCatter(" were provided."))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if length(at.lookupAttribute.dclBoundVars) != length(attl.types) then [ err(at.location, at.pp ++ " expects " ++ toString(length(at.lookupAttribute.dclBoundVars)) ++ " type variables, but " ++ toString(length(attl.types)) ++ " were provided.") ] else []
		if(silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup)); } })).equals(((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_attl, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs)))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PattributionDcl.i_at).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" expects ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup)); } })))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" type variables, but ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_attl, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs))))), (common.StringCatter)(new common.StringCatter(" were provided."))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// rewriteAndFreshenSubst = composeSubst(composeSubst(zipVarsIntoSubstitution(nt.lookupType.dclBoundVars, nttl.freeVariables), zipVarsAndTypesIntoSubstitution(at.lookupAttribute.dclBoundVars, attl.types)), zipVarsIntoSubstitution(nttl.freeVariables, freshTyVars(length(nttl.freeVariables))))
		silver.definition.core.PattributionDcl.localAttributes[silver.definition.core.Init.rewriteAndFreshenSubst__ON__silver_definition_core_attributionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PcomposeSubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PcomposeSubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PzipVarsIntoSubstitution.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup)); } }, context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_nttl, silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PzipVarsAndTypesIntoSubstitution.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup)); } }, context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_attl, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PzipVarsIntoSubstitution.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_nttl, silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PfreshTyVars.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_nttl, silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } })); } })); } })); } };
		// protontty = performSubstitution(nt.lookupType.typerep, rewriteAndFreshenSubst)
		silver.definition.core.PattributionDcl.localAttributes[silver.definition.core.Init.protontty__ON__silver_definition_core_attributionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.rewriteAndFreshenSubst__ON__silver_definition_core_attributionDcl)))); } };
		// protoatty = performSubstitution(at.lookupAttribute.typerep, rewriteAndFreshenSubst)
		silver.definition.core.PattributionDcl.localAttributes[silver.definition.core.Init.protoatty__ON__silver_definition_core_attributionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.rewriteAndFreshenSubst__ON__silver_definition_core_attributionDcl)))); } };
		// occursCheck = getOccursDcl(at.lookupAttribute.fullName, nt.lookupType.fullName, top.env)
		silver.definition.core.PattributionDcl.localAttributes[silver.definition.core.Init.occursCheck__ON__silver_definition_core_attributionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetOccursDcl.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// top.errors <- if length(occursCheck) > 1 then [ err(at.location, "Attribute '" ++ at.name ++ "' already occurs on '" ++ nt.name ++ "'.") ] else []
		if(silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.localAsIsLazy(silver.definition.core.Init.occursCheck__ON__silver_definition_core_attributionDcl))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PattributionDcl.i_at).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Attribute '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("' already occurs on '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter("'.")))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if ! nt.lookupType.typerep.isDecorable then [ err(nt.location, nt.name ++ " is not a nonterminal. Attributes can only occur on nonterminals.") ] else []
		if(silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter(" is not a nonterminal. Attributes can only occur on nonterminals."))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if ! null(nt.lookupType.errors ++ at.lookupType.errors) || ! at.lookupAttribute.dcl.isAnnotation || isExportedBy(top.grammarName, [ nt.lookupType.dcl.sourceGrammar ], top.compiledGrammars) then [] else [ err(top.location, "Annotations for a nonterminal must be in a module exported by the nonterminal's declaring grammar.") ]
		if(silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PattributionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })); } }))) || (!((Boolean)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_isAnnotation__ON__silver_definition_env_DclInfo)))) || ((Boolean)silver.driver.util.PisExportedBy.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Annotations for a nonterminal must be in a module exported by the nonterminal's declaring grammar.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });

	}

	public static final common.NodeFactory<PattributionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PattributionDcl> {

		@Override
		public PattributionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PattributionDcl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], annotations[0]);
		}
	};

}
