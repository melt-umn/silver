/******************************************/
/* WARNING: GENERATED FILE - DO NOT EDIT! */
/******************************************/
package edu.umn.cs.melt.ide.silver.preferences;

import java.util.List;
import java.util.ArrayList;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Group;
import org.eclipse.imp.preferences.*;
import org.eclipse.imp.preferences.fields.*;
import org.osgi.service.prefs.Preferences;


/**
 * The instance level preferences tab.
 */
public class SilverInstanceTab extends InstancePreferencesTab {

	public SilverInstanceTab(IPreferencesService prefService) {
		super(prefService, false);
	}

	/**
	 * Creates specific preference fields with settings appropriate to
	 * the instance preferences level.
	 *
	 * Overrides an unimplemented method in PreferencesTab.
	 *
	 * @return    An array that contains the created preference fields
	 *
	 */
	protected FieldEditor[] createFields(TabbedPreferencesPage page, Composite parent)
	{
		List<FieldEditor> fields = new ArrayList<FieldEditor>();

		DirectoryFieldEditor silverHome = fPrefUtils.makeNewDirectoryField(
			page, this, fPrefService,
			"instance", "silverHome", "silver home",
			"",
			parent,
			true, true,
			false, "",
			false);
		fields.add(silverHome);

		Link silverHomeDetailsLink = fPrefUtils.createDetailsLink(parent, silverHome, silverHome.getTextControl().getParent(), "Details ...");

		silverHomeDetailsLink.setEnabled(true);
		fDetailsLinks.add(silverHomeDetailsLink);

		return fields.toArray(new FieldEditor[fields.size()]);
	}
}
