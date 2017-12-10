package silver.support.monto;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.support.monto.products.Init.initAllStatics();
		silver.json.Init.initAllStatics();
		silver.support.monto.negotiation.Init.initAllStatics();
		silver.support.monto.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.support.monto.products.Init.init();
		silver.json.Init.init();
		silver.support.monto.negotiation.Init.init();
		silver.support.monto.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.support.monto.products.Init.postInit();
		silver.json.Init.postInit();
		silver.support.monto.negotiation.Init.postInit();
		silver.support.monto.Init.postInit();


		common.Decorator.applyDecorators(silver.support.monto.NBrokerRequest.decorators, PbrokerRequest.class);
		common.Decorator.applyDecorators(silver.support.monto.NServiceError.decorators, PerrorUnmetDependency.class);
		common.Decorator.applyDecorators(silver.support.monto.NServiceError.decorators, PerrorOther.class);
		common.Decorator.applyDecorators(silver.support.monto.NService.decorators, Pservice.class);
		common.Decorator.applyDecorators(silver.support.monto.NServiceProvider.decorators, PserviceProvider.class);
		common.Decorator.applyDecorators(silver.support.monto.NServiceNotice.decorators, PnoticeUnusedDependency.class);
	}

	private static void setupInheritedAttributes(){
		silver.support.monto.NBrokerRequest.occurs_syn[silver.support.monto.Init.silver_json_json__ON__silver_support_monto_BrokerRequest] = "silver:json:json";
		silver.support.monto.NBrokerRequest.occurs_syn[silver.support.monto.Init.silver_support_monto_requestIdentifier__ON__silver_support_monto_BrokerRequest] = "silver:support:monto:requestIdentifier";
		silver.support.monto.NBrokerRequest.occurs_syn[silver.support.monto.Init.silver_support_monto_requestProducts__ON__silver_support_monto_BrokerRequest] = "silver:support:monto:requestProducts";
		silver.support.monto.NServiceError.occurs_syn[silver.support.monto.Init.silver_json_json__ON__silver_support_monto_ServiceError] = "silver:json:json";
		silver.support.monto.NService.occurs_syn[silver.support.monto.Init.silver_support_monto_doNegotiation__ON__silver_support_monto_Service] = "silver:support:monto:doNegotiation";
		silver.support.monto.NService.occurs_syn[silver.support.monto.Init.silver_support_monto_onRequest__ON__silver_support_monto_Service] = "silver:support:monto:onRequest";
		silver.support.monto.NServiceProvider.occurs_syn[silver.support.monto.Init.silver_support_monto_descriptor__ON__silver_support_monto_ServiceProvider] = "silver:support:monto:descriptor";
		silver.support.monto.NServiceProvider.occurs_syn[silver.support.monto.Init.silver_support_monto_processService__ON__silver_support_monto_ServiceProvider] = "silver:support:monto:processService";
		silver.support.monto.NServiceNotice.occurs_syn[silver.support.monto.Init.silver_json_json__ON__silver_support_monto_ServiceNotice] = "silver:json:json";
	}

	private static void initProductionAttributeDefinitions(){
		silver.support.monto.PbrokerRequest.initProductionAttributeDefinitions();
		silver.support.monto.PerrorUnmetDependency.initProductionAttributeDefinitions();
		silver.support.monto.PerrorOther.initProductionAttributeDefinitions();
		silver.support.monto.Pservice.initProductionAttributeDefinitions();
		//FUNCTION asDescriptor ProductDescriptor ::= pi::ProductIdentifier 
		silver.support.monto.PserviceProvider.initProductionAttributeDefinitions();
		silver.support.monto.PnoticeUnusedDependency.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__BrokerRequest = 0;
	public static int count_syn__ON__BrokerRequest = 0;
	public static int count_local__ON__silver_support_monto_brokerRequest = 0;
	public static int count_inh__ON__ServiceError = 0;
	public static int count_syn__ON__ServiceError = 0;
	public static int count_local__ON__silver_support_monto_errorUnmetDependency = 0;
	public static int count_local__ON__silver_support_monto_errorOther = 0;
	public static int count_local__ON__silver_support_monto_runService = 0;
	public static int count_inh__ON__Service = 0;
	public static int count_syn__ON__Service = 0;
	public static int count_local__ON__silver_support_monto_service = 0;
	public static int count_local__ON__silver_support_monto_asDescriptor = 0;
	public static int count_inh__ON__ServiceProvider = 0;
	public static int count_syn__ON__ServiceProvider = 0;
	public static int count_local__ON__silver_support_monto_serviceProvider = 0;
	public static int count_inh__ON__ServiceNotice = 0;
	public static int count_syn__ON__ServiceNotice = 0;
	public static int count_local__ON__silver_support_monto_noticeUnusedDependency = 0;
public static final int silver_json_json__ON__silver_support_monto_BrokerRequest = silver.support.monto.Init.count_syn__ON__BrokerRequest++;
public static final int silver_support_monto_requestIdentifier__ON__silver_support_monto_BrokerRequest = silver.support.monto.Init.count_syn__ON__BrokerRequest++;
public static final int silver_support_monto_requestProducts__ON__silver_support_monto_BrokerRequest = silver.support.monto.Init.count_syn__ON__BrokerRequest++;
public static final int silver_json_json__ON__silver_support_monto_ServiceError = silver.support.monto.Init.count_syn__ON__ServiceError++;
public static final int silver_support_monto_doNegotiation__ON__silver_support_monto_Service = silver.support.monto.Init.count_syn__ON__Service++;
public static final int silver_support_monto_onRequest__ON__silver_support_monto_Service = silver.support.monto.Init.count_syn__ON__Service++;
public static final int silver_support_monto_descriptor__ON__silver_support_monto_ServiceProvider = silver.support.monto.Init.count_syn__ON__ServiceProvider++;
public static final int silver_support_monto_processService__ON__silver_support_monto_ServiceProvider = silver.support.monto.Init.count_syn__ON__ServiceProvider++;
public static final int silver_json_json__ON__silver_support_monto_ServiceNotice = silver.support.monto.Init.count_syn__ON__ServiceNotice++;
}
