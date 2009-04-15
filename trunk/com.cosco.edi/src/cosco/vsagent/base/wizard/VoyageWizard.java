package cosco.vsagent.base.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

public class VoyageWizard extends Wizard {
	private VoyageIeTypePage voyageIeTypePage;
	private VoyageIeInfoPage voyageIeInfoPage;
	private String flag;
	
	public  VoyageWizard(String flag){
//		this.getShell().setMaximized(false);
		
		setFlag(flag);

	}
	public void addPages(){
	
		voyageIeTypePage = new VoyageIeTypePage();
		voyageIeTypePage.setFlag(getFlag());
		voyageIeInfoPage = new VoyageIeInfoPage();
		
		addPage(voyageIeTypePage);
		addPage(voyageIeInfoPage);
	}
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub

		//得到窗口内各控件的值
		if (voyageIeTypePage.getIeCombo().getText().equals("")){
			voyageIeTypePage.setErrorMessage("请选择进出口类型");
			voyageIeTypePage.getIeCombo().forceFocus();
			return false;
		}
		
		if (voyageIeTypePage.getShipCombo().getText().equals("")){
			voyageIeTypePage.setErrorMessage("请选择船名");
			voyageIeTypePage.getShipCombo().setFocus();
			return false;
		}

		
		return true;
	}

	
	public IWizardPage getNextPage(IWizardPage page){
		if(page == voyageIeTypePage){
			//得到窗口内各控件的值
			if (voyageIeTypePage.getIeCombo().getText().equals("")){
				voyageIeTypePage.setErrorMessage("请选择进出口类型");
				voyageIeTypePage.getIeCombo().forceFocus();
				return page;
			}
			
			if (voyageIeTypePage.getShipCombo().getText().equals("")){
				voyageIeTypePage.setErrorMessage("请选择船名");
				voyageIeTypePage.getShipCombo().setFocus();
				return page;
			}
		
		}
		
		return super.getNextPage(page);
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}


}
