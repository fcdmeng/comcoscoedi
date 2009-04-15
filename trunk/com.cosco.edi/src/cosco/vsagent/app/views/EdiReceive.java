package cosco.vsagent.app.views;

import org.eclipse.jface.action.IToolBarManager;
import cosco.vsagent.app.Activator;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.ILogonListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;
import com.swtdesigner.ResourceManager;

public class EdiReceive extends ViewPart  implements ModifyListener, ILogonListener{

	public EdiReceive() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		// ����ѡ�
		TabFolder tableFolder = new TabFolder(parent, SWT.NONE);
		
		tableFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		TabItem tabItem1 = new TabItem(tableFolder, SWT.NONE);
		tabItem1.setText("δ����");
		tabItem1.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/EDI_E_.ico"));
		
		TabItem tabItem2 = new TabItem(tableFolder, SWT.NONE);
		tabItem2.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/EDI_I_.ico"));
		tabItem2.setText("�ѽ���");
		
		TabItem tabItem3 = new TabItem(tableFolder, SWT.NONE);
		tabItem3.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/set.png"));
		tabItem3.setText("������");
		
		// �������
		Table table = new Table(tableFolder, SWT.FULL_SELECTION | SWT.BORDER);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		// ��ʾ��ͷ�ͱ����
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tabItem1.setControl(table);
		// �����п��Ϊ80������
		TableColumn one = new TableColumn(table, SWT.CENTER);
		one.setText("EDI����");
		one.setWidth(80);
		TableColumn two = new TableColumn(table, SWT.LEFT);
		two.setText("���ͷ�����");
		two.setWidth(80);
		TableColumn three = new TableColumn(table, SWT.LEFT);
		three.setText("���ͷ�����");
		three.setWidth(80);
		TableColumn forth = new TableColumn(table, SWT.LEFT);
		forth.setText("�ϴ���");
		forth.setWidth(80);
		
		TableColumn five = new TableColumn(table, SWT.LEFT);
		five.setText("�ϴ�ʱ��");
		five.setWidth(80);
		TableColumn sex = new TableColumn(table, SWT.LEFT);
		sex.setText("�ļ���");
		sex.setWidth(80);
		TableColumn sev = new TableColumn(table, SWT.LEFT);
		sev.setText("��ע");
		sev.setWidth(100);
		
		// �������
		TableItem item = new TableItem(table, SWT.NONE);
		item.setText(0, "20020001");
		item.setText(1, "��־��");
		item.setText(2, "���񲿾���");
		
		item = new TableItem(table, SWT.NONE);
		item.setText(0, "20020002");
		item.setText(1, "��־ǿ");
		item.setText(2, "����������");

		item = new TableItem(table, SWT.NONE);
		item.setText(0, "20020003");
		item.setText(1, "���÷");
		item.setText(2, "���񲿾���");
		initializeToolBar();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	private void initializeToolBar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}

	public void modifyText(ModifyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void logoff() {
		// TODO Auto-generated method stub
		
	}

	public void logon() {
		// TODO Auto-generated method stub
		
	}
	
	public void dispose(){
		Context.getInstance().removeLogonListener(this);
		super.dispose();
	}

}
