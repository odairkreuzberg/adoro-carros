package br.iav.ac.database;

public class PostgreSQL {

	public static String TIPO_DB = "postgresql";
	public static String HOST = "localhost";
	public static String PORT = "5432";
	public static String DB = "adorocarros";
	public static String USER = "postgres";
	public static String PASS = "postgres";

	public static DB create() {
		return new DB("jdbc:" + TIPO_DB + "://" + HOST + ":" + PORT + "/" + DB, USER, PASS);
	}

}
