import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TictTacToeFrame extends JFrame{
    JPanel mainPnl;
    JPanel buttonPnl;
    JPanel quitPnl;
    JButton quitBtn;
    TicTacToeBoard board;
    public TictTacToeFrame() {
        setTitle("Tic Tac Toe");
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        createButtonPanel();
        mainPnl.add(buttonPnl, BorderLayout.NORTH);
        createQuitButton();
        mainPnl.add(quitPnl,BorderLayout.SOUTH);
        add(mainPnl);
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void createButtonPanel() {
        buttonPnl = new JPanel();
        board = new TicTacToeBoard();
        buttonPnl.setLayout(new BorderLayout());
        buttonPnl.add(board.getButtonPnl());
    }
    private void createQuitButton() {

        quitPnl = new JPanel();
        quitPnl.setLayout(new BorderLayout());

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            System.exit(0);
        });

        quitPnl.add(quitBtn);


    }
}
