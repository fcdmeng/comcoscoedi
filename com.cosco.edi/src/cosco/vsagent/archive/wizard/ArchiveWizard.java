package cosco.vsagent.archive.wizard;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import cosco.vsagent.app.Constants;
import cosco.vsagent.db.DbOperate;
import cosco.vsagent.model.IUser;
import cosco.vsagent.model.Student;
import cosco.vsagent.model.Teacher;
import cosco.vsagent.system.SmsFactory;


public class ArchiveWizard extends Wizard {
	private UserTypePage userTypePage;//选择用户类型
	private UserInfoPage userInfoPage;//用户基本信息页
	private SchoolClassPage schoolClassPage;
	private CoursesPage coursesPage;
	private IUser user;
	
	public void addPages(){
		userTypePage = new UserTypePage();
		userInfoPage = new UserInfoPage("userInfoPage");
		schoolClassPage = new SchoolClassPage("schoolClassPage");
		coursesPage = new CoursesPage();
		
		
		
		addPage(userTypePage);
		addPage(userInfoPage);
		addPage(coursesPage);
		addPage(schoolClassPage);
	}

	public boolean canFinish(){
		IWizardPage page = getContainer().getCurrentPage();
		if(page != coursesPage && page != schoolClassPage)
			return false;
		return super.canFinish();
	}
	
	public IWizardPage getNextPage(IWizardPage page){
		if(page == userInfoPage){
			String type = userTypePage.getUserType();
			if(type.equals(Constants.IUSER_TEACHER_TYPE))
				return coursesPage;
			else if(type.equals(Constants.IUSER_STUDENT_TYPE))
				return schoolClassPage;
			
		}
		
		if (page == coursesPage || page == schoolClassPage) return null;
		return super.getNextPage(page);
	}
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		String type = userTypePage.getUserType();
		if(type.equals(Constants.IUSER_TEACHER_TYPE)){
			Teacher o = new Teacher();
			coursesPage.getValue(o);
			user = o;
		}else if(type.equals(Constants.IUSER_STUDENT_TYPE)){
			Student o = new Student();
			schoolClassPage.getValue(o);
			user = o;
		}
		
		userInfoPage.getValue(user);
		DbOperate db = SmsFactory.getDbOperate();
		IUser o = db.getUser(user.getUserId());
		
		if(o != null){
			MessageDialog.openError(null, "", "用户名重复，不允许保存");
			return false;
		}
		
		return true;
		
	}
	
	public IUser getUser(){
		return user;
	}

}
