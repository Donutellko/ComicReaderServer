package AppUtilities;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import DbControllers.*;

/**
 * Application Lifecycle Listener implementation class AppContextListener
 */
@WebListener
public class AppContextListener implements ServletContextListener {
	
	// Будет обновлять БД с комиксами раз в  
	private ScheduledExecutorService scheduler;
	private static final long UPDATE_RATE_min = 30; // 60
	private static final boolean START_AUTO_UPDATES = false;
	
    public AppContextListener() {
        
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	Connection con = (Connection) sce.getServletContext().getAttribute("DBConnection");
    	try {
    		if (con != null) {
    			con.close();
    		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext ctx = sce.getServletContext();
    	
    	// Параметры подключения
    	String dbURL = ctx.getInitParameter("dbDriver") + ctx.getRealPath(ctx.getInitParameter("dbRelative"));
    	String user = ctx.getInitParameter("dbUser");
    	String pwd = ctx.getInitParameter("dbPassword");
    	
    	try {
    		// Создаем подключение к БД и прописываем в контекст сервлетов
    		DbConnectionManager.establishConnection(dbURL, user, pwd);
			Connection conn =  DbConnectionManager.getConnection();
			ctx.setAttribute("DbConnection", conn);
			System.out.println("Connection to " + dbURL + " initialized successfully.");
			
			// Инициализируем БД
			DbInitUtilities.initMainTables();
			
			if (START_AUTO_UPDATES) {
				// Запускаем обновление комиксов раз в час
			    scheduler = Executors.newSingleThreadScheduledExecutor();
			    scheduler.scheduleAtFixedRate(new DbPeriodicUpdater(), 0, UPDATE_RATE_min, TimeUnit.MINUTES);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

	
}
