
package core;

public final class Pexplode extends common.FunctionNode {

	public static final int i_sep = 0;
	public static final int i_str = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_explode;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pexplode(final Object c_sep, final Object c_str) {
		this.child_sep = c_sep;
		this.child_str = c_str;

	}

	private Object child_sep;
	public final common.StringCatter getChild_sep() {
		return (common.StringCatter) (child_sep = common.Util.demand(child_sep));
	}

	private Object child_str;
	public final common.StringCatter getChild_str() {
		return (common.StringCatter) (child_str = common.Util.demand(child_str));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sep: return getChild_sep();
			case i_str: return getChild_str();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sep: return child_sep;
			case i_str: return child_str;

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
		return "core:explode";
	}

	public static common.ConsCell invoke(final Object c_sep, final Object c_str) {
		try {
		final common.DecoratedNode context = new Pexplode(c_sep, c_str).decorate();
		//if sep == "" then explodeSingle(str) else if str == "" then [] else explodeNormal(sep, str)
		return (common.ConsCell)((((common.StringCatter)context.childAsIs(core.Pexplode.i_sep)).equals((new common.StringCatter(""))) ? ((common.ConsCell)core.PexplodeSingle.invoke(context.childAsIsLazy(core.Pexplode.i_str))) : (((common.StringCatter)context.childAsIs(core.Pexplode.i_str)).equals((new common.StringCatter(""))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.PexplodeNormal.invoke(context.childAsIsLazy(core.Pexplode.i_sep), context.childAsIsLazy(core.Pexplode.i_str))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:explode", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pexplode.invoke(children[0], children[1]);
		}
	};
}