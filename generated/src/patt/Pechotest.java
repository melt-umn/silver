
package patt;

public final class Pechotest extends common.FunctionNode {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = { Object.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_echotest;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pechotest(final Object c_s) {
		this.child_s = c_s;

	}

	private Object child_s;
	public final Object getChild_s() {
		return (Object) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		return "patt:echotest";
	}

	public static Object invoke(final Object c_s) {
		try {
		final common.DecoratedNode context = new Pechotest(c_s).decorate();
		//case decorate echo(,) with {input = s;} of t -> t.output end
		return (Object)(((Object)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_465_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NEcho)new patt.Pecho()).decorate(context, common.Util.populateInh(patt.NEcho.num_inh_attrs, new int[]{patt.Init.patt_input__ON__patt_Echo}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(patt.Pechotest.i_s)); } }})); } };
return ((Object)((common.DecoratedNode)(__SV_LOCAL_465_t.eval())).synthesized(patt.Init.patt_output__ON__patt_Echo)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:echotest", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pechotest.invoke(children[0]);
		}
	};
}