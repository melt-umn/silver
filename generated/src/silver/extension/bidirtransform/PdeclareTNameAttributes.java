
package silver.extension.bidirtransform;

// top::AGDcl ::= tdcl::Decorated TransformDcl absNames::[String] cncNames::[String] 
public final class PdeclareTNameAttributes extends silver.definition.core.NAGDcl {

	public static final int i_tdcl = 0;
	public static final int i_absNames = 1;
	public static final int i_cncNames = 2;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_declareTNameAttributes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PdeclareTNameAttributes(final Object c_tdcl, final Object c_absNames, final Object c_cncNames, final Object a_core_location) {
		super(a_core_location);
		this.child_tdcl = c_tdcl;
		this.child_absNames = c_absNames;
		this.child_cncNames = c_cncNames;

	}

	private Object child_tdcl;
	public final common.DecoratedNode getChild_tdcl() {
		return (common.DecoratedNode) (child_tdcl = common.Util.demand(child_tdcl));
	}

	private Object child_absNames;
	public final common.ConsCell getChild_absNames() {
		return (common.ConsCell) (child_absNames = common.Util.demand(child_absNames));
	}

	private Object child_cncNames;
	public final common.ConsCell getChild_cncNames() {
		return (common.ConsCell) (child_cncNames = common.Util.demand(child_cncNames));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tdcl: return getChild_tdcl();
			case i_absNames: return getChild_absNames();
			case i_cncNames: return getChild_cncNames();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tdcl: return child_tdcl;
			case i_absNames: return child_absNames;
			case i_cncNames: return child_cncNames;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAGDcl)core.Pfoldl.invoke((new common.NodeFactory<silver.definition.core.NAGDcl>() {
  public final silver.definition.core.NAGDcl invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13006_agDcls = args[0];
final Object __SV_LAMBDA_PARAM_13007_tr = args[1];

    return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PdeclareTRuleAttributes(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13007_tr)), context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdeclareTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_TransformDcl), context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdeclareTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transType__ON__silver_extension_bidirtransform_TransformDcl), context.childAsIsLazy(silver.extension.bidirtransform.PdeclareTNameAttributes.i_absNames), context.childAsIsLazy(silver.extension.bidirtransform.PdeclareTNameAttributes.i_cncNames), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, ((silver.definition.core.NAGDcl)common.Util.demand(__SV_LAMBDA_PARAM_13006_agDcls)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_declareTNameAttributes)), context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdeclareTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformRules__ON__silver_extension_bidirtransform_TransformDcl)));
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
		return "silver:extension:bidirtransform:declareTNameAttributes";
	}

	static void initProductionAttributeDefinitions() {
		// agDcls1 = synAttr(tdcl.name, tdcl.transType,location=top.location)
		silver.extension.bidirtransform.PdeclareTNameAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_declareTNameAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PsynAttr(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdeclareTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_TransformDcl), context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdeclareTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transType__ON__silver_extension_bidirtransform_TransformDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } };
		// agDcls2 = appendAGDcl(attrOn(tdcl.name, absNames,location=top.location), agDcls1,location=top.location)
		silver.extension.bidirtransform.PdeclareTNameAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_declareTNameAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PattrOn(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdeclareTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_TransformDcl), context.childAsIsLazy(silver.extension.bidirtransform.PdeclareTNameAttributes.i_absNames), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_declareTNameAttributes)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } };
		// agDcls2.compiledGrammars = top.compiledGrammars
		silver.extension.bidirtransform.PdeclareTNameAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_declareTNameAttributes][silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl)); } };
		// agDcls2.grammarName = top.grammarName
		silver.extension.bidirtransform.PdeclareTNameAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_declareTNameAttributes][silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)); } };
		// agDcls2.flowEnv = top.flowEnv
		silver.extension.bidirtransform.PdeclareTNameAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_declareTNameAttributes][silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcl)); } };

	}

	public static final common.NodeFactory<PdeclareTNameAttributes> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdeclareTNameAttributes> {

		@Override
		public PdeclareTNameAttributes invoke(final Object[] children, final Object[] annotations) {
			return new PdeclareTNameAttributes(children[0], children[1], children[2], annotations[0]);
		}
	};

}
