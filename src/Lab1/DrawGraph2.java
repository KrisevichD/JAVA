package Lab1;


import java.util.Random;

public class DrawGraph2 {

    public static void main(String[] args) {
        Random rand = new Random();
        Graph g = new Graph(10);
        int[][] arr = new int[12][12];
        for (int i=0; i<12; i++){
            for (int j=0; j<12 ;j++){
                if (i==0 || j==0 || i==11 || j==11){
                    arr[i][j]=0;
                }
                else {
                    arr[i][j]=g.getMatrix()[i-1][j-1];
                }
            }
        }
        int s1 = rand.nextInt(10)+1;
        int s2 = rand.nextInt(10)+1;
        arr[0][s1]=1;
        arr[s1][0]=1;
        arr[0][s2]=1;
        arr[s2][0]=1;
        int s3 = rand.nextInt(10)+1;
        int s4 = rand.nextInt(10)+1;
        arr[11][s3]=1;
        arr[s3][11]=1;
        arr[11][s4]=1;
        arr[s4][11]=1;
        g.setMatrix(arr);
        g.show2(g);



        
    }


}

