
public class MovieDVD extends Media {
	// local attributes
	private double size; // value in MB
	
	// constructor
	public MovieDVD(int id, String title, int year, int mediaRented, double size) {
		super(id, title, year, mediaRented);
		this.size = size;
	}
	
    // constructor to parse string with xml tags for its values
    public MovieDVD(String line) {
        super(line);
        size = Float.parseFloat(line.substring(line.indexOf("<size>") + 6, line.indexOf("</size>")));                
		System.out.println("MovieDVD size = " + size);
   }

	// get method
	public double getSize() {
		return size;
	}
	
	// set method
	public void setSize(double size) {
		this.size = size;
	}
	
	@Override
    public String toString() {
        return "<MovieDVD>"
                + "<title>" + getTitle() + "</title>"
                + "<id>" + getId() + "</id>"
                + "<year>" + getYear() + "</year>"
                + "<rented>" + getMediaRented() + "</rented>"
                + "<size>" + this.getSize() + "</size>"
                + "</MovieDVD>";
    }

	
}


