
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax;

// cs::StmtClauses ::= 
public final class PfailureStmtClause extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_failureStmtClause;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PfailureStmtClause(final Object a_core_location) {
		super(a_core_location);

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:abstractsyntax:failureStmtClause erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:abstractsyntax:failureStmtClause";
	}

	static void initProductionAttributeDefinitions() {
		// cs.pp = text("",)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PfailureStmtClause.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_pp__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("")))); } };
		// cs.errors := []
		if(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PfailureStmtClause.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses] == null)
			edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PfailureStmtClause.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses] = new silver.langutil.CAerrors(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses);
		((common.CollectionAttribute)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PfailureStmtClause.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// cs.transform = exprStmt(comment("no match, do nothing.",location=cs.location),)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PfailureStmtClause.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_transform__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PexprStmt(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pcomment((new common.StringCatter("no match, do nothing.")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses)context.undecorate()).getAnno_core_location()); } })); } })); } };

	}

	public static final common.NodeFactory<PfailureStmtClause> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfailureStmtClause> {

		@Override
		public PfailureStmtClause invoke(final Object[] children, final Object[] annotations) {
			return new PfailureStmtClause(annotations[0]);
		}
	};

}
