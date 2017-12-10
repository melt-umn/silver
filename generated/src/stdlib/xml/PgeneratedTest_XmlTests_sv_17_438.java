
package stdlib.xml;

// t::Test ::= 
public final class PgeneratedTest_XmlTests_sv_17_438 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_XmlTests_sv_17_438() {
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
		return "stdlib:xml:generatedTest_XmlTests_sv_17_438";
	}

	static void initProductionAttributeDefinitions() {
		// value = indexOf("Snow Crash", query1.xmlText,) != -1
		stdlib.xml.PgeneratedTest_XmlTests_sv_17_438.localAttributes[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return !((Integer)core.PindexOf.invoke((new common.StringCatter("Snow Crash")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((lib.xml.ast.NXMLNodeList)stdlib.xml.Init.query1.eval()).decorate(context, (common.Lazy[])null).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNodeList)); } })).equals(Integer.valueOf((int)-1)); } };
		// expected = true
		stdlib.xml.PgeneratedTest_XmlTests_sv_17_438.localAttributes[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// t.msg = "Test at 'XmlTests.sv', 17, 0, 17, 89, 511, 600 failed.\nChecking that expression\n   indexOf(\\\"Snow Crash\\\", query1.xmlText,) != -1\nshould be same as expression\n   true\nActual value:\n   " ++ toStringFromBoolean(value,) ++ "\nExpected value: \n   " ++ toStringFromBoolean(expected,) ++ "\n" ++ ""
		stdlib.xml.PgeneratedTest_XmlTests_sv_17_438.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'XmlTests.sv', 17, 0, 17, 89, 511, 600 failed.\nChecking that expression\n   indexOf(\\\"Snow Crash\\\", query1.xmlText,) != -1\nshould be same as expression\n   true\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromBoolean.invoke(context.localAsIsLazy(stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromBoolean.invoke(context.localAsIsLazy(stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsBoolean(value, expected,)
		stdlib.xml.PgeneratedTest_XmlTests_sv_17_438.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsBoolean.invoke(context.localAsIsLazy(stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438), context.localAsIsLazy(stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_XmlTests_sv_17_438> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_XmlTests_sv_17_438> {

		@Override
		public PgeneratedTest_XmlTests_sv_17_438 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_XmlTests_sv_17_438();
		}
	};

}
