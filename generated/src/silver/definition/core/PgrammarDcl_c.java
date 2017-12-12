
package silver.definition.core;

// top::GrammarDcl ::= 'grammar' qn::QName ';' 
public final class PgrammarDcl_c extends silver.definition.core.NGrammarDcl {

	public static final int i__G_2 = 0;
	public static final int i_qn = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.TGrammar_kwd.class,silver.definition.core.NQName.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_grammarDcl_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NGrammarDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NGrammarDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PgrammarDcl_c(final Object c__G_2, final Object c_qn, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_qn = c_qn;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.definition.core.TGrammar_kwd getChild__G_2() {
		return (silver.definition.core.TGrammar_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_qn: return getChild_qn();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_qn: return child_qn;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:grammarDcl_c erroneously claimed to forward");
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
		return "silver:definition:core:grammarDcl_c";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "grammar " ++ qn.pp ++ ";"
		silver.definition.core.PgrammarDcl_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_GrammarDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("grammar ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PgrammarDcl_c.i_qn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter(";")))); } };
		// top.declaredName = qn.name
		silver.definition.core.PgrammarDcl_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_declaredName__ON__silver_definition_core_GrammarDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PgrammarDcl_c.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)); } };
		// top.errors := if qn.name == top.grammarName then [] else [ err(top.location, "Grammar declaration is incorrect: " ++ qn.name) ]
		if(silver.definition.core.PgrammarDcl_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_GrammarDcl] == null)
			silver.definition.core.PgrammarDcl_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_GrammarDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_GrammarDcl);
		((common.CollectionAttribute)silver.definition.core.PgrammarDcl_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_GrammarDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)context.childDecorated(silver.definition.core.PgrammarDcl_c.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)).equals(((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_GrammarDcl))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NGrammarDcl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Grammar declaration is incorrect: ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PgrammarDcl_c.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });

	}

	public static final common.NodeFactory<PgrammarDcl_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgrammarDcl_c> {

		@Override
		public PgrammarDcl_c invoke(final Object[] children, final Object[] annotations) {
			return new PgrammarDcl_c(children[0], children[1], children[2], annotations[0]);
		}
	};

}
