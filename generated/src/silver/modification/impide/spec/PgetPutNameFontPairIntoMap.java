
package silver.modification.impide.spec;

public final class PgetPutNameFontPairIntoMap extends common.FunctionNode {

	public static final int i_tokenNameAndFontName = 0;


	public static final Class<?> childTypes[] = { core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_getPutNameFontPairIntoMap;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_tokenNameAndFontName] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PgetPutNameFontPairIntoMap(final Object c_tokenNameAndFontName) {
		this.child_tokenNameAndFontName = c_tokenNameAndFontName;

	}

	private Object child_tokenNameAndFontName;
	public final core.NPair getChild_tokenNameAndFontName() {
		return (core.NPair) (child_tokenNameAndFontName = common.Util.demand(child_tokenNameAndFontName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tokenNameAndFontName: return getChild_tokenNameAndFontName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tokenNameAndFontName: return child_tokenNameAndFontName;

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
		return "silver:modification:impide:spec:getPutNameFontPairIntoMap";
	}

	public static common.StringCatter invoke(final Object c_tokenNameAndFontName) {
		try {
		final common.DecoratedNode context = new PgetPutNameFontPairIntoMap(c_tokenNameAndFontName).decorate();
		//"map.put(\"" ++ tokenNameAndFontName.fst ++ "\", " ++ "TokenType." ++ (if tokenNameAndFontName.snd != "" then tokenNameAndFontName.snd else "DEFAULT") ++ ");"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("map.put(\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PgetPutNameFontPairIntoMap.i_tokenNameAndFontName).synthesized(core.Init.core_fst__ON__core_Pair)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("TokenType.")), (common.StringCatter)new common.StringCatter((common.StringCatter)(!((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PgetPutNameFontPairIntoMap.i_tokenNameAndFontName).synthesized(core.Init.core_snd__ON__core_Pair)).equals((new common.StringCatter(""))) ? ((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PgetPutNameFontPairIntoMap.i_tokenNameAndFontName).synthesized(core.Init.core_snd__ON__core_Pair)) : (new common.StringCatter("DEFAULT"))), (common.StringCatter)(new common.StringCatter(");"))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:spec:getPutNameFontPairIntoMap", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetPutNameFontPairIntoMap.invoke(children[0]);
		}
	};
}