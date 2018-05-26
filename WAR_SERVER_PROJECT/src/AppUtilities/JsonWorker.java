package AppUtilities;

import ComicsDataLogic.*;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWorker {
    
	private static Gson g; 
	
	/**
     * Переводит полученные с БД комиксы в JSON
     */
	/*
    public static String comicsListToJson(List<Comic> comics) {
        return g.toJson(comics);
    }
    */
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.disableHtmlEscaping();
		g = builder.create();
	}
	
    public static String toJson(Object o) {
    	// 
    	return g.toJson(o);
    }
 
}
