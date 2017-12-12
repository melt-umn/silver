
package core;

// top::TerminalDescriptor ::= lexeme::String lexerClasses::[String] terminalName::String terminalLocation::Location 
public final class PterminalDescriptor extends core.NTerminalDescriptor {

	public static final int i_lexeme = 0;
	public static final int i_lexerClasses = 1;
	public static final int i_terminalName = 2;
	public static final int i_terminalLocation = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class,common.StringCatter.class,core.NLocation.class};

	public static final int num_local_attrs = Init.count_local__ON__core_terminalDescriptor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NTerminalDescriptor.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NTerminalDescriptor.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_terminalLocation] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PterminalDescriptor(final Object c_lexeme, final Object c_lexerClasses, final Object c_terminalName, final Object c_terminalLocation) {
		super();
		this.child_lexeme = c_lexeme;
		this.child_lexerClasses = c_lexerClasses;
		this.child_terminalName = c_terminalName;
		this.child_terminalLocation = c_terminalLocation;

	}

	private Object child_lexeme;
	public final common.StringCatter getChild_lexeme() {
		return (common.StringCatter) (child_lexeme = common.Util.demand(child_lexeme));
	}

	private Object child_lexerClasses;
	public final common.ConsCell getChild_lexerClasses() {
		return (common.ConsCell) (child_lexerClasses = common.Util.demand(child_lexerClasses));
	}

	private Object child_terminalName;
	public final common.StringCatter getChild_terminalName() {
		return (common.StringCatter) (child_terminalName = common.Util.demand(child_terminalName));
	}

	private Object child_terminalLocation;
	public final core.NLocation getChild_terminalLocation() {
		return (core.NLocation) (child_terminalLocation = common.Util.demand(child_terminalLocation));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lexeme: return getChild_lexeme();
			case i_lexerClasses: return getChild_lexerClasses();
			case i_terminalName: return getChild_terminalName();
			case i_terminalLocation: return getChild_terminalLocation();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lexeme: return child_lexeme;
			case i_lexerClasses: return child_lexerClasses;
			case i_terminalName: return child_terminalName;
			case i_terminalLocation: return child_terminalLocation;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production core:terminalDescriptor erroneously claimed to forward");
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
		return "core:terminalDescriptor";
	}

	static void initProductionAttributeDefinitions() {
		// top.lexeme = top.lexeme
		core.PterminalDescriptor.synthesizedAttributes[core.Init.core_lexeme__ON__core_TerminalDescriptor] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.synthesized(core.Init.core_lexeme__ON__core_TerminalDescriptor)); } };
		// top.lexerClasses = lexerClasses
		core.PterminalDescriptor.synthesizedAttributes[core.Init.core_lexerClasses__ON__core_TerminalDescriptor] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(core.PterminalDescriptor.i_lexerClasses)); } };
		// top.terminalLocation = terminalLocation
		core.PterminalDescriptor.synthesizedAttributes[core.Init.core_terminalLocation__ON__core_TerminalDescriptor] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(core.PterminalDescriptor.i_terminalLocation).undecorate(); } };
		// top.terminalName = terminalName
		core.PterminalDescriptor.synthesizedAttributes[core.Init.core_terminalName__ON__core_TerminalDescriptor] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(core.PterminalDescriptor.i_terminalName)); } };

	}

	public static final common.NodeFactory<PterminalDescriptor> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalDescriptor> {

		@Override
		public PterminalDescriptor invoke(final Object[] children, final Object[] annotations) {
			return new PterminalDescriptor(children[0], children[1], children[2], children[3]);
		}
	};

}
