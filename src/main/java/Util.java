import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Util
{
  public static void create(Path path, String fileName, String fileContent) throws IOException
  {
    Path filePath = path.resolve(fileName);
    try {
      Files.createFile(filePath);
      Files.write(filePath, ImmutableList.of(fileContent), StandardCharsets.UTF_8);
    }
    catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
  }

  public static String read(Path path)
  {
    try {
      return new String(Files.readAllBytes(path));
    }
    catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
  }


  public static void delete(Path path)
  {
    try {
      Files.deleteIfExists(path);
    }
    catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
  }

  public static String getInput()
  {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  public static List<Path> findByName(Path path, String fileName)
      throws IOException
  {

    List<Path> result;
    try (Stream<Path> pathStream = Files.find(path,
        Integer.MAX_VALUE,
        (p, basicFileAttributes) ->
            p.getFileName().toString().equalsIgnoreCase(fileName))
    ) {
      result = pathStream.collect(Collectors.toList());
    }
    return result;

  }
}
