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
    	
        // TODO �Զ����ɷ������
    	/*  GLData data = new GLData();
        data.depthSize = 1;
        data.doubleBuffer = true;
        canvas = new GLCanvas(parent, SWT.NO_BACKGROUND, data);
       canvas.addControlListener(new ControlAdapter() {
            public void controlResized(ControlEvent e) {
                Rectangle rect = canvas.getClientArea();
                GL.glViewport(0, 0, rect.width, rect.height);
                
                //ѡ��ͶӰ����
                GL.glMatrixMode(GL.GL_PROJECTION);
                //����ͶӰ����
                GL.glLoadIdentity();
                //���ô��ڱ�����͸��ͼ
                GLU.gluPerspective(45.0f, (float) rect.width / (float) rect.height, 0.1f, 100.0f);
                //ѡ��ģ�͹۲����
                GL.glMatrixMode(GL.GL_MODELVIEW);
                //����ģ�͹۲����
                GL.glLoadIdentity();
                
                //��ɫ����
                GL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                //������Ȼ���
                GL.glClearDepth(1.0f);
                //������Ȳ���
                GL.glEnable(GL.GL_DEPTH_TEST);
                //ѡ����Ȳ�������
                GL.glDepthFunc(GL.GL_LESS);
                //������Ӱƽ��
                GL.glShadeModel(GL.GL_SMOOTH);
                //��ϸ����͸��ͼ
                GL.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
                //�����Ļ����Ȼ���
                GL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
                //���õ�ǰ��ģ�͹۲����
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
        // TODO �Զ����ɷ������

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
            //�������OpenGL��ͼ����
   
            //Χ��y��ת����
            rotate += 0.5;
            //���õݹ麯������������׶����
            drawPyramid(0,0,0,4);
            canvas.swapBuffers();
            this.canvas.getDisplay().timerExec(DELAY, this);
        }
    }
        
        public void drawPyramid(float x, float y, float z, int n){
            if(n == 0)return;
            //��һ������׶
   
            //�ݹ���ã����������׶
            drawPyramid(x,y-1.63f,z+1.15f,n-1);
            drawPyramid(x-1.0f,y-1.63f,z-0.57f,n-1);
            drawPyramid(x+1.0f,y-1.63f,z-0.57f,n-1);
        }
}