
package core;

// top::Either<a b> ::= value::a 
public final class Pleft extends core.NEither {

	public static final int i_value = 0;


	public static final Class<?> childTypes[] = {Object.class};

	public static final int num_local_attrs = Init.count_local__ON__core_left;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NEither.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NEither.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pleft(final Object c_value) {
		super();
		this.child_value = c_value;

	}

	private Object child_value;
	public final Object getChild_value() {
		return (Object) (child_value = common.Util.demand(child_value));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_value: return getChild_value();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_value: return child_value;

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
		throw new common.exceptions.SilverInternalError("Production core:left erroneously claimed to forward");
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
		return "core:left";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<Pleft> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pleft> {

		@Override
		public Pleft invoke(final Object[] children, final Object[] annotations) {
			return new Pleft(children[0]);
		}
	};

}
