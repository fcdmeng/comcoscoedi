package cosco.vsagent.model;

import java.util.List;

public interface ITreeEntry {
	void setName(String name);
	String getName();
	
	void setChildren(List<ITreeEntry> children);
	List<ITreeEntry> getChildren();
	void addChild(ITreeEntry entry);
	boolean hasChild();
	
	void setParententry(ITreeEntry parentEntry);
	ITreeEntry getParentEntry();

}
