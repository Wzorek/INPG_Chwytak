package wzorek.httparduino;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;


public class HomeActivity extends Activity implements View.OnClickListener {

    public final static String PREF_IP = "PREF_IP_ADDRESS";
    public final static String PREF_PORT = "PREF_PORT_NUMBER";
    // declare buttons and text inputs
    private Button Send;
    private EditText editTextIPAddress, editTextPortNumber;
    private TextView AngleText,Joint1Text, Joint2Text, SwitchText, GrabText;
    private SeekBar AngleSeekbar, Joint1Seekbar, Joint2Seekbar;
    private Switch SwitchSide, GrabMe;
    private int angle_value;
    private int joint1_value;
    private int joint2_value;
    private int grab_value;
    private String[] NameTab = new String[4];
    private String[] ValueTab = new String[4];
    private String ipAddress = "";
    private String portNumber = "";
    private String Nazwa="";
    private String Value="";
    private View SecretVariable;
    // shared preferences objects used to save the IP address and port so that the user doesn't have to
    // type them next time he/she opens the app.
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("HTTP_HELPER_PREFS",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // assign buttons
        Send = (Button)findViewById(R.id.Send);
        Send.setOnClickListener(this);

        // assign SeekBars
        AngleSeekbar = (SeekBar)findViewById(R.id.AngleSeekbar);
        Joint1Seekbar = (SeekBar)findViewById(R.id.Joint1Seekbar);
        Joint2Seekbar = (SeekBar)findViewById(R.id.Joint2Seekbar);

        // assign Switch
        SwitchSide = (Switch) findViewById(R.id.SwitchSide);
        GrabMe = (Switch) findViewById(R.id.GrabMe);

        // assign text inputs
        editTextIPAddress = (EditText)findViewById(R.id.editTextIPAddress);
        editTextPortNumber = (EditText)findViewById(R.id.editTextPortNumber);

        // assign text outputs
        AngleText = (TextView)findViewById(R.id.AngleText);
        Joint1Text = (TextView)findViewById(R.id.Joint1Text);
        Joint2Text = (TextView)findViewById(R.id.Joint2Text);
        SwitchText = (TextView)findViewById(R.id.SwitchText);
        GrabText = (TextView)findViewById(R.id.GrabText);

        // get the IP address and port number from the last time the user used the app,
        // put an empty string "" is this is the first time.
        editTextIPAddress.setText(sharedPreferences.getString(PREF_IP,""));
        editTextPortNumber.setText(sharedPreferences.getString(PREF_PORT,""));

        //Get Side value
        SwitchSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SwitchText.setText("Prawo");
                    angle_value = -angle_value;
                    AngleText.setText("Kąt wynosi: " + angle_value + "°");
                }
                else {
                    SwitchText.setText("Lewo");
                    angle_value = -angle_value;
                    AngleText.setText("Kąt wynosi: " + angle_value + "°");
                }
            }
        });

        // Get GrabMe value
        GrabMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    GrabText.setText("Szczęki chwytaka są zamknięte");
                    grab_value = 1;
                }
                else {
                    GrabText.setText("Szczęki chwytaka są otwarte");
                    grab_value = 0;
                }
            }
        });

        // Get Angle value
        AngleText.setText("Kąt wynosi: " + AngleSeekbar.getProgress() + "°");
        AngleSeekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(SwitchText.getText()=="Prawo") {
                            angle_value = (int) ((progress * 0.9));
                            AngleText.setText("Kąt wynosi: " + angle_value + "°");
                        }else {
                            angle_value = (int) ((progress * -0.9));
                            AngleText.setText("Kąt wynosi: " + angle_value + "°");
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        AngleText.setText("Kąt wynosi: " + angle_value + "°");
                    }
                });

        // Get Joint1 value
        Joint1Text.setText("Kąt wynosi: " + Joint1Seekbar.getProgress() + "°");
        Joint1Seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        joint1_value = (int) ((progress * 0.9));
                        Joint1Text.setText("Kąt wynosi: " + joint1_value + "°");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Joint1Text.setText("Kąt wynosi: " + joint1_value + "°");
                    }
                });

        // Get Joint2 value
        Joint2Text.setText("Kąt wynosi: " + Joint2Seekbar.getProgress() + "°");
        Joint2Seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        joint2_value = (int) ((progress * 0.9));
                        Joint2Text.setText("Kąt wynosi: " + joint2_value + "°");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Joint2Text.setText("Kąt wynosi: " + joint2_value + "°");
                    }
                });
    }


    @Override
    public void onClick(View view) {

        // get the pin number
        String parameterValue = "";
        // get the ip address
        ipAddress = editTextIPAddress.getText().toString().trim();
        // get the port number
        portNumber = editTextPortNumber.getText().toString().trim();

        // save the IP address and port for the next time the app is used
        editor.putString(PREF_IP, ipAddress); // set the ip address value to save
        editor.putString(PREF_PORT, portNumber); // set the port number to save
        editor.commit(); // save the IP and PORT
        parameterValue = (angle_value+":"+joint1_value+":"+joint2_value+":"+grab_value);

        // execute HTTP request
        if(ipAddress.length()>0 && portNumber.length()>0) {
            new HttpRequestAsyncTask(
                    view.getContext(), parameterValue, ipAddress, portNumber, "a:j1:j2:g"
            ).execute();
        }
    }


    /**
     * Description: Send an HTTP Get request to a specified ip address and port.
     * Also send a parameter "parameterName" with the value of "parameterValue".
     * @param parameterValue the pin number to toggle
     * @param ipAddress the ip address to send the request to
     * @param portNumber the port number of the ip address
     * @param parameterName
     * @return The ip address' reply text, or an ERROR message is it fails to receive one
     */
    public String sendRequest(String parameterValue, String ipAddress, String portNumber, String parameterName) {
        String serverResponse = "ERROR";

        try {

            HttpClient httpclient = new DefaultHttpClient(); // create an HTTP client
            // define the URL e.g. http://myIpaddress:myport/?pin=13 (to toggle pin 13 for example)
            URI website = new URI("http://"+ipAddress+":"+portNumber+"/?"+parameterName+"="+parameterValue);
            HttpGet getRequest = new HttpGet(); // create an HTTP GET object
            getRequest.setURI(website); // set the URL of the GET request
            HttpResponse response = httpclient.execute(getRequest); // execute the request
            // get the ip address server's reply
            InputStream content = null;
            content = response.getEntity().getContent();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    content
            ));
            serverResponse = in.readLine();
            // Close the connection
            content.close();
        } catch (ClientProtocolException e) {
            // HTTP error
            serverResponse = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            // IO error
            serverResponse = e.getMessage();
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // URL syntax error
            serverResponse = e.getMessage();
            e.printStackTrace();
        }
        // return the server's reply/response text
        return serverResponse;
    }


    /**
     * An AsyncTask is needed to execute HTTP requests in the background so that they do not
     * block the user interface.
     */
    private class HttpRequestAsyncTask extends AsyncTask<Void, Void, Void> {

        // declare variables needed
        private String requestReply,ipAddress, portNumber;
        private Context context;
        private AlertDialog alertDialog;
        private String parameter;
        private String parameterValue;

        /**
         * Description: The asyncTask class constructor. Assigns the values used in its other methods.
         * @param context the application context, needed to create the dialog
         * @param parameterValue the pin number to toggle
         * @param ipAddress the ip address to send the request to
         * @param portNumber the port number of the ip address
         */
        public HttpRequestAsyncTask(Context context, String parameterValue, String ipAddress, String portNumber, String parameter)
        {
            this.context = context;

            alertDialog = new AlertDialog.Builder(this.context)
                    .setTitle("HTTP Response From IP Address:")
                    .setCancelable(true)
                    .create();

            this.ipAddress = ipAddress;
            this.parameterValue = parameterValue;
            this.portNumber = portNumber;
            this.parameter = parameter;
        }

        /**
         * Name: doInBackground
         * Description: Sends the request to the ip address
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids) {
            alertDialog.setMessage("Data sent, waiting for reply from server...");
            if(!alertDialog.isShowing())
            {
                alertDialog.show();
            }
            requestReply = sendRequest(parameterValue,ipAddress,portNumber, parameter);
            return null;
        }

        /**
         * Name: onPostExecute
         * Description: This function is executed after the HTTP request returns from the ip address.
         * The function sets the dialog's message with the reply text from the server and display the dialog
         * if it's not displayed already (in case it was closed by accident);
         * @param aVoid void parameter
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            alertDialog.setMessage(requestReply);
            if(!alertDialog.isShowing())
            {
                alertDialog.show(); // show dialog
            }
        }

        /**
         * Name: onPreExecute
         * Description: This function is executed before the HTTP request is sent to ip address.
         * The function will set the dialog's message and display the dialog.
         */
        @Override
        protected void onPreExecute() {
            alertDialog.setMessage("Sending data to server, please wait...");
            if(!alertDialog.isShowing())
            {
                alertDialog.show();
            }
        }

    }
}