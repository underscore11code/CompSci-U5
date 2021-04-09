package io.github.underscore11code.compsci;

import java.util.ArrayList;
import java.util.List;

import static io.github.underscore11code.compsci.Main.prompt;

public class MadLib {
  private final String text;
  private final List<String> prompts = new ArrayList<>();
  private final List<String> responses = new ArrayList<>();

  public MadLib(final String rawIn) {
    StringBuilder placeholder = new StringBuilder();
    boolean inPlaceholder = false;
    for (int i = 0; i < rawIn.length(); i++) {
      final char at = rawIn.charAt(i);
      if (at == '{' || inPlaceholder) {
        placeholder.append(at);
        inPlaceholder = true;
      }

      if (at == '}') {
        prompts.add(placeholder.toString());
        placeholder = new StringBuilder();
        inPlaceholder = false;
      }
    }
    this.text = rawIn;
  }

  public void run() {
    for (final String prompt : prompts) {
      responses.add(
              prompt(
                      "Please enter a " + (prompt.replace("{", "").replace("}", "")),
                      p -> !p.equals(""))
      );
    }

    if (!(prompts.size() == responses.size())) throw new IllegalStateException();

    String tmp = this.text;
    for (int i = 0; i < prompts.size(); i++) {
      tmp = tmp.replaceFirst(prompts.get(i).replace("{", "\\{").replace("}", "\\}"), responses.get(i));
    }
    System.out.println("\n\nHere's your \"beautiful\" creation!");
    System.out.println(tmp);
  }
}
