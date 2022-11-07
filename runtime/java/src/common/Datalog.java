package common;

import java.util.HashMap;


public final class Datalog  {
    private static HashMap<String,DecoratedNode> decoratedRefs;


    public static StringCatter toDatalogID(DecoratedNode node) {
        String output = "DecoratedNode" + Integer.toHexString(System.identityHashCode(node));
        decoratedRefs.put(output,node);
        return new StringCatter(output);
    }

    public static DecoratedNode fromDatalogID(StringCatter name) {
        String actualName = name.toString();
        return decoratedRefs.get(actualName);
    }

}
