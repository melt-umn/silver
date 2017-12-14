package lib.xml.ast;

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

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		lib.xml.ast.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		lib.xml.ast.Init.postInit();


		common.Decorator.applyDecorators(lib.xml.ast.NXMLDocument.decorators, PxmlDocument.class);
		common.Decorator.applyDecorators(lib.xml.ast.NXMLDocumentType.decorators, PxmlDocumentType.class);
		common.Decorator.applyDecorators(lib.xml.ast.NXMLDocumentType.decorators, PxmlNoDocumentType.class);
		common.Decorator.applyDecorators(lib.xml.ast.NXMLNodeList.decorators, PxmlNodeListCons.class);
		common.Decorator.applyDecorators(lib.xml.ast.NXMLNodeList.decorators, PxmlNodeListNil.class);
		common.Decorator.applyDecorators(lib.xml.ast.NXMLNode.decorators, PxmlNodeElement.class);
		common.Decorator.applyDecorators(lib.xml.ast.NXMLNode.decorators, PxmlNodeText.class);
		common.Decorator.applyDecorators(lib.xml.ast.NXMLAttribute.decorators, PxmlAttribute.class);
	}

	private static void setupInheritedAttributes(){
		lib.xml.ast.NXMLDocument.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLDocument] = "lib:xml:ast:xmlSubNodes";
		lib.xml.ast.NXMLDocument.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlDTD__ON__lib_xml_ast_XMLDocument] = "lib:xml:ast:xmlDTD";
		lib.xml.ast.NXMLDocument.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLDocument] = "lib:xml:ast:xmlText";
		lib.xml.ast.NXMLDocument.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLDocument] = "lib:xml:ast:xmlUnparse";
		lib.xml.ast.NXMLDocumentType.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLDocumentType] = "lib:xml:ast:xmlText";
		lib.xml.ast.NXMLDocumentType.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlName__ON__lib_xml_ast_XMLDocumentType] = "lib:xml:ast:xmlName";
		lib.xml.ast.NXMLDocumentType.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLDocumentType] = "lib:xml:ast:xmlSubNodes";
		lib.xml.ast.NXMLDocumentType.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLDocumentType] = "lib:xml:ast:xmlUnparse";
		lib.xml.ast.NXMLNodeList.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlSubNodeList__ON__lib_xml_ast_XMLNodeList] = "lib:xml:ast:xmlSubNodeList";
		lib.xml.ast.NXMLNodeList.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNodeList] = "lib:xml:ast:xmlText";
		lib.xml.ast.NXMLNodeList.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNodeList] = "lib:xml:ast:xmlUnparse";
		lib.xml.ast.NXMLNode.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlName__ON__lib_xml_ast_XMLNode] = "lib:xml:ast:xmlName";
		lib.xml.ast.NXMLNode.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlAttributes__ON__lib_xml_ast_XMLNode] = "lib:xml:ast:xmlAttributes";
		lib.xml.ast.NXMLNode.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLNode] = "lib:xml:ast:xmlSubNodes";
		lib.xml.ast.NXMLNode.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNode] = "lib:xml:ast:xmlText";
		lib.xml.ast.NXMLNode.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNode] = "lib:xml:ast:xmlUnparse";
		lib.xml.ast.NXMLAttribute.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlName__ON__lib_xml_ast_XMLAttribute] = "lib:xml:ast:xmlName";
		lib.xml.ast.NXMLAttribute.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlAttributeValue__ON__lib_xml_ast_XMLAttribute] = "lib:xml:ast:xmlAttributeValue";
		lib.xml.ast.NXMLAttribute.occurs_syn[lib.xml.ast.Init.lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLAttribute] = "lib:xml:ast:xmlUnparse";
	}

	private static void initProductionAttributeDefinitions(){
		lib.xml.ast.PxmlDocument.initProductionAttributeDefinitions();
		lib.xml.ast.PxmlDocumentType.initProductionAttributeDefinitions();
		lib.xml.ast.PxmlNoDocumentType.initProductionAttributeDefinitions();
		lib.xml.ast.PxmlNodeListCons.initProductionAttributeDefinitions();
		lib.xml.ast.PxmlNodeListNil.initProductionAttributeDefinitions();
		lib.xml.ast.PxmlNodeElement.initProductionAttributeDefinitions();
		lib.xml.ast.PxmlNodeText.initProductionAttributeDefinitions();
		lib.xml.ast.PxmlAttribute.initProductionAttributeDefinitions();
		//FUNCTION xmlUnparseAttr String ::= xa::XMLAttribute 
	}

	public static int count_inh__ON__XMLDocument = 0;
	public static int count_syn__ON__XMLDocument = 0;
	public static int count_inh__ON__XMLDocumentType = 0;
	public static int count_syn__ON__XMLDocumentType = 0;
	public static int count_inh__ON__XMLNodeList = 0;
	public static int count_syn__ON__XMLNodeList = 0;
	public static int count_inh__ON__XMLNode = 0;
	public static int count_syn__ON__XMLNode = 0;
	public static int count_inh__ON__XMLAttribute = 0;
	public static int count_syn__ON__XMLAttribute = 0;
	public static int count_local__ON__lib_xml_ast_xmlDocument = 0;
	public static int count_local__ON__lib_xml_ast_xmlDocumentType = 0;
	public static int count_local__ON__lib_xml_ast_xmlNoDocumentType = 0;
	public static int count_local__ON__lib_xml_ast_xmlNodeListCons = 0;
	public static int count_local__ON__lib_xml_ast_xmlNodeListNil = 0;
	public static int count_local__ON__lib_xml_ast_xmlNodeElement = 0;
	public static int count_local__ON__lib_xml_ast_xmlNodeText = 0;
	public static int count_local__ON__lib_xml_ast_xmlAttribute = 0;
	public static int count_local__ON__lib_xml_ast_xmlUnparseAttr = 0;
public static final int lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLDocument = lib.xml.ast.Init.count_syn__ON__XMLDocument++;
public static final int lib_xml_ast_xmlDTD__ON__lib_xml_ast_XMLDocument = lib.xml.ast.Init.count_syn__ON__XMLDocument++;
public static final int lib_xml_ast_xmlText__ON__lib_xml_ast_XMLDocument = lib.xml.ast.Init.count_syn__ON__XMLDocument++;
public static final int lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLDocument = lib.xml.ast.Init.count_syn__ON__XMLDocument++;
public static final int lib_xml_ast_xmlText__ON__lib_xml_ast_XMLDocumentType = lib.xml.ast.Init.count_syn__ON__XMLDocumentType++;
public static final int lib_xml_ast_xmlName__ON__lib_xml_ast_XMLDocumentType = lib.xml.ast.Init.count_syn__ON__XMLDocumentType++;
public static final int lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLDocumentType = lib.xml.ast.Init.count_syn__ON__XMLDocumentType++;
public static final int lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLDocumentType = lib.xml.ast.Init.count_syn__ON__XMLDocumentType++;
public static final int lib_xml_ast_xmlSubNodeList__ON__lib_xml_ast_XMLNodeList = lib.xml.ast.Init.count_syn__ON__XMLNodeList++;
public static final int lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNodeList = lib.xml.ast.Init.count_syn__ON__XMLNodeList++;
public static final int lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNodeList = lib.xml.ast.Init.count_syn__ON__XMLNodeList++;
public static final int lib_xml_ast_xmlName__ON__lib_xml_ast_XMLNode = lib.xml.ast.Init.count_syn__ON__XMLNode++;
public static final int lib_xml_ast_xmlAttributes__ON__lib_xml_ast_XMLNode = lib.xml.ast.Init.count_syn__ON__XMLNode++;
public static final int lib_xml_ast_xmlSubNodes__ON__lib_xml_ast_XMLNode = lib.xml.ast.Init.count_syn__ON__XMLNode++;
public static final int lib_xml_ast_xmlText__ON__lib_xml_ast_XMLNode = lib.xml.ast.Init.count_syn__ON__XMLNode++;
public static final int lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLNode = lib.xml.ast.Init.count_syn__ON__XMLNode++;
public static final int lib_xml_ast_xmlName__ON__lib_xml_ast_XMLAttribute = lib.xml.ast.Init.count_syn__ON__XMLAttribute++;
public static final int lib_xml_ast_xmlAttributeValue__ON__lib_xml_ast_XMLAttribute = lib.xml.ast.Init.count_syn__ON__XMLAttribute++;
public static final int lib_xml_ast_xmlUnparse__ON__lib_xml_ast_XMLAttribute = lib.xml.ast.Init.count_syn__ON__XMLAttribute++;
}
