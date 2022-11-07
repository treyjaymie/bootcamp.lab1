package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LedgerReader {
    
	private final static String fileAddress = "C:\\Users\\anttr\\Desktop\\JAVA LABS\\bankingSystem\\bankingSystem\\src\\file\\ledger.txt";
    private FileReader fileReader;
    private BufferedReader reader;
    private LedgerList ledgerList;
    
    public LedgerReader() {
        try {
            fileReader = new FileReader(fileAddress);
            reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void readFromLedger() {
    	
    	ledgerList = new LedgerList();
        
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
               
                char symbol = line.charAt(0);
                double amount = Double.parseDouble(line.substring(1, line.length()));
                
                LedgerEntry ledgerEntry = new LedgerEntry(symbol, amount);
                ledgerList.add(ledgerEntry);
       
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

	public LedgerList getLedgerListObject() {
		return ledgerList;
	}

}

