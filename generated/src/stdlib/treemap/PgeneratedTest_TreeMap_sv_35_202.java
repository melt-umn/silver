
package stdlib.treemap;

// t::Test ::= 
public final class PgeneratedTest_TreeMap_sv_35_202 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_TreeMap_sv_35_202() {
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
		return "stdlib:treemap:generatedTest_TreeMap_sv_35_202";
	}

	static void initProductionAttributeDefinitions() {
		// value = t2.blackHeight == 3
		stdlib.treemap.PgeneratedTest_TreeMap_sv_35_202.localAttributes[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)((silver.util.treemap.NTreeMap)stdlib.treemap.Init.t2.eval()).decorate(context, (common.Lazy[])null).synthesized(stdlib.treemap.Init.stdlib_treemap_blackHeight__ON__silver_util_treemap_TreeMap)).equals(Integer.valueOf((int)3)); } };
		// expected = true
		stdlib.treemap.PgeneratedTest_TreeMap_sv_35_202.localAttributes[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// t.msg = "Test at 'TreeMap.sv', 35, 0, 35, 65, 853, 918 failed.\nChecking that expression\n   t2.blackHeight == 3\nshould be same as expression\n   true\nActual value:\n   " ++ toStringFromBoolean(value,) ++ "\nExpected value: \n   " ++ toStringFromBoolean(expected,) ++ "\n" ++ ""
		stdlib.treemap.PgeneratedTest_TreeMap_sv_35_202.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'TreeMap.sv', 35, 0, 35, 65, 853, 918 failed.\nChecking that expression\n   t2.blackHeight == 3\nshould be same as expression\n   true\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromBoolean.invoke(context.localAsIsLazy(stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromBoolean.invoke(context.localAsIsLazy(stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsBoolean(value, expected,)
		stdlib.treemap.PgeneratedTest_TreeMap_sv_35_202.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsBoolean.invoke(context.localAsIsLazy(stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202), context.localAsIsLazy(stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_TreeMap_sv_35_202> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_TreeMap_sv_35_202> {

		@Override
		public PgeneratedTest_TreeMap_sv_35_202 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_TreeMap_sv_35_202();
		}
	};

}
