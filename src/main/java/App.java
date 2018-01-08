import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        staticFileLocation("/public");


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/change_machine", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ChangeMachine changeMachine = new ChangeMachine();
            String userInput = request.queryParams("input");
            Float userInputFloat = Float.parseFloat(userInput);
            String change = changeMachine.makeChange(userInputFloat);
            model.put("converted", change);
            model.put("input", userInput);
            return new ModelAndView(model, "change_machine.hbs");
        }, new HandlebarsTemplateEngine());


    }
}