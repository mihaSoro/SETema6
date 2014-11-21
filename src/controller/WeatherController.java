package controller;

import interfaces.IController;
import interfaces.IView;
import model.WeatherModel;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by hp on 11/20/2014.
 */
public class WeatherController implements IController {

    private WeatherModel mModel;

    private List<IView> mViews;

    private WeatherHttpClient httpClient= new WeatherHttpClient();


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



    public void addView(IView view){
        if (mViews == null) {
            mViews = new ArrayList<IView>();
        }

        mViews.add(view);
    }


    public void addModel(WeatherModel model) {
        mModel = model;
    }


    private void notifyViews(boolean error, String message) {
        if (mViews != null && !mViews.isEmpty()) {
            for (IView view : mViews) {
                view.onMessage(error, message);
            }
        }
    }

    private void refreshWeather(){

        if (mModel!= null)
        {
            //mModel.setWeather(30,25,12.4);
            try {

                String data = httpClient.getWeatherData();
                int index = data.indexOf("temp");
                char dest[] = new char[3];
                data.getChars(index+6,index+9,dest,0);
                int temperature = Integer.parseInt(new String(dest));
               // System.out.println(temperature);
                index = data.indexOf("pressure");
                char dest1[] = new char[4];
                data.getChars(index+10,index+14,dest1,0);
                int pressure = Integer.parseInt(new String(dest1));
                //System.out.println(dest1);
                index= data.indexOf("humidity");
                char dest2[]=new char[2];
                data.getChars(index+10,index+12,dest2,0);
                int humidity = Integer.parseInt(new String(dest2));
                System.out.println(dest2);

                mModel.setWeather(temperature,humidity,pressure);

            }
            catch (Exception ex)
            {
                notifyViews(true,ex.getMessage());
            }



        }

    }
}
