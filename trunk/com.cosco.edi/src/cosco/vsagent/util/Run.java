package cosco.vsagent.util;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
             Runtime run = Runtime.getRuntime();
             Process p = run.exec("D:/workspace/RCP/ProCosco/asa/dbsrv8.exe   -c 8m -n vss D:/workspace/RCP/ProCosco/asa/db/vss.db");//启动另一个进程来执行命令
             /*BufferedInputStream in = new BufferedInputStream(p.getInputStream());
             BufferedInputStream err = new BufferedInputStream(p.getErrorStream());
             BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
             BufferedReader errBr = new BufferedReader(new InputStreamReader(err));
             String lineStr;
             while ((lineStr = errBr.readLine()) != null)
                 System.out.println(lineStr);
             while ((lineStr = inBr.readLine()) != null)
                 System.out.println(lineStr);
             //检查命令是否执行失败。
             try {
                 if (p.waitFor()!=0) {
                     if(p.exitValue()==1)//p.exitValue()==0表示正常结束，1：非正常结束
                         System.err.println("命令执行失败!");
                 }
             }catch (InterruptedException e){
                 e.printStackTrace();
             }*/
         } catch (Exception e) {
             e.printStackTrace();
         }

	}
	
	public void startServer(){
		/*
//		String workspace_path = Platform.getInstanceLocation().getURL().getPath();
//		System.out.println(workspace_path);
		
		Runtime run = Runtime.getRuntime();
		try {
			run.exec("D:/lvxz/ProCosco/asa/dbeng8.exe -c 8m -n vss D:/workspace/RCP/ProCosco/asa/db/vss.db");
//			run.exec("D:/workspace/RCP/ProCosco/asa/dbeng8.exe -Q -c 8m -n vss D:/workspace/RCP/ProCosco/asa/db/vss.db");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//启动另一个进程来执行命令
		*/
		
	}
	
	public void stopServer(){
		/*
		Runtime run = Runtime.getRuntime();
		try {
			//dbstop.exe   -c   "uid=dba;pwd=sql;eng=db_zjgl;"
			//D:\lvxz\ProCosco\asa
			run.exec("D:/lvxz/ProCosco/asa/dbstop.exe   -c   uid=dba;pwd=sql;eng=vss;");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//启动另一个进程来执行命令
		*/
	}

}
