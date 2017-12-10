
package core;

public final class Psnd extends common.FunctionNode {

	public static final int i_p = 0;


	public static final Class<?> childTypes[] = { core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__core_snd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_p] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public Psnd(final Object c_p) {
		this.child_p = c_p;

	}

	private Object child_p;
	public final core.NPair getChild_p() {
		return (core.NPair) (child_p = common.Util.demand(child_p));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "core:snd";
	}

	public static Object invoke(final Object c_p) {
		try {
		final common.DecoratedNode context = new Psnd(c_p).decorate();
		//p.snd
		return (Object)(((Object)context.childDecorated(core.Psnd.i_p).synthesized(core.Init.core_snd__ON__core_Pair)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:snd", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Psnd.invoke(children[0]);
		}
	};
}