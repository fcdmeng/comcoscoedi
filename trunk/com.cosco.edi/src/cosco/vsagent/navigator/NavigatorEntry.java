package cosco.vsagent.navigator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;

import cosco.vsagent.model.ITreeEntry;


public class NavigatorEntry implements ITreeEntry {
	private String name;
	private ITreeEntry parentEntry;
	private List<ITreeEntry> children = new ArrayList<ITreeEntry>();
	private Image image;
	private IEditorInput editorInput;
	private String editorId;
	public IEditorInput getEditorInput() {
		return editorInput;
	}
	public void setEditorInput(IEditorInput editorInput) {
		this.editorInput = editorInput;
	}
	public String getEditorId() {
		return editorId;
	}
	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}
	public NavigatorEntry(){};
	public NavigatorEntry(String name){
		this.name = name;
	}
	
	

	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public void addChild(ITreeEntry entry) {
		// TODO Auto-generated method stub
		children.add(entry);
	}

	public List<ITreeEntry> getChildren() {
		// TODO Auto-generated method stub
		return children;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public ITreeEntry getParentEntry() {
		// TODO Auto-generated method stub
		return parentEntry;
	}

	public boolean hasChild() {
		// TODO Auto-generated method stub
		return children.size()>0;
	}

	public void setChildren(List<ITreeEntry> children) {
		// TODO Auto-generated method stub
		this.children = children;

	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;

	}

	public void setParententry(ITreeEntry parentEntry) {
		// TODO Auto-generated method stub
		this.parentEntry = parentEntry;

	}

}
