
package silver.modification.impide;

public final class PcopyIdeResource extends common.FunctionNode {

	public static final int i_r = 0;


	public static final Class<?> childTypes[] = { core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_copyIdeResource;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_r] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PcopyIdeResource(final Object c_r) {
		this.child_r = c_r;

	}

	private Object child_r;
	public final core.NPair getChild_r() {
		return (core.NPair) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r: return child_r;

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
		return "silver:modification:impide:copyIdeResource";
	}

	public static common.StringCatter invoke(final Object c_r) {
		try {
		final common.DecoratedNode context = new PcopyIdeResource(c_r).decorate();
		//"\n      <mkdir dir='${ide.proj.plugin.path}/resource/" ++ r.fst ++ "'/>\n      <copy todir=\"${ide.proj.plugin.path}/resource/" ++ r.fst ++ "\" overwrite=\"false\" filtering=\"false\">\n        <fileset dir=\"${grammar.path}/" ++ r.snd ++ "\"/>\n      </copy>\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\n      <mkdir dir='${ide.proj.plugin.path}/resource/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PcopyIdeResource.i_r).synthesized(core.Init.core_fst__ON__core_Pair)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'/>\n      <copy todir=\"${ide.proj.plugin.path}/resource/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PcopyIdeResource.i_r).synthesized(core.Init.core_fst__ON__core_Pair)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" overwrite=\"false\" filtering=\"false\">\n        <fileset dir=\"${grammar.path}/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PcopyIdeResource.i_r).synthesized(core.Init.core_snd__ON__core_Pair)), (common.StringCatter)(new common.StringCatter("\"/>\n      </copy>\n")))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:copyIdeResource", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcopyIdeResource.invoke(children[0]);
		}
	};
}