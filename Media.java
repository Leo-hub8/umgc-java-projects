
public abstract class Media {
	// attributes
	private int id;
	private String title;
	private int year; // validate that it has 4 digits
	private int mediaRented;  // 0 or 1
	
	// constructor
	public Media(int id, String title, int year, int mediaRented) {
	
		this.id = id;
		this.title = title;
		this.year = year;
		this.mediaRented = mediaRented;
	}
	
	public Media (String line) {
		System.out.println(line);

		
		id = Integer.parseInt(line.substring(line.indexOf("<id>") + 4, line.indexOf("</id>")));
		System.out.println("Media id = " + id);
		
		title = line.substring(line.indexOf("<title>") + 7, line.indexOf("</title>"));
		System.out.println("Media title = " + title);
		
		year = Integer.parseInt(line.substring(line.indexOf("<year>") + 6, line.indexOf("</year>")));
		System.out.println("Media year = " + year);
		
		mediaRented = Integer.parseInt(line.substring(line.indexOf("<rented>") + 8, line.indexOf("</rented>")));
		System.out.println("Media mediaRented = " + mediaRented);
	}
	
	
	// get methods
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMediaRented() { 
		return mediaRented;
	}
	
	// set methods
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	// calculate rental fee; for generic media it is flat fee $3.50
	public double calculateRentalFee() {
		return 3.50;
	}
	
	public void setMediaRented(int mediaRented) {
		this.mediaRented = mediaRented;
	}
}


	
