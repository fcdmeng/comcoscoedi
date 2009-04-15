package cosco.vsagent.console;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;

public class LogConsoleFactory implements IConsoleFactory {
	private static MessageConsole console;
	public void openConsole() {
		getConsole();
		

	}
	
	public static MessageConsole getConsole(){
		if(console == null){
			console = new MessageConsole("»’÷æ", null);
			IConsoleManager mgr = ConsolePlugin.getDefault().getConsoleManager();
			mgr.addConsoles(new IConsole[]{console});
		}
		return console;
	}

}
