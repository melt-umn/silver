
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax;

// cs::StmtClauses ::= c::StmtClause rest::StmtClauses 
public final class PconsStmtClause extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses {

	public static final int i_c = 0;
	public static final int i_rest = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClause.class,edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_consStmtClause;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_c] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClause.num_inh_attrs];
	childInheritedAttributes[i_rest] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses.num_inh_attrs];

	}

	public PconsStmtClause(final Object c_c, final Object c_rest, final Object a_core_location) {
		super(a_core_location);
		this.child_c = c_c;
		this.child_rest = c_rest;

	}

	private Object child_c;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClause getChild_c() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClause) (child_c = common.Util.demand(child_c));
	}

	private Object child_rest;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses getChild_rest() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NStmtClauses) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_c: return getChild_c();
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_c: return child_c;
			case i_rest: return child_rest;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:abstractsyntax:consStmtClause erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:abstractsyntax:consStmtClause";
	}

	static void initProductionAttributeDefinitions() {
		// cs.pp = cat(c.pp, rest.pp,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_pp__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.i_c, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_pp__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClause), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.i_rest, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_pp__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses))); } };
		// c.expectedType = cs.expectedType
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.childInheritedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.i_c][edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_expectedType__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClause] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.inherited(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_expectedType__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses)); } };
		// rest.expectedType = cs.expectedType
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.childInheritedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.i_rest][edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_expectedType__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.inherited(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_expectedType__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses)); } };
		// cs.errors := c.errors ++ rest.errors
		if(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses] == null)
			edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses] = new silver.langutil.CAerrors(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses);
		((common.CollectionAttribute)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.i_c, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClause), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.i_rest, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses))); } });
		// cs.transform = c.transform
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_transform__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.i_c).synthesized(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_transform__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClause)); } };
		// c.transformIn = rest.transform
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.childInheritedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.i_c][edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_transformIn__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClause] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsStmtClause.i_rest).synthesized(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_transform__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_StmtClauses)); } };

	}

	public static final common.NodeFactory<PconsStmtClause> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsStmtClause> {

		@Override
		public PconsStmtClause invoke(final Object[] children, final Object[] annotations) {
			return new PconsStmtClause(children[0], children[1], annotations[0]);
		}
	};

}
