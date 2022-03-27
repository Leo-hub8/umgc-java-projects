import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	
	private ArrayList<Media> mediaList = null;
	
	// default Constructor
	public Manager() {
		
		mediaList = new ArrayList<Media>();
		
	}

    // load all media files from directory; assumes file name convention starts with media type followed by id
    // If directory is not found, it will throw exception
    public Manager(String directory) throws FileNotFoundException {
 
    	loadMediaList(directory);
    	
    }
    
    // creates (or overwrites) a file for each Media object in mediaList's attribute in the given directory
    public void createMediaFiles(String directory) throws IOException {
       
        PrintWriter out = null;
        File directoryPath = new File(directory);
        
        // for all Media objects create files using Media type and id value as filename
        for (int i=0; i < mediaList.size(); i++) {
            String filename = directoryPath + "/" + mediaList.get(i).getClass().getSimpleName() + "-" + mediaList.get(i).getTitle() + ".txt";
            out = new PrintWriter(new FileWriter(filename));  // create/overwrite file
            out.println(mediaList.get(i).toString());  // write the media's data
            out.flush();      // flush all the data to the file
            out.close();    // close the stream
        }
    }
    
    // display all stored media on console
    public void displayAllMedia() {
       
        // for all Media objects display the xml tag data
        for (int i=0; i < mediaList.size(); i++) {
            System.out.println(mediaList.get(i).toString());
        }
    }
    
    // add Media object
    public void addMedia(Media media) {
        mediaList.add(media);
    }
    
    public boolean checkOutMedia(int id) {
    	for(Media media : mediaList) {
    		if (media.getId() == id) {
    			if (media.getMediaRented() == 0) {
    				media.setMediaRented(1);
    				System.out.println("Media " + media.getTitle() + " Successfully rented");
    				return true;
    			} else {
    				System.out.println("Sorry - Media " + media.getTitle() + " is already rented.");
    			}
    		}
    	}
    	return false;
    }
    
    public boolean findMedia(String title) {
		System.out.println("findMedia looking for [" + title + "]");
		boolean found = false;
		
    	for(Media media : mediaList) {
    		if (media.getTitle().contains(title)) {
    			System.out.println("Found " + media.toString());
    			found = true;
    		} 
    	}
    	return found;
    }

    public void loadMediaList (String directory) throws FileNotFoundException {
    	if (mediaList != null) {
    		mediaList.clear();
    	}
        
		mediaList = new ArrayList<Media>();

		File directoryPath = new File(directory);
		
		// Get list of all files and directories
        File fileslist[] = directoryPath.listFiles();
		
		if(fileslist == null)
			throw new FileNotFoundException("Could not load, no such directory");
		
		Media media = null;
		String line = null;
		Scanner scan = null;
		
		for(File file : fileslist) {
			if (file.getName().contains("EBook") || file.getName().contains("MovieDVD") || file.getName().contains("MusicCD")) {
				
				scan = new Scanner(file);
				line = scan.nextLine();  // assumes the file is not empty
				
				if(file.getName().contains("EBook")) {
					media = new EBook(line);
				} 
				
				if(file.getName().contains("MovieDVD")) {
					media = new MovieDVD(line);
				} 
				
				if(file.getName().contains("MusicCD")) {
					media = new MusicCD(line);
				} 
                
				// add Media object to media attribute
				mediaList.add(media);
			}
		}
    }
}


