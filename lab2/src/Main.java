public class Main {

    public static void main(String[] args) {
        int[] numberStroke = {1, 0, 0, 0, 0, 0, 0};

        String numberStringCast = "";

        for(int i=0; i<numberStroke.length; i++)
        {
            numberStringCast += numberStroke[i];
        }

        int number = Integer.parseInt(numberStringCast);
        String temp = Integer.toString(number-1);
        int[] result = new int[temp.length()];

        for (int i = 0; i < temp.length(); i++)
        {
            result[i] = temp.charAt(i) - '0';
        }

        String stringResult = "{ ";
        for(int i=0; i<result.length; i++)
        {
            if(i == 0)
            {
                stringResult += result[i];
            }
            else
            {
                stringResult += "," + result[i];
            }

        }
        stringResult += " }";

        System.out.println("Result : " + stringResult);
    }
}
