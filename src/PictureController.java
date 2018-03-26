import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

class PictureController {

    private PictureView pictureView;
    private PictureModel pictureModel;

    PictureController(PictureView pictureView, PictureModel pictureModel) {
        this.pictureView = pictureView;
        this.pictureModel = pictureModel;

        this.pictureView.addImageButtonListener(new ImageButtonListener());
        this.pictureView.addTextButtonListener(new TextButtonListener());
        this.pictureView.addRectangleButtonListener(new RectangleButtonListener());
        this.pictureView.addRandomAreaButtonListener(new RandomAreaButtonListener());
        this.pictureView.addRandomGradientButtonListener(new RandomGradientButtonListener());
        this.pictureView.addLabelListener(new ImageResizeListener());
    }

    class ImageButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                pictureView.setChosenImage(ImageIO.read(
                        getClass().getResource("resources/cats.jpg")));
                putResizedImageInLabel();
            } catch (Exception IOException) {

            }
        }
    }

    class TextButtonListener implements ActionListener {

        private BufferedImage chosenImage;

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(pictureView.hasLabelIcon()) {
                chosenImage = pictureView.getChosenImage();
                drawStringOnImage();
                putResizedImageInLabel();
            }
        }

        private void drawStringOnImage() {
            Graphics2D graphics2D = setGraphics();
            Dimension textDimension = getTextDimension(graphics2D);
            Random random = new Random();
            graphics2D.drawString(pictureModel.getName(), random.nextInt(chosenImage.getWidth() - textDimension.width),
                    random.nextInt(chosenImage.getHeight() - textDimension.height) + textDimension.height);
        }

        private Graphics2D setGraphics() {
            Graphics2D graphics2D = (Graphics2D) chosenImage.getGraphics();
            graphics2D.setColor(getRandomColor());
            graphics2D.setFont(getTextFont());
            return graphics2D;
        }

        private Dimension getTextDimension(@NotNull Graphics2D graphics2D) {
            Dimension textDimension = new Dimension();
            textDimension.width = graphics2D.getFontMetrics().stringWidth(pictureModel.getName());
            textDimension.height = graphics2D.getFontMetrics().getHeight();
            return textDimension;
        }

        @NotNull
        @Contract(pure = true)
        private Font getTextFont() {
            int style = Font.BOLD | Font.ITALIC;
            return new Font("Garamond", style, 40);
        }
    }

    class RectangleButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(pictureView.hasLabelIcon()) {
                setGraphics();
                putResizedImageInLabel();
            }
        }

        private void setGraphics() {
            Graphics2D graphics2D = getGraphics2D();
            graphics2D.setColor(Color.GREEN);
            graphics2D.drawRect(40,120, 200,600);
        }
    }

    class RandomAreaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(pictureView.hasLabelIcon()) {
                setGraphics();
                putResizedImageInLabel();
            }
        }

        private void setGraphics() {
            Graphics2D graphics2D = getGraphics2D();
            graphics2D.setColor(Color.RED);
            graphics2D.fillOval(100,200,270,430);
        }
    }

    class RandomGradientButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(pictureView.hasLabelIcon()) {
                setGraphics();
                putResizedImageInLabel();
            }
        }

        private void setGraphics() {
            Graphics2D graphics2d = getGraphics2D();
            GradientPaint gradient = new GradientPaint(0, 700, getRandomColor(), 300, 1000, getRandomColor());
            graphics2d.setPaint(gradient);
            graphics2d.fillOval(300, 300, 300, 300);
        }
    }

    class ImageResizeListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            if(pictureView.hasLabelIcon()) putResizedImageInLabel();
        }
    }

    private Graphics2D getGraphics2D() {
        BufferedImage chosenImage = pictureView.getChosenImage();
        return (Graphics2D) chosenImage.getGraphics();
    }

    @NotNull
    private Color getRandomColor() {
        return new Color((int) (Math.random() * 0x1000000));
    }

    private void putResizedImageInLabel() {
        Image imageTemp = pictureView.getChosenImage().getScaledInstance(pictureView.getLabelImageWidth(),
                pictureView.getLabelImageHeight(), java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(imageTemp);
        pictureView.setLabelIcon(imageIcon);
        pictureView.setHasLabelIcon();
    }
}
