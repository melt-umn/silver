
package silver.extension.testing;

public final class PmkToStringExprCS extends common.FunctionNode {

	public static final int i_valueType = 0;
	public static final int i_exprName = 1;
	public static final int i_l = 2;


	public static final Class<?> childTypes[] = { silver.definition.type.syntax.NTypeExpr.class,common.StringCatter.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_testing_mkToStringExprCS;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_valueType] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PmkToStringExprCS(final Object c_valueType, final Object c_exprName, final Object c_l) {
		this.child_valueType = c_valueType;
		this.child_exprName = c_exprName;
		this.child_l = c_l;

	}

	private Object child_valueType;
	public final silver.definition.type.syntax.NTypeExpr getChild_valueType() {
		return (silver.definition.type.syntax.NTypeExpr) (child_valueType = common.Util.demand(child_valueType));
	}

	private Object child_exprName;
	public final common.StringCatter getChild_exprName() {
		return (common.StringCatter) (child_exprName = common.Util.demand(child_exprName));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_valueType: return getChild_valueType();
			case i_exprName: return getChild_exprName();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_valueType: return child_valueType;
			case i_exprName: return child_exprName;
			case i_l: return child_l;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:extension:testing:mkToStringExprCS";
	}

	public static core.NMaybe invoke(final Object c_valueType, final Object c_exprName, final Object c_l) {
		try {
		final common.DecoratedNode context = new PmkToStringExprCS(c_valueType, c_exprName, c_l).decorate();
		//case functionNameForBaseTypesCS(valueType, "toStringFrom") of just(btt) -> just(mkStrFunctionInvocation(l, btt, [ mkNameExpr(exprName, l) ])) | nothing() -> case valueType of listTypeExpr(_, elemType, _) -> case functionNameForBaseTypesCS(elemType, "toStringFrom") of just(btt) -> just(mkStrFunctionInvocation(l, "toStringFromList", [ mkNameExpr(btt, l), mkNameExpr(exprName, l) ])) | _ -> nothing() end | _ -> nothing() end end
		return (core.NMaybe)(new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv8596___sv_pv_8595_btt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8597_btt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv8596___sv_pv_8595_btt.eval())); } };
return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.definition.core.PmkStrFunctionInvocation.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.testing.PmkToStringExprCS.i_l)), __SV_LOCAL_8597_btt, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.extension.testing.PmkNameExpr.invoke(context.childAsIsLazy(silver.extension.testing.PmkToStringExprCS.i_exprName), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.testing.PmkToStringExprCS.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8598___fail_8599 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.list.PlistTypeExpr) { final common.Thunk<Object> __SV_LOCAL___pv8614___sv_tmp_pv_8615 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.extension.list.TLSqr_t)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8616___sv_pv_8613_elemType = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv8617___sv_tmp_pv_8618 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.extension.list.TRSqr_t)scrutinee.childAsIs(2); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8619_elemType = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8616___sv_pv_8613_elemType.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8620___fail_8621 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv8623___sv_pv_8622_btt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8624_btt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv8623___sv_pv_8622_btt.eval())); } };
return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.definition.core.PmkStrFunctionInvocation.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.testing.PmkToStringExprCS.i_l)), (new common.StringCatter("toStringFromList")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.extension.testing.PmkNameExpr.invoke(__SV_LOCAL_8624_btt, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.testing.PmkToStringExprCS.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.extension.testing.PmkNameExpr.invoke(context.childAsIsLazy(silver.extension.testing.PmkToStringExprCS.i_exprName), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.testing.PmkToStringExprCS.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_8620___fail_8621.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)silver.extension.testing.PfunctionNameForBaseTypesCS.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8619_elemType), (new common.StringCatter("toStringFrom")))).decorate(context, (common.Lazy[])null)); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_8598___fail_8599.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.testing.PmkToStringExprCS.i_valueType)); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:testing 'EqualityTest.sv', 213, 4, 225, 7, 9488, 10025\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)silver.extension.testing.PfunctionNameForBaseTypesCS.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.testing.PmkToStringExprCS.i_valueType)), (new common.StringCatter("toStringFrom")))).decorate(context, (common.Lazy[])null)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:testing:mkToStringExprCS", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmkToStringExprCS.invoke(children[0], children[1], children[2]);
		}
	};
}