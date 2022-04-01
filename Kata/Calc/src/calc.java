import java.util.Scanner;

class exiting {
	public static void exiting_met() {
		System.out.println("Введены некорректные символы.");
		System.exit(0);
	}
}

class help_calc {
	public static int how_total(char znak, int cif_word_1, int cif_word_2) {
		int total = 0;
		if (znak == '+') total = cif_word_1 + cif_word_2;
		if (znak == '-') total = cif_word_1 - cif_word_2;
		if (znak == '*') total = cif_word_1 * cif_word_2;
		if (znak == '/') total = cif_word_1 / cif_word_2;
		return(total);
	}
}

public class calc {
	
	public static int checker_symbol(String word_all) {
		int rim = 0;
		for (int i = 0; i < word_all.length(); i++) {
			if (word_all.charAt(i) == 'I' || word_all.charAt(i) == 'V' || word_all.charAt(i) == 'X' || Character.isDigit(word_all.charAt(i)) == true) {
				if (word_all.charAt(0) == 'I' || word_all.charAt(0) == 'V' || word_all.charAt(0) == 'X')
					rim = 1;
				else
					rim = -1;
				if (rim == 1) {
					if (word_all.charAt(i) != 'I' && word_all.charAt(i) != 'V' && word_all.charAt(i) != 'X') {
						System.out.println("Неверный запрос. Использование арабских и римскимих цифр одновременно.");
						System.exit(0);
					}
				}
				if (rim == -1) {
					if (word_all.charAt(i) == 'I' || word_all.charAt(i) == 'V' || word_all.charAt(i) == 'X') {
						exiting.exiting_met();
					}
				}
			}
			else {
				exiting.exiting_met();
			}
		}
		return(rim);
	}
	
	public static void check_diapazone(int cif_word_1, int cif_word_2) {
		if (cif_word_1 > 10 || cif_word_1 < 1 || cif_word_2 > 10 || cif_word_2 < 1) {
			System.out.println("Одно из введенных число выходит за пределы диапазона (1-10).");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input_string = sc.nextLine();
	    sc.close();
	    input_string = input_string.replaceAll(" ", "");
		
	    int check_indexes = 0;
	    char znak = '0';
	    int when_znak = 0;
	    for (int i = 0; i < input_string.length(); i++) {
	    	if (input_string.charAt(i) == '+' || input_string.charAt(i) == '-' || input_string.charAt(i) == '*' || input_string.charAt(i) == '/') {
	    		check_indexes++;
	    		znak = input_string.charAt(i);
	    		when_znak = i;
	    	}
	    }
	    if (when_znak == 0 || when_znak == (input_string.length() - 1)) {
	    	System.out.println("Строка не является математической операцией.");
	    	System.exit(0);
	    }
	    
		if (check_indexes == 1) {
			String word_1 = input_string.substring(0, when_znak);
			String word_2 = input_string.substring(when_znak + 1, input_string.length());
			
			String word_all = word_1 + word_2;
			
			int rim = checker_symbol(word_all);
			
			int cif_word_1 = 0;
			int cif_word_2 = 0;
			String[] rim_array  = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			String[] rim_array_des  = new String[] {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
			if (rim == 1) {
				for (int i = 0; i < 10; i++) {
					if (rim_array[i].equals(word_1)) {
						cif_word_1 = i + 1;
					}
				}
				for (int i = 0; i < 10; i++) {
					if (rim_array[i].equals(word_2)) {
						cif_word_2 = i + 1;
					}
				}
				if (cif_word_1 == 0 || cif_word_2 == 0) {
					System.out.println("Одно из введенных число выходит за пределы диапазона (1-10).");
					System.exit(0);
				}
			}
			else {
				cif_word_1 = Integer.parseInt(word_1);
				cif_word_2 = Integer.parseInt(word_2);
			}
			
			check_diapazone(cif_word_1, cif_word_2);
			
			int total = help_calc.how_total(znak, cif_word_1, cif_word_2);
			
			if (rim == 1) {
				String total_str = "";
				if (total > 0) {
					if (total == 100)
						total_str = "C";
					else if (total > 10) {
						int total_des = total / 10;
						int total_des_ost = total % 10;
						if (total_des_ost == 0)
							total_str = rim_array_des[total_des - 1];
						else
							total_str = rim_array_des[total_des - 1] + rim_array[(total - (total_des * 10)) - 1];
					}
					else
						total_str = rim_array[total - 1];
					System.out.println(total_str);
				}
				else
					System.out.println("Ответ меньше I.");
			}
			else
				System.out.println(total);
			
		}
		else
			System.out.println("Строка не является математической операцией или не удовлетворяет запросу.");
	}
}