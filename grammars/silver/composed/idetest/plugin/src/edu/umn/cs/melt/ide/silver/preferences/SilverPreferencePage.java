/******************************************/
/* WARNING: GENERATED FILE - DO NOT EDIT! */
/******************************************/
package edu.umn.cs.melt.ide.silver.preferences;

import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.preferences.PreferencesInitializer;
import org.eclipse.imp.preferences.PreferencesTab;
import org.eclipse.imp.preferences.TabbedPreferencesPage;
import org.eclipse.swt.widgets.TabFolder;

import silver.composed.idetest.imp.SILVERPlugin;

/**
 * A preference page class.
 */
public class SilverPreferencePage extends TabbedPreferencesPage {
	public SilverPreferencePage() {
		super();
		prefService = SILVERPlugin.getInstance().getPreferencesService();
	}

	protected PreferencesTab[] createTabs(IPreferencesService prefService,
		TabbedPreferencesPage page, TabFolder tabFolder) {
		PreferencesTab[] tabs = new PreferencesTab[1];

		SilverInstanceTab instanceTab = new SilverInstanceTab(prefService);
		instanceTab.createTabContents(page, tabFolder);
		tabs[0] = instanceTab;

		return tabs;
	}

	public PreferencesInitializer getPreferenceInitializer() {
		return new SilverInitializer();
	}
}
