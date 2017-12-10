
package patt;

// top::Existential ::= arg::a fun::(String ::= a) 
public final class Pexistentialprod extends patt.NExistential {

	public static final int i_arg = 0;
	public static final int i_fun = 1;


	public static final Class<?> childTypes[] = {Object.class,common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__patt_existentialprod;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[patt.NExistential.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[patt.NExistential.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pexistentialprod(final Object c_arg, final Object c_fun) {
		super();
		this.child_arg = c_arg;
		this.child_fun = c_fun;

	}

	private Object child_arg;
	public final Object getChild_arg() {
		return (Object) (child_arg = common.Util.demand(child_arg));
	}

	private Object child_fun;
	public final common.NodeFactory<common.StringCatter> getChild_fun() {
		return (common.NodeFactory<common.StringCatter>) (child_fun = common.Util.demand(child_fun));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_arg: return getChild_arg();
			case i_fun: return getChild_fun();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_arg: return child_arg;
			case i_fun: return child_fun;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production patt:existentialprod erroneously claimed to forward");
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
		return "patt:existentialprod";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<Pexistentialprod> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pexistentialprod> {

		@Override
		public Pexistentialprod invoke(final Object[] children, final Object[] annotations) {
			return new Pexistentialprod(children[0], children[1]);
		}
	};

}
