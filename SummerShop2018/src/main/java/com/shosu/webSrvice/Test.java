package com.shosu.webSrvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test {
	public static void main(String[] arg) {
		System.out.println(firstDuplicate());

	}

	public static int firstDuplicate() {
		// Arrays.sort(a);
		int[] a = { 2,2 };
		int distance = a.length;
		int result = -1;
		boolean isFirst = false;
		for (int i = 0; i < a.length - 1; i++) {

			for (int j = i + 1; (j < a.length) &&(j< i+distance); j++) {
				if (a[i] == a[j]) {
					if (!isFirst) {
						distance = j - i;
						result = a[i];
						
					}
					if(distance>j-i) {
						distance = j - i;
						result = a[i];
					}
					break;
					
				}
			}
		}

		return result;
	}

}
