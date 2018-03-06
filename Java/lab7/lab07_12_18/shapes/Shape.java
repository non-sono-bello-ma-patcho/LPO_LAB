package lab07_12_18.shapes;

public interface Shape {

	// restituisce il centro della figura
	Point getCenter();

	// calcola il perimetro della figura
	double perimeter();

	// calcola l'area della figura
	double area();

	/*
	 * confronta le aree di this e otherShape; restituisce un numero negativo se
	 * this è una figura meno estesa; zero se le due figure hanno la stessa
	 * area; un numero positivo se this è una figura più estesa
	 */
	int compareTo(Shape otherShape);

	/*
	 * trasla la figura lungo il vettore (dx,dy)
	 */
	void move(double dx, double dy);

	/*
	 * scala la figura del fattore factory, mantenendo invariato il suo centro
	 */
	void scale(double factory);

}
