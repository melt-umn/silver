package core;

public class Pappend_AnyTypeList extends common.FunctionNode{

	public static final int i_l1 = 0;
	public static final int i_l2 = 1;

	public static final Class<?> childTypes[] = {common.DecoratedNode.class, common.DecoratedNode.class};

	public static common.Lazy forward;
	public static final java.util.Map<String, common.Lazy> forwardAttributes = new java.util.TreeMap<String, common.Lazy>();

	public static final java.util.Map<String, common.Lazy> localAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();

	static{
	Pappend_AnyTypeList.inheritedAttributes.put(i_l1, new java.util.TreeMap<String, common.Lazy>());
	Pappend_AnyTypeList.inheritedAttributes.put(i_l2, new java.util.TreeMap<String, common.Lazy>());
	}

	public Pappend_AnyTypeList(Object c_l1, Object c_l2){
		super(2);
		this.children[i_l1] = c_l1;
		this.children[i_l2] = c_l2;

	}

	@Override
	public common.Lazy getSynthesized(String name) {
		return synthesizedAttributes.get(name);
	}

	@Override
	public java.util.Map<String, common.Lazy> getDefinedInheritedAttributes(Object key) {
		return inheritedAttributes.get(key);
	}

	@Override
	public common.Lazy getForward() {
		return forward;
	}

	@Override
	public common.Lazy getForwardInh(String name) {
		return forwardAttributes.get(name);
	}

	@Override
	public common.Lazy getLocal(String name) {
		return localAttributes.get(name);
	}

	@Override
	public String getName() {
		return "core:append_AnyTypeList";
	}

	public common.DecoratedNode doReturn(){
		return (common.DecoratedNode)super.doReturn();
	}
}
