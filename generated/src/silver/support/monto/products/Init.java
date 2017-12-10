package silver.support.monto.products;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();
		silver.json.Init.initAllStatics();
		silver.support.monto.products.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.langutil.Init.init();
		silver.json.Init.init();
		silver.support.monto.products.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.langutil.Init.postInit();
		silver.json.Init.postInit();
		silver.support.monto.products.Init.postInit();


		common.Decorator.applyDecorators(silver.support.monto.products.NProductValue.decorators, PproductValue.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NProduct.decorators, Pproduct.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NProductDescriptor.decorators, PproductDescriptor.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NProductIdentifier.decorators, PproductIdentifier.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NMetaItem.decorators, PmetaItem.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NMetaItem.decorators, PmetaDegraded.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NProductValue.decorators, PdirectoryProduct.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NDirectoryEntry.decorators, PdirectoryEntry.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NDirectoryEntryType.decorators, PdirectoryEntryFile.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NDirectoryEntryType.decorators, PdirectoryEntryDirectory.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NDirectoryEntryType.decorators, PdirectoryEntrySymlink.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NDirectoryEntryType.decorators, PdirectoryEntryOther.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NProductValue.decorators, PhighlightingProduct.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NHighlightToken.decorators, PhighlightToken.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NColor.decorators, PcommentColor.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NColor.decorators, PfunctionColor.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NColor.decorators, PidentifierColor.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NColor.decorators, PkeywordColor.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NColor.decorators, PliteralColor.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NColor.decorators, PpunctuationColor.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NColor.decorators, PtypeColor.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NColor.decorators, PpaletteColor.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NProductValue.decorators, PsourceProduct.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NProductValue.decorators, PerrorsProduct.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NError.decorators, PbyteRangeError.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NError.decorators, PmessageError.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NErrorSeverity.decorators, PseverityError.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NErrorSeverity.decorators, PseverityWarning.class);
		common.Decorator.applyDecorators(silver.support.monto.products.NErrorSeverity.decorators, PseverityInfo.class);
	}

	private static void setupInheritedAttributes(){
		silver.support.monto.products.NProductValue.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ProductValue] = "silver:json:json";
		silver.support.monto.products.NProductValue.occurs_syn[silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_ProductValue] = "silver:support:monto:products:productName";
		silver.support.monto.products.NProduct.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Product] = "silver:json:json";
		silver.support.monto.products.NProduct.occurs_syn[silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_Product] = "silver:support:monto:products:productName";
		silver.support.monto.products.NProduct.occurs_syn[silver.support.monto.products.Init.silver_support_monto_products_productLang__ON__silver_support_monto_products_Product] = "silver:support:monto:products:productLang";
		silver.support.monto.products.NProduct.occurs_syn[silver.support.monto.products.Init.silver_support_monto_products_productPath__ON__silver_support_monto_products_Product] = "silver:support:monto:products:productPath";
		silver.support.monto.products.NProduct.occurs_syn[silver.support.monto.products.Init.silver_support_monto_products_productValue__ON__silver_support_monto_products_Product] = "silver:support:monto:products:productValue";
		silver.support.monto.products.NProductDescriptor.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ProductDescriptor] = "silver:json:json";
		silver.support.monto.products.NProductDescriptor.occurs_syn[silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_ProductDescriptor] = "silver:support:monto:products:productName";
		silver.support.monto.products.NProductDescriptor.occurs_syn[silver.support.monto.products.Init.silver_support_monto_products_productLang__ON__silver_support_monto_products_ProductDescriptor] = "silver:support:monto:products:productLang";
		silver.support.monto.products.NProductIdentifier.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ProductIdentifier] = "silver:json:json";
		silver.support.monto.products.NProductIdentifier.occurs_syn[silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_ProductIdentifier] = "silver:support:monto:products:productName";
		silver.support.monto.products.NProductIdentifier.occurs_syn[silver.support.monto.products.Init.silver_support_monto_products_productLang__ON__silver_support_monto_products_ProductIdentifier] = "silver:support:monto:products:productLang";
		silver.support.monto.products.NProductIdentifier.occurs_syn[silver.support.monto.products.Init.silver_support_monto_products_productPath__ON__silver_support_monto_products_ProductIdentifier] = "silver:support:monto:products:productPath";
		silver.support.monto.products.NMetaItem.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_MetaItem] = "silver:json:json";
		silver.support.monto.products.NDirectoryEntry.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_DirectoryEntry] = "silver:json:json";
		silver.support.monto.products.PdirectoryEntry.occurs_local[silver.support.monto.products.Init.obj__ON__silver_support_monto_products_directoryEntry] = "silver:support:monto:products:directoryEntry:local:obj";
		silver.support.monto.products.NDirectoryEntryType.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_DirectoryEntryType] = "silver:json:json";
		silver.support.monto.products.NHighlightToken.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_HighlightToken] = "silver:json:json";
		silver.support.monto.products.PhighlightToken.occurs_local[silver.support.monto.products.Init.obj__ON__silver_support_monto_products_highlightToken] = "silver:support:monto:products:highlightToken:local:obj";
		silver.support.monto.products.NColor.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Color] = "silver:json:json";
		silver.support.monto.products.NError.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Error] = "silver:json:json";
		silver.support.monto.products.PbyteRangeError.occurs_local[silver.support.monto.products.Init.obj__ON__silver_support_monto_products_byteRangeError] = "silver:support:monto:products:byteRangeError:local:obj";
		//	local attribute severity::ErrorSeverity;
		silver.support.monto.products.PmessageError.localInheritedAttributes[silver.support.monto.products.Init.severity__ON__silver_support_monto_products_messageError] = new common.Lazy[silver.support.monto.products.NErrorSeverity.num_inh_attrs];
		silver.support.monto.products.PmessageError.occurs_local[silver.support.monto.products.Init.severity__ON__silver_support_monto_products_messageError] = "silver:support:monto:products:messageError:local:severity";
		silver.support.monto.products.PmessageError.occurs_local[silver.support.monto.products.Init.obj__ON__silver_support_monto_products_messageError] = "silver:support:monto:products:messageError:local:obj";
		silver.support.monto.products.NErrorSeverity.occurs_syn[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ErrorSeverity] = "silver:json:json";
	}

	private static void initProductionAttributeDefinitions(){
		silver.support.monto.products.PproductValue.initProductionAttributeDefinitions();
		silver.support.monto.products.Pproduct.initProductionAttributeDefinitions();
		silver.support.monto.products.PproductDescriptor.initProductionAttributeDefinitions();
		//FUNCTION productDescriptorEq Boolean ::= l::ProductDescriptor r::ProductDescriptor 
		silver.support.monto.products.PproductIdentifier.initProductionAttributeDefinitions();
		silver.support.monto.products.PmetaItem.initProductionAttributeDefinitions();
		silver.support.monto.products.PmetaDegraded.initProductionAttributeDefinitions();
		silver.support.monto.products.PdirectoryProduct.initProductionAttributeDefinitions();
		silver.support.monto.products.PdirectoryEntry.initProductionAttributeDefinitions();
		silver.support.monto.products.PdirectoryEntryFile.initProductionAttributeDefinitions();
		silver.support.monto.products.PdirectoryEntryDirectory.initProductionAttributeDefinitions();
		silver.support.monto.products.PdirectoryEntrySymlink.initProductionAttributeDefinitions();
		silver.support.monto.products.PdirectoryEntryOther.initProductionAttributeDefinitions();
		silver.support.monto.products.PhighlightingProduct.initProductionAttributeDefinitions();
		silver.support.monto.products.PhighlightToken.initProductionAttributeDefinitions();
		silver.support.monto.products.PcommentColor.initProductionAttributeDefinitions();
		silver.support.monto.products.PfunctionColor.initProductionAttributeDefinitions();
		silver.support.monto.products.PidentifierColor.initProductionAttributeDefinitions();
		silver.support.monto.products.PkeywordColor.initProductionAttributeDefinitions();
		silver.support.monto.products.PliteralColor.initProductionAttributeDefinitions();
		silver.support.monto.products.PpunctuationColor.initProductionAttributeDefinitions();
		silver.support.monto.products.PtypeColor.initProductionAttributeDefinitions();
		silver.support.monto.products.PpaletteColor.initProductionAttributeDefinitions();
		silver.support.monto.products.PsourceProduct.initProductionAttributeDefinitions();
		silver.support.monto.products.PerrorsProduct.initProductionAttributeDefinitions();
		silver.support.monto.products.PbyteRangeError.initProductionAttributeDefinitions();
		silver.support.monto.products.PmessageError.initProductionAttributeDefinitions();
		silver.support.monto.products.PseverityError.initProductionAttributeDefinitions();
		silver.support.monto.products.PseverityWarning.initProductionAttributeDefinitions();
		silver.support.monto.products.PseverityInfo.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__ProductValue = 0;
	public static int count_syn__ON__ProductValue = 0;
	public static int count_local__ON__silver_support_monto_products_productValue = 0;
	public static int count_inh__ON__Product = 0;
	public static int count_syn__ON__Product = 0;
	public static int count_local__ON__silver_support_monto_products_product = 0;
	public static int count_inh__ON__ProductDescriptor = 0;
	public static int count_syn__ON__ProductDescriptor = 0;
	public static int count_local__ON__silver_support_monto_products_productDescriptor = 0;
	public static int count_local__ON__silver_support_monto_products_productDescriptorEq = 0;
	public static int count_inh__ON__ProductIdentifier = 0;
	public static int count_syn__ON__ProductIdentifier = 0;
	public static int count_local__ON__silver_support_monto_products_productIdentifier = 0;
	public static int count_inh__ON__MetaItem = 0;
	public static int count_syn__ON__MetaItem = 0;
	public static int count_local__ON__silver_support_monto_products_metaItem = 0;
	public static int count_local__ON__silver_support_monto_products_metaDegraded = 0;
	public static int count_local__ON__silver_support_monto_products_directoryProduct = 0;
	public static int count_inh__ON__DirectoryEntry = 0;
	public static int count_syn__ON__DirectoryEntry = 0;
	public static int count_local__ON__silver_support_monto_products_directoryEntry = 0;
	public static int count_inh__ON__DirectoryEntryType = 0;
	public static int count_syn__ON__DirectoryEntryType = 0;
	public static int count_local__ON__silver_support_monto_products_directoryEntryFile = 0;
	public static int count_local__ON__silver_support_monto_products_directoryEntryDirectory = 0;
	public static int count_local__ON__silver_support_monto_products_directoryEntrySymlink = 0;
	public static int count_local__ON__silver_support_monto_products_directoryEntryOther = 0;
	public static int count_local__ON__silver_support_monto_products_highlightingProduct = 0;
	public static int count_inh__ON__HighlightToken = 0;
	public static int count_syn__ON__HighlightToken = 0;
	public static int count_local__ON__silver_support_monto_products_highlightToken = 0;
	public static int count_inh__ON__Color = 0;
	public static int count_syn__ON__Color = 0;
	public static int count_local__ON__silver_support_monto_products_commentColor = 0;
	public static int count_local__ON__silver_support_monto_products_functionColor = 0;
	public static int count_local__ON__silver_support_monto_products_identifierColor = 0;
	public static int count_local__ON__silver_support_monto_products_keywordColor = 0;
	public static int count_local__ON__silver_support_monto_products_literalColor = 0;
	public static int count_local__ON__silver_support_monto_products_punctuationColor = 0;
	public static int count_local__ON__silver_support_monto_products_typeColor = 0;
	public static int count_local__ON__silver_support_monto_products_paletteColor = 0;
	public static int count_local__ON__silver_support_monto_products_sourceProduct = 0;
	public static int count_local__ON__silver_support_monto_products_errorsProduct = 0;
	public static int count_inh__ON__Error = 0;
	public static int count_syn__ON__Error = 0;
	public static int count_local__ON__silver_support_monto_products_byteRangeError = 0;
	public static int count_local__ON__silver_support_monto_products_messageError = 0;
	public static int count_inh__ON__ErrorSeverity = 0;
	public static int count_syn__ON__ErrorSeverity = 0;
	public static int count_local__ON__silver_support_monto_products_severityError = 0;
	public static int count_local__ON__silver_support_monto_products_severityWarning = 0;
	public static int count_local__ON__silver_support_monto_products_severityInfo = 0;
public static final int silver_json_json__ON__silver_support_monto_products_ProductValue = silver.support.monto.products.Init.count_syn__ON__ProductValue++;
public static final int silver_support_monto_products_productName__ON__silver_support_monto_products_ProductValue = silver.support.monto.products.Init.count_syn__ON__ProductValue++;
public static final int silver_json_json__ON__silver_support_monto_products_Product = silver.support.monto.products.Init.count_syn__ON__Product++;
public static final int silver_support_monto_products_productName__ON__silver_support_monto_products_Product = silver.support.monto.products.Init.count_syn__ON__Product++;
public static final int silver_support_monto_products_productLang__ON__silver_support_monto_products_Product = silver.support.monto.products.Init.count_syn__ON__Product++;
public static final int silver_support_monto_products_productPath__ON__silver_support_monto_products_Product = silver.support.monto.products.Init.count_syn__ON__Product++;
public static final int silver_support_monto_products_productValue__ON__silver_support_monto_products_Product = silver.support.monto.products.Init.count_syn__ON__Product++;
public static final int silver_json_json__ON__silver_support_monto_products_ProductDescriptor = silver.support.monto.products.Init.count_syn__ON__ProductDescriptor++;
public static final int silver_support_monto_products_productName__ON__silver_support_monto_products_ProductDescriptor = silver.support.monto.products.Init.count_syn__ON__ProductDescriptor++;
public static final int silver_support_monto_products_productLang__ON__silver_support_monto_products_ProductDescriptor = silver.support.monto.products.Init.count_syn__ON__ProductDescriptor++;
public static final int silver_json_json__ON__silver_support_monto_products_ProductIdentifier = silver.support.monto.products.Init.count_syn__ON__ProductIdentifier++;
public static final int silver_support_monto_products_productName__ON__silver_support_monto_products_ProductIdentifier = silver.support.monto.products.Init.count_syn__ON__ProductIdentifier++;
public static final int silver_support_monto_products_productLang__ON__silver_support_monto_products_ProductIdentifier = silver.support.monto.products.Init.count_syn__ON__ProductIdentifier++;
public static final int silver_support_monto_products_productPath__ON__silver_support_monto_products_ProductIdentifier = silver.support.monto.products.Init.count_syn__ON__ProductIdentifier++;
public static final int silver_json_json__ON__silver_support_monto_products_MetaItem = silver.support.monto.products.Init.count_syn__ON__MetaItem++;
public static final int silver_json_json__ON__silver_support_monto_products_DirectoryEntry = silver.support.monto.products.Init.count_syn__ON__DirectoryEntry++;
public static final int obj__ON__silver_support_monto_products_directoryEntry = silver.support.monto.products.Init.count_local__ON__silver_support_monto_products_directoryEntry++;
public static final int silver_json_json__ON__silver_support_monto_products_DirectoryEntryType = silver.support.monto.products.Init.count_syn__ON__DirectoryEntryType++;
public static final int silver_json_json__ON__silver_support_monto_products_HighlightToken = silver.support.monto.products.Init.count_syn__ON__HighlightToken++;
public static final int obj__ON__silver_support_monto_products_highlightToken = silver.support.monto.products.Init.count_local__ON__silver_support_monto_products_highlightToken++;
public static final int silver_json_json__ON__silver_support_monto_products_Color = silver.support.monto.products.Init.count_syn__ON__Color++;
public static final int silver_json_json__ON__silver_support_monto_products_Error = silver.support.monto.products.Init.count_syn__ON__Error++;
public static final int obj__ON__silver_support_monto_products_byteRangeError = silver.support.monto.products.Init.count_local__ON__silver_support_monto_products_byteRangeError++;
public static final int severity__ON__silver_support_monto_products_messageError = silver.support.monto.products.Init.count_local__ON__silver_support_monto_products_messageError++;
public static final int obj__ON__silver_support_monto_products_messageError = silver.support.monto.products.Init.count_local__ON__silver_support_monto_products_messageError++;
public static final int silver_json_json__ON__silver_support_monto_products_ErrorSeverity = silver.support.monto.products.Init.count_syn__ON__ErrorSeverity++;
}
