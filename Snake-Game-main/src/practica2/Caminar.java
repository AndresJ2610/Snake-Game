package practica2;

public class Caminar implements Runnable {

    //Variable snake
    Snake snake;
    boolean estado = true;

    //Constructor donde pasemos la serpiente
    public Caminar(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void run() {
        while (estado) {
            //repetir avanzar y repintar mientras estado sea verdadero
            //Si no hay una dirección seteada entonces no moverse
            if (snake.direccion != ""){
            snake.avanzar();
            snake.comer();
            snake.repaint();
            }

            try {
                //esperar intervalo dado por una ecuación que toma la dificultad
                Thread.sleep(snake.vel);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Caminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    //Método para parar el hilo
    public void parar() {
        this.estado = false;
    }

}
