package link.apbs.llamasmsgateway;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import static spark.Spark.*;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import link.apbs.llamasmsgateway.SMSBackgroundService;
import android.content.Context;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private boolean isServiceRunning = false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isServiceRunning = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceRunning = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.start_button);
        Button stopButton = findViewById(R.id.stop_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isServiceRunning) {
                    Intent intent = new Intent(MainActivity.this, SMSBackgroundService.class);
                    startService(intent);
                    bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceRunning) {
                    Intent intent = new Intent(MainActivity.this, SMSBackgroundService.class);
                    stopService(intent);
                    unbindService(serviceConnection);
                }
            }
        });
    }

    public void startService(View view) {
        Intent serviceIntent = new Intent(this, SMSBackgroundService.class);
        startService(serviceIntent);
        TextView serviceStatusTextView = findViewById(R.id.service_status_text_view);
        serviceStatusTextView.setText("Service is running");
    }

    public void stopService(View view) {
        Intent serviceIntent = new Intent(this, SMSBackgroundService.class);
        stopService(serviceIntent);
        TextView serviceStatusTextView = findViewById(R.id.service_status_text_view);
        serviceStatusTextView.setText("Service is not running");
    }

}



