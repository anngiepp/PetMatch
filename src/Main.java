import models.Reportante;
import ui.PetMatchFrame;

public class Main {

    public static void main(String[] args) {

        Reportante reportante =
                new Reportante(
                        1,
                        "Angie",
                        "angie@correo.com",
                        5
                );

        System.out.println(reportante);

        new PetMatchFrame();
    }
}