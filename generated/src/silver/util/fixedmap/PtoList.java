
package silver.util.fixedmap;

public final class PtoList extends common.FunctionNode {

	public static final int i_mp = 0;


	public static final Class<?> childTypes[] = { silver.util.fixedmap.NMap.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_fixedmap_toList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_mp] = new common.Lazy[silver.util.fixedmap.NMap.num_inh_attrs];

	}

	public PtoList(final Object c_mp) {
		this.child_mp = c_mp;

	}

	private Object child_mp;
	public final silver.util.fixedmap.NMap getChild_mp() {
		return (silver.util.fixedmap.NMap) (child_mp = common.Util.demand(child_mp));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_mp: return getChild_mp();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_mp: return child_mp;

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
		return "silver:util:fixedmap:toList";
	}

	public static common.ConsCell invoke(final Object c_mp) {
		try {
		final common.DecoratedNode context = new PtoList(c_mp).decorate();
		//mp.treeToList
		return (common.ConsCell)(((common.ConsCell)context.childDecorated(silver.util.fixedmap.PtoList.i_mp).synthesized(silver.util.fixedmap.Init.silver_util_fixedmap_treeToList__ON__silver_util_fixedmap_Map)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:fixedmap:toList", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoList.invoke(children[0]);
		}
	};
}