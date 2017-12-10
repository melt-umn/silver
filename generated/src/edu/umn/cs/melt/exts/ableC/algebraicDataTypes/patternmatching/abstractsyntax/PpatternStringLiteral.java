
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax;

// p::Pattern ::= s::String 
public final class PpatternStringLiteral extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NPattern {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_patternStringLiteral;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PpatternStringLiteral(final Object c_s, final Object a_core_location) {
		super(a_core_location);
		this.child_s = c_s;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:abstractsyntax:patternStringLiteral erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:abstractsyntax:patternStringLiteral";
	}

	static void initProductionAttributeDefinitions() {
		// p.pp = text(s,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_pp__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.i_s))); } };
		// p.decls = []
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_decls__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// p.defs := []
		if(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern] == null)
			edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern] = new edu.umn.cs.melt.ableC.abstractsyntax.env.CAdefs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern);
		((common.CollectionAttribute)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// p.errors := (if compatibleTypes(p.expectedType, pointerType(nilQualifier(,), builtinType(consQualifier(constQualifier(,location=p.location), nilQualifier(,),), signedType(charType(,),),),), false, false,) then [] else [ err(p.location, "Unexpected string constant in pattern",) ]) ++ (if ! null(lookupValue("strcmp", p.env,),) then [] else [ err(p.location, "Pattern string literals require <string.h> to be included",) ])
		if(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern] == null)
			edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern] = new silver.langutil.CAerrors(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern);
		((common.CollectionAttribute)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypes.invoke(context.contextInheritedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_expectedType__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbuiltinType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsQualifier(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconstQualifier(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NPattern)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PsignedType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NIntegerType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcharType()); } })); } })); } })); } }, false, false)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NPattern)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Unexpected string constant in pattern")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupValue.invoke((new common.StringCatter("strcmp")), context.contextInheritedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern))); } }))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NPattern)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Pattern string literals require <string.h> to be included")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } })); } });
		// p.transform = ifStmt(parseExpr("strcmp( *_curr_scrutinee_ptr,(" ++ s ++ "))",), parseStmt("_match = 0;",), nullStmt(,),)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_transform__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PifStmt(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseExpr.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("strcmp( *_curr_scrutinee_ptr,(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternStringLiteral.i_s)), (common.StringCatter)(new common.StringCatter("))")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseStmt.invoke((new common.StringCatter("_match = 0;")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnullStmt()); } })); } };

	}

	public static final common.NodeFactory<PpatternStringLiteral> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpatternStringLiteral> {

		@Override
		public PpatternStringLiteral invoke(final Object[] children, final Object[] annotations) {
			return new PpatternStringLiteral(children[0], annotations[0]);
		}
	};

}
