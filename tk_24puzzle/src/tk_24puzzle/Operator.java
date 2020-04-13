package tk_24puzzle;

public class Operator {
	//0: Up
	//1: Down
	//2: Left
	//3: Right
	int i;
	
	public Operator(int i) {
		this.i = i;
	}

	State Move(State s) {
		switch (i) {
		case 0: return Up(s);
		case 1: return Down(s);
		case 2: return Left(s);
		case 3: return Right(s);
		}
		return null;
	}

	private State Right(State s) {
		MyPoint pzero = GetZero(s); 
		if (pzero.j>0) {
			State ns = s.Clone();
			ns.d[pzero.i][pzero.j] = ns.d[pzero.i][pzero.j-1];
			ns.d[pzero.i][pzero.j-1] = 0;
			return ns;
		}
		return null;
	}

	private State Left(State s) {
		MyPoint pzero = GetZero(s); 
		if (pzero.j<4) {
			State ns = s.Clone();
			ns.d[pzero.i][pzero.j] = ns.d[pzero.i][pzero.j+1];
			ns.d[pzero.i][pzero.j+1] = 0;
			return ns;
		}
		return null;
	}

	private State Down(State s) {
		MyPoint pzero = GetZero(s); 
		if (pzero.i>0) {
			State ns = s.Clone();
			ns.d[pzero.i][pzero.j] = ns.d[pzero.i-1][pzero.j];
			ns.d[pzero.i-1][pzero.j] = 0;
			return ns;
		}
		return null;
	}

	private State Up(State s) {
		MyPoint pzero = GetZero(s); 
		if (pzero.i<4) {
			State ns = s.Clone();
			ns.d[pzero.i][pzero.j] = ns.d[pzero.i+1][pzero.j];
			ns.d[pzero.i+1][pzero.j] = 0;
			return ns;
		}
		return null;
	}

	private MyPoint GetZero(State s) {
		for (int i=0;i<5;i++)
			for (int j=0;j<5;j++) {
				if (s.d[i][j]==0)
					return new MyPoint(i,j);
			}
		return null;
	}

}

class MyPoint{
	int i,j;
	public MyPoint(int i, int j) {
		this.i = i;
		this.j = j;
	}
}