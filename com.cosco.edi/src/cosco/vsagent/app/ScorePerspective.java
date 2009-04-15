package cosco.vsagent.app;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;
import org.eclipse.ui.console.IConsoleConstants;



public class ScorePerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) 
	{
		
		String editorArea = layout.getEditorArea();
		//layout.setEditorAreaVisible(false);
		// 加入主功能导航器视图
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.3f, editorArea);
		left.addView("cosco.vsagent.navigator.NavigatorView");
		
		layout.getViewLayout("cosco.vsagent.navigator.NavigatorView").setCloseable(false);
		// 加入搜索视图
//		IFolderLayout bottom1 = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.5f, "left");
//		bottom1.addView("cosco.vsagent.score.SearchView");
		
		//加入控制台视图
		IFolderLayout bottom2 = layout.createFolder("bottom2", IPageLayout.BOTTOM, 0.7f, editorArea);
		bottom2.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		bottom2.addView("cosco.vsagent.app.views.WelcomView");
		
		
		
		
	}
}
