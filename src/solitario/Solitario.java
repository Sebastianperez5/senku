/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solitario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Sebastián Pérez
 */
public class Solitario {
//  0123456
// 0  ***
// 1  ***
// 2*******
// 3***.***
// 4*******
// 5  ***
// 6  ***
    

    private char[][] matrizTablero;
    private char espacio = ' ';
    private char estrella = '*';
    private char punto = '.';
    private int posY1;
    private int posX1;
    private int posX2;
    private int posY2;
    BufferedReader br; 
    FileReader fr;
    int filas = 7;
    int columnas = 7;
    String texto;

    public void crearTablero() {
        matrizTablero = new char[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i < 2) {
                    if (j < 2) {
                        matrizTablero[i][j] = espacio;
                    } else {
                        if (j > 4) {
                            matrizTablero[i][j] = espacio;
                        } else {
                            matrizTablero[i][j] = estrella;
                        }
                    }
                } else {
                    if (i > 4) {
                        if (j < 2) {
                            matrizTablero[i][j] = espacio;
                        } else {
                            if (j > 4) {
                                matrizTablero[i][j] = espacio;
                            } else {
                                matrizTablero[i][j] = estrella;
                            }
                        }
                    } else {
                        matrizTablero[i][j] = estrella;
                    }
                }
                if (i == 3 && j == 3) {
                    matrizTablero[i][j] = punto;
                }
            }
        }
    }
    
    public boolean setLevel(int level){
        boolean incorrecto  = false;
        try {
            //fr = new FileReader("level/"+level+".txt");
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/level/"+level+".txt")));
            filas = Integer.valueOf(br.readLine());
            columnas = Integer.valueOf(br.readLine());
            matrizTablero = new char [filas][columnas];
            texto = br.readLine();
            if(texto != null){
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        matrizTablero[j][i] = texto.charAt(j);
                    }
                    texto = br.readLine();
                }
            }else{
                incorrecto = true;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Solitario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Solitario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return incorrecto;
        
    }

    public void setCoordenadas(int posX1, int posY1, int posX2, int posY2) {
        this.posX1 = posX1;
        this.posY1 = posY1;
        this.posX2 = posX2;
        this.posY2 = posY2;
    }

    private boolean movimientoVertical() {
        boolean correcto = false;
        if (posX1 == posX2) {
            correcto = true;
        }
        return correcto;
    }

    private boolean movimientoHorizontal() {
        boolean correcto = false;
        if (posY1 == posY2) {
            correcto = true;
        }
        return correcto;
    }

    public boolean esCorrecto() {
        boolean correcto = false;
        if (movimientoVertical()) {
            if (posY1 == (posY2 - 2) || (posY1 - 2) == posY2) {
                if (matrizTablero[posX1][posY1] == estrella
                        && matrizTablero[posX2][posY2] == punto
                        && matrizTablero[posX2][posY2 - 1] == estrella) {
                    correcto = true;
                }
            } else {
                if ((posY1 - 2) == posY2) {
                    if (matrizTablero[posX1][posY1] == estrella
                            && matrizTablero[posX2][posY2] == punto
                            && matrizTablero[posX1][posY1 - 1] == estrella) {
                        correcto = true;
                    }
                }
            }
        } else {
            if (movimientoHorizontal()) {
                if (posX1 == (posX2 - 2)) {
                    if (matrizTablero[posX1][posY1] == estrella
                            && matrizTablero[posX2][posY2] == punto
                            && matrizTablero[posX2 - 1][posY2] == estrella) {
                        correcto = true;
                    }
                } else {
                    if (posX1 - 2 == posX2) {
                        if (matrizTablero[posX1][posY1] == estrella
                                && matrizTablero[posX2][posY2] == punto
                                && matrizTablero[posX1 - 1][posY1] == estrella) {
                            correcto = true;
                        }
                    }
                }
            }
        }
        return correcto;
    }

    public String realizarMovimiento() {
        if (esCorrecto()) {
            matrizTablero[posX1][posY1] = punto;
            matrizTablero[posX2][posY2] = estrella;

            if (movimientoVertical()) {
                matrizTablero[posX1][posY1 + 1] = punto;

            } else {
                if (movimientoHorizontal()) {
                    matrizTablero[posX1 + 1][posY1] = punto;

                }
            }
        }
        return toString();
    }

    @Override
    public String toString() {
        String cadena = "";
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                cadena += matrizTablero[j][i];
            }
            cadena += "\n";
        }
        return cadena;
    }
}
