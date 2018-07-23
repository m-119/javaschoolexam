package com.tsystems.javaschool.tasks.calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as
     * decimal mark, parentheses, operations signs '+', '-', '*', '/'<br>
     * Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement
     * is invalid
     */
    public String evaluate(String statement) {
	// TODO: Implement the logic here
	try {
	    String result = null;

	    String sep = "([\\*\\+/-])";

	    //Prepare: -(-1 + -3 * -2) - uncorrect:
	    result = statement.trim().replaceAll(" +", "").replaceAll(sep, " $1 ").replaceAll(" +", " ");
	    
	    //Prepare with sign: -(-1 + -3 * -2) - correct:
	    //String sign = "([\\(\\*\\+-/] -) ([\\(\\d])";
	    //result = "("+statement.trim()+")".trim().replaceAll(" +", "").replaceAll(sep, " $1 ").replaceAll(" +", " ").replaceAll(sign, "$1$2").replaceAll(sign, "$1$2");
	    
	    String subExpressionPattern = "(\\()([^)\\(]*)(\\))";
	    Pattern subExpPatt = Pattern.compile(subExpressionPattern);
	    Matcher m = subExpPatt.matcher(result);

	    while (m.find()) {
		//with sign: -(-1) or --1 - correct:
		//result = result.replaceFirst(subExpressionPattern,simplecalc_long (m.group(2)).replaceAll("--", ""));
		result = result.replaceFirst(subExpressionPattern, simplecalc_long(m.group(2)));
		//System.out.println(result);
		m = subExpPatt.matcher(result);
	    }
	    result = simplecalc_long(result);
	    //System.out.println( simplecalc_long(s));
	    if ("Infinity".equals(result) || "-Infinity".equals(result) || result.contains(")") || result.contains("(")) {
		throw new Exception("null");
	    }

	    //Round
	    DecimalFormat df = new DecimalFormat("#.####");
	    df.setRoundingMode(RoundingMode.HALF_UP);
	    
	    return df.format(Float.valueOf(result)).toString().replace(",",".");
	} catch (Exception exc) {
	    return (null);
	}

    }

    private static String simplecalc_long(String expr) throws Exception {
	ArrayList<String> exprList = new ArrayList<>(Arrays.asList(expr.trim().split(" ")));
	for (int i = 1; i < exprList.size(); i += 2) {
	    if ("*".equals(exprList.get(i)) || "/".equals(exprList.get(i))) {
		exprList.set(i - 1, simplecalc_short(exprList.get(i - 1), exprList.get(i), exprList.get(i + 1)));
		exprList.remove(i);
		exprList.remove(i);
		i -= 2;
	    }
	}

	for (int i = 1; i < exprList.size(); i += 2) {
	    if ("+".equals(exprList.get(i)) || "-".equals(exprList.get(i))) {
		exprList.set(i - 1, simplecalc_short(exprList.get(i - 1), exprList.get(i), exprList.get(i + 1)));
		exprList.remove(i);
		exprList.remove(i);
		i -= 2;
	    }
	}

	return exprList.get(0);
    }

    private static String simplecalc_short(String a, String x, String b) throws Exception {
	Float result = null;
	switch (x.charAt(0)) {
	    case '*':
		result = Float.parseFloat(a) * Float.parseFloat(b);
		break;
	    case '/':
		result = Float.parseFloat(a) / Float.parseFloat(b);
		break;
	    case '+':
		result = Float.parseFloat(a) + Float.parseFloat(b);
		break;
	    case '-':
		result = Float.parseFloat(a) - Float.parseFloat(b);
		break;
	}
	//System.out.println( a + " " + x + " " + b + " = " + result.toString());
	if (Float.POSITIVE_INFINITY == Math.abs(result)) {
	    throw new Exception("null");
	}
	return result.toString();
    }

}
