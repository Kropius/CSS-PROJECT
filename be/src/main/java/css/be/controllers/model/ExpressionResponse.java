package css.be.controllers.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class ExpressionResponse {

    private List<String> steps;
    private String result;

    public ExpressionResponse(List<String> steps, String result) {
        this.steps = steps;
        this.result = result;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getJsonResponse() {
        JSONObject jsonObject = new JSONObject();
        JSONArray intermediaryOperations = new JSONArray();
        for (String operation: steps)
            intermediaryOperations.add(operation);
        jsonObject.put("finalResult", result);
        jsonObject.put("intermediaryOperations", intermediaryOperations);
        return jsonObject.toJSONString();
    }

    public String getXmlResponse() {
        String xmlString = "<results>\n<intermediaryOperations>\n";
        for (String operation: steps) {
            xmlString += "<operation>";
            xmlString += operation;
            xmlString += "</operation>";
        }
        xmlString += "</intermediaryOperations>\n<finalResult>";
        xmlString += result;
        xmlString += "</finalResult></results>";
        return xmlString;
    }
}
