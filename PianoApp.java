
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class PianoApp implements KeyListener {
    private static final int OCTAVES = 2;
    private static final int KEYS_PER_OCTAVE = 7 + 5;
    private static final int KEY_COUNT = OCTAVES * KEYS_PER_OCTAVE;
    private static char keyPressed;
    
    private PianoButton[] pianoKeys;
    private Map<Character, Integer> keyMap = new HashMap<>();

    public PianoApp() {
        pianoKeys = new PianoButton[KEY_COUNT];
        for (int i = 0; i < KEY_COUNT; i++) {
            final int note = i + 60; // MIDI note number
            boolean isBlack = isBlackKey(i);
            pianoKeys[i] = new PianoButton(note, isBlack);
        }
        // Initialize the key map
        keyMap.put('z', 0);//C
        keyMap.put('x', 2);//D
        keyMap.put('c', 4);//E
        keyMap.put('v', 5);//F
        keyMap.put('b', 7);//G
        keyMap.put('n', 9);//A
        keyMap.put('m', 11);//B
        //
        keyMap.put('Z', 12);//C
        keyMap.put('X', 14);//D
        keyMap.put('C', 16);//E
        keyMap.put('V', 17);//F
        keyMap.put('B', 19);//G
        keyMap.put('N', 21);//A
        keyMap.put('M', 23);//B
        //
        keyMap.put('a', 1);
        keyMap.put('s', 3);
        keyMap.put('j', 6);
        keyMap.put('k', 8);
        keyMap.put('l', 10);
        keyMap.put('A', 13);
        keyMap.put('S', 15);
        keyMap.put('J', 18);
        keyMap.put('K', 20);
        keyMap.put('L', 22);
        // Create piano panel
        JPanel pianoPanel = new JPanel();
        pianoPanel.setLayout(new GridLayout(1, KEY_COUNT));

        // Add empty border for padding and spacing
        int padding = 10;
        pianoPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));

        for (PianoButton key : pianoKeys) {
            pianoPanel.add(key);
        }

        // Add piano panel to the content pane of the JFrame
        JFrame frame = new JFrame("Piano App");
        frame.getContentPane().add(pianoPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);
        frame.setSize(1000, 200); // Set the window size to 1000x200 pixels
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private boolean isBlackKey(int keyIndex) {
        int keyIndexInOctave = keyIndex % KEYS_PER_OCTAVE;
        return keyIndexInOctave == 1 || keyIndexInOctave == 3 || keyIndexInOctave == 6
                || keyIndexInOctave == 8 || keyIndexInOctave == 10;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char press = e.getKeyChar();
        if (!keyMap.containsKey(press)) {
            if (press >= 48 && press <= 48 + 9) {
                pianoKeys[keyMap.get(keyPressed)].playSound(200 * ((int) press - 48));
                System.out.println("Key Pressed: " + keyPressed + "  " + press);
            }
            return;
        }
        keyPressed = e.getKeyChar();
        System.out.println("Key Pressed: " + keyPressed);
        pianoKeys[keyMap.get(keyPressed)].playSound();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pianoKeys[keyMap.get(keyPressed)].stopSound();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PianoApp app = new PianoApp();
        });
    }
}
