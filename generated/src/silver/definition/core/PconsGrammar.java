
package silver.definition.core;

// top::Grammar ::= h::Root t::Grammar 
public final class PconsGrammar extends silver.definition.core.NGrammar {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NRoot.class,silver.definition.core.NGrammar.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_consGrammar;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NGrammar.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NGrammar.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.core.NRoot.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.core.NGrammar.num_inh_attrs];

	}

	public PconsGrammar(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.core.NRoot getChild_h() {
		return (silver.definition.core.NRoot) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.core.NGrammar getChild_t() {
		return (silver.definition.core.NGrammar) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:consGrammar erroneously claimed to forward");
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
		return "silver:definition:core:consGrammar";
	}

	static void initProductionAttributeDefinitions() {
		// top.declaredName = if h.declaredName == t.declaredName then h.declaredName else top.grammarName
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_declaredName__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)context.childDecorated(silver.definition.core.PconsGrammar.i_h).synthesized(silver.definition.core.Init.silver_definition_env_declaredName__ON__silver_definition_core_Root)).equals(((common.StringCatter)context.childDecorated(silver.definition.core.PconsGrammar.i_t).synthesized(silver.definition.core.Init.silver_definition_env_declaredName__ON__silver_definition_core_Grammar))) ? ((common.StringCatter)context.childDecorated(silver.definition.core.PconsGrammar.i_h).synthesized(silver.definition.core.Init.silver_definition_env_declaredName__ON__silver_definition_core_Root)) : ((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_Grammar))); } };
		// top.moduleNames = h.moduleNames ++ t.moduleNames
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_Grammar))); } };
		// top.exportedGrammars = h.exportedGrammars ++ t.exportedGrammars
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_Grammar))); } };
		// top.optionalGrammars = h.optionalGrammars ++ t.optionalGrammars
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_Grammar))); } };
		// top.condBuild = h.condBuild ++ t.condBuild
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_Grammar))); } };
		// top.importedDefs = h.importedDefs ++ t.importedDefs
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_importedDefs__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.definition.core.Init.silver_definition_core_importedDefs__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.definition.core.Init.silver_definition_core_importedDefs__ON__silver_definition_core_Grammar))); } };
		// top.defs = h.defs ++ t.defs
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Grammar))); } };
		// top.grammarErrors = if null(h.errors) then t.grammarErrors else (pair(h.location.filename, h.errors) :: t.grammarErrors)
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_grammarErrors__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Root))) ? ((common.ConsCell)context.childDecorated(silver.definition.core.PconsGrammar.i_t).synthesized(silver.definition.core.Init.silver_definition_core_grammarErrors__ON__silver_definition_core_Grammar)) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NRoot)context.childDecorated(silver.definition.core.PconsGrammar.i_h).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } }, context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Root))); } }, context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.definition.core.Init.silver_definition_core_grammarErrors__ON__silver_definition_core_Grammar)))); } };

	}

	public static final common.NodeFactory<PconsGrammar> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsGrammar> {

		@Override
		public PconsGrammar invoke(final Object[] children, final Object[] annotations) {
			return new PconsGrammar(children[0], children[1]);
		}
	};

}
