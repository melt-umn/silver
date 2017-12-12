
package silver.extension.bidirtransform;

// top::AppExpr ::= toFill::AppExpr inType::String 
public final class PrestoreAppExpr extends silver.definition.core.NAppExpr {

	public static final int i_toFill = 0;
	public static final int i_inType = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NAppExpr.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_restoreAppExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	}

	public PrestoreAppExpr(final Object c_toFill, final Object c_inType, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_inType = c_inType;

	}

	private Object child_toFill;
	public final silver.definition.core.NAppExpr getChild_toFill() {
		return (silver.definition.core.NAppExpr) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_inType;
	public final common.StringCatter getChild_inType() {
		return (common.StringCatter) (child_inType = common.Util.demand(child_inType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_inType: return getChild_inType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_inType: return child_inType;

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
		return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NAppExpr>() { public final silver.definition.core.NAppExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PpresentAppExpr) { final common.Thunk<Object> __SV_LOCAL___pv11787___sv_pv_11786_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.core.NAppExpr)((silver.definition.core.NAppExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11788_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv11787___sv_pv_11786_e.eval())); } };
return ((silver.definition.core.NAppExpr)new silver.definition.core.PpresentAppExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11794___fail_11795 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PqAccess(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PrestoreNm.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrestoreAppExpr.i_inType))); } })); } }, common.Thunk.transformUndecorate(__SV_LOCAL_11788_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExpr)context.childDecorated(silver.extension.bidirtransform.PrestoreAppExpr.i_toFill).undecorate()).getAnno_core_location()); } })); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PterminalType) { final common.Thunk<Object> __SV_LOCAL___pv11798___sv_tmp_pv_11799 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)((common.DecoratedNode)__SV_LOCAL_11788_e.eval()).undecorate()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NExpr)(__SV_LOCAL_11794___fail_11795.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.type.NType)((common.DecoratedNode)(__SV_LOCAL_11788_e.eval())).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExpr)context.childDecorated(silver.extension.bidirtransform.PrestoreAppExpr.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NAppExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpRestore.sv', 33, 16, 38, 7, 1565, 1809\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PrestoreAppExpr.i_toFill));
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
		return "silver:extension:bidirtransform:restoreAppExpr";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PrestoreAppExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrestoreAppExpr> {

		@Override
		public PrestoreAppExpr invoke(final Object[] children, final Object[] annotations) {
			return new PrestoreAppExpr(children[0], children[1], annotations[0]);
		}
	};

}
