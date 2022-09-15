package common;

import java.util.HashMap;
import java.util.Map;

public class Statistics extends Thread {
	// Enabling:
	// Uncomment the two things in both DecoratedNode and Node and Thunk.
	// Also, as an ugly hack to make this all-runtime, uncomment the thing in Decorator. :(

	protected static boolean hooked = false;

	protected static final Map<String, Integer> dn_spawn = new HashMap<String, Integer>(); 
	protected static final Map<String, Integer> dn_final = new HashMap<String, Integer>(); 
	
	protected static final Map<String, Integer> n_spawn = new HashMap<String, Integer>(); 
	protected static final Map<String, Integer> n_final = new HashMap<String, Integer>(); 

	protected static final Map<String, Integer> t_spawn = new HashMap<String, Integer>(); 
	protected static final Map<String, Integer> t_final = new HashMap<String, Integer>(); 
	
	public static void hook() {
		if(!hooked) {
			hooked = true;
			Runtime rt = Runtime.getRuntime();
			rt.addShutdownHook(new Statistics());
		}
	}
	
	@Override
	public void run() {
		// Assumption: everything ever spawned is at some point spawned as a Node.
		int tns = 0;
		int tnf = 0;
		int tdns = 0;
		int tdnf = 0;
		int tts = 0;
		int ttf = 0;
		System.out.println("Nodes\t(res)\t\tDNodes\t(res)\t\t% Dec\t%rec(n)\t%rec(dn)\t\tThunks\t(res)\t%");
		for( String key : n_spawn.keySet()) {
			Integer ns = n_spawn.get(key);			//if(ns==null) ns = 0;
			Integer nf = n_final.get(key);			if(nf==null) nf = 0;
			Integer dns = dn_spawn.get(key);		if(dns==null) dns = 0;
			Integer dnf = dn_final.get(key);		if(dnf==null) dnf = 0;
			Integer ts = t_spawn.get(key);			if(ts==null) ts = 0;
			Integer tf = t_final.get(key);			if(tf==null) tf = 0;
			System.out.printf("%d\t%d\t\t%d\t%d\t\t%3f\t%3f\t%3f\t\t%d\t%d\t%3f\t", 
								ns,
								(ns-nf),
								dns,
								(dns-dnf),
								(((float)dns)/ns),
								(((float)(ns-nf))/ns),
								(((float)(dns-dnf))/dns),
								ts,
								(ts-tf),
								(((float)(ts-tf))/ts));
			System.out.println(key);
			tns+=ns;
			tnf+=nf;
			tdns+=dns;
			tdnf+=dnf;
			tts += ts;
			ttf += tf;
		}
		System.out.println("Total:\tNodes\t(res)\t\tDecN\t(res)\t\tThunk\t(res)");
		System.out.println("\t" + tns
		          +"\t" + (tns-tnf)
		        +"\t\t" + tdns
		          +"\t" + (tdns-tdnf)
		        +"\t\t" + tts
		          +"\t" + (tts-ttf));
		
	}

	private static void increment(Map<String, Integer> map, Class<?> class1) {
		Integer n = map.get(class1.getName());
		if(n == null)
			n = 0;
		map.put(class1.getName(), ++n);
	}

	protected static void dnSpawn(Class<?> class1) {
		increment(dn_spawn,class1);
	}

	protected static void dnFinal(Class<?> class1) {
		increment(dn_final,class1);
	}

	protected static void nSpawn(Class<?> class1) {
		increment(n_spawn,class1);
	}

	protected static void nFinal(Class<?> class1) {
		increment(n_final,class1);
	}

	protected static void tSpawn(Class<?> class1) {
		increment(t_spawn,class1);
	}

	protected static void tFinal(Class<?> class1) {
		increment(t_final,class1);
	}




}
