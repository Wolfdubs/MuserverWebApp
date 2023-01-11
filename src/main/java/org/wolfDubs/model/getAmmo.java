package org.wolfDubs.model;

import io.muserver.rest.Description;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


@Path("/ammo")
@Description("ammo page")
public class getAmmo {

    @GET
    @Produces("text/html")
    public String getAmmo(){
        String fileContent = "default";
        StringBuilder html = new StringBuilder();
        try {
//            int bufferSize = 1024;
//            char[] buffer = new char[bufferSize];
//            StringBuilder out = new StringBuilder();
//            InputStream inputStream = getClass().getResourceAsStream("/ammo.html");
//            Reader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//            for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
//                out.append(buffer, 0, numRead);
//            }
//            return out.toString();

//            InputStream inputStream = getClass().getResourceAsStream("/view/ammo.html");
//            BufferedInputStream bis = new BufferedInputStream(inputStream);
//            ByteArrayOutputStream buf = new ByteArrayOutputStream();
//            for (int result = bis.read(); result != -1; result = bis.read()) {
//                buf.write((byte) result);
//            }
//            return buf.toString("UTF-8");

//            FileReader fileReader = new FileReader("/view/ammo.html");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String value;
//            while ((value = bufferedReader.readLine()) != null) {
//                html.append(value);
//            }
//            bufferedReader.close();
//            fileReader.close();
//            return html.toString();


//            BufferedReader bufferedReader = new BufferedReader(new FileReader("/view/ammo.html"));
//            fileContent = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));


//            InputStream inputStream =new FileInputStream(new File("view/ammo.html"));
//            DataInputStream dataInputStream = new DataInputStream(inputStream);
//            fileContent = dataInputStream.readUTF();

//            InputStream inputStream = getClass().getResourceAsStream("/view/ammo.html");
//            StringBuilder sb = new StringBuilder();
//            for (int ch; (ch = inputStream.read()) != -1; ) {
//                sb.append((char) ch);
//            }

               String localDir = System.getProperty("user.dir");
            System.out.println("Local directory = " + localDir);
               String file = localDir.concat("/src/main/java/org/wolfDubs/view/ammo.html");

            //InputStream inputStream = new FileInputStream(String.valueOf(getClass().getResource("/org.wolfDubs/model/ammo.html")));
             //   InputStream inputStream = getClass().getResourceAsStream("src/main/java/org/wolfDubs/model/ammo.html");
            InputStream inputStream = new FileInputStream(file);
               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
               fileContent = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
               return fileContent;
        } catch (Exception e) {
            System.out.println("An error occurred loading the file in " + this.getClass());
            e.printStackTrace();
        }
      //  return html.toString();
        return "html file wasn't loaded";

    }
}
