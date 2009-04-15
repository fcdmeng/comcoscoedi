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
		// 建立选项卡
		TabFolder tableFolder = new TabFolder(parent, SWT.NONE);
		
		tableFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		TabItem tabItem1 = new TabItem(tableFolder, SWT.NONE);
		tabItem1.setText("未接收");
		tabItem1.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/EDI_E_.ico"));
		
		TabItem tabItem2 = new TabItem(tableFolder, SWT.NONE);
		tabItem2.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/EDI_I_.ico"));
		tabItem2.setText("已接收");
		
		TabItem tabItem3 = new TabItem(tableFolder, SWT.NONE);
		tabItem3.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/set.png"));
		tabItem3.setText("已生成");
		
		// 建立表格
		Table table = new Table(tableFolder, SWT.FULL_SELECTION | SWT.BORDER);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		// 显示表头和表格线
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tabItem1.setControl(table);
		// 建立列宽度为80个像素
		TableColumn one = new TableColumn(table, SWT.CENTER);
		one.setText("EDI类型");
		one.setWidth(80);
		TableColumn two = new TableColumn(table, SWT.LEFT);
		two.setText("发送方代码");
		two.setWidth(80);
		TableColumn three = new TableColumn(table, SWT.LEFT);
		three.setText("发送方名称");
		three.setWidth(80);
		TableColumn forth = new TableColumn(table, SWT.LEFT);
		forth.setText("上传人");
		forth.setWidth(80);
		
		TableColumn five = new TableColumn(table, SWT.LEFT);
		five.setText("上传时间");
		five.setWidth(80);
		TableColumn sex = new TableColumn(table, SWT.LEFT);
		sex.setText("文件名");
		sex.setWidth(80);
		TableColumn sev = new TableColumn(table, SWT.LEFT);
		sev.setText("备注");
		sev.setWidth(100);
		
		// 添加数据
		TableItem item = new TableItem(table, SWT.NONE);
		item.setText(0, "20020001");
		item.setText(1, "曾志光");
		item.setText(2, "商务部经理");
		
		item = new TableItem(table, SWT.NONE);
		item.setText(0, "20020002");
		item.setText(1, "张志强");
		item.setText(2, "技术部经理");

		item = new TableItem(table, SWT.NONE);
		item.setText(0, "20020003");
		item.setText(1, "李鸿梅");
		item.setText(2, "财务部经理");
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
