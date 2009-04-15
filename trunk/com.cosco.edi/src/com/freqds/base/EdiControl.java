package com.freqds.base;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class EdiControl {
	/**
	 * Creates a new checkbox instance and sets the default layout data.
	 * 
	 * @param group
	 *            the composite in which to create the checkbox
	 * @param label
	 *            the string to set into the checkbox
	 * @return the new checkbox
	 */
	public static Button createCheckBox(Composite group, String label) {
		Button button = new Button(group, SWT.CHECK | SWT.LEFT);
		button.setText(label);
		GridData data = new GridData();
//		data.horizontalSpan = 2;
		button.setLayoutData(data);
		return button;
	}

	/**
	 * Utility method that creates a combo box
	 * 
	 * @param parent=========================
	 *            the parent for the new label
	 * @return the new widget
	 */
	public Combo createCombo(Composite parent) {
		Combo combo = new Combo(parent, SWT.READ_ONLY);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
		combo.setLayoutData(data);
		
		combo.addTraverseListener(new TraverseListener () {
		    public void keyTraversed(TraverseEvent e) {                
		        if(e.detail==SWT.TRAVERSE_RETURN ||e.detail==SWT.TRAVERSE_TAB_NEXT){
		            e.detail = SWT.TRAVERSE_TAB_NEXT;
		            e.doit=true;
		        }else
		          e.doit=false;

		    }
		});
		
		return combo;
	}

	/**
	 * Creates composite control and sets the default layout data.
	 * 
	 * @param parent
	 *            the parent of the new composite
	 * @param numColumns
	 *            the number of columns for the new composite
	 * @param grabExcess
	 *            TODO
	 * 
	 * @return the newly-created coposite
	 */
	public static Composite createComposite(Composite parent, int numColumns,
			boolean grabExcess) {
		final Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(numColumns, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, grabExcess,
				grabExcess));
		return composite;
	}

	/**
	 * Utility method that creates a label instance and sets the default layout
	 * data.
	 * 
	 * @param parent
	 *            the parent for the new label
	 * @param text
	 *            the text for the new label
	 * @return the new label
	 */
	public static Label createLabel(Composite parent, String text) {
		return createIndentedLabel(parent, text, 0);
	}

	/**
	 * Utility method that creates a label instance indented by the specified
	 * number of pixels and sets the default layout data.
	 * 
	 * @param parent
	 *            the parent for the new label
	 * @param text
	 *            the text for the new label
	 * @param indent
	 *            the indent in pixels, or 0 for none
	 * @return the new label
	 */
	public static Label createIndentedLabel(Composite parent, String text,
			int indent) {
		Label label = new Label(parent, SWT.LEFT);
		label.setText(text);
		GridData data = new GridData();
		// data.horizontalSpan = 1;
		// data.horizontalAlignment = GridData.FILL;
		// data.horizontalIndent = indent;
		label.setLayoutData(data);
		return label;
	}

	/**
	 * Utility method that creates a label instance with word wrap and sets the
	 * default layout data.
	 * 
	 * @param parent
	 *            the parent for the new label
	 * @param text
	 *            the text for the new label
	 * @param indent
	 *            the indent in pixels, or 0 for none
	 * @param widthHint
	 *            the nominal width of the label
	 * @return the new label
	 */
	public static Label createWrappingLabel(Composite parent, String text,
			int indent) {
		return createWrappingLabel(parent, text, indent, 1);
	}

	public static Label createWrappingLabel(Composite parent, String text,
			int indent, int horizontalSpan) {
		Label label = new Label(parent, SWT.LEFT | SWT.WRAP);
		label.setText(text);
		GridData data = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		data.horizontalIndent = indent;
		data.horizontalSpan = horizontalSpan;
		// data.widthHint = LABEL_WIDTH_HINT;
		label.setLayoutData(data);
		return label;
	}

	/**
	 * Create a text field specific for this application
	 * 
	 * @param parent
	 *            the parent of the new text field
	 * @return the new text field
	 */
	public Text createTextField(Composite parent) {
		Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
		return layoutTextField(text);
	}
	static public Text createMTextField(Composite parent){
		Text text = new Text(parent,  SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		return layoutTextField(text);
	}
	/**
	 * Create a password field specific for this application
	 * 
	 * @param parent
	 *            the parent of the new text field
	 * @return the new text field
	 */
	static public Text createPasswordField(Composite parent) {
		Text text = new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD);
		return layoutTextField(text);
	}

	/**
	 * Layout a text or password field specific for this application
	 * 
	 * @param parent
	 *            the parent of the new text field
	 * @return the new text field
	 */
	static public Text layoutTextField(Text text) {
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.verticalAlignment = GridData.CENTER;
		data.grabExcessVerticalSpace = false;
		// data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
		text.setLayoutData(data);
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.text = e.text.toUpperCase();
			}
		});
		
		text.addTraverseListener(new TraverseListener () {
		    public void keyTraversed(TraverseEvent e) {                
		        if(e.detail==SWT.TRAVERSE_RETURN ||e.detail==SWT.TRAVERSE_TAB_NEXT){
		            e.detail = SWT.TRAVERSE_TAB_NEXT;
		            e.doit=true;
		        }else
		          e.doit=false;

		    }
		});
		return text;
	}

	/**
	 * Utility method to create a radio button
	 * 
	 * @param parent
	 *            the parent of the radio button
	 * @param label
	 *            the label of the radio button
	 * @param span
	 *            the number of columns to span
	 * @return the created radio button
	 */
	public static Button createRadioButton(Composite parent, String label, int span) {
		Button button = new Button(parent, SWT.RADIO);
		button.setText(label);
		GridData data = new GridData();
		data.horizontalSpan = span;
		button.setLayoutData(data);
		return button;
	}
	
	public void setInfoDecoration(Control control, String tip,
			boolean showOnlyOnFocus) {
		ControlDecoration decoration = new ControlDecoration(control, SWT.TOP
				| SWT.LEFT);
		FieldDecoration infoDecoration = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION);
		decoration.setImage(infoDecoration.getImage());
		decoration.setDescriptionText(tip);
		// decoration.setShowOnlyOnFocus(showOnlyOnFocus);
	}


	// ���GridData������ظ�����̫�࣬д��һ������Լ��ٳ������������4Ҳ����Щ
	public GridData createGridData(int style, int horizontalSpan) {
		GridData gridData = new GridData(style);
		gridData.horizontalSpan = horizontalSpan;
		return gridData;
	}

	public GridData createGridData(int style, int horizontalSpan,
			int verticalSpan) {
		GridData gridData = new GridData(style);
		gridData.horizontalSpan = horizontalSpan;
		gridData.verticalSpan = verticalSpan;
		return gridData;
	}

	/**
	 * Utility method to create an editable combo box
	 * 
	 * @param parent
	 *            the parent of the combo box
	 * @return the created combo
	 */
	protected Combo createEditableCombo(Composite parent) {
		Combo combo = new Combo(parent, SWT.NULL);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
		combo.setLayoutData(data);
		return combo;
	}

	protected Group createGroup(Composite parent, String text) {
		Group group = new Group(parent, SWT.NULL);
		group.setText(text);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		// data.widthHint = GROUP_WIDTH;

		group.setLayoutData(data);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		group.setLayout(layout);
		return group;
	}

	
}
