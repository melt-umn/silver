package core;

public class PioString extends core.NIOString {

	public static final int i_i = 0;
	public static final int i_s = 1;

	public static final Class<?> childTypes[] = {Object.class, StringBuffer.class};

	public static common.Lazy forward;
	public static final java.util.Map<String, common.Lazy> forwardAttributes = new java.util.TreeMap<String, common.Lazy>();

	public static final java.util.Map<String, common.Lazy> localAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();

	static{
	PioString.inheritedAttributes.put(i_i, new java.util.TreeMap<String, common.Lazy>());
	PioString.inheritedAttributes.put(i_s, new java.util.TreeMap<String, common.Lazy>());
	}

	public PioString(Object c_i, Object c_s){
		super(2);
		this.children[i_i] = c_i;
		this.children[i_s] = c_s;

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
		return "core:ioString";
	}

}
