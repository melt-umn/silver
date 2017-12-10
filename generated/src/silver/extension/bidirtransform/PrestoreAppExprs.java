
package silver.extension.bidirtransform;

// top::AppExprs ::= toFill::AppExprs inputTypes::[String] 
public final class PrestoreAppExprs extends silver.definition.core.NAppExprs {

	public static final int i_toFill = 0;
	public static final int i_inputTypes = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NAppExprs.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_restoreAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAppExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];

	}

	public PrestoreAppExprs(final Object c_toFill, final Object c_inputTypes, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_inputTypes = c_inputTypes;

	}

	private Object child_toFill;
	public final silver.definition.core.NAppExprs getChild_toFill() {
		return (silver.definition.core.NAppExprs) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_inputTypes;
	public final common.ConsCell getChild_inputTypes() {
		return (common.ConsCell) (child_inputTypes = common.Util.demand(child_inputTypes));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_inputTypes: return getChild_inputTypes();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_inputTypes: return child_inputTypes;

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
		return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrestoreAppExprs.i_inputTypes))) ? context.childDecorated(silver.extension.bidirtransform.PrestoreAppExprs.i_toFill).undecorate() : new common.PatternLazy<common.DecoratedNode, silver.definition.core.NAppExprs>() { public final silver.definition.core.NAppExprs eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PoneAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv11737___sv_pv_11736_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.core.NAppExprs)((silver.definition.core.NAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11738_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv11737___sv_pv_11736_e.eval())); } };
return ((silver.definition.core.NAppExprs)new silver.definition.core.PoneAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExpr)new silver.extension.bidirtransform.PrestoreAppExpr(common.Thunk.transformUndecorate(__SV_LOCAL_11738_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrestoreAppExprs.i_inputTypes))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.childDecorated(silver.extension.bidirtransform.PrestoreAppExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.childDecorated(silver.extension.bidirtransform.PrestoreAppExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PsnocAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv11746___sv_pv_11747_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv11748___sv_tmp_pv_11749 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv11750___sv_pv_11745_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.definition.core.NAppExprs)((silver.definition.core.NAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11751_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv11750___sv_pv_11745_e.eval())); } };
return ((silver.definition.core.NAppExprs)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11752_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv11746___sv_pv_11747_es.eval())); } };
return ((silver.definition.core.NAppExprs)new silver.definition.core.PsnocAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.extension.bidirtransform.PrestoreAppExprs(common.Thunk.transformUndecorate(__SV_LOCAL_11752_es), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrestoreAppExprs.i_inputTypes))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.childDecorated(silver.extension.bidirtransform.PrestoreAppExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExpr)new silver.extension.bidirtransform.PrestoreAppExpr(common.Thunk.transformUndecorate(__SV_LOCAL_11751_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrestoreAppExprs.i_inputTypes))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.childDecorated(silver.extension.bidirtransform.PrestoreAppExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.childDecorated(silver.extension.bidirtransform.PrestoreAppExprs.i_toFill).undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NAppExprs)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpRestore.sv', 21, 8, 27, 11, 1037, 1459\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PrestoreAppExprs.i_toFill)));
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
		return "silver:extension:bidirtransform:restoreAppExprs";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PrestoreAppExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrestoreAppExprs> {

		@Override
		public PrestoreAppExprs invoke(final Object[] children, final Object[] annotations) {
			return new PrestoreAppExprs(children[0], children[1], annotations[0]);
		}
	};

}
