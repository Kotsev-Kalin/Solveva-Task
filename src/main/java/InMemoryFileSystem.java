import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

import java.io.IOException;
import java.nio.file.*;

import java.util.List;

public class InMemoryFileSystem
{
  public static void main(final String[] args) throws IOException
  {


    FileSystem fs = Jimfs.newFileSystem(Configuration.forCurrentPlatform());
    Path path = fs.getPath("data");
    Files.createDirectory(path);

    System.out.println("Choose a command: (create, read, delete,search)");
    String command = Util.getInput();
    while (!command.equals("exit")) {


      if ("create".equals(command)) {
        System.out.println("Enter file name");
        String fileName = Util.getInput();
        System.out.println("Enter file content");
        String fileContent = Util.getInput();

        Util.create(path, fileName, fileContent);

      }
      else if ("delete".equals(command)) {
        System.out.println("Enter file name to delete:");
        String input = Util.getInput();
        Util.delete(fs.getPath(input));
      }

      else if ("search".equals(command)) {
        System.out.println("Enter file name:");
        String input = Util.getInput();
        List<Path> files = Util.findByName(path, input);

        files.forEach(System.out::println);

      }
      else if ("read".equals(command)) {
        System.out.println("Enter file name:");
        String input = Util.getInput();
        List<Path> filePath = Util.findByName(path, input);
        if (filePath.stream().findAny().isPresent()) {
          System.out.println(Util.read(filePath.stream().findAny().get()));
        }
        else {
          System.out.println("No such a file");
        }
      }
      else {
        System.out.println("Bad command!");
      }
      System.out.println("Choose a command: (create, read, delete,search)");
      command = Util.getInput();
    }
  }

}
