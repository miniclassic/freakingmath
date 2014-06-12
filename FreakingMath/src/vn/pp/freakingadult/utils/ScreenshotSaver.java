package vn.pp.freakingadult.utils;

import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenshotSaver {

        private static final int[] RGBA_OFFSETS = { 0, 1, 2, 3 };
        private static final int[] RGB_OFFSETS = { 0, 1, 2 };

        public static void saveScreenshot(String baseName) throws IOException {
                File createTempFile = File.createTempFile(baseName, ".png");
                saveScreenshot(createTempFile);
        }

        public static void saveScreenshot(File file) throws IOException {
                saveScreenshot(file, false);
        }

        public static void saveScreenshot(File file, boolean hasAlpha) throws IOException {
                if (Gdx.app.getType() == ApplicationType.Android)
                        return;

                byte[] screenshotPixels = ScreenUtils.getFrameBufferPixels(true);

                int width = Gdx.graphics.getWidth();
                int height = Gdx.graphics.getHeight();
                System.err.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                saveScreenshot(file, screenshotPixels, width, height, hasAlpha);
                System.err.println("bbb");
        }
        
        public static void saveScreenshot(File file, int width, int height, boolean hasAlpha) throws IOException {
        	byte[] pixels = ScreenUtils.getFrameBufferPixels(true);
            DataBufferByte dataBuffer = new DataBufferByte(pixels, pixels.length);

            PixelInterleavedSampleModel sampleModel = new PixelInterleavedSampleModel(DataBuffer.TYPE_BYTE, width, height, 4, 4 * width, getOffsets(hasAlpha));

            WritableRaster raster = Raster.createWritableRaster(sampleModel, dataBuffer, new Point(0, 0));

            BufferedImage img = new BufferedImage(getColorModel(hasAlpha), raster, false, null);

            ImageIO.write(img, "png", file);
            
            System.err.println("file.getAbsoluteFile()"+file.getAbsoluteFile());
        }
        
        
        public static void saveScreenshot(File file, int x,int y,int width, int height, boolean hasAlpha) throws IOException {
        	byte[] pixels = ScreenUtils.getFrameBufferPixels(x,y,width,height,true);
            DataBufferByte dataBuffer = new DataBufferByte(pixels, pixels.length);

            PixelInterleavedSampleModel sampleModel = new PixelInterleavedSampleModel(DataBuffer.TYPE_BYTE, width, height, 4, 4 * width, getOffsets(hasAlpha));

            WritableRaster raster = Raster.createWritableRaster(sampleModel, dataBuffer, new Point(0, 0));

            BufferedImage img = new BufferedImage(getColorModel(hasAlpha), raster, false, null);

            ImageIO.write(img, "png", file);
            
            System.err.println("file.getAbsoluteFile()"+file.getAbsoluteFile());
        }
        
        
       
        

        public static void saveScreenshot(File file, byte[] pixels, int width, int height, boolean hasAlpha) throws IOException {
                DataBufferByte dataBuffer = new DataBufferByte(pixels, pixels.length);

                PixelInterleavedSampleModel sampleModel = new PixelInterleavedSampleModel(DataBuffer.TYPE_BYTE, width, height, 4, 4 * width, getOffsets(hasAlpha));

                WritableRaster raster = Raster.createWritableRaster(sampleModel, dataBuffer, new Point(0, 0));

                BufferedImage img = new BufferedImage(getColorModel(hasAlpha), raster, false, null);

                ImageIO.write(img, "png", file);
                
                
        }

        private static ColorModel getColorModel(boolean alpha) {
                if (alpha)
                        return new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8, 8 }, true, false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);
                return new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8 }, false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);
        }

        private static int[] getOffsets(boolean alpha) {
                if (alpha)
                        return RGBA_OFFSETS;
                return RGB_OFFSETS;
        }
        
//======================
        public static String saveScreenshotPro() throws Exception{
        	FileHandle fh=null;
            try{
//                do{
//                    fh = new FileHandle("/data/data/vn.pp.block.minion/files/screenshot" + counter++ + ".png");
                    fh = new FileHandle("/mnt/sdcard/screenshot_frkm.png");
//                }while (fh.exists());
                Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
                PixmapIO.writePNG(fh, pixmap);
                pixmap.dispose();
                System.err.println("fh.path().toString() "+fh.path().toString());
            }catch (Exception e){   
        	e.printStackTrace();
            }
            return fh.path().toString();
        }

        private static Pixmap getScreenshot(int x, int y, int w, int h, boolean flipY){
            Gdx.gl.glPixelStorei(GL20.GL_PACK_ALIGNMENT, 1);

            final Pixmap pixmap = new Pixmap(w, h, Format.RGBA8888);
            ByteBuffer pixels = pixmap.getPixels();
            Gdx.gl.glReadPixels(x, y, w, h, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, pixels);

            final int numBytes = w * h * 4;
            byte[] lines = new byte[numBytes];
            if (flipY){
                pixels.clear();
                pixels.get(lines);
            }else{
                final int numBytesPerLine = w * 4;
                for (int i = 0; i < h; i++){
                    pixels.position((h - i - 1) * numBytesPerLine);
                    pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
                }
                pixels.clear();
                pixels.put(lines);
            }
            return pixmap;
        }

}