

public class Transportador {
    Matriz matrizSaida;
    Auxiliar auxiliar;

    // Construtor padrão, referencia matriz de saída e vetor auxiliar do Main
    public Transportador() {
        this.matrizSaida = Main.matrizSaida;
        this.auxiliar = Main.auxiliar;
    }

    public Transportador(Matriz matrizSaida, Auxiliar auxiliar) {
        this.matrizSaida = matrizSaida;
        this.auxiliar = auxiliar;
    }

    // Percorre a matriz preenchendo-a com os valores do vetor auxiliar valores
    public void transportarMatriz(){

        int posicao = 0;
        int contador = 0;
        System.out.println();

        for(int i = 0; i < matrizSaida.length(); i++) {
            for(int j = 0; j < matrizSaida.length(); j++) {

                if (contador == auxiliar.valores[posicao]) {
                    do {
                        posicao++;
                    } while (auxiliar.valores[posicao] == 0);

                    contador = 0;
                }

                matrizSaida.set(i, j, posicao);

                contador++;
            }
        }

    }
}