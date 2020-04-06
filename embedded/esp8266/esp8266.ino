#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include "ESP8266WebServer.h"

#ifndef STASSID
#define STASSID "your-ssid"
#define STAPSK  "your-password"
#endif

const char* ssid     = STASSID;
const char* password = STAPSK;

ESP8266WebServer server(80);

const int led = LED_BUILTIN;

const String indexPage = "<html><head><title>ESP8266 Web Server</title></head><body><h1>ESP8266 Web Server Work!/</h1></body></html>";

void handleRoot() {
  digitalWrite(led, 1);
  server.send(200, "text/html", indexPage);
  digitalWrite(led, 0);
}

void handleGetStatus() {
}

void handleUpdateStatus() {
  digitalWrite(led, 1);
  String message = "Parameters:\n";
  for (uint8_t i = 0; i < server.args(); i++) {
    message += " " + server.argName(i) + ": " + server.arg(i) + "\n";
  }
  server.send(200, "text/plain", message);
  digitalWrite(led, 0);
}

void handleNotFound() {
  digitalWrite(led, 1);
  String message = "File Not Found\n\n";
  message += "URI: ";
  message += server.uri();
  message += "\nMethod: ";
  message += (server.method() == HTTP_GET) ? "GET" : "POST";
  message += "\nArguments: ";
  message += server.args();
  message += "\n";
  for (uint8_t i = 0; i < server.args(); i++) {
    message += " " + server.argName(i) + ": " + server.arg(i) + "\n";
  }
  server.send(404, "text/plain", message);
  digitalWrite(led, 0);
}

void setup(void) {
  pinMode(led, OUTPUT);
  digitalWrite(led, 0);

  Serial.begin(115200);
  WiFi.begin(ssid, password);

  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  server.on("/", handleRoot);

  server.on("/api/list", handleGetStatus);

  server.on("/api/update", handleUpdateStatus);

  server.onNotFound(handleNotFound);

  server.begin();
  Serial.println("Server started");
}

void loop(void) {
  server.handleClient();
}
