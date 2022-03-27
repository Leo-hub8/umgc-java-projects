
import java.util.Calendar;

public class EBook extends Media {

	// local attributes
	private int numChapters;

	// constructor
	public EBook(int id, String title, int year, int mediaRented, int chapters) {
			super(id, title, year, mediaRented);
			numChapters = chapters;
	}
	
    // constructor to parse string with xml tags for its values
    public EBook(String line) {
        super(line);
        numChapters = Integer.parseInt(line.substring(line.indexOf("<chapters>") + 10, line.indexOf("</chapters>")));                
		System.out.println("EBook numChapters = " + numChapters);
    }
	
	// get method
	public int getNumChapters() {
		return numChapters;
	}
	
	// set method
	public void setNumChapters(int numChapters) {
		this.numChapters = numChapters;
	}
	
	// override parent's
	@Override
	public double calculateRentalFee() {
		double fee = numChapters * 0.10; // basic fee
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		if (this.getYear() == currYear)
			fee += 1; // add $1.00 fee
		return fee;
	}
	
	@Override
    public String toString() {
        return "<EBook>"
                + "<title>" + getTitle() + "</title>"
                + "<id>" + getId() + "</id>"
                + "<year>" + getYear() + "</year>"
                + "<rented>" + getMediaRented() + "</rented>"
                + "<chapters>" + this.getNumChapters() + "</chapters>"
                + "</EBook>";
    }
    
}

