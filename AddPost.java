/* Creates a new post page from a couple of questions.
 * This makes use of simplified main method in Java
 */

import static java.lang.IO.println;
import static java.lang.IO.readln;
import static java.util.function.Predicate.not;

void main() {
  String template = """
      ---
      layout: post
      title: "%s"
      date: %s
      categories: %s
      ---
      ## Here is a heading about
      This is a paragraph
      """;
  String defaultDate = LocalDate.now().format(FORMATTER);
  var title = Optional.ofNullable(readln(PROMPT_TITLE))
      .filter(not(String::isBlank))
      .orElse("A New Post");
  var dateStr = Optional.ofNullable(readln(
          String.format("Enter post date (dd/mm/yyyy) %s[%s]%s => ", ANSI_BOLD_COLOUR, defaultDate,
              ANSI_RESTORE)))
      .filter(not(String::isBlank))
      .orElse(defaultDate);
  var date = LocalDate.parse(dateStr, FORMATTER);

  var cats = Optional.ofNullable(readln(PROMPT_CATS))
      .filter(not(String::isBlank))
      .orElse("java programing");

  var kebabTitle = title.toLowerCase().replaceAll(" ", "-");

  println(String.format("""
      Save the output to a new file named %s%s-%s.md%s as a Here Document.  The command is below.
      """, ANSI_BOLD_COLOUR, date, kebabTitle, ANSI_RESTORE));
  String fileContent = String.format(template, title, date, cats);
  String exampleCmd = String.format("""
      cat <<EOF > %s-%s.md
      %s
      EOF
      """, date, kebabTitle, fileContent);
  println(exampleCmd);
}

private static final String ANSI_BOLD_COLOUR = "\033[1;32m";
private static final String ANSI_RESTORE = "\033[0m";
private static final String PROMPT_TITLE =
    "Enter post title " + ANSI_BOLD_COLOUR + "[A New Post]" + ANSI_RESTORE + " => ";
private static final String PROMPT_CATS =
    "Enter post categories " + ANSI_BOLD_COLOUR + "[java programing]" + ANSI_RESTORE + " => ";
private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");