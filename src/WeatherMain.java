import controller.WeatherController;
import model.WeatherModel;
import views.WeatherView;

/**
 * Created by hp on 11/20/2014.
 */
public class WeatherMain {

    public static void main(String[] args) {

        WeatherModel model = new WeatherModel();
        WeatherController controller = new WeatherController();
        WeatherView view = new WeatherView();


        model.addModelListener(view);

        view.addModel(model);
        view.addController(controller);

        controller.addView(view);
        controller.addModel(model);

        view.setVisible(true);



    }

}
