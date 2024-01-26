package xyz.chengzi.halma.controller;

import xyz.chengzi.halma.listener.GameListener;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.ChessPiece;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.ChessComponent;
import xyz.chengzi.halma.view.GameFrame0;
import xyz.chengzi.halma.view.SquareComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GameController implements GameListener {
    Scanner in = new Scanner(System.in);
    private ChessBoardComponent view;
    public static ChessBoard model;

    public static Color currentPlayer;
    private ChessPiece selectedPiece;
    private ChessBoardLocation selectedLocation;

    public GameController(ChessBoardComponent boardComponent, ChessBoard chessBoard) {
        this.view = boardComponent;
        this.model = chessBoard;
        JFrame framewho=new JFrame("谁先下");
        framewho.setVisible(true);
        framewho.setSize(210,250);
        framewho.setLocation(0,100);
        if(GameFrame0.now!=null){
            GameFrame0.now.dispose();
        }
        GameFrame0.who1=framewho;

        //背景图设置
        ImageIcon img1 = new ImageIcon("C:\\田宇琼\\b.jpg");
        JLabel imgLabel1 = new JLabel(img1);
        framewho.getLayeredPane().add(imgLabel1, new Integer(Integer.MIN_VALUE));
        imgLabel1.setBounds(0, 0, img1.getIconWidth(), img1.getIconHeight());
        Container cp1 = framewho.getContentPane();
        cp1.setLayout(null);
        ((JPanel) cp1).setOpaque(false);


        JButton buttonred=new JButton("红棋先");
        buttonred.setBorder(null);
        buttonred.setContentAreaFilled(false);
        buttonred.setFont( new Font("华文隶书", Font.BOLD, 30) );
        buttonred.setForeground(Color.BLUE);
        framewho.add(buttonred);
        buttonred.setBounds(45, 75, 100, 45);
        JButton buttongreen=new JButton("绿棋先");
        buttongreen.setBorder(null);
        buttongreen.setContentAreaFilled(false);
        buttongreen.setFont( new Font("华文隶书", Font.BOLD, 30) );
        buttongreen.setForeground(Color.BLUE);
        framewho.add(buttongreen);
        buttongreen.setBounds(45, 125, 100, 45);

        buttongreen.setVisible(true);
        buttonred.setVisible(true);
        buttonred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = Color.RED;
            }
        });

        buttongreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = Color.GREEN;
            }
        });
        view.registerListener(this);
        initGameState();
    }

    public void initGameState() {
        for (int row = 0; row < model.getDimension(); row++) {
            for (int col = 0; col < model.getDimension(); col++) {
                ChessBoardLocation location = new ChessBoardLocation(row, col);
                ChessPiece piece = model.getChessPieceAt(location);
                if (piece != null) {
                    view.setChessAtGrid(location, piece.getColor());
                }
            }
        }
        view.repaint();
    }

    public Color nextPlayer() {
        return currentPlayer = currentPlayer == Color.RED ? Color.GREEN : Color.RED;
    }

    @Override
    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {

       if (selectedLocation != null && model.isValidMove(selectedLocation, location)) {
            model.moveChessPiece(selectedLocation, location);
            view.setChessAtGrid(location, selectedPiece.getColor());
            view.removeChessAtGrid(selectedLocation);
            view.repaint();
            ChessBoard.huiqi=selectedPiece.getColor();
            ChessBoard.hefa=location;

            if (currentPlayer == Color.red) {
                if (selectedLocation.getRow() - location.getRow() == 2 || selectedLocation.getRow() - location.getRow() == -2 ||
                        selectedLocation.getColumn() - location.getColumn() == -2 || selectedLocation.getColumn() - location.getColumn() == 2) {
                        JFrame framered = new JFrame("状态");
                        framered.setSize(210, 250);
                        if(GameFrame0.now!=null){
                            GameFrame0.now.dispose();
                        }
                        if(GameFrame0.who1!=null){
                            GameFrame0.who1.dispose();
                        }
                        if(GameFrame0.who2!=null){
                            GameFrame0.who2.dispose();
                        }
                        GameFrame0.now=framered;
                        framered.setVisible(true);
                        JLabel labelred = new JLabel("轮到红棋下了");
                        labelred.setFont( new Font("华文隶书", Font.BOLD, 30) );
                        Color color=new Color(138, 0, 255, 255);
                        labelred.setOpaque(true);
                        labelred.setBackground(color);
                        labelred.setForeground(Color.WHITE);
                        framered.add(labelred);
                }else {
                    JFrame framered = new JFrame("状态");
                    framered.setSize(210, 250);
                    if(GameFrame0.now!=null){
                        GameFrame0.now.dispose();
                    }
                    if(GameFrame0.who1!=null){
                        GameFrame0.who1.dispose();
                    }
                    if(GameFrame0.who2!=null){
                        GameFrame0.who2.dispose();
                    }
                    GameFrame0.now=framered;
                    framered.setVisible(true);
                    JLabel labelred = new JLabel("轮到绿棋下了");
                    labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                    Color color = new Color(138, 0, 255, 255);
                    labelred.setOpaque(true);
                    labelred.setBackground(color);
                    labelred.setForeground(Color.WHITE);
                    framered.add(labelred);
                }
            }
            if (currentPlayer == Color.GREEN) {
                if (selectedLocation.getRow() - location.getRow() == 2 || selectedLocation.getRow() - location.getRow() == -2 ||
                        selectedLocation.getColumn() - location.getColumn() == -2 || selectedLocation.getColumn() - location.getColumn() == 2) {
                        JFrame framered = new JFrame("状态");
                        framered.setSize(210, 250);
                    if(GameFrame0.now!=null){
                        GameFrame0.now.dispose();
                    }
                    if(GameFrame0.who1!=null){
                        GameFrame0.who1.dispose();
                    }
                    if(GameFrame0.who2!=null){
                        GameFrame0.who2.dispose();
                    }
                        GameFrame0.now=framered;
                        framered.setVisible(true);
                        JLabel labelred = new JLabel("轮到绿棋下了");
                        labelred.setFont( new Font("华文隶书", Font.BOLD, 30) );
                        Color color=new Color(138, 0, 255, 255);
                        labelred.setOpaque(true);
                        labelred.setBackground(color);
                        labelred.setForeground(Color.WHITE);
                        framered.add(labelred);
                }else {
                    JFrame framegreen = new JFrame("状态");
                    framegreen.setSize(210, 250);
                    if(GameFrame0.now!=null){
                        GameFrame0.now.dispose();
                    }
                    if(GameFrame0.who1!=null){
                        GameFrame0.who1.dispose();
                    }
                    if(GameFrame0.who2!=null){
                        GameFrame0.who2.dispose();
                    }
                    GameFrame0.now=framegreen;
                    framegreen.setVisible(true);
                    JLabel labelred = new JLabel("轮到红棋下了");
                    labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                    Color color = new Color(138, 0, 255, 255);
                    labelred.setOpaque(true);
                    labelred.setBackground(color);
                    labelred.setForeground(Color.WHITE);
                    framegreen.add(labelred);
                }
            }

           int m=0;
           if(selectedLocation.getRow()-location.getRow()==1 || selectedLocation.getRow()-location.getRow()==-1 ||
                   selectedLocation.getColumn()-location.getColumn()==-1 || selectedLocation.getColumn()-location.getColumn()==1) {
               if(ChessBoard.isWinnerRed()){
                   JFrame framered = new JFrame();
                   framered.setSize(300, 100);
                   if(GameFrame0.now!=null){
                       GameFrame0.now.dispose();
                   }
                   if(GameFrame0.who1!=null){
                       GameFrame0.who1.dispose();
                   }
                   if(GameFrame0.who2!=null){
                       GameFrame0.who2.dispose();
                   }
                   GameFrame0.now=framered;
                   framered.setVisible(true);

                   framered.setLocation(800,100);
                   //背景图设置
                   ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
                   JLabel imgLabel = new JLabel(img);
                   framered.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                   imgLabel.setBounds(0, 0, 300, 100);
                   Container cp = framered.getContentPane();
                   cp.setLayout(null);
                   ((JPanel) cp).setOpaque(false);

                   JButton win = new JButton("红方胜!!!");
                   win.setBorder(null);
                   win.setContentAreaFilled(false);
                   win.setFont( new Font("华文隶书", Font.BOLD, 50) );
                   win.setForeground(Color.white);
                   win.setBounds(0, 0, 300, 40);
                   imgLabel.add(win);
                   win.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           new GameFrame0();
                       }
                   });
                   return;
               }
               if(ChessBoard.isWinnerGreen()){
                   JFrame framered = new JFrame();
                   framered.setSize(300, 100);
                   if(GameFrame0.now!=null){
                       GameFrame0.now.dispose();
                   }
                   if(GameFrame0.who1!=null){
                       GameFrame0.who1.dispose();
                   }
                   if(GameFrame0.who2!=null){
                       GameFrame0.who2.dispose();
                   }
                   GameFrame0.now=framered;
                   framered.setVisible(true);

                   framered.setLocation(800,100);
                   //背景图设置
                   ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
                   JLabel imgLabel = new JLabel(img);
                   framered.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                   imgLabel.setBounds(0, 0, 300, 100);
                   Container cp = framered.getContentPane();
                   cp.setLayout(null);
                   ((JPanel) cp).setOpaque(false);

                   JButton win = new JButton("绿方胜!!!");
                   win.setBorder(null);
                   win.setContentAreaFilled(false);
                   win.setFont( new Font("华文隶书", Font.BOLD, 50) );
                   win.setForeground(Color.white);
                   win.setBounds(0, 0, 300, 40);
                   imgLabel.add(win);
                   win.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           new GameFrame0();
                       }
                   });
                   return;
               }
               ChessBoard.hefa=new ChessBoardLocation(0,0);
               nextPlayer();
           }else {

               if(location.getColumn()==0){
                   if(location.getRow()==0){
                       for (int i = 0; i <= location.getRow() + 1; i++) {
                           for (int j = 0; j <= location.getColumn() + 1; j++) {
                               ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                               if (model.getChessPieceAt(location1) != null) {
                                   m++;
                               }
                           }
                       }
                   }
                   if(location.getRow()!=0 && location.getRow()!=15){
                       for (int i = location.getRow() - 1; i <= location.getRow() + 1; i++) {
                           for (int j = 0; j <= location.getColumn() + 1; j++) {
                               ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                               if (model.getChessPieceAt(location1) != null) {
                                   m++;
                               }
                           }
                       }
                   }
                   if(location.getRow()==15){
                       for (int i = location.getRow() - 1; i <= location.getRow(); i++) {
                           for (int j = 0; j <= location.getColumn() + 1; j++) {
                               ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                               if (model.getChessPieceAt(location1) != null) {
                                   m++;
                               }
                           }
                       }
                   }
               }
               if(location.getColumn()!=0 && location.getColumn()!=15){
                   if(location.getRow()==0){
                       for (int i = 0; i <= location.getRow() + 1; i++) {
                           for (int j = location.getColumn()-1; j <= location.getColumn() + 1; j++) {
                               ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                               if (model.getChessPieceAt(location1) != null) {
                                   m++;
                               }
                           }
                       }
                   }
                   if(location.getRow()!=0 && location.getRow()!=15){
                       for (int i = location.getRow() - 1; i <= location.getRow() + 1; i++) {
                           for (int j = location.getColumn()-1; j <= location.getColumn() + 1; j++) {
                               ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                               if (model.getChessPieceAt(location1) != null) {
                                   m++;
                               }
                           }
                       }
                   }
                   if(location.getRow()==15){
                       for (int i = location.getRow() - 1; i <= location.getRow(); i++) {
                           for (int j = location.getColumn()-1; j <= location.getColumn() + 1; j++) {
                               ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                               if (model.getChessPieceAt(location1) != null) {
                                   m++;
                               }
                           }
                       }
                   }
               }
               if(location.getColumn()==15){
                   if(location.getRow()==0){
                       for (int i = location.getRow(); i <= location.getRow()+1; i++) {
                           for (int j = location.getColumn()-1; j <= location.getColumn(); j++) {
                               ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                               if (model.getChessPieceAt(location1) != null) {
                                   m++;
                               }
                           }
                       }
                   }
                   if(location.getRow()!=0 && location.getRow()!=15){
                       for (int i = location.getRow() - 1; i <= location.getRow() + 1; i++) {
                           for (int j = location.getColumn()-1; j <= location.getColumn(); j++) {
                               ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                               if (model.getChessPieceAt(location1) != null) {
                                   m++;
                               }
                           }
                       }
                   }
                   if(location.getRow()==15){
                       for (int i = location.getRow() - 1; i <= location.getRow(); i++) {
                           for (int j = location.getColumn()-1; j <= location.getColumn(); j++) {
                               ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                               if (model.getChessPieceAt(location1) != null) {
                                   m++;
                               }
                           }
                       }
                   }
               }
               if (m == 1) {
                   if(ChessBoard.isWinnerRed()){
                       JFrame framered = new JFrame();
                       framered.setSize(300, 100);
                       if(GameFrame0.now!=null){
                           GameFrame0.now.dispose();
                       }
                       if(GameFrame0.who1!=null){
                           GameFrame0.who1.dispose();
                       }
                       if(GameFrame0.who2!=null){
                           GameFrame0.who2.dispose();
                       }
                       GameFrame0.now=framered;
                       framered.setVisible(true);

                       framered.setLocation(800,100);
                       //背景图设置
                       ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
                       JLabel imgLabel = new JLabel(img);
                       framered.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                       imgLabel.setBounds(0, 0, 300, 100);
                       Container cp = framered.getContentPane();
                       cp.setLayout(null);
                       ((JPanel) cp).setOpaque(false);

                       JButton win = new JButton("红方胜!!!");
                       win.setBorder(null);
                       win.setContentAreaFilled(false);
                       win.setFont( new Font("华文隶书", Font.BOLD, 50) );
                       win.setForeground(Color.white);
                       win.setBounds(0, 0, 300, 40);
                       imgLabel.add(win);
                       win.addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               new GameFrame0();
                           }
                       });
                       return;
                   }
                   if(ChessBoard.isWinnerGreen()){
                       JFrame framered = new JFrame();
                       framered.setSize(300, 100);
                       if(GameFrame0.now!=null){
                           GameFrame0.now.dispose();
                       }
                       if(GameFrame0.who1!=null){
                           GameFrame0.who1.dispose();
                       }
                       if(GameFrame0.who2!=null){
                           GameFrame0.who2.dispose();
                       }
                       GameFrame0.now=framered;
                       framered.setVisible(true);
                       framered.setLocation(800,100);
                       //背景图设置
                       ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
                       JLabel imgLabel = new JLabel(img);
                       framered.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                       imgLabel.setBounds(0, 0, 300, 100);
                       Container cp = framered.getContentPane();
                       cp.setLayout(null);
                       ((JPanel) cp).setOpaque(false);

                       JButton win = new JButton("绿方胜!!!");
                       win.setBorder(null);
                       win.setContentAreaFilled(false);
                       win.setFont( new Font("华文隶书", Font.BOLD, 50) );
                       win.setForeground(Color.white);
                       win.setBounds(0, 0, 300, 40);
                       imgLabel.add(win);
                       win.addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               new GameFrame0();
                           }
                       });
                       return;
                   }
                   ChessBoard.hefa=new ChessBoardLocation(0,0);
                   nextPlayer();
               } else {
                   if (selectedLocation.getRow() - location.getRow() == 2 || selectedLocation.getRow() - location.getRow() == -2 ||
                           selectedLocation.getColumn() - location.getColumn() == -2 || selectedLocation.getColumn() - location.getColumn() == 2) {
                       if(ChessBoard.isWinnerRed()){
                           JFrame framered = new JFrame();
                           framered.setSize(300, 100);
                           if(GameFrame0.now!=null){
                               GameFrame0.now.dispose();
                           }
                           if(GameFrame0.who1!=null){
                               GameFrame0.who1.dispose();
                           }
                           if(GameFrame0.who2!=null){
                               GameFrame0.who2.dispose();
                           }
                           GameFrame0.now=framered;
                           framered.setVisible(true);

                           framered.setLocation(800,100);
                           //背景图设置
                           ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
                           JLabel imgLabel = new JLabel(img);
                           framered.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                           imgLabel.setBounds(0, 0, 300, 100);
                           Container cp = framered.getContentPane();
                           cp.setLayout(null);
                           ((JPanel) cp).setOpaque(false);

                           JButton win = new JButton("红方胜!!!");
                           win.setBorder(null);
                           win.setContentAreaFilled(false);
                           win.setFont( new Font("华文隶书", Font.BOLD, 50) );
                           win.setForeground(Color.white);
                           win.setBounds(0, 0, 300, 40);
                           imgLabel.add(win);
                           win.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   new GameFrame0();
                               }
                           });
                           return;
                       }
                       if(ChessBoard.isWinnerGreen()){
                           JFrame framered = new JFrame();
                           framered.setSize(300, 100);
                           if(GameFrame0.now!=null){
                               GameFrame0.now.dispose();
                           }
                           if(GameFrame0.who1!=null){
                               GameFrame0.who1.dispose();
                           }
                           if(GameFrame0.who2!=null){
                               GameFrame0.who2.dispose();
                           }
                           GameFrame0.now=framered;
                           framered.setVisible(true);
                           framered.setLocation(800,100);
                           //背景图设置
                           ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
                           JLabel imgLabel = new JLabel(img);
                           framered.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                           imgLabel.setBounds(0, 0, 300, 100);
                           Container cp = framered.getContentPane();
                           cp.setLayout(null);
                           ((JPanel) cp).setOpaque(false);

                           JButton win = new JButton("绿方胜!!!");
                           win.setBorder(null);
                           win.setContentAreaFilled(false);
                           win.setFont( new Font("华文隶书", Font.BOLD, 50) );
                           win.setForeground(Color.white);
                           win.setBounds(0, 0, 300, 40);
                           imgLabel.add(win);
                           win.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   new GameFrame0();
                               }
                           });
                           return;
                       }
                   }
               }
           }

           selectedPiece = null;
           selectedLocation = null;

        }
    }

    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        ChessPiece piece = model.getChessPieceAt(location);
        if (currentPlayer == Color.red) {
            JFrame framered = new JFrame("状态");
            framered.setSize(210, 250);
            if(GameFrame0.now!=null){
                GameFrame0.now.dispose();
            }
            if(GameFrame0.who1!=null){
                GameFrame0.who1.dispose();
            }
            if(GameFrame0.who2!=null){
                GameFrame0.who2.dispose();
            }
            GameFrame0.now=framered;
            framered.setVisible(true);
            JLabel labelred = new JLabel();
            labelred.setText("下棋中...");
            labelred.setFont( new Font("华文隶书", Font.BOLD, 30) );
            Color color=new Color(138, 0, 255, 255);
            labelred.setOpaque(true);
            labelred.setBackground(color);
            labelred.setForeground(Color.WHITE);
            framered.add(labelred);

        }
        if (currentPlayer == Color.GREEN) {
            JFrame framegreen = new JFrame("状态");
            framegreen.setSize(210, 250);
            if(GameFrame0.now!=null){
                GameFrame0.now.dispose();
            }
            if(GameFrame0.who1!=null){
                GameFrame0.who1.dispose();
            }
            if(GameFrame0.who2!=null){
                GameFrame0.who2.dispose();
            }
            GameFrame0.now=framegreen;
            framegreen.setVisible(true);
            JLabel labelgreen = new JLabel();
            labelgreen.setText("下棋中......");
            labelgreen.setFont( new Font("华文隶书", Font.BOLD, 30) );
            Color color=new Color(138, 0, 255, 255);
            labelgreen.setOpaque(true);
            labelgreen.setBackground(color);
            labelgreen.setForeground(Color.WHITE);
            framegreen.add(labelgreen);
        }

        if (piece.getColor() == currentPlayer && (selectedPiece == piece || selectedPiece == null)) {
            if (selectedPiece == null) {
                selectedPiece = piece;
                selectedLocation = location;
            } else {
                selectedPiece = null;
                selectedLocation = null;
            }
            component.setSelected(!component.isSelected());
            component.repaint();
        }
    }

}

