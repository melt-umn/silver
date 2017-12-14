package silver.support.monto.negotiation;

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


		common.Decorator.applyDecorators(silver.support.monto.negotiation.NProtocolVersion.decorators, PprotocolVersion.class);
		common.Decorator.applyDecorators(silver.support.monto.negotiation.NSoftwareVersion.decorators, PsoftwareVersion.class);
		common.Decorator.applyDecorators(silver.support.monto.negotiation.NServiceBrokerNegotiation.decorators, PserviceBrokerNegotiation.class);
		common.Decorator.applyDecorators(silver.support.monto.negotiation.NServiceNegotiation.decorators, PserviceNegotiation.class);
	}

	private static void setupInheritedAttributes(){
		silver.support.monto.negotiation.NProtocolVersion.occurs_syn[silver.support.monto.negotiation.Init.silver_support_monto_negotiation_major__ON__silver_support_monto_negotiation_ProtocolVersion] = "silver:support:monto:negotiation:major";
		silver.support.monto.negotiation.NProtocolVersion.occurs_syn[silver.support.monto.negotiation.Init.silver_support_monto_negotiation_minor__ON__silver_support_monto_negotiation_ProtocolVersion] = "silver:support:monto:negotiation:minor";
		silver.support.monto.negotiation.NProtocolVersion.occurs_syn[silver.support.monto.negotiation.Init.silver_support_monto_negotiation_patch__ON__silver_support_monto_negotiation_ProtocolVersion] = "silver:support:monto:negotiation:patch";
		silver.support.monto.negotiation.NProtocolVersion.occurs_syn[silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_ProtocolVersion] = "silver:json:json";
		silver.support.monto.negotiation.NSoftwareVersion.occurs_syn[silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_SoftwareVersion] = "silver:json:json";
		silver.support.monto.negotiation.PsoftwareVersion.occurs_local[silver.support.monto.negotiation.Init.opts__ON__silver_support_monto_negotiation_softwareVersion] = "silver:support:monto:negotiation:softwareVersion:local:opts";
		silver.support.monto.negotiation.NServiceBrokerNegotiation.occurs_syn[silver.support.monto.negotiation.Init.silver_support_monto_negotiation_montoVersion__ON__silver_support_monto_negotiation_ServiceBrokerNegotiation] = "silver:support:monto:negotiation:montoVersion";
		silver.support.monto.negotiation.NServiceBrokerNegotiation.occurs_syn[silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_ServiceBrokerNegotiation] = "silver:json:json";
		silver.support.monto.negotiation.NServiceNegotiation.occurs_syn[silver.support.monto.negotiation.Init.silver_support_monto_negotiation_montoVersion__ON__silver_support_monto_negotiation_ServiceNegotiation] = "silver:support:monto:negotiation:montoVersion";
		silver.support.monto.negotiation.NServiceNegotiation.occurs_syn[silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_ServiceNegotiation] = "silver:json:json";
	}

	private static void initProductionAttributeDefinitions(){
		silver.support.monto.negotiation.PprotocolVersion.initProductionAttributeDefinitions();
		silver.support.monto.negotiation.PsoftwareVersion.initProductionAttributeDefinitions();
		//FUNCTION pairMaybe Maybe<Pair<a c>> ::= a::a f::(c ::= b) b::Maybe<b> 
		//FUNCTION negotiationsCompatible Boolean ::= us::ServiceNegotiation them::ServiceBrokerNegotiation 
		//FUNCTION semverCompatible Boolean ::= us::ProtocolVersion them::ProtocolVersion 
		silver.support.monto.negotiation.PserviceBrokerNegotiation.initProductionAttributeDefinitions();
		silver.support.monto.negotiation.PserviceNegotiation.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__ProtocolVersion = 0;
	public static int count_syn__ON__ProtocolVersion = 0;
	public static int count_local__ON__silver_support_monto_negotiation_protocolVersion = 0;
	public static int count_inh__ON__SoftwareVersion = 0;
	public static int count_syn__ON__SoftwareVersion = 0;
	public static int count_local__ON__silver_support_monto_negotiation_softwareVersion = 0;
	public static int count_local__ON__silver_support_monto_negotiation_pairMaybe = 0;
	public static int count_local__ON__silver_support_monto_negotiation_negotiationsCompatible = 0;
	public static int count_local__ON__silver_support_monto_negotiation_semverCompatible = 0;
	public static int count_inh__ON__ServiceBrokerNegotiation = 0;
	public static int count_syn__ON__ServiceBrokerNegotiation = 0;
	public static int count_local__ON__silver_support_monto_negotiation_serviceBrokerNegotiation = 0;
	public static int count_inh__ON__ServiceNegotiation = 0;
	public static int count_syn__ON__ServiceNegotiation = 0;
	public static int count_local__ON__silver_support_monto_negotiation_serviceNegotiation = 0;
public static final int silver_support_monto_negotiation_major__ON__silver_support_monto_negotiation_ProtocolVersion = silver.support.monto.negotiation.Init.count_syn__ON__ProtocolVersion++;
public static final int silver_support_monto_negotiation_minor__ON__silver_support_monto_negotiation_ProtocolVersion = silver.support.monto.negotiation.Init.count_syn__ON__ProtocolVersion++;
public static final int silver_support_monto_negotiation_patch__ON__silver_support_monto_negotiation_ProtocolVersion = silver.support.monto.negotiation.Init.count_syn__ON__ProtocolVersion++;
public static final int silver_json_json__ON__silver_support_monto_negotiation_ProtocolVersion = silver.support.monto.negotiation.Init.count_syn__ON__ProtocolVersion++;
public static final int silver_json_json__ON__silver_support_monto_negotiation_SoftwareVersion = silver.support.monto.negotiation.Init.count_syn__ON__SoftwareVersion++;
public static final int opts__ON__silver_support_monto_negotiation_softwareVersion = silver.support.monto.negotiation.Init.count_local__ON__silver_support_monto_negotiation_softwareVersion++;
public static final int silver_support_monto_negotiation_montoVersion__ON__silver_support_monto_negotiation_ServiceBrokerNegotiation = silver.support.monto.negotiation.Init.count_syn__ON__ServiceBrokerNegotiation++;
public static final int silver_json_json__ON__silver_support_monto_negotiation_ServiceBrokerNegotiation = silver.support.monto.negotiation.Init.count_syn__ON__ServiceBrokerNegotiation++;
public static final int silver_support_monto_negotiation_montoVersion__ON__silver_support_monto_negotiation_ServiceNegotiation = silver.support.monto.negotiation.Init.count_syn__ON__ServiceNegotiation++;
public static final int silver_json_json__ON__silver_support_monto_negotiation_ServiceNegotiation = silver.support.monto.negotiation.Init.count_syn__ON__ServiceNegotiation++;
}
