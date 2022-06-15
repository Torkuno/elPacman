import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class elPacman{

    public static void main (String[] args){

        int[][] matrizDelMapa = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,2,2,2,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,1,2,2,2,1,2,2,2,1,2,2,2,2,2,1,2,3,2,2,1},
            {1,2,1,2,1,2,2,2,2,2,2,1,2,2,2,1,2,3,2,1,2,1,1,1,2,1,2,2,2,2,1},
            {1,2,1,2,1,2,2,3,3,2,2,1,1,2,2,1,2,3,2,1,2,2,2,2,2,1,1,1,1,2,1},
            {1,2,1,2,1,2,2,3,3,2,2,2,2,2,2,1,2,3,2,1,1,2,1,2,2,2,1,2,2,2,1},
            {1,2,2,2,1,2,2,3,3,2,2,1,2,2,2,2,2,2,2,2,2,2,1,2,2,2,1,2,2,2,1},
            {1,2,2,2,1,2,2,3,3,2,2,1,2,2,2,2,2,2,2,1,1,2,1,2,1,2,2,2,1,2,1},
            {1,2,2,1,1,2,2,2,2,2,2,2,2,1,2,2,2,1,2,2,2,2,1,2,2,2,2,2,2,2,1},
            {4,2,2,2,2,2,2,2,2,2,2,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,2,2,2,2,4},
            {1,2,2,2,2,2,2,2,1,2,2,2,2,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,3,3,2,2,1,1,1,1,2,2,2,2,2,1,2,2,2,3,3,3,2,3,3,3,2,3,3,2,1},
            {1,2,3,2,2,2,2,2,2,1,2,2,2,2,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,3,2,1,2,3,3,2,1,2,2,1,2,2,2,2,1,1,1,1,1,2,1,1,2,3,2,1,2,1},
            {1,2,2,2,1,2,3,3,2,1,2,2,1,2,2,2,2,1,2,2,2,2,2,2,2,2,3,2,2,2,1},
            {1,2,1,1,1,2,3,3,2,1,2,2,2,2,2,1,1,1,2,3,3,3,2,2,2,2,2,2,1,2,1},
            {1,2,2,2,1,2,2,2,2,2,2,2,1,2,2,2,2,1,2,3,3,3,2,2,1,2,1,2,2,2,1},
            {1,2,1,2,1,1,1,2,1,2,1,1,1,2,2,2,2,2,2,3,2,2,2,2,1,2,1,2,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        int[][] elPersonaje = {{15, 14}};
		int[] elReloj = {16, 0};
		int[][] losFantasmas = {
			{14, 9 },
			{14, 10},
			{16, 9 },
			{16, 10}
		};
		int totalDeMonedas = 380;

		do {
			pasaElTiempo(elReloj);
			imprimeMundo(matrizDelMapa, elPersonaje, losFantasmas, elReloj);
		} while (procesaMovimiento(matrizDelMapa, elPersonaje, losFantasmas));
    }

    private static void pasaElTiempo(int[] elReloj) {

		elReloj[1] = elReloj[1] + 10;

		if (elReloj[1] == 60){
			elReloj[0] = elReloj[0] + 1;
			elReloj[1] = 0;
		}

		if (elReloj[0] == 24){
			elReloj[0] = 0;
			elReloj[1] = 0;
		}

		ALCANCE_ANTORCHA = alcanceAntorcha(elReloj);
	}

	private static int alcanceAntorcha(int[] elReloj) {
		
		int hora, minuto;
		double minutos;
		hora = elReloj[0];
		minuto = elReloj[1];
		minutos = hora * 60 + minuto;

		if (hora < 4 || hora >= 21){return 3;}
		if (hora >= 4 && hora < 8) {return ((int)(3.0 + ((32.0/240.0) * (minutos - 240.0))));}
		if (hora >= 17 && hora < 21) {return ((int)(35.0 + ((-32.0/240.0) * (minutos - 1030.0))));}
		return 35;
	}

	private static boolean procesaMovimiento(int[][] elMapa, int[][] elPersonaje, int[][] losFantasmas) {

		Scanner entrada = new Scanner(System.in);
		String inputUsuario;
		char laDireccion = ' ';

		inputUsuario = entrada.nextLine();

		if (inputUsuario.equals("w")) {laDireccion = 'N';} else
		if (inputUsuario.equals("a")) {laDireccion = 'O';} else
		if (inputUsuario.equals("s")) {laDireccion = 'S';} else
		if (inputUsuario.equals("d")) {laDireccion = 'E';} else
		if (inputUsuario.equals("f")) {return false;}

		mueveFantasmas(elMapa, losFantasmas);
		mueve(elPersonaje[0], elMapa, laDireccion);
		return true;
	}

	private static void mueve(int[] unPersonaje, int[][] matrizDelMapa, char unaDireccion ){

		int elPersonajeX, elPersonajeY;
		elPersonajeX = unPersonaje[0];
		elPersonajeY = unPersonaje[1];

		if (unaDireccion == 'N') {
			if (elPersonajeY == 0) {elPersonajeY = matrizDelMapa.length - 1;}
			else if  (matrizDelMapa[elPersonajeY - 1][elPersonajeX] % 2 == 0) {elPersonajeY = elPersonajeY - 1;}
		}
		else if (unaDireccion == 'O') {
			if (elPersonajeX == 0) {elPersonajeX = matrizDelMapa[0].length - 1;}
			else if  (matrizDelMapa[elPersonajeY][elPersonajeX - 1] % 2 == 0) {elPersonajeX = elPersonajeX - 1;}
		}
		else if (unaDireccion == 'S') {
			if (elPersonajeY == matrizDelMapa.length - 1) {elPersonajeY = 0;} 
			else if  (matrizDelMapa[elPersonajeY + 1][elPersonajeX] % 2 == 0) {elPersonajeY = elPersonajeY + 1;}
		}
		else if (unaDireccion == 'E') {
			if (elPersonajeX == matrizDelMapa[0].length - 1) {elPersonajeX = 0;} 
			else if  (matrizDelMapa[elPersonajeY][elPersonajeX + 1] % 2 == 0) {elPersonajeX = elPersonajeX + 1;}
		}

		unPersonaje[0] = elPersonajeX;
		unPersonaje[1] = elPersonajeY;	
	}

	private static void mueveFantasmas(int[][] elMapa, int[][] losFantasmas) {

		char[] laDireccion = {'N','S','E','O'};
		char unaDireccion = ' ';

		for (int unFantasma = 0; unFantasma < losFantasmas.length; unFantasma++) {
			Random random = new Random();
			unaDireccion = laDireccion[random.nextInt(3)];
			mueve(losFantasmas[unFantasma], elMapa, unaDireccion);
		}
	}

	private static boolean hayFantasma(int[][] losFantasmas, int i, int j) {

		for (int unFantasma = 0; unFantasma < losFantasmas.length; unFantasma++) {
			if (losFantasmas[unFantasma][0] == j && losFantasmas[unFantasma][1] == i) {
				return true;
			}
		}
		return false;
	}

	private static void limpiaPantalla() {

		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

    private static void imprimeMundo(int[][] elMapa, int[][] elPersonaje, int[][] losFantasmas, int[] elReloj) {

		limpiaPantalla();
		imprimeBordeHorizontal(elMapa[0].length);

		for (int i = 0; i < elMapa.length; i = i + 1) {
			imprimeBordeVertical(false);
			
			for (int j = 0; j < elMapa[i].length; j = j + 1) {
				if (puedoVer(i, j, elPersonaje)) {
					if (i == elPersonaje[0][1] && j == elPersonaje[0][0]) {
						imprimePersonaje();
					} else {
						if (hayFantasma(losFantasmas, i, j)) {
							imprimeFantasma();
						} else {
							imprimeElemento(elMapa[i][j]);
						}
					}
				} else {
					imprimirVacio();
				}
			}
			imprimeBordeVertical(true);
		}
		imprimeBordeHorizontal(elMapa[0].length);
		imprimeStatus(elPersonaje, losFantasmas, elReloj);
	}

    private static void imprimeStatus(int[][] elPersonaje, int[][] losFantasmas, int[] elReloj) {

		System.out.println("Reloj: [" + elReloj[0] + ":" + elReloj[1] + "]");
		System.out.println("Alcance de vision: [" + ALCANCE_ANTORCHA + "]");
		System.out.println("\nPacMan = (" + elPersonaje[0][0] + ", " + elPersonaje[0][1] + ")");
		for (int unFantasma = 0; unFantasma < losFantasmas.length; unFantasma++) {
			System.out.println("Fantasma [#" + unFantasma + "] = (" + losFantasmas[unFantasma][0] + ", " + losFantasmas[unFantasma][1] + ")");
		}
	}

	private static void imprimeElemento(int elementoDelMapa) {

		String[] matrizDeElementos = {
				INICIO + YELLOW + GREEN_BACKGROUND + "   " + RESET,
				INICIO + WHITE + WHITE_BACKGROUND + "[#]" + RESET,
				INICIO + YELLOW + GREEN_BACKGROUND + " . " + RESET,
				INICIO + BLUE_BOLD + BLUE_BACKGROUND + "~ ~" + RESET,
				INICIO + WHITE_BOLD + PURPLE_BACKGROUND + " @ " + RESET
		};
		System.out.print(matrizDeElementos[elementoDelMapa]);
	}

    private static void imprimirVacio() {

		System.out.print(INICIO + BLACK + BLACK_BACKGROUND + "   " + RESET);

	}

	private static boolean puedoVer(int i, int j, int[][] elPersonaje) {

		return Math.pow(elPersonaje[0][0] - j, 2) + Math.pow(elPersonaje[0][1] - i, 2) <= Math.pow(ALCANCE_ANTORCHA, 2);

	}

    private static void imprimePersonaje() {
		System.out.print(INICIO + BLACK + YELLOW_BACKGROUND + "°< " + RESET);
	}

	private static void imprimeFantasma() {
		System.out.print(INICIO + YELLOW_BOLD + RED_BACKGROUND + "°w°" + RESET);
	}

    private static void imprimeBordeHorizontal(int laLongitud) {

		System.out.print("+");
		for (int j = 0; j < laLongitud; j = j + 1) {
			System.out.print("---");
		}
		System.out.println("+");
	}

	private static void imprimeBordeVertical(boolean esBordeDerecho) {

		System.out.print("|");
		if (esBordeDerecho) {
			System.out.println();
		}
	}

	private static int ALCANCE_ANTORCHA = 3;

	private static String INICIO = "\033[";
	private static String RESET = "\033[0m";

	private static String BLACK = "0;30";
	private static String RED = "0;31";
	private static String GREEN = "0;32";
	private static String YELLOW = "0;33";
	private static String BLUE = "0;34";
	private static String PURPLE = "0;35";
	private static String CYAN = "0;36";
	private static String WHITE = "0;37";

	private static String BLACK_BOLD = "1;30";
	private static String RED_BOLD = "1;31";
	private static String GREEN_BOLD = "1;32";
	private static String YELLOW_BOLD = "1;33";
	private static String BLUE_BOLD = "1;34";
	private static String PURPLE_BOLD = "1;35";
	private static String CYAN_BOLD = "1;36";
	private static String WHITE_BOLD = "1;37";

	private static String BLACK_BACKGROUND = ";40m";
	private static String RED_BACKGROUND = ";41m";
	private static String GREEN_BACKGROUND = ";42m";
	private static String YELLOW_BACKGROUND = ";43m";
	private static String BLUE_BACKGROUND = ";44m";
	private static String PURPLE_BACKGROUND = ";45m";
	private static String CYAN_BACKGROUND = ";46m";
	private static String WHITE_BACKGROUND = ";47m";
}