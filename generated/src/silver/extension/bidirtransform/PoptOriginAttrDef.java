
package silver.extension.bidirtransform;

// top::AGDcl ::= qn::QName pfix::String 
public final class PoptOriginAttrDef extends silver.definition.core.NAGDcl {

	public static final int i_qn = 0;
	public static final int i_pfix = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_optOriginAttrDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PoptOriginAttrDef(final Object c_qn, final Object c_pfix, final Object a_core_location) {
		super(a_core_location);
		this.child_qn = c_qn;
		this.child_pfix = c_pfix;

	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child_pfix;
	public final common.StringCatter getChild_pfix() {
		return (common.StringCatter) (child_pfix = common.Util.demand(child_pfix));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();
			case i_pfix: return getChild_pfix();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;
			case i_pfix: return child_pfix;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAGDcl)core.Pfoldl.invoke((new common.NodeFactory<silver.definition.core.NAGDcl>() {
  public final silver.definition.core.NAGDcl invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_12676_agDcls = args[0];
final Object __SV_LAMBDA_PARAM_12677_ns = args[1];

    return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_12677_ns)).synthesized(silver.definition.env.Init.silver_definition_type_inputTypes__ON__silver_definition_env_NamedSignature)); } })) ? ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })) : (((Boolean)silver.extension.bidirtransform.PhasNamedAttr.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_12677_ns)).synthesized(silver.definition.env.Init.silver_definition_type_inputTypes__ON__silver_definition_env_NamedSignature)); } })).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoptOriginAttrDef.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName))) ? ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PaspectProdStmt(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_12677_ns)), (new common.NodeFactory<silver.definition.core.NProductionStmt>() {
  public final silver.definition.core.NProductionStmt invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_12683_ns = args[0];

    return ((silver.definition.core.NProductionStmt)new silver.extension.bidirtransform.PattribDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_12683_ns)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } }, context.localAsIsLazy(silver.extension.bidirtransform.Init.lhsAttr__ON__silver_extension_bidirtransform_optOriginAttrDef), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PoneArgFunc((new common.StringCatter("just")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExpr)new silver.extension.bidirtransform.PnamedAccess(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoptOriginAttrDef.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_12683_ns)).synthesized(silver.definition.env.Init.silver_definition_env_inputNames__ON__silver_definition_env_NamedSignature)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PaspectProdStmt(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_12677_ns)), (new common.NodeFactory<silver.definition.core.NProductionStmt>() {
  public final silver.definition.core.NProductionStmt invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_12690_ns = args[0];

    return ((silver.definition.core.NProductionStmt)new silver.extension.bidirtransform.PattribDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_12690_ns)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } }, context.localAsIsLazy(silver.extension.bidirtransform.Init.lhsAttr__ON__silver_extension_bidirtransform_optOriginAttrDef), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PemptyFunc((new common.StringCatter("nothing")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })))); } }, ((silver.definition.core.NAGDcl)common.Util.demand(__SV_LAMBDA_PARAM_12676_agDcls)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PfilterSigs.invoke((new common.StringCatter("Origin")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PprodsFromDefs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.inherited(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_allDefs__ON__silver_definition_env_Env)); } })); } })); } }));
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
		return "silver:extension:bidirtransform:optOriginAttrDef";
	}

	static void initProductionAttributeDefinitions() {
		// lhsAttr = pfix ++ qn.name
		silver.extension.bidirtransform.PoptOriginAttrDef.localAttributes[silver.extension.bidirtransform.Init.lhsAttr__ON__silver_extension_bidirtransform_optOriginAttrDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PoptOriginAttrDef.i_pfix)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PoptOriginAttrDef.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName))); } };

		// top.defs = []
		silver.extension.bidirtransform.PoptOriginAttrDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PoptOriginAttrDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoptOriginAttrDef> {

		@Override
		public PoptOriginAttrDef invoke(final Object[] children, final Object[] annotations) {
			return new PoptOriginAttrDef(children[0], children[1], annotations[0]);
		}
	};

}
