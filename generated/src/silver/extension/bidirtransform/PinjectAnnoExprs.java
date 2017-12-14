
package silver.extension.bidirtransform;

// top::AnnoAppExprs ::= toFill::AnnoAppExprs toInject::AnnoAppExprs needAnnos::[String] 
public final class PinjectAnnoExprs extends silver.definition.core.NAnnoAppExprs {

	public static final int i_toFill = 0;
	public static final int i_toInject = 1;
	public static final int i_needAnnos = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NAnnoAppExprs.class,silver.definition.core.NAnnoAppExprs.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_injectAnnoExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];
	childInheritedAttributes[i_toInject] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	}

	public PinjectAnnoExprs(final Object c_toFill, final Object c_toInject, final Object c_needAnnos, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_toInject = c_toInject;
		this.child_needAnnos = c_needAnnos;

	}

	private Object child_toFill;
	public final silver.definition.core.NAnnoAppExprs getChild_toFill() {
		return (silver.definition.core.NAnnoAppExprs) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_toInject;
	public final silver.definition.core.NAnnoAppExprs getChild_toInject() {
		return (silver.definition.core.NAnnoAppExprs) (child_toInject = common.Util.demand(child_toInject));
	}

	private Object child_needAnnos;
	public final common.ConsCell getChild_needAnnos() {
		return (common.ConsCell) (child_needAnnos = common.Util.demand(child_needAnnos));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_toInject: return getChild_toInject();
			case i_needAnnos: return getChild_needAnnos();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_toInject: return child_toInject;
			case i_needAnnos: return child_needAnnos;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return ((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13728___fail_13729 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.bidirtransform.PinjectAnnoExprs.i_toFill).undecorate(); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NAnnoAppExprs>() { public final silver.definition.core.NAnnoAppExprs eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PoneAnnoAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv13733___sv_pv_13732_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.core.NAnnoAppExprs)((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13734_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13733___sv_pv_13732_e.eval())); } };
return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PoneAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.extension.bidirtransform.PinjectAnnoExpr(common.Thunk.transformUndecorate(__SV_LOCAL_13734_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnoExprs.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnoExprs.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.extension.bidirtransform.PinjectAnnoExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.extension.bidirtransform.PinjectAnnoExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PsnocAnnoAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv13742___sv_pv_13743_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13744___sv_tmp_pv_13745 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13746___sv_pv_13741_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.definition.core.NAnnoAppExprs)((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13747_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13746___sv_pv_13741_e.eval())); } };
return ((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13748_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13742___sv_pv_13743_es.eval())); } };
return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PsnocAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.extension.bidirtransform.PinjectAnnoExprs(common.Thunk.transformUndecorate(__SV_LOCAL_13748_es), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnoExprs.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnoExprs.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.extension.bidirtransform.PinjectAnnoExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.extension.bidirtransform.PinjectAnnoExpr(common.Thunk.transformUndecorate(__SV_LOCAL_13747_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnoExprs.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnoExprs.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.extension.bidirtransform.PinjectAnnoExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.extension.bidirtransform.PinjectAnnoExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NAnnoAppExprs)(__SV_LOCAL_13728___fail_13729.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PinjectAnnoExprs.i_toFill)); } }).eval());
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
		return "silver:extension:bidirtransform:injectAnnoExprs";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PinjectAnnoExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PinjectAnnoExprs> {

		@Override
		public PinjectAnnoExprs invoke(final Object[] children, final Object[] annotations) {
			return new PinjectAnnoExprs(children[0], children[1], children[2], annotations[0]);
		}
	};

}
