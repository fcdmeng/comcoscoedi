package cosco.vsagent.console;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class ConsoleCommand {
	public void logconsole(String message){
		logconsole(message, false);
	}
	public void logconsole(String message, boolean color_flag){
		MessageConsole console = LogConsoleFactory.getConsole();
		MessageConsoleStream stream = console.newMessageStream();
		if(color_flag){
			stream.setColor(Display.getDefault().getSystemColor(SWT.COLOR_RED));
		}
		stream.println(message);
		
	}
	
	public void clearConsole(){
		LogConsoleFactory.getConsole().clearConsole();
	}
}
