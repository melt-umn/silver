
package silver.extension.patternmatching;

// top::Pattern ::= '[' ps::PatternList ']' 
public final class PlistPattern extends silver.extension.patternmatching.NPattern {

	public static final int i__G_2 = 0;
	public static final int i_ps = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.extension.list.TLSqr_t.class,silver.extension.patternmatching.NPatternList.class,silver.extension.list.TRSqr_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_listPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ps] = new common.Lazy[silver.extension.patternmatching.NPatternList.num_inh_attrs];

	}

	public PlistPattern(final Object c__G_2, final Object c_ps, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_ps = c_ps;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.extension.list.TLSqr_t getChild__G_2() {
		return (silver.extension.list.TLSqr_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ps;
	public final silver.extension.patternmatching.NPatternList getChild_ps() {
		return (silver.extension.patternmatching.NPatternList) (child_ps = common.Util.demand(child_ps));
	}

	private Object child__G_0;
	public final silver.extension.list.TRSqr_t getChild__G_0() {
		return (silver.extension.list.TRSqr_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_ps: return getChild_ps();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_ps: return child_ps;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.extension.patternmatching.NPattern)context.childDecorated(silver.extension.patternmatching.PlistPattern.i_ps).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_asListPattern__ON__silver_extension_patternmatching_PatternList));
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
		return "silver:extension:patternmatching:listPattern";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "[" ++ ps.pp ++ "]"
		silver.extension.patternmatching.PlistPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PlistPattern.i_ps).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_PatternList)), (common.StringCatter)(new common.StringCatter("]")))); } };

	}

	public static final common.NodeFactory<PlistPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlistPattern> {

		@Override
		public PlistPattern invoke(final Object[] children, final Object[] annotations) {
			return new PlistPattern(children[0], children[1], children[2], annotations[0]);
		}
	};

}
