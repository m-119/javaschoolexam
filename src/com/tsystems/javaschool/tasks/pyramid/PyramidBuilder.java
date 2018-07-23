package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line
     * and maximum at the bottom, from left to right). All vacant positions in
     * the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be
     * build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
	// TODO code application logic here

	try {
	    List<Integer> input = inputNumbers;
	    int heigh = 0;
	    int width = 0;

	    int heighIndex = 0;
	    int widthIndex = 0;
	    int widthIndexStart = 0;

	    int numInput = 0;	//Какую цифру вставлять

	    boolean pyramid = false;    //пишем?

	    //input.sort((o1, o2) -> Integer.compare(o1, o2));
	    Collections.sort(input);

	    //проверяем можно ли строить, расчитываем начальные позиции и пропорции
	    while (heighIndex != input.size()) {
		++heigh;
		heighIndex += heigh;
		if (heighIndex == input.size()) {
		    width = heigh * 2 - 1;
		    widthIndexStart = width / 2;
		    pyramid = true;
		    break;
		} else if (heigh > input.size()) {
		    throw new CannotBuildPyramidException();
		}
	    }

	    if (heigh > input.size() || !pyramid) {
		throw new CannotBuildPyramidException();
	    }

	    //матрица результата
	    int[][] result = new int[heigh][width];

	    for (heighIndex = 0; heighIndex < heigh; ++heighIndex) {
		pyramid = true;		    //в первой ячейке обязательно число
		widthIndex = widthIndexStart;   //заполнение с ячейки widthIndexStart
		while (widthIndex < width - widthIndexStart) //достижение правой границы
		{
		    if (pyramid == true) {
			result[heighIndex][widthIndex++] = input.get(numInput++);
		    } //Number
		    else {
			result[heighIndex][widthIndex++] = 0;
		    }					//0
		    pyramid = !pyramid;			//Next !pyramid
		}
		--widthIndexStart;		//

	    }
	    return result;
	} catch (Exception exc) {
	    throw new CannotBuildPyramidException();
	} catch (OutOfMemoryError oome) {
	    throw new CannotBuildPyramidException();
	}
    }
}
