package wzorek.wifitest;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static SeekBar PowerSet;
    private static SeekBar AngleSet;
    private static Switch SwitchSide;
    private static TextView AngleText;
    private static TextView PowerText;
    private static TextView SwitchText;
    private static Button ConnectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbarAngle();
        seekbarPower();
        switchDo();
    }

    @Override
    public void ButtonOn() {
        .findViewById(R.id.button);
    }

    public void switchDo() {
        SwitchSide = (Switch) findViewById(R.id.SwitchSide);
        SwitchText = (TextView) findViewById(R.id.SwitchText);
        SwitchSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SwitchText.setText("Prawo");
                }
                else {
                    SwitchText.setText("Lewo");
                }
            }
        });
    }

    public void seekbarAngle() {
        AngleSet = (SeekBar) findViewById(R.id.AngleSeekbar);
        AngleText = (TextView) findViewById(R.id.AngleText);
        AngleText.setText("Kąt wynosi: " + AngleSet.getProgress() + "°");
        AngleSet.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int angle_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        angle_value = (int) ((progress * 0.9));
                        AngleText.setText("Kąt wynosi: " + angle_value + "°");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        AngleText.setText("Kąt wynosi: " + angle_value + "°");
                    }
                });
    }

    public void seekbarPower() {
        PowerSet = (SeekBar) findViewById(R.id.PowerSeekbar);
        PowerText = (TextView) findViewById(R.id.PowerText);
        PowerText.setText("Kąt wynosi: " + PowerSet.getProgress() + "%");
        PowerSet.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int angle_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        angle_value = progress;
                        PowerText.setText("Kąt wynosi: " + angle_value + "%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        PowerText.setText("Kąt wynosi: " + angle_value + "%");
                    }
                });
            }
        }