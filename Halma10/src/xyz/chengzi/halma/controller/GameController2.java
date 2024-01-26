package xyz.chengzi.halma.controller;

import xyz.chengzi.halma.listener.GameListener;
import xyz.chengzi.halma.model.ChessBoard2;
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
import java.util.ArrayList;
import java.util.Scanner;

public class GameController2 implements GameListener {
    Scanner in=new Scanner(System.in);
    private ChessBoardComponent view;
    //private ChessBoard model;
    public static ChessBoard2 model2;
    public static Color currentPlayer;
    private ChessPiece selectedPiece;
    private ChessBoardLocation selectedLocation;

    public static ArrayList<String> winner = new ArrayList<>();

    public GameController2(ChessBoardComponent boardComponent, ChessBoard2 chessBoard) {
        /*this.view = boardComponent;
        this.model2 = chessBoard;

        this.currentPlayer = Color.BLACK;
        view.registerListener(this);
        initGameState();*/
        this.view = boardComponent;
        this.model2 = chessBoard;
        JFrame framewho=new JFrame("谁先下");
        framewho.setVisible(true);
        framewho.setSize(210,250);
        framewho.setLocation(0,100);
        if(GameFrame0.now!=null){
            GameFrame0.now.dispose();
        }
        GameFrame0.who2=framewho;
        
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
        buttonred.setBounds(45,25,100,45);
        JButton buttongreen=new JButton("绿棋先");
        buttongreen.setBorder(null);
        buttongreen.setContentAreaFilled(false);
        buttongreen.setFont( new Font("华文隶书", Font.BOLD, 30) );
        buttongreen.setForeground(Color.BLUE);
        framewho.add(buttongreen);
        buttongreen.setBounds(45,65,100,45);
        JButton buttonblack=new JButton("黑棋先");
        buttonblack.setBorder(null);
        buttonblack.setContentAreaFilled(false);
        buttonblack.setFont( new Font("华文隶书", Font.BOLD, 30) );
        buttonblack.setForeground(Color.BLUE);
        framewho.add(buttonblack);
        buttonblack.setBounds(45,105,100,45);
        JButton buttonwhite=new JButton("白棋先");
        buttonwhite.setBorder(null);
        buttonwhite.setContentAreaFilled(false);
        buttonwhite.setFont( new Font("华文隶书", Font.BOLD, 30) );
        buttonwhite.setForeground(Color.BLUE);
        framewho.add(buttonwhite);
        buttonwhite.setBounds(45,145,100,45);

        buttongreen.setVisible(true);
        buttonred.setVisible(true);
        buttonblack.setVisible(true);
        buttonwhite.setVisible(true);
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

        buttonwhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = Color.WHITE;
            }
        });

        buttonblack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = Color.BLACK;
            }
        });
        view.registerListener(this);
        initGameState();
    }


    public void initGameState() {
        for (int row = 0; row < model2.getDimension(); row++) {
            for (int col = 0; col < model2.getDimension(); col++) {
                ChessBoardLocation location = new ChessBoardLocation(row, col);
                ChessPiece piece = model2.getChessPieceAt(location);
                if (piece != null) {
                    view.setChessAtGrid(location, piece.getColor());
                }
            }
        }
        view.repaint();
    }

    public Color nextPlayer2() {
        //return currentPlayer = currentPlayer == Color.BLACK ? Color.GREEN : Color.BLACK;
        if (currentPlayer==Color.BLACK){
            return   currentPlayer=Color.RED;
        }
        if (currentPlayer==Color.RED){
          return currentPlayer= Color.WHITE;
        }
        if (currentPlayer==Color.WHITE){
           return currentPlayer= Color.GREEN;
        }
        if (currentPlayer==Color.GREEN){
            return currentPlayer=Color.BLACK;
        }
        else return null;
    }

    @Override
    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {

        if (selectedLocation != null && model2.isValidMove(selectedLocation, location)) {
            model2.moveChessPiece(selectedLocation, location);
            view.setChessAtGrid(location, selectedPiece.getColor());
            view.removeChessAtGrid(selectedLocation);
            view.repaint();
            ChessBoard2.huiqi=selectedPiece.getColor();
            ChessBoard2.hefa=location;

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
                    JLabel labelred = new JLabel("轮到白棋下了");
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
                    JLabel labelred = new JLabel("轮到黑棋下了");
                    labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                    Color color = new Color(138, 0, 255, 255);
                    labelred.setOpaque(true);
                    labelred.setBackground(color);
                    labelred.setForeground(Color.WHITE);
                    framegreen.add(labelred);
                }
            }
            if (currentPlayer == Color.BLACK) {
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
                    JLabel labelred = new JLabel("轮到黑棋下了");
                    labelred.setFont( new Font("华文隶书", Font.BOLD, 30) );
                    Color color=new Color(138, 0, 255, 255);
                    labelred.setOpaque(true);
                    labelred.setBackground(color);
                    labelred.setForeground(Color.WHITE);
                    framered.add(labelred);
                }else {
                    JFrame frameblack = new JFrame("状态");
                    frameblack.setSize(210, 250);
                    if(GameFrame0.now!=null){
                        GameFrame0.now.dispose();
                    }
                    if(GameFrame0.who1!=null){
                        GameFrame0.who1.dispose();
                    }
                    if(GameFrame0.who2!=null){
                        GameFrame0.who2.dispose();
                    }
                    GameFrame0.now=frameblack;
                    frameblack.setVisible(true);
                    JLabel labelred = new JLabel("轮到红棋下了");
                    labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                    Color color = new Color(138, 0, 255, 255);
                    labelred.setOpaque(true);
                    labelred.setBackground(color);
                    labelred.setForeground(Color.WHITE);
                    frameblack.add(labelred);
                }
            }
            if (currentPlayer == Color.WHITE) {
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
                    JLabel labelred = new JLabel("轮到白棋下了");
                    labelred.setFont( new Font("华文隶书", Font.BOLD, 30) );
                    Color color=new Color(138, 0, 255, 255);
                    labelred.setOpaque(true);
                    labelred.setBackground(color);
                    labelred.setForeground(Color.WHITE);
                    framered.add(labelred);
                }else {
                    JFrame framewhite = new JFrame("状态");
                    framewhite.setSize(210, 250);
                    if(GameFrame0.now!=null){
                        GameFrame0.now.dispose();
                    }
                    if(GameFrame0.who1!=null){
                        GameFrame0.who1.dispose();
                    }
                    if(GameFrame0.who2!=null){
                        GameFrame0.who2.dispose();
                    }
                    GameFrame0.now=framewhite;
                    framewhite.setVisible(true);
                    JLabel labelred = new JLabel("轮到绿棋下了");
                    labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                    Color color = new Color(138, 0, 255, 255);
                    labelred.setOpaque(true);
                    labelred.setBackground(color);
                    labelred.setForeground(Color.WHITE);
                    framewhite.add(labelred);
                }
            }

            int m=0;
            if(selectedLocation.getRow()-location.getRow()==1 || selectedLocation.getRow()-location.getRow()==-1 ||
                    selectedLocation.getColumn()-location.getColumn()==-1 || selectedLocation.getColumn()-location.getColumn()==1) {
                if(ChessBoard2.isWinnerRed()){
                    int index = winner.indexOf("红方");
                    if(index==-1) {
                        winner.add("红方");
                    }
                    if(winner.size()==3){
                        JFrame wrongreader=new JFrame("玩家排名");
                        wrongreader.setLocation(0,300);
                        wrongreader.setSize(300,300);
                        wrongreader.setVisible(true);
                        if(GameFrame0.now!=null){
                            GameFrame0.now.dispose();
                        }
                        if(GameFrame0.who1!=null){
                            GameFrame0.who1.dispose();
                        }
                        if(GameFrame0.who2!=null){
                            GameFrame0.who2.dispose();
                        }
                        GameFrame0.now=wrongreader;
                        JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                        labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                        Color color=new Color(48, 1, 185, 255);
                        labelred.setOpaque(true);
                        labelred.setBackground(color);
                        labelred.setForeground(Color.WHITE);
                        wrongreader.add(labelred);
                        return;
                    }
                }
                if(ChessBoard2.isWinnerGreen()){
                    int index = winner.indexOf("绿方");
                    if(index==-1) {
                        winner.add("绿方");
                    }
                    if(winner.size()==3){
                        JFrame wrongreader=new JFrame("玩家排名");
                        wrongreader.setLocation(0,300);
                        wrongreader.setSize(300,300);
                        wrongreader.setVisible(true);
                        if(GameFrame0.now!=null){
                            GameFrame0.now.dispose();
                        }
                        if(GameFrame0.who1!=null){
                            GameFrame0.who1.dispose();
                        }
                        if(GameFrame0.who2!=null){
                            GameFrame0.who2.dispose();
                        }
                        GameFrame0.now=wrongreader;
                        JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                        labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                        Color color=new Color(48, 1, 185, 255);
                        labelred.setOpaque(true);
                        labelred.setBackground(color);
                        labelred.setForeground(Color.WHITE);
                        wrongreader.add(labelred);
                        return;
                    }
                }
                if(ChessBoard2.isWinnerBlack()){
                    int index = winner.indexOf("黑方");
                    if(index==-1) {
                        winner.add("黑方");
                    }
                    if(winner.size()==3){
                        JFrame wrongreader=new JFrame("玩家排名");
                        wrongreader.setLocation(0,300);
                        wrongreader.setSize(300,300);
                        wrongreader.setVisible(true);
                        if(GameFrame0.now!=null){
                            GameFrame0.now.dispose();
                        }
                        if(GameFrame0.who1!=null){
                            GameFrame0.who1.dispose();
                        }
                        if(GameFrame0.who2!=null){
                            GameFrame0.who2.dispose();
                        }
                        GameFrame0.now=wrongreader;
                        JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                        labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                        Color color=new Color(48, 1, 185, 255);
                        labelred.setOpaque(true);
                        labelred.setBackground(color);
                        labelred.setForeground(Color.WHITE);
                        wrongreader.add(labelred);
                        return;
                    }
                }
                if(ChessBoard2.isWinnerWhite()){
                    int index = winner.indexOf("白方");
                    if(index==-1) {
                        winner.add("白方");
                    }
                    if(winner.size()==3){
                        JFrame wrongreader=new JFrame("玩家排名");
                        wrongreader.setLocation(0,300);
                        wrongreader.setSize(300,300);
                        wrongreader.setVisible(true);
                        if(GameFrame0.now!=null){
                            GameFrame0.now.dispose();
                        }
                        if(GameFrame0.who1!=null){
                            GameFrame0.who1.dispose();
                        }
                        if(GameFrame0.who2!=null){
                            GameFrame0.who2.dispose();
                        }
                        GameFrame0.now=wrongreader;
                        JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                        labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                        Color color=new Color(48, 1, 185, 255);
                        labelred.setOpaque(true);
                        labelred.setBackground(color);
                        labelred.setForeground(Color.WHITE);
                        wrongreader.add(labelred);
                        return;
                    }
                }
                ChessBoard2.hefa=new ChessBoardLocation(0,0);
                nextPlayer2();
            }else {

                if(location.getColumn()==0){
                    if(location.getRow()==0){
                        for (int i = 0; i <= location.getRow() + 1; i++) {
                            for (int j = 0; j <= location.getColumn() + 1; j++) {
                                ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                                if (model2.getChessPieceAt(location1) != null) {
                                    m++;
                                }
                            }
                        }
                    }
                    if(location.getRow()!=0 && location.getRow()!=15){
                        for (int i = location.getRow() - 1; i <= location.getRow() + 1; i++) {
                            for (int j = 0; j <= location.getColumn() + 1; j++) {
                                ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                                if (model2.getChessPieceAt(location1) != null) {
                                    m++;
                                }
                            }
                        }
                    }
                    if(location.getRow()==15){
                        for (int i = location.getRow() - 1; i <= location.getRow(); i++) {
                            for (int j = 0; j <= location.getColumn() + 1; j++) {
                                ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                                if (model2.getChessPieceAt(location1) != null) {
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
                                if (model2.getChessPieceAt(location1) != null) {
                                    m++;
                                }
                            }
                        }
                    }
                    if(location.getRow()!=0 && location.getRow()!=15){
                        for (int i = location.getRow() - 1; i <= location.getRow() + 1; i++) {
                            for (int j = location.getColumn()-1; j <= location.getColumn() + 1; j++) {
                                ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                                if (model2.getChessPieceAt(location1) != null) {
                                    m++;
                                }
                            }
                        }
                    }
                    if(location.getRow()==15){
                        for (int i = location.getRow() - 1; i <= location.getRow(); i++) {
                            for (int j = location.getColumn()-1; j <= location.getColumn() + 1; j++) {
                                ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                                if (model2.getChessPieceAt(location1) != null) {
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
                                if (model2.getChessPieceAt(location1) != null) {
                                    m++;
                                }
                            }
                        }
                    }
                    if(location.getRow()!=0 && location.getRow()!=15){
                        for (int i = location.getRow() - 1; i <= location.getRow() + 1; i++) {
                            for (int j = location.getColumn()-1; j <= location.getColumn(); j++) {
                                ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                                if (model2.getChessPieceAt(location1) != null) {
                                    m++;
                                }
                            }
                        }
                    }
                    if(location.getRow()==15){
                        for (int i = location.getRow() - 1; i <= location.getRow(); i++) {
                            for (int j = location.getColumn()-1; j <= location.getColumn(); j++) {
                                ChessBoardLocation location1 = new ChessBoardLocation(i, j);
                                if (model2.getChessPieceAt(location1) != null) {
                                    m++;
                                }
                            }
                        }
                    }
                }

                if (m == 1) {
                    if(ChessBoard2.isWinnerRed()){
                        int index = winner.indexOf("红方");
                        if(index==-1) {
                            winner.add("红方");
                        }
                        if(winner.size()==3){
                            JFrame wrongreader=new JFrame("玩家排名");
                            wrongreader.setLocation(0,300);
                            wrongreader.setSize(300,300);
                            wrongreader.setVisible(true);
                            if(GameFrame0.now!=null){
                                GameFrame0.now.dispose();
                            }
                            if(GameFrame0.who1!=null){
                                GameFrame0.who1.dispose();
                            }
                            if(GameFrame0.who2!=null){
                                GameFrame0.who2.dispose();
                            }
                            GameFrame0.now=wrongreader;
                            JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                            labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                            Color color=new Color(48, 1, 185, 255);
                            labelred.setOpaque(true);
                            labelred.setBackground(color);
                            labelred.setForeground(Color.WHITE);
                            wrongreader.add(labelred);
                            return;
                        }
                    }
                    if(ChessBoard2.isWinnerGreen()){
                        int index = winner.indexOf("绿方");
                        if(index==-1) {
                            winner.add("绿方");
                        }
                        if(winner.size()==3){
                            JFrame wrongreader=new JFrame("玩家排名");
                            wrongreader.setLocation(0,300);
                            wrongreader.setSize(300,300);
                            wrongreader.setVisible(true);
                            if(GameFrame0.now!=null){
                                GameFrame0.now.dispose();
                            }
                            if(GameFrame0.who1!=null){
                                GameFrame0.who1.dispose();
                            }
                            if(GameFrame0.who2!=null){
                                GameFrame0.who2.dispose();
                            }
                            GameFrame0.now=wrongreader;
                            JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                            labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                            Color color=new Color(48, 1, 185, 255);
                            labelred.setOpaque(true);
                            labelred.setBackground(color);
                            labelred.setForeground(Color.WHITE);
                            wrongreader.add(labelred);
                            return;
                        }
                    }
                    if(ChessBoard2.isWinnerBlack()){
                        int index = winner.indexOf("黑方");
                        if(index==-1) {
                            winner.add("黑方");
                        }
                        if(winner.size()==3){
                            JFrame wrongreader=new JFrame("玩家排名");
                            wrongreader.setLocation(0,300);
                            wrongreader.setSize(300,300);
                            wrongreader.setVisible(true);
                            if(GameFrame0.now!=null){
                                GameFrame0.now.dispose();
                            }
                            if(GameFrame0.who1!=null){
                                GameFrame0.who1.dispose();
                            }
                            if(GameFrame0.who2!=null){
                                GameFrame0.who2.dispose();
                            }
                            GameFrame0.now=wrongreader;
                            JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                            labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                            Color color=new Color(48, 1, 185, 255);
                            labelred.setOpaque(true);
                            labelred.setBackground(color);
                            labelred.setForeground(Color.WHITE);
                            wrongreader.add(labelred);
                            return;
                        }
                    }
                    if(ChessBoard2.isWinnerWhite()){
                        int index = winner.indexOf("白方");
                        if(index==-1) {
                            winner.add("白方");
                        }
                        if(winner.size()==3){
                            JFrame wrongreader=new JFrame("玩家排名");
                            wrongreader.setLocation(0,300);
                            wrongreader.setSize(300,300);
                            wrongreader.setVisible(true);
                            if(GameFrame0.now!=null){
                                GameFrame0.now.dispose();
                            }
                            if(GameFrame0.who1!=null){
                                GameFrame0.who1.dispose();
                            }
                            if(GameFrame0.who2!=null){
                                GameFrame0.who2.dispose();
                            }
                            GameFrame0.now=wrongreader;
                            JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                            labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                            Color color=new Color(48, 1, 185, 255);
                            labelred.setOpaque(true);
                            labelred.setBackground(color);
                            labelred.setForeground(Color.WHITE);
                            wrongreader.add(labelred);
                            return;
                        }
                    }
                    ChessBoard2.hefa=new ChessBoardLocation(0,0);
                    nextPlayer2();
                } else {
                    if (selectedLocation.getRow() - location.getRow() == 2 || selectedLocation.getRow() - location.getRow() == -2 ||
                            selectedLocation.getColumn() - location.getColumn() == -2 || selectedLocation.getColumn() - location.getColumn() == 2) {
                        if(ChessBoard2.isWinnerRed()){
                            int index = winner.indexOf("红方");
                            if(index==-1) {
                                winner.add("红方");
                            }
                            if(winner.size()==3){
                                JFrame wrongreader=new JFrame("玩家排名");
                                wrongreader.setLocation(0,300);
                                wrongreader.setSize(300,300);
                                wrongreader.setVisible(true);
                                if(GameFrame0.now!=null){
                                    GameFrame0.now.dispose();
                                }
                                if(GameFrame0.who1!=null){
                                    GameFrame0.who1.dispose();
                                }
                                if(GameFrame0.who2!=null){
                                    GameFrame0.who2.dispose();
                                }
                                GameFrame0.now=wrongreader;
                                JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                                labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                                Color color=new Color(48, 1, 185, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                wrongreader.add(labelred);
                                return;
                            }
                        }
                        if(ChessBoard2.isWinnerGreen()){
                            int index = winner.indexOf("绿方");
                            if(index==-1) {
                                winner.add("绿方");
                            }
                            if(winner.size()==3){
                                JFrame wrongreader=new JFrame("玩家排名");
                                wrongreader.setLocation(0,300);
                                wrongreader.setSize(300,300);
                                wrongreader.setVisible(true);
                                if(GameFrame0.now!=null){
                                    GameFrame0.now.dispose();
                                }
                                if(GameFrame0.who1!=null){
                                    GameFrame0.who1.dispose();
                                }
                                if(GameFrame0.who2!=null){
                                    GameFrame0.who2.dispose();
                                }
                                GameFrame0.now=wrongreader;
                                JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                                labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                                Color color=new Color(48, 1, 185, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                wrongreader.add(labelred);
                                return;
                            }
                        }
                        if(ChessBoard2.isWinnerBlack()){
                            int index = winner.indexOf("黑方");
                            if(index==-1) {
                                winner.add("黑方");
                            }
                            if(winner.size()==3){
                                JFrame wrongreader=new JFrame("玩家排名");
                                wrongreader.setLocation(0,300);
                                wrongreader.setSize(300,300);
                                wrongreader.setVisible(true);
                                if(GameFrame0.now!=null){
                                    GameFrame0.now.dispose();
                                }
                                if(GameFrame0.who1!=null){
                                    GameFrame0.who1.dispose();
                                }
                                if(GameFrame0.who2!=null){
                                    GameFrame0.who2.dispose();
                                }
                                GameFrame0.now=wrongreader;
                                JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                                labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                                Color color=new Color(48, 1, 185, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                wrongreader.add(labelred);
                                return;
                            }
                        }
                        if(ChessBoard2.isWinnerWhite()){
                            int index = winner.indexOf("白方");
                            if(index==-1) {
                                winner.add("白方");
                            }
                            if(winner.size()==3){
                                JFrame wrongreader=new JFrame("玩家排名");
                                wrongreader.setLocation(0,300);
                                wrongreader.setSize(300,300);
                                wrongreader.setVisible(true);
                                if(GameFrame0.now!=null){
                                    GameFrame0.now.dispose();
                                }
                                if(GameFrame0.who1!=null){
                                    GameFrame0.who1.dispose();
                                }
                                if(GameFrame0.who2!=null){
                                    GameFrame0.who2.dispose();
                                }
                                GameFrame0.now=wrongreader;
                                JLabel labelred = new JLabel("<html><body>"+"排名如下："+"<br>"+"<br>"+"第1名：" + winner.get(0) +"<br>"+"第2名：" + winner.get(1) +"<br>"+ "第3名：" + winner.get(2) +"<br>"+ "第4名：" + "猜猜看是谁呀~"+"<body></html>");
                                labelred.setFont( new Font("华文隶书", Font.BOLD, 25) );
                                Color color=new Color(48, 1, 185, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                wrongreader.add(labelred);
                                return;
                            }
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
        ChessPiece piece = model2.getChessPieceAt(location);
        //if (currentPlayer == Color.red||currentPlayer==Color.BLACK||currentPlayer==
        //Color.WHITE||currentPlayer==Color.GREEN) {
        JFrame framered = new JFrame("状态");
        framered.setSize(210, 250);
        framered.setVisible(true);
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

        JLabel labelred = new JLabel("下棋中......");
        labelred.setFont( new Font("华文隶书", Font.BOLD, 30) );
        Color color=new Color(138, 0, 255, 255);
        labelred.setOpaque(true);
        labelred.setBackground(color);
        labelred.setForeground(Color.WHITE);
        framered.add(labelred);

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

