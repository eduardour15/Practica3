package clasesRmi;

import interfacesRmi.objCoordinador;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class classCoordinador extends UnicastRemoteObject implements objCoordinador {

    int cant = 0;
    int tiempo;
    String msj = "No hay monitores activos";

    public classCoordinador() throws RemoteException {
        super();
    }

    @Override
    public int iniMonitor() throws RemoteException {
        cant++;
        return tiempo;
    }

    @Override
    public void loadMonitor(String loadavg) throws RemoteException {
        if ("No monitor".equals(loadavg)) {
            cant--;
        }

        msj = loadavg;
    }

    @Override
    public int iniClient(int segundos) throws RemoteException {
        tiempo = segundos;

        //ACA TENGO QUE MANDAR A LLAMAR A PING MONITOR

        return cant;
    }

    @Override
    public String getLoadAvg() throws RemoteException {
        return msj;
    }

}
