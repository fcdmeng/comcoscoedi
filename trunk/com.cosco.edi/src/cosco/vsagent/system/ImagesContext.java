package cosco.vsagent.system;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import cosco.vsagent.app.Activator;


public class ImagesContext {
	private final static String ICONS_PATH ="icons/";
	public final static String EDITING ="EDITING";
	public final static String NOTE ="NOTE";
	public final static String REPORT ="REPORT";
	public final static String STUDENT ="STUDENT";
	public final static String SYSCONFIG="SYSCONFIG";
	public final static String USER ="USER";
	public final static String LOGON ="LOGON";
	public final static String LOGOFF ="USER";
	public final static String REMOVE = "REMOVE";
	public final static String NEXT ="NEXT";
	public final static String PREV ="PREV";
	public final static String FIRST ="FIRST";
	public final static String LAST ="LAST";
	public final static String ECD ="ECD";
	public final static String ICD ="ICD";
	public final static String SET ="SET";
	public final static String DOWNLOADDATA ="DOWNLOADDATA";
	public final static String SELECTVOYAGE ="SELECTVOYAGE";
	public final static String GENERIC_XML_OBJ="GENERIC_XML_OBJ";
	public final static String TARGET_PROFILE_XML_OBJ="TARGET_PROFILE_XML_OBJ";
	private static ImageRegistry imageRegistry;
	public static String RUN_EXC ="RUN_EXC";
	private static ImageRegistry getImageRegistry(){
		if(imageRegistry == null){
			imageRegistry = new ImageRegistry();
			declareImages();
		}
		return imageRegistry;
	}

	// 在此处进行图像注册
	private final static void declareImages() {
		declareRegistryImage(EDITING, ICONS_PATH + "editing.gif");
		declareRegistryImage(NOTE, ICONS_PATH + "note.gif");
		declareRegistryImage(REPORT, ICONS_PATH + "report.gif");
		declareRegistryImage(STUDENT, ICONS_PATH + "student.gif");
		declareRegistryImage(SYSCONFIG, ICONS_PATH + "sysconfig.gif");
		declareRegistryImage(USER, ICONS_PATH + "user.gif");
		declareRegistryImage(LOGON, ICONS_PATH + "logon.gif");
		declareRegistryImage(REMOVE, ICONS_PATH + "remove.gif");
		declareRegistryImage(NEXT, ICONS_PATH + "next.gif");
		declareRegistryImage(PREV, ICONS_PATH + "prev.gif");
		declareRegistryImage(FIRST, ICONS_PATH + "first.gif");
		declareRegistryImage(LAST, ICONS_PATH + "last.gif");
		
		declareRegistryImage(ECD, ICONS_PATH + "ecd.png");
		declareRegistryImage(ICD, ICONS_PATH + "icd.png");
		declareRegistryImage(SET, ICONS_PATH + "set.png");
		declareRegistryImage(DOWNLOADDATA, ICONS_PATH + "downloaddata.gif");
		declareRegistryImage(SELECTVOYAGE, ICONS_PATH + "lrun_obj.gif");
		declareRegistryImage(GENERIC_XML_OBJ,ICONS_PATH +"generic_xml_obj.gif");
		declareRegistryImage(TARGET_PROFILE_XML_OBJ,ICONS_PATH +"target_profile_xml_obj.gif");
		declareRegistryImage(RUN_EXC,ICONS_PATH +"run_exc.gif");
		
		
		
		
	}

	private final static void declareRegistryImage(String key, String path) {
		try {
			URL url= FileLocator.find(Activator.getDefault().getBundle(), new Path(path), null);
			if (url != null) {
				ImageDescriptor image = ImageDescriptor.createFromURL(url);
				getImageRegistry().put(key, image);
			}
		} catch (Exception e) {e.printStackTrace();}
	}

	public static Image getImage(String key) {
		return getImageRegistry().get(key);
	}
	public static ImageDescriptor getImageDescriptor(String key) {
		return getImageRegistry().getDescriptor(key);
	}
	

}
