
package silver.extension.bidirtransform;

// prd::RewriteProduction ::= qn::QName '(' args::RewriteProductionArgs ')' 
public final class PrewriteProduction extends silver.extension.bidirtransform.NRewriteProduction {

	public static final int i_qn = 0;
	public static final int i__G_2 = 1;
	public static final int i_args = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TLParen_t.class,silver.extension.bidirtransform.NRewriteProductionArgs.class,silver.definition.core.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_rewriteProduction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteProduction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteProduction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_args] = new common.Lazy[silver.extension.bidirtransform.NRewriteProductionArgs.num_inh_attrs];

	}

	public PrewriteProduction(final Object c_qn, final Object c__G_2, final Object c_args, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_qn = c_qn;
		this.child__G_2 = c__G_2;
		this.child_args = c_args;
		this.child__G_0 = c__G_0;

	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child__G_2;
	public final silver.definition.core.TLParen_t getChild__G_2() {
		return (silver.definition.core.TLParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_args;
	public final silver.extension.bidirtransform.NRewriteProductionArgs getChild_args() {
		return (silver.extension.bidirtransform.NRewriteProductionArgs) (child_args = common.Util.demand(child_args));
	}

	private Object child__G_0;
	public final silver.definition.core.TRParen_t getChild__G_0() {
		return (silver.definition.core.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();
			case i__G_2: return getChild__G_2();
			case i_args: return getChild_args();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;
			case i__G_2: return child__G_2;
			case i_args: return child_args;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:rewriteProduction erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:rewriteProduction";
	}

	static void initProductionAttributeDefinitions() {
		// prd.pp = qn.pp ++ "(" ++ args.pp ++ ")"
		silver.extension.bidirtransform.PrewriteProduction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteProduction.i_qn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteProduction.i_args).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteProductionArgs)), (common.StringCatter)(new common.StringCatter(")"))))); } };
		// prd.inputNames = args.inputNames
		silver.extension.bidirtransform.PrewriteProduction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_inputNames__ON__silver_extension_bidirtransform_RewriteProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PrewriteProduction.i_args).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_inputNames__ON__silver_extension_bidirtransform_RewriteProductionArgs)); } };
		// prd.name = qn.name
		silver.extension.bidirtransform.PrewriteProduction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_RewriteProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteProduction.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)); } };
		// prd.errors := args.errors
		if(silver.extension.bidirtransform.PrewriteProduction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProduction] == null)
			silver.extension.bidirtransform.PrewriteProduction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProduction] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProduction);
		((common.CollectionAttribute)silver.extension.bidirtransform.PrewriteProduction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProduction]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PrewriteProduction.i_args).synthesized(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProductionArgs)); } });
		// absSig = getProdFromGroup(qn.name, prd.absGroup)
		silver.extension.bidirtransform.PrewriteProduction.localAttributes[silver.extension.bidirtransform.Init.absSig__ON__silver_extension_bidirtransform_rewriteProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PgetProdFromGroup.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteProduction.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_RewriteProduction))); } };
		// cncSig = getProdFromGroup(qn.name, prd.cncGroup)
		silver.extension.bidirtransform.PrewriteProduction.localAttributes[silver.extension.bidirtransform.Init.cncSig__ON__silver_extension_bidirtransform_rewriteProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PgetProdFromGroup.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteProduction.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_RewriteProduction))); } };
		// prd.decSig = if length(absSig) != 0 then head(absSig) else head(cncSig)
		silver.extension.bidirtransform.PrewriteProduction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_decSig__ON__silver_extension_bidirtransform_RewriteProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((Integer)core.PlistLength.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.absSig__ON__silver_extension_bidirtransform_rewriteProduction))).equals(Integer.valueOf((int)0)) ? ((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.absSig__ON__silver_extension_bidirtransform_rewriteProduction))) : ((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.cncSig__ON__silver_extension_bidirtransform_rewriteProduction)))); } };
		// prd.typerep = prd.decSig.typerep
		silver.extension.bidirtransform.PrewriteProduction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_typerep__ON__silver_extension_bidirtransform_RewriteProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_decSig__ON__silver_extension_bidirtransform_RewriteProduction)).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignature)); } };

	}

	public static final common.NodeFactory<PrewriteProduction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrewriteProduction> {

		@Override
		public PrewriteProduction invoke(final Object[] children, final Object[] annotations) {
			return new PrewriteProduction(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
