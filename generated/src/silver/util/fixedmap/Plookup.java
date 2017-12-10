
package silver.util.fixedmap;

public final class Plookup extends common.FunctionNode {

	public static final int i_key = 0;
	public static final int i_mp = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,silver.util.fixedmap.NMap.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_fixedmap_lookup;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_mp] = new common.Lazy[silver.util.fixedmap.NMap.num_inh_attrs];

	}

	public Plookup(final Object c_key, final Object c_mp) {
		this.child_key = c_key;
		this.child_mp = c_mp;

	}

	private Object child_key;
	public final common.StringCatter getChild_key() {
		return (common.StringCatter) (child_key = common.Util.demand(child_key));
	}

	private Object child_mp;
	public final silver.util.fixedmap.NMap getChild_mp() {
		return (silver.util.fixedmap.NMap) (child_mp = common.Util.demand(child_mp));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_key: return getChild_key();
			case i_mp: return getChild_mp();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_key: return child_key;
			case i_mp: return child_mp;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:util:fixedmap:lookup";
	}

	public static common.ConsCell invoke(final Object c_key, final Object c_mp) {
		try {
		final common.DecoratedNode context = new Plookup(c_key, c_mp).decorate();
		//mp.treeLookup
		return (common.ConsCell)(((common.ConsCell)context.childDecorated(silver.util.fixedmap.Plookup.i_mp).synthesized(silver.util.fixedmap.Init.silver_util_fixedmap_treeLookup__ON__silver_util_fixedmap_Map)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:fixedmap:lookup", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Plookup.invoke(children[0], children[1]);
		}
	};
}