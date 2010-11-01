package core;

import java.util.*;

public abstract class NIOInteger extends common.Node {

	public static final HashSet<String> occurs = new HashSet<String>();
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	protected NIOInteger(int children) {
		super(children);
	}
}
