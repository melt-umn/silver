
package silver.extension.bidirtransform;

// top::FullNonterminal ::= qn::QName 
public final class PfullNt extends silver.extension.bidirtransform.NFullNonterminal {

	public static final int i_qn = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_fullNt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NFullNonterminal.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NFullNonterminal.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PfullNt(final Object c_qn, final Object a_core_location) {
		super(a_core_location);
		this.child_qn = c_qn;

	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:fullNt erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:fullNt";
	}

	static void initProductionAttributeDefinitions() {
		// top.name = qn.name
		silver.extension.bidirtransform.PfullNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_FullNonterminal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfullNt.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)); } };
		// top.ntProds = filterSigs(qn.name, prodsFromDefs(top.grantedDefs))
		silver.extension.bidirtransform.PfullNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ntProds__ON__silver_extension_bidirtransform_FullNonterminal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PfilterSigs.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PfullNt.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PprodsFromDefs.invoke(context.contextInheritedLazy(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_grantedDefs__ON__silver_extension_bidirtransform_FullNonterminal))); } })); } };
		// top.errors := if length(getTypeDcl(top.name, top.env)) != 0 then [] else [ err(top.location, "Name " ++ top.name ++ " doesn't match any known nonterminal") ]
		if(silver.extension.bidirtransform.PfullNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_FullNonterminal] == null)
			silver.extension.bidirtransform.PfullNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_FullNonterminal] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_FullNonterminal);
		((common.CollectionAttribute)silver.extension.bidirtransform.PfullNt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_FullNonterminal]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetTypeDcl.invoke(context.contextSynthesizedLazy(silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_FullNonterminal), context.contextInheritedLazy(silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_FullNonterminal))); } })).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.bidirtransform.NFullNonterminal)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Name ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.synthesized(silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_FullNonterminal)), (common.StringCatter)(new common.StringCatter(" doesn't match any known nonterminal")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });

	}

	public static final common.NodeFactory<PfullNt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfullNt> {

		@Override
		public PfullNt invoke(final Object[] children, final Object[] annotations) {
			return new PfullNt(children[0], annotations[0]);
		}
	};

}
