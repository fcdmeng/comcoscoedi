package cosco.vsagent.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import cosco.vsagent.db.DbOperate;
import cosco.vsagent.dbo.base.BascountryDAO;
import cosco.vsagent.mapping.base.Bascountry;
import cosco.vsagent.model.Course;
import cosco.vsagent.model.SchoolClass;


public class SmsUtil {
	public static Combo createSchoolClassCombo(Composite parent, int style)
	{
		Combo combo = new Combo(parent, style);
		DbOperate db = SmsFactory.getDbOperate();
		for(SchoolClass sc : db.getAllSchoolClass()){
			String name = sc.getName();
			combo.add(name);
			combo.setData(name,sc);
		}
		return combo;
	}
	
	public static Combo createCourseCombo(Composite parent, int style){
		Combo combo = new Combo(parent, style);
		DbOperate db = SmsFactory.getDbOperate();
		for (Course course : db.getCourses()){
			String name = course.getName();
			combo.add(name);
			combo.setData(name, course);
		}
		return combo;
	}
	
	public static String dateToLongStr(Date date){
		if(date == null) return "";
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	public static String dateToShortStr(Date date){
		if(date == null) return "";
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public static Text createText(Composite parent, int style)
	{
		Text text = new Text(parent, style);
		text.addVerifyListener(
				new VerifyListener(){
				    public void verifyText(VerifyEvent e) {
				        e.text=e.text.toUpperCase();
				  }
				}
				);
		return text;
	}
	
	public static Combo createNationCombo(Composite parent, int style){
		Combo combo = new Combo(parent, style);
		for( Bascountry bascountry : new BascountryDAO().getAllBascountry()){
			String name= bascountry.getCname();
			combo.add(name);
			combo.setData(name, bascountry);
		}
		return combo;
	}
	
	public String ietype(String ietype, boolean flag){
		if(flag == true){
			if(ietype.equals("1")) return "本港进出口";
			if(ietype.equals("2")) return "内支线";
			if(ietype.equals("3")) return "国际中转";
			if(ietype.equals("4")) return "过境";
			if(ietype.equals("5")) return "调拨空箱";
			if(ietype.equals("6")) return "通运";
			if(ietype.equals("7")) return "海铁联运";
			if(ietype.equals("8")) return "直通";
			
		}
		if(flag == false){
			if(ietype.equals("本港进出口")) return "1";
			if(ietype.equals("内支线")) return "2";
			if(ietype.equals("国际中转")) return "3";
			if(ietype.equals("过境")) return "4";
			if(ietype.equals("调拨空箱")) return "5";
			if(ietype.equals("通运")) return "6";
			if(ietype.equals("海铁联运")) return "7";
			if(ietype.equals("直通")) return "8";
			
		}
	
		return "ietype";
	}
	
	public String bktype(String bktype, boolean flag){
		if(flag == true){
			if (bktype.equals("1")) return "整箱";
			if (bktype.equals("2")) return "主票";
			if (bktype.equals("3")) return "分票";

		}else{
			if(bktype.equals("整箱")) return "1";
			if(bktype.equals("拼箱主票")) return "2";
			if(bktype.equals("拼箱分票")) return "3";
		}
		return "bktype";
	}

}
