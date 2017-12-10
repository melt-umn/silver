
package stdlib.xml;

// t::Test ::= 
public final class PgeneratedTest_XmlTests_sv_50_446 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_XmlTests_sv_50_446() {
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
		return "stdlib:xml:generatedTest_XmlTests_sv_50_446";
	}

	static void initProductionAttributeDefinitions() {
		// value = smallxhtml.xmlDTD.xmlName
		stdlib.xml.PgeneratedTest_XmlTests_sv_50_446.localAttributes[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((lib.xml.ast.NXMLDocumentType)((lib.xml.ast.NXMLDocument)stdlib.xml.Init.smallxhtml.eval()).decorate(context, (common.Lazy[])null).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlDTD__ON__lib_xml_ast_XMLDocument)).decorate(context, (common.Lazy[])null).synthesized(lib.xml.ast.Init.lib_xml_ast_xmlName__ON__lib_xml_ast_XMLDocumentType)); } };
		// expected = "html"
		stdlib.xml.PgeneratedTest_XmlTests_sv_50_446.localAttributes[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("html")); } };
		// t.msg = "Test at 'XmlTests.sv', 50, 0, 50, 71, 2185, 2256 failed.\nChecking that expression\n   smallxhtml.xmlDTD.xmlName\nshould be same as expression\n   \\\"html\\\"\nActual value:\n   " ++ toStringFromString(value,) ++ "\nExpected value: \n   " ++ toStringFromString(expected,) ++ "\n" ++ ""
		stdlib.xml.PgeneratedTest_XmlTests_sv_50_446.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'XmlTests.sv', 50, 0, 50, 71, 2185, 2256 failed.\nChecking that expression\n   smallxhtml.xmlDTD.xmlName\nshould be same as expression\n   \\\"html\\\"\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsString(value, expected,)
		stdlib.xml.PgeneratedTest_XmlTests_sv_50_446.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsString.invoke(context.localAsIsLazy(stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446), context.localAsIsLazy(stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_XmlTests_sv_50_446> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_XmlTests_sv_50_446> {

		@Override
		public PgeneratedTest_XmlTests_sv_50_446 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_XmlTests_sv_50_446();
		}
	};

}
