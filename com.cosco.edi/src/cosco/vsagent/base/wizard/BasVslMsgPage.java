package cosco.vsagent.base.wizard;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.LongValidator;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
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
import cosco.vsagent.mapping.base.Basvslmsg;
import cosco.vsagent.model.IUser;
import cosco.vsagent.system.Context;
import cosco.vsagent.system.SmsUtil;
import cosco.vsagent.util.UUIDGenerator;

public class BasVslMsgPage extends WizardPage  implements ModifyListener{
	private Basvslmsg basvslmsg;
	
	private Text shipcodeText,vslcodeText,ennameText,cnnameText;
	private Combo deptcodeCombo,typenameCombo;
	private Text nationcnamText,nationpnamText,builddateText;
	private Text ownernameText;
	private Text grosstonText,nettonText,loadtonText,speedText;
	private Text lengthText, widthText, heightText, horsepowerText;
	private Text fulldraftText, hatchnumText, linenameText;
	private Text sumteuText, packnumText, bulknumText, lightdraftText;
	private Text tlxText, faxnoText, telText;
	private Text mobileText, emailText;
	private Text isscclassText, isscnoText;
	private Text gearsetText, secure_certificate_chdayText;
	private Text plaguenoText, sanitationText;
	private Button onlineButton, selfagentButton;
	private Text remarkText;
	private Text makerText, madetimeText, modifierText, moditimeText;
	
	String flag ="";
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BasVslMsgPage(String pageName) {
		super(pageName);
		setDescription("Wizard Page description");
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.getDefault(), "wizard/newfile_wiz.gif"));
		
	}

	/**
	 * Create contents of the wizard
	 * @param parent
	 */
	public void createControl(Composite parent) {
		setTitle("�����淶");
		setMessage("�����淶��ϸ", INFORMATION);
		Composite top = new Composite(parent, SWT.NULL);
		
		basvslmsg(top);
		 
		setControl(top);
		if(!getFlag().equals("INSERT")) setValue(getBasvslmsg());
	}


	public void modifyText(ModifyEvent e) {
		// TODO Auto-generated method stub
		setPageComplete(false);
		
		if(vslcodeText.getText().equals("")){
			setErrorMessage("�������벻��Ϊ��");
			return;
		}
		
		if(shipcodeText.getText().equals("")){
			setErrorMessage("�������Ų���Ϊ��");
			return;
		}
		
		if(ennameText.getText().equals("")){
			setErrorMessage("Ӣ�Ĵ�������Ϊ��");
			return;
		}
		
		if(cnnameText.getText().equals("")){
			setErrorMessage("���Ĵ�������Ϊ��");
			return;
		}
		
		setErrorMessage(null);
		setPageComplete(true);
		
	}
	

	public void basvslmsg(Composite top){
		top.setLayout(new GridLayout(8, false));
		
		new Label(top, SWT.NONE).setText("��������");
		vslcodeText = new Text(top, SWT.BORDER);
		vslcodeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		vslcodeText.addModifyListener(this);
			
		vslcodeText.addVerifyListener(
				new VerifyListener(){
				    public void verifyText(VerifyEvent e) {
				        e.text=e.text.toUpperCase();
				  }
				}
				);
		
		
		new Label(top, SWT.NONE).setText("��������");
		shipcodeText = new Text(top, SWT.BORDER);
		shipcodeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		shipcodeText.addModifyListener(this);
		new Label(top, SWT.NONE).setText("��������");
		typenameCombo = new Combo(top, SWT.DROP_DOWN);
		addCombodata(typenameCombo);
		typenameCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Label deptLabel = new Label(top, SWT.NONE);
		deptLabel.setText("��    ��");
		deptcodeCombo = new Combo(top, SWT.BORDER);
		deptcodeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		if(flag.equals("MODIFY")){
			vslcodeText.setEnabled(false);
			shipcodeText.setEnabled(false);
		}
		
		new Label(top, SWT.NONE).setText("Ӣ�Ĵ���");
//		ennameText = new Text(top, SWT.BORDER);
		ennameText = new SmsUtil().createText(top, SWT.BORDER);
		ennameText.addModifyListener(this);
		ennameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 7));
		new Label(top, SWT.NONE).setText("���Ĵ���");
//		cnnameText = new Text(top, SWT.BORDER);
		cnnameText = new SmsUtil().createText(top, SWT.BORDER);
		cnnameText.addModifyListener(this);
		cnnameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 7));
		
		new Label(top, SWT.NONE).setText("��    ��");
		nationcnamText = new Text(top, SWT.BORDER);
		
		nationcnamText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("�� �� ��");
		nationpnamText = new Text(top, SWT.BORDER);
		nationpnamText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		new Label(top, SWT.NONE).setText("��������");
		builddateText = new Text(top, SWT.BORDER);
		builddateText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		Composite c5 = new Composite(top, SWT.BORDER);
		GridData gridData3 = new GridData(GridData.FILL_HORIZONTAL);
		gridData3.horizontalSpan = 2;
		gridData3.heightHint = 20;// CompositeĬ�ϵĸ߶�̫��,���ֹ��趨�߶�Ϊ20����
		c5.setLayoutData(gridData3);
		c5.setLayout(new RowLayout());

		
		new Label(top, SWT.NONE).setText("������˾");
		ownernameText = new Text(top, SWT.BORDER);
		ownernameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 7));
		
		new Label(top, SWT.NONE).setText("��    ��");
		grosstonText = new Text(top, SWT.BORDER);
		grosstonText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("��    ��");
		nettonText = new Text(top, SWT.BORDER);
		nettonText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("�� �� ��");
		loadtonText = new Text(top, SWT.BORDER);
		loadtonText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(top, SWT.NONE).setText("��    ��");
		speedText = new Text(top, SWT.BORDER);
		speedText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(top, SWT.NONE).setText("��    ��");
		lengthText = new Text(top, SWT.BORDER);
		lengthText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("��    ��");
		widthText = new Text(top, SWT.BORDER);
		widthText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("��    ��");
		heightText = new Text(top, SWT.BORDER);
		heightText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("��    ��");
		horsepowerText = new Text(top, SWT.BORDER);
		horsepowerText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("�� �� ˮ");
		fulldraftText = new Text(top, SWT.BORDER);
		fulldraftText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("�� �� ��");
		hatchnumText = new Text(top, SWT.BORDER);
		hatchnumText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("��    ��");
		linenameText = new Text(top, SWT.BORDER);
		linenameText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 3));
		linenameText.setEnabled(false);
		
		new Label(top, SWT.NONE).setText("ȫ��TEU");
		sumteuText = new Text(top, SWT.BORDER);
		sumteuText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("�� װ ��");
		packnumText = new Text(top, SWT.BORDER);
		packnumText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("ɢ װ ��");
		bulknumText = new Text(top, SWT.BORDER);
		bulknumText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("�� �� ˮ");
		lightdraftText = new Text(top, SWT.BORDER);
		lightdraftText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("��    ��");
		tlxText = new Text(top, SWT.BORDER);
		tlxText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("��    ��");
		faxnoText = new Text(top, SWT.BORDER);
		faxnoText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 3));
		new Label(top, SWT.NONE).setText("��    ��");
		telText = new Text(top, SWT.BORDER);
		telText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("��    ��");
		mobileText = new Text(top, SWT.BORDER);
		mobileText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("��    ��");
		emailText = new Text(top, SWT.BORDER);
		emailText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 5));
		
		new Label(top, SWT.NONE).setText("��ȫ����");
		isscclassText = new Text(top, SWT.BORDER);
		isscclassText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 3));
		new Label(top, SWT.NONE).setText("�� ȫ ֤");
		isscnoText = new Text(top, SWT.BORDER);
		isscnoText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 3));
		
		new Label(top, SWT.NONE).setText("װж�豸");
		gearsetText = new Text(top, SWT.BORDER);
		gearsetText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 4));
		

		Composite c3 = new Composite(top, SWT.BORDER);
		GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_BEGINNING);
		gridData1.horizontalSpan = 3;
		gridData1.heightHint = 20;// CompositeĬ�ϵĸ߶�̫��,���ֹ��趨�߶�Ϊ20����
		c3.setLayoutData(gridData1);
		c3.setLayout(new FillLayout());
		new Label(c3, SWT.BORDER).setText("ȫ��֤����Ч��");
		secure_certificate_chdayText = new Text(c3, SWT.BORDER);

		
		new Label(top, SWT.NONE).setText("�����֤");
		plaguenoText = new Text(top, SWT.BORDER);
		plaguenoText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 1));
		new Label(top, SWT.NONE).setText("�� �� ֤");
		sanitationText = new Text(top, SWT.BORDER);
		sanitationText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL, 1));

		
		Composite rankComp = new Composite(top, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 4;
		gridData.heightHint = 20;// CompositeĬ�ϵĸ߶�̫��,���ֹ��趨�߶�Ϊ20����
		rankComp.setLayoutData(gridData);
		rankComp.setLayout(new RowLayout());
		new Label(rankComp, SWT.NONE).setText("��˾����");
		onlineButton = new Button(rankComp, SWT.CHECK);
		new Label(rankComp, SWT.NONE).setText("�Ƿ�����");
		selfagentButton = new Button(rankComp, SWT.CHECK);
		
		Label memoLabel = new Label(top, SWT.NONE);
		memoLabel.setText("��    ע");
		memoLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		remarkText = new Text(top, SWT.BORDER | SWT.WRAP);
		remarkText.setLayoutData(createGridData(GridData.HORIZONTAL_ALIGN_FILL| GridData.FILL_VERTICAL , 7));
		
		new Label(top, SWT.NONE).setText("¼ �� ��");
		makerText = new Text(top, SWT.BORDER);
		makerText.setEnabled(false);
		makerText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("¼��ʱ��");
		
		madetimeText = new Text(top, SWT.BORDER);
		madetimeText.setEnabled(false);
		madetimeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if(flag.equals("INSERT")){
			makerText.setText(getIuser().getName());
			madetimeText.setText(new Date().toLocaleString());
		}
			
		new Label(top, SWT.NONE).setText("�� �� ��");
		modifierText = new Text(top, SWT.BORDER);
		modifierText.setEnabled(false);
		modifierText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(top, SWT.NONE).setText("�޸�ʱ��");
		moditimeText = new Text(top, SWT.BORDER);
		moditimeText.setEnabled(false);
		moditimeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
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
	
	public void getValue(Basvslmsg basvslmsg){
		basvslmsg.setShipcode(shipcodeText.getText());
		basvslmsg.setVslcode(vslcodeText.getText());
		basvslmsg.setEnname(ennameText.getText());
		basvslmsg.setCnname(cnnameText.getText());
		basvslmsg.setLength(lengthText.getText());
		basvslmsg.setWidth(widthText.getText());
		basvslmsg.setHeight(heightText.getText());
		if(new DoubleValidator().isValid(nettonText.getText()))
			basvslmsg.setNetton(new Double(nettonText.getText()));
		else
			basvslmsg.setNetton(null);
		
		if(new DoubleValidator().isValid(grosstonText.getText()))
			basvslmsg.setGrosston(new Double(grosstonText.getText()));
		else
			basvslmsg.setGrosston(null);
		
		if(new DoubleValidator().isValid(loadtonText.getText()))
			basvslmsg.setLoadton(new Double(loadtonText.getText()));
		else
			basvslmsg.setLoadton(null);
//		basvslmsg.setMakercode(makercodeText.getText());
		basvslmsg.setMaker(makerText.getText());
//		System.out.println(Timestamp.valueOf(madetimeText.getText()));
		basvslmsg.setMadetime(Timestamp.valueOf(madetimeText.getText()));
		basvslmsg.setModifier(modifierText.getText());
//		basvslmsg.setModitime(new java.util.Date(moditimeText.getText()));
		
		basvslmsg.setNationcnam(nationcnamText.getText());
		basvslmsg.setEmail(emailText.getText());
		basvslmsg.setFaxno(faxnoText.getText());
		basvslmsg.setTel(telText.getText());
		basvslmsg.setMobile(mobileText.getText());
		basvslmsg.setTlx(tlxText.getText());
		basvslmsg.setLinename(linenameText.getText());
//		basvslmsg.setSecure_Certificate_Chday(new java.util.Date(secure_certificate_chdayText.getText()));
		
		basvslmsg.setSpeed(speedText.getText());
		basvslmsg.setHorsepower(horsepowerText.getText());
		basvslmsg.setBulknum(bulknumText.getText());
		basvslmsg.setPacknum(packnumText.getText());
		basvslmsg.setRemark(remarkText.getText());
		if(new LongValidator().isValid(sumteuText.getText()))
			basvslmsg.setSumteu(new Long(sumteuText.getText()));
		else
			basvslmsg.setSumteu(null);
		basvslmsg.setIsscclass(isscclassText.getText());
		if(new DoubleValidator().isValid(grosstonText.getText()))
			basvslmsg.setGrosston(new Double(grosstonText.getText()));
		else
			basvslmsg.setGrosston(null);
		basvslmsg.setSanitation(sanitationText.getText());
		basvslmsg.setPlagueno(plaguenoText.getText());
		basvslmsg.setBuilddate(builddateText.getText());
		basvslmsg.setFulldraft(fulldraftText.getText());
		basvslmsg.setTypename(typenameCombo.getText());
		
		basvslmsg.setHatchnum(hatchnumText.getText());
		basvslmsg.setLightdraft(lightdraftText.getText());
		basvslmsg.setIsscno(isscnoText.getText());
		
		if(onlineButton.getSelection()){
			basvslmsg.setOnline("1");
		}else{
			basvslmsg.setOnline("0");
		}
		
		if(selfagentButton.getSelection()){
			basvslmsg.setSelfagent("1");
		}else{
			basvslmsg.setSelfagent("0");
		}
		
		if(getFlag().equals("INSERT")){
			basvslmsg.setSequence((String) new UUIDGenerator().generate());
		}
			
		
//			onlineButton, selfagentButton
//		online		
//	    modicode;
//		mainkey;
//	      holdteu;
//	      deckteu;
//	      nationcode;
//	      nationenam;
//	      nationpcod;
//	      nationpnam;
//	      ownercode;
//	      ownername;
//	      ownertype;
//	      typecode;
//	      deptcode;
//	      deptname;
//	      selfagent;
//	      linecode;
//	      ;
		
		

	}
	void addCombodata(Combo combo){
		combo.add("��ɢ����");
		combo.setData("��ɢ����", "BD");
		combo.add("Һ��ɢ����ԭ�ͣ���");
		combo.setData("Һ��ɢ��(ԭ��)��", "BL");
		combo.add("��װ�䴬");
		combo.setData("��װ�䴬", "CT");
		combo.add("�ӻ���");
		combo.setData("�ӻ���", "GC");
		combo.add("��װ��");
		combo.setData("��װ��", "OR");
		combo.add("������");
		combo.setData("������", "OT");
		combo.add("���δ�");
		combo.setData("���δ�", "PS");
		combo.add("��֧��");
		combo.setData("��֧��", "CN");
		combo.add("������");
		combo.setData("������", "RS");
		combo.add("�ƿ���");
		combo.setData("�ƿ���", "SS");
		combo.add("����Ʒ��");
		combo.setData("����Ʒ��", "CS");
		combo.add("��Ʒ�ʹ�");
		combo.setData("��Ʒ�ʹ�", "CL");
		combo.add("ʵϰ��");
		combo.setData("ʵϰ��", "ES");
		combo.add("�ϲ���");
		combo.setData("�ϲ���", "DS");
		combo.add("���̴�");
		combo.setData("���̴�", "TS");
		combo.add("CAPESIZE��������");
		combo.setData("CAPESIZE��������", "HS");
		combo.add("PANAMAX��������");
		combo.setData("PANAMAX��������", "BS");	
		combo.add("HANDYSIZE�����");
		combo.setData("HANDYSIZE�����", "LS");
		combo.add("ԭ�ʹ�");
		combo.setData("ԭ�ʹ�", "YB");
		combo.add("��Ʒ�ʹ�");
		combo.setData("��Ʒ�ʹ�", "CB");
		combo.add("�˶�����");
		combo.setData("�˶�����", "CL");
		
	}
	
	public Text getShipcodeText() {
		return shipcodeText;
	}

	public Text getVslcodeText() {
		return vslcodeText;
	}

	public Text getEnnameText() {
		return ennameText;
	}

	public Text getCnnameText() {
		return cnnameText;
	}
	
	public Basvslmsg getBasvslmsg() {
		return basvslmsg;
	}

	public void setBasvslmsg(Basvslmsg basvslmsg) {
		this.basvslmsg = basvslmsg;
	}
	
	public void setValue(Basvslmsg basvslmsg){
		shipcodeText.setText(basvslmsg.getShipcode());
		vslcodeText.setText(basvslmsg.getVslcode());
		ennameText.setText(StringUtils.trimToEmpty(basvslmsg.getEnname()));
		cnnameText.setText(StringUtils.trimToEmpty(basvslmsg.getCnname()));
		lengthText.setText(StringUtils.trimToEmpty(basvslmsg.getLength()));
		widthText.setText(StringUtils.trimToEmpty(basvslmsg.getWidth()));
		heightText.setText(StringUtils.trimToEmpty(basvslmsg.getHeight()))		;
		nettonText.setText(StringUtils.trimToEmpty(basvslmsg.getNetton().toString()));
		grosstonText.setText(StringUtils.trimToEmpty(basvslmsg.getGrosston().toString()));
		loadtonText.setText(StringUtils.trimToEmpty(basvslmsg.getLoadton().toString()));
		
		makerText.setText(StringUtils.trimToEmpty(basvslmsg.getMaker()));
		if (basvslmsg.getMadetime() != null)
			madetimeText.setText(basvslmsg.getMadetime().toLocaleString());
		
		modifierText.setText(StringUtils.trimToEmpty(basvslmsg.getModifier()));
		if(basvslmsg.getModitime() != null)
			moditimeText.setText(basvslmsg.getModitime().toLocaleString());
		
		nationcnamText.setText(StringUtils.trimToEmpty(basvslmsg.getNationcnam()));
		emailText.setText(StringUtils.trimToEmpty(basvslmsg.getEmail()));
		faxnoText.setText(StringUtils.trimToEmpty(basvslmsg.getFaxno()));
		telText.setText(StringUtils.trimToEmpty(basvslmsg.getTel()));
		mobileText.setText(StringUtils.trimToEmpty(basvslmsg.getMobile()));
		tlxText.setText(StringUtils.trimToEmpty(basvslmsg.getTlx()));
		linenameText.setText(StringUtils.trimToEmpty(basvslmsg.getLinename()));

		speedText.setText(StringUtils.trimToEmpty(basvslmsg.getSpeed()));
		horsepowerText.setText(StringUtils.trimToEmpty(basvslmsg.getHorsepower()));
		bulknumText.setText(StringUtils.trimToEmpty(basvslmsg.getBulknum()));
		packnumText.setText(StringUtils.trimToEmpty(basvslmsg.getPacknum()));
		remarkText.setText(StringUtils.trimToEmpty(basvslmsg.getRemark()));
		sumteuText.setText(StringUtils.trimToEmpty(basvslmsg.getSumteu().toString()));
		isscclassText.setText(StringUtils.trimToEmpty(basvslmsg.getIsscclass()));
		grosstonText.setText(StringUtils.trimToEmpty(basvslmsg.getGrosston().toString()));

		sanitationText.setText(StringUtils.trimToEmpty(basvslmsg.getSanitation()));
		plaguenoText.setText(StringUtils.trimToEmpty(basvslmsg.getPlagueno()));
		builddateText.setText(StringUtils.trimToEmpty(basvslmsg.getBuilddate()));
		fulldraftText.setText(StringUtils.trimToEmpty(basvslmsg.getFulldraft()));
		typenameCombo.setText(StringUtils.trimToEmpty(basvslmsg.getTypename()));
		
		hatchnumText.setText(StringUtils.trimToEmpty(basvslmsg.getHatchnum()));
		lightdraftText.setText(StringUtils.trimToEmpty(basvslmsg.getLightdraft()));
		isscnoText.setText(StringUtils.trimToEmpty(basvslmsg.getIsscno()));
		
		if (basvslmsg.getOnline().equals("1")){
			onlineButton.setSelection(true);
		}
		if(basvslmsg.getSelfagent().equals("1")){
			selfagentButton.setSelection(true);
		}
		
	}
	
	public IUser getIuser(){
		Context ctx = Context.getInstance();
		return ctx.getCurrentUser();
	}

	
}
