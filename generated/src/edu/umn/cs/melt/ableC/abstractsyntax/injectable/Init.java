package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.initAllStatics();
		silver.langutil.pp.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.init();
		silver.langutil.pp.Init.init();
		silver.langutil.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.postInit();
		silver.langutil.pp.Init.postInit();
		silver.langutil.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.postInit();


		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PeqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PmulEqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PdivEqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PmodEqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PaddEqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PsubEqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PlshEqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PrshEqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PandEqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PxorEqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PorEqExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PandExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PorExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PandBitExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PorBitExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PxorExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PlshExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PrshExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PequalsExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PnotEqualsExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PgtExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PltExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PgteExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PlteExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PaddExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PsubExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PmulExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PdivExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PmodExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.decorators, PconsLhsOrRhsRuntimeMod.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.decorators, PnilLhsOrRhsRuntimeMod.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.decorators, PlhsRuntimeMod.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.decorators, PrhsRuntimeMod.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.decorators, PconsRuntimeMod.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.decorators, PnilRuntimeMod.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.decorators, PruntimeCheck.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.decorators, PruntimeConversion.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.decorators, PruntimeInsertion.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PdereferenceExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PunaryOpExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, ParraySubscriptExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PmemberExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PexplicitCastExpr.class);
	}

	private static void setupInheritedAttributes(){
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PeqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:eqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PeqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PeqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:eqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PeqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PeqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PeqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:eqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PeqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:eqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PeqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:mulEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:mulEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:mulEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:mulEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:divEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:divEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:divEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:divEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:addEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:addEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:addEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:addEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:subEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:subEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:subEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:subEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lshEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lshEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lshEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lshEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rshEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rshEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rshEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rshEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:xorEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:xorEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:xorEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:xorEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andBitExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andBitExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandBitExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andBitExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:andBitExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orBitExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orBitExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorBitExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orBitExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:orBitExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:xorExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:xorExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:xorExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:xorExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lshExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lshExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lshExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lshExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rshExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rshExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rshExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rshExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PequalsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:equalsExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PequalsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PequalsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:equalsExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PequalsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PequalsExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PequalsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:equalsExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PequalsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:equalsExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PequalsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnotEqualsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:notEqualsExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnotEqualsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnotEqualsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:notEqualsExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnotEqualsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnotEqualsExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnotEqualsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:notEqualsExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnotEqualsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:notEqualsExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnotEqualsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgtExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:gtExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgtExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgtExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:gtExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgtExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgtExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgtExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:gtExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgtExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:gtExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgtExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PltExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:ltExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PltExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PltExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:ltExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PltExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PltExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PltExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:ltExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PltExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:ltExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PltExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:gteExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:gteExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgteExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:gteExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:gteExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lteExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lteExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlteExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lteExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lteExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:addExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:addExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:addExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:addExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:subExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:subExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:subExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:subExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:mulExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:mulExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:mulExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:mulExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:divExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:divExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:divExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:divExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modLhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.occurs_inh[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_lhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lhsToModify";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.decorators.add(edu.umn.cs.melt.ableC.abstractsyntax.injectable.DlhsToModify.singleton);
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.occurs_inh[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_rhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rhsToModify";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.decorators.add(edu.umn.cs.melt.ableC.abstractsyntax.injectable.DrhsToModify.singleton);
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpLhsName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyLhsRhsMods:local:tmpLhsName";
		//	local attribute tmpLhs::Expr;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyLhsRhsMods:local:tmpLhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpRhsName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyLhsRhsMods:local:tmpRhsName";
		//	local attribute tmpRhs::Expr;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyLhsRhsMods:local:tmpRhs";
		//	local attribute mods::LhsOrRhsRuntimeMods;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyLhsRhsMods:local:mods";
		//	local attribute modLhs::Expr;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyLhsRhsMods:local:modLhs";
		//	local attribute modRhs::Expr;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyLhsRhsMods:local:modRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modLhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_isLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:isLhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.occurs_inh[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_lhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:lhsToModify";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.decorators.add(edu.umn.cs.melt.ableC.abstractsyntax.injectable.DlhsToModify.singleton);
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.occurs_inh[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_rhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rhsToModify";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.decorators.add(edu.umn.cs.melt.ableC.abstractsyntax.injectable.DrhsToModify.singleton);
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modExpr";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.occurs_inh[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:exprToModify";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.decorators.add(edu.umn.cs.melt.ableC.abstractsyntax.injectable.DexprToModify.singleton);
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyMods:local:tmpName";
		//	local attribute tmp::Expr;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmp__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmp__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyMods:local:tmp";
		//	local attribute mods::RuntimeMods;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyMods:local:mods";
		//	local attribute modExpr::Expr;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyMods:local:modExpr";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:modExpr";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.occurs_inh[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:exprToModify";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.decorators.add(edu.umn.cs.melt.ableC.abstractsyntax.injectable.DexprToModify.singleton);
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdereferenceExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:dereferenceExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdereferenceExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdereferenceExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:dereferenceExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdereferenceExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdereferenceExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:dereferenceExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdereferenceExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PunaryOpExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:unaryOpExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PunaryOpExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PunaryOpExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:unaryOpExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PunaryOpExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PunaryOpExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:unaryOpExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PunaryOpExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.ParraySubscriptExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:arraySubscriptExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.ParraySubscriptExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.ParraySubscriptExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:arraySubscriptExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.ParraySubscriptExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.ParraySubscriptExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.ParraySubscriptExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:arraySubscriptExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.ParraySubscriptExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:arraySubscriptExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.ParraySubscriptExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmemberExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:memberExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmemberExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmemberExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:memberExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmemberExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmemberExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:memberExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmemberExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PexplicitCastExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:explicitCastExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PexplicitCastExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PexplicitCastExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:explicitCastExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PexplicitCastExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PexplicitCastExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:injectable:explicitCastExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PexplicitCastExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
	}

	private static void initProductionAttributeDefinitions(){
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PeqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandBitExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PorBitExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PxorExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlshExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrshExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PequalsExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnotEqualsExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgtExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PltExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PgteExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlteExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PaddExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PsubExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmulExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdivExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmodExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnilLhsOrRhsRuntimeMod.initProductionAttributeDefinitions();
		//FUNCTION applyLhsRhsMods Pair<Expr Expr> ::= l::[LhsOrRhsRuntimeMod] lhs::Decorated Expr rhs::Decorated Expr 
		// tmpLhsName = "_tmpLhs" ++ toString(genInt(,))
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpLhsName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("_tmpLhs")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke())))); } };
		// tmpLhs = declRefExpr(name(tmpLhsName,location=lhs.location),location=lhs.location)
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdeclRefExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpLhsName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_lhs)).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_lhs)).undecorate()).getAnno_core_location()); } })); } };
		// tmpRhsName = "_tmpRhs" ++ toString(genInt(,))
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpRhsName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("_tmpRhs")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke())))); } };
		// tmpRhs = declRefExpr(name(tmpRhsName,location=rhs.location),location=rhs.location)
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdeclRefExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpRhsName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_rhs)).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_rhs)).undecorate()).getAnno_core_location()); } })); } };
		// mods = foldr(consLhsOrRhsRuntimeMod, nilLhsOrRhsRuntimeMod(,), l,)
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods)core.Pfoldr.invoke(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods)new edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnilLhsOrRhsRuntimeMod()); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_l))); } };
		// mods.lhsToModify = tmpLhs
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods][edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_lhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods).undecorate(); } };
		// mods.rhsToModify = tmpRhs
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods][edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_rhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods).undecorate(); } };
		// modLhs = if null(filter((.isLhs), l,),) then new(lhs) else stmtExpr(mkDecl(tmpLhsName, lhs.typerep, new(lhs), lhs.location,), mods.modLhs,location=lhs.location)
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfilter.invoke(new common.AttributeSection.Undecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_isLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod, context), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_l))); } })) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_lhs)).undecorate()) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstmtExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDecl.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpLhsName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_lhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_lhs)).undecorate()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_lhs)).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_lhs)).undecorate()).getAnno_core_location()); } }))); } };
		// modRhs = if null(filter(\ m::LhsOrRhsRuntimeMod  -> ! m.isLhs, l,),) then new(rhs) else stmtExpr(mkDecl(tmpRhsName, rhs.typerep, new(rhs), rhs.location,), mods.modRhs,location=rhs.location)
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfilter.invoke((new common.NodeFactory<Boolean>() {
  public final Boolean invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_12233_m = args[0];

    return (!((Boolean)((edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod)common.Util.demand(__SV_LAMBDA_PARAM_12233_m)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_isLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod)));
  }
}), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_l))); } })) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_rhs)).undecorate()) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstmtExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDecl.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpRhsName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_rhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_rhs)).undecorate()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_rhs)).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.i_rhs)).undecorate()).getAnno_core_location()); } }))); } };
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PlhsRuntimeMod.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrhsRuntimeMod.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsRuntimeMod.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnilRuntimeMod.initProductionAttributeDefinitions();
		//FUNCTION applyMods Expr ::= l::[RuntimeMod] e::Decorated Expr 
		// tmpName = "_tmp" ++ toString(genInt(,))
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("_tmp")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke())))); } };
		// tmp = declRefExpr(name(tmpName,location=e.location),location=e.location)
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmp__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdeclRefExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.i_e)).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.i_e)).undecorate()).getAnno_core_location()); } })); } };
		// mods = foldr(consRuntimeMod, nilRuntimeMod(,), l,)
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods)core.Pfoldr.invoke(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsRuntimeMod.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods)new edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnilRuntimeMod()); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.i_l))); } };
		// mods.exprToModify = tmp
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods][edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmp__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods).undecorate(); } };
		// modExpr = if null(l,) then new(e) else stmtExpr(mkDecl(tmpName, e.typerep, new(e), e.location,), mods.modExpr,location=e.location)
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.i_l))) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.i_e)).undecorate()) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstmtExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDecl.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.i_e, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.i_e)).undecorate()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.i_e)).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMods)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyMods.i_e)).undecorate()).getAnno_core_location()); } }))); } };
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeCheck.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeConversion.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeInsertion.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PdereferenceExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PunaryOpExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.ParraySubscriptExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmemberExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PexplicitCastExpr.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr = 0;
	public static int count_inh__ON__LhsOrRhsRuntimeMods = 0;
	public static int count_syn__ON__LhsOrRhsRuntimeMods = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_consLhsOrRhsRuntimeMod = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_nilLhsOrRhsRuntimeMod = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods = 0;
	public static int count_inh__ON__LhsOrRhsRuntimeMod = 0;
	public static int count_syn__ON__LhsOrRhsRuntimeMod = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lhsRuntimeMod = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rhsRuntimeMod = 0;
	public static int count_inh__ON__RuntimeMods = 0;
	public static int count_syn__ON__RuntimeMods = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_consRuntimeMod = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_nilRuntimeMod = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods = 0;
	public static int count_inh__ON__RuntimeMod = 0;
	public static int count_syn__ON__RuntimeMod = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_runtimeCheck = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_runtimeConversion = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_runtimeInsertion = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr = 0;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_eqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_andBitExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_orBitExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_xorExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lshExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rshExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_equalsExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_notEqualsExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gtExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_ltExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_gteExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_lteExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_addExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_subExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_mulExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_divExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_syn__ON__LhsOrRhsRuntimeMods++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_syn__ON__LhsOrRhsRuntimeMods++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_lhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_inh__ON__LhsOrRhsRuntimeMods++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_rhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_inh__ON__LhsOrRhsRuntimeMods++;
public static final int tmpLhsName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods++;
public static final int tmpLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods++;
public static final int tmpRhsName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods++;
public static final int tmpRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods++;
public static final int mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods++;
public static final int modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods++;
public static final int modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_syn__ON__LhsOrRhsRuntimeMod++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_syn__ON__LhsOrRhsRuntimeMod++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_isLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_syn__ON__LhsOrRhsRuntimeMod++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_lhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_inh__ON__LhsOrRhsRuntimeMod++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_rhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_inh__ON__LhsOrRhsRuntimeMod++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_syn__ON__RuntimeMods++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_inh__ON__RuntimeMods++;
public static final int tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods++;
public static final int tmp__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods++;
public static final int mods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods++;
public static final int modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_syn__ON__RuntimeMod++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_inh__ON__RuntimeMod++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_dereferenceExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_unaryOpExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_arraySubscriptExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_memberExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr = edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_explicitCastExpr++;
}
