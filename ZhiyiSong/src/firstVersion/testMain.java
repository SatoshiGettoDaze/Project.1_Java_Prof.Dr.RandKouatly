package firstVersion;

import java.util.Scanner;

public class testMain {

	public static void main(String[] args) {			
		/*
		 * initialization of the order
		 */
		System.out.println("Please Enter the Order of the Products:");
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Laptop = ");
		int laptop = sc1.nextInt();		
		System.out.print("Mouse = ");
		int mouse = sc1.nextInt();	
		System.out.print("Desktop = ");
		int desktop = sc1.nextInt();
		System.out.print("LCD = ");
		int lcd = sc1.nextInt();
		
		int[] order = {laptop,mouse,desktop,lcd};
		
		double totalVolume; //The unit of calculation is m
		double totalWeight; //The unit of calculation is kg
		final double volumeOfSmallContainer = 2.59*2.43*6.06;//The unit is m^3
		final double volumeOfBigContainer = 2.59*2.43*12.01;//The unit is m^3
		double cost1;
		double cost2 = 999999999;
		double amountOfBig = 0;
		double amountOfSmall = 0;
		
		
		/*
		 * The volume and the weight of the products and the container
		 */
		totalVolume = laptop*0.6*0.5*0.5 + mouse*0.3*0.3*0.2 + desktop*1*1.5*0.5 + lcd*1.2*1.4*0.8;
		System.out.println("The total Volume of the Shipment: " + totalVolume + " m^3");
		
		totalWeight = laptop*6.5 + mouse*0.2 + desktop*20 + lcd*2.6;
		System.out.println("The total Weighth of the Shipment: " + totalWeight + " kg");

		double maxLoadOfSmallContainer = (totalWeight/totalVolume)*volumeOfSmallContainer;
		
		System.out.println("The Volume Of Small Container: " + volumeOfSmallContainer + " m^3");
		System.out.println("The max Load Of Small Container: " + maxLoadOfSmallContainer + " kg");
		System.out.println("The Volume Of Big Container: " + volumeOfBigContainer + " m^3");
		System.out.println("------------------------------------------------");
		
		/*
		 * Select the type of container
		 */
		//only select big container
		amountOfBig = Math.ceil(totalVolume/volumeOfBigContainer);
		cost1 = amountOfBig*1800;
		
		//Reduce a large container and add ? small container,until the price is lowest
		double amountTemp = 0;
		while (cost1 < cost2)
		{
			amountTemp = amountOfSmall;
			cost2 = cost1;
			amountOfBig--;
			if(amountOfBig < 0) {
				break;
			}
			double cubicMetersPerKilogram = totalVolume/totalWeight;
			//double weightOfRemainingProduct = (totalVolume-amountOfBig*volumeOfBigContainer)/cubicMetersPerKilogram;
			double volumeOfRemainingProduct = totalVolume - amountOfBig*volumeOfBigContainer;
			amountOfSmall = Math.ceil(volumeOfRemainingProduct/volumeOfSmallContainer);
			
			if((volumeOfRemainingProduct/cubicMetersPerKilogram) <= 500){
				cost1 = amountOfBig*1800 + amountOfSmall*1000;
			}
			if((volumeOfRemainingProduct/cubicMetersPerKilogram) > 500) {
				cost1 = amountOfBig*1800 + amountOfSmall*1200;
			}
			
		}
		
		
		/*
		 * print results
		 */
		amountOfBig++;		
		System.out.println("The optimal solution is " + cost2 + " euro");
		System.out.println("The amount of big container:" + amountOfBig);
		System.out.println("The amount of small container:" + amountTemp);
			

	}

}
