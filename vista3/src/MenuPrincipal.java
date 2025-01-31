import java.io.IOException;
import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione un programa para ejecutar:");
            System.out.println("1. Programa 1");
            System.out.println("2. Programa 2");
            System.out.println("3. Programa 3");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ejecutarPrograma1();
                    break;
                case 2:
                    ejecutarPrograma2();
                    break;
                case 3:
                    ejecutarPrograma3();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Invocar el flujo del programa 1
    private static void ejecutarPrograma1() {
        // Aquí, se asume que las clases de Programa 1 están en el mismo proyecto.
        Program1.main(new String[0]);
    }

    // Invocar el flujo del programa 2
    private static void ejecutarPrograma2() {
        // Aquí, se asume que las clases de Programa 2 están en el mismo proyecto.
        Program2.main(new String[0]);
    }

    // Ejecutar el programa 3 utilizando ProcessBuilder
    private static void ejecutarPrograma3() {
        // Ruta hacia la carpeta de clases del programa 3
        String rutaClases = "C:\\tmp\\vista3\\src";  // Ruta donde están los archivos .class
        String comando = "java -cp " + rutaClases + " com.mycompany.casas.Main";

        try {
            // Usar ProcessBuilder para ejecutar el comando
            ProcessBuilder builder = new ProcessBuilder(comando.split(" "));
            builder.inheritIO();  // Muestra la salida del programa en la consola actual
            Process proceso = builder.start();
            proceso.waitFor();  // Espera a que el programa termine su ejecución
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
