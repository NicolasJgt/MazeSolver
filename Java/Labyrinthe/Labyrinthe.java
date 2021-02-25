import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Labyrinthe {
	

	private int[][] lab;
	private Set<int[][]> solutions; 
	public Labyrinthe(int[][] lab_) {
		this.lab = lab_;	  
		solutions =  new HashSet<>();
		
	}
	
	
	public Set<int[][]> cheminSortieAPartirDe(int x_, int y_) {
		return cheminSortieAPartirDe(x_,y_,this.lab);
	}
	public Set<int[][]> cheminSortieAPartirDe(int x_, int y_, int[][]tmp_) {

		Set<int[]> trouveDeplacement =  new HashSet<>();
		trouveDeplacement.add(new int[] {x_ + 1 ,y_ });
		trouveDeplacement.add(new int[] {x_ - 1,y_});
		trouveDeplacement.add(new int[] {x_ ,y_ + 1});
		trouveDeplacement.add(new int[] {x_ , y_ - 1});
	
		trouveDeplacement = trouveDeplacement.stream()
			.filter(pos -> pos[0] >= 0 && pos[1] >= 0 && pos[0] < tmp_.length && pos[1] < tmp_[0].length && (tmp_[pos[0]][pos[1]] == 0 || tmp_[pos[0]][pos[1]] == 9))
			.collect(Collectors.toSet());
		
		switch(tmp_[x_][y_]) {
		
			case 9 : 
				solutions.add(tmp_);
				break;
			case 0 :
				int[][] tmp = Arrays.stream(tmp_).map(int[]::clone).toArray(int[][]::new);
				tmp[x_][y_] = 2;
				trouveDeplacement.forEach(pos -> cheminSortieAPartirDe(pos[0],pos[1],tmp));
				break;
					
			

		}
		return solutions;
	}
	
	@Override
	public String toString() {
		String res = "";
		for (int[] x : this.lab) {
			for (int y : x) {
				switch (y) {
					case 0 : 
						res += Ansi.B_BLACK + "  " + Ansi.RESET; 
						break;
					case 1 : 
						res += Ansi.B_YELLOW + "  " + Ansi.RESET; 
						break;
					case 9: 
						res += Ansi.B_RED + "  " + Ansi.RESET;
						break;
					case 2 : 
						res += Ansi.B_GREEN + "  " + Ansi.RESET;
						break;
			}
		}
		res += "\n";
	}
	return res;

	}
	


}


 
