
package silver.support.monto;

// top::Service ::= negotiation::ServiceNegotiation providers::[ServiceProvider] 
public final class Pservice extends silver.support.monto.NService {

	public static final int i_negotiation = 0;
	public static final int i_providers = 1;


	public static final Class<?> childTypes[] = {silver.support.monto.negotiation.NServiceNegotiation.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_service;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.NService.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.NService.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_negotiation] = new common.Lazy[silver.support.monto.negotiation.NServiceNegotiation.num_inh_attrs];

	}

	public Pservice(final Object c_negotiation, final Object c_providers) {
		super();
		this.child_negotiation = c_negotiation;
		this.child_providers = c_providers;

	}

	private Object child_negotiation;
	public final silver.support.monto.negotiation.NServiceNegotiation getChild_negotiation() {
		return (silver.support.monto.negotiation.NServiceNegotiation) (child_negotiation = common.Util.demand(child_negotiation));
	}

	private Object child_providers;
	public final common.ConsCell getChild_providers() {
		return (common.ConsCell) (child_providers = common.Util.demand(child_providers));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_negotiation: return getChild_negotiation();
			case i_providers: return getChild_providers();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_negotiation: return child_negotiation;
			case i_providers: return child_providers;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:service erroneously claimed to forward");
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
		return "silver:support:monto:service";
	}

	static void initProductionAttributeDefinitions() {
		// top.doNegotiation = \ sbn::ServiceBrokerNegotiation  -> pair(negotiation.json.jsonString, negotiationsCompatible(negotiation, sbn))
		silver.support.monto.Pservice.synthesizedAttributes[silver.support.monto.Init.silver_support_monto_doNegotiation__ON__silver_support_monto_Service] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<core.NPair>() {
  public final core.NPair invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_67_sbn = args[0];

    return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.json.NJson)context.childDecorated(silver.support.monto.Pservice.i_negotiation).synthesized(silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_ServiceNegotiation)).decorate(context, (common.Lazy[])null).synthesized(silver.json.Init.silver_json_jsonString__ON__silver_json_Json)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)silver.support.monto.negotiation.PnegotiationsCompatible.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.support.monto.Pservice.i_negotiation)), ((silver.support.monto.negotiation.NServiceBrokerNegotiation)common.Util.demand(__SV_LAMBDA_PARAM_67_sbn)))); } }));
  }
}); } };
		// top.onRequest = \ pi::ProductIdentifier deps::[Product]  -> case find(\ p::ServiceProvider  -> productDescriptorEq(p.descriptor, asDescriptor(pi)), providers) of just(p) -> let tmp :: Pair<Json Integer> = case p.processService(pi.productPath, deps) of pair(left(errs), ntcs) -> pair(jsonObject([ pair("errors", jsonArray(map((.json), errs))), pair("notices", jsonArray(map((.json), ntcs))) ]), 500) | pair(right(prd), ntcs) -> pair(jsonObject([ pair("product", prd.json), pair("notices", jsonArray(map((.json), ntcs))) ]), 200) end in pair(tmp.fst.jsonString, tmp.snd) end | nothing() -> pair(pi.json.jsonString, 400) end
		silver.support.monto.Pservice.synthesizedAttributes[silver.support.monto.Init.silver_support_monto_onRequest__ON__silver_support_monto_Service] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<core.NPair>() {
  public final core.NPair invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_68_pi = args[0];
final Object __SV_LAMBDA_PARAM_69_deps = args[1];

    return new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv74___sv_pv_73_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.support.monto.NServiceProvider)scrutinee.childAsIs(0); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_75_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.support.monto.NServiceProvider)(__SV_LOCAL___pv74___sv_pv_73_p.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_124_tmp = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Ppair) { final common.Thunk<Object> __SV_LOCAL___pv81___sv_tmp_pv_80 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (core.NEither)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv82___sv_pv_83_ntcs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (core.NPair)new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pleft) { final common.Thunk<Object> __SV_LOCAL___pv90___sv_pv_91_errs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_92_ntcs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv82___sv_pv_83_ntcs.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_93_errs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv90___sv_pv_91_errs.eval())); } };
return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("errors")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonArray(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.support.monto.Init.silver_json_json__ON__silver_support_monto_ServiceError, context), __SV_LOCAL_93_errs)); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("notices")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonArray(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.support.monto.Init.silver_json_json__ON__silver_support_monto_ServiceNotice, context), __SV_LOCAL_92_ntcs)); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, Integer.valueOf((int)500))); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof core.Pright) { final common.Thunk<Object> __SV_LOCAL___pv96___sv_pv_97_prd = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.support.monto.products.NProduct)scrutinee.childAsIs(0); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_98_ntcs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv82___sv_pv_83_ntcs.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_99_prd = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.support.monto.products.NProduct)(__SV_LOCAL___pv96___sv_pv_97_prd.eval())); } };
return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("product")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)((silver.support.monto.products.NProduct)(__SV_LOCAL_99_prd.eval())).decorate(context, (common.Lazy[])null).synthesized(silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Product)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("notices")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonArray(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.support.monto.Init.silver_json_json__ON__silver_support_monto_ServiceNotice, context), __SV_LOCAL_98_ntcs)); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, Integer.valueOf((int)200))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:support:monto 'Service.sv', 22, 36, 31, 9, 962, 1372\n"))));}}.eval(context, (common.DecoratedNode)((core.NEither)(__SV_LOCAL___pv81___sv_tmp_pv_80.eval())).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:support:monto 'Service.sv', 22, 36, 31, 9, 962, 1372\n"))));}}.eval(context, (common.DecoratedNode)((core.NPair)((common.NodeFactory<core.NPair>)((silver.support.monto.NServiceProvider)(__SV_LOCAL_75_p.eval())).decorate(context, (common.Lazy[])null).synthesized(silver.support.monto.Init.silver_support_monto_processService__ON__silver_support_monto_ServiceProvider)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.support.monto.products.NProductIdentifier)common.Util.demand(__SV_LAMBDA_PARAM_68_pi)).decorate(context, (common.Lazy[])null).synthesized(silver.support.monto.products.Init.silver_support_monto_products_productPath__ON__silver_support_monto_products_ProductIdentifier)); } }, ((common.ConsCell)common.Util.demand(__SV_LAMBDA_PARAM_69_deps))}, null)).decorate(context, (common.Lazy[])null)); } };
return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.json.NJson)((core.NPair)(__SV_LOCAL_124_tmp.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)).decorate(context, (common.Lazy[])null).synthesized(silver.json.Init.silver_json_jsonString__ON__silver_json_Json)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((core.NPair)(__SV_LOCAL_124_tmp.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (core.NPair)((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.json.NJson)((silver.support.monto.products.NProductIdentifier)common.Util.demand(__SV_LAMBDA_PARAM_68_pi)).decorate(context, (common.Lazy[])null).synthesized(silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ProductIdentifier)).decorate(context, (common.Lazy[])null).synthesized(silver.json.Init.silver_json_jsonString__ON__silver_json_Json)); } }, Integer.valueOf((int)400))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:support:monto 'Service.sv', 20, 4, 33, 7, 809, 1470\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)core.Pfind.invoke((new common.NodeFactory<Boolean>() {
  public final Boolean invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_70_p = args[0];

    return ((Boolean)silver.support.monto.products.PproductDescriptorEq.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.support.monto.products.NProductDescriptor)((silver.support.monto.NServiceProvider)common.Util.demand(__SV_LAMBDA_PARAM_70_p)).decorate(context, (common.Lazy[])null).synthesized(silver.support.monto.Init.silver_support_monto_descriptor__ON__silver_support_monto_ServiceProvider)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.support.monto.products.NProductDescriptor)silver.support.monto.PasDescriptor.invoke(((silver.support.monto.products.NProductIdentifier)common.Util.demand(__SV_LAMBDA_PARAM_68_pi)))); } }));
  }
}), context.childAsIsLazy(silver.support.monto.Pservice.i_providers))).decorate(context, (common.Lazy[])null));
  }
}); } };

	}

	public static final common.NodeFactory<Pservice> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pservice> {

		@Override
		public Pservice invoke(final Object[] children, final Object[] annotations) {
			return new Pservice(children[0], children[1]);
		}
	};

}
