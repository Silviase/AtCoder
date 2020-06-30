package Library.Arithmetic;

import Library.Arithmetic.Modular.ModInt;

import java.util.ArrayList;

public class FastFourierTransform {
    private static void dft(ArrayList<Complex> f, int inv) {
        int size = f.size();
        if (size == 1) return;
        ArrayList<Complex> va = new ArrayList<>();
        ArrayList<Complex> vb = new ArrayList<>();
        for (int i = 0; i < size / 2; i++) {
            va.add(f.get(2 * i));
            vb.add(f.get(2 * i + 1));
        }

        dft(va, inv);
        dft(vb, inv);

        Complex now = new Complex(1, 0);
        Complex zeta = Complex.polar(1, inv * 2 * Math.PI / size);

        for (int i = 0; i < size; i++) {
            f.set(i, Complex.add(va.get(i % (size / 2)),
                                    Complex.mul(now, vb.get(i % (size / 2)))));
            now = Complex.mul(now, zeta);
        }
    }

    public static double[] multiple(double[] f, double[] g) {
        ArrayList<Complex> nf = new ArrayList<>(), ng = new ArrayList<>();
        int size = 1;
        while (size < f.length + g.length) size <<= 1;
        for (double v : f) nf.add(new Complex(v, 0));
        for (double v : g) ng.add(new Complex(v, 0));
        while(nf.size() < size) nf.add(Complex.ZERO());
        while(ng.size() < size) ng.add(Complex.ZERO());
        dft(nf, 1);
        dft(ng, 1);
        for (int i = 0; i < size; i++) {
            nf.set(i, Complex.mul(nf.get(i), ng.get(i)));
        }

        dft(nf, -1);

        double[] res = new double[size];
        for (int i = 0; i < size; i++) {
            res[i] = nf.get(i).re / size;
        }
        return res;
    }




}
