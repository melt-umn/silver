
package silver.definition.flow.driver;

public final class PlocalStitchPoints extends common.FunctionNode {

	public static final int i_nt = 0;
	public static final int i_d = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_localStitchPoints;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PlocalStitchPoints(final Object c_nt, final Object c_d) {
		this.child_nt = c_nt;
		this.child_d = c_d;

	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_d;
	public final common.ConsCell getChild_d() {
		return (common.ConsCell) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_d: return child_d;

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
		return "silver:definition:flow:driver:localStitchPoints";
	}

	public static common.ConsCell invoke(final Object c_nt, final Object c_d) {
		try {
		final common.DecoratedNode context = new PlocalStitchPoints(c_nt, c_d).decorate();
		//case d of [] -> [] | fwdEq(_, _, _)::rest -> (nonterminalStitchPoint(nt, forwardVertexType) :: localStitchPoints(nt, rest)) | localEq(_, fN, "", _)::rest -> localStitchPoints(nt, rest) | localEq(_, fN, tN, _)::rest -> (nonterminalStitchPoint(tN, localVertexType(fN)) :: localStitchPoints(nt, rest)) | anonEq(_, fN, tN, _, _)::rest -> (nonterminalStitchPoint(tN, anonVertexType(fN)) :: localStitchPoints(nt, rest)) | _::rest -> localStitchPoints(nt, rest) end
		return (common.ConsCell)(new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_5990___sv_tmp_pv_5991 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_5992___sv_pv_5989_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5994___fail_5995 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5993_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_5992___sv_pv_5989_rest.eval())); } };
return ((common.ConsCell)silver.definition.flow.driver.PlocalStitchPoints.invoke(context.childAsIsLazy(silver.definition.flow.driver.PlocalStitchPoints.i_nt), __SV_LOCAL_5993_rest)); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.ast.PanonEq) { final common.Thunk<Object> __SV_LOCAL___pv5999___sv_tmp_pv_6000 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6001___sv_pv_6002_fN = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6003___sv_pv_6004_tN = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv6005___sv_tmp_pv_6006 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(3); } };
final common.Thunk<Object> __SV_LOCAL___pv6007___sv_tmp_pv_6008 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(4); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6009_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_5992___sv_pv_5989_rest.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6010_tN = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6003___sv_pv_6004_tN.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6011_fN = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6001___sv_pv_6002_fN.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NStitchPoint)new silver.definition.flow.driver.PnonterminalStitchPoint(__SV_LOCAL_6010_tN, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PanonVertexType(__SV_LOCAL_6011_fN)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PlocalStitchPoints.invoke(context.childAsIsLazy(silver.definition.flow.driver.PlocalStitchPoints.i_nt), __SV_LOCAL_6009_rest)); } })); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.flow.ast.PfwdEq) { final common.Thunk<Object> __SV_LOCAL___pv6012___sv_tmp_pv_6013 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6014___sv_tmp_pv_6015 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6016___sv_tmp_pv_6017 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(2); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6018_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_5992___sv_pv_5989_rest.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NStitchPoint)new silver.definition.flow.driver.PnonterminalStitchPoint(context.childAsIsLazy(silver.definition.flow.driver.PlocalStitchPoints.i_nt), silver.definition.flow.ast.Init.forwardVertexType)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PlocalStitchPoints.invoke(context.childAsIsLazy(silver.definition.flow.driver.PlocalStitchPoints.i_nt), __SV_LOCAL_6018_rest)); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.flow.ast.PlocalEq) { final common.Thunk<Object> __SV_LOCAL___pv6019___sv_tmp_pv_6020 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6021___sv_pv_6022_fN = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6023___sv_tmp_pv_6024 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv6025___sv_tmp_pv_6026 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(3); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6030___fail_6031 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6027_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_5992___sv_pv_5989_rest.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6028_tN = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6023___sv_tmp_pv_6024.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6029_fN = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6021___sv_pv_6022_fN.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NStitchPoint)new silver.definition.flow.driver.PnonterminalStitchPoint(__SV_LOCAL_6028_tN, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PlocalVertexType(__SV_LOCAL_6029_fN)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PlocalStitchPoints.invoke(context.childAsIsLazy(silver.definition.flow.driver.PlocalStitchPoints.i_nt), __SV_LOCAL_6027_rest)); } })); } }).eval()); } }).eval()); } }).eval()); } };
return new common.PatternLazy<common.StringCatter, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.StringCatter scrutineeIter) {final common.StringCatter scrutinee = scrutineeIter; if(scrutinee.equals("")) { return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6032_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_5992___sv_pv_5989_rest.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6033_fN = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6021___sv_pv_6022_fN.eval())); } };
return ((common.ConsCell)silver.definition.flow.driver.PlocalStitchPoints.invoke(context.childAsIsLazy(silver.definition.flow.driver.PlocalStitchPoints.i_nt), __SV_LOCAL_6032_rest)); } }).eval()); } }).eval()); }return ((common.ConsCell)(__SV_LOCAL_6030___fail_6031.eval()));}}.eval(context, (common.StringCatter)((common.StringCatter)(__SV_LOCAL___pv6023___sv_tmp_pv_6024.eval()))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_5994___fail_5995.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.flow.ast.NFlowDef)(__SV_LOCAL_5990___sv_tmp_pv_5991.eval())).decorate(context, (common.Lazy[])null)); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:flow:driver 'ProductionGraph.sv', 345, 9, 357, 5, 14000, 14691\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.definition.flow.driver.PlocalStitchPoints.i_d))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:localStitchPoints", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PlocalStitchPoints.invoke(children[0], children[1]);
		}
	};
}