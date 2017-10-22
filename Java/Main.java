
public class Main {
	public static void main(String[] args) {
		String targetPhrase = "Was geht ab?";
		int populationSize = 1000;
		double mutationRate = 0.01;
		
		//setup
		//create empty population
		Population population = new Population(populationSize, targetPhrase.length());
		
		//fill population with random DNA objects
		population.initializePopulation();
		population.printPopulation();
		
		while(true) {
			
//			System.out.println(population.id + ". population\n");
//			population.printPopulation();
//			System.out.println();
			
			//fitness evaluation and creation of mating pool
			population.evaluateFitness(targetPhrase);
			System.out.println(population.bestDNA.fitness);
			if(population.targetReached){
				System.out.println(population.id + ". population\n");
				population.printPopulation();
				System.out.println(population.bestDNA.genes.toString());
				break;
			}

			
//			System.out.println(population.matingPool.size());
			
			population = population.reproduce(mutationRate);
		}
		
	}
	
}
