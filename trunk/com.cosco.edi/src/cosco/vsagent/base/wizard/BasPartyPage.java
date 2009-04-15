package cosco.vsagent.base.wizard;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;

import cosco.vsagent.app.Activator;
import cosco.vsagent.mapping.base.Basparty;
import cosco.vsagent.model.IUser;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.SmsUtil;

public class BasPartyPage extends WizardPage implements ModifyListener {
	private Text partycodeText,memocodeText,fundText,enameText,ebrnameText;
	private Combo nationcodeCombo;
	private Text cnameText,cbrnameText,contacterText,telexText,faxText,telephoneText;
	private Text addressText,postalText, emailText,remarkText;
	private Text makercodeText,makerText,madetimeText,modicodeText,modifierText,moditimeText;
	
	private Button dockCheck, carrCheck, cloCheck, cusCheck, usedcoCheck, carcoCheck, cntcoCheck,
		deptCheck, otherCheck, proCheck,entrCheck, ordeptCheck, cusmonCheck, shipperCheck;

//	private Text mainkeyText;
//	private Text dockcodeText,docknameText;
//	private Text ifcoscoText,mgrcodeText;
//	private Text mgrdescText,transwayText,vslmgrcodeText,nationnameText;
//	private Text deptcodeText,deptnameText,vslmgrnameText,ifaccountText,effectText,keeperText,is_FleetText;
	String flag = "";
	private Basparty basparty;
	public Basparty getBasparty() {
		return basparty;
	}

	public void setBasparty(Basparty basparty) {
		this.basparty = basparty;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	protected BasPartyPage(String pageName) {
		super(pageName);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.getDefault(), "wizard/newfile_wiz.gif"));
		// TODO Auto-generated constructor stub
	}

	public void modifyText(ModifyEvent e) {
		// TODO Auto-generated method stub
		setPageComplete(false);
		if(partycodeText.getText().equals("")){
			setErrorMessage("代码不能为空");
			return;
		}
		
		
		if (enameText.getText().equals("")){
			setErrorMessage("英文不能为空");
			return;
		}
		
		if (cnameText.getText().equals("")){
			setErrorMessage("中文不能为空");
			return;
		}
		setErrorMessage(null);
		setPageComplete(true);

	}

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		setTitle("委托方");
		setMessage("委托方详细", INFORMATION);
		
		Composite top = new Composite(parent, SWT.NULL);
		basparty(top);
		setControl(top);
		if(!getFlag().equals("INSERT")) setValue(getBasparty());

	}

	private void setValue(Basparty basparty) {
		partycodeText.setText(basparty.getPartycode());
		memocodeText.setText(StringUtils.trimToEmpty(basparty.getMemocode()));
		fundText.setText(StringUtils.trimToEmpty(basparty.getFund()));
		enameText.setText(StringUtils.trimToEmpty(basparty.getEname()));
		ebrnameText.setText(StringUtils.trimToEmpty(basparty.getEbrname()));
		nationcodeCombo.setText(StringUtils.trimToEmpty(basparty.getNationcode()));
		cnameText.setText(StringUtils.trimToEmpty(basparty.getCname()));
		cbrnameText.setText(StringUtils.trimToEmpty(basparty.getCbrname()));
		contacterText.setText(StringUtils.trimToEmpty(basparty.getContacter()));
		telexText.setText(StringUtils.trimToEmpty(basparty.getTelex()));
		faxText.setText(StringUtils.trimToEmpty(basparty.getFax()));
		telephoneText.setText(StringUtils.trimToEmpty(basparty.getTelephone()));
		addressText.setText(StringUtils.trimToEmpty(basparty.getAddress()));
		postalText.setText(StringUtils.trimToEmpty(basparty.getPostal()));
		emailText.setText(StringUtils.trimToEmpty(basparty.getEmail()));
		remarkText.setText(StringUtils.trimToEmpty(basparty.getRemark()));
		makercodeText.setText(StringUtils.trimToEmpty(basparty.getMakercode()));
//		makerText, modifierText
		if(basparty.getMadetime() != null)
			madetimeText.setText(StringUtils.trimToEmpty(basparty.getMadetime().toLocaleString()));
		modicodeText.setText(StringUtils.trimToEmpty(basparty.getModicode()));
		if(basparty.getModitime() != null)
			moditimeText.setText(StringUtils.trimToEmpty(basparty.getModitime().toLocaleString()));
	}

	public void getValue(Basparty basparty) {
		basparty.setPartycode(partycodeText.getText());
		basparty.setMemocode(memocodeText.getText());
		basparty.setFund(fundText.getText());
		basparty.setEname(enameText.getText());
		basparty.setEbrname(ebrnameText.getText());
;		basparty.setNationcode(nationcodeCombo.getText());
		basparty.setCname(cnameText.getText());
		basparty.setCbrname(cbrnameText.getText());
		basparty.setContacter(contacterText.getText());
		basparty.setTelex(telexText.getText());
		basparty.setFax(faxText.getText());
		basparty.setTelephone(telephoneText.getText());
		basparty.setAddress(addressText.getText());
		basparty.setPostal(postalText.getText());
		basparty.setEmail(emailText.getText());
		basparty.setRemark(remarkText.getText());
		basparty.setMakercode(makercodeText.getText());
		basparty.setMadetime(Timestamp.valueOf(madetimeText.getText()));
		basparty.setModicode(modicodeText.getText());
		basparty.setModitime(Timestamp.valueOf(moditimeText.getText()));
		
	}
	
	public void basparty(Composite top){
		top.setLayout(new GridLayout(8, false));
		new Label(top, SWT.NONE).setText("代码");
		partycodeText =  new SmsUtil().createText(top, SWT.BORDER);
		if(flag.equals("MODIFY")){
			partycodeText.setEnabled(false);
		}
		partycodeText.addModifyListener(this);
		partycodeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("助记码");
		memocodeText = new SmsUtil().createText(top, SWT.BORDER);
		memocodeText.addModifyListener(this);
		memocodeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("国籍");
		nationcodeCombo = new SmsUtil().createNationCombo(top, SWT.BORDER);
		nationcodeCombo.addModifyListener(this);
		nationcodeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("注册资金");
		fundText = new SmsUtil().createText(top, SWT.BORDER);
		fundText.addModifyListener(this);
		fundText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(top, SWT.NONE).setText("英文名称");
		enameText = new SmsUtil().createText(top, SWT.BORDER);
		enameText.addModifyListener(this);
		enameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 4));
		new Label(top, SWT.NONE).setText("英文简称");
		ebrnameText = new SmsUtil().createText(top, SWT.BORDER);
		ebrnameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 2));
		
		new Label(top, SWT.NONE).setText("中文名称");
		cnameText = new SmsUtil().createText(top, SWT.BORDER);
		cnameText.addModifyListener(this);
		cnameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 4));
		new Label(top, SWT.NONE).setText("中文简称");
		cbrnameText = new SmsUtil().createText(top, SWT.BORDER);
		cbrnameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 2));
		
		new Label(top, SWT.NONE).setText("联系人");
		contacterText = new SmsUtil().createText(top, SWT.BORDER);
		contacterText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		
		new Label(top, SWT.NONE).setText("联系电话");
		telexText = new SmsUtil().createText(top, SWT.BORDER);
		telexText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("传真");
		telephoneText = new SmsUtil().createText(top, SWT.BORDER);
		telephoneText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("电传号码");
		faxText = new SmsUtil().createText(top, SWT.BORDER);
		faxText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		
		new Label(top, SWT.NONE).setText("地址");
		addressText = new SmsUtil().createText(top, SWT.BORDER);
		addressText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 5));
		new Label(top, SWT.NONE).setText("邮编");
		postalText = new SmsUtil().createText(top, SWT.BORDER);
		postalText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		
		new Label(top, SWT.NONE).setText("Email");
		emailText = new SmsUtil().createText(top, SWT.BORDER);
		emailText.setLayoutData(createGridData(GridData.FILL_HORIZONTAL, 7));
		
		new Label(top, SWT.NONE).setText("委托方身份");
		Composite rankComp = new Composite(top, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		
		gridData.horizontalSpan = 7;
		gridData.heightHint = 20;// Composite默认的高度太高,故手工设定高度为20像素
		rankComp.setLayoutData(gridData);
		rankComp.setLayout(new RowLayout());
		dockCheck = new Button(rankComp, SWT.CHECK);
		dockCheck.setText("堆场");
		carrCheck = new Button(rankComp, SWT.CHECK);
		carrCheck.setText("承运人");
		cloCheck = new Button(rankComp, SWT.CHECK);
		cloCheck.setText("用箱人");
		cusCheck = new Button(rankComp, SWT.CHECK);
		cusCheck.setText("订舱人");
		usedcoCheck = new Button(rankComp, SWT.CHECK);
		usedcoCheck.setText("租箱公司");
		carcoCheck = new Button(rankComp, SWT.CHECK);
		carcoCheck.setText("车队");
		cntcoCheck = new Button(rankComp, SWT.CHECK);
		cntcoCheck.setText("箱经营人");
		
		
		new Label(top, SWT.NONE).setText("");
		Composite cd= new Composite(top, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 7;
		gd.heightHint = 20;// Composite默认的高度太高,故手工设定高度为20像素
		cd.setLayoutData(gd);
		cd.setLayout(new RowLayout());
		
		proCheck = new Button(cd, SWT.CHECK);
		proCheck.setText("港口供应商");
		entrCheck = new Button(cd, SWT.CHECK);
		entrCheck.setText("委托方");
		ordeptCheck = new Button(cd, SWT.CHECK);
		ordeptCheck.setText("订舱部门");
		cusmonCheck = new Button(cd, SWT.CHECK);
		cusmonCheck.setText("月结订舱客户");
		shipperCheck = new Button(cd, SWT.CHECK);
		shipperCheck.setText("直接货主");
		deptCheck = new Button(cd, SWT.CHECK);
		deptCheck.setText("本司部门");
		otherCheck = new Button(cd, SWT.CHECK);
		otherCheck.setText("其它");
		
		Label memoLabel = new Label(top, SWT.NONE);
		memoLabel.setText("备    注");
		memoLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		remarkText = new SmsUtil().createText(top, SWT.BORDER | SWT.WRAP);
		remarkText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL| GridData.FILL_VERTICAL , 7));
		
		new Label(top, SWT.NONE).setText("录入人");
		makercodeText = new SmsUtil().createText(top, SWT.BORDER);
		makercodeText.setEnabled(false);
		makercodeText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("录入时间");
		madetimeText = new SmsUtil().createText(top, SWT.BORDER);
		madetimeText.setEnabled(false);
		if(flag.equals("INSERT")){
			makercodeText.setText(getIuser().getUserId());
			madetimeText.setText(new Date().toLocaleString());
		}
		
		madetimeText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("修改人");
		modicodeText = new SmsUtil().createText(top, SWT.BORDER);
		modicodeText.setEnabled(false);
		modicodeText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("修改时间");
		moditimeText = new SmsUtil().createText(top, SWT.BORDER);
		moditimeText.setEnabled(false);
		moditimeText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		top.layout();
	}


	// 生成GridData对象的重复代码太多，写成一个方法可以减少程序的行数，用起来也方便些
	private static GridData createGridData(int style, int horizontalSpan) {
		GridData gridData = new GridData(style);
		gridData.horizontalSpan = horizontalSpan;
		return gridData;
	}
	
	private static GridData createGridData(int style, int horizontalSpan, int verticalSpan) {
		GridData gridData = new GridData(style);
		gridData.horizontalSpan = horizontalSpan;
		gridData.verticalSpan = verticalSpan;
		return gridData;
	}
	
	public IUser getIuser(){
		Context ctx = Context.getInstance();
		return ctx.getCurrentUser();
//		System.out.println(ctx.getCurrentUser().getUserId());
	}


}
