package lab07_12_18.shapes;

public abstract class AbstractShape implements Shape {
    /* da completare */
    // restituisce il centro della figura
    public abstract Point getCenter();

    // calcola il perimetro della figura
    public abstract double perimeter();

    // calcola l'area della figura
    public abstract double area();

    /*
     * confronta le aree di this e otherShape; restituisce un numero negativo se
     * this è una figura meno estesa; zero se le due figure hanno la stessa
     * area; un numero positivo se this è una figura più estesa
     */
    public abstract int  compareTo(Shape otherShape);

    /*
     * trasla la figura lungo il vettore (dx,dy)
     */
    public abstract void move(double dx, double dy);

    /*
     * scala la figura del fattore factory, mantenendo invariato il suo centro
     */
    public abstract void scale(double factory);
}
