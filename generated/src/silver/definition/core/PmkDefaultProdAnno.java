
package silver.definition.core;

// top::ProductionStmt ::= qn::QName aexpr::AppExpr 
public final class PmkDefaultProdAnno extends silver.definition.core.NProductionStmt {

	public static final int i_qn = 0;
	public static final int i_aexpr = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.NAppExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_mkDefaultProdAnno;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_aexpr] = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	}

	public PmkDefaultProdAnno(final Object c_qn, final Object c_aexpr, final Object a_core_location) {
		super(a_core_location);
		this.child_qn = c_qn;
		this.child_aexpr = c_aexpr;

	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child_aexpr;
	public final silver.definition.core.NAppExpr getChild_aexpr() {
		return (silver.definition.core.NAppExpr) (child_aexpr = common.Util.demand(child_aexpr));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();
			case i_aexpr: return getChild_aexpr();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;
			case i_aexpr: return child_aexpr;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:mkDefaultProdAnno erroneously claimed to forward");
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
		return "silver:definition:core:mkDefaultProdAnno";
	}

	static void initProductionAttributeDefinitions() {
		// top.defaultSuppliedAnnos := []
		if(silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_defaultSuppliedAnnos__ON__silver_definition_core_ProductionStmt] == null)
			silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_defaultSuppliedAnnos__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAdefaultSuppliedAnnos(silver.definition.core.Init.silver_definition_core_defaultSuppliedAnnos__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_defaultSuppliedAnnos__ON__silver_definition_core_ProductionStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.defaultSuppliedAnnos <- [ pair(qn.name, aexpr) ]
		if(silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_defaultSuppliedAnnos__ON__silver_definition_core_ProductionStmt] == null)
			silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_defaultSuppliedAnnos__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAdefaultSuppliedAnnos(silver.definition.core.Init.silver_definition_core_defaultSuppliedAnnos__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_defaultSuppliedAnnos__ON__silver_definition_core_ProductionStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.definition.core.PmkDefaultProdAnno.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PmkDefaultProdAnno.i_aexpr)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// top.flowDefs = []
		silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.errors := aexpr.errors
		if(silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PmkDefaultProdAnno.i_aexpr).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr)); } });
		// top.pp = "\tdefault annotation" ++ qn.name ++ aexpr.pp ++ ";"
		silver.definition.core.PmkDefaultProdAnno.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\tdefault annotation")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmkDefaultProdAnno.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmkDefaultProdAnno.i_aexpr).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AppExpr)), (common.StringCatter)(new common.StringCatter(";"))))); } };
		// aexpr.downSubst = emptySubst()
		silver.definition.core.PmkDefaultProdAnno.childInheritedAttributes[silver.definition.core.PmkDefaultProdAnno.i_aexpr][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke()); } };
		// annoAttrs = getAttrDcl(qn.name, top.env)
		silver.definition.core.PmkDefaultProdAnno.localAttributes[silver.definition.core.Init.annoAttrs__ON__silver_definition_core_mkDefaultProdAnno] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetAttrDcl.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmkDefaultProdAnno.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionStmt))); } };
		// aexpr.appExprTyperep = if null(annoAttrs) then errorType() else if head(annoAttrs).isAnnotation then head(annoAttrs).typerep else errorType()
		silver.definition.core.PmkDefaultProdAnno.childInheritedAttributes[silver.definition.core.PmkDefaultProdAnno.i_aexpr][silver.definition.core.Init.silver_definition_core_appExprTyperep__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.core.Init.annoAttrs__ON__silver_definition_core_mkDefaultProdAnno))) ? ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()) : (((Boolean)((silver.definition.env.NDclInfo)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.annoAttrs__ON__silver_definition_core_mkDefaultProdAnno))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_isAnnotation__ON__silver_definition_env_DclInfo)) ? ((silver.definition.type.NType)((silver.definition.env.NDclInfo)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.annoAttrs__ON__silver_definition_core_mkDefaultProdAnno))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo)) : ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()))); } };

	}

	public static final common.NodeFactory<PmkDefaultProdAnno> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmkDefaultProdAnno> {

		@Override
		public PmkDefaultProdAnno invoke(final Object[] children, final Object[] annotations) {
			return new PmkDefaultProdAnno(children[0], children[1], annotations[0]);
		}
	};

}
