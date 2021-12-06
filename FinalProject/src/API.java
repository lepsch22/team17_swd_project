
import com.mysql.cj.xdevapi.JsonParser;
import com.mysql.cj.xdevapi.JsonString;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class API {
    public static HashMap<String,String> getStats(String org, String dest ) throws IOException {
        org=org.replaceAll("\\s", "");
        dest=dest.replaceAll("\\s", "");
        String API="AIzaSyCdtZkbyXhmryzqMo1pReEet4BXNDIl14w";
       // https://maps.googleapis.com/maps/api/distancematrix/json?origins=Washington%2C%20DC&destinations=New%20York%20City%2C%20NY&units=imperial&key=AIzaSyCdtZkbyXhmryzqMo1pReEet4BXNDIl14w
        URL url=new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+org+"&destinations="+dest+"&units=imperial&key="+API);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        System.out.println(status);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();


        System.out.println(content);
        HashMap<String,String> map =new HashMap();
        String val="";
        int idx=0;
       // List ls= Arrays.asList("destination_addresses","origin_addresses");
        for (int i = 0; i < content.length(); i++) {
            if(content.charAt(i)=='[')
            {
                val="";
            }
            else if (content.charAt(i)==']')
            {
                map.put("destination",val);
                idx=i;
                break;
            }
            else
            {
                val+=content.charAt(i);
            }
        }
        for (int i = idx+1; i < content.length(); i++) {
            if(content.charAt(i)=='[')
            {
                val="";
            }
            else if (content.charAt(i)==']')
            {
                map.put("origin",val);
                idx=i;
                break;
            }
            else
            {
                val+=content.charAt(i);
            }
        }
        for (int i = idx+1; i < content.length(); i++) {
            if (content.charAt(i)=='x')
            {
                i+=6;

                val="";
                while (content.charAt(i)!='i')
                {
                    val+=content.charAt(i);
                    i++;
                }
                val+='i';
                idx=i;
                map.put("distance",val+'"');
                break;
            }
        }
        for (int i = idx+1; i < content.length(); i++) {
            if (content.charAt(i)=='x')
            {
                i+=6;

                val="";
                while (content.charAt(i)!=',')
                {
                    val+=content.charAt(i);
                    i++;
                }
                idx=i;
                map.put("time",val);
                break;
            }
        }
        System.out.println(map);
        return map;
        }



}
