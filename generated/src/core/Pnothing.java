
package core;

// top::Maybe<a> ::= 
public final class Pnothing extends core.NMaybe {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__core_nothing;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NMaybe.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NMaybe.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pnothing() {
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
		throw new common.exceptions.SilverInternalError("Production core:nothing erroneously claimed to forward");
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
		return "core:nothing";
	}

	static void initProductionAttributeDefinitions() {
		// top.fromJust = error("fromJust accessed on a Maybe that was actually nothing!")
		core.Pnothing.synthesizedAttributes[core.Init.core_fromJust__ON__core_Maybe] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke((new common.StringCatter("fromJust accessed on a Maybe that was actually nothing!")))); } };
		// top.isJust = false
		core.Pnothing.synthesizedAttributes[core.Init.core_isJust__ON__core_Maybe] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };

	}

	public static final common.NodeFactory<Pnothing> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pnothing> {

		@Override
		public Pnothing invoke(final Object[] children, final Object[] annotations) {
			return new Pnothing();
		}
	};

}
