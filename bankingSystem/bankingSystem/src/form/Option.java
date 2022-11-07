package form;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.LedgerList;
import io.LedgerEntry;
import io.LedgerReader;
import io.LedgerWriter;
import options.Balance;
import options.Deposit;
import options.Withdrawl;

public class Option {

	private Scanner input;
	private double amount;
	private LedgerList ledgerList;
	private LedgerReader ledgerReader;
	private Balance balance;

	public Option() {

		input = new Scanner(System.in);
		ledgerList = new LedgerList();//arraylist
		ledgerReader = new LedgerReader();
		ledgerReader.readFromLedger(); // get record in file and store to list
		if (ledgerReader.getLedgerListObject().getList().size() != 0)
		{
			for (LedgerEntry record : ledgerReader.getLedgerListObject().getList())
			{
				ledgerList.add(record);
			}
		}
	}

	public void options() {

		char menu;

		do {

			displayOptions();
			ledgerReader = new LedgerReader();
			balance = new Balance();
			out.print("\nEnter Option Number: ");
			menu = input.next().charAt(0);

			switch (Character.toUpperCase(menu)) {

			case '1':// DEPOSIT
				executeDeposit();
				break;

			case '2':// WITHDRAW
				executeWithdraw(balance);
				break;

			case '3':// STATEMENT
				displayStatement();
				break;

			case '4':// BALANCE
				displayBalance(balance);
				break;

			case '5':// PRINT RECEIPT
				displayStatement();
				displayBalance(balance);
				break;
			
			case '6':
				System.out.println("Exited ATM Application");
				break;
			}
		} while (menu != '6');

	}

	private void displayBalance(Balance balance) {
		getDepositList();
		ledgerReader.readFromLedger(); // get record in file and store to list
		if (ledgerReader.getLedgerListObject().getList().size() != 0)
		{
			// validate if file has record before
			// processing the balance
			balance.compute(ledgerReader.getLedgerListObject().getList());
			out.println("Your Balance is: " + balance.getBalance());
		} 
		else
		{
			out.println("Zero Balance!");
		}
	}

	private void executeWithdraw(Balance balance) {
		ledgerReader.readFromLedger(); // get record in file and store to list
		if (ledgerReader.getLedgerListObject().getList().size() != 0)
		{
			// validate if file has record before		
			// processing the balance
			out.print("\nEnter Amount To Withdraw: ");
			amount = input.nextDouble();
			balance.compute(ledgerReader.getLedgerListObject().getList());// compute balance
			Withdrawl withdraw = new Withdrawl(amount, balance.getBalance());
			if (withdraw.validate())
			{
				LedgerEntry entry = new LedgerEntry('-', amount);
				ledgerList.add(entry);
				out.println("Transaction Successfull!!!");
			}
		}
		else
		{
			out.println("Zero Balance!");
		}
	}

	private void executeDeposit() {
		out.print("\nEnter Amount To Deposit: ");
		amount = input.nextDouble();
		Deposit deposit = new Deposit(amount);
		if (deposit.validate())
		{
			LedgerEntry entry = new LedgerEntry('+', amount);
			ledgerList.add(entry);
			out.println("Transaction Successfull!!!");
		}
		else
		{
			out.println("Deposit Amount Must Greater Than 0\n");
		}
	}

	private void displayOptions() {

		out.println("\nEnter any of the below options");
		out.println("[1] Deposit.");
		out.println("[2] Withdraw");
		out.println("[3] Statement");
		out.println("[4] Balance");
		out.println("[5] Print Receipt");
		out.println("[6] Exit");

	}

	private void getDepositList() {

		LedgerWriter write = new LedgerWriter();

		for (LedgerEntry dpt : ledgerList.getList()) {

			write.writeToLedger(dpt.getOperationSymbol(), dpt.getAmount());

		}
	}
	
	private void displayStatement() {
		
		out.println("Bank Statement: ");
		
		for (LedgerEntry record : ledgerList.getList()) {

			out.println(String.format("%s%s", record.getOperationSymbol(), record.getAmount()));

		}
	}

}
