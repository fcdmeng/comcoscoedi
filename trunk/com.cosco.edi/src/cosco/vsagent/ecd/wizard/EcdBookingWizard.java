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
		setWindowTitle("�ᵥ");
	}
	
	public void addPages(){
		ecdBookingPage = new EcdBookingPage("ecdBookingPage");
		addPage(ecdBookingPage);
		
		if(getFlag().equals("MODIFY")){
			this.setWindowTitle("�ᵥ �޸�"+" --->�ᵥ��:"+getEcdbooking().getBlno());
		}
		
		if(getFlag().equals("INSERT")){
			this.setWindowTitle("�ᵥ �½�");
		}
		
		if(getFlag().equals("VIEW")){
			this.setWindowTitle("�ᵥ �鿴");
		}
		
		ecdBookingPage.setFlag(getFlag());
		ecdBookingPage.setEcdbooking(getEcdbooking());
	}

	
	@Override
	public boolean performFinish() {
		ecdbooking = new Ecdbooking();
		//�õ������ڸ��ؼ���ֵ
		ecdBookingPage.getValue(ecdbooking);

		if(ecdbooking.getBlno() == null || ecdbooking.getBlno().equals("")){
			ecdBookingPage.setErrorMessage("�ᵥ�Ų���Ϊ��");
			ecdBookingPage.getBlnoText().setFocus();
			return false;
		}
		
		if(ecdbooking.getLdpename() == null || ecdbooking.getLdpename().equals("")){
			ecdBookingPage.setErrorMessage("װ���۲���Ϊ��");
			ecdBookingPage.getLdpenameText().setFocus();
			return false;
		}
		
		if(ecdbooking.getDispename() == null || ecdbooking.getDispename().equals("")){
			ecdBookingPage.setErrorMessage("ж���۲���Ϊ��");
			ecdBookingPage.getDispenameText().setFocus();
			
			return false;
		}
		
		if(ecdbooking.getDelpename() == null || ecdbooking.getDelpename().equals("")){
			ecdBookingPage.setErrorMessage("Ŀ�ĸ۲���Ϊ��");
			ecdBookingPage.getDelpenameText().setFocus();
			return false;
		}
		
		if(ecdbooking.getPcename() == null || ecdbooking.getPcename().equals("")){
			ecdBookingPage.setErrorMessage("���ѷ�ʽ����Ϊ��");
			return false;
		}
		
		if(ecdBookingPage.getPkgnameText() == null || ecdBookingPage.getPkgnameText().getText().equals("")){
			ecdBookingPage.setErrorMessage("��װ���벻��Ϊ��");
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
