package interfacesRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface objCoordinador extends Remote {

    int iniMonitor() throws RemoteException;

    void loadMonitor(String loadavg) throws RemoteException;

    int iniClient(int segundos) throws RemoteException;

    String getLoadAvg() throws RemoteException;
}
