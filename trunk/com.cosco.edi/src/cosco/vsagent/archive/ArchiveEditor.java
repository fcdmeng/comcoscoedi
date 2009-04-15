package cosco.vsagent.archive;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;

import cosco.vsagent.model.Course;
import cosco.vsagent.model.IUser;
import cosco.vsagent.model.SchoolClass;
import cosco.vsagent.model.Student;
import cosco.vsagent.model.Teacher;
import cosco.vsagent.system.EditorPartAdapter;
import cosco.vsagent.system.SmsContentProvider;
import cosco.vsagent.system.TableLabelProviderAdapter;
import cosco.vsagent.system.ViewerSortSelectionListener;


public class ArchiveEditor extends EditorPartAdapter {
	private TableViewer tv;

	public void createPartControl(Composite parent) {
		ViewForm topComp = new ViewForm(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		createTableViewer(topComp);// �Զ��巽��������һ��TableViewer���
		tv.setContentProvider(new SmsContentProvider());// ������
		tv.setLabelProvider(new TableViewerLabelProvider());// ��ǩ��
		// ������
		ArchiveEditorActionGroup actionGroup = new ArchiveEditorActionGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		// ���ñ��͹��������ڲ����е�λ��
		topComp.setContent(tv.getControl()); // ���壺���
		topComp.setTopLeft(toolBar); // ���˱�Ե��������
		actionGroup.fireFirstAction();// ��������ҳ����ť�ĵ���
	}

	// �������
	private void createTableViewer(Composite parent) {
		tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true); // ��ʾ��ͷ
		table.setLinesVisible(true); // ��ʾ�����
		table.setLayout(new TableLayout());// ר���ڱ��Ĳ���
		// ����TableViewer�е���,createColumn���Զ��巽��
		createColumn(10, "ID", ArchiveEditorSorter.ID_ASC, ArchiveEditorSorter.ID_DESC);
		createColumn(20, "�û���", ArchiveEditorSorter.USERID_ASC, ArchiveEditorSorter.USERID_DESC);
		createColumn(20, "����", null, null);// ����û������
		createColumn(20, "����", null, null);// ����û������
		createColumn(20, "�༶", ArchiveEditorSorter.SCHOOLCLASS_ASC, ArchiveEditorSorter.SCHOOLCLASS_DESC);
		createColumn(20, "�γ�", null, null);// �γ�û������
		createColumn(60, "����¼ʱ��", ArchiveEditorSorter.LATESTONLINE_ASC, ArchiveEditorSorter.LATESTONLINE_DESC);
	}

	// ����������
	private TableColumn createColumn(int weight, String name, ViewerSorter asc, ViewerSorter desc) {
		Table table = tv.getTable();
		TableLayout layout = (TableLayout) table.getLayout();
		layout.addColumnData(new ColumnWeightData(weight));
		TableColumn col = new TableColumn(table, SWT.NONE);
		col.setText(name);
		if (asc != null && desc != null)
			col.addSelectionListener(new ViewerSortSelectionListener(tv, asc, desc));
		return col;
	}

	// ��ǩ��
	private static final class TableViewerLabelProvider extends TableLabelProviderAdapter {
		public String getColumnText(Object element, int col) {
			// String result = "";
			IUser o = (IUser) element;
			switch (col) {
			case 0:
				return o.getId().toString();
			case 1:
				return o.getUserId();
			case 2:
				return o.getPassword();
			case 3:
				return o.getName();
			case 4:
				if (element instanceof Student) {
					SchoolClass s = ((Student) o).getSchoolclass();
					if (s != null)
						return s.getName();
				}
				return "";
			case 5:
				StringBuilder sb = new StringBuilder();
				if (element instanceof Teacher) {
					Set<Course> set = ((Teacher) o).getCourses();

					for (Iterator it = set.iterator(); it.hasNext();) {
						Course course = (Course) it.next();
						sb.append(course.getName());
						if (it.hasNext())
							sb.append(", ");
					}
				}
				return sb.toString();
			case 6:
				Date date = o.getLatestOnline();
				return date == null ? "" : date.toString();
			default:
				return "";
			}
		}
	}
}
