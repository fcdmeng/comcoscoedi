package cosco.xml;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cosco.vsagent.system.Context;

public class test {
	private static File testDir;
	 static String OperationType="SEND";//���ͻ��ǽ���
	 static String SystemId="ICD";//ϵͳ��ʾ
	 static String BorderTransportMeans="AAA";
	 static String JourneyID="BBB";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		getTestDirectory();
//		tt();
		/*Calendar   calendar   =   new   GregorianCalendar();  
		Date   trialTime   =   new   Date();  
		calendar.setTime(trialTime); 
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH) + 1);
		System.out.println("YEAR:   "   +   calendar.get(Calendar.YEAR));   
		System.out.println("YEAR:   "   +   calendar.get(Calendar.MONTH)); 
		
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM");
		String a1=new SimpleDateFormat("yyyy��MM").format(new Date());
		System.out.println(a1);*/
//		System.out.println(EdiPath());
//		System.out.println(System.getProperty("file.separator"));
		String temp = System.getProperty ("line.separator", "\n");
		System.out.println(temp);
//		System.out.println(System.getProperty ("line.separator","xxx"));
	}
	
	public static String EdiPath(){
		String path = "c:/EDI";
		
		String month = new SimpleDateFormat("yyyy-MM").format(new Date());
		path = path + "/"+ month;//�·�
		path = path + "/"+SystemId;//ϵͳ�ɣģ����ڻ��ǳ���
		path = path + "/"+OperationType;//���ͻ��ǽ���
		path = path + "/"+(Context.getInstance().getCurrentUser()==null?"�����û�":"XXXX");//��ǰ�û�
		path = path +"/"+ BorderTransportMeans+"��"+JourneyID;//�����Ӻ���
		
		File dir = (new File(path)).getAbsoluteFile();
		if( dir.exists()== false ){
			if (dir.mkdirs() == false ) return "����Ŀ¼ʧ��";
		}else{
			System.out.println("**************");
		}
		return path;
	}
	
	public static File getTestDirectory() {
       /* if (testDir == null) {
            testDir = (new File("c:/t2t/io/")).getAbsoluteFile();
        }
        testDir.mkdirs();*/
        return testDir;
    }
	public static void tt(){
		/*File f = new File("c:/home/girish/Desktop/g.txt");
		testDir = (f).getAbsoluteFile();
        if (f.exists()) {
            System.out.println("Pathname of the File is: " + f.getAbsoluteFile());

        } else {
            System.out.println("File does not exists");
        }*/
	}

}
