package Library.Arithmetic;

public class Complex {
    public double re, im;

    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }

    public static Complex add(Complex c1, Complex c2) {
        return new Complex(c1.re + c2.re, c1.im + c2.im);
    }

    public static Complex sub(Complex c1, Complex c2) {
        return new Complex(c1.re - c2.re, c1.im - c2.im);
    }

    public static Complex mul(Complex c1, Complex c2) {
        return new Complex(c1.re * c2.re - c1.im * c2.im,
                            c1.im * c2.re + c1.re * c2.im);
    }

    public static Complex div(Complex c1, Complex c2) {
        if (norm2(c2) == 0) System.out.println("ZERO DIVIDE");
        return mul(mul(c1, conjugate(c2)), new Complex(1 / (norm2(c2) * norm2(c2)), 0));
    }

    public static double norm2(Complex c) {
        return c.re * c.re + c.im * c.im;
    }

    public static Complex conjugate(Complex c) {
        return new Complex(c.re, -c.im);
    }

    public static Complex polar(double rho, double theta){
        return new Complex(rho * Math.cos(theta), rho*Math.sin(theta));
    }

    public static Complex ZERO(){
        return new Complex(0,0);
    }

    @Override
    public String toString() {
        return "Complex{" +
                "re=" + re +
                ", im=" + im +
                '}';
    }
}
