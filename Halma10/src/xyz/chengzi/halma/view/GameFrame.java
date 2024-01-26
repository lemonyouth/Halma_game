package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.model.ChessBoard;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        setTitle("2020 CS102A Project Demo");
        setSize(776, 810);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel statusLabel = new JLabel("Sample label");
        statusLabel.setLocation(0, 560);
        statusLabel.setSize(200, 10);
        add(statusLabel);

        JButton button = new JButton("...");
        /*JRadioButton b1=new JRadioButton("两人组");
        JRadioButton b2=new JRadioButton("四人组");
        b1.setSelected(true);
        b1.setBounds(50, 50, 130, 30);
        b2.setBounds(50, 100, 130, 30);
        add(b1);
        add(b2);*/
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        //if ()
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Button clicked!"));
        button.setLocation(540, 560);
        button.setSize(20, 12);
        add(button);
    }
}
