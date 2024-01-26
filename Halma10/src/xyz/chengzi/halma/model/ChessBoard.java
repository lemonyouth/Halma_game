package xyz.chengzi.halma.model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ChessBoard {
    private static Square[][] grid;
    private static int dimension;

    public static ChessBoardLocation lishi;
    public static ChessBoardLocation lishi2;
    public static Color huiqi;

    public static ChessBoardLocation hefa;
    public static ChessBoardLocation kebian=new ChessBoardLocation(0,0);

    public ChessBoard(int dimension) throws FileNotFoundException {
        this.grid = new Square[dimension][dimension];
        this.dimension = dimension;

        initGrid();
        initPieces();
    }
    public ChessBoard(int dimension,int a) throws FileNotFoundException {
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
        FileReader fileReader = new FileReader("C:\\Users\\田宇琼\\Desktop\\a.txt");
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
                    grid[i][j].setPiece(new ChessPiece(Color.RED));
                }
                else if (out[i][j]==2){
                    grid[i][j].setPiece(new ChessPiece(Color.GREEN));
                }
            }
        }
    }


    private void initPieces() {
        // TODO: This is only a demo implementation.
        grid[0][0].setPiece(new ChessPiece(Color.RED));
        grid[0][1].setPiece(new ChessPiece(Color.RED));
        grid[0][2].setPiece(new ChessPiece(Color.RED));
        grid[0][3].setPiece(new ChessPiece(Color.RED));
        grid[0][4].setPiece(new ChessPiece(Color.RED));
        grid[1][0].setPiece(new ChessPiece(Color.RED));
        grid[1][1].setPiece(new ChessPiece(Color.RED));
        grid[1][2].setPiece(new ChessPiece(Color.RED));
        grid[1][3].setPiece(new ChessPiece(Color.RED));
        grid[1][4].setPiece(new ChessPiece(Color.RED));
        grid[2][0].setPiece(new ChessPiece(Color.RED));
        grid[2][1].setPiece(new ChessPiece(Color.RED));
        grid[2][2].setPiece(new ChessPiece(Color.RED));
        grid[2][3].setPiece(new ChessPiece(Color.RED));
        grid[3][0].setPiece(new ChessPiece(Color.RED));
        grid[3][1].setPiece(new ChessPiece(Color.RED));
        grid[3][2].setPiece(new ChessPiece(Color.RED));
        grid[4][0].setPiece(new ChessPiece(Color.RED));
        grid[4][1].setPiece(new ChessPiece(Color.RED));

        grid[dimension - 1][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 4].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 5].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 4].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 5].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 4].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 4][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 4][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 4][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 5][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 5][dimension - 2].setPiece(new ChessPiece(Color.GREEN));

    }
    public Square getGrid(int a,int b){
        return grid[a][b];
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



    public static boolean isWinnerRed() {//判断红方是否已经获胜

        if (ChessBoard.grid[dimension - 1][dimension - 1].getPiece()!=null &&
                ChessBoard.grid[dimension - 1][dimension - 2].getPiece()!=null &&
                ChessBoard.grid[dimension - 1][dimension - 3].getPiece()!=null &&
                ChessBoard.grid[dimension - 1][dimension - 4].getPiece()!=null &&
                ChessBoard.grid[dimension - 1][dimension - 5].getPiece()!=null &&
                ChessBoard.grid[dimension - 2][dimension - 2].getPiece()!=null &&
                ChessBoard.grid[dimension - 2][dimension - 1].getPiece()!=null &&
                ChessBoard.grid[dimension - 2][dimension - 3].getPiece()!=null &&
                ChessBoard.grid[dimension - 2][dimension - 4].getPiece()!=null &&
                ChessBoard.grid[dimension - 2][dimension - 5].getPiece()!=null &&
                ChessBoard.grid[dimension - 3][dimension - 1].getPiece()!=null &&
                ChessBoard.grid[dimension - 3][dimension - 2].getPiece()!=null &&
                ChessBoard.grid[dimension - 3][dimension - 3].getPiece()!=null &&
                ChessBoard.grid[dimension - 3][dimension - 4].getPiece()!=null &&
                ChessBoard.grid[dimension - 4][dimension - 1].getPiece()!=null &&
                ChessBoard.grid[dimension - 4][dimension - 2].getPiece()!=null &&
                ChessBoard.grid[dimension - 4][dimension - 3].getPiece()!=null &&
                ChessBoard.grid[dimension - 5][dimension - 1].getPiece()!=null &&
                ChessBoard.grid[dimension - 5][dimension - 2].getPiece()!=null){
            if(
                ChessBoard.grid[dimension - 1][dimension - 1].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 1][dimension - 2].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 1][dimension - 3].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 1][dimension - 4].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 1][dimension - 5].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 2][dimension - 1].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 2][dimension - 2].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 2][dimension - 3].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 2][dimension - 4].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 2][dimension - 5].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 3][dimension - 1].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 3][dimension - 2].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 3][dimension - 3].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 3][dimension - 4].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 4][dimension - 1].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 4][dimension - 2].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 4][dimension - 3].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 5][dimension - 1].getPiece().getColor()==Color.RED &&
                ChessBoard.grid[dimension - 5][dimension - 2].getPiece().getColor()==Color.RED) {
            return true;
        }
        }
        return false;
    }
    public static boolean isWinnerGreen() {//判断绿方是否已经获胜

       if(ChessBoard.grid[0][0].getPiece()!=null &&
               ChessBoard.grid[0][1].getPiece()!=null &&
               ChessBoard.grid[0][2].getPiece()!=null &&
               ChessBoard.grid[0][3].getPiece()!=null &&
               ChessBoard.grid[0][4].getPiece()!=null &&
               ChessBoard.grid[1][0].getPiece()!=null &&
               ChessBoard.grid[1][1].getPiece()!=null &&
               ChessBoard.grid[1][2].getPiece()!=null &&
               ChessBoard.grid[1][3].getPiece()!=null &&
               ChessBoard.grid[1][4].getPiece()!=null &&
               ChessBoard.grid[2][0].getPiece()!=null &&
               ChessBoard.grid[2][1].getPiece()!=null &&
               ChessBoard.grid[2][2].getPiece()!=null &&
               ChessBoard.grid[2][3].getPiece()!=null &&
               ChessBoard.grid[3][0].getPiece()!=null &&
               ChessBoard.grid[3][1].getPiece()!=null &&
               ChessBoard.grid[3][2].getPiece()!=null &&
               ChessBoard.grid[4][0].getPiece()!=null &&
               ChessBoard.grid[4][1].getPiece()!=null) {
           if (ChessBoard.grid[0][0].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[0][1].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[0][2].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[0][3].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[0][4].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[1][0].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[1][1].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[1][2].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[1][3].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[1][4].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[2][0].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[2][1].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[2][2].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[2][3].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[3][0].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[3][1].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[3][2].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[4][0].getPiece().getColor() == Color.GREEN &&
                   ChessBoard.grid[4][1].getPiece().getColor() == Color.GREEN) {
               return true;
           }
       }
        return false;
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

}
