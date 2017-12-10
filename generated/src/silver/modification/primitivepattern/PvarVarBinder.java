
package silver.modification.primitivepattern;

// top::VarBinder ::= n::Name 
public final class PvarVarBinder extends silver.modification.primitivepattern.NVarBinder {

	public static final int i_n = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_varVarBinder;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.primitivepattern.NVarBinder.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.primitivepattern.NVarBinder.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];

	}

	public PvarVarBinder(final Object c_n, final Object a_core_location) {
		super(a_core_location);
		this.child_n = c_n;

	}

	private Object child_n;
	public final silver.definition.core.NName getChild_n() {
		return (silver.definition.core.NName) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:primitivepattern:varVarBinder erroneously claimed to forward");
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
		return "silver:modification:primitivepattern:varVarBinder";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = n.pp
		silver.modification.primitivepattern.PvarVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_VarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PvarVarBinder.i_n).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)); } };
		// ty = if top.bindingType.isDecorable then decoratedType(top.bindingType) else top.bindingType
		silver.modification.primitivepattern.PvarVarBinder.localAttributes[silver.modification.primitivepattern.Init.ty__ON__silver_modification_primitivepattern_varVarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((silver.definition.type.NType)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingType__ON__silver_modification_primitivepattern_VarBinder)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type)) ? ((silver.definition.type.NType)new silver.definition.type.PdecoratedType(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingType__ON__silver_modification_primitivepattern_VarBinder))) : ((silver.definition.type.NType)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingType__ON__silver_modification_primitivepattern_VarBinder))); } };
		// fName = "__pv" ++ toString(genInt()) ++ ":" ++ n.name
		silver.modification.primitivepattern.PvarVarBinder.localAttributes[silver.modification.primitivepattern.Init.fName__ON__silver_modification_primitivepattern_varVarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("__pv")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PvarVarBinder.i_n).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))))); } };
		// top.flowProjections = if top.bindingType.isDecorable then [ patternVarProjection(top.bindingName, top.bindingType.typeName, fName) ] else []
		silver.modification.primitivepattern.PvarVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_modification_primitivepattern_flowProjections__ON__silver_modification_primitivepattern_VarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((silver.definition.type.NType)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingType__ON__silver_modification_primitivepattern_VarBinder)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NPatternVarProjection)new silver.definition.flow.ast.PpatternVarProjection(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingName__ON__silver_modification_primitivepattern_VarBinder), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingType__ON__silver_modification_primitivepattern_VarBinder)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.localAsIsLazy(silver.modification.primitivepattern.Init.fName__ON__silver_modification_primitivepattern_varVarBinder))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
		// vt = if top.bindingType.isDecorable then hasVertex(anonVertexType(fName)) else noVertex()
		silver.modification.primitivepattern.PvarVarBinder.localAttributes[silver.modification.primitivepattern.Init.vt__ON__silver_modification_primitivepattern_varVarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((silver.definition.type.NType)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingType__ON__silver_modification_primitivepattern_VarBinder)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type)) ? ((silver.definition.flow.ast.NExprVertexInfo)new silver.definition.flow.ast.PhasVertex(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PanonVertexType(context.localAsIsLazy(silver.modification.primitivepattern.Init.fName__ON__silver_modification_primitivepattern_varVarBinder))); } })) : ((silver.definition.flow.ast.NExprVertexInfo)new silver.definition.flow.ast.PnoVertex())); } };
		// deps = if top.bindingType.isDecorable then depsForTakingRef(anonVertexType(fName), ty.typeName, top.flowEnv) else []
		silver.modification.primitivepattern.PvarVarBinder.localAttributes[silver.modification.primitivepattern.Init.deps__ON__silver_modification_primitivepattern_varVarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((silver.definition.type.NType)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingType__ON__silver_modification_primitivepattern_VarBinder)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type)) ? ((common.ConsCell)silver.definition.flow.env.PdepsForTakingRef.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PanonVertexType(context.localAsIsLazy(silver.modification.primitivepattern.Init.fName__ON__silver_modification_primitivepattern_varVarBinder))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.ty__ON__silver_modification_primitivepattern_varVarBinder).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_definition_flow_env_flowEnv__ON__silver_modification_primitivepattern_VarBinder))) : ((common.ConsCell)core.Pnil.invoke())); } };
		// top.defs = [ lexicalLocalDef(top.grammarName, n.location, fName, ty, vt, deps) ]
		silver.modification.primitivepattern.PvarVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_defs__ON__silver_modification_primitivepattern_VarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.let_fix.PlexicalLocalDef.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_definition_core_grammarName__ON__silver_modification_primitivepattern_VarBinder), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.primitivepattern.PvarVarBinder.i_n).undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.primitivepattern.Init.fName__ON__silver_modification_primitivepattern_varVarBinder), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.ty__ON__silver_modification_primitivepattern_varVarBinder)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.vt__ON__silver_modification_primitivepattern_varVarBinder)), context.localAsIsLazy(silver.modification.primitivepattern.Init.deps__ON__silver_modification_primitivepattern_varVarBinder))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// actualTy = performSubstitution(ty, top.finalSubst)
		silver.modification.primitivepattern.PvarVarBinder.localAttributes[silver.modification.primitivepattern.Init.actualTy__ON__silver_modification_primitivepattern_varVarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.ty__ON__silver_modification_primitivepattern_varVarBinder)), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_VarBinder))); } };
		// top.translation = makeSpecialLocalBinding(fName, "(" ++ actualTy.transType ++ ")scrutinee." ++ (if top.bindingType.isDecorable then "childDecorated(" else "childAsIs(") ++ toString(top.bindingIndex) ++ ")", actualTy.transType)
		silver.modification.primitivepattern.PvarVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_VarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.modification.let_fix.java.PmakeSpecialLocalBinding.invoke(context.localAsIsLazy(silver.modification.primitivepattern.Init.fName__ON__silver_modification_primitivepattern_varVarBinder), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.actualTy__ON__silver_modification_primitivepattern_varVarBinder).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")scrutinee.")), (common.StringCatter)new common.StringCatter((common.StringCatter)(((Boolean)((silver.definition.type.NType)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingType__ON__silver_modification_primitivepattern_VarBinder)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type)) ? (new common.StringCatter("childDecorated(")) : (new common.StringCatter("childAsIs("))), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingIndex__ON__silver_modification_primitivepattern_VarBinder)))), (common.StringCatter)(new common.StringCatter(")"))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.actualTy__ON__silver_modification_primitivepattern_varVarBinder).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)); } })); } };
		// top.errors := if ! isUpper(substring(0, 1, n.name)) then [] else [ err(top.location, "Pattern variables must start with a lower case letter") ]
		if(silver.modification.primitivepattern.PvarVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinder] == null)
			silver.modification.primitivepattern.PvarVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinder] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinder);
		((common.CollectionAttribute)silver.modification.primitivepattern.PvarVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinder]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.PisUpper.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), Integer.valueOf((int)1), context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PvarVarBinder.i_n, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))); } }))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NVarBinder)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Pattern variables must start with a lower case letter")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.errors <- case getValueDcl(n.name, top.env) of prodDcl(_, _, _, _)::_ -> [ err(top.location, "Pattern variables cannot have the same name as productions (to avoid confusion)") ] | _ -> [] end
		if(silver.modification.primitivepattern.PvarVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinder] == null)
			silver.modification.primitivepattern.PvarVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinder] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinder);
		((common.CollectionAttribute)silver.modification.primitivepattern.PvarVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinder]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7866___fail_7867 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_7873___sv_tmp_pv_7872 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_7874___sv_tmp_pv_7875 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.PprodDcl) { final common.Thunk<Object> __SV_LOCAL___pv7877___sv_tmp_pv_7878 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7879___sv_tmp_pv_7880 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv7881___sv_tmp_pv_7882 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv7883___sv_tmp_pv_7884 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(3); } };
 return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NVarBinder)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Pattern variables cannot have the same name as productions (to avoid confusion)")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_7866___fail_7867.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.env.NDclInfo)(__SV_LOCAL_7873___sv_tmp_pv_7872.eval())).decorate(context, (common.Lazy[])null)); }return ((common.ConsCell)(__SV_LOCAL_7866___fail_7867.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.env.PgetValueDcl.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PvarVarBinder.i_n, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_definition_env_env__ON__silver_modification_primitivepattern_VarBinder)))); } }).eval()); } });

	}

	public static final common.NodeFactory<PvarVarBinder> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PvarVarBinder> {

		@Override
		public PvarVarBinder invoke(final Object[] children, final Object[] annotations) {
			return new PvarVarBinder(children[0], annotations[0]);
		}
	};

}
