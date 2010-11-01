package core;

public class Pinternal_cons_AnyTypeList extends core.NAnyTypeList {

	public static final int i_h = 0;
	public static final int i_t = 1;

	public static final Class<?> childTypes[] = {common.AnyType.class, common.DecoratedNode.class};

	public static common.Lazy forward;
	public static final java.util.Map<String, common.Lazy> forwardAttributes = new java.util.TreeMap<String, common.Lazy>();

	public static final java.util.Map<String, common.Lazy> localAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();

	static{
	Pinternal_cons_AnyTypeList.inheritedAttributes.put(i_h, new java.util.TreeMap<String, common.Lazy>());
	Pinternal_cons_AnyTypeList.inheritedAttributes.put(i_t, new java.util.TreeMap<String, common.Lazy>());
	}

	public Pinternal_cons_AnyTypeList(Object c_h, Object c_t){
		super(2);
		this.children[i_h] = c_h;
		this.children[i_t] = c_t;

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
		return "core:internal_cons_AnyTypeList";
	}

}
