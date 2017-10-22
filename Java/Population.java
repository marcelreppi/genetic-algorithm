import java.util.ArrayList;
import java.util.Random;

public class Population {
	public ArrayList<DNA> members = new ArrayList<>();
	public ArrayList<DNA> matingPool = new ArrayList<>();
	public int pop_size;
	public int dna_size;
	public DNA bestDNA = null;
	public double sumFitness;
	public boolean targetReached = false;
	public double optimalFitness = 1.0;
	
	public int id = nextID++;
	public static int nextID = 0;
	
	
	public Population(int pop_size, int dna_size) {
		this.pop_size = pop_size;
		this.dna_size = dna_size;
		
	}
	
	public void initializePopulation() {
		for (int i = 0; i < pop_size; i++) {
			DNA newMember = new DNA(dna_size);
			newMember.createRandomGenes();
			members.add(newMember);
		}
	}
	
	public void printPopulation(){
		for (DNA dna : members) {
			for (Character c : dna.genes) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public void evaluateFitness(String target) {
		double maxFitness = 0.0;
		for (DNA dna : members) {
			if(dna.calcFitness(target) == optimalFitness){
				targetReached = true;
			}
			if(dna.fitness > maxFitness){
				maxFitness = dna.fitness;
				bestDNA = dna;
			}
			sumFitness += dna.fitness;
		}
	}

	public Population reproduce(double mutationRate) {
		Population nextPopulation = new Population(pop_size, dna_size);
		Random rand = new Random();
		for(int i = 0; i < pop_size; i+=2){
			
			DNA parentA = null;
			while(true){
				parentA = members.get(rand.nextInt(pop_size));
				double x = rand.nextDouble() * sumFitness;
				if(x < parentA.fitness){
					break;
				}
			}
			
			DNA parentB = null;
			while(true){
				parentB = members.get(rand.nextInt(pop_size));
				double x = rand.nextDouble() * sumFitness;
				if(x < parentB.fitness){
					break;
				}
			}
			
			DNA childA = parentA.crossover(parentB);
			DNA childB = parentB.crossover(parentA);
			
			childA.makeRandomMutations(mutationRate);
			childB.makeRandomMutations(mutationRate);
			
			nextPopulation.members.add(childA);
			nextPopulation.members.add(childB);
		}
		return nextPopulation;
	}
}
