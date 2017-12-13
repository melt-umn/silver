
package silver.definition.flow.syntax;

// top::NtList ::= h::NtName 
public final class PoneNtList extends silver.definition.flow.syntax.NNtList {

	public static final int i_h = 0;


	public static final Class<?> childTypes[] = {silver.definition.flow.syntax.NNtName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_syntax_oneNtList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.syntax.NNtList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.syntax.NNtList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.flow.syntax.NNtName.num_inh_attrs];

	}

	public PoneNtList(final Object c_h, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;

	}

	private Object child_h;
	public final silver.definition.flow.syntax.NNtName getChild_h() {
		return (silver.definition.flow.syntax.NNtName) (child_h = common.Util.demand(child_h));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:syntax:oneNtList erroneously claimed to forward");
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
		return "silver:definition:flow:syntax:oneNtList";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp
		silver.definition.flow.syntax.PoneNtList.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_NtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PoneNtList.i_h).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_NtName)); } };
		// top.errors := h.errors
		if(silver.definition.flow.syntax.PoneNtList.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_NtList] == null)
			silver.definition.flow.syntax.PoneNtList.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_NtList] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_NtList);
		((common.CollectionAttribute)silver.definition.flow.syntax.PoneNtList.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_NtList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.flow.syntax.PoneNtList.i_h).synthesized(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_NtName)); } });
		// top.flowDefs = h.flowDefs
		silver.definition.flow.syntax.PoneNtList.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_syntax_NtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.flow.syntax.PoneNtList.i_h).synthesized(silver.definition.flow.syntax.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_syntax_NtName)); } };

	}

	public static final common.NodeFactory<PoneNtList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneNtList> {

		@Override
		public PoneNtList invoke(final Object[] children, final Object[] annotations) {
			return new PoneNtList(children[0], annotations[0]);
		}
	};

}
