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

	public interface ITreeEntry<T> {//�ӿ�����һ���Դ�д��I��ͷ���ڶ�����ĸҲ��д    
		  public String getName();//��������������
		  public void setName(String name); //�õ�����������
		  public void setChildren(List<T> children); //�����ӽ�㼯��
		  public List<T> getChildren();//�õ��ӽ�㼯��
	}
	public class TreeViewerContentProvider implements ITreeContentProvider {
		// �ɴ˷����������ġ���һ���������ʾ��Щ����inputElement����tv.setInput()����������Ǹ�����
		// Object[]һ�����飬������һ��Ԫ�ؾ���һ�����
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				List input = (List) inputElement;
				return input.toArray();
			}
			return new Object[0]; // ������
		}

		// �жϲ���element����Ƿ����ӽ�㡣
		// ����true��ʾelement���ӽ�㣬����ǰ�����ʾ�С�������ͼ�ꡣ
		public boolean hasChildren(Object element) {
			ITreeEntry entry = (ITreeEntry) element;
			List list = entry.getChildren();
			return !(list == null || list.isEmpty()); // �ж�list�Ƿ�����
		}

		// �������е���ĳ���ʱ���ɴ˷����������������Ӧ����ʾ��Щ�ӽ�㡣
		// parentElement���Ǳ������Ľ����󡣷��ص��������Ӧ��ʾ���ӽ��
		public Object[] getChildren(Object parentElement) {
			ITreeEntry entry = (ITreeEntry) parentElement;
			List list = entry.getChildren();
			// ��Ȼͨ�����浥����ʽ�����ӵĽ��Ż�ִ�е��˷���������ȻҪ���ǿ��жϣ���Ϊ�ڵ���TreeViewer��ĳЩ����ʱ���ڲ��ḽ�����ô˷�����
			if (list == null)
				return new Object[0];
			return list.toArray();
		}

		// --------------���·������ã���ʵ��----------------
		public Object getParent(Object element) {
			return null;
		}

		public void dispose() {}

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {}
	}
	public class TreeViewerLableProvider implements ILabelProvider {
		// �����ʾ�����֡����ܷ���nullֵ
		public String getText(Object element) {
			ITreeEntry entry = (ITreeEntry) element;
			return entry.getName();
		}

		// �����ʾ��ͼ�񣬿��Է���nullֵ
		public Image getImage(Object element) {
			return null;
		}

		// --------���·����ݲ��ã���ʵ��----------
		public void addListener(ILabelProviderListener listener) {}
		public void removeListener(ILabelProviderListener listener) {}
		public void dispose() {}
		public boolean isLabelProperty(Object e, String p) {	return false;	}
	}
	
	public class PeopleEntity implements ITreeEntry{
		private Long id; //Ωһʶ���룬�����ݿ��ﳣΪ�Զ�������ID��
		private String name; //����
		private boolean sex; //�Ա� true�У�flaseŮ
		private int age; //����
		private Date createDate; //��¼�Ľ������ڡ�Date������java.util.Date��������java.sql.Date
		//���캯��
		public PeopleEntity() {}
		public PeopleEntity(String name) { this.name = name;  }
		//���´���Ϊ�ֶθ��Ե�Setter/Geter�������ο���3.5.2�ڣ���Щ������Eclipse���Զ����ɡ�
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

	//���ҵ�ʵ����
	public class CountryEntity implements ITreeEntry<CityEntity> {
	    private Long id; //Ωһʶ���룬�����ݿ��ﳣΪ�Զ�������ID��
	    private String name; //������
	    private List<CityEntity> cities = new ArrayList<CityEntity>(); //�˹����������ĳ��еļ���
		//---------�������캯��---------
	    public CountryEntity() {}
	    public CountryEntity(String name) { this.name = name; }
	    //---------�ֶ���Ӧ��Getter/Setter����---------
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }
	    public void setChildren(List<CityEntity> children) {this.cities = children;}
	    public List<CityEntity> getChildren() {return cities;}
	}
	//���е�ʵ����
	public class CityEntity  implements ITreeEntry<PeopleEntity>{
	    private Long id; //Ωһʶ���룬�����ݿ��ﳣΪ�Զ�������ID��
	    private String name;//������
	    private List<PeopleEntity> peoples = new ArrayList<PeopleEntity>();//�����е���
	    //---------�������캯��---------
	    public CityEntity() {}
	    public CityEntity(String name) {this.name = name;}
	    //---------�ֶ���Ӧ��Getter/Setter����---------
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getName() { return name;}
	    public void setName(String name) {this.name = name;}
	    public void setChildren(List<PeopleEntity> children) {this.peoples = children;}
	    public List<PeopleEntity> getChildren() {return peoples;}
	}

	public class DataFactory {
		public List<CountryEntity> createTreeData() {
			// ���ɹ��ҵ����ݶ���
			CountryEntity cn = new CountryEntity("�й�");
			CountryEntity us = new CountryEntity("����");
			// ���ɳ��е����ݶ���
			CityEntity city1 = new CityEntity("����");
			CityEntity city2 = new CityEntity("̨��");
			CityEntity city3 = new CityEntity("����");
			CityEntity city4 = new CityEntity("֥�Ӹ�");
			CityEntity city5 = new CityEntity("ŦԼ");
			{// ----------�����м���---------------------
				// ����
				ArrayList<PeopleEntity> list = new ArrayList<PeopleEntity>();
				list.add(new PeopleEntity("�¸�"));
				list.add(new PeopleEntity("��֪��"));
				list.add(new PeopleEntity("������"));
				city1.setChildren(list);
				// ̨��
				list = new ArrayList<PeopleEntity>();
				list.add(new PeopleEntity("����"));
				list.add(new PeopleEntity("������"));
				list.add(new PeopleEntity("�³���"));
				city2.setChildren(list);
				// ŦԼ
				list = new ArrayList<PeopleEntity>();
				list.add(new PeopleEntity("Giles"));
				list.add(new PeopleEntity("Tom"));
				list.add(new PeopleEntity("Rose"));
				city5.setChildren(list);
			}
			{// ---------���к͹��ҵĹ�ϵ------------------
				// ������̨�塢���������й�
				ArrayList<CityEntity> list = new ArrayList<CityEntity>();
				list.add(city1);
				list.add(city2);
				list.add(city3);
				cn.setChildren(list);
				// ֥�Ӹ硢ŦԼ��������
				list = new ArrayList<CityEntity>();
				list.add(city4);
				list.add(city5);
				us.setChildren(list);
			}
			{// �����й��ҷ���һ�������У�Ҳ���Էŵ�һ��������
				ArrayList<CountryEntity> list = new ArrayList<CountryEntity>();
				list.add(cn);
				list.add(us);
				return list;
			}
		}
	}

}






