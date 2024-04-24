import java.util.Scanner;

public class Tris {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static final char EMPTY = '-';
    
    private char[][] board;
    private char currentPlayer;
    
    public Tris() {
        board = new char[ROWS][COLS];
        currentPlayer = 'X';
        initializeBoard();
    }
    
    public void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }
    
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("| ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }
    
    public boolean isBoardFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }
    
    private boolean checkRows() {
        for (int i = 0; i < ROWS; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkColumns() {
        for (int i = 0; i < COLS; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkDiagonals() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || 
                checkRowCol(board[0][2], board[1][1], board[2][0]));
    }
    
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != EMPTY) && (c1 == c2) && (c2 == c3));
    }
    
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    public boolean placeMark(int row, int col) {
        if ((row >= 0) && (row < ROWS) && (col >= 0) && (col < COLS) && (board[row][col] == EMPTY)) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }
    
    public char getCurrentPlayer() {
        return currentPlayer;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tris game = new Tris();
        
        System.out.println("Benvenuto nel gioco del Tris!");
        System.out.println("Le posizioni della scacchiera sono numerate come segue:");
        System.out.println(" 1 | 2 | 3 ");
        System.out.println("-----------");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("-----------");
        System.out.println(" 7 | 8 | 9 ");
        
        while (!game.checkWin() && !game.isBoardFull()) {
            System.out.println("\nÈ il turno del giocatore " + game.getCurrentPlayer() + ". Inserisci la tua mossa (1-9): ");
            int position = scanner.nextInt();
            int row = (position - 1) / ROWS;
            int col = (position - 1) % COLS;
            
            if (game.placeMark(row, col)) {
                game.printBoard();
                if (game.checkWin()) {
                    System.out.println("Congratulazioni! Il giocatore " + game.getCurrentPlayer() + " ha vinto!");
                } else if (game.isBoardFull()) {
                    System.out.println("Pareggio! La partita è finita in pareggio.");
                } else {
                    game.switchPlayer();
                }
            } else {
                System.out.println("Posizione non valida. Riprova.");
            }
        }
        scanner.close();
    }
}
