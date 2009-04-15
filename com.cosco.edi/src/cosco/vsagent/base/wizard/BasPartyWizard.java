package cosco.vsagent.base.wizard;

import com.swtdesigner.ResourceManager;
import org.eclipse.jface.wizard.Wizard;

import cosco.vsagent.app.Activator;
import cosco.vsagent.mapping.base.Basparty;

public class BasPartyWizard extends Wizard {
	private BasPartyPage basPartyPage;
	private Basparty basparty;
	private String flag;
	

	public String getFlag() {
		return flag;
	}


	public Basparty getBasparty() {
		return basparty;
	}


	public void setBasparty(Basparty basparty) {
		this.basparty = basparty;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public void addPages(){
		basPartyPage = new BasPartyPage("basPartyPage");
		addPage(basPartyPage);
		basPartyPage.setFlag(getFlag());
		if(getFlag().equals("INSERT")){
			this.setWindowTitle("ί�з�-����");
		}else if(getFlag().equals("MODIFY")){
			this.setWindowTitle("ί�з�-�༭");
			basPartyPage.setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.getDefault(), "wizard/edtsrclkup_wiz.gif"));
		}else if(getFlag().equals("VIEW")){
			this.setWindowTitle("ί�з�-�鿴");
		}
		
		basPartyPage.setBasparty(getBasparty());
	}


	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		basparty = new Basparty();
		basPartyPage.getValue(basparty);
		
		if(basparty.getPartycode().equals("")){
			basPartyPage.setErrorMessage("ί�з����벻��Ϊ��");
			return false;
		}
		if(basparty.getEname().equals("")){
			basPartyPage.setErrorMessage("ί�з�Ӣ�����Ʋ���Ϊ��");
			return false;
		}
		
		if(basparty.getCname().equals("")){
			basPartyPage.setErrorMessage("ί�з��������Ʋ���Ϊ��");
			return false;
		}
		
		
		return true;
	}

}
