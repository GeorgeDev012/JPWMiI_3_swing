import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;

class PictureView extends JFrame {

    private JButton addImageButton = new JButton("Add image");
    private JButton addTextButton = new JButton("Add text");
    private JButton addRectangleButton = new JButton("Add a green rectangle");
    private JButton addRandomAreaButton = new JButton ("Add red to random area");
    private JButton addRandomGradientButton = new JButton ("Add gradient to random area");
    private JLabel imageLabel = new JLabel("");
    private BufferedImage chosenImage;
    private boolean hasLabelIcon = false;

     PictureView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.setSize(new Dimension(500,300));
        this.setTitle("Title");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new GridLayout(5,1));
        jPanel2.add(addImageButton);
        jPanel2.add(addTextButton);
        jPanel2.add(addRectangleButton);
        jPanel2.add(addRandomAreaButton);
        jPanel2.add(addRandomGradientButton);

        jPanel.add(jPanel2, BorderLayout.EAST);
        jPanel.add(imageLabel, BorderLayout.CENTER);

        this.setLocationByPlatform(true);
        this.add(jPanel);
    }

    public int getLabelImageHeight() {
        return imageLabel.getSize().height;
    }

    public int getLabelImageWidth() {
        return imageLabel.getSize().width;
    }

    public void setLabelIcon(ImageIcon imageIcon) {
        imageLabel.setIcon(imageIcon);
    }

    public boolean hasLabelIcon() {
        return hasLabelIcon;
    }

    public void setHasLabelIcon() {
         hasLabelIcon = true;
    }

    public BufferedImage getChosenImage() {
        return chosenImage;
    }

    public void setChosenImage(BufferedImage chosenImage) {
        this.chosenImage = chosenImage;
    }

    public void addImageButtonListener(ActionListener listenForButton) {
        addImageButton.addActionListener(listenForButton);
    }

    public void addTextButtonListener(ActionListener listenForButton) {
        addTextButton.addActionListener(listenForButton);
    }

    public void addRectangleButtonListener(ActionListener listenForButton) {
        addRectangleButton.addActionListener(listenForButton);
    }

    public void addRandomAreaButtonListener(ActionListener listenForButton) {
        addRandomAreaButton.addActionListener(listenForButton);
    }

    public void addRandomGradientButtonListener(ActionListener listenForButton) {
        addRandomGradientButton.addActionListener(listenForButton);
    }

    public void addLabelListener(ComponentAdapter listenForResize) {
        imageLabel.addComponentListener(listenForResize);
    }
}

