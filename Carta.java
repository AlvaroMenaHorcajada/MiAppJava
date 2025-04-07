public class Carta {
    private int numero; // Número de la carta (1 al 13)
    private String palo; // Palo (Picas, Corazones, Tréboles, Diamantes)


    public Carta error (int numero, String palo) {
        this.numero = numero;
        this.palo = palo;
    }


    public int getNumero() {
        return numero;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }


    public String getPalo() {
        return palo;
    }


    public void setPalo(String palo) {
        this.palo = palo;
    }


    private String numeroTexto() {
        switch (numero) {
            case 11: return "J";
            case 12: return "Q";
            case 13: return "K";
            default: return String.valueOf(numero);
        }
    }


    @Override
    public String toString() {
        return numeroTexto() + " de " + palo;
    }
}
