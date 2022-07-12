package clasesRmi;

import interfacesRmi.objMonitor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class classMonitor extends UnicastRemoteObject implements objMonitor {

    public classMonitor() throws RemoteException {
        super();
    }

    @Override
    public void pingMonitor() throws RemoteException {

    }
}
