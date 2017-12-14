
package silver.extension.bidirtransform;

// top::AGDcl ::= tdcl::Decorated TransformDcl absNames::[String] allNames::[String] absProdDcls::[Decorated NamedSignature] 
public final class PdefineTNameAttributes extends silver.definition.core.NAGDcl {

	public static final int i_tdcl = 0;
	public static final int i_absNames = 1;
	public static final int i_allNames = 2;
	public static final int i_absProdDcls = 3;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_defineTNameAttributes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PdefineTNameAttributes(final Object c_tdcl, final Object c_absNames, final Object c_allNames, final Object c_absProdDcls, final Object a_core_location) {
		super(a_core_location);
		this.child_tdcl = c_tdcl;
		this.child_absNames = c_absNames;
		this.child_allNames = c_allNames;
		this.child_absProdDcls = c_absProdDcls;

	}

	private Object child_tdcl;
	public final common.DecoratedNode getChild_tdcl() {
		return (common.DecoratedNode) (child_tdcl = common.Util.demand(child_tdcl));
	}

	private Object child_absNames;
	public final common.ConsCell getChild_absNames() {
		return (common.ConsCell) (child_absNames = common.Util.demand(child_absNames));
	}

	private Object child_allNames;
	public final common.ConsCell getChild_allNames() {
		return (common.ConsCell) (child_allNames = common.Util.demand(child_allNames));
	}

	private Object child_absProdDcls;
	public final common.ConsCell getChild_absProdDcls() {
		return (common.ConsCell) (child_absProdDcls = common.Util.demand(child_absProdDcls));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tdcl: return getChild_tdcl();
			case i_absNames: return getChild_absNames();
			case i_allNames: return getChild_allNames();
			case i_absProdDcls: return getChild_absProdDcls();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tdcl: return child_tdcl;
			case i_absNames: return child_absNames;
			case i_allNames: return child_allNames;
			case i_absProdDcls: return child_absProdDcls;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAGDcl)core.Pfoldl.invoke((new common.NodeFactory<silver.definition.core.NAGDcl>() {
  public final silver.definition.core.NAGDcl invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13043_agDcls = args[0];
final Object __SV_LAMBDA_PARAM_13044_tr = args[1];

    return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PdefineTRuleAttributes(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13044_tr)), context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdefineTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_TransformDcl), context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdefineTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_definition_env_typeName__ON__silver_extension_bidirtransform_TransformDcl), context.childAsIsLazy(silver.extension.bidirtransform.PdefineTNameAttributes.i_absNames), context.childAsIsLazy(silver.extension.bidirtransform.PdefineTNameAttributes.i_allNames), context.childAsIsLazy(silver.extension.bidirtransform.PdefineTNameAttributes.i_absProdDcls), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, ((silver.definition.core.NAGDcl)common.Util.demand(__SV_LAMBDA_PARAM_13043_agDcls)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_defineTNameAttributes)), context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdefineTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformRules__ON__silver_extension_bidirtransform_TransformDcl)));
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
		return "silver:extension:bidirtransform:defineTNameAttributes";
	}

	static void initProductionAttributeDefinitions() {
		// agDcls1 = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature  -> appendAGDcl(aspectProdStmts(dcl, \ ns::Decorated NamedSignature  -> prdStmtList([ attribDef(ns.outputElement.elementName, tdcl.name, transAccess(tdcl.name, tdcl.transformRules, ns.outputElement.elementName,location=top.location),location=top.location) ],location=top.location),location=top.location), agDcls,location=top.location), emptyAGDcl(location=top.location), absProdDcls)
		silver.extension.bidirtransform.PdefineTNameAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_defineTNameAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)core.Pfoldl.invoke((new common.NodeFactory<silver.definition.core.NAGDcl>() {
  public final silver.definition.core.NAGDcl invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13030_agDcls = args[0];
final Object __SV_LAMBDA_PARAM_13031_dcl = args[1];

    return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PaspectProdStmts(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13031_dcl)), (new common.NodeFactory<silver.definition.core.NProductionStmts>() {
  public final silver.definition.core.NProductionStmts invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13032_ns = args[0];

    return ((silver.definition.core.NProductionStmts)new silver.extension.bidirtransform.PprdStmtList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)new silver.extension.bidirtransform.PattribDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13032_ns)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } }, context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdefineTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_TransformDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PtransAccess(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdefineTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_TransformDcl), context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PdefineTNameAttributes.i_tdcl, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformRules__ON__silver_extension_bidirtransform_TransformDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13032_ns)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, ((silver.definition.core.NAGDcl)common.Util.demand(__SV_LAMBDA_PARAM_13030_agDcls)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, context.childAsIsLazy(silver.extension.bidirtransform.PdefineTNameAttributes.i_absProdDcls))); } };
		// agDcls1.compiledGrammars = top.compiledGrammars
		silver.extension.bidirtransform.PdefineTNameAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_defineTNameAttributes][silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl)); } };
		// agDcls1.grammarName = top.grammarName
		silver.extension.bidirtransform.PdefineTNameAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_defineTNameAttributes][silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)); } };
		// agDcls1.flowEnv = top.flowEnv
		silver.extension.bidirtransform.PdefineTNameAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_defineTNameAttributes][silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcl)); } };

	}

	public static final common.NodeFactory<PdefineTNameAttributes> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdefineTNameAttributes> {

		@Override
		public PdefineTNameAttributes invoke(final Object[] children, final Object[] annotations) {
			return new PdefineTNameAttributes(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
