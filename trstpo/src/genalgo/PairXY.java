package genalgo;

import java.math.BigDecimal;

public class PairXY {

    private String binaryX;
    private String binaryY;

    private double doubleX;
    private double doubleY;


    PairXY(String binaryX, String binaryY) {

        this.doubleX = BigDecimal.valueOf(AnotherConverter.FromBinaryToDouble(binaryX)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        this.doubleY = BigDecimal.valueOf(AnotherConverter.FromBinaryToDouble(binaryY)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();


        this.binaryX = AnotherConverter.FromDoubleToBinary(this.doubleX);
        this.binaryY = AnotherConverter.FromDoubleToBinary(this.doubleY);
    }

    PairXY(double doubleX, double doubleY) {
        this.doubleX = doubleX;
        this.doubleY = doubleY;


        this.binaryX = AnotherConverter.FromDoubleToBinary(this.doubleX);
        this.binaryY = AnotherConverter.FromDoubleToBinary(this.doubleY);
    }

    void setBinaryX(String binaryX) {
        this.binaryX = AnotherConverter.FromDoubleToBinary(this.doubleX);
        this.doubleX = BigDecimal.valueOf(AnotherConverter.FromBinaryToDouble(this.binaryX)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    void setBinaryY(String binaryY) {
        this.binaryY = AnotherConverter.FromDoubleToBinary(this.doubleY);
        this.doubleY = BigDecimal.valueOf(AnotherConverter.FromBinaryToDouble(this.binaryY)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    String getBinaryX() {
        return binaryX;
    }

    String getBinaryY() {
        return binaryY;
    }

    double getDoubleX() {
        return doubleX;
    }

    double getDoubleY() {
        return doubleY;
    }

    public void setDoubleX(double doubleX) {
        this.doubleX = doubleX;
        this.binaryX = AnotherConverter.FromDoubleToBinary(this.doubleX);

    }

    public void setDoubleY(double doubleY) {

        this.doubleY = doubleY;
        this.binaryY = AnotherConverter.FromDoubleToBinary(this.doubleY);

    }
}
