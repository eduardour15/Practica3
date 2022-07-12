package aplicaciones;

import clasesRmi.classCoordinador;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class servidor {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1100);
        registry.bind("miCoordinador", new classCoordinador());

        System.out.println("Servidor, corriendo...");
    }
}
