package Library.Arithmetic;

public class Matrix {
    public long[][] m;
    public int h, w;

    public Matrix(int h, int w, long[][] m) {
        this.h = h;
        this.w = w;
        this.m = m;
    }

    public Matrix(long[][] m) {
        this.m = m;
        this.h = m.length;
        this.w = m[0].length;
    }

    public static Matrix add(Matrix m1, Matrix m2) throws Exception {
        if (m1.h != m2.h || m1.w != m2.w) {
            throw new Exception("Matrix Sizes Differ.");
        }
        Matrix m = new Matrix(m1.m);
        for (int i = 0; i < m.h; i++) {
            for (int j = 0; j < m.w; j++) {
                m.m[i][j] += m2.m[i][j];
            }
        }
        return m;
    }

    public static Matrix identity(int n) {
        long[][] mat = new long[n][n];
        for (int i = 0; i < n; i++) {
            mat[i][i] = 1;
        }
        return new Matrix(mat);
    }

    public static Matrix mul(Matrix m1, Matrix m2) {
        long[][] res = new long[m1.h][m2.w];
        long[][] mat1 = m1.m;
        long[][] mat2 = m2.m;

        for (int h = 0; h < m1.h; h++) {
            for (int w = 0; w < m2.w; w++) {
                for (int x = 0; x < m1.w; x++) {
                    res[h][w] += mat1[h][x] * mat2[x][w];
                }
            }
        }
        return new Matrix(res);
    }

    public static Matrix mul(Matrix m1, Matrix m2, long MOD) {
        long[][] res = new long[m1.h][m2.w];
        long[][] mat1 = m1.m;
        long[][] mat2 = m2.m;

        for (int h = 0; h < m1.h; h++) {
            for (int w = 0; w < m2.w; w++) {
                for (int x = 0; x < m1.w; x++) {
                    res[h][w] += mat1[h][x] * mat2[x][w];
                    res[h][w] %= MOD;
                }
            }
        }
        return new Matrix(res);
    }

    public static Matrix pow(Matrix m, long a) {
        return a == 0 ? identity(m.h) : mul(mul(pow(m, a / 2), pow(m, a / 2)), a % 2 == 0 ? identity(m.h) : m);
    }

    public static Matrix pow(Matrix m, long a, long MOD) {
        if (a == 0) return identity(m.h);
        Matrix half = pow(m, a / 2, MOD);
        return mul(mul(half, half, MOD), a % 2 == 0 ? identity(m.h) : m, MOD);
    }

}
