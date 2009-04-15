package cosco.vsagent.app.views;
import org.eclipse.jface.action.IToolBarManager;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import com.swtdesigner.ResourceManager;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.SWT;
import cosco.vsagent.app.Activator;

public class OpenGLView extends ViewPart {

    GLCanvas canvas;
    @Override
    public void createPartControl(Composite parent) {
    	
        // TODO 自动生成方法存根
    	/*  GLData data = new GLData();
        data.depthSize = 1;
        data.doubleBuffer = true;
        canvas = new GLCanvas(parent, SWT.NO_BACKGROUND, data);
       canvas.addControlListener(new ControlAdapter() {
            public void controlResized(ControlEvent e) {
                Rectangle rect = canvas.getClientArea();
                GL.glViewport(0, 0, rect.width, rect.height);
                
                //选择投影矩阵
                GL.glMatrixMode(GL.GL_PROJECTION);
                //重置投影矩阵
                GL.glLoadIdentity();
                //设置窗口比例和透视图
                GLU.gluPerspective(45.0f, (float) rect.width / (float) rect.height, 0.1f, 100.0f);
                //选择模型观察矩阵
                GL.glMatrixMode(GL.GL_MODELVIEW);
                //重置模型观察矩阵
                GL.glLoadIdentity();
                
                //黑色背景
                GL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                //设置深度缓存
                GL.glClearDepth(1.0f);
                //启动深度测试
                GL.glEnable(GL.GL_DEPTH_TEST);
                //选择深度测试类型
                GL.glDepthFunc(GL.GL_LESS);
                //启用阴影平滑
                GL.glShadeModel(GL.GL_SMOOTH);
                //精细修正透视图
                GL.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
                //清除屏幕和深度缓存
                GL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
                //重置当前的模型观察矩阵
                GL.glLoadIdentity();
            }
        });  
        canvas.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e) {
                dispose();
            }
        });
*/        
        
        
        
        
        Refresher rf = new Refresher(canvas);
        rf.run();
        initializeToolBar();
    }

    @Override
    public void setFocus() {
        // TODO 自动生成方法存根

    }
	private void initializeToolBar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}

}

class Refresher implements Runnable {
    public static final int DELAY = 100;
    
    private GLCanvas canvas;
    private float rotate = 0.0f;
    
    public Refresher(GLCanvas canvas) {
        this.canvas = canvas;
    }
    
    public void run() {
        if (this.canvas != null && !this.canvas.isDisposed()) {
            if(!canvas.isCurrent()){
                canvas.setCurrent();
            }
            //这里添加OpenGL绘图代码
   
            //围绕y轴转起来
            rotate += 0.5;
            //调用递归函数，绘制三菱锥矩阵
            drawPyramid(0,0,0,4);
            canvas.swapBuffers();
            this.canvas.getDisplay().timerExec(DELAY, this);
        }
    }
        
        public void drawPyramid(float x, float y, float z, int n){
            if(n == 0)return;
            //画一个三菱锥
   
            //递归调用，画多个三菱锥
            drawPyramid(x,y-1.63f,z+1.15f,n-1);
            drawPyramid(x-1.0f,y-1.63f,z-0.57f,n-1);
            drawPyramid(x+1.0f,y-1.63f,z-0.57f,n-1);
        }
}