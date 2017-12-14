
package silver.driver.util;

// top::Compilation ::= g::Grammars r::Grammars buildGrammar::String benv::BuildEnv 
public final class Pcompilation extends silver.driver.util.NCompilation {

	public static final int i_g = 0;
	public static final int i_r = 1;
	public static final int i_buildGrammar = 2;
	public static final int i_benv = 3;


	public static final Class<?> childTypes[] = {silver.driver.util.NGrammars.class,silver.driver.util.NGrammars.class,common.StringCatter.class,silver.driver.util.NBuildEnv.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_compilation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NCompilation.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NCompilation.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_g] = new common.Lazy[silver.driver.util.NGrammars.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.driver.util.NGrammars.num_inh_attrs];
	childInheritedAttributes[i_benv] = new common.Lazy[silver.driver.util.NBuildEnv.num_inh_attrs];

	}

	public Pcompilation(final Object c_g, final Object c_r, final Object c_buildGrammar, final Object c_benv) {
		super();
		this.child_g = c_g;
		this.child_r = c_r;
		this.child_buildGrammar = c_buildGrammar;
		this.child_benv = c_benv;

	}

	private Object child_g;
	public final silver.driver.util.NGrammars getChild_g() {
		return (silver.driver.util.NGrammars) (child_g = common.Util.demand(child_g));
	}

	private Object child_r;
	public final silver.driver.util.NGrammars getChild_r() {
		return (silver.driver.util.NGrammars) (child_r = common.Util.demand(child_r));
	}

	private Object child_buildGrammar;
	public final common.StringCatter getChild_buildGrammar() {
		return (common.StringCatter) (child_buildGrammar = common.Util.demand(child_buildGrammar));
	}

	private Object child_benv;
	public final silver.driver.util.NBuildEnv getChild_benv() {
		return (silver.driver.util.NBuildEnv) (child_benv = common.Util.demand(child_benv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_g: return getChild_g();
			case i_r: return getChild_r();
			case i_buildGrammar: return getChild_buildGrammar();
			case i_benv: return getChild_benv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_g: return child_g;
			case i_r: return child_r;
			case i_buildGrammar: return child_buildGrammar;
			case i_benv: return child_benv;

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
		throw new common.exceptions.SilverInternalError("Production silver:driver:util:compilation erroneously claimed to forward");
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
		return "silver:driver:util:compilation";
	}

	static void initProductionAttributeDefinitions() {
		// top.grammarList = g.grammarList
		silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.Pcompilation.i_g).synthesized(silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars)); } };
		// top.recheckGrammars = g.recheckGrammars
		silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_Compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.Pcompilation.i_g).synthesized(silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_Grammars)); } };
		// g.compiledGrammars = directBuildTree(map(grammarPairing, g.grammarList))
		silver.driver.util.Pcompilation.childInheritedAttributes[silver.driver.util.Pcompilation.i_g][silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PdirectBuildTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.driver.util.PgrammarPairing.factory, context.childDecoratedSynthesizedLazy(silver.driver.util.Pcompilation.i_g, silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars))); } })); } };
		// r.compiledGrammars = g.compiledGrammars
		silver.driver.util.Pcompilation.childInheritedAttributes[silver.driver.util.Pcompilation.i_r][silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.driver.util.Pcompilation.i_g).inherited(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_Grammars)); } };
		// grammarsDependedUpon = expandAllDeps([ buildGrammar ], [], g.compiledGrammars)
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.grammarsDependedUpon__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PexpandAllDeps.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.driver.util.Pcompilation.i_buildGrammar), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.driver.util.Pcompilation.i_g).inherited(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_Grammars)); } })); } };
		// grammarsRelevant = keepGrammars(grammarsDependedUpon, g.grammarList)
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.grammarsRelevant__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PkeepGrammars.invoke(context.localAsIsLazy(silver.driver.util.Init.grammarsDependedUpon__ON__silver_driver_util_compilation), context.childDecoratedSynthesizedLazy(silver.driver.util.Pcompilation.i_g, silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars))); } };
		// grammarsToTranslate = keepGrammars(grammarsDependedUpon, g.translateGrammars)
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.grammarsToTranslate__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PkeepGrammars.invoke(context.localAsIsLazy(silver.driver.util.Init.grammarsDependedUpon__ON__silver_driver_util_compilation), context.childDecoratedSynthesizedLazy(silver.driver.util.Pcompilation.i_g, silver.driver.util.Init.silver_driver_util_translateGrammars__ON__silver_driver_util_Grammars))); } };
		// top.allGrammars = g.grammarList ++ r.grammarList
		silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_allGrammars__ON__silver_driver_util_Compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.driver.util.Pcompilation.i_g, silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars), context.childDecoratedSynthesizedLazy(silver.driver.util.Pcompilation.i_r, silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars))); } };
		// top.postOps := []
		if(silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] == null)
			silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] = new silver.driver.util.CApostOps(silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation);
		((common.CollectionAttribute)silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<Pcompilation> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pcompilation> {

		@Override
		public Pcompilation invoke(final Object[] children, final Object[] annotations) {
			return new Pcompilation(children[0], children[1], children[2], children[3]);
		}
	};

}
