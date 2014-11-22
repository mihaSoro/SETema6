package interfaces;

/**
 * Created by hp on 11/20/2014.
 */
public interface IView {

    /**
     * A message received form the controller
     * @param errorStatus if true the message is an error
     * @param message The message to be displayed
     */
    public void onMessage (boolean errorStatus, String message);
}
