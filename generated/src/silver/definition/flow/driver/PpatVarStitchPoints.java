
package silver.definition.flow.driver;

public final class PpatVarStitchPoints extends common.FunctionNode {

	public static final int i_matchProd = 0;
	public static final int i_scrutinee = 1;
	public static final int i_realEnv = 2;
	public static final int i_var = 3;


	public static final Class<?> childTypes[] = { common.StringCatter.class,silver.definition.flow.ast.NVertexType.class,common.DecoratedNode.class,silver.definition.flow.ast.NPatternVarProjection.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_patVarStitchPoints;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_scrutinee] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];
	childInheritedAttributes[i_var] = new common.Lazy[silver.definition.flow.ast.NPatternVarProjection.num_inh_attrs];

	}

	public PpatVarStitchPoints(final Object c_matchProd, final Object c_scrutinee, final Object c_realEnv, final Object c_var) {
		this.child_matchProd = c_matchProd;
		this.child_scrutinee = c_scrutinee;
		this.child_realEnv = c_realEnv;
		this.child_var = c_var;

	}

	private Object child_matchProd;
	public final common.StringCatter getChild_matchProd() {
		return (common.StringCatter) (child_matchProd = common.Util.demand(child_matchProd));
	}

	private Object child_scrutinee;
	public final silver.definition.flow.ast.NVertexType getChild_scrutinee() {
		return (silver.definition.flow.ast.NVertexType) (child_scrutinee = common.Util.demand(child_scrutinee));
	}

	private Object child_realEnv;
	public final common.DecoratedNode getChild_realEnv() {
		return (common.DecoratedNode) (child_realEnv = common.Util.demand(child_realEnv));
	}

	private Object child_var;
	public final silver.definition.flow.ast.NPatternVarProjection getChild_var() {
		return (silver.definition.flow.ast.NPatternVarProjection) (child_var = common.Util.demand(child_var));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_matchProd: return getChild_matchProd();
			case i_scrutinee: return getChild_scrutinee();
			case i_realEnv: return getChild_realEnv();
			case i_var: return getChild_var();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_matchProd: return child_matchProd;
			case i_scrutinee: return child_scrutinee;
			case i_realEnv: return child_realEnv;
			case i_var: return child_var;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:definition:flow:driver:patVarStitchPoints";
	}

	public static common.ConsCell invoke(final Object c_matchProd, final Object c_scrutinee, final Object c_realEnv, final Object c_var) {
		try {
		final common.DecoratedNode context = new PpatVarStitchPoints(c_matchProd, c_scrutinee, c_realEnv, c_var).decorate();
		//case var of patternVarProjection(child, typeName, patternVar) -> [ nonterminalStitchPoint(typeName, anonVertexType(patternVar)), projectionStitchPoint(matchProd, anonVertexType(patternVar), scrutinee, rhsVertexType(child), getInhsForNtForPatternVars(typeName, realEnv)) ] end
		return (common.ConsCell)(new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.ast.PpatternVarProjection) { final common.Thunk<Object> __SV_LOCAL___pv6045___sv_pv_6046_child = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6047___sv_pv_6048_typeName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6049___sv_pv_6044_patternVar = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6050_patternVar = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6049___sv_pv_6044_patternVar.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6051_typeName = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6047___sv_pv_6048_typeName.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6052_child = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6045___sv_pv_6046_child.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NStitchPoint)new silver.definition.flow.driver.PnonterminalStitchPoint(__SV_LOCAL_6051_typeName, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PanonVertexType(__SV_LOCAL_6050_patternVar)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NStitchPoint)new silver.definition.flow.driver.PprojectionStitchPoint(context.childAsIsLazy(silver.definition.flow.driver.PpatVarStitchPoints.i_matchProd), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PanonVertexType(__SV_LOCAL_6050_patternVar)); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.flow.driver.PpatVarStitchPoints.i_scrutinee)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PrhsVertexType(__SV_LOCAL_6052_child)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PgetInhsForNtForPatternVars.invoke(__SV_LOCAL_6051_typeName, context.childAsIsLazy(silver.definition.flow.driver.PpatVarStitchPoints.i_realEnv))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:flow:driver 'ProductionGraph.sv', 384, 9, 388, 5, 15598, 15891\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.flow.driver.PpatVarStitchPoints.i_var)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:patVarStitchPoints", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpatVarStitchPoints.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}