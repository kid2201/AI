import java.util.Scanner;

public class Caro {
	public static void main(String[] args) {
		new Caro();
	}

	public Caro() {
		Scanner sc = new Scanner(System.in);
		int player = 1;
		int turn = 0;
		int d = 5;
		State s = new State();
		s.Print();
		while (true) {
			if (turn % 2 + 1 == player) {
				// User
				State child = null;
				while (child == null) {
					System.out.println("Please input coordinate!");
					int x = sc.nextInt();
					int y = sc.nextInt();
					child = new Operator(x, y).Move(s);
				}
				s = child;
				if (Win(s)) {
					s.Print();
					System.out.println("Player Win!");
					break;
				}
			} else {
				// AI
				System.out.println("AI turn!!");
				int min = Integer.MAX_VALUE;
				State minchild = null;
				for (int i = 0; i < s.N; i++)
					for (int j = 0; j < s.N; j++) {
						State child = new Operator(i, j).Move(s);
						if (child == null)
							continue;
						int tmp = MiniMax(child, 1, true);
						if (min>tmp){
							min=tmp;
							minchild = child;
						}
					}
				s = minchild;
				if (Win(s)) {
					s.Print();
					System.out.println("AI Win!");
					break;
				}
			}
			s.Print();
			if (isEndNode(s)){
				System.out.println("Draw!");
				break;
			}
			turn++;
		}
	}
	
	private boolean Win(State s) {
		for (int i = 0; i < s.N; i++) {
			if (s.a[i][0] != 0 && s.a[i][0] == s.a[i][1] && s.a[i][0] == s.a[i][2])
				return true;
			if (s.a[0][i] != 0 && s.a[0][i] == s.a[1][i] && s.a[0][i] == s.a[2][i])
				return true;
		}
		if (s.a[0][0] != 0 && s.a[0][0] == s.a[1][1] && s.a[0][0] == s.a[2][2])
			return true;
		if (s.a[0][2] != 0 && s.a[0][2] == s.a[1][1] && s.a[0][2] == s.a[2][0])
			return true;
		return false;
	}
	
	private boolean isEndNode(State s) {
		if (Win(s)) return true;

		for (int i = 0; i < s.N; i++)
			for (int j = 0; j < s.N; j++)
				if (s.a[i][j] == 0)
					return false;
		return true;
	}
	
	private int Value(State s) {
		if (Win(s))
		{
			if (MyTurn(s)) return 1;
			else return -1;
		}
		return 0;
	}

	private boolean MyTurn(State s) {
		int count=0;;
		for (int i=0;i<s.N;i++)
			for (int j=0;j<s.N;j++){
				if (s.a[i][j]==0) count++;
			}
		if (count%2==0) return true;
		return false;
	}
	
	private int MiniMax(State s, int d, boolean MP) {
		if (isEndNode(s) || d == 0) {
			return Value(s);
		}
		if (MP) {
			int Max = -Integer.MAX_VALUE;
			for (int i = 0; i < s.N; i++)
				for (int j = 0; j < s.N; j++) {
					State child = new Operator(i, j).Move(s);
					if (child == null)
						continue;
					int tmp = MiniMax(child, d+1,false);
					if (Max < tmp) Max = tmp;
				}
			return Max;
		}
		else {
			int Min = Integer.MAX_VALUE;
			for (int i = 0; i < s.N; i++)
				for (int j = 0; j < s.N; j++) {
					State child = new Operator(i, j).Move(s);
					if (child == null)
						continue;
					int tmp = MiniMax(child, d+1,true);
					if (Min > tmp) Min = tmp;
				}
			return Min;
		}
	}
		
}
