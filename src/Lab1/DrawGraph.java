package Lab1;


public class DrawGraph {
    public static void main(String[] args) {

        Graph g = new Graph(6);
        g.show(g);
        int e=0;
        for (int i=0; i<g.getMatrix().length; i++){
            for (int j=i+1; j<g.getMatrix().length; j++){
                e+=g.getMatrix()[i][j];
            }
        }
        int max = findMaxCep(g.getMatrix());
        System.out.println("All: "+e);
        System.out.println("Min need to delete: "+ max);
        for (int i=0;i<g.getMatrix().length;i++){
            for (int j=0;j<g.getMatrix().length;j++){
                System.out.print(g.getMatrix()[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("incident");
        g.incident(g.getMatrix(), e);
        System.out.println("Spiski smezhnosti");
        g.listOfSmezhnosti(g.getMatrix());
        System.out.println("Spiski dug");
        g.listOfDugs(g.getMatrix());
        System.out.println();
        System.out.println("Min lengths to vertices");
        System.out.println("Index of mediana: "+g.findMed(g.getMatrix()));


    }


    public static int findMaxCep(int[][] m){
        if (isCepo4ka(m)){
            return m.length;
        }
        int[] maxis = new int[m.length];
        if (m.length>2)
        for (int i=0; i<m.length; i++){
            int[][] b = new int[m.length-1][m.length-1];
            for (int j=0; j<m.length; j++){
                for (int k=0; k<m.length; k++) {
                    int plus1=0;
                    int plus2=0;
                    if (j==i || k==i){

                    }else {
                        if (k > i) {
                            plus2=1;
                        }
                        if (j > i) {
                            plus1=1;
                        }

                        b[j-plus1][k-plus2] = m[j][k];
                    }
                }
            }
            maxis[i] = findMaxCep(b);
        }
        int max =0;
        for (int i=0; i< maxis.length; i++){
            if (maxis[i]>max){
                max = maxis[i];
            }
        }
        return max;
    }

    public static boolean isCepo4ka(int[][] m){

        for (int i=0; i<m.length; i++){
            int sum = 0;
            for (int j=0; j<m.length; j++){
                sum+=m[i][j];
            }
            if (sum !=2){
                return false;
            }
        }
        return true;
    }
}

