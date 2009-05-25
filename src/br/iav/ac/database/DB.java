package br.iav.ac.database;

import java.sql.*;

public class DB { 
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	String url;
	String usr;
	String pass;

	public DB() {
		this.url = "";
		this.usr = "";
		this.pass = "";

	}	

	public DB(String url, String usr, String pass) {
		this.url = url;
		this.usr = usr;
		this.pass = pass;		
	}

	public boolean connect() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, usr, pass);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	public void disconnect() {
		try {
			con.close();
			
			if ( stmt != null )
				stmt.close();
			
			if ( rs != null )
				rs.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}

	public void clear() {
		try {
			stmt.close();
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}			
		
	}
	
	public boolean select(String sql) {
		try {
			stmt = con.createStatement();		
			rs = stmt.executeQuery(sql);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean update(String sql) {
		
		System.out.println("Executando update: " + sql);
		
		try {
			stmt = con.createStatement();		
			int i = stmt.executeUpdate(sql);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean moveBack() {
		try {
			return rs.previous();
		} catch(Exception e) {
			return false;
		}			
	}

	public boolean moveNext() {
		try {
			return rs.next();
		} catch(Exception e) {
			return false;
		}			
	}

	public String getString(String col) {
		try {
			return rs.getString(col);
		} catch(Exception e) {
			return new String("");
		}	
	}

	public int getInt(String col) {
		try {
			return rs.getInt(col);
		} catch(Exception e) {
			return -999;
		}	
	}

	public float getFloat(String col) {
		try{
			return(rs.getFloat(col));
		}
		catch(Exception e){
			return -999;
		}	
	}

	public Date getDate(String col) {
		try {
			return rs.getDate(col);
		} catch(Exception e) {
			return null;
		}	
	}

}		