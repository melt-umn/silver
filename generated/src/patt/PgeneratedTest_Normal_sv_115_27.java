
package patt;

// t::Test ::= 
public final class PgeneratedTest_Normal_sv_115_27 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__patt_generatedTest_Normal_sv_115_27;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_Normal_sv_115_27() {
		super();

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.testing.NTest)new silver.testing.PdefTest());
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
		return "patt:generatedTest_Normal_sv_115_27";
	}

	static void initProductionAttributeDefinitions() {
		// value = lookattees([ a(,), b(,), c(,), d(,) ],)
		patt.PgeneratedTest_Normal_sv_115_27.localAttributes[patt.Init.value__ON__patt_generatedTest_Normal_sv_115_27] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)patt.Plookattees.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NT)new patt.Pa()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NT)new patt.Pb()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NT)new patt.Pc()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NT)new patt.Pd()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } };
		// expected = [ true, false, true, false ]
		patt.PgeneratedTest_Normal_sv_115_27.localAttributes[patt.Init.expected__ON__patt_generatedTest_Normal_sv_115_27] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(true, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(false, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(true, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(false, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
		// t.msg = "Test at 'Normal.sv', 115, 0, 116, 38, 2762, 2874 failed.\nChecking that expression\n   lookattees([ a(,), b(,), c(,), d(,) ],)\nshould be same as expression\n   [ true, false, true, false ]\nActual value:\n   " ++ toStringFromList(toStringFromBoolean, value,) ++ "\nExpected value: \n   " ++ toStringFromList(toStringFromBoolean, expected,) ++ "\n" ++ ""
		patt.PgeneratedTest_Normal_sv_115_27.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'Normal.sv', 115, 0, 116, 38, 2762, 2874 failed.\nChecking that expression\n   lookattees([ a(,), b(,), c(,), d(,) ],)\nshould be same as expression\n   [ true, false, true, false ]\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromBoolean.factory, context.localAsIsLazy(patt.Init.value__ON__patt_generatedTest_Normal_sv_115_27))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromBoolean.factory, context.localAsIsLazy(patt.Init.expected__ON__patt_generatedTest_Normal_sv_115_27))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsList(equalsBoolean, value, expected,)
		patt.PgeneratedTest_Normal_sv_115_27.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsList.invoke(lib.extcore.PequalsBoolean.factory, context.localAsIsLazy(patt.Init.value__ON__patt_generatedTest_Normal_sv_115_27), context.localAsIsLazy(patt.Init.expected__ON__patt_generatedTest_Normal_sv_115_27))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_Normal_sv_115_27> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_Normal_sv_115_27> {

		@Override
		public PgeneratedTest_Normal_sv_115_27 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_Normal_sv_115_27();
		}
	};

}
