package interfacesRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface objMonitor extends Remote {

    void pingMonitor() throws RemoteException;
}
