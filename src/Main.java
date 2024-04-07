import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String[][] board = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
        System.out.println("Vítejte ve hře piškvorky");
        String currentPlayer = "X"; // X začíná
        boolean gameEnded = false;

        while (!gameEnded) {
            boolean validMove = false;
            while (!validMove) {
                printBoard(board);
                System.out.println("Hráč " + currentPlayer + ", zadejte číslo pole (1-9): ");
                if (scanner.hasNextInt()) {
                    int move = scanner.nextInt();
                    if (move >= 1 && move <= 9) {
                        if (!checkIfNotEmpty(move, board)){
                            validMove = false;
                        } else {
                            addSymbol(move, currentPlayer, board);
                            validMove = true;
                        }
                    } else {
                        System.out.println("Neplatný tah, zadejte číslo od 1 do 9.");
                    }
                } else {
                    System.out.println("Neplatný vstup, zadejte číslo.");
                    scanner.next();
                }
            }

            // Kontrola stavu hry
            if (checkForWin(board)) {
                printBoard(board);
                System.out.println("Hráč " + currentPlayer + " vyhrál!");
                gameEnded = true;
            } else if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("Hra skončila remízou!");
                gameEnded = true;
            } else {
                // Přepínání hráčů
                currentPlayer = (currentPlayer == "X") ? "0" : "X";
            }
        }

        scanner.close();
    }


    private static void printBoard(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 3 - 1) {
                    System.out.print("|"); // Vložení vertikálního oddělovače mezi čísla, kromě na konci řádku
                }
            }
            System.out.println();
            if (i < 3 - 1) { // Po posledním řádku symboly "-" nevypisujeme
                for (int k = 0; k < board[i].length * 2 - 1; k++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
    }
    public static boolean checkIfNotEmpty(int number, String[][] board) {
        // Převod čísla na indexy pole
        int index = number - 1; // Převod na indexy 0-8
        int row = index / 3;
        int col = index % 3;

        if (board[row][col] != " ") {
            System.out.println("Buňka je již obsazena. Zkuste jiné číslo.");
            return false;
        }
        return true;
    }
    public static void addSymbol(int number, String symbol, String[][] board) {
        if (number < 1 || number > 9) {
            System.out.println("Neplatný vstup. Zadejte číslo od 1 do 9.");
            return;
        }

        // Převod čísla na indexy pole
        int index = number - 1; // Převod na indexy 0-8
        int row = index / 3;
        int col = index % 3;

        // Kontrola, zda je buňka již obsazena
        if (board[row][col] != " ") {
            System.out.println("Buňka je již obsazena. Zkuste jiné číslo.");
        } else {
            if (symbol != "X" && symbol != "0") {
                System.out.println("Nesprávný symbol");
            } else {
                // Přidání symbolu do vybrané buňky
                board[row][col] = symbol;
            }
        }
    }
    public static boolean checkForWin(String[][] board) {
        // Kontrola řádků a sloupců
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != " " && board[i][0] == board[i][1] && board[i][1] == board[i][2]) ||
                    (board[0][i] != " " && board[0][i] == board[1][i] && board[1][i] == board[2][i])) {
                return true; // Vítězství v řádku nebo sloupci
            }
        }

        // Kontrola diagonál
        if ((board[0][0] != " " && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] != " " && board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return true; // Vítězství na diagonále
        }

        return false; // Žádné vítězství nenalezeno
    }
    // Kontrola, zda je pole plné
    public static boolean isBoardFull(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == " ") {
                    return false; // Nalezeno volné místo
                }
            }
        }
        return true; // Žádné volné místo nenalezeno
    }
    // Kontrola zda nenastala remíza
    public static boolean checkForDraw(String[][] board) {
        // Remíza nastane, pokud není vítězství a pole je plné
        return !checkForWin(board) && isBoardFull(board);
    }
}

