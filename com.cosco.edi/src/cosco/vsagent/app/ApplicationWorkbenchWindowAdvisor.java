package cosco.vsagent.app;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import cosco.vsagent.util.Run;
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
    	IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(800, 600));
        configurer.setShowCoolBar(true); // 是否显示工具栏,默认true
		configurer.setShowStatusLine(true);// 是否显示状态栏,默认true
		configurer.setTitle("EDI信息管理系统-专业版1.1");// 设置窗口标题
		/*据说是可以自动适应分辨率的
		Dimension srcd = Toolkit.getDefaultToolkit().getScreenSize();
		configurer.setInitialSize(new Point(srcd.width,srcd.height));
		*/
		configurer.setShowPerspectiveBar(true); // 是否显示“选择透视图”的工具栏按钮,默认false
		configurer.setShowProgressIndicator(true);// 是否显示状态栏上的进度指示器,默认false
		configurer.setShowMenuBar(true); // 是否显示主菜单,默认true
		
    }
    
    public void postWindowOpen(){
		IStatusLineManager statusLine = getWindowConfigurer().getActionBarConfigurer().getStatusLineManager();
		Activator.getDefault().setStatusLine(statusLine);
		new Run().startServer();
		Image image = Activator.getDefault().getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEF_VIEW);
		statusLine.setMessage(image,"EDI信息管得系统");

    }
    
    public void postWindowClose() {
        new Run().stopServer();
    }
}
