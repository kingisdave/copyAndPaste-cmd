import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.regex.Pattern; //
import java.util.regex.Matcher;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CopyPaste{
	Scanner userInput = new Scanner(System.in);
	String[] menuOne= {	"> 1. Copy   ", "> 2. Paste  "	};
	String[] menuTwo= {	"> 1. Folder   ", "> 2. File  "	};
	String[] copiedFolder; 
	String[] copiedFile;
	String folderName;
	String fileName;
	String pasteToDir;
	String myValues;
	String dataFile;
	
	public CopyPaste(){
		System.out.println("\n WELCOME TO DE-COPY-LA-PASTE!!! \n Click Enter to continue \n");
		firstMenu(null, true);	
	}

	public void firstMenu(String copiedName, boolean notCopied){
		System.out.println(" Choose an option, reply with number.");
		for (String menu : menuOne) {
			System.out.print(menu);
		}
		System.out.print("\n Reply >> ");
		myValues = userInput.nextLine();
		String lowerVals = myValues.toLowerCase();
		if (lowerVals.equals("copy") || lowerVals.equals("paste") || myValues.equals("1") || myValues.equals("2")) {
			if(notCopied){
				secondMenu(myValues, true, null);
			}else {
				secondMenu(myValues, false, copiedName);
			}
		} else {
			System.out.println("You entered invalid details");
			firstMenu(null, true);
		}
		
	}
	public void secondMenu(String myval, boolean notCopied, String copyName){
		if(myval.equals("1") || myval.equals("copy")){
			System.out.println("What are you willing to Copy ?, Choose an option:");
			for (String menu : menuTwo) {
				System.out.println(menu);
			}
			System.out.print(" Reply  >> ");
			myValues = userInput.nextLine();
			
			if (myValues.toLowerCase().equals("folder") || myValues.equals("1") || myValues.toLowerCase().equals("file") || myValues.equals("2")) {
				copyAnything(myValues, null);
				
			} else {
				System.out.println("You entered invalid details");
				secondMenu(myval, true, null);
			}

		} else if (myval.equals("2") || myval.equals("paste")) {
			if (notCopied) {
				System.out.println("\n Wrong Option ");
				System.out.println("You have nothing to paste yet\n");
				firstMenu(null,true);
				
			} else {
				System.out.println("What are you willing to Paste?, Choose an option: ");
				pasteAnything(myValues, copyName);
			}
		} else {
			System.out.println("Wrong Option ");
		}

	}

	public void copyAnything(String anyThing, String myCopiedName ){
		String folderDir;
		
		if (anyThing.toLowerCase().equals("folder") || anyThing.equals("1")) {
			System.out.println("\n Directory To Copy From ");
			System.out.print("Reply >>");
			folderDir = userInput.nextLine();
			try{
				File myFolder = new File(folderDir);
				if(myFolder.exists()){
					String[] output = myFolder.list();   // Listing of all the files inside folder
					for(String nextFile : output){       
						System.out.print(" "+nextFile + ",  ");	
					}
					System.out.println("\n\n Enter Folder Name: ");
					System.out.print("Reply  >> ");
					folderName = userInput.nextLine();
					if(folderName.equals("")){
						System.out.println("Error Processing Empty Value");
						copyAnything(anyThing, null);
					} else {
						for (int ii = 0; ii < output.length; ii++) {
							if (output[ii].toLowerCase().equals(folderName.toLowerCase()) ) {
								System.out.println(folderName +" successfully copied \n");
								firstMenu(output[ii], false);
							}
						}	

					}

				} else {
					System.out.println("Invalid Folder Directory");	
					copyAnything(anyThing, null);
				}

			} catch(Exception e) {
				System.out.println("Operation encounter an error");
				e.printStackTrace();
				copyAnything(anyThing, null);
			}

			
		} else if (anyThing.toLowerCase().equals("file") || anyThing.equals("2")) {
			System.out.println("You want to copy a file");
			System.out.println("\n Directory To Copy From ");
			System.out.print("Reply >>");
			folderDir = userInput.nextLine();
			try{
				File myFolder = new File(folderDir);
				if(myFolder.exists()){
					String[] output = myFolder.list();   // Listing of all the files inside folder
					for(String nextFile : output){       
						System.out.print(" "+nextFile + ",  ");	
					}
					System.out.println("\n\n Enter File Name: ");
					System.out.print("Reply  >> ");
					fileName = userInput.nextLine();
					if(fileName.equals("")){
						System.out.println("Error Processing Empty Value");
						copyAnything(anyThing, null);
					} else {
						for (int ii = 0; ii < output.length; ii++) {
							if (output[ii].toLowerCase().equals(fileName.toLowerCase()) ) {
								System.out.println(output[ii] +" successfully copied \n");
								// firstMenu(output[ii], false);
								System.out.println("\n Directory to Paste Folder To");
								System.out.print("Reply >>");
								pasteToDir = userInput.nextLine();
								if(pasteToDir.equals(null)){
									System.out.println("Invalid Folder Directory");	
								} else {
									File myFile = new File(pasteToDir+"\\"+output[ii]);
									if(myFile.exists()){
										Pattern text = Pattern.compile(".", Pattern.LITERAL); // Match . character
										Matcher found = text.matcher(pasteToDir+"\\"+output[ii]);
										String nwtFile = found.replaceFirst("_copy.");
										
										File newsFile = new File(nwtFile);
										
										if (newsFile.createNewFile()) {
											Scanner myRead = new Scanner(myFile);
											while(myRead.hasNextLine()){
												dataFile = myRead.nextLine();
												FileWriter nxtFile = new FileWriter(nwtFile, true);    // new FileWriter(Filenamel, boolean append);
												System.out.print(dataFile);
												nxtFile.write(dataFile);
												nxtFile.close();				
											}
											myRead.close();
											System.out.println("File written successfully");	
										}else{
											System.out.println("Unable write on file so unseen");
									 	}
									} else {
										System.out.println("Doesn't exist but unable to write on file");
									}
								}

							}
						}	

					}

				} else {
					System.out.println("Invalid Folder Directory");	
					copyAnything(anyThing, null);
				}

			} catch(Exception e) {
				System.out.println("Operation encounter an error");
				e.printStackTrace();
				copyAnything(anyThing, null);
			}
		}
	}

	public void pasteAnything(String myval, String newFolder){
		
		
		System.out.println("\n Directory to Paste Folder To");
		System.out.print("Reply >>");
		pasteToDir = userInput.nextLine();
		if(pasteToDir.equals(null)){
			System.out.println("Invalid Folder Directory");	
		} else {
			try{
				File myFolder = new File(pasteToDir+"\\"+newFolder);
				if(myFolder.exists()){
					File myFolderCopy = new File(pasteToDir+"\\"+newFolder+"_copy");
					if (myFolderCopy.mkdir()) {
						String[] output = myFolder.list(); 
						for(String eachFile : output){       
							File oldFile = new File(pasteToDir+"\\"+newFolder+"\\"+eachFile); //
							File newFile = new File(pasteToDir+"\\"+newFolder+"_copy"+"\\"+eachFile); //
							if (newFile.createNewFile()) {
								Scanner myRead = new Scanner(oldFile);
								while(myRead.hasNextLine()){
									dataFile = myRead.nextLine();
						 			FileWriter myFile = new FileWriter(newFile, true);
									myFile.write(dataFile);
									myFile.close();
								}
								myRead.close();
							} else  {
								System.out.print("Nothing to Display");
							}
						}
						System.out.println(myFolderCopy +" Folder has been created");
					} else {
						System.out.println(" Folder does exists but does not create ");
					}
				} else {
					if (myFolder.mkdir()) {
						System.out.println(myFolder +" Folder has been created");

					} else {
						System.out.println(" Folder does not exist and does not create ");

					}
				}
			}catch(Exception e){
				System.out.println("Operation encounter an error");
				e.printStackTrace();
				pasteAnything(myval, newFolder);
			}
		}
	}

	public static void main(String[] args){
		CopyPaste cp = new CopyPaste();
	}

}
