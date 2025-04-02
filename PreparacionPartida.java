import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PreparacionPartida {
    private List<Carta> mano;
    private List<Carta> castillo;
    private List<Carta> mazoPosada;
    private List<Carta> mazoJugadas;
    private List<Carta> mazoDescartes;


    public PreparacionPartida() {
        mano = new ArrayList<>();
        castillo = new ArrayList<>();
        mazoPosada = new ArrayList<>();
        mazoJugadas = new ArrayList<>();
        mazoDescartes = new ArrayList<>();

        inicializarColecciones();
    }


    private void inicializarColecciones() {
        Mazo mazoCompleto = new Mazo();

        // Creacion castillo (Jotas, Reinas, Reyes)
        for (int numero = 11; numero <= 13; numero++) {
            for (String palo : new String[]{"Picas", "Corazones", "Tréboles", "Diamantes"}) {
                castillo.add(new Carta(numero, palo));
            }
        }

        java.util.Collections.shuffle(castillo.subList(0, 4));//Jotas
        java.util.Collections.shuffle(castillo.subList(4, 8));//Reinas
        java.util.Collections.shuffle(castillo.subList(8, 12));//Reyes

        // Creacion posada con lo que sobra
        for (Carta carta : mazoCompleto.getCartas()) {
            if (carta.getNumero() < 11) {
                mazoPosada.add(carta);
            }
        }
        java.util.Collections.shuffle(mazoPosada);

        // segarro amego de 8 cartas de posada a mano
        for (int i = 0; i < 8; i++) {
            mano.add(mazoPosada.remove(0));
        }
    }


    public void mostrarEstadoInicial() {
        System.out.println("BIENVENIDO A REGICIDE MODO SOLITARIO");

        System.out.println("\nTu mano actual:");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + mano.get(i));
        }

        Carta enemigoActual = castillo.get(0);
        System.out.println("\nEnemigo actual en el castillo: " + enemigoActual + " (Vida: " + calcularVidaEnemigo(enemigoActual.getNumero()) + ")");

        System.out.println("\nCartas restantes:");
        System.out.println("- Castillo: " + castillo.size() + " enemigos");
        System.out.println("- Mazo de Posada: " + mazoPosada.size() + " cartas");
        System.out.println("- Mazo de Cartas Jugadas: " + mazoJugadas.size() + " cartas");
        System.out.println("- Mazo de Descartes: " + mazoDescartes.size() + " cartas");
    }


    public void jugarCarta() {
        if (mano.isEmpty()) {
            System.out.println("No tienes cartas en tu mano para jugar.");
            return;
        }

        Carta enemigoActual = castillo.get(0);
        int vidaEnemigo = calcularVidaEnemigo(enemigoActual.getNumero());

        System.out.println("\n¡Comienza la pelea!");



        System.out.print("\nSelecciona una carta para jugar (1-" + mano.size() + "): ");
        Scanner scanner = new Scanner(System.in);
        int eleccion = scanner.nextInt();

        if (eleccion < 1 || eleccion > mano.size()) {
            System.out.println("Selección inválida. Turno perdido.");
            return;
        }

        Carta cartaJugada = mano.remove(eleccion - 1);
        mazoJugadas.add(cartaJugada);

        int daño = cartaJugada.getNumero();
        vidaEnemigo -= daño;

        System.out.println("\nHas jugado: " + cartaJugada);
        System.out.println("Daño causado al enemigo: " + daño);
        System.out.println("Vida restante del enemigo: " + Math.max(0, vidaEnemigo));

        if (vidaEnemigo <= 0) {
            System.out.println("\n¡Has derrotado al enemigo: " + enemigoActual + "!");
            castillo.remove(0);
            mazoDescartes.add(enemigoActual);

            if (!castillo.isEmpty()) {
                System.out.println("El próximo enemigo es: " + castillo.get(0));
            } else {
                System.out.println("¡Has derrotado a todos los enemigos en el castillo!");
            }
        }

        System.out.println("\nTu mano actual:");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + mano.get(i));
        }

        System.out.println("\nCartas restantes:");
        System.out.println("- Castillo: " + castillo.size() + " enemigos");
        System.out.println("- Mazo de Posada: " + mazoPosada.size() + " cartas");
        System.out.println("- Mazo de Cartas Jugadas: " + mazoJugadas.size() + " cartas");
        System.out.println("- Mazo de Descartes: " + mazoDescartes.size() + " cartas");
    }


    private int calcularVidaEnemigo(int numero) {
        switch (numero) {
            case 11: return 20; // Jota
            case 12: return 30; // Reina
            case 13: return 40; // Rey
            default:
                throw new IllegalArgumentException("El número de la carta no es válido para un enemigo.");
        }
    }
}
