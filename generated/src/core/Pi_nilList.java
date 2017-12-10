
package core;

// l::List<a> ::= 
public final class Pi_nilList extends core.NList {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__core_i_nilList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pi_nilList() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production core:i_nilList erroneously claimed to forward");
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
		return "core:i_nilList";
	}

	static void initProductionAttributeDefinitions() {
		// l.i_emptyList = true
		core.Pi_nilList.synthesizedAttributes[core.Init.core_i_emptyList__ON__core_List] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// l.i_lengthList = 0
		core.Pi_nilList.synthesizedAttributes[core.Init.core_i_lengthList__ON__core_List] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// l.i_headList = error("requested head of nil")
		core.Pi_nilList.synthesizedAttributes[core.Init.core_i_headList__ON__core_List] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke((new common.StringCatter("requested head of nil")))); } };
		// l.i_tailList = error("requested tail of nil")
		core.Pi_nilList.synthesizedAttributes[core.Init.core_i_tailList__ON__core_List] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Perror.invoke((new common.StringCatter("requested tail of nil")))); } };

	}

	public static final common.NodeFactory<Pi_nilList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pi_nilList> {

		@Override
		public Pi_nilList invoke(final Object[] children, final Object[] annotations) {
			return new Pi_nilList();
		}
	};

}
