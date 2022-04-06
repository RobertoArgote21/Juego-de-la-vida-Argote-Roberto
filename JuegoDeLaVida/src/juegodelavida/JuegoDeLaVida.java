/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodelavida;

import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author xxrca
 */
public class JuegoDeLaVida {

    /**
     * @param args the command line arguments
     */
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    
    public static void main(String[] args) {
        
        String Estados[] = new String[] {"~", "#"};
        int len = 0;
        int generaciones = 0;
        
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido");
        System.out.println("Digite el número de generacioens deseado: ");
        generaciones = sc.nextInt();
        System.out.println("Digite el tamaño del tablero nxn: ");
        len = sc.nextInt();
        String Tablero[][] = new String[len][len];
        llenarTablero(Tablero, Estados);
        System.out.println("GENERACIÓN 0");
        imprimirTablero(Tablero);
        
        for(int k=0; k<generaciones;k++){
            
            Tablero = jugar(Tablero);
            try {
                //Ponemos a "Dormir" el programa durante los ms que queremos
                Thread.sleep(1*1000);
             } catch (Exception e) {
                System.out.println(e);
             }
            for (int i=0; i < 30; i++){
             System.out.println();
            }
            System.out.println("GENERACIÓN "+(k+1));
            imprimirTablero(Tablero);
        }
        
    }
    
    public static void llenarTablero(String Tablero[][], String Estados[]){
        int tamano = Tablero.length;
        for(int i=0; i<tamano; i++){
            for(int j=0; j<tamano; j++){
                Tablero[i][j] = Estados[new Random().nextInt(1+1)];
            }
        }
    }
    
    public static String[][] jugar(String Tablero[][]){
        //Contar numero de vecinos
        int tamano = Tablero.length, i=0, j=0, vecinos=0;
        String TableroActualizado[][] = new String[tamano][tamano];
        for(i=0; i<tamano; i++){
            for(j=0; j<tamano; j++){
                try{
                    if(Tablero[i-1][j-1] == "#"){
                        vecinos ++;
                    }
                }catch(Exception e){}
                try{
                    if(Tablero[i-1][j] == "#"){
                        vecinos ++;
                    }
                }catch(Exception e){}
                try{
                    if(Tablero[i-1][j+1] == "#"){
                        vecinos ++;
                    }
                }catch(Exception e){}

                try{
                    if(Tablero[i][j-1] == "#"){
                        vecinos ++;
                    }
                }catch(Exception e){}
                try{
                    if(Tablero[i][j+1] == "#"){
                        vecinos ++;
                    }
                }catch(Exception e){}


                try{
                    if(Tablero[i+1][j-1] == "#"){
                        vecinos ++;
                    }
                }catch(Exception e){}
                try{
                    if(Tablero[i+1][j] == "#"){
                        vecinos ++;
                    }
                }catch(Exception e){}
                try{
                    if(Tablero[i+1][j+1] == "#"){
                        vecinos ++;
                    }
                }catch(Exception e){}
                
                //System.out.println("Vecinos de i:"+i+", j:"+j+" ="+vecinos);
                
                if (Tablero[i][j].equals("#")){
                    //Si una célula está viva y tiene dos o tres vecinas vivas, sobrevive.
                    if (vecinos == 2 || vecinos ==3) {
                        TableroActualizado[i][j] = "#";
                    }
                    //Si una célula está viva y tiene más de tres vecinas vivas o solo tiene una o menos, muere.
                    if (vecinos > 3 || vecinos <=1) {
                        TableroActualizado[i][j] = "~";
                    }
                }
                //Si una célula está muerta y tiene tres vecinas vivas, nace.
                else if(vecinos == 3){
                    TableroActualizado[i][j] = "#";
                }
                //Si una celula esta muerta y no tiene 3 vecinas vivas sigue muerta
                else{
                    TableroActualizado[i][j] = "~";
                }
                vecinos = 0;
            }
        }
        return TableroActualizado;
    }
    
    public static void imprimirTablero(String Tablero[][]){
        int tamano = Tablero.length;
        for(int i=0; i<tamano; i++){
            System.out.print("\t"+i);
        }
        System.out.println("");
        for(int i=0; i<tamano; i++){
            System.out.print(i+".\t");
            for(int j=0; j<tamano; j++){
                if(Tablero[i][j].equals("#")){
                    System.out.print(ANSI_GREEN+Tablero[i][j]+"\t"+ANSI_RESET);
                }else{
                    System.out.print(ANSI_RED+Tablero[i][j]+"\t"+ANSI_RESET);
                }
                
            }
            System.out.println("");
        }
    }
    
}
