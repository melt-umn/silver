
package silver.definition.core;

// top::ModuleExpr ::= pkg1::QName 'as' pkg2::QName 
public final class PmoduleAs extends silver.definition.core.NModuleExpr {

	public static final int i_pkg1 = 0;
	public static final int i__G_1 = 1;
	public static final int i_pkg2 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TAs_kwd.class,silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_moduleAs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NModuleExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NModuleExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_pkg1] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_pkg2] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PmoduleAs(final Object c_pkg1, final Object c__G_1, final Object c_pkg2, final Object a_core_location) {
		super(a_core_location);
		this.child_pkg1 = c_pkg1;
		this.child__G_1 = c__G_1;
		this.child_pkg2 = c_pkg2;

	}

	private Object child_pkg1;
	public final silver.definition.core.NQName getChild_pkg1() {
		return (silver.definition.core.NQName) (child_pkg1 = common.Util.demand(child_pkg1));
	}

	private Object child__G_1;
	public final silver.definition.core.TAs_kwd getChild__G_1() {
		return (silver.definition.core.TAs_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_pkg2;
	public final silver.definition.core.NQName getChild_pkg2() {
		return (silver.definition.core.NQName) (child_pkg2 = common.Util.demand(child_pkg2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pkg1: return getChild_pkg1();
			case i__G_1: return getChild__G_1();
			case i_pkg2: return getChild_pkg2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pkg1: return child_pkg1;
			case i__G_1: return child__G_1;
			case i_pkg2: return child_pkg2;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:moduleAs erroneously claimed to forward");
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
		return "silver:definition:core:moduleAs";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = pkg1.pp ++ " as " ++ pkg2.pp
		silver.definition.core.PmoduleAs.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmoduleAs.i_pkg1).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" as ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmoduleAs.i_pkg2).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)))); } };
		// top.moduleNames = [ pkg1.name ]
		silver.definition.core.PmoduleAs.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleAs.i_pkg1, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// m = module(pkg1.location, [ pkg1.name ], [ top.grammarName ], top.compiledGrammars, top.grammarDependencies, pkg2.name, [], [], [])
		silver.definition.core.PmoduleAs.localAttributes[silver.definition.core.Init.m__ON__silver_definition_core_moduleAs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModule)new silver.definition.core.Pmodule(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PmoduleAs.i_pkg1).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleAs.i_pkg1, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_ModuleExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_ModuleExpr), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarDependencies__ON__silver_definition_core_ModuleExpr), context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleAs.i_pkg2, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := m.errors
		if(silver.definition.core.PmoduleAs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr] == null)
			silver.definition.core.PmoduleAs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr);
		((common.CollectionAttribute)silver.definition.core.PmoduleAs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.m__ON__silver_definition_core_moduleAs).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Module)); } });
		// top.defs = m.defs
		silver.definition.core.PmoduleAs.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.m__ON__silver_definition_core_moduleAs).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Module)); } };

	}

	public static final common.NodeFactory<PmoduleAs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmoduleAs> {

		@Override
		public PmoduleAs invoke(final Object[] children, final Object[] annotations) {
			return new PmoduleAs(children[0], children[1], children[2], annotations[0]);
		}
	};

}
