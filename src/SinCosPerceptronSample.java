import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.TransferFunctionType;

/**
 * This sample shows how to create, train, save and load simple Multi Layer
 * Perceptron
 */
public class SinCosPerceptronSample {
	
	public static void main(String[] args) throws FileNotFoundException {

		DataSet trainingSet = new DataSet(14, 1);
		
		try {
			FileWriter csvDataSet = new FileWriter("bankTREINAMENTO.csv");

			File file = new File("bank.csv");
			InputStream is = new FileInputStream(file);
			StringBuilder resultStringBuilder = new StringBuilder();
			int i = 0;
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
				String line;
				while ((line = br.readLine()) != null) {
					if(i == 0) {
						i++;
						continue;
					}
					Cliente cliente = new Cliente();
					String[] input = line.split(";");
					cliente.setAge(Integer.parseInt(input[0]));
					cliente.setJob(input[1]);
					cliente.setMarital(input[2]);
					cliente.setEducation(input[3]);
					cliente.setDefaultCredit(Boolean.parseBoolean(input[4]));
					cliente.setBalance(new Double(input[5]));
					cliente.setHousing(Boolean.parseBoolean(input[6]));
					cliente.setLoan(Boolean.parseBoolean(input[7]));
					cliente.setContact(input[8]);
					cliente.setDay(Integer.parseInt(input[9]));
					cliente.setMonth(input[10]);
					cliente.setDuration(Integer.parseInt(input[11]));
					cliente.setCampaign(Integer.parseInt(input[12]));
					cliente.setpDays(Integer.parseInt(input[13]));
					cliente.setPrevious(Integer.parseInt(input[14]));
					cliente.setpOutcome(input[15]);
					cliente.setY(input[16]);

					double[] in = {
							cliente.getAgeValue(),
							cliente.getJobValue(),
							cliente.getMaritalValue(),
							cliente.getEducationValue(),
							cliente.getDefaultCreditValue(),
							cliente.getBalanceValue(),
							cliente.getHousingValue(),
							cliente.getContactValue(),
							cliente.getDateValue(),
							cliente.getDurationValue(),
							cliente.getCampaignValue(),
							cliente.getpDaysValue(),
							cliente.getPreviousValue(),
							cliente.getpOutcomeValue()
					};
					double[] out = { cliente.getYValue() };
					trainingSet.addRow(new DataSetRow(in, out));
					i++;

					csvDataSet.write(""+line+";"+cliente.getYValue()+"\n");
					if(i==5) break;
				}
			}

			csvDataSet.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Vai Trainar");

		// create multi layer perceptron
		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 14, 4,10, 1);
		BackPropagation learningrules = new BackPropagation();
		learningrules.setMaxIterations(1000000); 
		learningrules.setMaxError(0.000000001);
		myMlPerceptron.learn(trainingSet, learningrules);
		// test perceptron
		System.out.println("Testing trained neural network");
		testNeuralNetwork(myMlPerceptron, trainingSet);
		// save trained neural network
		System.out.println("Save Perceptron");
		myMlPerceptron.save("myMlPerceptron.nnet");
		// load saved neural network
		
		System.out.println("Load Perceptron");
		NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("myMlPerceptron.nnet");
		// test loaded neural network
		
		DataSet testgSet = new DataSet(14, 1);
		File file = new File("bank.csv");
		InputStream is = new FileInputStream(file);
		int i = 0;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			FileWriter csvTESTE = new FileWriter("bankTESTE.csv");

			String line;
			while ((line = br.readLine()) != null) {
				if(i == 0) {
					i++;
					continue;
				}
				Cliente cliente = new Cliente();
				String[] input = line.split(";");
				cliente.setAge(Integer.parseInt(input[0]));
				cliente.setJob(input[1]);
				cliente.setMarital(input[2]);
				cliente.setEducation(input[3]);
				cliente.setDefaultCredit(Boolean.parseBoolean(input[4]));
				cliente.setBalance(new Double(input[5]));
				cliente.setHousing(Boolean.parseBoolean(input[6]));
				cliente.setLoan(Boolean.parseBoolean(input[7]));
				cliente.setContact(input[8]);
				cliente.setDay(Integer.parseInt(input[9]));
				cliente.setMonth(input[10]);
				cliente.setDuration(Integer.parseInt(input[11]));
				cliente.setCampaign(Integer.parseInt(input[12]));
				cliente.setpDays(Integer.parseInt(input[13]));
				cliente.setPrevious(Integer.parseInt(input[14]));
				cliente.setpOutcome(input[15]);
				cliente.setY(input[16]);

				double[] in = {
						cliente.getAgeValue(),
						cliente.getJobValue(),
						cliente.getMaritalValue(),
						cliente.getEducationValue(),
						cliente.getDefaultCreditValue(),
						cliente.getBalanceValue(),
						cliente.getHousingValue(),
						cliente.getContactValue(),
						cliente.getDateValue(),
						cliente.getDurationValue(),
						cliente.getCampaignValue(),
						cliente.getpDaysValue(),
						cliente.getPreviousValue(),
						cliente.getpOutcomeValue()
				};
				double[] out = { cliente.getYValue() };
				trainingSet.addRow(new DataSetRow(in, out));

				csvTESTE.write(""+line+";"+cliente.getYValue()+"\n");
				i++;
			}
			csvTESTE.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Testing loaded neural network");
		testNeuralNetworkScore(loadedMlPerceptron, testgSet,trainingSet);
	}

		public static void testNeuralNetwork(NeuralNetwork nnet, DataSet testSet) {
		for (DataSetRow dataRow : testSet.getRows()) {
			nnet.setInput(dataRow.getInput());
			nnet.calculate();
			double[] networkOutput = nnet.getOutput();
			System.out.print("Input: " + Arrays.toString(dataRow.getInput()));
			System.out.println(" Output: " + Arrays.toString(networkOutput));
		}
	}
	
	public static void testNeuralNetworkScore(NeuralNetwork nnet, DataSet testSet, DataSet trainingSet) {
		int i = 0;
		double somaerro = 0;
		
		BufferedImage img = new BufferedImage(800,500,BufferedImage.TYPE_INT_ARGB);
		Graphics2D dbg = (Graphics2D)img.getGraphics();
		
		dbg.setColor(Color.white);
		dbg.fillRect(0,0,800,500);
		
		float olposx1 = 10;
		float olposy1 = 250;
		
		float olposx2 = 10;
		float olposy2 = 250;
		
		for (DataSetRow dataRow : testSet.getRows()) {
			nnet.setInput(dataRow.getInput());
			nnet.calculate();
			double[] networkOutput = nnet.getOutput();
			int angulo = i*1;
			double sin = Math.sin(Math.toRadians(angulo));
			System.out.println(""+dataRow.getInput()[0]+";"+dataRow.getInput()[1]+";"+(i*5)+";"+networkOutput[0]+";"+sin);
			
			somaerro += Math.abs(networkOutput[0]-sin);
			i++;
			
			dbg.setColor(Color.BLACK);
			dbg.drawLine((int)olposx1, (int)olposy1, (int)(olposx1+2), (int)(250+sin*200));
			dbg.setColor(Color.RED);
			dbg.drawLine((int)olposx2, (int)olposy2, (int)(olposx2+2), (int)(250+networkOutput[0]*200));
			olposx1 = olposx1+2;
			olposy1 = (float)(250+sin*200);
			
			olposx2 = olposx2+2;
			olposy2 = (float)(250+networkOutput[0]*200);
			
			if(angulo%18==0) {
				dbg.setColor(Color.BLUE);
				dbg.drawLine((int)(olposx1+2), (int)(250+sin*200), (int)(olposx2+2), (int)(250+networkOutput[0]*200));
			}
		}
		for (DataSetRow dataRow : trainingSet.getRows()) {
			Double valorSaida = dataRow.getDesiredOutput()[0];
			Double valorEntrada = dataRow.getInput()[0];
			double angulo = valorEntrada*360;
			dbg.setColor(new Color(0,128,0));
			dbg.fillRect((int)(10+angulo*2)-1, (int)(250+valorSaida*200)-1,3,3);
		}
		
		
		System.out.println("Erro Total "+somaerro);
		try {
			ImageIO.write(img,"PNG",new File("SaidaSeno.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	

}