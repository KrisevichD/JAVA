package Lab1;

public class Test {

    private static void testFromKolikWhiteboard() {
        int[][] arr = new int[][]{
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 1},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0},
        };

        int[] in = {1, 2};
        int[] out = {5, 6};

        var counter = new MazePathCounter(arr, in, out);
        System.out.println(counter.calcPeople());
    }


    public static void main(String[] args) {
        testFromKolikWhiteboard();
    }


}
