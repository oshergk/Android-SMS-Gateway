# Android-SMS-Gateway
#### Use Android as GSM server and send sms with API, the homelabby alternative to Twillio and more!
- important! give SMS permission to the app(android settings) befor starting.
- compatible with android 8.0 or later.
- Need an actual SIM card to send SMS.
- runs on port 8086.

api request example:
```
{
    "number": "+1234567890",
    "message": "Hello, this is a test message.",
}

```
Post request to: http://yourANDROIDphoneIP:8086/sendsms

btw: if you want to use it from a server in different network you can use vpn :)