import controller.WeatherController;
import model.WeatherModel;
import views.WeatherView;

/**
 * Created by hp on 11/20/2014.
 */
public class WeatherMain {

    public static void main(String[] args) {

        //instantiate the MVC elements: model, controller, view
        WeatherModel model = new WeatherModel();
        WeatherController controller = new WeatherController();
        WeatherView view = new WeatherView();

        //add the view to the model
        model.addModelListener(view);

        //add the model and controller to the view
        view.addModel(model);
        view.addController(controller);

        //add the view and the model to the controller
        controller.addView(view);
        controller.addModel(model);

        //make view visible
        view.setVisible(true);



    }

}
