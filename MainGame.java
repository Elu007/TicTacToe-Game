// Sk Elaf Ahmed 
// Tic Tac Toe game in Java

import java.util.*;

public class MainGame {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) {
        // Here we use 5 rows to create the game board
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                            {'=', '+', '=', '+', '='}, 
                            {' ', '|', ' ', '|', ' '},
                            {'=', '+', '=', '+', '='},
                            {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);
        
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your placement(1-9):");
            int playerPos = sc.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Position taken already!");
                playerPos = sc.nextInt();
            }

            System.out.println("Your choice is: "+playerPos);
            placePiece(gameBoard, playerPos, "player");
            // Here we will check result after placing my piece
            String result = checkWinner();
            if(result.length()>0){
                System.out.println(result);
                break;
            }
    
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            // This code if we get player pos and cpu pos is same
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                //System.out.println("Position taken already!");
                cpuPos = rand.nextInt(9) + 1;
            }
    
            System.out.println("Computer choice is: "+cpuPos);
    
            placePiece(gameBoard, cpuPos, "cpu");
    
            printGameBoard(gameBoard);
            // Here we will check result after cpu placing piece
            result = checkWinner();
            if(result.length()>0){
                System.out.println(result);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard){
        // Printing the gameBoard
        for(char[] row: gameBoard){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard,int pos, String user){
    // This method for place character that player want        
        char symbol = ' ';

        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        } else if(user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch(pos){
            case 1: 
            gameBoard[0][0] = symbol;
            break;
            case 2: 
            gameBoard[0][2] = symbol;
            break;
            case 3: 
            gameBoard[0][4] = symbol;
            break;
            case 4: 
            gameBoard[2][0] = symbol;
            break;
            case 5: 
            gameBoard[2][2] = symbol;
            break;
            case 6: 
            gameBoard[2][4] = symbol;
            break;
            case 7: 
            gameBoard[4][0] = symbol;
            break;
            case 8: 
            gameBoard[4][2] = symbol;
            break;
            case 9: 
            gameBoard[4][4] = symbol;
            break;
            default:
                break;
        }
    }

    public static String checkWinner(){
        // This method will check wheather the user or the computer wins
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> botRow = Arrays.asList(7,8,9);
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l: winning){
            if(playerPositions.containsAll(l)){
                return "Congratulation you won!";
            }
            else if(cpuPositions.containsAll(l)){
                return "CPU Wins :(";
            } else if(playerPositions.size() + cpuPositions.size() == 9){
                return "DRAW!";
            }
        }
        return "";
    }
}
