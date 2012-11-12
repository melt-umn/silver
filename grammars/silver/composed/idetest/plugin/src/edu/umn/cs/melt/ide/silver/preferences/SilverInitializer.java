/******************************************/
/* WARNING: GENERATED FILE - DO NOT EDIT! */
/******************************************/
package edu.umn.cs.melt.ide.silver.preferences;

import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.preferences.PreferencesInitializer;

import silver.composed.idetest.imp.SILVERPlugin;


/**
 * Initializations of default values for preferences.
 */
public class SilverInitializer extends PreferencesInitializer {
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferencesService service = SILVERPlugin.getInstance().getPreferencesService();

		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, SilverConstants.P_SILVERHOME, "/");
	}

	/*
	 * Clear (remove) any preferences set on the given level.
	 */
	public void clearPreferencesOnLevel(String level) {
		IPreferencesService service = SILVERPlugin.getInstance().getPreferencesService();
		service.clearPreferencesAtLevel(level);

	}
}
