package cn.ean.medium.array;

/**
 * @FileName Rotate
 * @Author ean
 * @Date 2023/1/16 18:07
 **/
public class RotateTest {

    public void rotate(int[][] matrix) {
        int length = matrix.length;
        int rotateFrequency = matrix.length / 2;
        int temp1, temp2, temp3, temp4;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                temp1 = matrix[i][j];
                temp2 = matrix[j][length - 1 - i];
                temp3 = matrix[length - 1 - i][length - 1 - j];
                temp4 = matrix[length -1 -j][i];
                matrix[i][j] = temp4;
                matrix[j][length - 1 - i] = temp1;
                matrix[length - 1 - i][length - 1 - j] = temp2;
                matrix[length - 1 - j][i] = temp3;
            }
        }

    }

    private int[][] temp(int[][] nums) {

        return nums;
    }
}
