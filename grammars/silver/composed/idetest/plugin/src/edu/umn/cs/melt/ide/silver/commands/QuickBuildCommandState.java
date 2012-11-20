package edu.umn.cs.melt.ide.silver.commands;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

public class QuickBuildCommandState extends AbstractSourceProvider {

	public final static String ENABLEMENT = "edu.umn.cs.melt.ide.silver.quickbuildcommand.enablement";

	public final static String ENABLED = "ENABLED";

	public final static String DISABLED = "DISABLED";
	
	private boolean enabled = true;

	@Override
	public void dispose() {

	}

	@Override
	public String[] getProvidedSourceNames() {
		return new String[] { ENABLEMENT };
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map getCurrentState() {
		Map map = new HashMap(1);
		String value = enabled ? ENABLED : DISABLED;
		map.put(ENABLEMENT, value);
		return map;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		String value = enabled ? ENABLED : DISABLED;
		fireSourceChanged(ISources.WORKBENCH, ENABLEMENT, value);
	}

}
