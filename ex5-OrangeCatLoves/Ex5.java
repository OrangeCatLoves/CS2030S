import cs2030s.fp.Maybe;
import cs2030s.fp.Transformer;
import java.util.Map;

class Ex5 {

  //Use this skeleton to replace getGrade
   
  public static String getGrade(String student, String module, String assessment,
      Map<String, Map<String, Map<String, String>>> map) {


    Transformer<Map<String, Map<String, String>>, Maybe<Map<String, String>>> getModule = 
        new Transformer<>() {
          public Maybe<Map<String, String>> transform(Map<String, Map<String, String>> obj) {
            Maybe<Map<String, String>> maybe = (Maybe<Map<String, String>>) Maybe.of(obj.get(
                  module));
            return maybe;
          }
        };

    Transformer<Map<String, String>, Maybe<String>> getAssessment = new Transformer<>() {
      public Maybe<String> transform(Map<String, String> obj) {
        Maybe<String> maybe = (Maybe<String>) Maybe.of(obj.get(assessment));
        return maybe;
      }
    };
    Map<String, Map<String, String>> r = map.get(student);
    String result = (String) Maybe.of(r).flatMap(getModule).flatMap(
        getAssessment).orElse("No Such Entry");
    return result;
  }
  

  public static void main(String[] args) {
    Map<String, Map<String, Map<String, String>>> students =
        Map.of(
            "Steve", Map.of(
                "CS2030S", Map.of(
                        "ex1", "A",
                        "ex2", "A-",
                        "ex3", "A+",
                        "ex4", "B",
                        "pe1", "C"),
                "CS2040S", Map.of(
                        "ex1", "A",
                        "ex2", "A+",
                        "ex3", "A+",
                        "ex4", "A",
                        "midterm", "A+")),
            "Tony", Map.of(
                "CS2030S", Map.of(
                    "ex1", "C",
                    "ex2", "C",
                    "ex3", "B-",
                    "ex4", "B+",
                    "pe1", "A")));

    System.out.println(getGrade("Steve", "CS2030S", "ex1", students));
    System.out.println(getGrade("Steve", "CS2030S", "ex2", students));
    System.out.println(getGrade("Steve", "CS2040S", "ex3", students));
    System.out.println(getGrade("Steve", "CS2040S", "ex4", students));
    System.out.println(getGrade("Tony", "CS2030S", "ex1", students));
    System.out.println(getGrade("Tony", "CS2030S", "midterm", students));
    System.out.println(getGrade("Tony", "CS2040S", "ex4", students));
    System.out.println(getGrade("Bruce", "CS2040S", "ex4", students));
  }
}
