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
			setErrorMessage("���벻��Ϊ��");
			return;
		}
		
		
		if (enameText.getText().equals("")){
			setErrorMessage("Ӣ�Ĳ���Ϊ��");
			return;
		}
		
		if (cnameText.getText().equals("")){
			setErrorMessage("���Ĳ���Ϊ��");
			return;
		}
		setErrorMessage(null);
		setPageComplete(true);

	}

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		setTitle("ί�з�");
		setMessage("ί�з���ϸ", INFORMATION);
		
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
		new Label(top, SWT.NONE).setText("����");
		partycodeText =  new SmsUtil().createText(top, SWT.BORDER);
		if(flag.equals("MODIFY")){
			partycodeText.setEnabled(false);
		}
		partycodeText.addModifyListener(this);
		partycodeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("������");
		memocodeText = new SmsUtil().createText(top, SWT.BORDER);
		memocodeText.addModifyListener(this);
		memocodeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("����");
		nationcodeCombo = new SmsUtil().createNationCombo(top, SWT.BORDER);
		nationcodeCombo.addModifyListener(this);
		nationcodeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("ע���ʽ�");
		fundText = new SmsUtil().createText(top, SWT.BORDER);
		fundText.addModifyListener(this);
		fundText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(top, SWT.NONE).setText("Ӣ������");
		enameText = new SmsUtil().createText(top, SWT.BORDER);
		enameText.addModifyListener(this);
		enameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 4));
		new Label(top, SWT.NONE).setText("Ӣ�ļ��");
		ebrnameText = new SmsUtil().createText(top, SWT.BORDER);
		ebrnameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 2));
		
		new Label(top, SWT.NONE).setText("��������");
		cnameText = new SmsUtil().createText(top, SWT.BORDER);
		cnameText.addModifyListener(this);
		cnameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 4));
		new Label(top, SWT.NONE).setText("���ļ��");
		cbrnameText = new SmsUtil().createText(top, SWT.BORDER);
		cbrnameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 2));
		
		new Label(top, SWT.NONE).setText("��ϵ��");
		contacterText = new SmsUtil().createText(top, SWT.BORDER);
		contacterText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		
		new Label(top, SWT.NONE).setText("��ϵ�绰");
		telexText = new SmsUtil().createText(top, SWT.BORDER);
		telexText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("����");
		telephoneText = new SmsUtil().createText(top, SWT.BORDER);
		telephoneText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("�紫����");
		faxText = new SmsUtil().createText(top, SWT.BORDER);
		faxText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		
		new Label(top, SWT.NONE).setText("��ַ");
		addressText = new SmsUtil().createText(top, SWT.BORDER);
		addressText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 5));
		new Label(top, SWT.NONE).setText("�ʱ�");
		postalText = new SmsUtil().createText(top, SWT.BORDER);
		postalText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		
		new Label(top, SWT.NONE).setText("Email");
		emailText = new SmsUtil().createText(top, SWT.BORDER);
		emailText.setLayoutData(createGridData(GridData.FILL_HORIZONTAL, 7));
		
		new Label(top, SWT.NONE).setText("ί�з����");
		Composite rankComp = new Composite(top, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		
		gridData.horizontalSpan = 7;
		gridData.heightHint = 20;// CompositeĬ�ϵĸ߶�̫��,���ֹ��趨�߶�Ϊ20����
		rankComp.setLayoutData(gridData);
		rankComp.setLayout(new RowLayout());
		dockCheck = new Button(rankComp, SWT.CHECK);
		dockCheck.setText("�ѳ�");
		carrCheck = new Button(rankComp, SWT.CHECK);
		carrCheck.setText("������");
		cloCheck = new Button(rankComp, SWT.CHECK);
		cloCheck.setText("������");
		cusCheck = new Button(rankComp, SWT.CHECK);
		cusCheck.setText("������");
		usedcoCheck = new Button(rankComp, SWT.CHECK);
		usedcoCheck.setText("���乫˾");
		carcoCheck = new Button(rankComp, SWT.CHECK);
		carcoCheck.setText("����");
		cntcoCheck = new Button(rankComp, SWT.CHECK);
		cntcoCheck.setText("�侭Ӫ��");
		
		
		new Label(top, SWT.NONE).setText("");
		Composite cd= new Composite(top, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 7;
		gd.heightHint = 20;// CompositeĬ�ϵĸ߶�̫��,���ֹ��趨�߶�Ϊ20����
		cd.setLayoutData(gd);
		cd.setLayout(new RowLayout());
		
		proCheck = new Button(cd, SWT.CHECK);
		proCheck.setText("�ۿڹ�Ӧ��");
		entrCheck = new Button(cd, SWT.CHECK);
		entrCheck.setText("ί�з�");
		ordeptCheck = new Button(cd, SWT.CHECK);
		ordeptCheck.setText("���ղ���");
		cusmonCheck = new Button(cd, SWT.CHECK);
		cusmonCheck.setText("�½ᶩ�տͻ�");
		shipperCheck = new Button(cd, SWT.CHECK);
		shipperCheck.setText("ֱ�ӻ���");
		deptCheck = new Button(cd, SWT.CHECK);
		deptCheck.setText("��˾����");
		otherCheck = new Button(cd, SWT.CHECK);
		otherCheck.setText("����");
		
		Label memoLabel = new Label(top, SWT.NONE);
		memoLabel.setText("��    ע");
		memoLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		remarkText = new SmsUtil().createText(top, SWT.BORDER | SWT.WRAP);
		remarkText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL| GridData.FILL_VERTICAL , 7));
		
		new Label(top, SWT.NONE).setText("¼����");
		makercodeText = new SmsUtil().createText(top, SWT.BORDER);
		makercodeText.setEnabled(false);
		makercodeText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("¼��ʱ��");
		madetimeText = new SmsUtil().createText(top, SWT.BORDER);
		madetimeText.setEnabled(false);
		if(flag.equals("INSERT")){
			makercodeText.setText(getIuser().getUserId());
			madetimeText.setText(new Date().toLocaleString());
		}
		
		madetimeText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("�޸���");
		modicodeText = new SmsUtil().createText(top, SWT.BORDER);
		modicodeText.setEnabled(false);
		modicodeText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("�޸�ʱ��");
		moditimeText = new SmsUtil().createText(top, SWT.BORDER);
		moditimeText.setEnabled(false);
		moditimeText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		top.layout();
	}


	// ����GridData������ظ�����̫�࣬д��һ���������Լ��ٳ����������������Ҳ����Щ
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
