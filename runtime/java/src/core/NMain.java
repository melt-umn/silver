package core;

import java.util.*;

public abstract class NMain extends common.Node {

	public static final HashSet<String> occurs = new HashSet<String>();
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	protected NMain(int children) {
		super(children);
	}
}
