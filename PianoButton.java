import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import java.awt.*;

public class PianoButton extends JButton {
    private int note;
    private boolean isBlack;
    private Synthesizer synthesizer;
    private MidiChannel midiChannel;

    public PianoButton(int note, boolean isBlack) {
        this.note = note;
        this.isBlack = isBlack;

        // Set button appearance based on key type
        if (isBlack) {
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);
        } else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }
        // Initialize synthesizer and midiChannel
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            midiChannel = synthesizer.getChannels()[0];
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public int getNote() {
        return note;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void playSound() {
        midiChannel.noteOn(note, 80); // Play the note at the specified velocity
        try {
            Thread.sleep(20); // Pause execution for 200 milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("EE");
        }
    }
    public void playSound(int duartion) {
        midiChannel.noteOn(note, 80); // Play the note at the specified velocity
        try {
            Thread.sleep(duartion); // Pause execution for duration milliseconds
        } catch (InterruptedException e) {
            // e.printStackTrace();
            System.out.println("EE");
        }
        midiChannel.noteOff(note); // Stop playing the note
    }
    public void stopSound(){
        midiChannel.noteOff(note); // Stop playing the note
    }
}
