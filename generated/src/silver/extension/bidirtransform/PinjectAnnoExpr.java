
package silver.extension.bidirtransform;

// top::AnnoExpr ::= toFill::AnnoExpr toInject::AnnoAppExprs needAnnos::[String] 
public final class PinjectAnnoExpr extends silver.definition.core.NAnnoExpr {

	public static final int i_toFill = 0;
	public static final int i_toInject = 1;
	public static final int i_needAnnos = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NAnnoExpr.class,silver.definition.core.NAnnoAppExprs.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_injectAnnoExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAnnoExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAnnoExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NAnnoExpr.num_inh_attrs];
	childInheritedAttributes[i_toInject] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	}

	public PinjectAnnoExpr(final Object c_toFill, final Object c_toInject, final Object c_needAnnos, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_toInject = c_toInject;
		this.child_needAnnos = c_needAnnos;

	}

	private Object child_toFill;
	public final silver.definition.core.NAnnoExpr getChild_toFill() {
		return (silver.definition.core.NAnnoExpr) (child_toFill = common.Util.demand(child_toFill));
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
		return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NAnnoExpr>() { public final silver.definition.core.NAnnoExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PannoExpr) { final common.Thunk<Object> __SV_LOCAL___pv13516___sv_pv_13517_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13518___sv_tmp_pv_13519 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TEqual_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv13520___sv_pv_13515_ae = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.definition.core.NAnnoExpr)((silver.definition.core.NAnnoExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13521_ae = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13520___sv_pv_13515_ae.eval())); } };
return ((silver.definition.core.NAnnoExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13522_qn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13516___sv_pv_13517_qn.eval())); } };
return ((silver.definition.core.NAnnoExpr)new silver.definition.core.PannoExpr(common.Thunk.transformUndecorate(__SV_LOCAL_13522_qn), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TEqual_t((new common.StringCatter("=")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExpr)new silver.extension.bidirtransform.PinjectAppExpr(common.Thunk.transformUndecorate(__SV_LOCAL_13521_ae), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectAnnoExpr.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectAnnoExpr.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnoExpr.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoExpr)context.childDecorated(silver.extension.bidirtransform.PinjectAnnoExpr.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NAnnoExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpInject.sv', 113, 16, 116, 7, 5042, 5214\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PinjectAnnoExpr.i_toFill));
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
		return "silver:extension:bidirtransform:injectAnnoExpr";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PinjectAnnoExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PinjectAnnoExpr> {

		@Override
		public PinjectAnnoExpr invoke(final Object[] children, final Object[] annotations) {
			return new PinjectAnnoExpr(children[0], children[1], children[2], annotations[0]);
		}
	};

}
