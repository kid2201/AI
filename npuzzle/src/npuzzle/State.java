package npuzzle;

public class State {
	int g,h;
	State Cha;
	Operator Me;
	int N = 5;
	int a[][] = new int[N][N];

	public State Clone() {
		State s = new State();
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++)
				s.a[y][x] = this.a[y][x];
		return s;
	}

	public void Print(){
		for (int y = 0; y < N; y++){
			for (int x = 0; x < N; x++){
				System.out.print(a[y][x]+" ");
			}
			System.out.println();
		}
		System.out.println(".............");
	}
	
	public String key(){
		String st = "";
		for (int y = 0; y < N; y++){
			for (int x = 0; x < N; x++){
				st=st+a[y][x];
			}
		}
		return st;
	}
}

