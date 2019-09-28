package com.simulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaRunBatFile {

	public void dataGeneration() {
		// TODO Auto-generated method stub
		
		/*Path currentRelativePath = Paths.get("C:\\Users\\Nk\\git\\WashTrade\\WashTrades\\src\\JavaRunBatFile.java");
		String s = currentRelativePath.toString();
		System.out.println("Current relative path is: " + s);
	Path path = FileSystems.getDefault().getPath(".");	C:\Users\Nk\git\WashTrade\WashTrades\Master\Master_run.bat
		*/
		/*(String path = "C:\\Users\\Nk\\git\\WashTrade\\WashTrades\\Master\\Master_run.bat";
	    String base = System.getProperty("user.dir");
	    System.out.println("usr.dir: " + base);
	    String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();
	  System.out.println("relative: "+ relative);*/
		
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\Nk\\Desktop\\Simulator\\Master_run.bat");
	       
		//Process process = Runtime.getRuntime().exec(
        //            "cmd /c hello.bat", null, new File("C:\\Users\\mkyong\\"));
					
        try {

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println(output);
                System.exit(0);
            } else {
            	System.out.println("Error in executing batch file");
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
   
	
     }
	
	public void dataGeneration1() {
		try {
			Process process = Runtime.getRuntime().exec(
					"cmd /c Master_run.bat", null, new File("C:\\Users\\Nk\\Desktop\\Simulator\\")); 
			process.waitFor();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
	}

}


