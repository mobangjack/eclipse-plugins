/*******************************************************************************
 * Copyright (c) 2015 Liviu Ionescu.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Liviu Ionescu - initial version
 *******************************************************************************/

package ilg.gnumcueclipse.debug.gdbjtag.openocd.ui.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import ilg.gnumcueclipse.core.ScopedPreferenceStoreWithoutDefaults;
import ilg.gnumcueclipse.core.preferences.DirectoryNotStrictFieldEditor;
import ilg.gnumcueclipse.debug.gdbjtag.openocd.Activator;
import ilg.gnumcueclipse.debug.gdbjtag.openocd.preferences.PersistentPreferences;
import ilg.gnumcueclipse.debug.gdbjtag.openocd.ui.Messages;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page uses special filed editors, that get the default values from the
 * preferences store, but the values are from the variables store.
 */
public class WorkspaceMcuPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	// ------------------------------------------------------------------------

	public static final String ID = "ilg.gnumcueclipse.debug.gdbjtag.openocd.workspacePreferencePage";

	// ------------------------------------------------------------------------

	private PersistentPreferences fPersistentPreferences;

	// ------------------------------------------------------------------------

	public WorkspaceMcuPage() {
		super(GRID);

		if (Activator.getInstance().isDebugging()) {
			System.out.println("openocd.WorkspaceMcuPage()");
		}

		fPersistentPreferences = Activator.getInstance().getPersistentPreferences();

		// Explicit use of the workspace preferences.
		setPreferenceStore(new ScopedPreferenceStoreWithoutDefaults(InstanceScope.INSTANCE, Activator.PLUGIN_ID));

		setDescription(Messages.WorkspaceMcuPagePropertyPage_description);
	}

	// ------------------------------------------------------------------------

	// Contributed by IWorkbenchPreferencePage
	@Override
	public void init(IWorkbench workbench) {

		if (Activator.getInstance().isDebugging()) {
			System.out.println("openocd.WorkspaceMcuPage.init()");
		}
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	@Override
	protected void createFieldEditors() {

		FieldEditor executable;
		executable = new StringFieldEditor(PersistentPreferences.EXECUTABLE_NAME, Messages.McuPage_executable_label,
				getFieldEditorParent());
		addField(executable);

		boolean isStrict;
		isStrict = fPersistentPreferences.getFolderStrict();

		FieldEditor folder;
		folder = new DirectoryNotStrictFieldEditor(PersistentPreferences.INSTALL_FOLDER,
				Messages.McuPage_executable_folder, getFieldEditorParent(), isStrict);
		addField(folder);
	}

	// ------------------------------------------------------------------------
}
