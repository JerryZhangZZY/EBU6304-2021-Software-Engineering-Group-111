package Backendmain;
/**
 * It can start the back-end system
 *
 * @author Wang Chenyu
 * @date 2022/3/26
 * @version 1.0
 *
 * @author Zhang Zeyu
 * @date 2022/3/27
 * @version 1.1
 * bug fixed
 */
import BackendSystemFrame.BackendMainFrame;
import BackendSystemPanel.LogINPanel;
import BackendSystemPanel.TablePanel;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class ControlBackSystem{
    public static void main(String[] args) throws InterruptedException, IOException {
        BackendMainFrame backend = new BackendMainFrame();
        TablePanel table=new TablePanel();
        LogINPanel log=new LogINPanel();
        int currentPC =-1;
        Systempointer.setPc(0);
        while (true){
            backend.setVisible(true);
                while (currentPC == Systempointer.getPc()) {
                    sleep(1);
                }
                currentPC=Systempointer.getPc();
                switch (Systempointer.getPc()) {
                     case 0: {
                            backend.unloadPanel(backend.getLoadedPanel());
                            backend.loadPanel(log);
                            backend.resetWelcomeText();
                            backend.repaint();
                            break;
                     }
                     case 1: {
                            backend.unloadPanel(backend.getLoadedPanel());
                            backend.loadPanel(table);
                            backend.setWelcomeText();
                            backend.repaint();
                            break;
                     }
                }
            }
    }
}
