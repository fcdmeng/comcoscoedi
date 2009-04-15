package cosco.vsagent.system;

import java.util.HashSet;
import java.util.Set;

import cosco.vsagent.mapping.base.Basvslvoy;
import cosco.vsagent.model.IUser;


public class Context {
	// ����ģʽ
	private static Context context = new Context();
	private Context() {}
	public static Context getInstance() {	return context;	}

	/** ******���浱ǰ��¼�û��Ķ���********** */
	private IUser currentUser = null;
	public IUser getCurrentUser() {return currentUser;	}
	public void setCurrentUser(IUser currentUser) {this.currentUser = currentUser;}
	private Basvslvoy basvslvoy = null;
	
	public Basvslvoy getBasvslvoy() {
		return basvslvoy;
	}
	public void setBasvslvoy(Basvslvoy basvslvoy) {
		this.basvslvoy = basvslvoy;
	}
	//�жϵ�ǰϵͳ�Ƿ����û���¼
	public boolean isLogon() {return currentUser != null;}

	/** ******�¼������Ĵ���********** */
	// ��������ע���
	private Set<ILogonListener> logonListeners = new HashSet<ILogonListener>(); 
	// ע�������
	public void addLogonListener(ILogonListener listener) {	logonListeners.add(listener);}
	// ����������
	public void removeLogonListener(ILogonListener listener) {logonListeners.remove(listener);}
	// �������м������еĵ�¼�¼�������
	public void fireLogonEvent() {
//		for (ILogonListener listener : logonListeners)
//			listener.logon();
		for(ILogonListener listener : new HashSet<ILogonListener>(logonListeners))
			listener.logon();
	}
	// �������м������е��˳��¼�������
	public void fireLogoffEvent() {
//		for (ILogonListener listener : logonListeners)
//			listener.logoff();
		for(ILogonListener listener : new HashSet<ILogonListener>(logonListeners))
			listener.logoff();
		currentUser = null;// ��ǰ��¼�û���Ϊ��
	}

}
