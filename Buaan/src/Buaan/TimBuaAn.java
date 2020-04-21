package Buaan;

import java.util.Arrays;
import java.util.Random;

public class TimBuaAn {
	public String [] FoodName={"Tao","Cherry","Cam","Quyt","Duahau","Chanh","Kiwi","Nho",
								"DuaLuoi","DaoTien","Chuoi","DauTay","Thom","Le","SuaBo","CaChua",
								"CaRot","CaTim","HanhTay","Toi","KhoaiTay","Nam","BanhMy","PhoMai",
								"Bia","Ngheu","Cua","Ca","Tom","Ga","Bo","Trung"};
	public int[][] Food={	{0,0,1,1,0,0,0,0,0,0},{0,0,1,0,0,0,1,1,0,0},{0,0,1,0,0,0,0,0,1,1},{0,0,1,1,1,0,0,0,0,1},
							{0,0,1,0,0,0,0,1,0,0},{0,0,0,1,0,0,1,0,0,1},{0,0,1,1,1,0,0,1,0,1},{0,0,1,0,0,1,0,0,0,0},
							{0,0,0,0,0,1,1,0,1,0},{0,0,1,1,0,1,0,0,0,0},{0,0,1,0,0,1,0,0,0,1},{0,0,1,1,0,0,1,0,0,1},
							{0,0,1,0,0,0,0,0,1,0},{0,0,1,0,0,0,0,0,0,0},{1,1,1,0,1,1,0,1,0,0},{0,0,1,0,0,1,0,1,0,1},
							{0,0,1,1,0,0,1,1,0,0},{0,0,0,0,0,0,0,1,0,0},{0,0,1,0,1,1,0,0,0,0},{1,0,0,1,0,0,1,0,1,1},
							{0,0,1,0,0,0,0,0,0,1},{0,0,1,1,0,0,0,0,0,0},{1,1,1,0,0,0,0,0,0,0},{1,1,0,0,1,1,0,0,0,0},
							{0,0,1,0,0,0,0,0,0,0},{1,0,0,0,1,1,0,0,1,0},{1,0,0,0,1,1,0,0,0,0},{1,1,0,0,0,0,0,0,1,0},
							{1,0,0,0,1,0,1,0,0,0},{1,0,0,0,0,1,0,1,0,0},{1,1,0,0,0,1,0,0,0,0},{1,1,0,0,1,1,0,1,0,0}};
	
	public int[] FoodValue={31,66,55,70,34,50,106,38,
							62,49,55,82,33,13,118,54,
							83,15,56,123,32,40,52,63,
							18,74,49,62,64,55,70,100};
	
	public static void main(String[] args) {
		new TimBuaAn();
	}
	
	public TimBuaAn() {
		KhoiTao();
		for (int i=0; i<100; i++) {
			Danhgia();
			PrintBest();
			LuaChon();
			LaiGhep();
			DotBien();
		}
	}
	
	private void PrintBest() {
		// TODO Auto-generated method stub
		int [] f1 = f.clone();
		Arrays.sort(f1);
		int best = f1[0];
		for (int i = 0; i < N; i++)
			if (f[i] == best) {
				
				int tien = 0;
				for (int j = 0; j < 32; j++) 
					tien +=nghiem[i][j]*FoodValue[j];
				
				int dinhduong = 0;
				for (int k = 0; k < 10; k++)
					for (int j = 0; j < 32; j++)
						if (nghiem[i][j]==1 && Food[j][k]==1) {
							dinhduong++;
							break;
						}
				for (int j = 0; j < 32; j++)
					if (nghiem[i][j]==1) System.out.print(FoodName[j]+", ");
				System.out.println(tien+","+dinhduong);
				break;
			}
	}

	int N = 100; // so nghiem
	Random rand = new Random();
	int [][] nghiem = new int[N][32];
	int [] f = new int[N];
	
	private void KhoiTao() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < 32; j++) {
				nghiem[i][j] = rand.nextInt(2);
			}
	}
	
	private void Danhgia() {
		// TODO Auto-generated method stub
		// danh gia mon thu i qua 2 tieu chi
		for (int i = 0; i < N; i++) {
			int tien = 0;
			for (int j = 0; j < 32; j++) 
				tien +=nghiem[i][j]*FoodValue[j];
			
			int dinhduong = 0;
			for (int k = 0; k < 10; k++)
				for (int j = 0; j < 32; j++)
					if (nghiem[i][j]==1 && Food[j][k]==1) {
						dinhduong++;
						break;
					}
			
			f[i] = tien - 40*dinhduong;
			
		}		
	}
	
	private void LuaChon() {
		// TODO Auto-generated method stub
		int [] f1 = f.clone();
		Arrays.sort(f1);
		int nguong = f1[N*80/100];
		for (int i = 0; i < N; i++)
			if (f[i] > nguong) {
				nghiem[i] = nghiem[rand.nextInt(N)].clone();
			}
	}
	
	private void LaiGhep() {
		// TODO Auto-generated method stub
		for (int i =0; i < N*30/100; i++) {
			int indexcha = rand.nextInt(N);
			int indexme = rand.nextInt(N);
			
			for (int j = 0; j < 32; j++) {
				if (rand.nextBoolean()) {
					int tmp = nghiem[indexcha][j];
					nghiem[indexcha][j] = nghiem[indexme][j];
					nghiem[indexme][j] = tmp;
				}
			}
		}
	}
	
	private void DotBien() {
		// TODO Auto-generated method stub
		int index = rand.nextInt(N);
		int k = rand.nextInt(32);
		nghiem[index][k] = 1 - nghiem[index][k];	
	}

	

	

	
	
	

}
