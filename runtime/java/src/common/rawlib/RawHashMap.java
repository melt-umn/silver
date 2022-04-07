package common.rawlib;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import common.ConsCell;
import common.NodeFactory;
import common.StringCatter;
import common.javainterop.ConsCellCollection;

public final class RawHashMap {
	public static HashMap<String,Object> empty() {
		return new HashMap<String,Object>();
	}

	public static HashMap<String,ConsCell> fromCSVString(StringCatter csvStr) {
        System.out.println("EYYYY");

        System.out.println("lmao: " + csvStr.toString() + "done");
		String[] lines = (csvStr.toString()).split("\n");
        System.out.println("first: " + lines[0] + "first done");
		HashMap<String,ConsCell> outputMap = new HashMap<String,ConsCell>();
		for (String line : lines) {

			List<StringCatter> tokens = new ArrayList<StringCatter>();
            for (String elem : (line.split("\t"))){
                tokens.add(new StringCatter(elem));
            }
                // (Arrays.asList(line.split("\t")));
			if (tokens.size() > 1) {
				String head = tokens.remove(0).toString();
                System.out.println("head: " + head);
                System.out.println("length of tail now: " + tokens.size());
                System.out.println("head now: " + tokens.get(0));
                ConsCell rest = ConsCell.fromList(tokens);
                if (outputMap.containsKey(head)) {
                    ConsCell existing = outputMap.get(head);
                    outputMap.put(head, new ConsCell(rest, existing));
                } else {
                    outputMap.put(head, rest);
                }
			}
		}
		return outputMap;
	}

	public static ConsCell lookupList( HashMap<String,ConsCell> t, StringCatter k) {
        ConsCell r = t.get(k.toString());
        if (r == null) {
            System.out.println("lookup for " + k + " is null");
            return ConsCell.nil;
            }
        else {
        return r == null ? ConsCell.nil : r;
        }
	}

}
