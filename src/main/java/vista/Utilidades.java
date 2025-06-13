package vista;

import java.util.Scanner;

public class Utilidades {

    // Método para pedir un número entero positivo al usuario con validación
    public static int pedirEntero(String mensaje) {
        Scanner scan = new Scanner(System.in);
        int valor = 0;
        boolean error;

        do {
            error = false;
            System.out.print(mensaje);
            try {
                String entrada = scan.nextLine();
                if (entrada.trim().isEmpty()) {
                    System.out.println("Error --> El campo no puede estar vacío.\n");
                    error = true;
                    continue;
                }
                valor = Integer.parseInt(entrada);
                if (valor < 0) {
                    System.out.println("Error --> No se permiten valores negativos.\n");
                    error = true;
                }
            } catch (Exception e) {
                System.out.println("Error --> Valor incorrecto. Intenta con un número entero.\n");
                error = true;
            }
        } while (error);
        return valor;
    }

    // Método para pedir un número decimal positivo al usuario con validación
    public static double pedirDouble(String mensaje) {
        Scanner scan = new Scanner(System.in);
        double valor = 0;
        boolean error;

        do {
            error = false;
            System.out.print(mensaje);
            try {
                String entrada = scan.nextLine();
                if (entrada.trim().isEmpty()) {
                    System.out.println("Error --> El campo no puede estar vacío.\n");
                    error = true;
                    continue;
                }
                valor = Double.parseDouble(entrada);
                if (valor < 0) {
                    System.out.println("Error --> No se permiten valores negativos.\n");
                    error = true;
                }
            } catch (Exception e) {
                System.out.println("Error --> Valor incorrecto. Intenta con un número decimal (ej. 10.5).\n");
                error = true;
            }
        } while (error);
        return valor;
    }

    // Método para pedir una cadena de texto no vacía al usuario
    public static String pedirString(String mensaje) {
        Scanner scan = new Scanner(System.in);
        String valor = "";
        boolean error;

        do {
            error = false;
            System.out.print(mensaje);
            try {
                valor = scan.nextLine();
                if (valor.trim().isEmpty()) {
                    System.out.println("Error --> El campo no puede estar vacío.\n");
                    error = true;
                }
            } catch (Exception e) {
                System.out.println("Error --> Valor incorrecto.\n");
                error = true;
            }
        } while (error);
        return valor;
    }

    // Método para pedir texto que solo contenga letras y espacios (sin números ni símbolos)
    public static String pedirText(String mensaje) {
        Scanner scan = new Scanner(System.in);
        String valor = "";
        boolean error;

        do {
            error = false;
            System.out.print(mensaje);
            valor = scan.nextLine();

            if (valor.trim().isEmpty()) {
                System.out.println("Error --> El campo no puede estar vacío.\n");
                error = true;
            } else if (!valor.matches("[a-zA-Z ]+")) {
                System.out.println(
                        "Error --> Solo se permiten letras y espacios, no números ni caracteres especiales.\n");
                error = true;
            }
        } while (error);
        return valor;
    }

    // Método para pedir un número de teléfono válido (hasta 9 dígitos numéricos)
    public static int pedirTelefono(String mensaje) {
        Scanner scan = new Scanner(System.in);
        int telefono = 0;
        boolean error;

        do {
            error = false;
            System.out.print(mensaje);
            String entrada = scan.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("Error --> El campo no puede estar vacío.\n");
                error = true;
            } else if (!entrada.matches("\\d{1,9}")) {
                System.out.println("Error --> Solo se permiten hasta 9 dígitos numéricos (sin letras ni símbolos).\n");
                error = true;
            } else {
                try {
                    telefono = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Error --> Número fuera de rango para tipo int (demasiado grande).\n");
                    error = true;
                }
            }
        } while (error);

        return telefono;
    }

    // Método para pedir un email válido con reglas básicas de formato
    public static String pedirEmail(String mensaje) {
        Scanner scan = new Scanner(System.in);
        String email = "";
        boolean error;

        do {
            error = false;
            System.out.print(mensaje);
            email = scan.nextLine().trim();

            if (email.isEmpty()) {
                System.out.println("Error --> El campo no puede estar vacío.\n");
                error = true;
            } else if (!email.contains("@") || !email.contains(".") || email.contains(" ")) {
                System.out.println(
                        "Error --> El email debe contener '@', al menos un punto ('.') y no debe tener espacios.\n");
                error = true;
            } else {
                int atIndex = email.indexOf("@");
                int dotIndex = email.lastIndexOf(".");

                if (atIndex == 0 || atIndex == email.length() - 1) {
                    System.out.println("Error --> El '@' no puede estar al inicio ni al final.\n");
                    error = true;
                } else if (dotIndex < atIndex || dotIndex == email.length() - 1) {
                    System.out.println("Error --> El punto debe ir después del '@' y no puede estar al final.\n");
                    error = true;
                }
            }
        } while (error);
        return email;
    }

}
