package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.initAllStatics();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.initAllStatics();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.init();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.init();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.postInit();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.postInit();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
	}

}
