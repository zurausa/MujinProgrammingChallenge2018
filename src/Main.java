import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main {
	private static  boolean[][] map;
	private static int row;
	private static int col;
	private static char ch = ".".charAt(0);

	public static void main(String[] args){
		FastScanner sc = new FastScanner();
		row = sc.nextInt();
		col = sc.nextInt();
		map = new boolean[row][col];
		for(int i=0;i<row;i++){
			String line = sc.next();
			for(int j=0;j<col;j++){
				if(line.charAt(j) == ch){
					map[i][j] = true;
				} else {
					map[i][j] = false;
				}
			}
		}
		int count = 0;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(!map[i][j]){
					continue;
				}else{
//					//to top
//					count += countToTop(i, j);
//					//to right
//					count += countToRight(i, j);
//					//to under
//					count += countToBottom(i, j);
//					//to left
//					count += countToLeft(i, j);
					count += checkBlank(i,j);
				}
			}
		}
		System.out.println(count);
	}

	/**
	 * 指定箇所を交差点とした時の取ることができる終着点を計算
	 **/
	private static int checkBlank(int x,int y){
		int height = 0;
		int width = 0;
		//check to up
		for(int i = x-1; i >= 0; i-- ){
			if(map[i][y]){
				height++;
			}else{
				break;
			}
		}
		//check to down;
		for(int i = x+1; i < row; i++ ){
			if(map[i][y]){
				height++;
			}else{
				break;
			}
		}
		// check to left;
		for (int i = y-1; i >= 0; i--) {
			if (map[x][i]) {
				width++;
			} else {
				break;
			}
		}
		// check to left;
		for (int i = y+1; i < col; i++) {
			if (map[x][i]) {
				width++;
			} else {
				break;
			}
		}
		return height * width;
	}


//	private static int countToTop(int i,int j){
//		int count = 0;
//		int positionY = i-1;
//		int positionX;
//		if(j+1<col){
//			while(positionY >= 0){
//				positionX = j+1;
//				if(map[positionY][j]){
//					while(positionX < col){
//						if(map[positionY][positionX]){
//							count++;
//							positionX++;
//						}else{
//							break;
//						}
//					}
//				}else{
//					break;
//				}
//				positionY--;
//			}
//		}
//		return count;
//	}
//
//	private static int countToRight(int i,int j){
//		int count = 0;
//		int positionY;
//		int positionX = j+1;
//		if(i+1<row){
//			while(positionX < col){
//				positionY = i+1;
//				if(map[i][positionX]){
//					while(positionY < row){
//						if(map[positionY][positionX]){
//							count++;
//							positionY++;
//						}else{
//							break;
//						}
//					}
//				}else{
//					break;
//				}
//				positionX++;
//			}
//		}
//		return count;
//	}
//
//	private static int countToBottom(int i,int j){
//		int count = 0;
//		int positionY = i+1;
//		int positionX;
//		if(j>0){
//			while(positionY < row){
//				positionX = j - 1;
//				if(map[positionY][j]){
//					while(positionX >= 0){
//						if(map[positionY][positionX]){
//							count++;
//							positionX--;
//						}else{
//							break;
//						}
//					}
//				}else{
//					break;
//				}
//				positionY++;
//			}
//		}
//		return count;
//	}
//
//	private static int countToLeft(int i,int j){
//		int count = 0;
//		int positionY;
//		int positionX = j-1;
//		if(i>0){
//			while(positionX >= 0){
//				positionY = i -1;
//				if(map[i][positionX]){
//					while(positionY >= 0){
//						if(map[positionY][positionX]){
//							positionY--;
//							count++;
//						}else{
//							break;
//						}
//					}
//				}else{
//					break;
//				}
//				positionX--;
//			}
//		}
//		return count;
//	}


}
class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble() { return Double.parseDouble(next());}
}