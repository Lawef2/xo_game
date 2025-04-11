/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LEGION
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {

    private JButton[][] buttons;
    private char currentPlayer;
    private JLabel messageLabel;
    private JButton resetButton;
    private final Color xColor = Color.BLUE;
    private final Color oColor = Color.RED;

    public TicTacToe() {
        setTitle("Colorful Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        currentPlayer = 'X'; // اللاعب الأول
        buttons = new JButton[3][3];

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setForeground(Color.BLUE);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                buttonPanel.add(buttons[i][j]);
            }
        }

        messageLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        messageLabel.setForeground(Color.BLUE);

        add(messageLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        messageLabel.setForeground(xColor); // Initial color for player X
        resetButton = new JButton("New Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(e -> resetGame());
        JPanel controlPanel = new JPanel();
        controlPanel.add(resetButton);
        add(controlPanel, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (!buttonClicked.getText().equals("")) {
            return; // If the cell is already taken, do nothing
        }

        buttonClicked.setText(String.valueOf(currentPlayer));

        // Set the button color based on player
        if (currentPlayer == 'X') {
            buttonClicked.setForeground(xColor);
        } else {
            buttonClicked.setForeground(oColor);
        }

        if (checkForWin()) {
            messageLabel.setText("Player " + currentPlayer + " wins!");
            messageLabel.setForeground(currentPlayer == 'X' ? xColor : oColor);
            disableButtons();
        } else if (isBoardFull()) {
            messageLabel.setText("It's a draw!");
            messageLabel.setForeground(Color.BLACK);
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            messageLabel.setText("Player " + currentPlayer + "'s turn");
            messageLabel.setForeground(currentPlayer == 'X' ? xColor : oColor);
        }
    }

    private boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            // افحص الصفوف والأعمدة
            if ((buttons[i][0].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][2].getText().equals(String.valueOf(currentPlayer)))
                    || (buttons[0][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[1][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][i].getText().equals(String.valueOf(currentPlayer)))) {
                return true;
            }
        }
        // افحص الأقطار
        return (buttons[0][0].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][2].getText().equals(String.valueOf(currentPlayer)))
                || (buttons[0][2].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][0].getText().equals(String.valueOf(currentPlayer)));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        currentPlayer = 'X';
        messageLabel.setText("Player X's turn");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToe().setVisible(true);
        });
    }
}
