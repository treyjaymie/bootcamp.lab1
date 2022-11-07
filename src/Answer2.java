import java.util.Scanner;

public class Answer2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int number = in.nextInt();
		
		if (number % 2 == 0)
			System.out.println("Even Number");
		else
			System.out.println("Odd Number");
		
		System.out.println("bye!");
	}
}
