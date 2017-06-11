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

package ilg.gnumcueclipse.debug.gdbjtag.jlink.ui.properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;

import ilg.gnumcueclipse.core.ScopedPreferenceStoreWithoutDefaults;
import ilg.gnumcueclipse.core.preferences.DirectoryNotStrictFieldEditor;
import ilg.gnumcueclipse.core.ui.FieldEditorPropertyPage;
import ilg.gnumcueclipse.debug.gdbjtag.jlink.Activator;
import ilg.gnumcueclipse.debug.gdbjtag.jlink.preferences.PersistentPreferences;
import ilg.gnumcueclipse.debug.gdbjtag.jlink.ui.Messages;

public class ProjectMcuPage extends FieldEditorPropertyPage {

	// ------------------------------------------------------------------------

	public static final String ID = "ilg.gnumcueclipse.debug.gdbjtag.jlink.projectPropertiesPage";

	// ------------------------------------------------------------------------

	private PersistentPreferences fPersistentPreferences;

	// ------------------------------------------------------------------------

	public ProjectMcuPage() {
		super(GRID);

		if (Activator.getInstance().isDebugging()) {
			System.out.println("jlink.ProjectMcuPage()");
		}

		fPersistentPreferences = Activator.getInstance().getPersistentPreferences();

		setDescription(Messages.ProjectMcuPagePropertyPage_description);
	}

	// ------------------------------------------------------------------------

	protected IPreferenceStore doGetPreferenceStore() {

		Object element = getElement();
		if (element instanceof IProject) {
			if (Activator.getInstance().isDebugging()) {
				System.out.println("jlink.ProjectMcuPage.doGetPreferenceStore() project store");
			}
			return new ScopedPreferenceStoreWithoutDefaults(new ProjectScope((IProject) element), Activator.PLUGIN_ID);
		}
		return null;
	}

	@Override
	protected void createFieldEditors() {

		FieldEditor executable = new StringFieldEditor(PersistentPreferences.EXECUTABLE_NAME,
				Messages.McuPage_executable_label, getFieldEditorParent());
		addField(executable);

		boolean isStrict = fPersistentPreferences.getFolderStrict();

		FieldEditor folder = new DirectoryNotStrictFieldEditor(PersistentPreferences.INSTALL_FOLDER,
				Messages.McuPage_executable_folder, getFieldEditorParent(), isStrict);
		addField(folder);
	}

	// ------------------------------------------------------------------------
}
