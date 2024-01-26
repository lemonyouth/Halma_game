package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.controller.GameController2;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoard2;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class GameFrame0 extends JFrame {
    public static JFrame now;
    public static JFrame zhu;
    public static JFrame who1;
    public static JFrame who2;

    public void setCurrentPlayer1(Color color) {
        GameController.currentPlayer=color;
    }
    public void setCurrentPlayer2(Color color){
        GameController2.currentPlayer=color;
    }

    //static JFrame frame=new JFrame();
    public GameFrame0(){
        JFrame shou=new JFrame();
        shou.setSize(210,250);
        if(now!=null){
            now.dispose();
        }
        if(who1!=null){
            who1.dispose();
        }
        if(who2!=null){
            who2.dispose();
        }
        if(zhu!=null){
            zhu.dispose();
        }
        now=shou;
        shou.setLayout(null);

        //背景图设置
        ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
        JLabel imgLabel = new JLabel(img);
        shou.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        Container cp = shou.getContentPane();
        cp.setLayout(null);
        ((JPanel) cp).setOpaque(false);

        JButton b1=new JButton("两人");
        JButton b2=new JButton("四人");
        b1.setBorder(null);
        b1.setContentAreaFilled(false);
        b2.setBorder(null);
        b2.setContentAreaFilled(false);
        b1.setFont( new Font("华文隶书", Font.BOLD, 30) );
        b1.setForeground(Color.BLUE);
        b2.setFont( new Font("华文隶书", Font.BOLD, 30) );
        b2.setForeground(Color.BLUE);
        b1.setBounds(45, 75, 100, 45);
        b2.setBounds(45, 125, 100, 45);
        shou.add(b1);
        shou.add(b2);
        shou.setVisible(true);
        b1.setVisible(true);b2.setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("两人跳棋");
                frame.setSize(600, 545);
                if(zhu!=null) {
                    zhu.dispose();
                }
                if(now!=null){
                    now.dispose();
                }
                zhu=frame;
                frame.setLocationRelativeTo(null); // Center the window.
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLayout(null);
                frame.setVisible(true);

                ChessBoard.hefa=new ChessBoardLocation(0,0);

                //背景图设置
                ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
                JLabel imgLabel = new JLabel(img);
                frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
                Container cp = frame.getContentPane();
                cp.setLayout(null);
                ((JPanel) cp).setOpaque(false);

                ChessBoardComponent chessBoardComponent = new ChessBoardComponent(500, 16);
                ChessBoard chessBoard = null;
                try {
                    chessBoard = new ChessBoard(16);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                GameController controller = new GameController(chessBoardComponent, chessBoard);
                frame.add(chessBoardComponent);

                //换不换人按钮
                JButton button = new JButton("换人");
                button.setBorder(null);
                button.setContentAreaFilled(false);
                button.setFont( new Font("华文隶书", Font.BOLD, 30) );
                button.setForeground(Color.BLUE);
                button.setBounds(500, 100, 80, 40);
                imgLabel.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(GameController.currentPlayer == Color.red){
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
                        }else{
                            if(GameController.currentPlayer == Color.green){
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
                        ChessBoard.hefa=new ChessBoardLocation(0,0);
                        controller.nextPlayer();
                    }
                });

                //存档按钮
                int [][] readin=new int[16][16];
                JButton buttonin=new JButton("存档");
                buttonin.setBorder(null);
                buttonin.setContentAreaFilled(false);
                buttonin.setFont( new Font("华文隶书", Font.BOLD, 30) );
                buttonin.setForeground(Color.BLUE);
                buttonin.setBounds(500,200,80,40);
                buttonin.setVisible(true);
                imgLabel.add(buttonin);
                buttonin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        File file = new File("C:\\Users\\田宇琼\\Desktop\\a.txt");
                        try {
                            FileWriter out = new FileWriter(file);
                            for (int i=0;i<16;i++){
                                for (int j=0;j<16;j++){
                                    ChessBoardLocation location1=new ChessBoardLocation(i,j);
                                    if (GameController.model.getChessPieceAt(location1)!=null) {
                                        if (GameController.model.getChessPieceAt(location1).getColor().equals(Color.RED)) {
                                            readin[i][j] = 1;
                                        } else if (GameController.model.getChessPieceAt(location1).getColor().equals(Color.GREEN)) {
                                            readin[i][j] = 2;
                                        } else readin[i][j] = 0;
                                    }
                                }
                            }
                            for (int i=0;i<16;i++){
                                for (int j=0;j<16;j++){
                                    out.write(readin[i][j]+" ");
                                }
                                out.write("\r\n");//\r
                            }
                            if (GameController.currentPlayer==Color.RED){
                                out.write("Red");
                            }
                            else if (GameController.currentPlayer==Color.GREEN){
                                out.write("Green");
                            }
                            out.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }
                });

                //悔棋按钮
                JButton hui = new JButton("悔棋");
                hui.setBorder(null);
                hui.setContentAreaFilled(false);
                hui.setFont( new Font("华文隶书", Font.BOLD, 30) );
                hui.setForeground(Color.BLUE);
                hui.setBounds(500, 300, 80, 40);
                imgLabel.add(hui);
                hui.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color wu=new Color(0x0FFFFFF, true);
                        chessBoardComponent.setChessAtGrid(ChessBoard.lishi2,wu);
                       // ChessBoard.grid[ChessBoard.lishi2.getColumn()][ChessBoard.lishi2.getRow()].setPiece(new ChessPiece(wu));
                        chessBoardComponent.setChessAtGrid(ChessBoard.lishi,ChessBoard.huiqi);
                        //ChessBoard.grid[ChessBoard.lishi.getColumn()][ChessBoard.lishi.getRow()].setPiece(new ChessPiece(ChessBoard.huiqi));
                        GameController.model.moveChessPiece(ChessBoard.lishi2, ChessBoard.lishi);
                        GameController.model.getGridAt(ChessBoard.lishi);
                        GameController.model.getChessPieceAt(ChessBoard.lishi);
                        ChessPiece pice = new ChessPiece(ChessBoard.huiqi);
                        GameController.model.setChessPieceAt(ChessBoard.lishi,pice);
                        GameController.model.removeChessPieceAt(ChessBoard.lishi);
                        GameController.currentPlayer=ChessBoard.huiqi;
                        repaint();

                        if(ChessBoard.huiqi == Color.red){
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
                            labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                            Color color = new Color(138, 0, 255, 255);
                            labelred.setOpaque(true);
                            labelred.setBackground(color);
                            labelred.setForeground(Color.WHITE);
                            framered.add(labelred);
                        }else{
                            if(ChessBoard.huiqi == Color.green){
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
                                JLabel labelred = new JLabel("轮到绿棋下了");
                                labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                Color color = new Color(138, 0, 255, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                framegreen.add(labelred);
                            }
                        }
                    }
                });

                //首页按钮
                JButton shou = new JButton("首页");
                shou.setBorder(null);
                shou.setContentAreaFilled(false);
                shou.setFont( new Font("华文隶书", Font.BOLD, 30) );
                shou.setForeground(Color.BLUE);
                shou.setBounds(500, 450, 80, 40);
                imgLabel.add(shou);
                shou.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new GameFrame0();
                    }
                });

                //读档按钮
                /*JFrame frameout=new JFrame("读档");
                frameout.setSize(100,100);
                frameout.setLocation(300,300);
                frameout.setVisible(true);*/
                JButton readout=new JButton("读档");
                readout.setBorder(null);
                readout.setContentAreaFilled(false);
                readout.setFont( new Font("华文隶书", Font.BOLD, 30) );
                readout.setForeground(Color.BLUE);
                imgLabel.add(readout);
                readout.setBounds(500,0,80,40);
                readout.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            FileReader fileReader=new FileReader("C:\\Users\\田宇琼\\Desktop\\a.txt");
                            BufferedReader reader=new BufferedReader(fileReader);
                            int counter=0; int red=0;int green=0;
                            int[][] out=new int[16][16];

                                while (counter<=15){
                                String strread=reader.readLine();
                                int [] a=new int[16];

                                char[] chararray=strread.toCharArray();
                                for (int i=0;i<32;i+=2){
                                    out[counter][i/2]=chararray[i]-48;
                                    if (chararray[i]-48==1){
                                        red+=1;
                                    }
                                    if (chararray[i]-48==2){
                                        green+=1;
                                    }
                                }
                                counter++;
                            }

                            String who=reader.readLine();

                            if (green==19&&red==19) {
                                JFrame frame2 = new JFrame("两人跳棋");
                                frame2.setSize(600, 545);
                                if (now != null) {
                                    now.dispose();
                                }
                                if (who1 != null) {
                                    who1.dispose();
                                }
                                if (who2 != null) {
                                    who2.dispose();
                                }
                                if (zhu != null) {
                                    zhu.dispose();
                                }
                                zhu = frame2;
                                frame2.setLocationRelativeTo(null); // Center the window.
                                frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                                frame2.setLayout(null);
                                frame2.setVisible(true);

                                ChessBoard.hefa=new ChessBoardLocation(0,0);

                                //背景图设置
                                ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
                                JLabel imgLabel = new JLabel(img);
                                frame2.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                                imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
                                Container cp = frame2.getContentPane();
                                cp.setLayout(null);
                                ((JPanel) cp).setOpaque(false);

                                ChessBoardComponent chessBoardComponent = new ChessBoardComponent(500, 16);
                                ChessBoard chessBoard = new ChessBoard(16, 0);
                                GameController controller = new GameController(chessBoardComponent, chessBoard);

                                if(who!=null) {
                                    if (who.equals("Red")) {
                                        setCurrentPlayer1(Color.RED);
                                    }
                                    if (who.equals("Green")) {
                                        setCurrentPlayer1(Color.GREEN);
                                    }
                                }

                                frame2.add(chessBoardComponent);

                            //换不换人按钮
                            JButton button = new JButton("换人");
                            button.setBorder(null);
                            button.setContentAreaFilled(false);
                            button.setFont( new Font("华文隶书", Font.BOLD, 30) );
                            button.setForeground(Color.BLUE);
                            button.setBounds(500, 100, 80, 40);
                            imgLabel.add(button);
                            button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(GameController.currentPlayer == Color.red){
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
                                    }else{
                                        if(GameController.currentPlayer == Color.green){
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
                                    ChessBoard.hefa=new ChessBoardLocation(0,0);
                                    controller.nextPlayer();
                                }
                            });

                            //存档按钮
                            int [][] readin=new int[16][16];
                            JButton buttonin=new JButton("存档");
                            buttonin.setBorder(null);
                            buttonin.setContentAreaFilled(false);
                            buttonin.setFont( new Font("华文隶书", Font.BOLD, 30) );
                            buttonin.setForeground(Color.BLUE);
                            buttonin.setBounds(500,200,80,40);
                            buttonin.setVisible(true);
                            imgLabel.add(buttonin);
                            buttonin.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    File file = new File("C:\\Users\\田宇琼\\Desktop\\a.txt");
                                    try {
                                        FileWriter out = new FileWriter(file);
                                        for (int i=0;i<16;i++){
                                            for (int j=0;j<16;j++){
                                                ChessBoardLocation location1=new ChessBoardLocation(i,j);
                                                if (GameController.model.getChessPieceAt(location1)!=null) {
                                                    if (GameController.model.getChessPieceAt(location1).getColor().equals(Color.RED)) {
                                                        readin[i][j] = 1;
                                                    } else if (GameController.model.getChessPieceAt(location1).getColor().equals(Color.GREEN)) {
                                                        readin[i][j] = 2;
                                                    } else readin[i][j] = 0;
                                                }
                                            }
                                        }
                                        for (int i=0;i<16;i++){
                                            for (int j=0;j<16;j++){
                                                out.write(readin[i][j]+" ");
                                            }
                                            out.write("\r\n");//\r
                                        }
                                        if (GameController.currentPlayer==Color.RED){
                                            out.write("Red");
                                        }
                                        else if (GameController.currentPlayer==Color.GREEN){
                                            out.write("Green");
                                        }
                                        out.close();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });

                                //悔棋按钮
                                JButton hui = new JButton("悔棋");
                                hui.setBorder(null);
                                hui.setContentAreaFilled(false);
                                hui.setFont( new Font("华文隶书", Font.BOLD, 30) );
                                hui.setForeground(Color.BLUE);
                                hui.setBounds(500, 300, 80, 40);
                                imgLabel.add(hui);
                                hui.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        Color wu=new Color(0x0FFFFFF, true);
                                        chessBoardComponent.setChessAtGrid(ChessBoard.lishi2,wu);
                                        chessBoardComponent.setChessAtGrid(ChessBoard.lishi,ChessBoard.huiqi);
                                        GameController.model.moveChessPiece(ChessBoard.lishi2, ChessBoard.lishi);
                                        GameController.model.getChessPieceAt(ChessBoard.lishi);
                                        GameController.currentPlayer=ChessBoard.huiqi;
                                        repaint();

                                        if(ChessBoard.huiqi == Color.red){
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
                                            labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                            Color color = new Color(138, 0, 255, 255);
                                            labelred.setOpaque(true);
                                            labelred.setBackground(color);
                                            labelred.setForeground(Color.WHITE);
                                            framered.add(labelred);
                                        }else{
                                            if(ChessBoard.huiqi == Color.green){
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
                                                JLabel labelred = new JLabel("轮到绿棋下了");
                                                labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                                Color color = new Color(138, 0, 255, 255);
                                                labelred.setOpaque(true);
                                                labelred.setBackground(color);
                                                labelred.setForeground(Color.WHITE);
                                                framegreen.add(labelred);
                                            }
                                        }
                                    }
                                });

                                //首页按钮
                            JButton shou = new JButton("首页");
                            shou.setBorder(null);
                            shou.setContentAreaFilled(false);
                            shou.setFont( new Font("华文隶书", Font.BOLD, 30) );
                            shou.setForeground(Color.BLUE);
                            shou.setBounds(500, 450, 80, 40);
                            imgLabel.add(shou);
                            shou.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    new GameFrame0();
                                }
                            });

                            if(GameController.currentPlayer == Color.red){
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
                                labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                Color color = new Color(138, 0, 255, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                framered.add(labelred);
                            }else{
                                if(GameController.currentPlayer == Color.green){
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
                                    JLabel labelred = new JLabel("轮到绿棋下了");
                                    labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                    Color color = new Color(138, 0, 255, 255);
                                    labelred.setOpaque(true);
                                    labelred.setBackground(color);
                                    labelred.setForeground(Color.WHITE);
                                    framegreen.add(labelred);
                                }
                            }
                            }
                            else {
                                JFrame wrongreader=new JFrame("读档错误");
                                wrongreader.setLocation(800,500);
                                wrongreader.setSize(275,100);
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
                                JLabel labelred = new JLabel("Wrong Read!");
                                labelred.setFont( new Font("华文隶书", Font.BOLD, 45) );
                                Color color=new Color(255, 9, 97, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                wrongreader.add(labelred);
                            }

                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });


            }
        });


        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("四人跳棋");
                frame.setSize(600, 545);
                if(zhu!=null) {
                    zhu.dispose();
                }
                zhu=frame;
                frame.setLocationRelativeTo(null); // Center the window.
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLayout(null);
                frame.setVisible(true);

                ChessBoard2.hefa=new ChessBoardLocation(0,0);

                //背景图设置
                ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
                JLabel imgLabel = new JLabel(img);
                frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
                Container cp = frame.getContentPane();
                cp.setLayout(null);
                ((JPanel) cp).setOpaque(false);

                ChessBoardComponent chessBoardComponent = new ChessBoardComponent(500, 16);
                ChessBoard2 chessBoard = null;
                chessBoard = new ChessBoard2(16);
                GameController2 controller = new GameController2(chessBoardComponent, chessBoard);
                frame.add(chessBoardComponent);

                //读档按钮
                /*JFrame frameout=new JFrame("读档");
                frameout.setSize(100,100);
                frameout.setLocation(300,300);
                frameout.setVisible(true);*/
                JButton readout=new JButton("读档");
                readout.setBorder(null);
                readout.setContentAreaFilled(false);
                readout.setFont( new Font("华文隶书", Font.BOLD, 30) );
                readout.setForeground(Color.BLUE);
                frame.add(readout);
                readout.setBounds(500,0,80,40);

                readout.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            FileReader fileReader=new FileReader("C:\\Users\\田宇琼\\Desktop\\四人读档.txt");
                            int red=0;int green=0;int black=0;int white=0;
                            BufferedReader reader=new BufferedReader(fileReader);
                            int counter=0;
                            int[][] out=new int[16][16];

                            while (counter<=15){
                                String strread=reader.readLine();
                                int [] a=new int[16];

                                char[] chararray=strread.toCharArray();
                                for (int i=0;i<32;i+=2){
                                    out[counter][i/2]=chararray[i]-48;
                                    if (chararray[i]-48==1){
                                        black+=1;
                                    }
                                    else if (chararray[i]-48==2){
                                        red+=1;
                                    }
                                    else if (chararray[i]-48==3){
                                        white+=1;
                                    }
                                    else if (chararray[i]-48==4){
                                        green+=1;
                                    }
                                }
                                counter++;
                            }
                            String who=reader.readLine();
                            if (black==13&&red==13&&white==13&&green==13) {
                            JFrame frame2=new JFrame("四人跳棋");
                            frame2.setSize(600, 545);
                            if(now!=null){
                                now.dispose();
                            }
                            if(who1!=null){
                                who1.dispose();
                            }
                            if(who2!=null){
                                who2.dispose();
                            }
                            if(zhu!=null){
                                zhu.dispose();
                            }
                            zhu=frame2;
                            frame2.setLocationRelativeTo(null); // Center the window.
                            frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            frame2.setLayout(null);
                            frame2.setVisible(true);

                            ChessBoard2.hefa=new ChessBoardLocation(0,0);

                                //背景图设置
                            ImageIcon img = new ImageIcon("C:\\田宇琼\\b.jpg");
                            JLabel imgLabel = new JLabel(img);
                            frame2.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                            imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
                            Container cp = frame2.getContentPane();
                            cp.setLayout(null);
                            ((JPanel) cp).setOpaque(false);

                            ChessBoardComponent chessBoardComponent = new ChessBoardComponent(500, 16);
                            ChessBoard2 chessBoard = new ChessBoard2(16,0);
                            GameController2 controller = new GameController2(chessBoardComponent, chessBoard);

                            if(who!=null) {
                                if (who.equals("Red")) {
                                    setCurrentPlayer2(Color.RED);
                                }
                                if (who.equals("Green")) {
                                    setCurrentPlayer2(Color.GREEN);
                                }
                                if (who.equals("Black")) {
                                    setCurrentPlayer2(Color.BLACK);
                                }
                                if (who.equals("White")) {
                                    setCurrentPlayer2(Color.WHITE);
                                }
                            }

                            frame2.add(chessBoardComponent);

                            String duWin = reader.readLine();
                            ArrayList<String> shengzhe = new ArrayList<>();
                            if(duWin.length()==2){
                                GameController2.winner=shengzhe;
                            }
                            if(duWin.length()==4){
                                String a=duWin.substring(1,3);
                                shengzhe.add(a);
                                GameController2.winner=shengzhe;
                            }
                            if(duWin.length()==8){
                                String a=duWin.substring(1,3);
                                String b=duWin.substring(5,7);
                                shengzhe.add(a);
                                shengzhe.add(b);
                                GameController2.winner=shengzhe;
                            }

                            JButton button = new JButton("换人");
                            button.setBorder(null);
                            button.setContentAreaFilled(false);
                            button.setFont( new Font("华文隶书", Font.BOLD, 30) );
                            button.setForeground(Color.BLUE);
                            button.setBounds(500, 100, 80, 40);
                            imgLabel.add(button);
                            button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(GameController2.currentPlayer == Color.red){
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
                                    }else
                                    if(GameController2.currentPlayer == Color.green){
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
                                    }else
                                    if(GameController2.currentPlayer == Color.black){
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
                                    }else
                                    if(GameController2.currentPlayer == Color.white){
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
                                    ChessBoard2.hefa=new ChessBoardLocation(0,0);
                                    controller.nextPlayer2();
                                }
                            });

                            //存档按钮
                            int [][] readin=new int[16][16];
                            JButton buttonin=new JButton("存档");
                            buttonin.setBorder(null);
                            buttonin.setContentAreaFilled(false);
                            buttonin.setFont( new Font("华文隶书", Font.BOLD, 30) );
                            buttonin.setForeground(Color.BLUE);
                            buttonin.setBounds(500,200,80,40);
                            buttonin.setVisible(true);
                            imgLabel.add(buttonin);
                            buttonin.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    File file = new File("C:\\Users\\田宇琼\\Desktop\\四人读档.txt");
                                    try {
                                        FileWriter out = new FileWriter(file);
                                        for (int i=0;i<16;i++){
                                            for (int j=0;j<16;j++){
                                                ChessBoardLocation location1=new ChessBoardLocation(i,j);
                                                if (GameController2.model2.getChessPieceAt(location1)!=null) {
                                                    if (GameController2.model2.getChessPieceAt(location1).getColor().equals(Color.BLACK)) {
                                                        readin[i][j] = 1;
                                                    } else if (GameController2.model2.getChessPieceAt(location1).getColor().equals(Color.RED)) {
                                                        readin[i][j] = 4;
                                                    } else if (GameController2.model2.getChessPieceAt(location1).getColor().equals(Color.WHITE)){
                                                        readin[i][j]=3;
                                                    }else if (GameController2.model2.getChessPieceAt(location1).getColor().equals(Color.GREEN)){
                                                        readin[i][j]=2;
                                                    }
                                                    else readin[i][j] = 0;
                                                }
                                            }
                                        }
                                        for (int i=0;i<16;i++){
                                            for (int j=0;j<16;j++){
                                                out.write(readin[i][j]+" ");
                                            }
                                            out.write("\r\n");
                                        }
                                        if (GameController2.currentPlayer==Color.RED){
                                            out.write("Red");
                                            out.write("\r\n");
                                        }
                                        else if (GameController2.currentPlayer==Color.GREEN){
                                            out.write("Green");
                                            out.write("\r\n");
                                        }
                                        else if (GameController2.currentPlayer==Color.BLACK){
                                            out.write("Black");
                                            out.write("\r\n");
                                        }
                                        else if (GameController2.currentPlayer==Color.WHITE){
                                            out.write("White");
                                            out.write("\r\n");
                                        }
                                        ArrayList<String> win = GameController2.winner;
                                        out.write(win.toString());
                                        out.close();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });

                                //悔棋按钮
                                JButton hui = new JButton("悔棋");
                                hui.setBorder(null);
                                hui.setContentAreaFilled(false);
                                hui.setFont( new Font("华文隶书", Font.BOLD, 30) );
                                hui.setForeground(Color.BLUE);
                                hui.setBounds(500, 300, 80, 40);
                                imgLabel.add(hui);
                                hui.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        Color wu=new Color(0x0FFFFFF, true);
                                        chessBoardComponent.setChessAtGrid(ChessBoard2.lishi2,wu);
                                        chessBoardComponent.setChessAtGrid(ChessBoard2.lishi,ChessBoard2.huiqi);
                                        GameController2.model2.moveChessPiece(ChessBoard2.lishi2, ChessBoard2.lishi);
                                        GameController2.model2.getChessPieceAt(ChessBoard2.lishi);
                                        GameController2.currentPlayer=ChessBoard2.huiqi;
                                        repaint();

                                        if(ChessBoard2.huiqi == Color.red){
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
                                            labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                            Color color = new Color(138, 0, 255, 255);
                                            labelred.setOpaque(true);
                                            labelred.setBackground(color);
                                            labelred.setForeground(Color.WHITE);
                                            framered.add(labelred);
                                        }else{
                                            if(ChessBoard2.huiqi == Color.green){
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
                                                JLabel labelred = new JLabel("轮到绿棋下了");
                                                labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                                Color color = new Color(138, 0, 255, 255);
                                                labelred.setOpaque(true);
                                                labelred.setBackground(color);
                                                labelred.setForeground(Color.WHITE);
                                                framegreen.add(labelred);
                                            }else {
                                                if(ChessBoard2.huiqi == Color.black){
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
                                                }else{
                                                    if(ChessBoard2.huiqi == Color.white){
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
                                                        JLabel labelred = new JLabel("轮到白棋下了");
                                                        labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                                        Color color = new Color(138, 0, 255, 255);
                                                        labelred.setOpaque(true);
                                                        labelred.setBackground(color);
                                                        labelred.setForeground(Color.WHITE);
                                                        framegreen.add(labelred);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });

                            //首页按钮
                            JButton shou = new JButton("首页");
                            shou.setBorder(null);
                            shou.setContentAreaFilled(false);
                            shou.setFont( new Font("华文隶书", Font.BOLD, 30) );
                            shou.setForeground(Color.BLUE);
                            shou.setBounds(500, 450, 80, 40);
                            imgLabel.add(shou);
                            shou.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    new GameFrame0();
                                }
                            });

                            if(GameController2.currentPlayer == Color.red){
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
                                labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                Color color = new Color(138, 0, 255, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                framered.add(labelred);
                            }else
                            if(GameController2.currentPlayer == Color.green){
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
                                JLabel labelred = new JLabel("轮到绿棋下了");
                                labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                Color color = new Color(138, 0, 255, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                framegreen.add(labelred);
                            }else
                            if(GameController2.currentPlayer == Color.black){
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
                                JLabel labelred = new JLabel("轮到黑棋下了");
                                labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                Color color = new Color(138, 0, 255, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                frameblack.add(labelred);
                            }else
                            if(GameController2.currentPlayer == Color.white){
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
                                JLabel labelred = new JLabel("轮到白棋下了");
                                labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                Color color = new Color(138, 0, 255, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                framewhite.add(labelred);
                            }
                            }
                            else {
                                JFrame wrongreader=new JFrame("错误");
                                wrongreader.setLocation(800,500);
                                wrongreader.setSize(275,100);
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
                                JLabel labelred = new JLabel("Wrong Read!");
                                labelred.setFont( new Font("华文隶书", Font.BOLD, 45) );
                                Color color=new Color(255, 9, 97, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                wrongreader.add(labelred);
                            }

                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                //换人按钮
                /*JFrame changeplay = new JFrame("是否换人");
                changeplay.setSize(210, 250);
                changeplay.setVisible(true);
                //背景图设置
                ImageIcon img = new ImageIcon("D:\\b.jpg");
                JLabel imgLabel = new JLabel(img);
                changeplay.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
                imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
                Container cp = changeplay.getContentPane();
                cp.setLayout(null);
                ((JPanel) cp).setOpaque(false);*/
                JButton button = new JButton("换人");
                button.setBorder(null);
                button.setContentAreaFilled(false);
                button.setFont( new Font("华文隶书", Font.BOLD, 30) );
                button.setForeground(Color.BLUE);
                button.setBounds(500, 100, 80, 40);
                imgLabel.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(GameController2.currentPlayer == Color.red){
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
                        }else
                        if(GameController2.currentPlayer == Color.green){
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
                        }else
                        if(GameController2.currentPlayer == Color.black){
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
                        }else
                        if(GameController2.currentPlayer == Color.white){
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
                        ChessBoard2.hefa=new ChessBoardLocation(0,0);
                        controller.nextPlayer2();
                    }
                });

                //存档按钮
                int [][] readin=new int[16][16];
                JButton buttonin=new JButton("存档");
                buttonin.setBorder(null);
                buttonin.setContentAreaFilled(false);
                buttonin.setFont( new Font("华文隶书", Font.BOLD, 30) );
                buttonin.setForeground(Color.BLUE);
                buttonin.setBounds(500,200,80,40);
                buttonin.setVisible(true);
                imgLabel.add(buttonin);
                buttonin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        File file = new File("C:\\Users\\田宇琼\\Desktop\\四人读档.txt");
                        try {
                            FileWriter out = new FileWriter(file);
                            for (int i=0;i<16;i++){
                                for (int j=0;j<16;j++){
                                    ChessBoardLocation location1=new ChessBoardLocation(i,j);
                                    if (GameController2.model2.getChessPieceAt(location1)!=null) {
                                        if (GameController2.model2.getChessPieceAt(location1).getColor().equals(Color.BLACK)) {
                                            readin[i][j] = 1;
                                        } else if (GameController2.model2.getChessPieceAt(location1).getColor().equals(Color.RED)) {
                                            readin[i][j] = 4;
                                        } else if (GameController2.model2.getChessPieceAt(location1).getColor().equals(Color.WHITE)){
                                            readin[i][j]=3;
                                        }else if (GameController2.model2.getChessPieceAt(location1).getColor().equals(Color.GREEN)){
                                            readin[i][j]=2;
                                        }
                                        else readin[i][j] = 0;
                                    }
                                }
                            }
                            for (int i=0;i<16;i++){
                                for (int j=0;j<16;j++){
                                    out.write(readin[i][j]+" ");
                                }
                                out.write("\r\n");//\r
                            }
                            if (GameController2.currentPlayer==Color.RED){
                                out.write("Red");
                                out.write("\r\n");
                            }
                            else if (GameController2.currentPlayer==Color.GREEN){
                                out.write("Green");
                                out.write("\r\n");
                            }
                            else if (GameController2.currentPlayer==Color.BLACK){
                                out.write("Black");
                                out.write("\r\n");
                            }
                            else if (GameController2.currentPlayer==Color.WHITE){
                                out.write("White");
                                out.write("\r\n");
                            }
                            ArrayList<String> win = GameController2.winner;
                            out.write(win.toString());
                            out.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                //悔棋按钮
                JButton hui = new JButton("悔棋");
                hui.setBorder(null);
                hui.setContentAreaFilled(false);
                hui.setFont( new Font("华文隶书", Font.BOLD, 30) );
                hui.setForeground(Color.BLUE);
                hui.setBounds(500, 300, 80, 40);
                imgLabel.add(hui);
                hui.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color wu=new Color(0x0FFFFFF, true);
                        chessBoardComponent.setChessAtGrid(ChessBoard2.lishi2,wu);
                        chessBoardComponent.setChessAtGrid(ChessBoard2.lishi,ChessBoard2.huiqi);
                        GameController2.model2.moveChessPiece(ChessBoard2.lishi2, ChessBoard2.lishi);
                        GameController2.model2.getChessPieceAt(ChessBoard2.lishi);
                        GameController2.currentPlayer=ChessBoard2.huiqi;
                        repaint();

                        if(ChessBoard2.huiqi == Color.red){
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
                            labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                            Color color = new Color(138, 0, 255, 255);
                            labelred.setOpaque(true);
                            labelred.setBackground(color);
                            labelred.setForeground(Color.WHITE);
                            framered.add(labelred);
                        }else{
                            if(ChessBoard2.huiqi == Color.green){
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
                                JLabel labelred = new JLabel("轮到绿棋下了");
                                labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                Color color = new Color(138, 0, 255, 255);
                                labelred.setOpaque(true);
                                labelred.setBackground(color);
                                labelred.setForeground(Color.WHITE);
                                framegreen.add(labelred);
                            }else {
                                if(ChessBoard2.huiqi == Color.black){
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
                                }else{
                                    if(ChessBoard2.huiqi == Color.white){
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
                                        JLabel labelred = new JLabel("轮到白棋下了");
                                        labelred.setFont(new Font("华文隶书", Font.BOLD, 30));
                                        Color color = new Color(138, 0, 255, 255);
                                        labelred.setOpaque(true);
                                        labelred.setBackground(color);
                                        labelred.setForeground(Color.WHITE);
                                        framegreen.add(labelred);
                                    }
                                }
                            }
                        }
                    }
                });


                //首页按钮
                JButton shou = new JButton("首页");
                shou.setBorder(null);
                shou.setContentAreaFilled(false);
                shou.setFont( new Font("华文隶书", Font.BOLD, 30) );
                shou.setForeground(Color.BLUE);
                shou.setBounds(500, 450, 80, 40);
                imgLabel.add(shou);
                shou.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new GameFrame0();
                    }
                });

            }
        });

    }
}
