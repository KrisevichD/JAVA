package Lab1;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Graph {

    private int[][] matrix;

    public Graph(){
        Random rand = new Random();
        int len = rand.nextInt(5)+5;
        int[][] m = new int[len][len];

        for (int i = 0; i<len; i++){

            m[i][i] = 0;
            for (int j=i+1; j<len; j++){
                int r=rand.nextInt(2);
                m[i][j] = r;
                m[j][i] = r;
            }
        }
        this.matrix = m;
    }
    public Graph(int len){
        Random rand = new Random();
        int[][] m = new int[len][len];

        for (int i = 0; i<len; i++){

            m[i][i] = 0;
            for (int j=i+1; j<len; j++){
                int r=rand.nextInt(2);
                m[i][j] = r;
                m[j][i] = r;
            }
        }
        this.matrix = m;
    }



    public int[][] getMatrix() {
        return matrix;
    }

    public void incident(int[][] m, int e){
        int[][] b = new int[m.length][e];
        int k=0;
        for (int i = 0; i< m.length; i++){
            for (int j = i; j<m.length; j++){
                if (m[i][j] == 1){
                    b[i][k]=1;
                    b[j][k]=1;
                    k++;
                }
            }
        }
        for (int i=0; i<m.length; i++){
            for (int j=0; j<e; j++){
                System.out.print(b[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void listOfSmezhnosti(int[][] m){
        for (int i = 0; i<m.length; i++){
            System.out.print(i+" || ");
            for (int j=0; j<m.length; j++){
                if (m[i][j] == 1){
                    System.out.print(j+" ");
                }
            }
            System.out.println();
        }
    }

    public int findMed(int[][] m){
        int len = m.length;
        int[][] w = new int[len][len];
        for (int i=0;i<len;i++){
            for (int j=0;j<len;j++){
                if (i==j){
                    w[i][j]=0;
                } else
                if (m[i][j]==0){
                    w[i][j]=100;
                } else{
                    w[i][j]=1;
                }
            }
        }
        for (int k=0;k<m.length;k++){
            for (int i=0;i<m.length;i++){
                for (int j=0;j<m.length;j++){
                    w[i][j] = Math.min(w[i][j], w[i][k] + w[k][j]);
                }
            }
        }
        int min=1000;
        int x=0;
        int t;
        for (int i=0;i<len;i++){
            t=0;
            for (int j=0;j<len;j++){
                System.out.print(w[i][j]+" ");
                t+=w[i][j];
            }
            if (t<min){
                min = t;
                x=i;
            }
            System.out.println(" | "+t);
        }
        return x;
    }

    public void listOfDugs(int[][] m){
        for (int i=0; i<m.length; i++){
            for (int j=i; j<m.length; j++){
                if (m[i][j]==1){
                    System.out.print(i+"-"+j+"  ");
                }
            }
        }
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }


    private int dfs(int next) {
        return 0;
    }

    public void show(Graph graph){
        Vertex[] vertices = new Vertex[graph.getMatrix().length];
        for (int i = 0; i<vertices.length; i++){
            vertices[i] = new Vertex(i);
        }
        int med = graph.findMed(graph.getMatrix());

        JFrame frame=new JFrame("Test");
        frame.setBounds(0, 0,800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(){
            Graphics2D gg;

            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                gg=(Graphics2D)g;

                int len=graph.getMatrix().length;
                for (int i = 0; i<len; i++){

                    int x = vertices[i].getX();
                    int y = vertices[i].getY();
                    for (int j = 0; j<len; j++){
                        if (graph.getMatrix()[i][j] == 1){
                            gg.setColor(Color.BLACK);
                            gg.drawLine(x,y,vertices[j].getX(),vertices[j].getY());
                        }
                        if (graph.getMatrix()[i][j] == 2){
                            gg.setColor(Color.RED);
                            gg.drawLine(x,y,vertices[j].getX(),vertices[j].getY());
                        }
                    }
                }
                for (int i = 0; i<len; i++){
                    int x = vertices[i].getX();
                    int y = vertices[i].getY();

                    if (i==med){
                        gg.setColor(Color.GREEN);
                        gg.fillOval(x-10,y-10,20,20);

                    }else{
                        gg.setColor(Color.RED);
                        gg.fillOval(x-10,y-10,20,20);

                    }

                    gg.setColor(Color.BLACK);
                    gg.setFont(new Font("Calibri",Font.BOLD,20));
                    gg.drawString(Integer.toString(i),x-4, y+6);
                }
            }
        };
        frame.setContentPane(contentPane);
    }


    public void show2(Graph graph){
        Vertex[] vertices = new Vertex[graph.getMatrix().length];
        for (int i = 0; i<vertices.length; i++){
            vertices[i] = new Vertex(i);
        }

        JFrame frame=new JFrame("Test");
        frame.setBounds(0, 0,800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(){
            Graphics2D gg;

            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                gg=(Graphics2D)g;

                int len=graph.getMatrix().length;
                for (int i = 0; i<len; i++){

                    int x = vertices[i].getX();
                    int y = vertices[i].getY();
                    for (int j = 0; j<len; j++){
                        if (graph.getMatrix()[i][j] == 1){
                            gg.setColor(Color.BLACK);
                            gg.drawLine(x,y,vertices[j].getX(),vertices[j].getY());
                        }
                        if (graph.getMatrix()[i][j] == 2){
                            gg.setColor(Color.RED);
                            gg.drawLine(x,y,vertices[j].getX(),vertices[j].getY());
                        }
                    }
                }
                for (int i = 0; i<len; i++){
                    int x = vertices[i].getX();
                    int y = vertices[i].getY();
                    if (i == 0 || i == 11){
                        gg.setColor(Color.BLUE);
                        gg.fillOval(x-10,y-10,20,20);
                    }else if (graph.getMatrix()[0][i]==1){
                        gg.setColor(Color.YELLOW);
                        gg.fillOval(x-10,y-10,20,20);
                    }else if (graph.getMatrix()[11][i]==1){
                        gg.setColor(Color.PINK);
                        gg.fillOval(x-10,y-10,20,20);
                    }else {
                        gg.setColor(Color.RED);
                        gg.fillOval(x - 10, y - 10, 20, 20);
                    }


                    gg.setColor(Color.BLACK);
                    gg.setFont(new Font("Calibri",Font.BOLD,20));
                    gg.drawString(Integer.toString(i),x-4, y+6);
                }
            }
        };
        frame.setContentPane(contentPane);
    }
}