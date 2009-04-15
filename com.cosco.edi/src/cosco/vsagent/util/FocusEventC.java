package cosco.vsagent.util;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Text;



public class FocusEventC extends FocusAdapter {
	private String tableName;
	
	public FocusEventC(String table) {
		setTableName(table);
		
	}
	
	public void focusGained(FocusEvent e){
		
		System.out.println("begin");
		Text t = (Text)e.widget;
//		t.selectAll();
		
	}
	
	public void focusLost(FocusEvent e){
		/**
		 * System.out.println("over");
		 */
		if(getTableName().equals("basport")){
			System.out.println(((Text)e.widget).getText());
		}
	}
	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}

/*
FocusListener focusListener = new FocusListener( ) {
    public void focusGained(FocusEvent e) {
        Text t = (Text)e.widget;
        t.selectAll();
    }
    public void focusLost(FocusEvent e) {
        Text t = (Text)e.widget;
        if(t.getSelectionCount( ) > 0){
            t.clearSelection( );
        }                
    }
};
 text1.addFocusListener(focusListener);
*/
