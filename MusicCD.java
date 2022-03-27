
import java.util.Calendar;

	public class MusicCD extends Media {
		
		// local attributes
		private int length;
		
		// constructor
		public MusicCD(int id, String title, int year, int mediaRented, int length) {
			super(id, title, year, mediaRented);
			this.length = length;
		}
		
	    // constructor to parse string with xml tags for its values
	    public MusicCD(String line) {
	        super(line);
	        length = Integer.parseInt(line.substring(line.indexOf("<length>") + 8, line.indexOf("</length>")));                
			System.out.println("MusicCD length = " + length);
	    }

		// get method
		public int getLength() {
			return length;
		}
		
		// set method
		public void setLength(int length) {
			this.length = length;
		}
		
		// override parent's
		@Override
		public double calculateRentalFee() {
			double fee = length * 0.02; // basic fee
			int currYear = Calendar.getInstance().get(Calendar.YEAR);
			if (this.getYear() == currYear)
				fee += 1; // add $1.00 fee
			return fee;
		}
		
		@Override
	    public String toString() {
	        return "<MusicCD>"
	                + "<title>" + getTitle() + "</title>"
	                + "<id>" + getId() + "</id>"
	                + "<year>" + getYear() + "</year>"
	                + "<rented>" + getMediaRented() + "</rented>"
	                + "<length>" + this.getLength() + "</length>"
	                + "</MusicCD>";
	    }


	}

