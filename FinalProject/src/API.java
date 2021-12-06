
import com.mysql.cj.xdevapi.JsonParser;
import com.mysql.cj.xdevapi.JsonString;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Class allowing us to control google maps api
 */
public class API {
    public static HashMap<String,String> getStats(String org, String dest ) throws IOException {
        org=org.replaceAll("\\s", "");
        dest=dest.replaceAll("\\s", "");
        String API="AIzaSyCdtZkbyXhmryzqMo1pReEet4BXNDIl14w"; // api key
       // https://maps.googleapis.com/maps/api/distancematrix/json?origins=Washington%2C%20DC&destinations=New%20York%20City%2C%20NY&units=imperial&key=AIzaSyCdtZkbyXhmryzqMo1pReEet4BXNDIl14w
        URL url=new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+org+"&destinations="+dest+"&units=imperial&key="+API);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET"); // get request

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer(); // get Json as string
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        HashMap<String,String> map =new HashMap();
        String val="";
        int idx=0;

        // Manual String to mapp parsing
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
