package mpjp.game;

import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Images extends java.lang.Object {

	private static java.util.Set<java.lang.String> extensions = new HashSet<>(Arrays.asList("jpg", "jpeg", "png"));
	private static java.io.File imageDirectory; //= new File("C:\\Users\\User\\git\\trabalho-2\\Padrões - AS\\src\\mpjp\\game\\images.txt");
	
	public static java.util.Set<java.lang.String> getExtensions() {
		return extensions;
	};
	
	public static void addExtensions(java.lang.String extension) {
		extensions.add(extension);
	};
	
	public static java.io.File getImageDirectory() {
		return imageDirectory;
	};
	
	public static void setImageDirectory(java.io.File imageDirectory) {
		Images.imageDirectory = imageDirectory;
	};
	
	public java.util.Set<java.lang.String> getAvailableImages() throws IOException {
		java.util.Set<java.lang.String> availableImages = new HashSet<java.lang.String>();
		for (String i:extensions) {
			FilenameFilter filter = (dir, name) -> name.endsWith(i);
			String[] pathnames = imageDirectory.list(filter);
			for(String s:pathnames) {
				availableImages.add(s);
			}
		}
		return availableImages;
	};
}
