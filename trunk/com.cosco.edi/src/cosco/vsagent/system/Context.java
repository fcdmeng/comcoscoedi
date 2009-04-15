package cosco.vsagent.system;

import java.util.HashSet;
import java.util.Set;

import cosco.vsagent.mapping.base.Basvslvoy;
import cosco.vsagent.model.IUser;


public class Context {
	// 单例模式
	private static Context context = new Context();
	private Context() {}
	public static Context getInstance() {	return context;	}

	/** ******保存当前登录用户的对象********** */
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
	//判断当前系统是否有用户登录
	public boolean isLogon() {return currentUser != null;}

	/** ******事件监听的处理********** */
	// 监听器的注册表
	private Set<ILogonListener> logonListeners = new HashSet<ILogonListener>(); 
	// 注册监听器
	public void addLogonListener(ILogonListener listener) {	logonListeners.add(listener);}
	// 撤销监听器
	public void removeLogonListener(ILogonListener listener) {logonListeners.remove(listener);}
	// 触发所有监听器中的登录事件处理方法
	public void fireLogonEvent() {
//		for (ILogonListener listener : logonListeners)
//			listener.logon();
		for(ILogonListener listener : new HashSet<ILogonListener>(logonListeners))
			listener.logon();
	}
	// 触发所有监听器中的退出事件处理方法
	public void fireLogoffEvent() {
//		for (ILogonListener listener : logonListeners)
//			listener.logoff();
		for(ILogonListener listener : new HashSet<ILogonListener>(logonListeners))
			listener.logoff();
		currentUser = null;// 当前登录用户设为空
	}

}
