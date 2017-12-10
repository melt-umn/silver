package stdlib.xml;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		lib.xml.ast.Init.initAllStatics();
		core.Init.initAllStatics();
		stdlib.Init.initAllStatics();
		lib.xml.foreigntypes.Init.initAllStatics();
		lib.xml.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		stdlib.xml.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		lib.xml.ast.Init.init();
		core.Init.init();
		stdlib.Init.init();
		lib.xml.foreigntypes.Init.init();
		lib.xml.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		stdlib.xml.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		lib.xml.ast.Init.postInit();
		core.Init.postInit();
		stdlib.Init.postInit();
		lib.xml.foreigntypes.Init.postInit();
		lib.xml.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		stdlib.xml.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_XmlTests_sv_17_438.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_XmlTests_sv_18_439.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_XmlTests_sv_19_440.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_XmlTests_sv_24_441.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_XmlTests_sv_28_442.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_XmlTests_sv_34_443.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_XmlTests_sv_44_444.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_XmlTests_sv_47_445.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_XmlTests_sv_50_446.class);
	}

	private static void setupInheritedAttributes(){
		stdlib.xml.PgeneratedTest_XmlTests_sv_17_438.occurs_local[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438] = "stdlib:xml:generatedTest_XmlTests_sv_17_438:local:value";
		stdlib.xml.PgeneratedTest_XmlTests_sv_17_438.occurs_local[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438] = "stdlib:xml:generatedTest_XmlTests_sv_17_438:local:expected";
		stdlib.xml.PgeneratedTest_XmlTests_sv_18_439.occurs_local[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_18_439] = "stdlib:xml:generatedTest_XmlTests_sv_18_439:local:value";
		stdlib.xml.PgeneratedTest_XmlTests_sv_18_439.occurs_local[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_18_439] = "stdlib:xml:generatedTest_XmlTests_sv_18_439:local:expected";
		stdlib.xml.PgeneratedTest_XmlTests_sv_19_440.occurs_local[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_19_440] = "stdlib:xml:generatedTest_XmlTests_sv_19_440:local:value";
		stdlib.xml.PgeneratedTest_XmlTests_sv_19_440.occurs_local[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_19_440] = "stdlib:xml:generatedTest_XmlTests_sv_19_440:local:expected";
		stdlib.xml.PgeneratedTest_XmlTests_sv_24_441.occurs_local[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_24_441] = "stdlib:xml:generatedTest_XmlTests_sv_24_441:local:value";
		stdlib.xml.PgeneratedTest_XmlTests_sv_24_441.occurs_local[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_24_441] = "stdlib:xml:generatedTest_XmlTests_sv_24_441:local:expected";
		stdlib.xml.PgeneratedTest_XmlTests_sv_28_442.occurs_local[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_28_442] = "stdlib:xml:generatedTest_XmlTests_sv_28_442:local:value";
		stdlib.xml.PgeneratedTest_XmlTests_sv_28_442.occurs_local[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_28_442] = "stdlib:xml:generatedTest_XmlTests_sv_28_442:local:expected";
		stdlib.xml.PgeneratedTest_XmlTests_sv_34_443.occurs_local[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_34_443] = "stdlib:xml:generatedTest_XmlTests_sv_34_443:local:value";
		stdlib.xml.PgeneratedTest_XmlTests_sv_34_443.occurs_local[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_34_443] = "stdlib:xml:generatedTest_XmlTests_sv_34_443:local:expected";
		stdlib.xml.PgeneratedTest_XmlTests_sv_44_444.occurs_local[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_44_444] = "stdlib:xml:generatedTest_XmlTests_sv_44_444:local:value";
		stdlib.xml.PgeneratedTest_XmlTests_sv_44_444.occurs_local[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_44_444] = "stdlib:xml:generatedTest_XmlTests_sv_44_444:local:expected";
		stdlib.xml.PgeneratedTest_XmlTests_sv_47_445.occurs_local[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_47_445] = "stdlib:xml:generatedTest_XmlTests_sv_47_445:local:value";
		stdlib.xml.PgeneratedTest_XmlTests_sv_47_445.occurs_local[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_47_445] = "stdlib:xml:generatedTest_XmlTests_sv_47_445:local:expected";
		stdlib.xml.PgeneratedTest_XmlTests_sv_50_446.occurs_local[stdlib.xml.Init.value__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446] = "stdlib:xml:generatedTest_XmlTests_sv_50_446:local:value";
		stdlib.xml.PgeneratedTest_XmlTests_sv_50_446.occurs_local[stdlib.xml.Init.expected__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446] = "stdlib:xml:generatedTest_XmlTests_sv_50_446:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		stdlib.xml.PgeneratedTest_XmlTests_sv_17_438.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_XmlTests_sv_17_438(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.xml.PgeneratedTest_XmlTests_sv_17_438()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.xml.PgeneratedTest_XmlTests_sv_18_439.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_XmlTests_sv_18_439(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.xml.PgeneratedTest_XmlTests_sv_18_439()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.xml.PgeneratedTest_XmlTests_sv_19_440.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_XmlTests_sv_19_440(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.xml.PgeneratedTest_XmlTests_sv_19_440()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.xml.PgeneratedTest_XmlTests_sv_24_441.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_XmlTests_sv_24_441(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.xml.PgeneratedTest_XmlTests_sv_24_441()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.xml.PgeneratedTest_XmlTests_sv_28_442.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_XmlTests_sv_28_442(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.xml.PgeneratedTest_XmlTests_sv_28_442()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.xml.PgeneratedTest_XmlTests_sv_34_443.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_XmlTests_sv_34_443(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.xml.PgeneratedTest_XmlTests_sv_34_443()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.xml.PgeneratedTest_XmlTests_sv_44_444.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_XmlTests_sv_44_444(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.xml.PgeneratedTest_XmlTests_sv_44_444()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.xml.PgeneratedTest_XmlTests_sv_47_445.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_XmlTests_sv_47_445(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.xml.PgeneratedTest_XmlTests_sv_47_445()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.xml.PgeneratedTest_XmlTests_sv_50_446.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_XmlTests_sv_50_446(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.xml.PgeneratedTest_XmlTests_sv_50_446()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438 = 0;
	public static int count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_18_439 = 0;
	public static int count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_19_440 = 0;
	public static int count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_24_441 = 0;
	public static int count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_28_442 = 0;
	public static int count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_34_443 = 0;
	public static int count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_44_444 = 0;
	public static int count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_47_445 = 0;
	public static int count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446 = 0;
public static final int value__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438++;
public static final int expected__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_17_438++;
public static final int value__ON__stdlib_xml_generatedTest_XmlTests_sv_18_439 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_18_439++;
public static final int expected__ON__stdlib_xml_generatedTest_XmlTests_sv_18_439 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_18_439++;
public static final int value__ON__stdlib_xml_generatedTest_XmlTests_sv_19_440 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_19_440++;
public static final int expected__ON__stdlib_xml_generatedTest_XmlTests_sv_19_440 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_19_440++;
public static final int value__ON__stdlib_xml_generatedTest_XmlTests_sv_24_441 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_24_441++;
public static final int expected__ON__stdlib_xml_generatedTest_XmlTests_sv_24_441 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_24_441++;
public static final int value__ON__stdlib_xml_generatedTest_XmlTests_sv_28_442 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_28_442++;
public static final int expected__ON__stdlib_xml_generatedTest_XmlTests_sv_28_442 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_28_442++;
public static final int value__ON__stdlib_xml_generatedTest_XmlTests_sv_34_443 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_34_443++;
public static final int expected__ON__stdlib_xml_generatedTest_XmlTests_sv_34_443 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_34_443++;
public static final int value__ON__stdlib_xml_generatedTest_XmlTests_sv_44_444 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_44_444++;
public static final int expected__ON__stdlib_xml_generatedTest_XmlTests_sv_44_444 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_44_444++;
public static final int value__ON__stdlib_xml_generatedTest_XmlTests_sv_47_445 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_47_445++;
public static final int expected__ON__stdlib_xml_generatedTest_XmlTests_sv_47_445 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_47_445++;
public static final int value__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446++;
public static final int expected__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446 = stdlib.xml.Init.count_local__ON__stdlib_xml_generatedTest_XmlTests_sv_50_446++;
	public static final common.Thunk<Object> books = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NParseResult)lib.xml.foreigntypes.PparseXMLFileF.invoke((new common.StringCatter("xml/books.xml")))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_parseTree__ON__core_ParseResult)); } };
	public static final common.Thunk<Object> smallxhtml = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLDocument)((core.NParseResult)lib.xml.PparseXMLFileN.invoke((new common.StringCatter("xml/smallxhtml.xml")))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_parseTree__ON__core_ParseResult)); } };
	public static final common.Thunk<Object> mynamespaces = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("x")), (new common.StringCatter("http://www.asdfexample.com/books")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
	public static final common.Thunk<Object> query1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)lib.xml.foreigntypes.PxmlNodeListF2N.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)lib.xml.foreigntypes.PnodeListXPathQueryFns.invoke((new common.StringCatter("//x:book[x:author=\"Neal Stephenson\"]")), stdlib.xml.Init.mynamespaces, stdlib.xml.Init.books)); } })); } };
	public static final common.Thunk<Object> query2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)lib.xml.foreigntypes.PxmlNodeListF2N.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)lib.xml.foreigntypes.PnodeListXPathQueryFns.invoke((new common.StringCatter("//x:book[x:author=\"Neal Stephenson\"]")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, stdlib.xml.Init.books)); } })); } };
	public static final common.Thunk<Object> query3 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)lib.xml.foreigntypes.PxmlNodeListF2N.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)lib.xml.foreigntypes.PnodeListXPathQueryFns.invoke((new common.StringCatter("//book[author=\"Neal Stephenson\"]")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, stdlib.xml.Init.books)); } })); } };
	public static final common.Thunk<Object> query4 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)lib.xml.foreigntypes.PxmlNodeListF2N.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)lib.xml.foreigntypes.PnodeListXPathReQueryFns.invoke((new common.StringCatter("//x:book[x:title=\"Snow Crash\"]")), stdlib.xml.Init.mynamespaces, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)lib.xml.foreigntypes.PnodeListF2NPartial.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)lib.xml.foreigntypes.PnodeListXPathQueryFns.invoke((new common.StringCatter("//x:book[x:author=\"Neal Stephenson\"]")), stdlib.xml.Init.mynamespaces, stdlib.xml.Init.books)); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> mydoc = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLDocument)new lib.xml.ast.PxmlDocument(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLDocumentType)new lib.xml.ast.PxmlNoDocumentType()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)new lib.xml.ast.PxmlNodeListCons(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNode)new lib.xml.ast.PxmlNodeElement((new common.StringCatter("foo")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)new lib.xml.ast.PxmlNodeListCons(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNode)new lib.xml.ast.PxmlNodeElement((new common.StringCatter("bar")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLAttribute)new lib.xml.ast.PxmlAttribute((new common.StringCatter("baz")), (new common.StringCatter("&")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)new lib.xml.ast.PxmlNodeListNil()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)new lib.xml.ast.PxmlNodeListNil()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((lib.xml.ast.NXMLNodeList)new lib.xml.ast.PxmlNodeListNil()); } })); } })); } };
	public static final common.Thunk<Object> mydocpp = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)lib.xml.foreigntypes.PxmlDocumentN2String.invoke(stdlib.xml.Init.mydoc)); } };
}
