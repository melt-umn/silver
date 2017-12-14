
package silver.definition.env;

public final class PannoInstanceToNamed extends common.FunctionNode {

	public static final int i_nt = 0;
	public static final int i_anno = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class,silver.definition.env.NDclInfo.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_annoInstanceToNamed;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_nt] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_anno] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PannoInstanceToNamed(final Object c_nt, final Object c_anno) {
		this.child_nt = c_nt;
		this.child_anno = c_anno;

	}

	private Object child_nt;
	public final silver.definition.type.NType getChild_nt() {
		return (silver.definition.type.NType) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_anno;
	public final silver.definition.env.NDclInfo getChild_anno() {
		return (silver.definition.env.NDclInfo) (child_anno = common.Util.demand(child_anno));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();
			case i_anno: return getChild_anno();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_anno: return child_anno;

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
		return "silver:definition:env:annoInstanceToNamed";
	}

	public static silver.definition.env.NNamedSignatureElement invoke(final Object c_nt, final Object c_anno) {
		try {
		final common.DecoratedNode context = new PannoInstanceToNamed(c_nt, c_anno).decorate();
		//namedSignatureElement(anno.attrOccurring, anno.typerep)
		return (silver.definition.env.NNamedSignatureElement)(((silver.definition.env.NNamedSignatureElement)new silver.definition.env.PnamedSignatureElement(context.childDecoratedSynthesizedLazy(silver.definition.env.PannoInstanceToNamed.i_anno, silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo), context.childDecoratedSynthesizedLazy(silver.definition.env.PannoInstanceToNamed.i_anno, silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:annoInstanceToNamed", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NNamedSignatureElement> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NNamedSignatureElement> {
		@Override
		public silver.definition.env.NNamedSignatureElement invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PannoInstanceToNamed.invoke(children[0], children[1]);
		}
	};
}