import java.util.ArrayList;
import java.util.Random;

public class DNA {
	public ArrayList<Character> genes = new ArrayList<>();
	int size;
	double fitness;
	
	public DNA(int size){
		this.size = size;
	}
	
	public void createRandomGenes(){
		for (int i = 0; i < size; i++) {
			genes.add(getRandomChar());
		}
	}

	private Character getRandomChar() {
		Random rand = new Random();
		//get random number between 32 and 126
		int n = 32 + rand.nextInt(95);
		return (char) n;
	}

	public double calcFitness(String target) {
		int score = 0;
		for(int i = 0; i < target.length(); i++){
			if(genes.get(i) == target.charAt(i)){
				score++;
			}
		}
		fitness = ((double) score/target.length());
		return fitness;
	}

	public DNA crossover(DNA partner) {
		DNA child = new DNA(size);
		int midpoint = Math.round((float) size/2);
		for(int i = 0; i < size; i++){
			if(i < midpoint){
				child.genes.add(genes.get(i));
			}else{
				child.genes.add(partner.genes.get(i));
			}
		}
		return child;
	}

	public void makeRandomMutations(double mutationRate) {
		for(int i = 0; i < size; i++){
			Random rand = new Random();
			if(rand.nextDouble() < mutationRate){
				genes.set(i, getRandomChar());
			}
		}		
	}
}
