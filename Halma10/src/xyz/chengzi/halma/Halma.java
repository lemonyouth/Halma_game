package xyz.chengzi.halma;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.controller.GameController2;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoard2;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.GameFrame0;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Halma {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessBoardComponent chessBoardComponent = new ChessBoardComponent(500, 16);
            ChessBoard chessBoard = null;
            try {
                chessBoard = new ChessBoard(16);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ChessBoard2 chessBoard2 = new ChessBoard2(16);
            GameController controller = new GameController(chessBoardComponent, chessBoard);
            GameController2 controller2 = new GameController2(chessBoardComponent, chessBoard2);

            /*GameFrame mainFrame = new GameFrame();
            mainFrame.add(chessBoardComponent);
            mainFrame.setVisible(true);*/
            GameFrame0 mainFrame = new GameFrame0();
        });

        Music music = new Music();

    }

}