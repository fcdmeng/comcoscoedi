package cosco.vsagent.ecd;

import java.sql.SQLException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import sun.misc.Sort;
import cosco.vsagent.db.DbOperate;
import cosco.vsagent.system.EditorPartAdapter;

public class EcdbkcntrEditor extends EditorPartAdapter {
	private CTabFolder CTab;

	private CTabItem Item;

	private Composite comp1;

	private Composite comp2;

	private Composite comp3;

	private Composite container;

	private TableViewer tv;

	private Table table;

	private Boolean a = true;

	private Text text1;

	private Text text2;

	private Text text3;
	public EcdbkcntrEditor() {
		// TODO Auto-generated constructor stub
	}

	
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		// �����Զ��巽��
		createSurface(composite);

	
	}
	
	private void createSurface(Composite comp) {
		Composite composite = new Composite(comp, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(new FillLayout());
		// ����ָ
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		// ������չ��
		ExpandBar expandbar = new ExpandBar(sashForm, SWT.V_SCROLL);
		{ // /������չ��Item1
			comp1 = new Composite(expandbar, SWT.NONE);
			comp1.setLayoutData(new GridData(GridData.FILL_BOTH));
			comp1.setLayout(new GridLayout());
			ExpandItem item1 = new ExpandItem(expandbar, SWT.NONE);
			item1.setText("��ѯԱ����Ϣ");
			item1.setHeight(40);
			item1.setControl(comp1);
			// ����link
			Link link = new Link(comp1, SWT.NONE);
			GridData grid = new GridData();
			grid.horizontalIndent = 30;
			grid.verticalIndent = 10;
			link.setLayoutData(grid);
			link.setText(" <a>Ա��������Ϣ��ѯ</a>    ");
			link.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
/*
					try {
						tv.setInput(DbOperate.BasicInfor());
					} catch (SQLException e) {
					}
*/				}
			});

		}
		{ // ������չ��Item2
			comp2 = new Composite(expandbar, SWT.NONE);
			comp2.setLayoutData(new GridData(GridData.FILL_BOTH));
			comp2.setLayout(new GridLayout(2, false));
			ExpandItem item2 = new ExpandItem(expandbar, SWT.NONE);
			item2.setText("�����ѯԱ����Ϣ");
			item2.setHeight(150);
			item2.setControl(comp2);

			final Label label1 = new Label(comp2, SWT.NONE);
			label1.setText("��Ա���Ų�ѯ��");
			GridData grid0 = new GridData();
			grid0.horizontalSpan = 2;
			label1.setLayoutData(grid0);
			text1 = new Text(comp2, SWT.BORDER);
			GridData gridId = new GridData();
			gridId.widthHint = 80;
			text1.setLayoutData(gridId);
			final Button button1 = new Button(comp2, SWT.PUSH);
			button1.setText("��ѯ");
			button1.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {

					/*try {
						String Atr = text1.getText();
						// ����ı����Ƿ�Ϊ��
						if (Atr == null || Atr.equals("")) {
							MessageDialog.openInformation(null, null, "��ѯʧ�ܣ�"
									+ '\n' + '\n' + "  ע�⣺�ı��������Ϊ�գ�������");
						} else {
							tv.setInput(DbOperate.SearchId(text1));
						}
					} catch (SQLException ee) {
					}*/

				}
			});

			GridData grid1 = new GridData();
			grid1.horizontalIndent =5;
			grid1.widthHint = 60;
			button1.setLayoutData(grid1);

			final Label label2 = new Label(comp2, SWT.NONE);
			label2.setText("��������ѯ��");
			label2.setLayoutData(grid0);
			text2 = new Text(comp2, SWT.BORDER);
			text2.setLayoutData(gridId);
			final Button button2 = new Button(comp2, SWT.PUSH);
			button2.setText("��ѯ");
			button2.setLayoutData(grid1);
			button2.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {

					/*try {
						String Btr = text2.getText();
						// ����ı����Ƿ�Ϊ��
						if (Btr == null || Btr.equals("")) {
							MessageDialog.openInformation(null, null, "��ѯʧ�ܣ�"
									+ '\n' + '\n' + "  ע�⣺�ı����Ϊ�գ�������");
						} else {
							tv.setInput(DbOperate.SearchName(text2));
						}
					} catch (SQLException ee) {
					}*/

				}
			});

			final Label label3 = new Label(comp2, SWT.NONE);
			label3.setText("���Ա��ѯ��");
			label3.setLayoutData(grid0);
			text3 = new Text(comp2, SWT.BORDER);
			text3.setLayoutData(gridId);
			final Button button3 = new Button(comp2, SWT.PUSH);
			button3.setText("��ѯ");
			button3.setLayoutData(grid1);
			button3.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {

					/*try {
						String Ctr = text3.getText();
						// ����ı����Ƿ�Ϊ��
						if (Ctr == null || Ctr.equals("")) {
							MessageDialog.openInformation(null, null, "��ѯʧ�ܣ�"
									+ '\n' + '\n' + "  ע�⣺�ı����Ϊ�գ�������");
						} else {
							tv.setInput(DbOperate.SearchSex(text3));
						}
					} catch (SQLException ee) {
					}*/

				}
			});
		}
		{// ������չ��Item3
			comp3 = new Composite(expandbar, SWT.NONE);
			comp3.setLayoutData(new GridData(GridData.FILL_BOTH));
			comp3.setLayout(new GridLayout(2, false));
			ExpandItem item3 = new ExpandItem(expandbar, SWT.NONE);
			item3.setText("��ѯ˵��");
			item3.setHeight(100);
			item3.setControl(comp3);

		}

		container = new Composite(sashForm, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout());
		sashForm.setWeights(new int[] { 2, 8 });
		{
			CTab = new CTabFolder(container, SWT.BORDER);
			// ����CTabItem����Item1
			CTab.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true,
					false));
			CTab.setSimple(false);

			Item = new CTabItem(CTab, SWT.NONE, 0);
			Item.setText(" Ա����Ϣ");
			// ������С������󻯱�ǩ�ɼ�
			CTab.setSelection(Item);
			CTab.setMinimizeVisible(true);
			CTab.setMaximizeVisible(true);
			CTab.setMaximized(true);
			// ����ѡ�����С������󻯺ͻָ����ܡ�
			CTab.addCTabFolder2Listener(new CTabFolder2Adapter() {
				// ʹѡ���С��
				public void minimize(CTabFolderEvent event) {
					CTab.setMinimized(true);
					container.layout(true);
				}

				// ��ѡ����
				public void maximize(CTabFolderEvent event) {
					CTab.setMaximized(true);
					CTab.setLayoutData(new GridData(GridData.FILL_BOTH));
					container.layout(true);
				}

				// ��ѡ��ָ�
				public void restore(CTabFolderEvent event) {
					CTab.setMinimized(false);
					CTab.setMaximized(false);
					CTab.setLayoutData(new GridData(GridData.FILL,
							GridData.FILL, true, false));
					container.layout(true);
				}
			});
/*			// �����Զ��巽��
			createTableViewer(CTab);
			// ���������ṩ��
			tv.setContentProvider(new EmployeeContentProvider());
			// ���ñ�ǩ�ṩ��
			tv.setLabelProvider(new EmployeeLabelProvider());
			// ����������
			tv.setSorter(new Sort());
*/		}

	}
	
}
