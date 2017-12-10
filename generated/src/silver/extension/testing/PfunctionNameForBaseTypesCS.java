
package silver.extension.testing;

public final class PfunctionNameForBaseTypesCS extends common.FunctionNode {

	public static final int i_valueType = 0;
	public static final int i_prefixS = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.syntax.NTypeExpr.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_testing_functionNameForBaseTypesCS;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_valueType] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	}

	public PfunctionNameForBaseTypesCS(final Object c_valueType, final Object c_prefixS) {
		this.child_valueType = c_valueType;
		this.child_prefixS = c_prefixS;

	}

	private Object child_valueType;
	public final silver.definition.type.syntax.NTypeExpr getChild_valueType() {
		return (silver.definition.type.syntax.NTypeExpr) (child_valueType = common.Util.demand(child_valueType));
	}

	private Object child_prefixS;
	public final common.StringCatter getChild_prefixS() {
		return (common.StringCatter) (child_prefixS = common.Util.demand(child_prefixS));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_valueType: return getChild_valueType();
			case i_prefixS: return getChild_prefixS();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_valueType: return child_valueType;
			case i_prefixS: return child_prefixS;

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
		return "silver:extension:testing:functionNameForBaseTypesCS";
	}

	public static core.NMaybe invoke(final Object c_valueType, final Object c_prefixS) {
		try {
		final common.DecoratedNode context = new PfunctionNameForBaseTypesCS(c_valueType, c_prefixS).decorate();
		//case valueType of integerTypeExpr(_) -> just(prefixS ++ "Integer") | floatTypeExpr(_) -> just(prefixS ++ "Float") | stringTypeExpr(_) -> just(prefixS ++ "String") | booleanTypeExpr(_) -> just(prefixS ++ "Boolean") | _ -> nothing() end
		return (core.NMaybe)(((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8580___fail_8581 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.syntax.PbooleanTypeExpr) { final common.Thunk<Object> __SV_LOCAL___pv8585___sv_tmp_pv_8586 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.type.syntax.TBoolean_tkwd)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.testing.PfunctionNameForBaseTypesCS.i_prefixS)), (common.StringCatter)(new common.StringCatter("Boolean"))); } })); }
else if(scrutineeNode instanceof silver.definition.type.syntax.PfloatTypeExpr) { final common.Thunk<Object> __SV_LOCAL___pv8587___sv_tmp_pv_8588 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.type.syntax.TFloat_tkwd)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.testing.PfunctionNameForBaseTypesCS.i_prefixS)), (common.StringCatter)(new common.StringCatter("Float"))); } })); }
else if(scrutineeNode instanceof silver.definition.type.syntax.PintegerTypeExpr) { final common.Thunk<Object> __SV_LOCAL___pv8589___sv_tmp_pv_8590 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.type.syntax.TInteger_tkwd)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.testing.PfunctionNameForBaseTypesCS.i_prefixS)), (common.StringCatter)(new common.StringCatter("Integer"))); } })); }
else if(scrutineeNode instanceof silver.definition.type.syntax.PstringTypeExpr) { final common.Thunk<Object> __SV_LOCAL___pv8591___sv_tmp_pv_8592 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.type.syntax.TString_tkwd)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.testing.PfunctionNameForBaseTypesCS.i_prefixS)), (common.StringCatter)(new common.StringCatter("String"))); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_8580___fail_8581.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.testing.PfunctionNameForBaseTypesCS.i_valueType)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:testing:functionNameForBaseTypesCS", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfunctionNameForBaseTypesCS.invoke(children[0], children[1]);
		}
	};
}