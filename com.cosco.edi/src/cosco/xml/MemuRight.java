package cosco.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;

import cosco.vsagent.system.ImagesContext;
import cosco.xml.mapping.Xmlbkcntr;
import cosco.xml.mapping.Xmlbooking;
import cosco.xml.service.packer.AbstractXml;
import cosco.xml.service.packer.Manifest_Declare_Export_Ship_Edit;
import cosco.xml.service.packer.Manifest_Declare_Export_Ship_First;
import cosco.xml.service.packer.Manifest_Declare_Export_Ship_Secondly;
import cosco.xml.service.packer.Manifest_Declare_Import_Ship_Delete;
import cosco.xml.service.packer.Manifest_Declare_Import_Ship_Edit;
import cosco.xml.service.packer.Manifest_Declare_Import_Ship_First;
import cosco.xml.service.packer.Manifest_Declare_Import_Ship_Secondly;

public class MemuRight {
	private Table table;
	public MemuRight(Menu menu,Table table){
		this.table = table;
		
		MenuItem item1 = new MenuItem(menu, SWT.PUSH);
		item1.setText("接收");
		item1.setImage(ImagesContext.getImage(ImagesContext.RUN_EXC));
		MenuItem item2 = new MenuItem(menu, SWT.CASCADE);
		item2.setText("原始舱单");
		item2.setImage(ImagesContext.getImage(ImagesContext.SELECTVOYAGE));
		MenuItem item3 = new MenuItem(menu, SWT.CASCADE);
		item3.setText("预配舱单");
		item3.setImage(ImagesContext.getImage(ImagesContext.SELECTVOYAGE));
		
//		MenuItem item4 = new MenuItem(menu, SWT.CASCADE);
//		item4.setText("装载舱单");
//		item4.setImage(ImagesContext.getImage(ImagesContext.SELECTVOYAGE));
		{
		Menu subMenu = new Menu(menu);
		item2.setMenu(subMenu);
		MenuItem subItem1 = new MenuItem(subMenu, SWT.PUSH);
		subItem1.setText("一次申报");
		subItem1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Selected("一次申报",true);
			}
		});

		MenuItem subItem2 = new MenuItem(subMenu, SWT.PUSH);
		subItem2.setText("二次申报");

		subItem2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("二次申报");
				Selected("二次申报",true);
			}
		});

		MenuItem add = new MenuItem(subMenu, SWT.PUSH);
		add.setText("增加");
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Selected("增加",true);
			}
		});

		MenuItem update = new MenuItem(subMenu, SWT.PUSH);
		update.setText("变更");
		update.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Selected("变更",true);
			}
		});
		MenuItem delete = new MenuItem(subMenu, SWT.PUSH);
		delete.setText("删除");
		delete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("删除");
				Selected("删除",true);
			}
		});
		MenuItem receipt = new MenuItem(subMenu, SWT.PUSH);
		receipt.setText("回执");
		receipt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("回执");
				Selected("回执",true);
			}
		});
	}
		
		Menu ExporMenu = new Menu(menu);
		item3.setMenu(ExporMenu);
		MenuItem ExportItem1 = new MenuItem(ExporMenu, SWT.PUSH);
		ExportItem1.setText("一次申报");
		ExportItem1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				//Selected("一次申报");
			}
		});

		MenuItem ExportItem2 = new MenuItem(ExporMenu, SWT.PUSH);
		ExportItem2.setText("二次申报");

		ExportItem2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("二次申报");
				//Selected("二次申报");
			}
		});

		MenuItem Exportadd = new MenuItem(ExporMenu, SWT.PUSH);
		Exportadd.setText("增加");
		Exportadd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
//				Selected("增加");
			}
		});

		MenuItem Exportupdate = new MenuItem(ExporMenu, SWT.PUSH);
		Exportupdate.setText("变更");
		Exportupdate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
//				Selected("变更");
			}
		});
		MenuItem Exportdelete = new MenuItem(ExporMenu, SWT.PUSH);
		Exportdelete.setText("删除");
		Exportdelete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("删除");
//				Selected("删除");
			}
		});
		MenuItem Exporreceipt = new MenuItem(ExporMenu, SWT.PUSH);
		Exporreceipt.setText("回执");
		Exporreceipt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("回执");
//				Selected("回执");
			}
		});
	}
	
	// 得到选中的行
	public boolean Selected(String string, boolean type) {
		int SelectionCount = this.table.getSelectionCount();
		if (SelectionCount == 0)
			return false;
		if (MessageDialog.openQuestion(null, "提示", "你选中了["
				+ String.valueOf(SelectionCount) + "]提运单，确定要执行吗？") == false)
			return false;

		TableItem t[] = table.getSelection();
		List<Xmlbooking> booking = new ArrayList<Xmlbooking>();
		for (int i = 0; i < t.length; i++) {
			Xmlbooking xmlbooking = (Xmlbooking) t[i].getData();
//			System.out.println(xmlbooking.getBlno());

			Set<Xmlbkcntr> cntrSt = xmlbooking.getXmlbkcntr();

			booking.add(xmlbooking);

		}
		
		AbstractXml Manifest = null;
		if (type == true){
			if (string.equals("一次申报")){
				Manifest = new Manifest_Declare_Import_Ship_First("海运原始舱单");
			}else if(string.equals("二次申报")){
				Manifest = new Manifest_Declare_Import_Ship_Secondly("海运原始舱单");
			}
			else if(string.equals("删除")){
				Manifest = new Manifest_Declare_Import_Ship_Delete("海运原始舱单");
			}else if(string.equals("变更")){
				Manifest = new Manifest_Declare_Import_Ship_Edit("海运原始舱单");
			}else{
				return false;
			}
		}
		
		if (type == false){
			if (string.equals("一次申报")){
				Manifest = new Manifest_Declare_Export_Ship_First("海运预配舱单");
			}else if(string.equals("二次申报")){
				Manifest = new Manifest_Declare_Export_Ship_Secondly("海运预配舱单");
			}
			else if(string.equals("删除")){
				Manifest = new Manifest_Declare_Import_Ship_Delete("海运预配舱单");
			}else if(string.equals("变更")){
				Manifest = new Manifest_Declare_Export_Ship_Edit("海运预配舱单");
			}else{
				return false;
			}
		}
		
		
		
		Manifest.setFunctionCode(string);
		Manifest.getDataStore().setXmlbooking(booking);
		
		if (Manifest.ExecutePack() == true) {
			System.out.println("打包完成,文件名:" + Manifest.getFName());
			if (MessageDialog.openQuestion(null, "提示", "文件打包完成，是否下载到本地") == true) {

				DirectoryDialog dirDlg = new DirectoryDialog(PlatformUI
						.createDisplay().getActiveShell().getShell());
				dirDlg.setText("请选择目录");

				String dir = dirDlg.open();
				if (dir != null) {
					File src = new File(Manifest.getEdiFullName());
					File dest = new File(dir + Manifest.getSeparator()
							+ Manifest.getEdiFileName());//  
					try {
						FileUtils.copyFile(src, dest);
						// FileUtils.copyDirectory(src, dest);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("用户取消保存");
				}
			}

		}

		return true;
	}
}
