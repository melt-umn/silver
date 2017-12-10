
package silver.modification.collection;

// top::NameOrBOperator ::= q::QName 
public final class PnameOperator extends silver.modification.collection.NNameOrBOperator {

	public static final int i_q = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_collection_nameOperator;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.collection.NNameOrBOperator.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.collection.NNameOrBOperator.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_q] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PnameOperator(final Object c_q, final Object a_core_location) {
		super(a_core_location);
		this.child_q = c_q;

	}

	private Object child_q;
	public final silver.definition.core.NQName getChild_q() {
		return (silver.definition.core.NQName) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:collection:nameOperator erroneously claimed to forward");
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
		return "silver:modification:collection:nameOperator";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = q.pp
		silver.modification.collection.PnameOperator.synthesizedAttributes[silver.modification.collection.Init.silver_definition_env_pp__ON__silver_modification_collection_NameOrBOperator] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.collection.PnameOperator.i_q).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// top.operation = case q.lookupValue.dcl of funDcl(_, _, _) -> functionOperation(q.lookupValue.fullName) | prodDcl(_, _, _, _) -> productionOperation(q.lookupValue.fullName) | _ -> error("INTERNAL ERROR: operation attribute demanded for non-function or production.") end
		silver.modification.collection.PnameOperator.synthesizedAttributes[silver.modification.collection.Init.silver_modification_collection_operation__ON__silver_modification_collection_NameOrBOperator] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.modification.collection.NOperation)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6843___fail_6844 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.collection.NOperation)core.Perror.invoke((new common.StringCatter("INTERNAL ERROR: operation attribute demanded for non-function or production.")))); } };
return new common.PatternLazy<common.DecoratedNode, silver.modification.collection.NOperation>() { public final silver.modification.collection.NOperation eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.PfunDcl) { final common.Thunk<Object> __SV_LOCAL___pv6848___sv_tmp_pv_6849 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6850___sv_tmp_pv_6851 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6852___sv_tmp_pv_6853 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.modification.collection.NOperation)((silver.modification.collection.NOperation)new silver.modification.collection.PfunctionOperation(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.modification.collection.PnameOperator.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })); }
else if(scrutineeNode instanceof silver.definition.env.PprodDcl) { final common.Thunk<Object> __SV_LOCAL___pv6855___sv_tmp_pv_6856 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6857___sv_tmp_pv_6858 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6859___sv_tmp_pv_6860 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv6861___sv_tmp_pv_6862 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(3); } };
 return (silver.modification.collection.NOperation)((silver.modification.collection.NOperation)new silver.modification.collection.PproductionOperation(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.modification.collection.PnameOperator.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.modification.collection.NOperation)(__SV_LOCAL_6843___fail_6844.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.modification.collection.PnameOperator.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null)); } }).eval()); } };
		// top.errors := q.lookupValue.errors
		if(silver.modification.collection.PnameOperator.synthesizedAttributes[silver.modification.collection.Init.silver_definition_core_errors__ON__silver_modification_collection_NameOrBOperator] == null)
			silver.modification.collection.PnameOperator.synthesizedAttributes[silver.modification.collection.Init.silver_definition_core_errors__ON__silver_modification_collection_NameOrBOperator] = new silver.definition.core.CAerrors(silver.modification.collection.Init.silver_definition_core_errors__ON__silver_modification_collection_NameOrBOperator);
		((common.CollectionAttribute)silver.modification.collection.PnameOperator.synthesizedAttributes[silver.modification.collection.Init.silver_definition_core_errors__ON__silver_modification_collection_NameOrBOperator]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.collection.PnameOperator.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } });
		// checkOperationType = check(freshenCompletely(q.lookupValue.typerep), functionType(top.operatorForType, [ top.operatorForType, top.operatorForType ], []))
		silver.modification.collection.PnameOperator.localAttributes[silver.modification.collection.Init.checkOperationType__ON__silver_modification_collection_nameOperator] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshenCompletely.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.modification.collection.PnameOperator.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PfunctionType(context.contextInheritedLazy(silver.modification.collection.Init.silver_modification_collection_operatorForType__ON__silver_modification_collection_NameOrBOperator), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.modification.collection.Init.silver_modification_collection_operatorForType__ON__silver_modification_collection_NameOrBOperator), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.modification.collection.Init.silver_modification_collection_operatorForType__ON__silver_modification_collection_NameOrBOperator), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };
		// checkOperationType.downSubst = emptySubst()
		silver.modification.collection.PnameOperator.localInheritedAttributes[silver.modification.collection.Init.checkOperationType__ON__silver_modification_collection_nameOperator][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke()); } };
		// checkOperationType.finalSubst = checkOperationType.upSubst
		silver.modification.collection.PnameOperator.localInheritedAttributes[silver.modification.collection.Init.checkOperationType__ON__silver_modification_collection_nameOperator][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.collection.Init.checkOperationType__ON__silver_modification_collection_nameOperator).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// operationErrors = if ! checkOperationType.typeerror then [] else [ err(top.location, q.pp ++ " must be of type " ++ checkOperationType.rightpp ++ " instead it is of type " ++ checkOperationType.leftpp) ]
		silver.modification.collection.PnameOperator.localAttributes[silver.modification.collection.Init.operationErrors__ON__silver_modification_collection_nameOperator] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.localDecorated(silver.modification.collection.Init.checkOperationType__ON__silver_modification_collection_nameOperator).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.collection.NNameOrBOperator)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PnameOperator.i_q).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" must be of type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.collection.Init.checkOperationType__ON__silver_modification_collection_nameOperator).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" instead it is of type ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.collection.Init.checkOperationType__ON__silver_modification_collection_nameOperator).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck)))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } };
		// top.errors <- if ! null(q.lookupValue.errors) then [] else case q.lookupValue.dcl of funDcl(_, _, _) -> operationErrors | prodDcl(_, _, _, _) -> operationErrors | _ -> [ err(top.location, q.pp ++ " is not a valid operator for collections.") ] end
		if(silver.modification.collection.PnameOperator.synthesizedAttributes[silver.modification.collection.Init.silver_definition_core_errors__ON__silver_modification_collection_NameOrBOperator] == null)
			silver.modification.collection.PnameOperator.synthesizedAttributes[silver.modification.collection.Init.silver_definition_core_errors__ON__silver_modification_collection_NameOrBOperator] = new silver.definition.core.CAerrors(silver.modification.collection.Init.silver_definition_core_errors__ON__silver_modification_collection_NameOrBOperator);
		((common.CollectionAttribute)silver.modification.collection.PnameOperator.synthesizedAttributes[silver.modification.collection.Init.silver_definition_core_errors__ON__silver_modification_collection_NameOrBOperator]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.collection.PnameOperator.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6878___fail_6879 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.collection.NNameOrBOperator)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PnameOperator.i_q).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter(" is not a valid operator for collections."))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.PfunDcl) { final common.Thunk<Object> __SV_LOCAL___pv6884___sv_tmp_pv_6885 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6886___sv_tmp_pv_6887 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6888___sv_tmp_pv_6889 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (common.ConsCell)((common.ConsCell)context.localAsIs(silver.modification.collection.Init.operationErrors__ON__silver_modification_collection_nameOperator)); }
else if(scrutineeNode instanceof silver.definition.env.PprodDcl) { final common.Thunk<Object> __SV_LOCAL___pv6890___sv_tmp_pv_6891 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6892___sv_tmp_pv_6893 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6894___sv_tmp_pv_6895 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv6896___sv_tmp_pv_6897 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(3); } };
 return (common.ConsCell)((common.ConsCell)context.localAsIs(silver.modification.collection.Init.operationErrors__ON__silver_modification_collection_nameOperator)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_6878___fail_6879.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.modification.collection.PnameOperator.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null)); } }).eval())); } });

	}

	public static final common.NodeFactory<PnameOperator> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnameOperator> {

		@Override
		public PnameOperator invoke(final Object[] children, final Object[] annotations) {
			return new PnameOperator(children[0], annotations[0]);
		}
	};

}
