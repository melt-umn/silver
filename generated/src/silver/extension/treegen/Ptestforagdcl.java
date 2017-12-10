
package silver.extension.treegen;

// top::AGDcl ::= 'testFor' testSuite::Name ':' n::Name '::' id::QName ',' e::Expr ';' 
public final class Ptestforagdcl extends silver.definition.core.NAGDcl {

	public static final int i__G_8 = 0;
	public static final int i_testSuite = 1;
	public static final int i__G_6 = 2;
	public static final int i_n = 3;
	public static final int i__G_4 = 4;
	public static final int i_id = 5;
	public static final int i__G_2 = 6;
	public static final int i_e = 7;
	public static final int i__G_0 = 8;


	public static final Class<?> childTypes[] = {silver.extension.treegen.TTestFor_T.class,silver.definition.core.NName.class,silver.definition.core.TColon_t.class,silver.definition.core.NName.class,silver.definition.core.TColonColon_t.class,silver.definition.core.NQName.class,silver.definition.core.TComma_t.class,silver.definition.core.NExpr.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_testforagdcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[9][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_testSuite] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public Ptestforagdcl(final Object c__G_8, final Object c_testSuite, final Object c__G_6, final Object c_n, final Object c__G_4, final Object c_id, final Object c__G_2, final Object c_e, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_8 = c__G_8;
		this.child_testSuite = c_testSuite;
		this.child__G_6 = c__G_6;
		this.child_n = c_n;
		this.child__G_4 = c__G_4;
		this.child_id = c_id;
		this.child__G_2 = c__G_2;
		this.child_e = c_e;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_8;
	public final silver.extension.treegen.TTestFor_T getChild__G_8() {
		return (silver.extension.treegen.TTestFor_T) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child_testSuite;
	public final silver.definition.core.NName getChild_testSuite() {
		return (silver.definition.core.NName) (child_testSuite = common.Util.demand(child_testSuite));
	}

	private Object child__G_6;
	public final silver.definition.core.TColon_t getChild__G_6() {
		return (silver.definition.core.TColon_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_n;
	public final silver.definition.core.NName getChild_n() {
		return (silver.definition.core.NName) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_4;
	public final silver.definition.core.TColonColon_t getChild__G_4() {
		return (silver.definition.core.TColonColon_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_id;
	public final silver.definition.core.NQName getChild_id() {
		return (silver.definition.core.NQName) (child_id = common.Util.demand(child_id));
	}

	private Object child__G_2;
	public final silver.definition.core.TComma_t getChild__G_2() {
		return (silver.definition.core.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_8: return getChild__G_8();
			case i_testSuite: return getChild_testSuite();
			case i__G_6: return getChild__G_6();
			case i_n: return getChild_n();
			case i__G_4: return getChild__G_4();
			case i_id: return getChild_id();
			case i__G_2: return getChild__G_2();
			case i_e: return getChild_e();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_8: return child__G_8;
			case i_testSuite: return child_testSuite;
			case i__G_6: return child__G_6;
			case i_n: return child_n;
			case i__G_4: return child__G_4;
			case i_id: return child_id;
			case i__G_2: return child__G_2;
			case i_e: return child_e;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 9;
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
		return ((silver.definition.core.NAGDcl)core.Pfoldr.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.core.PappendAGDcl.factory.invokeNamedPartial(null, new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.fundcl__ON__silver_extension_treegen_testforagdcl)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.extension.treegen.PgenerateTestFor.factory.invokePartial(new int[]{1, 2, 3}, new Object[]{context.localAsIsLazy(silver.extension.treegen.Init.generatedName__ON__silver_extension_treegen_testforagdcl), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.Ptestforagdcl.i_testSuite))}); } }, context.localAsIsLazy(silver.extension.treegen.Init.prods__ON__silver_extension_treegen_testforagdcl))); } })); } }));
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
		return "silver:extension:treegen:testforagdcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.defs = []
		silver.extension.treegen.Ptestforagdcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.moduleNames = []
		silver.extension.treegen.Ptestforagdcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.flowDefs = []
		silver.extension.treegen.Ptestforagdcl.synthesizedAttributes[silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// forward.env = newScopeEnv(forward.defs, top.env)
		silver.extension.treegen.Ptestforagdcl.forwardInheritedAttributes[silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.forward().synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// prods = getProdsForNt(id.lookupType.fullName, top.env)
		silver.extension.treegen.Ptestforagdcl.localAttributes[silver.extension.treegen.Init.prods__ON__silver_extension_treegen_testforagdcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetProdsForNt.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.extension.treegen.Ptestforagdcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// l = top.location
		silver.extension.treegen.Ptestforagdcl.localAttributes[silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } };
		// generatedName = "checkPropOn" ++ id.name ++ toString(genInt())
		silver.extension.treegen.Ptestforagdcl.localAttributes[silver.extension.treegen.Init.generatedName__ON__silver_extension_treegen_testforagdcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("checkPropOn")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.treegen.Ptestforagdcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))))); } };
		// fundcl = functionDcl('function', name(generatedName, l), sig, body,location=l)
		silver.extension.treegen.Ptestforagdcl.localAttributes[silver.extension.treegen.Init.fundcl__ON__silver_extension_treegen_testforagdcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PfunctionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TFunction_kwd((new common.StringCatter("function")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke(context.localAsIsLazy(silver.extension.treegen.Init.generatedName__ON__silver_extension_treegen_testforagdcl), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.sig__ON__silver_extension_treegen_testforagdcl)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.body__ON__silver_extension_treegen_testforagdcl)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } };
		// sig = functionSignature(functionLHS(typerepTypeExpr(boolType(),location=l),location=l), '::=', productionRHSCons(productionRHSElem(n, '::', typerepTypeExpr(id.lookupType.typerep,location=l),location=l), productionRHSNil(location=l),location=l),location=l)
		silver.extension.treegen.Ptestforagdcl.localAttributes[silver.extension.treegen.Init.sig__ON__silver_extension_treegen_testforagdcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NFunctionSignature)new silver.definition.core.PfunctionSignature(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NFunctionLHS)new silver.definition.core.PfunctionLHS(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.definition.type.syntax.PtyperepTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PboolType()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TCCEQ_t((new common.StringCatter("::=")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionRHS)new silver.definition.core.PproductionRHSCons(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionRHSElem)new silver.definition.core.PproductionRHSElem(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.Ptestforagdcl.i_n)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TColonColon_t((new common.StringCatter("::")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.definition.type.syntax.PtyperepTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.extension.treegen.Ptestforagdcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionRHS)new silver.definition.core.PproductionRHSNil(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } };
		// body = productionBody('{', foldl(productionStmtsSnoc(_, _,location=l), productionStmtsNil(location=l), stmts), '}',location=l)
		silver.extension.treegen.Ptestforagdcl.localAttributes[silver.extension.treegen.Init.body__ON__silver_extension_treegen_testforagdcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionBody)new silver.definition.core.PproductionBody(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLCurly_t((new common.StringCatter("{")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmts)core.Pfoldl.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.core.PproductionStmtsSnoc.factory.invokeNamedPartial(null, new int[]{0}, new Object[]{common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl))}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmts)new silver.definition.core.PproductionStmtsNil(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } }, context.localAsIsLazy(silver.extension.treegen.Init.stmts__ON__silver_extension_treegen_testforagdcl))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRCurly_t((new common.StringCatter("}")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } };
		// stmts = [ returnDef('return', e, ';',location=l) ]
		silver.extension.treegen.Ptestforagdcl.localAttributes[silver.extension.treegen.Init.stmts__ON__silver_extension_treegen_testforagdcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)new silver.definition.core.PreturnDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TReturn_kwd((new common.StringCatter("return")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.Ptestforagdcl.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TSemi_t((new common.StringCatter(";")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.l__ON__silver_extension_treegen_testforagdcl)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<Ptestforagdcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Ptestforagdcl> {

		@Override
		public Ptestforagdcl invoke(final Object[] children, final Object[] annotations) {
			return new Ptestforagdcl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], annotations[0]);
		}
	};

}
