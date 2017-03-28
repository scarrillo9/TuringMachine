import java.util.*;

public class TuringMachine {
	public static void main(String[] args){
		
		System.out.println("--Odd evaluation--");
		int[] tape = userInput();
		acceptsOdd(tape);
		
		System.out.println("\n--Even evaluation--");
		tape = userInput();
		acceptsEven(tape);
		
	}//end main method
	
	public static int[] userInput(){
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Input word to evaluate: ");
		String word = input.nextLine();
		String[] strTape = word.split("");
		int[] tape = new int[strTape.length];
		
		for(int i = 0; i < strTape.length; i++){
			tape[i] = Integer.parseInt(strTape[i]);
		}//end for loop
		
		return tape;
	}//end userInput
	
	public static void acceptsEven(int[] tape){
		int N = 6;
		int M = 3;
		
		int[][] state = new int[N][M];
		char[][] LR = new char[N][M];
		int[][] symbol = new int[N][M];
		
		for(int i = 0; i < tape.length; i++){	//this for loop changes 0's to 2
			if(tape[i] == 0)					//0 is blank, 1 is 1 and 2 is 0
				tape[i] = 2;
		}//end for loop
		
		//RULES FOR TURING MACHINE
		state[0][0] = 1;	//start, blank -> R, rstart
		LR[0][0] = 'R';
		symbol[0][0] = 0;
		
		state[1][1] = 3;	//rstart, 1 -> last1, R
		LR[1][1] = 'R';
		symbol[1][1] = 1;
		
		state[1][2] = 2;	//rstart, 0 -> last0, R
		LR[1][2] = 'R';
		symbol[1][2] = 2;
		
		state[3][1] = 3;	//last1, 1 -> R
		LR[3][1] = 'R';
		symbol[3][1] = 1;
		
		state[3][2] = 2;	//last1, 0 -> last0, R
		LR[3][2] = 'R';
		symbol[3][2] = 2;
		
		state[2][2] = 2;	//last0, 0 -> R
		LR[2][2] = 'R';
		symbol[2][2] = 2;
		
		state[2][1] = 3;	//last0, 1 -> last1, R
		LR[2][1] = 'R';
		symbol[2][1] = 1;
		
		state[3][0] = 5;	//last1, blank -> reject
		LR[3][0] = ' ';
		symbol[3][0] = 0;
		
		state[2][0] = 4;	//last0, blank -> accept
		LR[2][0] = ' ';
		symbol[2][0] = 0;
		
		state[1][0] = 5;	//rstart, blank -> reject
		LR[1][0] = ' ';
		symbol[1][0] = 0;
		
		turing(N, M, state, symbol, LR, tape);
	}//end acceptsEven
	
	public static void acceptsOdd(int[] tape){
		int N = 6;
		int M = 3;
		
		int[][] state = new int[N][M];
		char[][] LR = new char[N][M];
		int[][] symbol = new int[N][M];
		
		for(int i = 0; i < tape.length; i++){	//this for loop changes 0's to 2
			if(tape[i] == 0)					//0 is blank, 1 is 1 and 2 is 0
				tape[i] = 2;
		}//end for loop
		
		//RULES FOR TURING MACHINE
		state[0][0] = 1;	//start, blank -> R, rstart
		LR[0][0] = 'R';
		symbol[0][0] = 0;
		
		state[1][1] = 3;	//rstart, 1 -> last1, R
		LR[1][1] = 'R';
		symbol[1][1] = 1;
		
		state[1][2] = 2;	//rstart, 0 -> last0, R
		LR[1][2] = 'R';
		symbol[1][2] = 2;
		
		state[3][1] = 3;	//last1, 1 -> R
		LR[3][1] = 'R';
		symbol[3][1] = 1;
		
		state[3][2] = 2;	//last1, 0 -> last0, R
		LR[3][2] = 'R';
		symbol[3][2] = 2;
		
		state[2][2] = 2;	//last0, 0 -> R
		LR[2][2] = 'R';
		symbol[2][2] = 2;
		
		state[2][1] = 3;	//last0, 1 -> last1, R
		LR[2][1] = 'R';
		symbol[2][1] = 1;
		
		state[3][0] = 4;	//last1, blank -> accept
		LR[3][0] = ' ';
		symbol[3][0] = 0;
		
		state[2][0] = 5;	//last0, blank -> reject
		LR[2][0] = ' ';
		symbol[2][0] = 0;
		
		state[1][0] = 5;	//rstart, blank -> reject
		LR[1][0] = ' ';
		symbol[1][0] = 0;
		
		turing(N, M, state, symbol, LR, tape);
	}//acceptsLast1
	
	/** UNIVERSAL TURING MACHINE */
	public static void turing(int N, int M, int[][] state, int[][] symbol, 
													char[][] LR, int[] tape){
		int head = 0;
		int currSt = 0;
		int currSym = 0;
		int nextSym = 0;
		int nextSt = 0;
		char direction = ' ';
		
		while((currSt != N-1) && (currSt != N-2)){
			//checking that the head is not going
			//out of bounds of the tape array
			if(head == tape.length)
				currSym = 0;
			else
				currSym = tape[head];
			
			nextSt = state[currSt][currSym];
			nextSym = symbol[currSt][currSym];
			direction = LR[currSt][currSym];
			
			//like earlier, checking that the head
			//is still not out of bounds
			if(head == tape.length)
				nextSym = 0;
			else
				tape[head] = nextSym;
			
			currSt = nextSt;
			
			if(direction == 'R')
				head++;
			else if(direction == 'L')
				head--;
		}//end while
		
		if(currSt == N-1)
			System.out.println("This word is rejected.");
		if(currSt == N-2)
			System.out.println("This word is accepted.");
	}//end turing
	
}//end TuringMachine
