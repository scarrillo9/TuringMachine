import java.util.*;

public class TuringMachine {
	public static void main(String[] args){
		
	}//end main class
	
	public static void acceptsLast1(int[] tape){
		
	}//acceptsLast1
	
	public static void turing(int N, int M, int[][] state, int[][] symbol, 
													char[][] LR, int[] tape){
		int head = 0;
		int currSt = 0;
		int currSym = 0;
		int nextSym = 0;
		int nextSt = 0;
		char direction = ' ';
		
		while((currSt != N-1) && (currSt != N-2)){
			currSym = tape[head];
			nextSt = state[currSt][currSym];
			nextSym = symbol[currSt][currSym];
			direction = LR[currSt][currSym];
			tape[head] = nextSym;
			currSt = nextSt;
			if(direction == 'R')
				head++;
			else if(direction == 'L')
				head--;
		}//end while
		System.out.print("|");
		for(int i = 0; i < tape.length; i++){
			System.out.print(tape[i] + "| ");
		}//end for loop
	}//end turing
}//end TuringMachine
