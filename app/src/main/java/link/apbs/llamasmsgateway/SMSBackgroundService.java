package link.apbs.llamasmsgateway;

import static spark.Spark.*;
import android.app.Service;
import android.os.IBinder;
import android.content.Intent;
import android.telephony.SmsManager;


public class SMSBackgroundService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        // Specify the port for the server to listen on
        port(8086);
        post("/sendsms", (request, response) -> {
            String number = request.queryParams("number");
            String message = request.queryParams("message");
            sendSMS(number, message);
            return "SMS sent";
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the server when the service is destroyed
        stop();
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

}