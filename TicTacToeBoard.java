import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeBoard {
    private static final int ROW = 3;
    private static final int COL = 3;
    private int moveCnt = 0;
    private final int MOVES_FOR_WIN = 5;
    private final int MOVES_FOR_TIE = 7;
    private String player;
    private JPanel buttonPnl;
    private TicTacToeTile[][] board;

    public TicTacToeBoard() {
        createButtonPanel();
    }
    private void createButtonPanel() {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(3, 3));
        buttonPnl.setBorder(new TitledBorder(new EtchedBorder(), "Squares"));
        board = new TicTacToeTile[3][3];
        player = "X";
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText("");
                board[row][col].addActionListener(this::buttonClicked);
                buttonPnl.add(board[row][col]);
            }
    }
    private void buttonClicked(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();

        if (!clickedButton.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "This cell is already occupied!", "Invalid Move", JOptionPane.ERROR_MESSAGE);

            return;

        }

        clickedButton.setText(player);

        moveCnt++;



        if (isWin(player)) {

            clearBoard();

            moveCnt = 0;

            int response;
            response = JOptionPane.showConfirmDialog(null, "Player " + player + " wins! Do you want to play again?", "Play again",
                    JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.NO_OPTION) {
                System.exit(0);
            }

            return;

        }



        if (moveCnt == MOVES_FOR_TIE) {

            clearBoard();

            moveCnt = 0;

            int response;
            response = JOptionPane.showConfirmDialog(null, "It's a Tie! Do you want to play again?", "Play again",
                    JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.NO_OPTION) {
                System.exit(0);
            }

            return;

        }



        player = (player.equals("X")) ? "O" : "X";

    }
    private void clearBoard()
    {
        // sets all the board elements to a space
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                board[row][col].setText("");
            }
        }
    }
    private boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }
    private boolean isColWin(String player)
    {
        // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals(player) &&
                    board[1][col].getText().equals(player) &&
                    board[2][col].getText().equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }
    private boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private boolean isDiagnalWin(String player)
    {
        // checks for a diagonal win for the specified player

        if(board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player) )
        {
            return true;
        }
        if(board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player) )
        {
            return true;
        }
        return false;
    }

    // checks for a tie before board is filled.
    // check for the win first to be efficient
    private boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }

    public int getMoveCnt() {
        return moveCnt;
    }

    public void setMoveCnt(int moveCnt) {
        this.moveCnt = moveCnt;
    }

    public int getMOVES_FOR_WIN() {
        return MOVES_FOR_WIN;
    }

    public int getMOVES_FOR_TIE() {
        return MOVES_FOR_TIE;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public JPanel getButtonPnl() {
        return buttonPnl;
    }

    public void setButtonPnl(JPanel buttonPnl) {
        this.buttonPnl = buttonPnl;
    }

    public TicTacToeTile[][] getBoard() {
        return board;
    }

    public void setBoard(TicTacToeTile[][] board) {
        this.board = board;
    }
}
