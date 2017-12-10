
package silver.extension.bidirtransform;

// top::AnnoAppExprs ::= a::AnnoAppExprs b::AnnoAppExprs 
public final class PconsAnnoAppExprs extends silver.definition.core.NAnnoAppExprs {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NAnnoAppExprs.class,silver.definition.core.NAnnoAppExprs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_consAnnoAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_a] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];
	childInheritedAttributes[i_b] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	}

	public PconsAnnoAppExprs(final Object c_a, final Object c_b, final Object a_core_location) {
		super(a_core_location);
		this.child_a = c_a;
		this.child_b = c_b;

	}

	private Object child_a;
	public final silver.definition.core.NAnnoAppExprs getChild_a() {
		return (silver.definition.core.NAnnoAppExprs) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final silver.definition.core.NAnnoAppExprs getChild_b() {
		return (silver.definition.core.NAnnoAppExprs) (child_b = common.Util.demand(child_b));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_b: return getChild_b();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_b: return child_b;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10482___fail_10481 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.bidirtransform.PconsAnnoAppExprs.i_a).undecorate(); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NAnnoAppExprs>() { public final silver.definition.core.NAnnoAppExprs eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PoneAnnoAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv10486___sv_pv_10485_expr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.core.NAnnoAppExprs)((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10489_expr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10486___sv_pv_10485_expr.eval())); } };
return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PsnocAnnoAppExprs(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PconsAnnoAppExprs.i_a)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(__SV_LOCAL_10489_expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PsnocAnnoAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv10497___sv_pv_10494_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv10498___sv_tmp_pv_10495 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv10499___sv_pv_10496_expr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.definition.core.NAnnoAppExprs)((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10502_expr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10499___sv_pv_10496_expr.eval())); } };
return ((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10505_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10497___sv_pv_10494_c.eval())); } };
return ((silver.definition.core.NAnnoAppExprs)new silver.extension.bidirtransform.PconsAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PsnocAnnoAppExprs(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PconsAnnoAppExprs.i_a)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(__SV_LOCAL_10502_expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(__SV_LOCAL_10505_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NAnnoAppExprs)(__SV_LOCAL_10482___fail_10481.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PconsAnnoAppExprs.i_b)); } }).eval());
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
		return "silver:extension:bidirtransform:consAnnoAppExprs";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PconsAnnoAppExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsAnnoAppExprs> {

		@Override
		public PconsAnnoAppExprs invoke(final Object[] children, final Object[] annotations) {
			return new PconsAnnoAppExprs(children[0], children[1], annotations[0]);
		}
	};

}
