
package silver.extension.treegen;

public final class PgenerateTestFor extends common.FunctionNode {

	public static final int i_d = 0;
	public static final int i_testfunname = 1;
	public static final int i_l = 2;
	public static final int i_testSuite = 3;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,common.StringCatter.class,core.NLocation.class,silver.definition.core.NName.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_generateTestFor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_testSuite] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];

	}

	public PgenerateTestFor(final Object c_d, final Object c_testfunname, final Object c_l, final Object c_testSuite) {
		this.child_d = c_d;
		this.child_testfunname = c_testfunname;
		this.child_l = c_l;
		this.child_testSuite = c_testSuite;

	}

	private Object child_d;
	public final silver.definition.env.NDclInfo getChild_d() {
		return (silver.definition.env.NDclInfo) (child_d = common.Util.demand(child_d));
	}

	private Object child_testfunname;
	public final common.StringCatter getChild_testfunname() {
		return (common.StringCatter) (child_testfunname = common.Util.demand(child_testfunname));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_testSuite;
	public final silver.definition.core.NName getChild_testSuite() {
		return (silver.definition.core.NName) (child_testSuite = common.Util.demand(child_testSuite));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();
			case i_testfunname: return getChild_testfunname();
			case i_l: return getChild_l();
			case i_testSuite: return getChild_testSuite();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;
			case i_testfunname: return child_testfunname;
			case i_l: return child_l;
			case i_testSuite: return child_testSuite;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:extension:treegen:generateTestFor";
	}

	public static silver.definition.core.NAGDcl invoke(final Object c_d, final Object c_testfunname, final Object c_l, final Object c_testSuite) {
		try {
		final common.DecoratedNode context = new PgenerateTestFor(c_d, c_testfunname, c_l, c_testSuite).decorate();
		//foldr(appendAGDcl(_, _,location=l), emptyAGDcl(location=l), [ functionDcl('function', name(generatedName, l), sig, body,location=l), equalityTest2_p(terminal(EqualityTest_t, "equalityTest", l), '(', mkStrFunctionInvocation(l, "repeatTestTimes", [ baseExpr(qName(l, generatedName),location=l), intConst(terminal(Int_t, "10", core:loc("??", -1, -1, -1, -1, -1, -1)),location=l) ]), ',', trueConst('true',location=l), ',', booleanTypeExpr('Boolean',location=l), ',', testSuite, ')', ';',location=l) ])
		return (silver.definition.core.NAGDcl)(((silver.definition.core.NAGDcl)core.Pfoldr.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.core.PappendAGDcl.factory.invokeNamedPartial(null, new int[]{0}, new Object[]{common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l))}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PfunctionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TFunction_kwd((new common.StringCatter("function")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke(context.localAsIsLazy(silver.extension.treegen.Init.generatedName__ON__silver_extension_treegen_generateTestFor), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.sig__ON__silver_extension_treegen_generateTestFor)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.body__ON__silver_extension_treegen_generateTestFor)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.testing.PequalityTest2_p(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.testing.TEqualityTest_t((new common.StringCatter("equalityTest")), (core.NLocation)context.childDecorated(silver.extension.treegen.PgenerateTestFor.i_l).undecorate()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.definition.core.PmkStrFunctionInvocation.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l)), (new common.StringCatter("repeatTestTimes")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l)), context.localAsIsLazy(silver.extension.treegen.Init.generatedName__ON__silver_extension_treegen_generateTestFor))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PintConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TInt_t((new common.StringCatter("10")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PtrueConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TTrue_kwd((new common.StringCatter("true")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.definition.type.syntax.PbooleanTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.type.syntax.TBoolean_tkwd((new common.StringCatter("Boolean")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_testSuite)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TSemi_t((new common.StringCatter(";")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateTestFor.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:generateTestFor", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NAGDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NAGDcl> {
		@Override
		public silver.definition.core.NAGDcl invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgenerateTestFor.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}