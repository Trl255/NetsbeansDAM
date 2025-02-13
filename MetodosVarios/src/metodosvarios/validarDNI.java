/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodosvarios;

/**
 *
 * @author josea
 */
public class validarDNI {
  
    /**
     * Valida un DNI Comprueba si tiene entre 8 y 9 caracteres Comprueba si la
     * parte numerica es correcta Comprueba si la letra es correcta Comprueba si
     * el numero y la letra es corrrecta
     *
     * @param DNI
     * @throws DNIException
     */
    public static void validarDNI(String DNI) throws DNIException {

        //Comprobamos la longitud del dni
        if (!(DNI.length() >= 8 && DNI.length() <= 9)) {
            throw new DNIException(DNIException.LONGITUD_NO_CORRECTA);
        }

        //saco la parte numerica
        String parte_numerica = DNI.substring(0, DNI.length() - 1);

        //Aqui guardare el dni
        int numeroDNI = 0;

        try {
            //Lo transformo en un numero
            //Puede saltar la excepcion
            numeroDNI = Integer.parseInt(parte_numerica);
        } catch (NumberFormatException e) {
            throw new DNIException(DNIException.PARTE_NUMERICA_NO_CORRECTA);
        }

        //
        char letra = DNI.substring(DNI.length() - 1, DNI.length()).toUpperCase().charAt(0);

        if (!(letra >= 'A' && letra <= 'Z')) {
            throw new DNIException(DNIException.PARTE_LETRA_NO_CORRECTA);
        }

        //Ya hemos validado el formato
        final int DIVISOR = 23;

        char letrasNIF[] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y',
            'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z',
            'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        int resto = numeroDNI % DIVISOR;

        String nuevoDNI = numeroDNI + "" + letrasNIF[resto];

        if (DNI.startsWith("0")) {
            nuevoDNI = "0" + nuevoDNI;
        }

        if (!(nuevoDNI.equals(DNI))) {
            throw new DNIException(DNIException.FORMATO_NO_CORRECTO);
        }

    }

  
}
