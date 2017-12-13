
package silver.extension.bidirtransform;

// top::AGDcl ::= qns::QNameList pfix::String 
public final class PwriteOptAttributes extends silver.definition.core.NAGDcl {

	public static final int i_qns = 0;
	public static final int i_pfix = 1;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.NQNameList.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_writeOptAttributes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qns] = new common.Lazy[silver.extension.bidirtransform.NQNameList.num_inh_attrs];

	}

	public PwriteOptAttributes(final Object c_qns, final Object c_pfix, final Object a_core_location) {
		super(a_core_location);
		this.child_qns = c_qns;
		this.child_pfix = c_pfix;

	}

	private Object child_qns;
	public final silver.extension.bidirtransform.NQNameList getChild_qns() {
		return (silver.extension.bidirtransform.NQNameList) (child_qns = common.Util.demand(child_qns));
	}

	private Object child_pfix;
	public final common.StringCatter getChild_pfix() {
		return (common.StringCatter) (child_pfix = common.Util.demand(child_pfix));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qns: return getChild_qns();
			case i_pfix: return getChild_pfix();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qns: return child_qns;
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
    final Object __SV_LAMBDA_PARAM_12641_agDcls = args[0];
final Object __SV_LAMBDA_PARAM_12642_attr = args[1];

    return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PsynAttr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NPair)common.Util.demand(__SV_LAMBDA_PARAM_12642_attr)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)((core.NPair)common.Util.demand(__SV_LAMBDA_PARAM_12642_attr)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, ((silver.definition.core.NAGDcl)common.Util.demand(__SV_LAMBDA_PARAM_12641_agDcls)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke(core.Ppair.factory, context.localAsIsLazy(silver.extension.bidirtransform.Init.prefixedNames__ON__silver_extension_bidirtransform_writeOptAttributes), context.localAsIsLazy(silver.extension.bidirtransform.Init.tyExprs__ON__silver_extension_bidirtransform_writeOptAttributes))); } }));
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
		return "silver:extension:bidirtransform:writeOptAttributes";
	}

	static void initProductionAttributeDefinitions() {
		// prefixedNames = map(\ qn::QName  -> pfix ++ qn.name, qns.qList)
		silver.extension.bidirtransform.PwriteOptAttributes.localAttributes[silver.extension.bidirtransform.Init.prefixedNames__ON__silver_extension_bidirtransform_writeOptAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke((new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_12619_qn = args[0];

    return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PwriteOptAttributes.i_pfix)), (common.StringCatter)((common.StringCatter)((silver.definition.core.NQName)common.Util.demand(__SV_LAMBDA_PARAM_12619_qn)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)));
  }
}), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PwriteOptAttributes.i_qns, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_qList__ON__silver_extension_bidirtransform_QNameList))); } };
		// tyExprs = map(\ qn::QName  -> mkMaybeTypeExpr(head(getAttrDcl(qn.name, top.env)).typerep.typeName), qns.qList)
		silver.extension.bidirtransform.PwriteOptAttributes.localAttributes[silver.extension.bidirtransform.Init.tyExprs__ON__silver_extension_bidirtransform_writeOptAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke((new common.NodeFactory<silver.definition.type.syntax.NTypeExpr>() {
  public final silver.definition.type.syntax.NTypeExpr invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_12623_qn = args[0];

    return ((silver.definition.type.syntax.NTypeExpr)new silver.extension.bidirtransform.PmkMaybeTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NDclInfo)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetAttrDcl.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.NQName)common.Util.demand(__SV_LAMBDA_PARAM_12623_qn)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PwriteOptAttributes.i_qns, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_qList__ON__silver_extension_bidirtransform_QNameList))); } };
		// top.errors <- foldl(\ errs::[Message] qn::QName  -> if null(getAttrDcl(qn.name, top.env)) then [ err(qn.location, "Unknown attribute " ++ qn.name) ] ++ errs else errs, [], qns.qList)
		if(silver.extension.bidirtransform.PwriteOptAttributes.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.extension.bidirtransform.PwriteOptAttributes.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.bidirtransform.PwriteOptAttributes.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfoldl.invoke((new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_12630_errs = args[0];
final Object __SV_LAMBDA_PARAM_12631_qn = args[1];

    return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetAttrDcl.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.NQName)common.Util.demand(__SV_LAMBDA_PARAM_12631_qn)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })) ? ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)common.Util.demand(__SV_LAMBDA_PARAM_12631_qn)).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Unknown attribute ")), (common.StringCatter)((common.StringCatter)((silver.definition.core.NQName)common.Util.demand(__SV_LAMBDA_PARAM_12631_qn)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, ((common.ConsCell)common.Util.demand(__SV_LAMBDA_PARAM_12630_errs)))) : ((common.ConsCell)common.Util.demand(__SV_LAMBDA_PARAM_12630_errs)));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PwriteOptAttributes.i_qns, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_qList__ON__silver_extension_bidirtransform_QNameList))); } });


	}

	public static final common.NodeFactory<PwriteOptAttributes> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PwriteOptAttributes> {

		@Override
		public PwriteOptAttributes invoke(final Object[] children, final Object[] annotations) {
			return new PwriteOptAttributes(children[0], children[1], annotations[0]);
		}
	};

}
