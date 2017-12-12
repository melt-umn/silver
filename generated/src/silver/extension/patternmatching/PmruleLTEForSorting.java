
package silver.extension.patternmatching;

public final class PmruleLTEForSorting extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = { silver.extension.patternmatching.NAbstractMatchRule.class,silver.extension.patternmatching.NAbstractMatchRule.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_mruleLTEForSorting;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_a] = new common.Lazy[silver.extension.patternmatching.NAbstractMatchRule.num_inh_attrs];
	childInheritedAttributes[i_b] = new common.Lazy[silver.extension.patternmatching.NAbstractMatchRule.num_inh_attrs];

	}

	public PmruleLTEForSorting(final Object c_a, final Object c_b) {
		this.child_a = c_a;
		this.child_b = c_b;

	}

	private Object child_a;
	public final silver.extension.patternmatching.NAbstractMatchRule getChild_a() {
		return (silver.extension.patternmatching.NAbstractMatchRule) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final silver.extension.patternmatching.NAbstractMatchRule getChild_b() {
		return (silver.extension.patternmatching.NAbstractMatchRule) (child_b = common.Util.demand(child_b));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_b: return getChild_b();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_b: return child_b;

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
		return "silver:extension:patternmatching:mruleLTEForSorting";
	}

	public static Boolean invoke(final Object c_a, final Object c_b) {
		try {
		final common.DecoratedNode context = new PmruleLTEForSorting(c_a, c_b).decorate();
		//a.headPattern.patternSortKey <= b.headPattern.patternSortKey
		return (Boolean)((((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.extension.patternmatching.PmruleLTEForSorting.i_a).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_headPattern__ON__silver_extension_patternmatching_AbstractMatchRule)).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSortKey__ON__silver_extension_patternmatching_Pattern)).toString().compareTo(((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.extension.patternmatching.PmruleLTEForSorting.i_b).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_headPattern__ON__silver_extension_patternmatching_AbstractMatchRule)).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSortKey__ON__silver_extension_patternmatching_Pattern)).toString()) <= 0));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:patternmatching:mruleLTEForSorting", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmruleLTEForSorting.invoke(children[0], children[1]);
		}
	};
}