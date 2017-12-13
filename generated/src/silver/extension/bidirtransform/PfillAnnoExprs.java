
package silver.extension.bidirtransform;

// top::AnnoAppExprs ::= toFill::AnnoAppExprs exps::[Expr] names::[String] 
public final class PfillAnnoExprs extends silver.definition.core.NAnnoAppExprs {

	public static final int i_toFill = 0;
	public static final int i_exps = 1;
	public static final int i_names = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NAnnoAppExprs.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_fillAnnoExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	}

	public PfillAnnoExprs(final Object c_toFill, final Object c_exps, final Object c_names, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_exps = c_exps;
		this.child_names = c_names;

	}

	private Object child_toFill;
	public final silver.definition.core.NAnnoAppExprs getChild_toFill() {
		return (silver.definition.core.NAnnoAppExprs) (child_toFill = common.Util.demand(child_toFill));
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
		return ((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12174___fail_12175 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.bidirtransform.PfillAnnoExprs.i_toFill).undecorate(); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NAnnoAppExprs>() { public final silver.definition.core.NAnnoAppExprs eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PoneAnnoAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv12179___sv_pv_12178_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.core.NAnnoAppExprs)((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12180_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12179___sv_pv_12178_e.eval())); } };
return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PoneAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.extension.bidirtransform.PfillAnnoAppExpr(common.Thunk.transformUndecorate(__SV_LOCAL_12180_e), context.childAsIsLazy(silver.extension.bidirtransform.PfillAnnoExprs.i_exps), context.childAsIsLazy(silver.extension.bidirtransform.PfillAnnoExprs.i_names), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.extension.bidirtransform.PfillAnnoExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.extension.bidirtransform.PfillAnnoExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PsnocAnnoAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv12187___sv_pv_12188_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12189___sv_tmp_pv_12190 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12191___sv_pv_12186_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.definition.core.NAnnoAppExprs)((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12192_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12191___sv_pv_12186_e.eval())); } };
return ((silver.definition.core.NAnnoAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12193_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12187___sv_pv_12188_es.eval())); } };
return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PsnocAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.extension.bidirtransform.PfillAnnoExprs(common.Thunk.transformUndecorate(__SV_LOCAL_12193_es), context.childAsIsLazy(silver.extension.bidirtransform.PfillAnnoExprs.i_exps), context.childAsIsLazy(silver.extension.bidirtransform.PfillAnnoExprs.i_names), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.extension.bidirtransform.PfillAnnoExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.extension.bidirtransform.PfillAnnoAppExpr(common.Thunk.transformUndecorate(__SV_LOCAL_12192_e), context.childAsIsLazy(silver.extension.bidirtransform.PfillAnnoExprs.i_exps), context.childAsIsLazy(silver.extension.bidirtransform.PfillAnnoExprs.i_names), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.extension.bidirtransform.PfillAnnoExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.childDecorated(silver.extension.bidirtransform.PfillAnnoExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NAnnoAppExprs)(__SV_LOCAL_12174___fail_12175.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PfillAnnoExprs.i_toFill)); } }).eval());
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
		return "silver:extension:bidirtransform:fillAnnoExprs";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PfillAnnoExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfillAnnoExprs> {

		@Override
		public PfillAnnoExprs invoke(final Object[] children, final Object[] annotations) {
			return new PfillAnnoExprs(children[0], children[1], children[2], annotations[0]);
		}
	};

}
