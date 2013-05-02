import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

	public static int[][] scores;
	int totalPoints, totalGoals;
	public static int count;
	public static void main(String[] args)
	{
		ArrayList<String> results = readResults();
		int games = 10;
		int winA = 0, winB = 0, draw = 0;
		scores = new int[games][4];
		
		char[] ch = {'A', 'B', 'C', 'D'};
		for(int i = 0; i < games; i++)
		{	
			System.out.println(readReport("C:\\Users\\Liam\\workspace\\HurlingSim\\report" + i + ".txt"));	
			count++;
		}
		
		int[] total = new int[4];
		for(int i = 0; i < count; i++)
		{
			total[0] += scores[i][0];
			total[1] += scores[i][1];
			total[2] += scores[i][2];
			total[3] += scores[i][3];
			
			if(((scores[i][0] * 3) + scores[i][1] > ((scores[i][2] * 3) + scores[i][3])))
			{
				winA++;
			}
			else if((scores[i][0] * 3) + scores[i][1] < ((scores[i][2] * 3) + scores[i][3]))
			{
				winB++;
			}
			else
			{
				draw++;
			}
		}
		
		System.out.println("TOTAL: " + total[0] + " " + total[1] +  " " + total[2] + " " + total[3]);
		System.out.println("AVERAGE: " + ((double)total[0]/(double)count) + " " + ((double)total[1]/(double)count) +  " " 
							+ ((double)total[2]/(double)count) + " " + ((double)total[3]/(double)count));
		System.out.println("WINA:" + winA + " WINB: " + winB + " DRAW: " + draw);
		results.add("teamOne," + ((double)total[0]/(double)count) + "," + ((double)total[1]/(double)count) + 
					",teamTwo," + ((double)total[2]/(double)count) + "," + ((double)total[3]/(double)count) + 
					"," + winA + "," + winB + "," + draw);
		writeResult(results);
	}
	
	
	public static String readReport(String fileName)
	{
		int FFscore = 0, HFscore = 0, MFscore = 0, HBscore = 0, FBscore = 0;
		String report = "";
		String scoreLine;
		String score[] = new String[4];
		ArrayList<String> lines = new ArrayList<String>();
		try{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		
		while(br.ready())
		{
			line = br.readLine() + "\n";
			lines.add(line);
			if(line.contains("FF"))
			{
				FFscore++;
			}
			else if(line.contains("HF"))
			{
				HFscore++;
			}
			else if(line.contains("MF"))
			{
				MFscore++;
			}
			else if(line.contains("HB"))
			{
				HBscore++;
			}
			else if(line.contains("FB"))
			{
				FBscore++;
			}
		}
		br.close();
		} 
		catch(IOException e)
		{
			System.out.println("Couldnt Read file");
		}
		scoreLine = lines.get(lines.size() - 1).replaceAll(" TO ", "-");
		score = scoreLine.split("-");
		
		
		scores[count][0] = Integer.parseInt(score[0]);
		
		scores[count][1] = Integer.parseInt(score[1]);
		
		scores[count][2] = Integer.parseInt(score[2]);
		
		score[3] = score[3].replaceAll(" \\\n", "");
		scores[count][3] = Integer.parseInt(score[3]);
		
		report = "FF: " + FFscore + " HF: " + HFscore + " MF: " + MFscore + " HB: " + HBscore + " FB: " + FBscore + " \n" + lines.get(lines.size() - 1);
		return report;
	}
	
	public static void writeResult(ArrayList<String> list)
	{
		try
		{
			FileWriter fw = new FileWriter("result.csv");
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(String s: list)
			{	
				bw.write(s);
				bw.newLine();
			}
			
			bw.close();
		}
		catch(IOException e)
		{
			System.out.println("Couldnt Write file");
		}
	}
	
	public static ArrayList<String> readResults()
	{
		ArrayList<String> results = new ArrayList<String>();
		try{
		FileReader fr = new FileReader("result.csv");
		BufferedReader br = new BufferedReader(fr);
		

		while(br.ready())
		{
			results.add(br.readLine());
		}
		br.close();
		} 
		catch(IOException e)
		{
			System.out.println("Couldnt Read file");
		}
		
		return results;
	}
	
}
