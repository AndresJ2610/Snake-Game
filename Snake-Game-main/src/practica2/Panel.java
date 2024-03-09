package practica2;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class Panel extends JPanel{
    
    //Se define el color del pintor
    Color colorfondo = Color.gray;
    int tamax, tam, can, res;
    
    //Se definen los valores del tablero en el constructor
    public Panel(int tamax, int can){
        this.tamax = tamax;
        this.can = can;
        this.tam = tamax/can;
        this.res = tamax%can;
    }
    
    //Método del pintor
    public void paint(Graphics pintor){
        //Se llama al pintor
        super.paint(pintor);
        //Se define el color del pintor
        pintor.setColor(colorfondo);
        //Se pinta el escenario
        for (int i = 0; i < can; i++) {
            for (int j = 0; j < can; j++) {
                pintor.fillRect(i*tam, j*tam, tam-1, tam-1);
            }
        }
    }
}
