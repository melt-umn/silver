package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.initAllStatics();
		silver.langutil.pp.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.init();
		silver.langutil.pp.Init.init();
		silver.langutil.Init.init();
		core.monad.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.postInit();
		silver.langutil.pp.Init.postInit();
		silver.langutil.Init.postInit();
		core.monad.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.postInit();


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
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PunaryOpExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PdereferenceExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PexplicitCastExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, ParraySubscriptExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PcallExpr.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.decorators, PmemberExpr.class);
	}

	private static void setupInheritedAttributes(){
		edu.umn.cs.melt.ableC.abstractsyntax.host.NUnaryOp.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:unaryProd";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetArraySubscriptOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getArraySubscriptOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getArraySubscriptOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetArraySubscriptOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getArraySubscriptOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberCallOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberCallOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getMemberCallOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberCallOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberCallOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetCallOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getCallOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getCallOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetCallOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getCallOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getMemberOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubscriptAssignOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubscriptAssignOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getSubscriptAssignOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubscriptAssignOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubscriptAssignOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberAssignOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberAssignOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getMemberAssignOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberAssignOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberAssignOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDereferenceOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getDereferenceOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDereferenceOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPreIncOpOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreIncOpOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getPreIncOpOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPreIncOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreIncOpOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPreDecOpOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreDecOpOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getPreDecOpOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPreDecOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreDecOpOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPostIncOpOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostIncOpOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getPostIncOpOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPostIncOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostIncOpOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPostDecOpOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostDecOpOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getPostDecOpOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPostDecOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostDecOpOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddressOfOpOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddressOfOpOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAddressOfOpOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddressOfOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddressOfOpOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDereferenceOpOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOpOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getDereferenceOpOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDereferenceOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOpOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPositiveOpOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPositiveOpOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getPositiveOpOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPositiveOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPositiveOpOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNegativeOpOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNegativeOpOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getNegativeOpOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNegativeOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNegativeOpOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBitNegateOpOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBitNegateOpOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getBitNegateOpOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBitNegateOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBitNegateOpOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotOpOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotOpOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getNotOpOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotOpOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getMulEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getMulEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getMulEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getDivEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getDivEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getDivEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getModEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getModEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getModEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAddEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAddEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAddEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getSubEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getSubEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getSubEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLshEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLshEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLshEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getRshEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getRshEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getRshEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAndEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAndEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAndEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getXorEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getXorEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getXorEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getOrEqOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getOrEqOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrEqOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getOrEqOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAndOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAndOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAndOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getOrOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getOrOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getOrOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndBitOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAndBitOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndBitOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAndBitOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndBitOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAndBitOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrBitOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getOrBitOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrBitOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getOrBitOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrBitOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getOrBitOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getXorOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getXorOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getXorOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLshOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLshOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLshOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getRshOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getRshOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getRshOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqualsOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getEqualsOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqualsOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getEqualsOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqualsOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getEqualsOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotEqualsOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getNotEqualsOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotEqualsOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getNotEqualsOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotEqualsOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getNotEqualsOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGtOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getGtOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGtOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getGtOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGtOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getGtOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLtOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLtOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLtOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLtOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLtOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLtOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGteOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getGteOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGteOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getGteOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGteOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getGteOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLteOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLteOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLteOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLteOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLteOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getLteOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAddOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAddOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAddOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getSubOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getSubOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getSubOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getMulOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getMulOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getMulOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getDivOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getDivOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getDivOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getModOverload:local:overloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getModOverload:local:lOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getModOverload:local:rOverloads";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute lModuleName::Maybe<String>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getBinaryOverload:local:lModuleName";
		//	local attribute rModuleName::Maybe<String>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getBinaryOverload:local:rModuleName";
		//	local attribute option1::Maybe<(Expr ::= Expr Expr Location)>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getBinaryOverload:local:option1";
		//	local attribute option2::Maybe<(Expr ::= Expr Expr Location)>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getBinaryOverload:local:option2";
		//	local attribute option3::Maybe<(Expr ::= Expr Expr Location)>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option3__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option3__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getBinaryOverload:local:option3";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:eqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:eqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:eqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:eqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:eqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:mulEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:mulEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:mulEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:mulEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:mulEqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:divEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:divEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:divEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:divEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:divEqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:modEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:modEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:modEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:modEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:modEqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:addEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:addEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:addEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:addEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:addEqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:subEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:subEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:subEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:subEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:subEqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lshEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lshEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lshEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lshEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lshEqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:rshEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:rshEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:rshEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:rshEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:rshEqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andEqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:xorEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:xorEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:xorEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:xorEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:xorEqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orEqExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orEqExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orEqExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orEqExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orEqExpr:local:option1";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_expandAssign] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:expandAssign:local:tmpName";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andBitExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andBitExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandBitExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andBitExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andBitExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orBitExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orBitExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorBitExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orBitExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorBitExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:orBitExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorBitExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:xorExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:xorExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:xorExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:xorExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lshExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lshExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lshExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lshExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:rshExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:rshExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:rshExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:rshExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PequalsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:equalsExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PequalsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PequalsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:equalsExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PequalsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PequalsExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PequalsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:equalsExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PequalsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:equalsExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PequalsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PnotEqualsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:notEqualsExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PnotEqualsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PnotEqualsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:notEqualsExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PnotEqualsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PnotEqualsExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PnotEqualsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:notEqualsExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PnotEqualsExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:notEqualsExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PnotEqualsExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgtExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:gtExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgtExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgtExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:gtExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgtExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgtExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgtExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:gtExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgtExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:gtExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgtExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PltExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:ltExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PltExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PltExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:ltExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PltExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PltExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PltExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:ltExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PltExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:ltExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PltExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:gteExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:gteExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgteExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:gteExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:gteExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lteExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lteExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlteExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lteExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlteExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:lteExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlteExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:addExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:addExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:addExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:addExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:subExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:subExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:subExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:subExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:mulExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:mulExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:mulExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:mulExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:divExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:divExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:divExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:divExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:modExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:modExpr:local:runtimeMods";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute modLhsRhs::Pair<host:Expr host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:modExpr:local:modLhsRhs";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:modExpr:local:injectedQualifiers";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PunaryOpExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryOpExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:unaryOpExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PunaryOpExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryOpExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdereferenceExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_dereferenceExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:dereferenceExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdereferenceExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_dereferenceExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexplicitCastExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_explicitCastExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:explicitCastExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexplicitCastExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_explicitCastExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.ParraySubscriptExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_arraySubscriptExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:arraySubscriptExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.ParraySubscriptExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_arraySubscriptExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute option1::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:callExpr:local:option1";
		//	local attribute option2::Maybe<host:Expr>;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr] = new common.Lazy[core.NMaybe.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:callExpr:local:option2";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:memberExpr:local:lerrors";
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		//	local attribute ty::host:Type;
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.ty__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.ty__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:memberExpr:local:ty";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION getArraySubscriptOverload Maybe<(Expr ::= Expr Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetArraySubscriptOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getArraySubscriptOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getMemberCallOverload Maybe<(Expr ::= Expr Boolean Name Exprs Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberCallOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberCallOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getCallOverload Maybe<(Expr ::= Expr Exprs Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetCallOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getCallOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getMemberOverload Maybe<(Expr ::= Expr Boolean Name Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getSubscriptAssignOverload Maybe<(Expr ::= Expr Expr (Expr ::= Expr Expr Location) Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubscriptAssignOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubscriptAssignOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getMemberAssignOverload Maybe<(Expr ::= Expr Boolean Name (Expr ::= Expr Expr Location) Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberAssignOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberAssignOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getDereferenceOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDereferenceOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getPreIncOpOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPreIncOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreIncOpOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getPreDecOpOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPreDecOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreDecOpOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getPostIncOpOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPostIncOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostIncOpOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getPostDecOpOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPostDecOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostDecOpOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getAddressOfOpOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddressOfOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddressOfOpOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getDereferenceOpOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDereferenceOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOpOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getPositiveOpOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPositiveOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPositiveOpOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getNegativeOpOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNegativeOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNegativeOpOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getBitNegateOpOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBitNegateOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBitNegateOpOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getNotOpOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotOpOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotOpOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getMulEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getDivEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getModEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getAddEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getSubEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getLshEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getRshEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getAndEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getXorEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getOrEqOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrEqOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getAndOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getOrOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getAndBitOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getOrBitOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetOrBitOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getXorOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetXorOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getLshOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getRshOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetRshOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getEqualsOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getNotEqualsOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotEqualsOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getGtOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getLtOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLtOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getGteOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetGteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getLteOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetLteOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getAddOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getSubOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getMulOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMulOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getDivOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetDivOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getModOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env 
		// overloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// lOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// rOverloads := []
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetModOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//FUNCTION getUnaryOverload Maybe<(Expr ::= Expr Location)> ::= t::Type env::Decorated Env overloads::[Pair<String (Expr ::= Expr Location)>] 
		//FUNCTION getBinaryOverload Maybe<(Expr ::= Expr Expr Location)> ::= l::Type r::Type env::Decorated Env overloads::[Pair<Pair<String String> (Expr ::= Expr Expr Location)>] lOverloads::[Pair<String (Expr ::= Expr Expr Location)>] rOverloads::[Pair<String (Expr ::= Expr Expr Location)>] 
		// lModuleName = moduleName(env, l,)
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.host.PmoduleName.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.i_env), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.i_l)))); } };
		// rModuleName = moduleName(env, r,)
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.host.PmoduleName.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.i_env), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.i_r)))); } };
		// option1 = do (bindMaybe, returnMaybe) {n1::String <- lModuleName; n2::String <- rModuleName; prod::(Expr ::= Expr Expr Location) <- lookupBy(stringPairEq, pair(n1, n2,), overloads,); return prod;}
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.monad.PbindMaybe.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload)), (new common.NodeFactory<core.NMaybe>() {
  public final core.NMaybe invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_892_n1 = args[0];

    return ((core.NMaybe)core.monad.PbindMaybe.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload)), (new common.NodeFactory<core.NMaybe>() {
  public final core.NMaybe invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_893_n2 = args[0];

    return ((core.NMaybe)core.monad.PbindMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)core.PlookupBy.invoke(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PstringPairEq.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_892_n1)), ((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_893_n2)))); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.i_overloads))); } }, (new common.NodeFactory<core.NMaybe>() {
  public final core.NMaybe invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_894_prod = args[0];

    return ((core.NMaybe)core.monad.PreturnMaybe.invoke(((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)common.Util.demand(__SV_LAMBDA_PARAM_894_prod))));
  }
})));
  }
})));
  }
}))); } };
		// option2 = do (bindMaybe, returnMaybe) {n::String <- lModuleName; prod::(Expr ::= Expr Expr Location) <- lookupBy(stringEq, n, lOverloads,); return prod;}
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.monad.PbindMaybe.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload)), (new common.NodeFactory<core.NMaybe>() {
  public final core.NMaybe invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_895_n = args[0];

    return ((core.NMaybe)core.monad.PbindMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)core.PlookupBy.invoke(core.PstringEq.factory, ((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_895_n)), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.i_lOverloads))); } }, (new common.NodeFactory<core.NMaybe>() {
  public final core.NMaybe invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_896_prod = args[0];

    return ((core.NMaybe)core.monad.PreturnMaybe.invoke(((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)common.Util.demand(__SV_LAMBDA_PARAM_896_prod))));
  }
})));
  }
}))); } };
		// option3 = do (bindMaybe, returnMaybe) {n::String <- rModuleName; prod::(Expr ::= Expr Expr Location) <- lookupBy(stringEq, n, rOverloads,); return prod;}
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option3__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.monad.PbindMaybe.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload)), (new common.NodeFactory<core.NMaybe>() {
  public final core.NMaybe invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_897_n = args[0];

    return ((core.NMaybe)core.monad.PbindMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)core.PlookupBy.invoke(core.PstringEq.factory, ((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_897_n)), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.i_rOverloads))); } }, (new common.NodeFactory<core.NMaybe>() {
  public final core.NMaybe invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_898_prod = args[0];

    return ((core.NMaybe)core.monad.PreturnMaybe.invoke(((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)common.Util.demand(__SV_LAMBDA_PARAM_898_prod))));
  }
})));
  }
}))); } };
		//FUNCTION stringPairEq Boolean ::= p1::Pair<String String> p2::Pair<String String> 
		//FUNCTION applyMaybe Maybe<a> ::= maybeFn::Maybe<(a ::= b)> a::b 
		//FUNCTION applyMaybe2 Maybe<a> ::= maybeFn::Maybe<(a ::= b c)> a1::b a2::c 
		//FUNCTION applyMaybe3 Maybe<a> ::= maybeFn::Maybe<(a ::= b c d)> a1::b a2::c a3::d 
		//FUNCTION applyMaybe4 Maybe<a> ::= maybeFn::Maybe<(a ::= b c d e)> a1::b a2::c a3::d a4::e 
		//FUNCTION applyMaybe5 Maybe<a> ::= maybeFn::Maybe<(a ::= b c d e f)> a1::b a2::c a3::d a4::e a5::f 
		//FUNCTION applyMaybe6 Maybe<a> ::= maybeFn::Maybe<(a ::= b c d e f g)> a1::b a2::c a3::d a4::e a5::f a6::g 
		//FUNCTION applyMaybe7 Maybe<a> ::= maybeFn::Maybe<(a ::= b c d e f g h)> a1::b a2::c a3::d a4::e a5::f a6::g a7::h 
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PeqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorEqExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorEqExpr.initProductionAttributeDefinitions();
		//FUNCTION expandAssign host:Expr ::= lhs::host:Expr rhs::host:Expr ty::host:Type prod::(host:Expr ::= host:Expr host:Expr Location) loc::Location 
		// tmpName = "_tmp" ++ toString(genInt(,))
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_expandAssign] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("_tmp")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke())))); } };
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandBitExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PorBitExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PxorExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlshExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PrshExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PequalsExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PnotEqualsExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgtExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PltExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgteExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PlteExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PaddExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PsubExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmulExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdivExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmodExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PunaryOpExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PdereferenceExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexplicitCastExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.ParraySubscriptExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for UnaryOp
		// top.unaryProd = nothing(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.NUnaryOp.defaultSynthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		//ASPECT PRODUCTION preIncOp top ::= 
		// top.unaryProd = getPreIncOpOverload(top.op.typerep, top.op.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPreIncOpOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } })); } };
		//ASPECT PRODUCTION preDecOp top ::= 
		// top.unaryProd = getPreDecOpOverload(top.op.typerep, top.op.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpreDecOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPreDecOpOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } })); } };
		//ASPECT PRODUCTION postIncOp top ::= 
		// top.unaryProd = getPostIncOpOverload(top.op.typerep, top.op.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpostIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPostIncOpOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } })); } };
		//ASPECT PRODUCTION postDecOp top ::= 
		// top.unaryProd = getPostDecOpOverload(top.op.typerep, top.op.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpostDecOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPostDecOpOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } })); } };
		//ASPECT PRODUCTION addressOfOp top ::= 
		// top.unaryProd = getAddressOfOpOverload(top.op.typerep, top.op.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PaddressOfOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddressOfOpOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } })); } };
		//ASPECT PRODUCTION positiveOp top ::= 
		// top.unaryProd = getPositiveOpOverload(top.op.typerep, top.op.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpositiveOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetPositiveOpOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } })); } };
		//ASPECT PRODUCTION negativeOp top ::= 
		// top.unaryProd = getNegativeOpOverload(top.op.typerep, top.op.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnegativeOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNegativeOpOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } })); } };
		//ASPECT PRODUCTION bitNegateOp top ::= 
		// top.unaryProd = getBitNegateOpOverload(top.op.typerep, top.op.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbitNegateOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBitNegateOpOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } })); } };
		//ASPECT PRODUCTION notOp top ::= 
		// top.unaryProd = getNotOpOverload(top.op.typerep, top.op.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnotOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetNotOpOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } })); } };
	}

	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getArraySubscriptOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberCallOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getCallOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubscriptAssignOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberAssignOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreIncOpOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreDecOpOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostIncOpOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostDecOpOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddressOfOpOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOpOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPositiveOpOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNegativeOpOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBitNegateOpOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotOpOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getUnaryOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_stringPairEq = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_applyMaybe = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_applyMaybe2 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_applyMaybe3 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_applyMaybe4 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_applyMaybe5 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_applyMaybe6 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_applyMaybe7 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_expandAssign = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryOpExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_dereferenceExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_explicitCastExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_arraySubscriptExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr = 0;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryProd__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp = edu.umn.cs.melt.ableC.abstractsyntax.host.Init.count_syn__ON__UnaryOp++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getArraySubscriptOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getArraySubscriptOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberCallOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberCallOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getCallOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getCallOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubscriptAssignOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubscriptAssignOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberAssignOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMemberAssignOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreIncOpOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreIncOpOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreDecOpOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPreDecOpOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostIncOpOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostIncOpOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostDecOpOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPostDecOpOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddressOfOpOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddressOfOpOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOpOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDereferenceOpOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPositiveOpOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getPositiveOpOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNegativeOpOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNegativeOpOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBitNegateOpOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBitNegateOpOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotOpOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotOpOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrEqOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAndBitOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getOrBitOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getXorOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLshOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getRshOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getEqualsOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getNotEqualsOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGtOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLtOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getGteOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getLteOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getSubOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getMulOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getDivOverload++;
public static final int overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload++;
public static final int lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload++;
public static final int rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getModOverload++;
public static final int lModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload++;
public static final int rModuleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload++;
public static final int option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload++;
public static final int option3__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_eqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorEqExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orEqExpr++;
public static final int tmpName__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_expandAssign = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_expandAssign++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andBitExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_orBitExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_xorExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lshExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_rshExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_equalsExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_notEqualsExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gtExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_ltExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_gteExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_lteExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_addExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_subExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_mulExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_divExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr++;
public static final int runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr++;
public static final int modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr++;
public static final int injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_modExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryOpExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_unaryOpExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_dereferenceExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_dereferenceExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_explicitCastExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_explicitCastExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_arraySubscriptExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_arraySubscriptExpr++;
public static final int option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr++;
public static final int option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr++;
public static final int lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr++;
public static final int ty__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr = edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr++;
}
