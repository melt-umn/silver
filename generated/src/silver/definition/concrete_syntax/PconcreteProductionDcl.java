
package silver.definition.concrete_syntax;

// top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 
public final class PconcreteProductionDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_5 = 0;
	public static final int i__G_4 = 1;
	public static final int i_id = 2;
	public static final int i_ns = 3;
	public static final int i_pm = 4;
	public static final int i_body = 5;


	public static final Class<?> childTypes[] = {silver.definition.core.TConcrete_kwd.class,silver.definition.core.TProduction_kwd.class,silver.definition.core.NName.class,silver.definition.core.NProductionSignature.class,silver.definition.concrete_syntax.NProductionModifiers.class,silver.definition.core.NProductionBody.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_concreteProductionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NProductionSignature.num_inh_attrs];
	childInheritedAttributes[i_pm] = new common.Lazy[silver.definition.concrete_syntax.NProductionModifiers.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];

	}

	public PconcreteProductionDcl(final Object c__G_5, final Object c__G_4, final Object c_id, final Object c_ns, final Object c_pm, final Object c_body, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child_id = c_id;
		this.child_ns = c_ns;
		this.child_pm = c_pm;
		this.child_body = c_body;

	}

	private Object child__G_5;
	public final silver.definition.core.TConcrete_kwd getChild__G_5() {
		return (silver.definition.core.TConcrete_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child__G_4;
	public final silver.definition.core.TProduction_kwd getChild__G_4() {
		return (silver.definition.core.TProduction_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_ns;
	public final silver.definition.core.NProductionSignature getChild_ns() {
		return (silver.definition.core.NProductionSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_pm;
	public final silver.definition.concrete_syntax.NProductionModifiers getChild_pm() {
		return (silver.definition.concrete_syntax.NProductionModifiers) (child_pm = common.Util.demand(child_pm));
	}

	private Object child_body;
	public final silver.definition.core.NProductionBody getChild_body() {
		return (silver.definition.core.NProductionBody) (child_body = common.Util.demand(child_body));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i__G_4: return getChild__G_4();
			case i_id: return getChild_id();
			case i_ns: return getChild_ns();
			case i_pm: return getChild_pm();
			case i_body: return getChild_body();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i__G_4: return child__G_4;
			case i_id: return child_id;
			case i_ns: return child_ns;
			case i_pm: return child_pm;
			case i_body: return child_body;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PmkProductionDcl(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.PconcreteProductionDcl.i_id)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.PconcreteProductionDcl.i_ns)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.PconcreteProductionDcl.i_body)), false, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:concrete_syntax:concreteProductionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "concrete production " ++ id.pp ++ "\n" ++ ns.pp ++ " " ++ pm.pp ++ "\n" ++ body.pp
		silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("concrete production ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_pm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifiers)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_body).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionBody))))))))); } };
		// fName = top.grammarName ++ ":" ++ id.name
		silver.definition.concrete_syntax.PconcreteProductionDcl.localAttributes[silver.definition.concrete_syntax.Init.fName__ON__silver_definition_concrete_syntax_concreteProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// namedSig = ns.namedSignature
		silver.definition.concrete_syntax.PconcreteProductionDcl.localAttributes[silver.definition.concrete_syntax.Init.namedSig__ON__silver_definition_concrete_syntax_concreteProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature)); } };
		// pm.productionName = fName
		silver.definition.concrete_syntax.PconcreteProductionDcl.childInheritedAttributes[silver.definition.concrete_syntax.PconcreteProductionDcl.i_pm][silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ProductionModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.Init.fName__ON__silver_definition_concrete_syntax_concreteProductionDcl)); } };
		// ns.signatureName = fName
		silver.definition.concrete_syntax.PconcreteProductionDcl.childInheritedAttributes[silver.definition.concrete_syntax.PconcreteProductionDcl.i_ns][silver.definition.core.Init.silver_definition_core_signatureName__ON__silver_definition_core_ProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.Init.fName__ON__silver_definition_concrete_syntax_concreteProductionDcl)); } };
		// ns.env = newScopeEnv(ns.defs, top.env)
		silver.definition.concrete_syntax.PconcreteProductionDcl.childInheritedAttributes[silver.definition.concrete_syntax.PconcreteProductionDcl.i_ns][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PconcreteProductionDcl.i_ns, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionSignature), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// top.errors <- pm.errors
		if(silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_pm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifiers)); } });
		// top.errors <- ns.concreteSyntaxTypeErrors
		if(silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_ns).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature)); } });
		// top.syntaxAst = [ syntaxProduction(namedSig, foldr(consProductionMod, nilProductionMod(), pm.productionModifiers)) ]
		silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxProduction(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.concrete_syntax.Init.namedSig__ON__silver_definition_concrete_syntax_concreteProductionDcl)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsProductionMod.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers)new silver.definition.concrete_syntax.ast.PnilProductionMod()); } }, context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PconcreteProductionDcl.i_pm, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifiers))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PconcreteProductionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconcreteProductionDcl> {

		@Override
		public PconcreteProductionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PconcreteProductionDcl(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
