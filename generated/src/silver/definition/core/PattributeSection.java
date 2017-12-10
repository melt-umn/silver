
package silver.definition.core;

// top::Expr ::= '(' '.' q::QName ')' 
public final class PattributeSection extends silver.definition.core.NExpr {

	public static final int i__G_3 = 0;
	public static final int i__G_2 = 1;
	public static final int i_q = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.TLParen_t.class,silver.definition.core.TDot_t.class,silver.definition.core.NQName.class,silver.definition.core.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_attributeSection;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_q] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PattributeSection(final Object c__G_3, final Object c__G_2, final Object c_q, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_q = c_q;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_3;
	public final silver.definition.core.TLParen_t getChild__G_3() {
		return (silver.definition.core.TLParen_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TDot_t getChild__G_2() {
		return (silver.definition.core.TDot_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_q;
	public final silver.definition.core.NQName getChild_q() {
		return (silver.definition.core.NQName) (child_q = common.Util.demand(child_q));
	}

	private Object child__G_0;
	public final silver.definition.core.TRParen_t getChild__G_0() {
		return (silver.definition.core.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_q: return getChild_q();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_q: return child_q;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:attributeSection erroneously claimed to forward");
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
		return "silver:definition:core:attributeSection";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "(." ++ q.pp ++ ")"
		silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(.")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributeSection.i_q).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter(")")))); } };
		// rawInputType = freshType()
		silver.definition.core.PattributeSection.localAttributes[silver.definition.core.Init.rawInputType__ON__silver_definition_core_attributeSection] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshType.invoke()); } };
		// top.typerep = functionType(freshenCompletely(q.lookupAttribute.typerep), [ rawInputType ], [])
		silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PfunctionType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshenCompletely.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributeSection.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.rawInputType__ON__silver_definition_core_attributeSection)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := q.lookupAttribute.errors
		if(silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributeSection.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } });
		// top.errors <- if null(q.lookupAttribute.dclBoundVars) then [] else [ err(q.location, "Attribute " ++ q.pp ++ " is parameterized, and attribute sections currently do not work with parameterized attributes, yet.") ]
		if(silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributeSection.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dclBoundVars__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PattributeSection.i_q).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Attribute ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PattributeSection.i_q).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter(" is parameterized, and attribute sections currently do not work with parameterized attributes, yet.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.errors <- if ! null(q.lookupAttribute.errors) || q.lookupAttribute.dcl.isSynthesized then [] else [ err(q.location, "Only synthesized attributes are currently supported in attribute sections.") ]
		if(silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributeSection.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }))) || ((Boolean)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributeSection.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_isSynthesized__ON__silver_definition_env_DclInfo))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PattributeSection.i_q).undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Only synthesized attributes are currently supported in attribute sections.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// inputType = performSubstitution(rawInputType, top.finalSubst)
		silver.definition.core.PattributeSection.localAttributes[silver.definition.core.Init.inputType__ON__silver_definition_core_attributeSection] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.rawInputType__ON__silver_definition_core_attributeSection)), context.contextInheritedLazy(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_Expr))); } };
		// occursCheck = occursCheckQName(q, if inputType.isDecorated then inputType.decoratedType else inputType)
		silver.definition.core.PattributeSection.localAttributes[silver.definition.core.Init.occursCheck__ON__silver_definition_core_attributeSection] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NOccursCheck)new silver.definition.core.PoccursCheckQName(context.childDecoratedLazy(silver.definition.core.PattributeSection.i_q), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.definition.core.Init.inputType__ON__silver_definition_core_attributeSection).synthesized(silver.definition.type.Init.silver_definition_type_isDecorated__ON__silver_definition_type_Type)) ? ((silver.definition.type.NType)context.localDecorated(silver.definition.core.Init.inputType__ON__silver_definition_core_attributeSection).synthesized(silver.definition.type.Init.silver_definition_type_decoratedType__ON__silver_definition_type_Type)) : context.localDecorated(silver.definition.core.Init.inputType__ON__silver_definition_core_attributeSection).undecorate()); } })); } };
		// top.errors <- occursCheck.errors
		if(silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PattributeSection.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.occursCheck__ON__silver_definition_core_attributeSection).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_OccursCheck)); } });

	}

	public static final common.NodeFactory<PattributeSection> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PattributeSection> {

		@Override
		public PattributeSection invoke(final Object[] children, final Object[] annotations) {
			return new PattributeSection(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
