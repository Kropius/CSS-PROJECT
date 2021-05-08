package css.be.xml_test_helpers;

public class XmlConstants {

    private XmlConstants(){

    }

    public static final String XML_ONE_OPERAND_OPERATION = "<operation>\n" +
            "\t<operator>\n" +
            "\t\tsqrt\n" +
            "\t</operator>\n" +
            "\t<operand>\n" +
            "\t\t9\n" +
            "\t</operand>\n" +
            "</operation>\n";

    public static final String XML_TWO_OPERAND_OPERATION = "<operation>\n" +
            "\t<operand>\n" +
            "\t\t2\n" +
            "\t</operand>\n" +
            "\t<operator>\n" +
            "\t\t+\n" +
            "\t</operator>\n" +
            "\t<operand>\n" +
            "\t\t3\n" +
            "\t</operand>\n" +
            "</operation>\n";

    public static final String XML_PARENTHESES_OPERATION_EXAMPLE = "<operation>\n" +
            "\t<par>\n" +
            "\t\t<operation>\n" +
            "\t\t\t<operand>\n" +
            "\t\t\t\t2\n" +
            "\t\t\t</operand>\n" +
            "\t\t\t<operator>\n" +
            "\t\t\t\t+\n" +
            "\t\t\t</operator>\n" +
            "\t\t\t<operand>\n" +
            "\t\t\t\t3\n" +
            "\t\t\t</operand>\n" +
            "\t\t</operation>\n" +
            "\t</par>\n" +
            "\t<operator>\n" +
            "\t\t*\n" +
            "\t</operator>\n" +
            "\t<par>\n" +
            "\t\t<operation>\n" +
            "\t\t\t<operand>\n" +
            "\t\t\t\t5\n" +
            "\t\t\t</operand>\n" +
            "\t\t\t<operator>\n" +
            "\t\t\t\t+\n" +
            "\t\t\t</operator>\n" +
            "\t\t\t<operand>\n" +
            "\t\t\t\t5\n" +
            "\t\t\t</operand>\n" +
            "\t\t</operation>\n" +
            "\t</par>\n" +
            "</operation>\n";

    public static final String XML_WRONG_CHARACTERS_INPUT = "<operation>\n" +
            "\t<par>\n" +
            "\t\t<operation>\n" +
            "\t\t\t<operand>\n" +
            "\t\t\t\t2\n" +
            "\t\t\t</operand>\n" +
            "\t\t\t<operator>\n" +
            "\t\t\t\t+\n" +
            "\t\t\t</operator>\n" +
            "\t\t\t<operand>\n" +
            "\t\t\t\t3\n" +
            "\t\t\t</operand>\n" +
            "\t\t</operation>\n" +
            "\t</par>\n" +
            "\t<operator>\n" +
            "\t\t@*\n" +
            "\t</operator>\n" +
            "\t<par>\n" +
            "\t\t<operation>\n" +
            "\t\t\t<operand>\n" +
            "\t\t\t\tae5\n" +
            "\t\t\t</operand>\n" +
            "\t\t\t<operator>\n" +
            "\t\t\t\t+\n" +
            "\t\t\t</operator>\n" +
            "\t\t\t<operand>\n" +
            "\t\t\t\t5\n" +
            "\t\t\t</operand>\n" +
            "\t\t</operation>\n" +
            "\t</par>\n" +
            "</operation>\n";

    public static final String XML_RESPONSE_EXAMPLE = "<results>\n" +
            "<intermediaryOperations>\n" +
            "<operation>2 + 3 = 5</operation><operation>5 + 5 = 10</operation><operation>5 * 10 = 50</operation></intermediaryOperations>\n" +
            "<finalResult>50</finalResult></results>";
}
