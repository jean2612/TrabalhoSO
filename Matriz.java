

public class Matriz {
    int[][] matriz;

    public Matriz(int length) {
        matriz = new int[length][length];
    }

    public int get(int x, int y) {
        return matriz[x][y];
    }

    public void set(int x, int y, int value) {
        matriz[x][y] = value;
    }

    public int length(){
        return matriz.length;
    }


    public void imprimir(){
        //Imprimir matriz
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz.length; j++){
                System.out.print("["+matriz[i][j]+"]");
            }
            System.out.println();
        }
    }

}