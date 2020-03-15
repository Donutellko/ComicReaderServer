package DataAccessorServlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AppUtilities.JsonWorker;

import ComicsDataLogic.Comic;
import ComicsDataLogic.ComicDB;
import ComicsDataLogic.ComicDBContentGetter;
import DbControllers.DbConnectionManager;

/**
 * Сервлет для доступа к списку необходимых страниц из БД 
 */
@WebServlet(value = "/pageslist")
public class PagesListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public PagesListServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Getting params from request
    	String comicIdStr   = request.getParameter("comicid"); 
    	String timestampStr = request.getParameter("timestamp");
    	String lastPageStr  = request.getParameter("lastpage");
    	
    	// Response type
    	response.setCharacterEncoding("UTF8");
    	response.setContentType("application/json");
    	
    	// Response storage
		PrintWriter out = response.getWriter();
		
		int comicId, lastPage;
		long timestamp;
		
    	try {
    		comicId = Integer.parseInt(comicIdStr);
    		timestamp = Long.parseLong(timestampStr);
    		lastPage = Integer.parseInt(lastPageStr);
    	} catch (Exception e){
    		formHeaders(response, 1, "Invalid params");
    		writeRespJsonObject(out, 0, null);
    		out.close();
    		return;
    	}
    		
		// Using database
		//Connection dbConn = (Connection) getServletContext().getAttribute("DbConnection");
		
		try {
			// Retrieve data
			ResultSet rs = null;
			rs = ComicDB.getPagesAfter(comicId, lastPage, timestamp);
			List<Comic.Page> pages = ComicDBContentGetter.getPages(rs);
			
			// output
			formHeaders(response, 0, "Pages loaded");
			writeRespJsonObject(out, pages.size(), pages);
			out.close();
		}
		catch (SQLException e) {
			log("Failed executing SQL query: ");
			e.printStackTrace();
		}
	}
    
    class ResponseJsonObject{
    	
    	int selectedPagesCount;
    	//int totalPagesCount;
    	List<Comic.Page> pages;
    	
    	
    	ResponseJsonObject(int pagesNumber, List<Comic.Page> pages){
    		this.selectedPagesCount = pagesNumber;
    		this.pages = pages;
    	}
    }

    private void writeRespJsonObject(PrintWriter writer, int pagesNumber, List<Comic.Page> pages) {
		ResponseJsonObject resp = new ResponseJsonObject(pagesNumber, pages);
		String jsonedResponse = JsonWorker.toJson(resp);
		writer.println(jsonedResponse);
    }
    
    private void formHeaders(HttpServletResponse response, int responseCode, String responseMsg) {
    	response.setIntHeader("response_code", responseCode);
		response.setHeader("response_message", responseMsg);
		response.setHeader("Access-Control-Allow-Origin", "*");
	}
    
}
