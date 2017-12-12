
package silver.json;

public final class PjsonInteger extends common.FunctionNode {

	public static final int i_int = 0;


	public static final Class<?> childTypes[] = { Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_json_jsonInteger;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PjsonInteger(final Object c_int) {
		this.child_int = c_int;

	}

	private Object child_int;
	public final Integer getChild_int() {
		return (Integer) (child_int = common.Util.demand(child_int));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_int: return getChild_int();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_int: return child_int;

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
		return "silver:json:jsonInteger";
	}

	public static silver.json.NJson invoke(final Object c_int) {
		try {
		final common.DecoratedNode context = new PjsonInteger(c_int).decorate();
		//jsonFloat(toFloat(int))
		return (silver.json.NJson)(((silver.json.NJson)new silver.json.PjsonFloat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Float.valueOf(((Integer)((Integer)context.childAsIs(silver.json.PjsonInteger.i_int))).floatValue()); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:json:jsonInteger", t);
		}
	}

	public static final common.NodeFactory<silver.json.NJson> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.json.NJson> {
		@Override
		public silver.json.NJson invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PjsonInteger.invoke(children[0]);
		}
	};
}