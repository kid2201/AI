package tk_24puzzle;

public class State {
	int g;
	int h;
	State cha;
	Operator me;
	int d[][] = new int[5][5]; 
	public State() {
		for (int i=0;i<5;i++)
			for (int j=0;j<5;j++)
				d[i][j] = (i*5+j+1)%25;
	}
	public State Clone() {
		State n = new State();
		for (int i=0;i<5;i++)
			for (int j=0;j<5;j++)
				n.d[i][j] = this.d[i][j];
		return n;
	}
	
	public void Print() {
		
		for (int i=0;i<5;i++) {
			for (int j=0;j<5;j++) {
				System.out.print(d[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("------------");
	}
	public String getkey() {
		String s = "";
		for (int i=0;i<5;i++) 
			for (int j=0;j<5;j++) 
				s+=d[i][j];

		return s;
	}

}

