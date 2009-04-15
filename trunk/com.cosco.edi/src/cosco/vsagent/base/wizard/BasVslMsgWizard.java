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
			this.setWindowTitle("船舶规范-增加");
		}else if(getFlag().equals("MODIFY")){
			this.setWindowTitle("船舶规范-编辑");
			basVslMsgPage.setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.getDefault(), "wizard/edtsrclkup_wiz.gif"));
			
		}else if(getFlag().equals("VIEW")){
			this.setWindowTitle("船舶规范-查看");
		}
		//传值到page页
		basVslMsgPage.setBasvslmsg(getBasvslmsg());
	}
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		
		basvslmsg = new Basvslmsg();
		//得到窗口内各控件的值
		basVslMsgPage.getValue(basvslmsg);
//		System.out.println(basvslmsg.getNetton());
		
		if(basvslmsg.getVslcode().equals("")){
			basVslMsgPage.setErrorMessage("船舶代码不能为空");
			basVslMsgPage.getVslcodeText().setFocus();
			return false;
		}
		
		if(basvslmsg.getShipcode().equals("")){
			basVslMsgPage.setErrorMessage("船舶呼号不能为空");
			basVslMsgPage.getShipcodeText().setFocus();
			return false;
		}
		
		
		if(basvslmsg.getEnname().equals("")){
			basVslMsgPage.setErrorMessage("英文船名不能为空");
			basVslMsgPage.getEnnameText().setFocus();
			
			return false;
		}
		
		if(basvslmsg.getCnname().equals("")){
			basVslMsgPage.setErrorMessage("中文船名不能为空");
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
