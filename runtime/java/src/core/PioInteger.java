package core;

public class PioInteger extends core.NIOInteger {

	public static final int i_i = 0;
	public static final int i_int = 1;

	public static final Class<?> childTypes[] = {Object.class, Integer.class};

	public static common.Lazy forward;
	public static final java.util.Map<String, common.Lazy> forwardAttributes = new java.util.TreeMap<String, common.Lazy>();

	public static final java.util.Map<String, common.Lazy> localAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();

	static{
	PioInteger.inheritedAttributes.put(i_i, new java.util.TreeMap<String, common.Lazy>());
	PioInteger.inheritedAttributes.put(i_int, new java.util.TreeMap<String, common.Lazy>());
	}

	public PioInteger(Object c_i, Object c_int){
		super(2);
		this.children[i_i] = c_i;
		this.children[i_int] = c_int;

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
		return "core:ioInteger";
	}

}
