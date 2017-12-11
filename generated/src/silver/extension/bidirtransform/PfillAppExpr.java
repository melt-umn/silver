
package silver.extension.bidirtransform;

// top::AppExpr ::= toFill::AppExpr exps::[Expr] names::[String] 
public final class PfillAppExpr extends silver.definition.core.NAppExpr {

	public static final int i_toFill = 0;
	public static final int i_exps = 1;
	public static final int i_names = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NAppExpr.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_fillAppExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	}

	public PfillAppExpr(final Object c_toFill, final Object c_exps, final Object c_names, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_exps = c_exps;
		this.child_names = c_names;

	}

	private Object child_toFill;
	public final silver.definition.core.NAppExpr getChild_toFill() {
		return (silver.definition.core.NAppExpr) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_exps;
	public final common.ConsCell getChild_exps() {
		return (common.ConsCell) (child_exps = common.Util.demand(child_exps));
	}

	private Object child_names;
	public final common.ConsCell getChild_names() {
		return (common.ConsCell) (child_names = common.Util.demand(child_names));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_exps: return getChild_exps();
			case i_names: return getChild_names();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_exps: return child_exps;
			case i_names: return child_names;

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
		return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NAppExpr>() { public final silver.definition.core.NAppExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PpresentAppExpr) { final common.Thunk<Object> __SV_LOCAL___pv12119___sv_pv_12118_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.core.NAppExpr)((silver.definition.core.NAppExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12120_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12119___sv_pv_12118_e.eval())); } };
return ((silver.definition.core.NAppExpr)new silver.definition.core.PpresentAppExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PfillExpr(common.Thunk.transformUndecorate(__SV_LOCAL_12120_e), context.childAsIsLazy(silver.extension.bidirtransform.PfillAppExpr.i_exps), context.childAsIsLazy(silver.extension.bidirtransform.PfillAppExpr.i_names), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExpr)context.childDecorated(silver.extension.bidirtransform.PfillAppExpr.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExpr)context.childDecorated(silver.extension.bidirtransform.PfillAppExpr.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NAppExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpFills.sv', 86, 16, 89, 7, 4308, 4465\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PfillAppExpr.i_toFill));
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
		return "silver:extension:bidirtransform:fillAppExpr";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PfillAppExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfillAppExpr> {

		@Override
		public PfillAppExpr invoke(final Object[] children, final Object[] annotations) {
			return new PfillAppExpr(children[0], children[1], children[2], annotations[0]);
		}
	};

}
