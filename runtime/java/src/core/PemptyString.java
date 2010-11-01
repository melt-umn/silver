package core;

public class PemptyString extends core.NStringList {


	public static final Class<?> childTypes[] = {};

	public static common.Lazy forward;
	public static final java.util.Map<String, common.Lazy> forwardAttributes = new java.util.TreeMap<String, common.Lazy>();

	public static final java.util.Map<String, common.Lazy> localAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();
	public static final java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();

	static{
	}

	public PemptyString(){
		super(0);

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
		return "core:emptyString";
	}

}
