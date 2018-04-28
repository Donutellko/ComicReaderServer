package DataAccessorServlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ComicsDataLogic.*;
import AppUtilities.JsonWorker;
import AppUtilities.JsonWorker.ResponseInfo;

/**
 * Servlet implementation class comicslist
 */
@WebServlet(value = "/comicslist")
public class ComicListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static final PreparedStatement comicPS = ComicDB.getPSComicsAfter();
    
    public ComicListServlet() {
        super();
    }
   

    /**
     * Process GET request with params:
     *  - timestamp
     *  
     *  Response consists of comics with timestamp greater-equal than given
     */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Getting params from request 
    	String timestampStr = request.getParameter("timestamp");
    	
    	// Response type
    	response.setCharacterEncoding("UTF8");
    	response.setContentType("application/json"); // 
    	
    	// Response storage
		PrintWriter out = response.getWriter();
		
		// ... Check invalid params ...
    	try {
    		Long.parseLong(timestampStr);
    	} catch (NumberFormatException e) {
    		writeRespJsonObject(out, 1, "Timestamp invalid format", null);
    		out.close();
		}
    	
		// Using database
		//Connection dbConn = (Connection)getServletContext().getAttribute("DbConnection");

		try {
			// Preparing SQL query
			comicPS.clearParameters();
			comicPS.setString(1, timestampStr);
			
			// Executing it
			ResultSet rs = null;
			rs = comicPS.executeQuery();
			
			// Forming response
			writeRespJsonObject(out, 0, "Comics loaded", ComicDBContentGetter.getComics(rs));
			out.close();
		}
		catch (SQLException e) {
			log("Failed executing SQL query: " + e.getMessage());
		} finally {
			
		}
	}

    class ResponseJsonObject{
    	
    	ResponseInfo responseCode;
    	List<Comic> comics;
    	
    	ResponseJsonObject(ResponseInfo responseCode, List<Comic> comics){
    		this.responseCode = responseCode;
    		this.comics = comics;
    	}
    }

    private void writeRespJsonObject(PrintWriter writer, int respCode, String respMsg, List<Comic> comics) {
    	ResponseInfo responseInfo = new ResponseInfo(respCode, respMsg);
    	/*for (Comic c : comics) {
    		c.initUrl=null; // initUrl Донату не надо
    	}*/
		ResponseJsonObject resp = new ResponseJsonObject(responseInfo, comics);
		String jsonedResponse = JsonWorker.toJson(resp);
		writer.println(jsonedResponse);
    }
}
