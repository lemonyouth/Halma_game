package xyz.chengzi.halma.model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ChessBoard2 {
    private static Square[][] grid;
    private int dimension;

    public static ChessBoardLocation lishi;
    public static ChessBoardLocation lishi2;
    public static Color huiqi;

    public static ChessBoardLocation hefa;
    public static ChessBoardLocation kebian=new ChessBoardLocation(0,0);

    public ChessBoard2(int dimension) {
        this.grid = new Square[dimension][dimension];
        this.dimension = dimension;

        initGrid();
        initPieces();
    }
    public ChessBoard2(int dimension,int a) throws FileNotFoundException {
        this.grid = new Square[dimension][dimension];
        this.dimension = dimension;

        initGrid();
        read();
    }

    private void initGrid() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                grid[i][j] = new Square(new ChessBoardLocation(i, j));
            }
        }
    }
    public void read() throws FileNotFoundException {
        FileReader fileReader = new FileReader("C:\\Users\\田宇琼\\Desktop\\四人读档.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        int counter = 0;
        int[][] out = new int[16][16];
        while (counter <= 15) {
            String strread = null;
            try {
                strread = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int[] a = new int[16];

            char[] chararray = strread.toCharArray();
            for (int i = 0; i < 32; i += 2) {
                out[counter][i / 2] = chararray[i] - 48;
            }
            counter++;
        }
        for (int i=0;i<16;i++){
            for (int j=0;j<16;j++){
                if (out[i][j]==1){
                    grid[i][j].setPiece(new ChessPiece(Color.BLACK));
                }
                else if (out[i][j]==4){
                    grid[i][j].setPiece(new ChessPiece(Color.RED));
                }
                else if (out[i][j]==3){
                    grid[i][j].setPiece(new ChessPiece(Color.WHITE));
                }
                else if (out[i][j]==2){
                    grid[i][j].setPiece(new ChessPiece(Color.GREEN));
                }
            }
        }
    }

    private void initPieces() {
        // TODO: This is only a demo implementation.
        grid[0][0].setPiece(new ChessPiece(Color.BLACK));
        grid[0][1].setPiece(new ChessPiece(Color.BLACK));
        grid[0][2].setPiece(new ChessPiece(Color.BLACK));
        grid[0][3].setPiece(new ChessPiece(Color.BLACK));
        grid[1][0].setPiece(new ChessPiece(Color.BLACK));
        grid[1][1].setPiece(new ChessPiece(Color.BLACK));
        grid[1][2].setPiece(new ChessPiece(Color.BLACK));
        grid[1][3].setPiece(new ChessPiece(Color.BLACK));
        grid[2][0].setPiece(new ChessPiece(Color.BLACK));
        grid[2][1].setPiece(new ChessPiece(Color.BLACK));
        grid[2][2].setPiece(new ChessPiece(Color.BLACK));
        grid[3][0].setPiece(new ChessPiece(Color.BLACK));
        grid[3][1].setPiece(new ChessPiece(Color.BLACK));

        grid[0][15].setPiece(new ChessPiece(Color.GREEN));
        grid[0][14].setPiece(new ChessPiece(Color.GREEN));
        grid[0][13].setPiece(new ChessPiece(Color.GREEN));
        grid[0][12].setPiece(new ChessPiece(Color.GREEN));
        grid[1][15].setPiece(new ChessPiece(Color.GREEN));
        grid[1][14].setPiece(new ChessPiece(Color.GREEN));
        grid[1][13].setPiece(new ChessPiece(Color.GREEN));
        grid[1][12].setPiece(new ChessPiece(Color.GREEN));
        grid[2][15].setPiece(new ChessPiece(Color.GREEN));
        grid[2][14].setPiece(new ChessPiece(Color.GREEN));
        grid[2][13].setPiece(new ChessPiece(Color.GREEN));
        grid[3][15].setPiece(new ChessPiece(Color.GREEN));
        grid[3][14].setPiece(new ChessPiece(Color.GREEN));

        grid[15][0].setPiece(new ChessPiece(Color.RED));
        grid[15][1].setPiece(new ChessPiece(Color.RED));
        grid[15][2].setPiece(new ChessPiece(Color.RED));
        grid[15][3].setPiece(new ChessPiece(Color.RED));
        grid[14][0].setPiece(new ChessPiece(Color.RED));
        grid[14][1].setPiece(new ChessPiece(Color.RED));
        grid[14][2].setPiece(new ChessPiece(Color.RED));
        grid[14][3].setPiece(new ChessPiece(Color.RED));
        grid[13][0].setPiece(new ChessPiece(Color.RED));
        grid[13][1].setPiece(new ChessPiece(Color.RED));
        grid[13][2].setPiece(new ChessPiece(Color.RED));
        grid[12][0].setPiece(new ChessPiece(Color.RED));
        grid[12][1].setPiece(new ChessPiece(Color.RED));

        grid[15][15].setPiece(new ChessPiece(Color.WHITE));
        grid[15][14].setPiece(new ChessPiece(Color.WHITE));
        grid[15][13].setPiece(new ChessPiece(Color.WHITE));
        grid[15][12].setPiece(new ChessPiece(Color.WHITE));
        grid[14][15].setPiece(new ChessPiece(Color.WHITE));
        grid[14][14].setPiece(new ChessPiece(Color.WHITE));
        grid[14][13].setPiece(new ChessPiece(Color.WHITE));
        grid[14][12].setPiece(new ChessPiece(Color.WHITE));
        grid[13][15].setPiece(new ChessPiece(Color.WHITE));
        grid[13][14].setPiece(new ChessPiece(Color.WHITE));
        grid[13][13].setPiece(new ChessPiece(Color.WHITE));
        grid[12][15].setPiece(new ChessPiece(Color.WHITE));
        grid[12][14].setPiece(new ChessPiece(Color.WHITE));
    }

    public Square getGridAt(ChessBoardLocation location) {
        return grid[location.getRow()][location.getColumn()];
    }

    public ChessPiece getChessPieceAt(ChessBoardLocation location) {
        return getGridAt(location).getPiece();
    }

    public void setChessPieceAt(ChessBoardLocation location, ChessPiece piece) {
        getGridAt(location).setPiece(piece);
    }

    public ChessPiece removeChessPieceAt(ChessBoardLocation location) {
        ChessPiece piece = getGridAt(location).getPiece();
        getGridAt(location).setPiece(null);
        return piece;
    }

    public void moveChessPiece(ChessBoardLocation src, ChessBoardLocation dest) {
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal halma move");
        }
        setChessPieceAt(dest, removeChessPieceAt(src));
    }

    public int getDimension() {
        return dimension;
    }

    public boolean isValidMove(ChessBoardLocation src, ChessBoardLocation dest) {
        lishi = src;
        lishi2 = dest;
        if(hefa.getColumn()==0 && hefa.getRow()==0) {
            kebian = src;
            int srcRow = src.getRow(), srcCol = src.getColumn(), destRow = dest.getRow(), destCol = dest.getColumn();
            int rowDistance = destRow - srcRow, colDistance = destCol - srcCol;
        /*if (rowDistance != 0 && colDistance != 0 && Math.abs((double) rowDistance / colDistance) != 1.0) {
            return false;
        }*/
            if ((getChessPieceAt(src) == null || getChessPieceAt(dest) != null) && (srcRow != destRow || srcCol != destCol)) {
                return false;
            }
            if (srcRow == destRow && srcCol == destCol) {
                return true;
            }

            if (Math.abs(rowDistance) <= 1 && Math.abs(colDistance) <= 1) {
                return true;
            }

            if (Math.abs(rowDistance) == 0 && src.getColumn() - dest.getColumn() == 2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow(), src.getColumn() - 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (Math.abs(rowDistance) == 0 && src.getColumn() - dest.getColumn() == -2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow(), src.getColumn() + 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == 2 && Math.abs(colDistance) == 0) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() - 1, src.getColumn());
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == -2 && Math.abs(colDistance) == 0) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() + 1, src.getColumn());
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == 2 && src.getColumn() - dest.getColumn() == 2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() - 1, src.getColumn() - 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == -2 && src.getColumn() - dest.getColumn() == -2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() + 1, src.getColumn() + 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == 2 && src.getColumn() - dest.getColumn() == -2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() - 1, src.getColumn() + 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == -2 && src.getColumn() - dest.getColumn() == 2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() + 1, src.getColumn() - 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
        }
        if(hefa.getRow()==src.getRow() && hefa.getColumn()==src.getColumn()) {
            int srcRow = src.getRow(), srcCol = src.getColumn(), destRow = dest.getRow(), destCol = dest.getColumn();
            int rowDistance = destRow - srcRow, colDistance = destCol - srcCol;
        /*if (rowDistance != 0 && colDistance != 0 && Math.abs((double) rowDistance / colDistance) != 1.0) {
            return false;
        }*/
            if ((getChessPieceAt(src) == null || getChessPieceAt(dest) != null) && (srcRow != destRow || srcCol != destCol)) {
                return false;
            }
            if (srcRow == destRow && srcCol == destCol) {
                return true;
            }

            if (Math.abs(rowDistance) == 0 && src.getColumn() - dest.getColumn() == 2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow(), src.getColumn() - 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (Math.abs(rowDistance) == 0 && src.getColumn() - dest.getColumn() == -2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow(), src.getColumn() + 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == 2 && Math.abs(colDistance) == 0) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() - 1, src.getColumn());
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == -2 && Math.abs(colDistance) == 0) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() + 1, src.getColumn());
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == 2 && src.getColumn() - dest.getColumn() == 2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() - 1, src.getColumn() - 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == -2 && src.getColumn() - dest.getColumn() == -2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() + 1, src.getColumn() + 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == 2 && src.getColumn() - dest.getColumn() == -2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() - 1, src.getColumn() + 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == -2 && src.getColumn() - dest.getColumn() == 2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() + 1, src.getColumn() - 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
        }

        if(hefa.getRow()==kebian.getRow() && hefa.getColumn()==kebian.getColumn()) {
            int srcRow = src.getRow(), srcCol = src.getColumn(), destRow = dest.getRow(), destCol = dest.getColumn();
            int rowDistance = destRow - srcRow, colDistance = destCol - srcCol;
        /*if (rowDistance != 0 && colDistance != 0 && Math.abs((double) rowDistance / colDistance) != 1.0) {
            return false;
        }*/
            if ((getChessPieceAt(src) == null || getChessPieceAt(dest) != null) && (srcRow != destRow || srcCol != destCol)) {
                return false;
            }
            if (srcRow == destRow && srcCol == destCol) {
                return true;
            }

            if (Math.abs(rowDistance) <= 1 && Math.abs(colDistance) <= 1) {
                return true;
            }

            if (Math.abs(rowDistance) == 0 && src.getColumn() - dest.getColumn() == 2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow(), src.getColumn() - 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (Math.abs(rowDistance) == 0 && src.getColumn() - dest.getColumn() == -2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow(), src.getColumn() + 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == 2 && Math.abs(colDistance) == 0) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() - 1, src.getColumn());
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == -2 && Math.abs(colDistance) == 0) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() + 1, src.getColumn());
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == 2 && src.getColumn() - dest.getColumn() == 2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() - 1, src.getColumn() - 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == -2 && src.getColumn() - dest.getColumn() == -2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() + 1, src.getColumn() + 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == 2 && src.getColumn() - dest.getColumn() == -2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() - 1, src.getColumn() + 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }
            if (src.getRow() - dest.getRow() == -2 && src.getColumn() - dest.getColumn() == 2) {
                ChessBoardLocation location = new ChessBoardLocation(src.getRow() + 1, src.getColumn() - 1);
                if (getChessPieceAt(location) != null) {
                    return true;
                }
            }

        }

        return false;
    }


    public static boolean isWinnerBlack(){
        /*ChessBoard.grid[15][15].getPiece()!=null &&
        ChessBoard.grid[15][14].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[15][13].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[15][12].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[14][15].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[14][14].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[14][13].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[14][12].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[13][15].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[13][14].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[13][13].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[12][15].getPiece().getColor()==Color.BLACK &&
        ChessBoard.grid[12][14].getPiece().getColor()==Color.BLACK*/

        if(ChessBoard2.grid[15][15].getPiece()!=null &&
                ChessBoard2.grid[15][14].getPiece()!=null &&
                ChessBoard2.grid[15][13].getPiece()!=null &&
                ChessBoard2.grid[15][12].getPiece()!=null &&
                ChessBoard2.grid[14][15].getPiece()!=null &&
                ChessBoard2.grid[14][14].getPiece()!=null &&
                ChessBoard2.grid[14][13].getPiece()!=null &&
                ChessBoard2.grid[14][12].getPiece()!=null &&
                ChessBoard2.grid[13][15].getPiece()!=null &&
                ChessBoard2.grid[13][14].getPiece()!=null &&
                ChessBoard2.grid[13][13].getPiece()!=null &&
                ChessBoard2.grid[12][15].getPiece()!=null &&
                ChessBoard2.grid[12][14].getPiece()!=null
        ){
            if(ChessBoard2.grid[15][15].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[15][14].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[15][13].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[15][12].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[14][15].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[14][14].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[14][13].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[14][12].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[13][15].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[13][14].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[13][13].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[12][15].getPiece().getColor()==Color.BLACK &&
                    ChessBoard2.grid[12][14].getPiece().getColor()==Color.BLACK){
                return true;
            }
        }
        return false;
    }
    public static boolean isWinnerWhite(){
        /*grid[0][0].setPiece(new ChessPiece(Color.BLACK));
        grid[0][1].setPiece(new ChessPiece(Color.BLACK));
        grid[0][2].setPiece(new ChessPiece(Color.BLACK));
        grid[0][3].setPiece(new ChessPiece(Color.BLACK));
        grid[1][0].setPiece(new ChessPiece(Color.BLACK));
        grid[1][1].setPiece(new ChessPiece(Color.BLACK));
        grid[1][2].setPiece(new ChessPiece(Color.BLACK));
        grid[1][3].setPiece(new ChessPiece(Color.BLACK));
        grid[2][0].setPiece(new ChessPiece(Color.BLACK));
        grid[2][1].setPiece(new ChessPiece(Color.BLACK));
        grid[2][2].setPiece(new ChessPiece(Color.BLACK));
        grid[3][0].setPiece(new ChessPiece(Color.BLACK));
        grid[3][1].setPiece(new ChessPiece(Color.BLACK));*/

        if(ChessBoard2.grid[0][0].getPiece()!=null &&
                ChessBoard2.grid[0][1].getPiece()!=null &&
                ChessBoard2.grid[0][2].getPiece()!=null &&
                ChessBoard2.grid[0][3].getPiece()!=null &&
                ChessBoard2.grid[1][0].getPiece()!=null &&
                ChessBoard2.grid[1][1].getPiece()!=null &&
                ChessBoard2.grid[1][2].getPiece()!=null &&
                ChessBoard2.grid[1][3].getPiece()!=null &&
                ChessBoard2.grid[2][0].getPiece()!=null &&
                ChessBoard2.grid[2][1].getPiece()!=null &&
                ChessBoard2.grid[2][2].getPiece()!=null &&
                ChessBoard2.grid[3][0].getPiece()!=null &&
                ChessBoard2.grid[3][1].getPiece()!=null
        ){
            if(ChessBoard2.grid[0][0].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[0][1].getPiece().getColor()==Color.WHITE&&
                    ChessBoard2.grid[0][2].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[0][3].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[1][0].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[1][1].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[1][2].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[1][3].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[2][0].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[2][1].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[2][2].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[3][0].getPiece().getColor()==Color.WHITE &&
                    ChessBoard2.grid[3][1].getPiece().getColor()==Color.WHITE){
                return true;
            }
        }
        return false;
    }
    public static boolean isWinnerRed(){
        /*grid[0][15].setPiece(new ChessPiece(Color.GREEN));
        grid[0][14].setPiece(new ChessPiece(Color.GREEN));
        grid[0][13].setPiece(new ChessPiece(Color.GREEN));
        grid[0][12].setPiece(new ChessPiece(Color.GREEN));
        grid[1][15].setPiece(new ChessPiece(Color.GREEN));
        grid[1][14].setPiece(new ChessPiece(Color.GREEN));
        grid[1][13].setPiece(new ChessPiece(Color.GREEN));
        grid[1][12].setPiece(new ChessPiece(Color.GREEN));
        grid[2][15].setPiece(new ChessPiece(Color.GREEN));
        grid[2][14].setPiece(new ChessPiece(Color.GREEN));
        grid[2][13].setPiece(new ChessPiece(Color.GREEN));
        grid[3][15].setPiece(new ChessPiece(Color.GREEN));
        grid[3][14].setPiece(new ChessPiece(Color.GREEN));*/

        if(ChessBoard2.grid[0][15].getPiece()!=null &&
                ChessBoard2.grid[0][14].getPiece()!=null &&
                ChessBoard2.grid[0][13].getPiece()!=null &&
                ChessBoard2.grid[0][12].getPiece()!=null &&
                ChessBoard2.grid[1][15].getPiece()!=null &&
                ChessBoard2.grid[1][14].getPiece()!=null &&
                ChessBoard2.grid[1][13].getPiece()!=null &&
                ChessBoard2.grid[1][12].getPiece()!=null &&
                ChessBoard2.grid[2][15].getPiece()!=null &&
                ChessBoard2.grid[2][14].getPiece()!=null &&
                ChessBoard2.grid[2][13].getPiece()!=null &&
                ChessBoard2.grid[3][15].getPiece()!=null &&
                ChessBoard2.grid[3][14].getPiece()!=null
        ){
            if(ChessBoard2.grid[0][15].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[0][14].getPiece().getColor()==Color.RED&&
                    ChessBoard2.grid[0][13].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[0][12].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[1][15].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[1][14].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[1][13].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[1][12].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[2][15].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[2][14].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[2][13].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[3][15].getPiece().getColor()==Color.RED &&
                    ChessBoard2.grid[3][14].getPiece().getColor()==Color.RED){
                return true;
            }
        }
        return false;
    }
    public static boolean isWinnerGreen(){
        /*grid[15][0].setPiece(new ChessPiece(Color.RED));
        grid[15][1].setPiece(new ChessPiece(Color.RED));
        grid[15][2].setPiece(new ChessPiece(Color.RED));
        grid[15][3].setPiece(new ChessPiece(Color.RED));
        grid[14][0].setPiece(new ChessPiece(Color.RED));
        grid[14][1].setPiece(new ChessPiece(Color.RED));
        grid[14][2].setPiece(new ChessPiece(Color.RED));
        grid[14][3].setPiece(new ChessPiece(Color.RED));
        grid[13][0].setPiece(new ChessPiece(Color.RED));
        grid[13][1].setPiece(new ChessPiece(Color.RED));
        grid[13][2].setPiece(new ChessPiece(Color.RED));
        grid[12][0].setPiece(new ChessPiece(Color.RED));
        grid[12][1].setPiece(new ChessPiece(Color.RED));*/

        if(ChessBoard2.grid[15][0].getPiece()!=null &&
                ChessBoard2.grid[15][1].getPiece()!=null &&
                ChessBoard2.grid[15][2].getPiece()!=null &&
                ChessBoard2.grid[15][3].getPiece()!=null &&
                ChessBoard2.grid[14][0].getPiece()!=null &&
                ChessBoard2.grid[14][1].getPiece()!=null &&
                ChessBoard2.grid[14][2].getPiece()!=null &&
                ChessBoard2.grid[14][3].getPiece()!=null &&
                ChessBoard2.grid[13][0].getPiece()!=null &&
                ChessBoard2.grid[13][1].getPiece()!=null &&
                ChessBoard2.grid[13][2].getPiece()!=null &&
                ChessBoard2.grid[12][0].getPiece()!=null &&
                ChessBoard2.grid[12][1].getPiece()!=null
        ){
            if(ChessBoard2.grid[15][0].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[15][1].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[15][2].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[15][3].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[14][0].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[14][1].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[14][2].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[14][3].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[13][0].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[13][1].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[13][2].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[12][0].getPiece().getColor()==Color.GREEN &&
                    ChessBoard2.grid[12][1].getPiece().getColor()==Color.GREEN){
                return true;
            }
        }
        return false;

    }


}
