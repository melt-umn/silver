
package silver.definition.flow.ast;

public final class PcollectAnonOriginItem extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_rest = 1;


	public static final Class<?> childTypes[] = { silver.definition.flow.ast.NFlowDef.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_collectAnonOriginItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_f] = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_inh_attrs];

	}

	public PcollectAnonOriginItem(final Object c_f, final Object c_rest) {
		this.child_f = c_f;
		this.child_rest = c_rest;

	}

	private Object child_f;
	public final silver.definition.flow.ast.NFlowDef getChild_f() {
		return (silver.definition.flow.ast.NFlowDef) (child_f = common.Util.demand(child_f));
	}

	private Object child_rest;
	public final common.ConsCell getChild_rest() {
		return (common.ConsCell) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_rest: return child_rest;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:definition:flow:ast:collectAnonOriginItem";
	}

	public static common.ConsCell invoke(final Object c_f, final Object c_rest) {
		try {
		final common.DecoratedNode context = new PcollectAnonOriginItem(c_f, c_rest).decorate();
		//case f of anonEq(_, fN, _, l, _) -> if startsWith("__scrutinee", fN) then rest else (pair(fN, l) :: rest) | _ -> rest end
		return (common.ConsCell)(((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6398___fail_6399 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.flow.ast.PcollectAnonOriginItem.i_rest)); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.ast.PanonEq) { final common.Thunk<Object> __SV_LOCAL___pv6403___sv_tmp_pv_6404 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6405___sv_pv_6406_fN = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6407___sv_tmp_pv_6408 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv6409___sv_pv_6402_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(3); } };
final common.Thunk<Object> __SV_LOCAL___pv6410___sv_tmp_pv_6411 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(4); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6412_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv6409___sv_pv_6402_l.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6413_fN = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6405___sv_pv_6406_fN.eval())); } };
return (((Boolean)core.PstartsWith.invoke((new common.StringCatter("__scrutinee")), __SV_LOCAL_6413_fN)) ? ((common.ConsCell)context.childAsIs(silver.definition.flow.ast.PcollectAnonOriginItem.i_rest)) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(__SV_LOCAL_6413_fN, common.Thunk.transformUndecorate(__SV_LOCAL_6412_l))); } }, context.childAsIsLazy(silver.definition.flow.ast.PcollectAnonOriginItem.i_rest)))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_6398___fail_6399.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.flow.ast.PcollectAnonOriginItem.i_f)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:ast:collectAnonOriginItem", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcollectAnonOriginItem.invoke(children[0], children[1]);
		}
	};
}