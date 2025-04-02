import java.util.ArrayList;
import java.util.List;

public class Mazo {
    private List<Carta> cartas;


    public Mazo() {
        cartas = new ArrayList<>();
        String[] palos = {"Picas", "Corazones", "Tréboles", "Diamantes"};


        for (String palo : palos) {
            for (int numero = 1; numero <= 13; numero++) {
                cartas.add(new Carta(numero, palo));
            }
        }
    }


    public List<Carta> getCartas() {
        return cartas;
    }


    public void mostrarMazo() {
        for (Carta carta : cartas) {
            System.out.println(carta);
        }
    }


    public void barajar() {
        java.util.Collections.shuffle(cartas);
    }
}

