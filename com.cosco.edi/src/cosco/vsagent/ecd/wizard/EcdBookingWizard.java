package cosco.vsagent.ecd.wizard;

import org.eclipse.jface.wizard.Wizard;
//import org.eclipse.jdt.internal.ui.JavaPlugin;
//import org.eclipse.jdt.internal.ui.JavaPluginImages;

import cosco.vsagent.mapping.ecd.Ecdbooking;

public class EcdBookingWizard extends Wizard {
	private EcdBookingPage ecdBookingPage;
	private String flag;
	private Ecdbooking ecdbooking;
	
	public EcdBookingWizard(){
//		setDefaultPageImageDescriptor(JavaPluginImages.DESC_WIZBAN_NEWCLASS);
//		setDialogSettings(JavaPlugin.getDefault().getDialogSettings());
		setWindowTitle("提单");
	}
	
	public void addPages(){
		ecdBookingPage = new EcdBookingPage("ecdBookingPage");
		addPage(ecdBookingPage);
		
		if(getFlag().equals("MODIFY")){
			this.setWindowTitle("提单 修改"+" --->提单号:"+getEcdbooking().getBlno());
		}
		
		if(getFlag().equals("INSERT")){
			this.setWindowTitle("提单 新建");
		}
		
		if(getFlag().equals("VIEW")){
			this.setWindowTitle("提单 查看");
		}
		
		ecdBookingPage.setFlag(getFlag());
		ecdBookingPage.setEcdbooking(getEcdbooking());
	}

	
	@Override
	public boolean performFinish() {
		ecdbooking = new Ecdbooking();
		//得到窗口内各控件的值
		ecdBookingPage.getValue(ecdbooking);

		if(ecdbooking.getBlno() == null || ecdbooking.getBlno().equals("")){
			ecdBookingPage.setErrorMessage("提单号不能为空");
			ecdBookingPage.getBlnoText().setFocus();
			return false;
		}
		
		if(ecdbooking.getLdpename() == null || ecdbooking.getLdpename().equals("")){
			ecdBookingPage.setErrorMessage("装货港不能为空");
			ecdBookingPage.getLdpenameText().setFocus();
			return false;
		}
		
		if(ecdbooking.getDispename() == null || ecdbooking.getDispename().equals("")){
			ecdBookingPage.setErrorMessage("卸货港不能为空");
			ecdBookingPage.getDispenameText().setFocus();
			
			return false;
		}
		
		if(ecdbooking.getDelpename() == null || ecdbooking.getDelpename().equals("")){
			ecdBookingPage.setErrorMessage("目的港不能为空");
			ecdBookingPage.getDelpenameText().setFocus();
			return false;
		}
		
		if(ecdbooking.getPcename() == null || ecdbooking.getPcename().equals("")){
			ecdBookingPage.setErrorMessage("付费方式不能为空");
			return false;
		}
		
		if(ecdBookingPage.getPkgnameText() == null || ecdBookingPage.getPkgnameText().getText().equals("")){
			ecdBookingPage.setErrorMessage("包装代码不能为空");
			return false;
		}
		
		return true;
	}

	public void setFlag(String flag) {
		// TODO Auto-generated method stub
		this.flag = flag;
		
	}

	public void setEcdbooking(Ecdbooking ecdbooking) {
		this.ecdbooking = ecdbooking;
		
	}

	public String getFlag() {
		return flag;
	}

	public Ecdbooking getEcdbooking() {
		return ecdbooking;
	}
	

}
