package core;

import java.util.*;

public abstract class NIOBoolean extends common.Node {

	public static final HashSet<String> occurs = new HashSet<String>();
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	protected NIOBoolean(int children) {
		super(children);
	}
}
