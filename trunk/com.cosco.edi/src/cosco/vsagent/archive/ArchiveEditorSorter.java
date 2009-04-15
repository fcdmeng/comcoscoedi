package cosco.vsagent.archive;

import java.util.Date;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import cosco.vsagent.model.IUser;
import cosco.vsagent.model.SchoolClass;
import cosco.vsagent.model.Student;


public class ArchiveEditorSorter extends ViewerSorter {
	

	public static final int ID =1;
	public static final int USERID = 2;
	public static final int LATEST_ONLINE =3;
	public static final int SCHOOLCLASS =4;
	private int sortType;
	public static final ViewerSorter ID_ASC = new ArchiveEditorSorter(ID);
	public static final ViewerSorter ID_DESC = new ArchiveEditorSorter(-ID);
	
	public static final ViewerSorter USERID_ASC = new ArchiveEditorSorter(USERID);
	public static final ViewerSorter USERID_DESC = new ArchiveEditorSorter(-USERID);
	public static final ViewerSorter LATESTONLINE_ASC = new ArchiveEditorSorter(LATEST_ONLINE);
	public static final ViewerSorter LATESTONLINE_DESC = new ArchiveEditorSorter(-LATEST_ONLINE);
	
	public static final ViewerSorter SCHOOLCLASS_ASC = new ArchiveEditorSorter(SCHOOLCLASS);
	public static final ViewerSorter SCHOOLCLASS_DESC = new ArchiveEditorSorter(-SCHOOLCLASS);
	
	public ArchiveEditorSorter(int sortType) {
		// TODO Auto-generated constructor stub
		this.sortType = sortType;
	}
	
	public int compare(Viewer viewer, Object obj1, Object obj2){
		IUser o1 = (IUser) obj1;
		IUser o2 = (IUser) obj2;
		
		switch(sortType){
		case ID: return o1.getId().compareTo(o2.getId());
		case -ID:return o2.getId().compareTo(o1.getId());
		case USERID: return o1.getUserId().compareTo(o2.getUserId());
		case -USERID: return o2.getUserId().compareTo(o1.getUserId());
		case LATEST_ONLINE:return compareLatestOnline(o1, o2);
		case -LATEST_ONLINE:return compareLatestOnline(o2, o1);
		case SCHOOLCLASS:return compareSchoolClass(o1, o2);
		case -SCHOOLCLASS:return compareSchoolClass(o2, o1);
		}
		return 0;
	}

	private int compareSchoolClass(IUser o1, IUser o2) {
		// TODO Auto-generated method stub
		Long l1 = new Long(-1);
		Long l2 = new Long(-1);
		
		if(o1 instanceof Student){
			SchoolClass class1 = ((Student)o1).getSchoolclass();
			l1 = (class1 == null?new Long(0):class1.getId());
		}
		
		if(o2 instanceof Student){
			SchoolClass class2 = ((Student)o2).getSchoolclass();
			l1 = (class2 == null?new Long(0):class2.getId());
		}
		
		return l1.compareTo(l2);
	}

	private int compareLatestOnline(IUser o1, IUser o2) {
		// TODO Auto-generated method stub
		Date d1 = o1.getLatestOnline();
		Date d2 = o2.getLatestOnline();
		
		if(d1 == null) d1 = new Date(1);
		if(d2 == null) d2 = new Date(1);
		return d1.compareTo(d2);
	}

}
