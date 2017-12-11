
package silver.extension.list;

// top::Type ::= el::Type 
public final class PlistType extends silver.definition.type.NType {

	public static final int i_el = 0;


	public static final Class<?> childTypes[] = {silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_list_listType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_el] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PlistType(final Object c_el) {
		super();
		this.child_el = c_el;

	}

	private Object child_el;
	public final silver.definition.type.NType getChild_el() {
		return (silver.definition.type.NType) (child_el = common.Util.demand(child_el));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_el: return getChild_el();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_el: return child_el;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.type.NType)new silver.definition.type.PdecoratedType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PnonterminalType((new common.StringCatter("core:List")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.list.PlistType.i_el)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }));
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
		return "silver:extension:list:listType";
	}

	static void initProductionAttributeDefinitions() {
		// top.freeVariables = el.freeVariables
		silver.extension.list.PlistType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.list.PlistType.i_el).synthesized(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type)); } };
		// top.substituted = listType(el.substituted)
		silver.extension.list.PlistType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_substituted__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.extension.list.PlistType(context.childDecoratedSynthesizedLazy(silver.extension.list.PlistType.i_el, silver.definition.type.Init.silver_definition_type_substituted__ON__silver_definition_type_Type))); } };
		// top.typepp = "[" ++ el.typepp ++ "]"
		silver.extension.list.PlistType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_typepp__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.list.PlistType.i_el).synthesized(silver.definition.type.Init.silver_definition_type_typepp__ON__silver_definition_type_Type)), (common.StringCatter)(new common.StringCatter("]")))); } };
		// top.unify = case top.unifyWith of listType(fel) -> unify(el, fel) | decoratedType(nonterminalType("core:List", ftes)) -> unify(el, head(ftes)) | _ -> errorSubst("Tried to unify list with " ++ prettyType(top.unifyWith)) end
		silver.extension.list.PlistType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_unify__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2106___fail_2107 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PerrorSubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Tried to unify list with ")), (common.StringCatter)((common.StringCatter)silver.definition.type.PprettyType.invoke(context.contextInheritedLazy(silver.definition.type.Init.silver_definition_type_unifyWith__ON__silver_definition_type_Type)))); } })); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PdecoratedType) { final common.Thunk<Object> __SV_LOCAL___pv2112___sv_tmp_pv_2111 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.type.NSubstitution)new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PnonterminalType) { final common.Thunk<Object> __SV_LOCAL___pv2115___sv_tmp_pv_2114 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2116___sv_pv_2117_ftes = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (silver.definition.type.NSubstitution)new common.PatternLazy<common.StringCatter, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.StringCatter scrutineeIter) {final common.StringCatter scrutinee = scrutineeIter; if(scrutinee.equals("core:List")) { return (silver.definition.type.NSubstitution)((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2118_ftes = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv2116___sv_pv_2117_ftes.eval())); } };
return ((silver.definition.type.NSubstitution)silver.definition.type.Punify.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.list.PlistType.i_el)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)core.Phead.invoke(__SV_LOCAL_2118_ftes)); } })); } }).eval()); }return ((silver.definition.type.NSubstitution)(__SV_LOCAL_2106___fail_2107.eval()));}}.eval(context, (common.StringCatter)((common.StringCatter)(__SV_LOCAL___pv2115___sv_tmp_pv_2114.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)(__SV_LOCAL_2106___fail_2107.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv2112___sv_tmp_pv_2111.eval()))); }
else if(scrutineeNode instanceof silver.extension.list.PlistType) { final common.Thunk<Object> __SV_LOCAL___pv2120___sv_pv_2119_fel = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.type.NSubstitution)((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2121_fel = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv2120___sv_pv_2119_fel.eval())); } };
return ((silver.definition.type.NSubstitution)silver.definition.type.Punify.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.list.PlistType.i_el)), common.Thunk.transformUndecorate(__SV_LOCAL_2121_fel))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)(__SV_LOCAL_2106___fail_2107.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.type.NType)context.inherited(silver.definition.type.Init.silver_definition_type_unifyWith__ON__silver_definition_type_Type)).decorate(context, (common.Lazy[])null)); } }).eval()); } };
		// top.isDecorable = false
		silver.extension.list.PlistType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.isDecorated = false
		silver.extension.list.PlistType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_isDecorated__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.lengthDispatcher = listLengthBouncer(_,location=_)
		silver.extension.list.PlistType.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_lengthDispatcher__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.extension.list.PlistLengthBouncer.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.appendDispatcher = listPlusPlus(_, _,location=_)
		silver.extension.list.PlistType.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_appendDispatcher__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.extension.list.PlistPlusPlus.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.unparse = "[" ++ el.unparse ++ "]"
		silver.extension.list.PlistType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.list.PlistType.i_el).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)(new common.StringCatter("]")))); } };

	}

	public static final common.NodeFactory<PlistType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlistType> {

		@Override
		public PlistType invoke(final Object[] children, final Object[] annotations) {
			return new PlistType(children[0]);
		}
	};

}
