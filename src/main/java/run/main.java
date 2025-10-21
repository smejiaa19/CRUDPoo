package run;

import entities.Autor;
import services.dao.MyDao;
import services.interfaces.ICRUD;

public class main {
    public static final ICRUD dao = new MyDao();

    public static void insertarAutor(){
        Autor a = new Autor();
        a.setNombre("Gabriel Garcia Marquez");
        a.setNacionalidad("Mexicano");
        dao.insert(a);

        Autor r = new Autor();
        r.setNombre("Ruben Dario");
        r.setNacionalidad("Nicaraguense");
        dao.insert(r);
    }
}
