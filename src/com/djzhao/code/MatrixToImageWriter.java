package com.djzhao.code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;

/**
 * ��ά���������Ҫ����MatrixToImageWriter�࣬��������Google�ṩ�ģ����Խ�����ֱ�ӿ�����Դ����ʹ�ã���Ȼ��Ҳ�����Լ�д��
 * ����������Ļ���
 */
public class MatrixToImageWriter {
	private static final int BLACK = 0xFF000000;// ��������ͼ������ɫ
	private static final int WHITE = 0x00F6F6F6; // ���ڱ���ɫ

	private MatrixToImageWriter() {
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, (matrix.get(x, y) ? BLACK : WHITE));
				// image.setRGB(x, y, (matrix.get(x, y) ? Color.YELLOW.getRGB()
				// : Color.CYAN.getRGB()));
			}
		}
		return image;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		// ����logoͼ��
		// LogoConfig logoConfig = new LogoConfig();
		// image = logoConfig.LogoMatrix(image);

		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		} else {
			// System.out.println("ͼƬ���ɳɹ���");
		}
	}

	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		// ����logoͼ��
		// LogoConfig logoConfig = new LogoConfig();
		// image = logoConfig.LogoMatrix(image);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}
}