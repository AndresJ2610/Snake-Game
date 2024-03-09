package practica2;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

public class Snake extends JPanel {

    //Color de la serpiente y de la comida
    Color colorsnake = Color.green;
    Color colorcomida = Color.red;
    //variables para determinar el tamaño del panel de la serpiente
    int tamax, tam, can, res;
    //Variable para la dificultad
    double dif;
    int vel = 1000;
    //Arreglo para la ubicación de la comida
    int[] comida = new int[2];
    //Variable para conocer la dirección en la que se moverá la serpiente
    String direccion = "";
    String direccionproxima = "";
    //variables para determinar la posición de la comida
    int foodx;
    int foody;
    //puntaje del jugador
    int puntos = 0;
    //Matriz para determinar la posición de la serpiente
    int snake[][] = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    //Variables para el hilo "Thread"
    Thread hilo;
    Caminar camino;

    //Constructor
    public Snake(int tamax, int can) {
        //Se define el tablero
        this.tamax = tamax;
        this.can = can;
        this.tam = tamax / can;
        this.res = tamax % can;
        //Se define el hilo
        camino = new Caminar(this);
        hilo = new Thread(camino);
        hilo.start();
        generarComida();
    }

    //Método del pintor que colocará a la serpiente y a la comida
    @Override
    public void paint(Graphics pintor) {
        //Se define el pintor
        super.paint(pintor);
        //se define el color del pintor
        pintor.setColor(colorsnake);
        //se pinta la serpiente
        for (int i = 0; i < snake.length; i++) {
            for (int j = 0; j < snake[0].length; j++) {
                if (snake[i][j] != 0) {
                    pintor.fillRect(res / 2 + j * tam, res / 2 + i * tam, tam, tam);
                }
            }
        }
        //se define un nuevo color al pintor
        pintor.setColor(colorcomida);
        //se pinta la comida
        pintor.fillRect(res / 2 + comida[0] * tam, res / 2 + comida[1] * tam, tam, tam);
    }

    //Método para el movimiento de la serpiente
    public void avanzar() {
        igualarDir();
        //se definen 2 variables para conocer la ubicación de la serpiente
        int placex = 0;
        int placey = 0;
        //se busca la ubicación actual de la serpiente
        for (int i = 0; i < snake.length; i++) {
            for (int j = 0; j < snake[0].length; j++) {
                if (snake[i][j] == 1) {
                    //se le dan los mismos valores a las variables de la ubicación
                    placex = i;
                    placey = j;
                }
            }
        }
        boolean ver = false;
        //Se define un switch para conocer la dirección en la que se moverá la serpiente
        switch (direccion) {
            case "Right":
                //Si es derecha verificar que no tope con el límite derecho
                if (placey + 1 != 10) {
                    for (int i = 0; i < snake[0].length - 1 && !ver; i++) {
                        //se hace el traslado de la serpiente a la derecha
                        if (snake[placex][placey] == 1) {
                            snake[placex][placey] = 0;
                            snake[placex][placey + 1] = 1;
                            ver = true;
                        }
                    }
                    //si topa con la pared regresar al jugador e indicar que a perdido
                } else {
                    direccion = "";
                    direccionproxima = "";
                    JOptionPane.showMessageDialog(null, "Has perdido");
                }
                break;
            case "Left":
                //Si es izquierda verificar que no tope con el límite izquierdo
                if (placey - 1 >= 0) {
                    for (int i = 0; i < snake[0].length - 1 && !ver; i++) {
                        //se hace el traslado de la serpiente a la izquierda
                        if (snake[placex][placey] == 1) {
                            snake[placex][placey] = 0;
                            snake[placex][placey - 1] = 1;
                            ver = true;
                        }
                    }
                    //si topa con la pared regresar al jugador e indicar que a perdido
                } else {
                    direccion = "";
                    direccionproxima = "";
                    JOptionPane.showMessageDialog(null, "Has perdido");
                }
                break;
            case "Up":
                //Si es arriba verificar que no tope con el límite superior
                if (placex - 1 >= 0) {
                    for (int i = 0; i < snake.length - 1 && !ver; i++) {
                        //se hace el traslado de la serpiente hacia arriba
                        if (snake[placex][placey] == 1) {
                            snake[placex][placey] = 0;
                            snake[placex - 1][placey] = 1;
                            ver = true;
                        }
                    }
                    //si topa con la pared regresar al jugador e indicar que a perdido
                } else {
                    direccion = "";
                    direccionproxima = "";
                    JOptionPane.showMessageDialog(null, "Has perdido");
                }
                break;
            case "Down":
                //Si es abajo verificar que no tope con el límite inferior
                if (placex + 1 != 10) {
                    for (int i = 0; i < snake.length - 1 && !ver; i++) {
                        //se hace el traslado de la serpiente hacia abajo
                        if (snake[placex][placey] == 1) {
                            snake[placex][placey] = 0;
                            snake[placex + 1][placey] = 1;
                            ver = true;
                        }
                    }
                    //si topa con la pared regresar al jugador e indicar que a perdido
                } else {
                    direccion = "";
                    direccionproxima = "";
                    JOptionPane.showMessageDialog(null, "Has perdido");
                }
                break;
        }

    }
    
    //Método para la acción de comer
    public void comer() {
        //Busca la posición de la serpiente
        for (int i = 0; i < snake.length; i++) {
            for (int j = 0; j < snake[0].length; j++) {
                if (snake[i][j] == 1 && i == comida[1] && j == comida[0]) {
                    //si su posición coincide con la comida, busca un nuevo lugar para esta
                    //y añade un punto además de aumentar la velocidad
                    this.foodx = (int) (Math.random() * can);
                    this.foody = (int) (Math.random() * can);
                    puntos = puntos + 1;
                    vel = (int)(vel * (1-dif));
                    break;
                }
            }
        }
        if (puntos >= 25) {
            terminar();
        }
        //coloca la nueva posición de la fruta
        this.comida[0] = foodx;
        this.comida[1] = foody;
    }

    //Método para nuevo juego
    public void terminar() {
        //Busca la posición de la serpiente para eliminarla de ahí
        for (int i = 0; i < snake.length; i++) {
            for (int j = 0; j < snake[0].length; j++) {
                snake[i][j] = 0;
            }
        }
        //regresa la serpiente al inicio
        snake[0][0] = 1;
        //devuelve todos los valores a como comezó
        puntos = 0;
        vel = 1000;
        direccionproxima = "";
        direccion = "";
        repaint();
    }

    //Método para generar la comida en una posición
    public void generarComida() {
        boolean existe = false;
        //Se verifica la posición de la serpiente
        for (int i = 0; i < snake.length; i++) {
            for (int j = 0; j < snake[0].length; j++) {
                if (snake[i][j] == 1 && foodx == i && foody == j) {
                    existe = true;
                    //Si se encuentra la posición con la serpiente entonces buscar una nueva posición
                    this.foodx = (int) (Math.random() * can);
                    this.foody = (int) (Math.random() * can);
                    generarComida();
                    break;
                }
            }
        }
        //Pasar la ubicación al arreglo para pintarlo en el panel
        if (!existe) {
            this.comida[0] = foodx;
            this.comida[1] = foody;
        }
    }
    
    //igualar la dirección para conocer si va al mismo sitio o no
    public void cambiarDireccion(String dir){
        if (dir != direccionproxima){
        this.direccionproxima = dir;
        }
    }

    public void igualarDir(){
        this.direccion = this.direccionproxima;
    }

}
