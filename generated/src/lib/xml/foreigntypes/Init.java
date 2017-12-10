package lib.xml.foreigntypes;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		lib.xml.ast.Init.initAllStatics();
		lib.xml.foreigntypes.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		lib.xml.ast.Init.init();
		lib.xml.foreigntypes.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		lib.xml.ast.Init.postInit();
		lib.xml.foreigntypes.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION nodeListXPathQueryN XMLNodeList ::= query::String doc::XML_Document 
		//FUNCTION xmlDocumentN2String String ::= doc::XMLDocument 
	}

	public static int count_local__ON__lib_xml_foreigntypes_parseXMLFileF = 0;
	public static int count_local__ON__lib_xml_foreigntypes_nodeListXPathQueryN = 0;
	public static int count_local__ON__lib_xml_foreigntypes_nodeListXPathQueryF = 0;
	public static int count_local__ON__lib_xml_foreigntypes_stringXPathQuery = 0;
	public static int count_local__ON__lib_xml_foreigntypes_nodeListXPathReQueryF = 0;
	public static int count_local__ON__lib_xml_foreigntypes_stringXPathReQuery = 0;
	public static int count_local__ON__lib_xml_foreigntypes_nodeListXPathQueryFns = 0;
	public static int count_local__ON__lib_xml_foreigntypes_stringXPathQueryns = 0;
	public static int count_local__ON__lib_xml_foreigntypes_nodeListXPathReQueryFns = 0;
	public static int count_local__ON__lib_xml_foreigntypes_stringXPathReQueryns = 0;
	public static int count_local__ON__lib_xml_foreigntypes_xmlNodeListF2N = 0;
	public static int count_local__ON__lib_xml_foreigntypes_nodeListF2NPartial = 0;
	public static int count_local__ON__lib_xml_foreigntypes_xmlDocumentF2N = 0;
	public static int count_local__ON__lib_xml_foreigntypes_xmlDocumentN2F = 0;
	public static int count_local__ON__lib_xml_foreigntypes_xmlDocumentF2String = 0;
	public static int count_local__ON__lib_xml_foreigntypes_xmlDocumentN2String = 0;
}
