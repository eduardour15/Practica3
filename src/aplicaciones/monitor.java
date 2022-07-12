package aplicaciones;

import clasesRmi.classMonitor;
import interfacesRmi.objCoordinador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class monitor extends Thread {

    static objCoordinador miCoordinador;
    static int segundos;
    static int cont = 1;

    public static void main(String[] args) throws RemoteException, NotBoundException {
        //REGISTRO DE LAS FUNCIONES
        Registry registry = LocateRegistry.getRegistry("localhost", 1100);
        miCoordinador = (objCoordinador) registry.lookup("miCoordinador");

        segundos = miCoordinador.iniMonitor();

        monitor hilo = new monitor();
        hilo.start();

        //RECUPERO EL CONTROL
        System.out.print("Ingrese 0 para finalizar:");
        Scanner leer = new Scanner(System.in);
        cont = leer.nextInt();

    }

    @Override
    public void run() {

        //INFORMAR AL COORDINADOR EL ESTADO DE LOADAVG
        //EXTRAER VALOR DEL FICHERO
        while (cont == 1) {

            File archivo = new File("/proc/loadavg");
            FileReader fr;
            String loadavg = "";
            try {
                fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                loadavg = br.readLine();
            } catch (FileNotFoundException ex) {
                System.out.print(ex.getMessage());
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }

            try {
                //INSERTAR DATO DEL FICHERO LOADAVG
                miCoordinador.loadMonitor(loadavg);
            } catch (RemoteException ex) {
                System.out.print(ex.getMessage());
            }

            //ESPERAMOS X SEGUNDOS PARA INVOCAR DE NUEVO LA FUNCION
            try {
                Thread.sleep(segundos * 1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }

        try {
            //INSERTAR DATO DEL FICHERO LOADAVG
            miCoordinador.loadMonitor("No monitor");
        } catch (RemoteException ex) {
            System.out.print(ex.getMessage());
        }

        System.out.println("Monitor terminado...");
    }

}
