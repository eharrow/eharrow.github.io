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
  var title = Optional.ofNullable(IO.readln(PROMPT_TITLE))
      .filter(Predicate.not(String::isBlank))
      .orElse("A New Post");
  var dateStr = Optional.ofNullable(IO.readln(
          String.format("Enter post date (dd/mm/yyyy) %s[%s]%s => ", ANSI_BOLD_COLOUR, defaultDate, ANSI_RESTORE)))
      .filter(Predicate.not(String::isBlank))
      .orElseGet(() -> defaultDate);
  var date = LocalDate.parse(dateStr, FORMATTER);

  var cats = Optional.ofNullable(IO.readln(PROMPT_CATS))
      .filter(Predicate.not(String::isBlank))
      .orElse("java programing");

  var kebabTitle = title.toLowerCase().replaceAll(" ", "-");
  IO.println(String.format("\n\nSave the following to a new file named: " + ANSI_BOLD_COLOUR + "%s-%s.md" + ANSI_RESTORE + "\n", date, kebabTitle));
  String out = String.format(template, title, date, cats);
  IO.println(out);
}

private static final String ANSI_BOLD_COLOUR = "\033[1;32m";
private static final String ANSI_RESTORE = "\033[0m";
private static final String PROMPT_TITLE =
    "Enter post title " + ANSI_BOLD_COLOUR + "[A New Post]" + ANSI_RESTORE + " => ";
private static final String PROMPT_CATS =
    "Enter post categories " + ANSI_BOLD_COLOUR + "[java programing]" + ANSI_RESTORE + " => ";
private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");