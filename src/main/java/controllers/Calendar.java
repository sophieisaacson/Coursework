package controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("usersdates/")

public class Calendar {

    @GET
    @Path("list")
    public String UsersDatesList() {
        System.out.println("Invoked Users.UsersDatesLists()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT UsersDates.Date, UsersDates.Name\n" +
                    "FROM UsersDates\n" +
                    "JOIN Users ON Users.UserID = UsersDates.UserID\n" +
                    "WHERE Users.Token = ?");
            ResultSet results = ps.executeQuery();
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("Date", results.getInt(1));
                row.put("Name", results.getString(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items. Please see server console for more information.\"}";
        }
    }


}
