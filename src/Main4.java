import java.io.PrintWriter;

public class Main4{
	private static  int[][] result  = new int[1000][1000];

	private static int rev(int x){
		if(x<10) return x;
		int div10 = x/10;
		if(x<100){
			return (x-(10*div10))*10+div10;
		}
		int div100 = x/100;
		return (x-(10*div10))*100 + (div10 - 10*div100)*10 + div100;
	}

	public static void main(String[] args){
		FastScanner sc = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int N = sc.nextInt();
		int M = sc.nextInt();
		out.println(task(N,M));
		out.flush();
	}

	private static void init(){
		for(int i=0;i<1000;i++){
			result[i][0] = 2;
			result[0][i] = 2;
		}
	}

	private static long task(int N,int M){
		init();
		long count = 0;
		for(int i=1;i<=N;i++){
			for(int j=1;j<=M;j++){
				if(result[i][j]==1){
					continue;
				}else if(result[i][j] == 2){
					count++;
				}else{
					result[i][j] = rec(i, j);
					if(result[i][j] == 2){
						count++;
					}
				}
			}
		}
		return N*M-count;
	}

	private static int rec(int x,int y){
		if(x==0 || y== 0)return result[x][y] = 2;
		if(result[x][y]==1)return 1;

		if(result[x][y]!=2) result[x][y]=1;

		if(x<y)x = rev(x);
		else y = rev(y);


		if(x<y)y=y-x;
		else x = x-y;

		return result[x][y] = rec(x, y);
	}
}
