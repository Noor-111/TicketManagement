package com.fresco.springtickets;

import com.fresco.springtickets.controllers.TicketController;
import com.fresco.springtickets.service.TicketService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class SpringticketsApplicationTests {

//    @InjectMocks
//    TicketController ticketController;
//
//    @Mock
//    TicketService ticketService;

    final String baseUrl = "http://localhost:8080/";


    @Test
    void testTicket0() {
        try {
            URL url = new URL(baseUrl + "new-admin?name=Admin1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");

            assert conn.getResponseCode() == 200;

            URL url2 = new URL(baseUrl + "new-admin?name=Admin2");
            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
            conn2.setRequestMethod("POST");
            conn2.setRequestProperty("Accept", "application/json");

            assert conn2.getResponseCode() == 200;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            assert false;
        } catch (IOException e) {
            assert false;
            e.printStackTrace();
        }
    }

    @Test
    void testTicket1() {
        try {
            URL url = new URL(baseUrl + "tickets");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            assert conn.getResponseCode() == 200;

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            JSONArray json = new JSONArray(sb.toString());
            assertEquals(json.length(), 0);

        } catch (MalformedURLException | JSONException e) {
            e.printStackTrace();
            assert false;
        } catch (IOException e) {
            assert false;
            e.printStackTrace();
        }
    }

    @Test
    void testTicket2() {
        try {
            URL url = new URL(baseUrl + "new-ticket?content=ConnectionIssue&sender=Sam");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");

            assert conn.getResponseCode() == 200;

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            JSONObject json = new JSONObject(sb.toString());
            assertEquals(json.get("status"), "registered");
            assertEquals(json.get("sender_name"), "Sam");
            testSchdeulerDataBeforeChange();

        } catch (MalformedURLException | JSONException e) {
            e.printStackTrace();
            assert false;
        } catch (IOException e) {
            assert false;
            e.printStackTrace();
        }
    }

    private void testSchdeulerDataBeforeChange() {
        try {
            URL url = new URL(baseUrl + "tickets");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            assert conn.getResponseCode() == 200;

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            JSONArray json = new JSONArray(sb.toString());
            assertEquals(json.getJSONObject(0).get("sender_name"), "Sam");
        } catch (IOException | JSONException e) {
            assert false;
        }
    }

    @Test
    void testTicket3() {
        try {
            Thread.sleep(4000);
            URL url = new URL(baseUrl + "tickets");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            assert conn.getResponseCode() == 200;

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            JSONArray json = new JSONArray(sb.toString());
            assertEquals(json.getJSONObject(0).get("status"), "processing");
            assertEquals(json.getJSONObject(0).get("sender_name"), "Sam");
            assert json.getJSONObject(0).getInt("admin_id") != 0;
        } catch (InterruptedException | JSONException | IOException e) {
            e.printStackTrace();
            assert false;
        }

    }


    @Test
    void testTicket4() {
        try {
            URL url = new URL(baseUrl + "new-ticket?content=SoftwareLagging&sender=Aroon");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");

            assert conn.getResponseCode() == 200;

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            JSONObject json = new JSONObject(sb.toString());
            assertEquals(json.get("status"), "registered");
            assertEquals(json.get("sender_name"), "Aroon");
            testSchdeulerDataBeforeChange();

        } catch (MalformedURLException | JSONException e) {
            e.printStackTrace();
            assert false;
        } catch (IOException e) {
            assert false;
            e.printStackTrace();
        }
    }


    @Test
    void testTicket5() {
        try {
            Thread.sleep(5000);
            URL url = new URL(baseUrl + "tickets");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            assert conn.getResponseCode() == 200;

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            JSONArray json = new JSONArray(sb.toString());
            assertNotEquals(json.getJSONObject(0).getInt("admin_id"), json.getJSONObject(1).getInt("admin_id"));
        } catch (IOException | JSONException | InterruptedException e) {
            assert false;
        }
    }

}
