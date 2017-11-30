import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		ImageToFile img = new ImageToFile();
		img.getpixel(4);
		img.wirteToFile("ascii.txt");

	}

}
