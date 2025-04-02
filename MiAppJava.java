public class MiAppJava {
    public static void main(String[] args) {
        String separadores= "===================================";
        System.out.println(separadores +
                "\n BIENVENIDO A REGICIDE \n\t MODO SOLITARIO"+"\n"+separadores);

        PreparacionPartida partida = new PreparacionPartida();

        partida.mostrarEstadoInicial();

        partida.jugarCarta();
        Mazo mazo = new Mazo();
         mazo.barajar();
System.out.println("hola mundo");
           }
}