package genalgo;

import java.math.BigInteger;

public class AnotherConverter {

    static Double FromBinaryToDouble (String binary) {
        return Double.longBitsToDouble(new BigInteger(binary, 2).longValue());
    }

    static String FromDoubleToBinary (Double doubleValue) {
        if (doubleValue > 0) {
            return "00" + Long.toBinaryString(Double.doubleToRawLongBits(doubleValue));
        } else if (doubleValue < 0) {
            return Long.toBinaryString(Double.doubleToRawLongBits(doubleValue));
        } else return "0000000000000000000000000000000000000000000000000000000000000000";
    }

    /*public static void main(String[] args) {
        System.out.println(AnotherConverter.FromBinaryToDouble("1111111111110010011001100110011001100110011001100110011001100110"));
    }*/
}