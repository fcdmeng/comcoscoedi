package cosco.vsagent.base.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.swtdesigner.ResourceManager;

import cosco.vsagent.app.Activator;
import cosco.vsagent.app.Constants;
import cosco.vsagent.mapping.base.Basvslmsg;
import cosco.vsagent.model.Course;

public class BasVslMsgWizard extends Wizard {
	private BasVslMsgPage basVslMsgPage;
	private Basvslmsg basvslmsg;
	private String flag ="";
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Basvslmsg getBasvslmsg() {
		return basvslmsg;
	}
	public void setBasvslmsg(Basvslmsg basvslmsg) {
		this.basvslmsg = basvslmsg;
	}
	
	public void addPages(){

		
		basVslMsgPage = new BasVslMsgPage("basVslMsgPage");
		addPage(basVslMsgPage);
		basVslMsgPage.setFlag(getFlag());
		if(getFlag().equals("INSERT")){
			this.setWindowTitle("�����淶-����");
		}else if(getFlag().equals("MODIFY")){
			this.setWindowTitle("�����淶-�༭");
			basVslMsgPage.setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.getDefault(), "wizard/edtsrclkup_wiz.gif"));
			
		}else if(getFlag().equals("VIEW")){
			this.setWindowTitle("�����淶-�鿴");
		}
		//��ֵ��pageҳ
		basVslMsgPage.setBasvslmsg(getBasvslmsg());
	}
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		
		basvslmsg = new Basvslmsg();
		//�õ������ڸ��ؼ���ֵ
		basVslMsgPage.getValue(basvslmsg);
//		System.out.println(basvslmsg.getNetton());
		
		if(basvslmsg.getVslcode().equals("")){
			basVslMsgPage.setErrorMessage("�������벻��Ϊ��");
			basVslMsgPage.getVslcodeText().setFocus();
			return false;
		}
		
		if(basvslmsg.getShipcode().equals("")){
			basVslMsgPage.setErrorMessage("�������Ų���Ϊ��");
			basVslMsgPage.getShipcodeText().setFocus();
			return false;
		}
		
		
		if(basvslmsg.getEnname().equals("")){
			basVslMsgPage.setErrorMessage("Ӣ�Ĵ�������Ϊ��");
			basVslMsgPage.getEnnameText().setFocus();
			
			return false;
		}
		
		if(basvslmsg.getCnname().equals("")){
			basVslMsgPage.setErrorMessage("���Ĵ�������Ϊ��");
			basVslMsgPage.getCnnameText().setFocus();
			return false;
		}
		
		
		return true;
	}
	
	public IWizardPage getNextPage(IWizardPage page){
		if(page == basVslMsgPage){
	
		
		}
		
		return super.getNextPage(page);
	}

}
