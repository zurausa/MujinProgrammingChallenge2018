import java.io.PrintWriter;


public class Main3 {
	private static  boolean[][] map;
	private static int row;
	private static int col;
	private static char ch = ".".charAt(0);

	public static void main(String[] args){
		FastScanner sc = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		row = sc.nextInt();
		col = sc.nextInt();
		long count = 0;
		map = new boolean[row+2][col+2];
		char[] line = null;
		for(int i = 0; i< row+2;i++){
			map[i][0] = false;
			map[i][col+1] = false;
		}
		for(int i=0;i<col+2;i++){
			map[0][i] = false;
			map[row+1][i] = false;
		}
		for (int i = 1; i < row+1; i++) {
			line = sc.next().toCharArray();
			for (int j = 1; j < col+1; j++) {
				map[i][j] = line[j-1] == ch;
			}
		}
		/**
		 * 指定箇所を交差点とした時の取ることができる終着点を計算
		 **/
		int[][] left = new int[row+2][col+2];
		int[][] right = new int[row+2][col+2];
		int[][] up = new int[row+2][col+2];
		int[][] down = new int[row+2][col+2];
		for(int i=1;i<row+1;i++) {
			for(int j=1;j<col+1;j++) {
				if (!map[i][j]) continue;
				left[i][j] = left[i][j-1] + 1;
				up[i][j] = up[i-1][j] + 1;
			}
		}
		for(int i=row;i>=1;i--) {
			for(int j=col;j>=1;j--) {
				if (!map[i][j]) continue;
				down[i][j] = down[i+1][j] + 1;
				right[i][j] = right[i][j+1] + 1;
			}
		}
		for (int i = 1; i < row+1; i++) {
			for (int j = 1; j < col+1; j++) {
				if (!map[i][j]) {
					continue;
				} else {
					count += (left[i][j] + right[i][j] -2 ) * ( up[i][j] + down[i][j] - 2);
				}
			}
		}
		out.println(count);
		out.flush();
	}
}

