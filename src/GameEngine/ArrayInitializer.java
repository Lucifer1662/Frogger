package GameEngine;

import java.util.function.Supplier;

public final class ArrayInitializer {
	private ArrayInitializer() {}
	
	public static <T extends Object> T[] Initializer(Supplier<T> supplier, T[] array){
		
		for(int i = 0; i < array.length; i++) {
			array[i] = supplier.get();
		}
		return array;
	}
}
