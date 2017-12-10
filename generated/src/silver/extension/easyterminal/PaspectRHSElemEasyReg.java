
package silver.extension.easyterminal;

// top::AspectRHSElem ::= reg::EasyTerminalRef 
public final class PaspectRHSElemEasyReg extends silver.definition.core.NAspectRHSElem {

	public static final int i_reg = 0;


	public static final Class<?> childTypes[] = {silver.extension.easyterminal.NEasyTerminalRef.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_easyterminal_aspectRHSElemEasyReg;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAspectRHSElem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAspectRHSElem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_reg] = new common.Lazy[silver.extension.easyterminal.NEasyTerminalRef.num_inh_attrs];

	}

	public PaspectRHSElemEasyReg(final Object c_reg, final Object a_core_location) {
		super(a_core_location);
		this.child_reg = c_reg;

	}

	private Object child_reg;
	public final silver.extension.easyterminal.NEasyTerminalRef getChild_reg() {
		return (silver.extension.easyterminal.NEasyTerminalRef) (child_reg = common.Util.demand(child_reg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_reg: return getChild_reg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_reg: return child_reg;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return ((silver.definition.core.NAspectRHSElem)new silver.definition.core.PaspectRHSElemNone(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TUnderScore_t((new common.StringCatter("_")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.easyterminal.NEasyTerminalRef)context.childDecorated(silver.extension.easyterminal.PaspectRHSElemEasyReg.i_reg).undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:easyterminal:aspectRHSElemEasyReg";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = reg.pp
		silver.extension.easyterminal.PaspectRHSElemEasyReg.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.easyterminal.PaspectRHSElemEasyReg.i_reg).synthesized(silver.extension.easyterminal.Init.silver_definition_env_pp__ON__silver_extension_easyterminal_EasyTerminalRef)); } };
		// top.errors <- reg.errors
		if(silver.extension.easyterminal.PaspectRHSElemEasyReg.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHSElem] == null)
			silver.extension.easyterminal.PaspectRHSElemEasyReg.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHSElem] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHSElem);
		((common.CollectionAttribute)silver.extension.easyterminal.PaspectRHSElemEasyReg.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHSElem]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.easyterminal.PaspectRHSElemEasyReg.i_reg).synthesized(silver.extension.easyterminal.Init.silver_definition_core_errors__ON__silver_extension_easyterminal_EasyTerminalRef)); } });

	}

	public static final common.NodeFactory<PaspectRHSElemEasyReg> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaspectRHSElemEasyReg> {

		@Override
		public PaspectRHSElemEasyReg invoke(final Object[] children, final Object[] annotations) {
			return new PaspectRHSElemEasyReg(children[0], annotations[0]);
		}
	};

}
