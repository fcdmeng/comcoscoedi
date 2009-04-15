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
		item1.setText("����");
		item1.setImage(ImagesContext.getImage(ImagesContext.RUN_EXC));
		MenuItem item2 = new MenuItem(menu, SWT.CASCADE);
		item2.setText("ԭʼ�յ�");
		item2.setImage(ImagesContext.getImage(ImagesContext.SELECTVOYAGE));
		MenuItem item3 = new MenuItem(menu, SWT.CASCADE);
		item3.setText("Ԥ��յ�");
		item3.setImage(ImagesContext.getImage(ImagesContext.SELECTVOYAGE));
		
//		MenuItem item4 = new MenuItem(menu, SWT.CASCADE);
//		item4.setText("װ�زյ�");
//		item4.setImage(ImagesContext.getImage(ImagesContext.SELECTVOYAGE));
		{
		Menu subMenu = new Menu(menu);
		item2.setMenu(subMenu);
		MenuItem subItem1 = new MenuItem(subMenu, SWT.PUSH);
		subItem1.setText("һ���걨");
		subItem1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Selected("һ���걨",true);
			}
		});

		MenuItem subItem2 = new MenuItem(subMenu, SWT.PUSH);
		subItem2.setText("�����걨");

		subItem2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("�����걨");
				Selected("�����걨",true);
			}
		});

		MenuItem add = new MenuItem(subMenu, SWT.PUSH);
		add.setText("����");
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Selected("����",true);
			}
		});

		MenuItem update = new MenuItem(subMenu, SWT.PUSH);
		update.setText("���");
		update.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Selected("���",true);
			}
		});
		MenuItem delete = new MenuItem(subMenu, SWT.PUSH);
		delete.setText("ɾ��");
		delete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("ɾ��");
				Selected("ɾ��",true);
			}
		});
		MenuItem receipt = new MenuItem(subMenu, SWT.PUSH);
		receipt.setText("��ִ");
		receipt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("��ִ");
				Selected("��ִ",true);
			}
		});
	}
		
		Menu ExporMenu = new Menu(menu);
		item3.setMenu(ExporMenu);
		MenuItem ExportItem1 = new MenuItem(ExporMenu, SWT.PUSH);
		ExportItem1.setText("һ���걨");
		ExportItem1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				//Selected("һ���걨");
			}
		});

		MenuItem ExportItem2 = new MenuItem(ExporMenu, SWT.PUSH);
		ExportItem2.setText("�����걨");

		ExportItem2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("�����걨");
				//Selected("�����걨");
			}
		});

		MenuItem Exportadd = new MenuItem(ExporMenu, SWT.PUSH);
		Exportadd.setText("����");
		Exportadd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
//				Selected("����");
			}
		});

		MenuItem Exportupdate = new MenuItem(ExporMenu, SWT.PUSH);
		Exportupdate.setText("���");
		Exportupdate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
//				Selected("���");
			}
		});
		MenuItem Exportdelete = new MenuItem(ExporMenu, SWT.PUSH);
		Exportdelete.setText("ɾ��");
		Exportdelete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("ɾ��");
//				Selected("ɾ��");
			}
		});
		MenuItem Exporreceipt = new MenuItem(ExporMenu, SWT.PUSH);
		Exporreceipt.setText("��ִ");
		Exporreceipt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("��ִ");
//				Selected("��ִ");
			}
		});
	}
	
	// �õ�ѡ�е���
	public boolean Selected(String string, boolean type) {
		int SelectionCount = this.table.getSelectionCount();
		if (SelectionCount == 0)
			return false;
		if (MessageDialog.openQuestion(null, "��ʾ", "��ѡ����["
				+ String.valueOf(SelectionCount) + "]���˵���ȷ��Ҫִ����") == false)
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
			if (string.equals("һ���걨")){
				Manifest = new Manifest_Declare_Import_Ship_First("����ԭʼ�յ�");
			}else if(string.equals("�����걨")){
				Manifest = new Manifest_Declare_Import_Ship_Secondly("����ԭʼ�յ�");
			}
			else if(string.equals("ɾ��")){
				Manifest = new Manifest_Declare_Import_Ship_Delete("����ԭʼ�յ�");
			}else if(string.equals("���")){
				Manifest = new Manifest_Declare_Import_Ship_Edit("����ԭʼ�յ�");
			}else{
				return false;
			}
		}
		
		if (type == false){
			if (string.equals("һ���걨")){
				Manifest = new Manifest_Declare_Export_Ship_First("����Ԥ��յ�");
			}else if(string.equals("�����걨")){
				Manifest = new Manifest_Declare_Export_Ship_Secondly("����Ԥ��յ�");
			}
			else if(string.equals("ɾ��")){
				Manifest = new Manifest_Declare_Import_Ship_Delete("����Ԥ��յ�");
			}else if(string.equals("���")){
				Manifest = new Manifest_Declare_Export_Ship_Edit("����Ԥ��յ�");
			}else{
				return false;
			}
		}
		
		
		
		Manifest.setFunctionCode(string);
		Manifest.getDataStore().setXmlbooking(booking);
		
		if (Manifest.ExecutePack() == true) {
			System.out.println("������,�ļ���:" + Manifest.getFName());
			if (MessageDialog.openQuestion(null, "��ʾ", "�ļ������ɣ��Ƿ����ص�����") == true) {

				DirectoryDialog dirDlg = new DirectoryDialog(PlatformUI
						.createDisplay().getActiveShell().getShell());
				dirDlg.setText("��ѡ��Ŀ¼");

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
					System.out.println("�û�ȡ������");
				}
			}

		}

		return true;
	}
}
