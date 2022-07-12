package aplicaciones;

import interfacesRmi.objCoordinador;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class cliente {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        int log = 0;
        int cant = 0;
        //REGISTRO DE LAS FUNCIONES
        Registry registry = LocateRegistry.getRegistry("localhost", 1100);
        objCoordinador miCoordinador = (objCoordinador) registry.lookup("miCoordinador");

        //DECLARACION DE VARIABLES
        int segundos;
        String avg;

        System.out.print("Ingrese el intervalo:");

        //OBTENEMOS EL INTERVALO DE MEDICION
        Scanner leer = new Scanner(System.in);
        segundos = leer.nextInt();
        cant = miCoordinador.iniClient(segundos);
        System.out.println("Hay: " + cant + " monitor(es) activo(s)");

        do {
            cant = miCoordinador.iniClient(segundos);

            if (cant == 0) {
                //ESPERO LA MISMA CANT DE SEGUNDOS
                esperar(segundos);
            } else {
                //CICLO
                while (!(avg = miCoordinador.getLoadAvg()).equals("No monitor")) {

                    //IMPRIMIR LOS VALORES
                    System.out.println("#" + log + ": " + avg);
                    log++;

                    esperar(segundos);
                }
            }

        } while (cant == 0);

        System.out.println("Cliente terminado por falta de  monitores");
    }

    public static void esperar(int x) {
        //ESPERAMOS X SEGUNDOS PARA INVOCAR DE NUEVO LA FUNCION
        try {
            Thread.sleep(x * 1000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
