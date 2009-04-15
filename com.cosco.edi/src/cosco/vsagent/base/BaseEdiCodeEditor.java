package cosco.vsagent.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import cosco.vsagent.system.EditorPartAdapter;

public class BaseEdiCodeEditor extends EditorPartAdapter {
	
	TableViewer tb;
	
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		SashForm sashForm = new SashForm(parent, SWT.NONE);
		TreeViewer tv = new TreeViewer(sashForm, SWT.BORDER);
	
		tv.setContentProvider(new TreeViewerContentProvider());
		tv.setLabelProvider(new TreeViewerLableProvider());
		
		List<CountryEntity> input = new DataFactory().createTreeData();
		tv.setInput(input);
		
		tb = new TableViewer(sashForm, SWT.MULTI|SWT.BORDER);
		
		Table table = tb.getTable();
		
		sashForm.setWeights(new int[]{1,3});
		
	}

	public interface ITreeEntry<T> {//接口名称一般以大写的I开头，第二个字母也大写    
		  public String getName();//设置树结点的名称
		  public void setName(String name); //得到树结点的名称
		  public void setChildren(List<T> children); //设置子结点集合
		  public List<T> getChildren();//得到子结点集合
	}
	public class TreeViewerContentProvider implements ITreeContentProvider {
		// 由此方法决定树的“第一级”结点显示哪些对象。inputElement是用tv.setInput()方法输入的那个对象。
		// Object[]一个数组，数组中一个元素就是一个结点
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				List input = (List) inputElement;
				return input.toArray();
			}
			return new Object[0]; // 空数组
		}

		// 判断参数element结点是否有子结点。
		// 返回true表示element有子结点，则其前面会显示有“＋”号图标。
		public boolean hasChildren(Object element) {
			ITreeEntry entry = (ITreeEntry) element;
			List list = entry.getChildren();
			return !(list == null || list.isEmpty()); // 判断list是否有子
		}

		// 当界面中单击某结点时，由此方法决定被单击结点应该显示哪些子结点。
		// parentElement就是被单击的结点对象。返回的数组就是应显示的子结点
		public Object[] getChildren(Object parentElement) {
			ITreeEntry entry = (ITreeEntry) parentElement;
			List list = entry.getChildren();
			// 虽然通过界面单击方式，有子的结点才会执行到此方法，但仍然要做非空判断，因为在调用TreeViewer的某些方法时其内部会附带调用此方法。
			if (list == null)
				return new Object[0];
			return list.toArray();
		}

		// --------------以下方法无用，空实现----------------
		public Object getParent(Object element) {
			return null;
		}

		public void dispose() {}

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {}
	}
	public class TreeViewerLableProvider implements ILabelProvider {
		// 结点显示的文字。不能返回null值
		public String getText(Object element) {
			ITreeEntry entry = (ITreeEntry) element;
			return entry.getName();
		}

		// 结点显示的图像，可以返回null值
		public Image getImage(Object element) {
			return null;
		}

		// --------以下方法暂不用，空实现----------
		public void addListener(ILabelProviderListener listener) {}
		public void removeListener(ILabelProviderListener listener) {}
		public void dispose() {}
		public boolean isLabelProperty(Object e, String p) {	return false;	}
	}
	
	public class PeopleEntity implements ITreeEntry{
		private Long id; //惟一识别码，在数据库里常为自动递增的ID列
		private String name; //姓名
		private boolean sex; //性别 true男，flase女
		private int age; //年龄
		private Date createDate; //记录的建立日期。Date类型是java.util.Date，而不是java.sql.Date
		//构造函数
		public PeopleEntity() {}
		public PeopleEntity(String name) { this.name = name;  }
		//以下代码为字段各自的Setter/Geter方法。参考第3.5.2节，这些方法在Eclipse可自动生成。
		public Long getId() { return id;}
		public void setId(Long long1) {id = long1;}
		public String getName() {return name;}
		public void setName(String string) {name = string;}
		public boolean isSex() { return sex;}
		public void setSex(boolean sex) { this.sex = sex; }
		public int getAge() {return age;}
		public void setAge(int i) {age = i;}
		public Date getCreateDate() {return createDate;}
		public void setCreateDate(Date date) {createDate = date;}
		public List getChildren() {	return null;}
		public void setChildren(List children) {}
	}

	//国家的实体类
	public class CountryEntity implements ITreeEntry<CityEntity> {
	    private Long id; //惟一识别码，在数据库里常为自动递增的ID列
	    private String name; //国家名
	    private List<CityEntity> cities = new ArrayList<CityEntity>(); //此国家所包含的城市的集合
		//---------两个构造函数---------
	    public CountryEntity() {}
	    public CountryEntity(String name) { this.name = name; }
	    //---------字段相应的Getter/Setter方法---------
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }
	    public void setChildren(List<CityEntity> children) {this.cities = children;}
	    public List<CityEntity> getChildren() {return cities;}
	}
	//城市的实体类
	public class CityEntity  implements ITreeEntry<PeopleEntity>{
	    private Long id; //惟一识别码，在数据库里常为自动递增的ID列
	    private String name;//城市名
	    private List<PeopleEntity> peoples = new ArrayList<PeopleEntity>();//城市中的人
	    //---------两个构造函数---------
	    public CityEntity() {}
	    public CityEntity(String name) {this.name = name;}
	    //---------字段相应的Getter/Setter方法---------
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getName() { return name;}
	    public void setName(String name) {this.name = name;}
	    public void setChildren(List<PeopleEntity> children) {this.peoples = children;}
	    public List<PeopleEntity> getChildren() {return peoples;}
	}

	public class DataFactory {
		public List<CountryEntity> createTreeData() {
			// 生成国家的数据对象
			CountryEntity cn = new CountryEntity("中国");
			CountryEntity us = new CountryEntity("美国");
			// 生成城市的数据对象
			CityEntity city1 = new CityEntity("北京");
			CityEntity city2 = new CityEntity("台湾");
			CityEntity city3 = new CityEntity("桂林");
			CityEntity city4 = new CityEntity("芝加哥");
			CityEntity city5 = new CityEntity("纽约");
			{// ----------往城市加人---------------------
				// 北京
				ArrayList<PeopleEntity> list = new ArrayList<PeopleEntity>();
				list.add(new PeopleEntity("陈刚"));
				list.add(new PeopleEntity("陈知行"));
				list.add(new PeopleEntity("韩立新"));
				city1.setChildren(list);
				// 台湾
				list = new ArrayList<PeopleEntity>();
				list.add(new PeopleEntity("桃子"));
				list.add(new PeopleEntity("林雅仕"));
				list.add(new PeopleEntity("陈常恩"));
				city2.setChildren(list);
				// 纽约
				list = new ArrayList<PeopleEntity>();
				list.add(new PeopleEntity("Giles"));
				list.add(new PeopleEntity("Tom"));
				list.add(new PeopleEntity("Rose"));
				city5.setChildren(list);
			}
			{// ---------城市和国家的关系------------------
				// 北京、台湾、桂林属于中国
				ArrayList<CityEntity> list = new ArrayList<CityEntity>();
				list.add(city1);
				list.add(city2);
				list.add(city3);
				cn.setChildren(list);
				// 芝加哥、纽约属于美国
				list = new ArrayList<CityEntity>();
				list.add(city4);
				list.add(city5);
				us.setChildren(list);
			}
			{// 将所有国家放于一个集合中，也可以放到一个数组中
				ArrayList<CountryEntity> list = new ArrayList<CountryEntity>();
				list.add(cn);
				list.add(us);
				return list;
			}
		}
	}

}






