/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaschoolexam;

import com.tsystems.javaschool.tasks.calculator.Calculator;
import com.tsystems.javaschool.tasks.pyramid.PyramidBuilder;
import com.tsystems.javaschool.tasks.subsequence.Subsequence;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

/**
 *
 * @author C
 */
public class Javaschoolexam {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	// TODO code application logic here
	Calculator calc = new Calculator();
	Subsequence subsequence = new Subsequence();
	PyramidBuilder pyramidBuilder = new PyramidBuilder();
	
	System.out.println ("//Calculator");

        System.out.println ( (calc.evaluate("22/3*3.0480"))		);	//22.352
	System.out.println ( (calc.evaluate("10/((7-5)+3)*4"))		);	//8
	System.out.println ( (calc.evaluate("(1+2)*(2/1+(1-1)*100)"))	);	//6
	System.out.println ( (calc.evaluate("4.3/12*2+(4"))		);	//null
	
	System.out.println ("//PyramidBuilder");
	
	List<Integer> input = Arrays.asList(11, 1, 12, 3, 2, 13, 9, 4, 5, 14, 10, 8, 7, 15, 6);
        int[][] expected = new int[][]{
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 3, 0, 0, 0},
                {0, 0, 4, 0, 5, 0, 6, 0, 0},
                {0, 7, 0, 8, 0, 9, 0, 10, 0},
                {11, 0, 12, 0, 13, 0, 14, 0, 15}
        };
	
	System.out.println (Arrays.deepEquals(pyramidBuilder.buildPyramid(input),expected));	//true
	
	System.out.println ("//Subsequence");
	
	List a1 = Stream.of(1, 2, 3).collect(toList());
	List a2 = Stream.of("A", "C", "D").collect(toList());
	List a3 = Stream.of("A", "C", "B").collect(toList());
        List b1 = Stream.of( 0, "A", 1, 2, 4, 5, 7, 9, 3, "B", "C", "D").collect(toList());
	List b2 = new ArrayList();
	
	System.out.println (subsequence.find(a1, b1));    //true
	System.out.println (subsequence.find(a2, b1));    //true
	System.out.println (subsequence.find(a3, b1));    //false
	System.out.println (subsequence.find(a1, b2));    //false
	System.out.println (subsequence.find(b2, b2));    //true

    }
    
}
