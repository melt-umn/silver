package common;

import java.util.*;

abstract public class RTTI {
	private static final Map<String, Prodleton> productionsByName = new HashMap<String, Prodleton>();
	private static final Map<String, ArrayList<Prodleton>> productionsByNonterminal = new HashMap<String, ArrayList<Prodleton>>();
	private static final Map<String, Terminalton> terminalsByName = new HashMap<String, Terminalton>();

	public static Prodleton getProdleton(String name) {
		return productionsByName.get(name);
	}

	public static ArrayList<Prodleton> getProdletonsForNonterminal(String ntName) {
		return productionsByNonterminal.get(ntName);
	}

	public static void registerProduction(Prodleton pton) {
		String prod = pton.getProdName();

		if (productionsByName.containsKey(prod)) return;

		productionsByName.put(prod, pton);

		String nt = pton.getNTName();

		if (!productionsByNonterminal.containsKey(nt)) {
			productionsByNonterminal.put(nt, new ArrayList<Prodleton>(Arrays.asList(pton)));
		} else {
			productionsByNonterminal.get(nt).add(pton);
		}
	}

	public static Terminalton getTerminalton(String name) {
		return terminalsByName.get(name);
	}

	public static void registerTerminal(Terminalton tton) {
		terminalsByName.put(tton.getName(), tton);
	}
}
