
public class Leitor {
    Matriz matriz;
    Auxiliar auxiliar;

    // Construtor padrão, referencia matriz de entrada e vetor auxiliar do Main
    public Leitor() {
        this.matriz = Main.matrizEntrada;
        this.auxiliar = Main.auxiliar;
    }

    public Leitor(Matriz matriz, Auxiliar auxiliar) {
        this.matriz = matriz;
        this.auxiliar = auxiliar;
    }

    // Percorre a matriz sequencialmente e incrementa a posição do valor lido no vetor auxiliar valores
    public void lerSequencial(){
        for(int i = 0; i < matriz.length(); i++) {
            for(int j = 0; j < matriz.length(); j++){
                auxiliar.valores[ matriz.get(i, j) ]++;
            }
        }
    }

    // Percorre a matriz utilizando o vetor posições e incrementa a posição correspondente do valor lido no vetor valores
    public void lerAleatorio(){
        for(int i = 0; i < auxiliar.posicoes.size(); i++) {
            auxiliar.valores[ matriz.get(i) ]++;
        }
    }
}