package core;

import java.util.*;

public abstract class NStringList extends common.Node {

	public static final HashSet<String> occurs = new HashSet<String>();
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	protected NStringList(int children) {
		super(children);
	}
}
