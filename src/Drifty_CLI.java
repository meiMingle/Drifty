package src;

import java.util.Scanner;

public class Drifty_CLI {
    private static String downloadsFolder;
    protected static final String COLOR_BRIGHT_BLUE_BOLD = "\033[1;94m";
    protected static final String COLOR_BLUE_UNDERLINED = "\033[4;34m";
    protected static final String COLOR_RED = "\u001B[31m";
    protected static final String COLOR_CYAN_BOLD = "\033[1;36m";
    protected static final String COLOR_PURPLE = "\u001B[35m";
    protected static final String COLOR_GREEN = "\u001B[32m";
    protected static final String COLOR_BLUE = "\u001B[34m";
    protected static final String COLOR_RESET = "\u001B[0m";
    private static final Scanner SC = new Scanner(System.in);
    protected static final String COLOR_CYAN = "\u001B[36m";
    public static void main(String[] args) {
        System.out.println(COLOR_GREEN + "====================================================================" + COLOR_RESET);
        System.out.println(COLOR_BRIGHT_BLUE_BOLD + "\t\t\tDRIFTY" + COLOR_RESET);
        System.out.println(COLOR_GREEN + "====================================================================" + COLOR_RESET);

        System.out.print(COLOR_PURPLE + "Enter the link to the file : ");
        String link = SC.next();
        SC.nextLine();
        System.out.print("Enter the filename (with file extension) : " + COLOR_RESET);
        String fName = SC.nextLine();
        while (true) {
            System.out.print(COLOR_BLUE + "Do you want to download the file in your default downloads folder? (Enter Y for yes and N for no) : " + COLOR_RESET);
            char default_folder = SC.nextLine().toLowerCase().charAt(0);
            if (default_folder == 'y') {
                downloadsFolder = DefaultDownloadFolderLocationFinder.findPath() + System.getProperty("file.separator");
                if (downloadsFolder == null) {
                    System.out.println(COLOR_RED + "Failed to retrieve default download folder!" + COLOR_RESET);
                    enterDownloadsFolder();
                } else {
                    System.out.println(COLOR_PURPLE + "Default download folder detected : " + COLOR_CYAN_BOLD + downloadsFolder + COLOR_RESET);
                }
            } else if (default_folder == 'n') {
                enterDownloadsFolder();
            } else {
                System.out.println(COLOR_RED + "Invalid input!" + COLOR_RESET);
                continue;
            }
            break;
        }
        FileDownloader fDownload = new FileDownloader(link, fName, downloadsFolder);
        fDownload.run();
    }

    private static void enterDownloadsFolder(){
        System.out.print(COLOR_PURPLE + "Enter the directory in which you want to download the file : " + COLOR_RESET);
        downloadsFolder = SC.nextLine().replace('/', '\\');
        if (!(downloadsFolder.endsWith("\\"))) {
            downloadsFolder = downloadsFolder + System.getProperty("file.separator");
        }
    }
}
