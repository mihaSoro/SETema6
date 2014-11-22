package controller;

import interfaces.IController;
import interfaces.IView;
import jdk.nashorn.internal.parser.JSONParser;
import json.JSONObject;
import model.WeatherModel;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The Weather controller class
 */
public class WeatherController implements IController {

    /**
     * The members of this class are:
     *  mModel - a reference to the WeatherModel
     *  mViews - the list of views
     *  httpClient - the httpClient used to get Weather info
     *  jsonObject - the jsonObject used to read the data in json format
     */

    private WeatherModel mModel;

    private List<IView> mViews;

    private WeatherHttpClient httpClient= new WeatherHttpClient();
    private JSONObject jsonObject;


    /**
     * The action performed if the Refresh button has been pushed
     * @param e The action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ACTION_REFRESH)) {
            // Make the operation
            try {
                JButton source = (JButton) e.getSource();
                if (source != null && source.getAction() != null) {
                    refreshWeather();
                } else {
                    notifyViews(true, "Invalid operation data");

                }
            } catch (Exception ex) {
                notifyViews(true, ex.getMessage());
            }
        }
    }


    /**
     * Adds reference of a view to the list
     * @param view The View
     */

    public void addView(IView view){
        if (mViews == null) {
            mViews = new ArrayList<IView>();
        }

        mViews.add(view);
    }

    /**
     * Adds reference of model to the controller
     * @param model The weather model
     */

    public void addModel(WeatherModel model) {
        mModel = model;
    }


    /**
     * Notifies the views if an error has occurred or a message has to be shown to the user
     * @param error if true an error has occurred
     * @param message The message to be displayed
     */
    private void notifyViews(boolean error, String message) {
        if (mViews != null && !mViews.isEmpty()) {
            for (IView view : mViews) {
                view.onMessage(error, message);
            }
        }
    }

    /**
     * Refreshes the WeatherModel parameters with the new values
     */
    private void refreshWeather(){

        if (mModel!= null)
        {

            try {

                String data = httpClient.getWeatherData();
                jsonObject = new JSONObject(data);
                JSONObject main = jsonObject.getJSONObject("main");
                double temperature = main.getDouble("temp");
               // System.out.println(temperature);
                int humidity = main.getInt("humidity");
                double pressure = main.getDouble("pressure");
                mModel.setWeather(temperature,humidity,pressure);


            }
            catch (Exception ex)
            {
                notifyViews(true,ex.getMessage());
            }



        }

    }
}
