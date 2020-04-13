package npuzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class Search {

	public static void main(String[] args) {
		new Search();
	}

	Random rand = new Random();

	public Search() {
		PriorityQueue<State> Open = new PriorityQueue(100000,new Comparator<State>() {
			public int compare(State o1, State o2) {
				return (o1.g+o1.h)-(o2.g+o2.h);
			}
		});
		
		Map<String,State> Closed = new HashMap<>();
		State O = null;
		State Goal = new State();
		for (int y = 0; y < 5; y++)
			for (int x = 0; x < 5; x++)
				Goal.a[y][x] = (x + y * 5 + 1) % 25;

		State Start = Goal.Clone();
		for (int i = 0; i < 100; i++) {
			State tmp = (new Operator(rand.nextInt(4))).move(Start);
			if (tmp != null)
				Start = tmp;
		}
		boolean OK = false;
		System.out.println("TRANG THAI GOAL:");
		Goal.Print();
		System.out.println("TRANG THAI START:");

		Start.Print();
		
		int count=0;
		int mem=0;
	
		//1.
		Start.g = 0;
		Start.h = Distance(Start,Goal);
		Open.add(Start);
		Closed.put(Start.key(), Start);
		//2.6.
		while(true){
			if (Open.size()==0) break;
			//3.
			O = Open.remove();
			//4.
			if (Equal(O,Goal)){
				OK = true;
				break;
			}
			
			//5.
			for (int i=0;i<4;i++){
				Operator op = new Operator(i);
				State Child = op.move(O);
				if (Child==null) continue;
				if (In(Child,Closed)) continue;
				Child.g = O.g+1;
				Child.h = Distance(Child, Goal);
				Open.add(Child);
				Closed.put(Child.key(), Child);
				Child.Cha = O;
				Child.Me = op;
			}
		}
		
		System.out.println(OK);
		System.out.println("Tim kiem thanh cong");
		PrintResult(O);
	}
	private static void PrintResult(State o) {
		if (o.Cha!=null) {
			PrintResult(o.Cha);
			switch (o.Me.i) {
			case 0: System.out.println("Up");break;
			case 1: System.out.println("Down");break;
			case 2: System.out.println("Left");break;
			case 3: System.out.println("Right");break;
			}
		}
		o.Print();
	}
	private int Distance(State s, State g) {
		int count=0;
		for (int j=0;j<5;j++)
			for (int i=0;i<5;i++){
				if (s.a[j][i]==0) continue;
				int x = (s.a[j][i] - 1)%5;
				int y = (s.a[j][i] - 1)/5;
				count+=Math.abs(x-i)+Math.abs(y-j);
			}
		return count;
	}


	private boolean In(State c, Map<String,State> list) {
		State s = list.get(c.key());
		if (s==null) return false;
		return true;
	}

	private boolean Equal(State o, State goal) {
		for (int y = 0; y < 5; y++)
			for (int x = 0; x < 5; x++)
				if (o.a[y][x]!=goal.a[y][x]) return false;
		return true;
	}
}












