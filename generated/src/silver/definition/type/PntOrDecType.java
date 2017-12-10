
package silver.definition.type;

// top::Type ::= nt::Type hidden::Type 
public final class PntOrDecType extends silver.definition.type.NType {

	public static final int i_nt = 0;
	public static final int i_hidden = 1;


	public static final Class<?> childTypes[] = {silver.definition.type.NType.class,silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_ntOrDecType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_nt] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_hidden] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PntOrDecType(final Object c_nt, final Object c_hidden) {
		super();
		this.child_nt = c_nt;
		this.child_hidden = c_hidden;

	}

	private Object child_nt;
	public final silver.definition.type.NType getChild_nt() {
		return (silver.definition.type.NType) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_hidden;
	public final silver.definition.type.NType getChild_hidden() {
		return (silver.definition.type.NType) (child_hidden = common.Util.demand(child_hidden));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();
			case i_hidden: return getChild_hidden();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_hidden: return child_hidden;

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
		return ((silver.definition.type.NType)new silver.definition.type.PdecoratedType(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PntOrDecType.i_nt))));
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
		return "silver:definition:type:ntOrDecType";
	}

	static void initProductionAttributeDefinitions() {
		// top.freeVariables = case hidden of varType(_) -> nt.freeVariables | _ -> hidden.freeVariables end
		silver.definition.type.PntOrDecType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5662___fail_5663 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.PntOrDecType.i_hidden).synthesized(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type)); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PvarType) { final common.Thunk<Object> __SV_LOCAL___pv5667___sv_tmp_pv_5668 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (common.ConsCell)((common.ConsCell)context.childDecorated(silver.definition.type.PntOrDecType.i_nt).synthesized(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_5662___fail_5663.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.type.PntOrDecType.i_hidden)); } }).eval()); } };

	}

	public static final common.NodeFactory<PntOrDecType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PntOrDecType> {

		@Override
		public PntOrDecType invoke(final Object[] children, final Object[] annotations) {
			return new PntOrDecType(children[0], children[1]);
		}
	};

}
