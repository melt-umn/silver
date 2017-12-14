
package silver.extension.bidirtransform;

// top::AGDcl ::= tr::Decorated TransformRule nm::String typeName::String absNames::[String] allNames::[String] absProdDcls::[Decorated NamedSignature] 
public final class PdefineTRuleAttributes extends silver.definition.core.NAGDcl {

	public static final int i_tr = 0;
	public static final int i_nm = 1;
	public static final int i_typeName = 2;
	public static final int i_absNames = 3;
	public static final int i_allNames = 4;
	public static final int i_absProdDcls = 5;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_defineTRuleAttributes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PdefineTRuleAttributes(final Object c_tr, final Object c_nm, final Object c_typeName, final Object c_absNames, final Object c_allNames, final Object c_absProdDcls, final Object a_core_location) {
		super(a_core_location);
		this.child_tr = c_tr;
		this.child_nm = c_nm;
		this.child_typeName = c_typeName;
		this.child_absNames = c_absNames;
		this.child_allNames = c_allNames;
		this.child_absProdDcls = c_absProdDcls;

	}

	private Object child_tr;
	public final common.DecoratedNode getChild_tr() {
		return (common.DecoratedNode) (child_tr = common.Util.demand(child_tr));
	}

	private Object child_nm;
	public final common.StringCatter getChild_nm() {
		return (common.StringCatter) (child_nm = common.Util.demand(child_nm));
	}

	private Object child_typeName;
	public final common.StringCatter getChild_typeName() {
		return (common.StringCatter) (child_typeName = common.Util.demand(child_typeName));
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
			case i_tr: return getChild_tr();
			case i_nm: return getChild_nm();
			case i_typeName: return getChild_typeName();
			case i_absNames: return getChild_absNames();
			case i_allNames: return getChild_allNames();
			case i_absProdDcls: return getChild_absProdDcls();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tr: return child_tr;
			case i_nm: return child_nm;
			case i_typeName: return child_typeName;
			case i_absNames: return child_absNames;
			case i_allNames: return child_allNames;
			case i_absProdDcls: return child_absProdDcls;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return context.localDecorated(silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_defineTRuleAttributes).undecorate();
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
		return "silver:extension:bidirtransform:defineTRuleAttributes";
	}

	static void initProductionAttributeDefinitions() {
		// tName = nm ++ toString(tr.trIndex)
		silver.extension.bidirtransform.PdefineTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_defineTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PdefineTRuleAttributes.i_nm)), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PdefineTRuleAttributes.i_tr)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_trIndex__ON__silver_extension_bidirtransform_TransformRule))))); } };
		// inhRedexName = inhRedexNm(tName)
		silver.extension.bidirtransform.PdefineTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.inhRedexName__ON__silver_extension_bidirtransform_defineTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PinhRedexNm.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_defineTRuleAttributes))); } };
		// transformName = transformNm(tName)
		silver.extension.bidirtransform.PdefineTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.transformName__ON__silver_extension_bidirtransform_defineTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PtransformNm.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_defineTRuleAttributes))); } };
		// absProdNames = map(unFull, map((.fullName), absProdDcls))
		silver.extension.bidirtransform.PdefineTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.absProdNames__ON__silver_extension_bidirtransform_defineTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.extension.bidirtransform.PunFull.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature), context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_absProdDcls))); } })); } };
		// agDcls1 = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature  -> appendAGDcl(aspectProdStmts(dcl, \ ns::Decorated NamedSignature  -> if ! hasTrans([ tr ], dcl) && ns.outputElement.typerep.typeName != typeName then productionStmtsNil(location=top.location) else prdStmtList([ attribDef(ns.outputElement.elementName, tName, if ! hasTrans([ tr ], dcl) then prdRecurse(ns, tName, absNames,location=top.location) else mkCond(lhsExprAccess(transformName, ns,location=top.location), injectAnnos(applyTrans([ tr ], dcl,location=top.location), annoAppExprList([ annExpr("labels", emptyList('[', ']',location=top.location),location=top.location), annExpr("redex", exprAccess(inhRedexName, inhRedexNameSig(ns, allNames),location=top.location),location=top.location), annExpr("origin", mkOrigin(ns,location=top.location),location=top.location) ],location=top.location), absProdNames,location=top.location), prdRecurse(ns, tName, absNames,location=top.location),location=top.location),location=top.location) ],location=top.location),location=top.location), agDcls,location=top.location), emptyAGDcl(location=top.location), absProdDcls)
		silver.extension.bidirtransform.PdefineTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_defineTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)core.Pfoldl.invoke((new common.NodeFactory<silver.definition.core.NAGDcl>() {
  public final silver.definition.core.NAGDcl invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13047_agDcls = args[0];
final Object __SV_LAMBDA_PARAM_13048_dcl = args[1];

    return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PaspectProdStmts(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13048_dcl)), (new common.NodeFactory<silver.definition.core.NProductionStmts>() {
  public final silver.definition.core.NProductionStmts invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13049_ns = args[0];

    return (((!((Boolean)silver.extension.bidirtransform.PhasTrans.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_tr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13048_dcl))))) && !((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13049_ns)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)).equals(((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PdefineTRuleAttributes.i_typeName)))) ? ((silver.definition.core.NProductionStmts)new silver.definition.core.PproductionStmtsNil(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NProductionStmts)new silver.extension.bidirtransform.PprdStmtList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)new silver.extension.bidirtransform.PattribDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13049_ns)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } }, context.localAsIsLazy(silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_defineTRuleAttributes), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((!((Boolean)silver.extension.bidirtransform.PhasTrans.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_tr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13048_dcl))))) ? ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PprdRecurse(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13049_ns)), context.localAsIsLazy(silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_defineTRuleAttributes), context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_absNames), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PmkCond(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PlhsExprAccess(context.localAsIsLazy(silver.extension.bidirtransform.Init.transformName__ON__silver_extension_bidirtransform_defineTRuleAttributes), ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13049_ns)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PinjectAnnos(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PapplyTrans(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_tr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13048_dcl)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.extension.bidirtransform.PannoAppExprList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.extension.bidirtransform.PannExpr((new common.StringCatter("labels")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.list.PemptyList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TLSqr_t((new common.StringCatter("[")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TRSqr_t((new common.StringCatter("]")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.extension.bidirtransform.PannExpr((new common.StringCatter("redex")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PexprAccess(context.localAsIsLazy(silver.extension.bidirtransform.Init.inhRedexName__ON__silver_extension_bidirtransform_defineTRuleAttributes), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PinhRedexNameSig.invoke(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13049_ns)), context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_allNames))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.extension.bidirtransform.PannExpr((new common.StringCatter("origin")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PmkOrigin(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13049_ns)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, context.localAsIsLazy(silver.extension.bidirtransform.Init.absProdNames__ON__silver_extension_bidirtransform_defineTRuleAttributes), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PprdRecurse(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13049_ns)), context.localAsIsLazy(silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_defineTRuleAttributes), context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_absNames), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, ((silver.definition.core.NAGDcl)common.Util.demand(__SV_LAMBDA_PARAM_13047_agDcls)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_absProdDcls))); } };
		// agDcls2 = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature  -> if ! hasTrans([ tr ], dcl) then agDcls else appendAGDcl(aspectProdStmts(dcl, \ ns::Decorated NamedSignature  -> prdStmtList([ attribDef(ns.outputElement.elementName, transformName, getTrans([ tr ], dcl).matchProd,location=top.location) ],location=top.location),location=top.location), agDcls,location=top.location), agDcls1, absProdDcls)
		silver.extension.bidirtransform.PdefineTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_defineTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)core.Pfoldl.invoke((new common.NodeFactory<silver.definition.core.NAGDcl>() {
  public final silver.definition.core.NAGDcl invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13083_agDcls = args[0];
final Object __SV_LAMBDA_PARAM_13084_dcl = args[1];

    return ((!((Boolean)silver.extension.bidirtransform.PhasTrans.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_tr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13084_dcl))))) ? ((silver.definition.core.NAGDcl)common.Util.demand(__SV_LAMBDA_PARAM_13083_agDcls)) : ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PaspectProdStmts(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13084_dcl)), (new common.NodeFactory<silver.definition.core.NProductionStmts>() {
  public final silver.definition.core.NProductionStmts invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13085_ns = args[0];

    return ((silver.definition.core.NProductionStmts)new silver.extension.bidirtransform.PprdStmtList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)new silver.extension.bidirtransform.PattribDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13085_ns)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } }, context.localAsIsLazy(silver.extension.bidirtransform.Init.transformName__ON__silver_extension_bidirtransform_defineTRuleAttributes), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)((common.DecoratedNode)silver.extension.bidirtransform.PgetTrans.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_tr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13084_dcl)))).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_matchProd__ON__silver_extension_bidirtransform_TransformRule)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, ((silver.definition.core.NAGDcl)common.Util.demand(__SV_LAMBDA_PARAM_13083_agDcls)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })));
  }
}), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_defineTRuleAttributes)), context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_absProdDcls))); } };
		// agDcls3 = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature  -> appendAGDcl(aspectProdStmts(dcl, \ ns::Decorated NamedSignature  -> foldl(\ stmts::ProductionStmts rhs::NamedSignatureElement  -> if ! contains(unFull(rhs.typerep.typeName), allNames) then stmts else productionStmtsSnoc(stmts, attribDef(rhs.elementName, inhRedexName, if ! hasTrans([ tr ], dcl) then emptyFunc("nothing",location=top.location) else mkCond(lhsExprAccess(transformName, ns,location=top.location), argFunc("just", oneApp(mkOrigin(ns,location=top.location),location=top.location),location=top.location), emptyFunc("nothing",location=top.location),location=top.location),location=top.location),location=top.location), productionStmtsNil(location=top.location), ns.inputElements),location=top.location), agDcls,location=top.location), agDcls2, absProdDcls)
		silver.extension.bidirtransform.PdefineTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_defineTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)core.Pfoldl.invoke((new common.NodeFactory<silver.definition.core.NAGDcl>() {
  public final silver.definition.core.NAGDcl invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13096_agDcls = args[0];
final Object __SV_LAMBDA_PARAM_13097_dcl = args[1];

    return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PaspectProdStmts(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13097_dcl)), (new common.NodeFactory<silver.definition.core.NProductionStmts>() {
  public final silver.definition.core.NProductionStmts invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13098_ns = args[0];

    return ((silver.definition.core.NProductionStmts)core.Pfoldl.invoke((new common.NodeFactory<silver.definition.core.NProductionStmts>() {
  public final silver.definition.core.NProductionStmts invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13099_stmts = args[0];
final Object __SV_LAMBDA_PARAM_13100_rhs = args[1];

    return ((!((Boolean)silver.util.Pcontains.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)common.Util.demand(__SV_LAMBDA_PARAM_13100_rhs)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } })); } }, context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_allNames)))) ? ((silver.definition.core.NProductionStmts)common.Util.demand(__SV_LAMBDA_PARAM_13099_stmts)) : ((silver.definition.core.NProductionStmts)new silver.definition.core.PproductionStmtsSnoc(((silver.definition.core.NProductionStmts)common.Util.demand(__SV_LAMBDA_PARAM_13099_stmts)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)new silver.extension.bidirtransform.PattribDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)common.Util.demand(__SV_LAMBDA_PARAM_13100_rhs)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } }, context.localAsIsLazy(silver.extension.bidirtransform.Init.inhRedexName__ON__silver_extension_bidirtransform_defineTRuleAttributes), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((!((Boolean)silver.extension.bidirtransform.PhasTrans.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_tr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13097_dcl))))) ? ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PemptyFunc((new common.StringCatter("nothing")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PmkCond(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PlhsExprAccess(context.localAsIsLazy(silver.extension.bidirtransform.Init.transformName__ON__silver_extension_bidirtransform_defineTRuleAttributes), ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13098_ns)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PargFunc((new common.StringCatter("just")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.extension.bidirtransform.PoneApp(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PmkOrigin(((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13098_ns)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PemptyFunc((new common.StringCatter("nothing")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmts)new silver.definition.core.PproductionStmtsNil(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_13098_ns)).synthesized(silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)); } }));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, ((silver.definition.core.NAGDcl)common.Util.demand(__SV_LAMBDA_PARAM_13096_agDcls)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
  }
}), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_defineTRuleAttributes)), context.childAsIsLazy(silver.extension.bidirtransform.PdefineTRuleAttributes.i_absProdDcls))); } };
		// agDcls3.compiledGrammars = top.compiledGrammars
		silver.extension.bidirtransform.PdefineTRuleAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_defineTRuleAttributes][silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl)); } };
		// agDcls3.grammarName = top.grammarName
		silver.extension.bidirtransform.PdefineTRuleAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_defineTRuleAttributes][silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)); } };
		// agDcls3.flowEnv = top.flowEnv
		silver.extension.bidirtransform.PdefineTRuleAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_defineTRuleAttributes][silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcl)); } };

	}

	public static final common.NodeFactory<PdefineTRuleAttributes> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdefineTRuleAttributes> {

		@Override
		public PdefineTRuleAttributes invoke(final Object[] children, final Object[] annotations) {
			return new PdefineTRuleAttributes(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
