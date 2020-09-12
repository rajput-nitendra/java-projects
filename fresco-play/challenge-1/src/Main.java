import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, JsonMappingException {

        int result = avgRotorSpeed("RUNNING", 1);
        System.out.println(result);
    }

    public static int avgRotorSpeed(String statusQuery, int parentId) throws IOException {
        int avgRotorSpeed = 0;
        int totalRotorSpeed = 0;
        try {
            String baseUrl = "https://jsonmock.hackerrank.com/api/iot_devices/search?"
                    + "status" + statusQuery
                    + "&page" + parentId;

            URL url = new URL(baseUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                final String responseString = response.toString();
                System.out.println(responseString);

                ObjectMapper mapper = new ObjectMapper();

                final IOTDevice iotDevice = mapper.readValue(responseString, IOTDevice.class);

                for (Data data : iotDevice.getData()) {
                    if (data.getParent() != null && data.getParent().getId() == parentId
                            && data.getStatus() != null && data.getStatus().equalsIgnoreCase(statusQuery)) {
                        totalRotorSpeed = totalRotorSpeed + data.getOperatingParams().getRotorSpeed();
                    }
                }

                System.out.println(totalRotorSpeed);
                avgRotorSpeed = totalRotorSpeed/iotDevice.getData().size();
            }
        } catch (MalformedURLException | JsonMappingException e) {
            e.printStackTrace();
        }
        return avgRotorSpeed;
    }
}

class OperatingParams {
    private int rotorSpeed;

    private int slack;

    private double rootThreshold;

    public void setRotorSpeed(int rotorSpeed) {
        this.rotorSpeed = rotorSpeed;
    }

    public int getRotorSpeed() {
        return this.rotorSpeed;
    }

    public void setSlack(int slack) {
        this.slack = slack;
    }

    public int getSlack() {
        return this.slack;
    }

    public void setRootThreshold(double rootThreshold) {
        this.rootThreshold = rootThreshold;
    }

    public double getRootThreshold() {
        return this.rootThreshold;
    }
}

class Asset {
    private int id;

    private String alias;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }
}

class Parent {
    private int id;

    private String alias;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }
}

class Data {
    private int id;

    private long timestamp;

    private String status;

    private OperatingParams operatingParams;

    private Asset asset;

    private Parent parent;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setOperatingParams(OperatingParams operatingParams) {
        this.operatingParams = operatingParams;
    }

    public OperatingParams getOperatingParams() {
        return this.operatingParams;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Asset getAsset() {
        return this.asset;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Parent getParent() {
        return this.parent;
    }
}

class IOTDevice {
    private String page;

    private int per_page;

    private int total;

    private int total_pages;

    private List<Data> data;

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return this.page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPer_page() {
        return this.per_page;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_pages() {
        return this.total_pages;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return this.data;
    }
}
