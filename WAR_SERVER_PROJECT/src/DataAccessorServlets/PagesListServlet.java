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
import AppUtilities.JsonWorker.ResponseInfo;
import ComicsDataLogic.Comic;
import ComicsDataLogic.ComicDB;
import ComicsDataLogic.ComicDBContentGetter;
import DataAccessorServlets.ComicListServlet.ResponseJsonObject;
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
    		writeRespJsonObject(out, 1, "Invalid params", 0, null);
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
			
			writeRespJsonObject(out, 0, "Pages loaded", pages.size(), pages);
			out.close();
		}
		catch (SQLException e) {
			log("Failed executing SQL query: ");
			e.printStackTrace();
		}
	}
    
    class ResponseJsonObject{
    	
    	ResponseInfo responseCode;
    	int selectedPagesCount;
    	//int totalPagesCount;
    	List<Comic.Page> pages;
    	
    	
    	ResponseJsonObject(ResponseInfo responseCode, int pagesNumber, List<Comic.Page> pages){
    		this.responseCode = responseCode;
    		this.selectedPagesCount = pagesNumber;
    		this.pages = pages;
    	}
    }

    private void writeRespJsonObject(PrintWriter writer, int respCode, String respMsg, int pagesNumber, List<Comic.Page> pages) {
    	ResponseInfo responseInfo = new ResponseInfo(respCode, respMsg);
		ResponseJsonObject resp = new ResponseJsonObject(responseInfo, pagesNumber, pages);
		String jsonedResponse = JsonWorker.toJson(resp);
		writer.println(jsonedResponse);
    }
    
}
