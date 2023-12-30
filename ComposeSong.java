import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

// Abstract class representing instruments
abstract class Instrument {
    abstract String compose();
}

// Double bass class
class Bass extends Instrument {
    private static final int NUM_NOTES = 4;
    private static final String[] NOTES = {"do", "re", "mi", "fa", "sol", "la", "si"};

    @Override
    String compose() {
        Random random = new Random();
        StringBuilder composition = new StringBuilder();
        composition.append("bass").append(random.nextInt(3) + 1).append(" ");

        for (int i = 0; i < NUM_NOTES; i++) {
            composition.append(NOTES[random.nextInt(7)]).append(" ");
        }

        return composition.toString().trim();
    }
}

// Violin class
class Violin extends Instrument {
    private static final int NUM_NOTES = 5;
    private static final String[] NOTES = {"do", "re", "mi", "fa", "sol", "la", "si"};

    @Override
    String compose() {
        Random random = new Random();
        StringBuilder composition = new StringBuilder();
        composition.append("violin").append(random.nextInt(3) + 1).append(" ");

        for (int i = 0; i < NUM_NOTES; i++) {
            composition.append(NOTES[random.nextInt(7)]).append(" ");
        }

        return composition.toString().trim();
    }
}

// Guitar class
class Guitar extends Instrument {
    private static final int NUM_NOTES = 6;
    private static final String[] NOTES = {"do", "re", "mi", "fa", "sol", "la", "si"};

    @Override
    String compose() {
        Random random = new Random();
        StringBuilder composition = new StringBuilder();
        composition.append("guitar").append(random.nextInt(3) + 1).append(" ");

        for (int i = 0; i < NUM_NOTES; i++) {
            composition.append(NOTES[random.nextInt(7)]).append(" ");
        }

        return composition.toString().trim();
    }
}

// Application class
public class ComposeSong {
    public static void main(String[] args) {
        try {
            FileWriter bassWriter = new FileWriter("bass.txt");
            FileWriter violinWriter = new FileWriter("violin.txt");
            FileWriter guitarWriter = new FileWriter("guitar.txt");
            FileWriter songWriter = new FileWriter("song.txt");

            int totalInstruments = 6;
            int bassCount = 0, violinCount = 0, guitarCount = 0;

            for (int i = 0; i < totalInstruments; i++) {
                Instrument instrument;
                if (i % 3 == 0) {
                    instrument = new Bass();
                    bassCount++;
                } else if (i % 3 == 1) {
                    instrument = new Violin();
                    violinCount++;
                } else {
                    instrument = new Guitar();
                    guitarCount++;
                }

                String composition = instrument.compose();
                String fileName = instrument.getClass().getSimpleName().toLowerCase() + ".txt";

                // Write to the instrument-specific file
                FileWriter instrumentWriter = switch (i % 3) {
                    case 0 -> bassWriter;
                    case 1 -> violinWriter;
                    case 2 -> guitarWriter;
                    default -> null;
                };
                assert instrumentWriter != null;
                instrumentWriter.write(composition + "\n");

                // Write to the song file
                songWriter.write(composition + "\n");
            }

            // Close all writers
            bassWriter.close();
            violinWriter.close();
            guitarWriter.close();
            songWriter.close();

            // Display the summary in the song file
            System.out.println("Instruments:" + totalInstruments + " bass:" + bassCount + " violin:" + violinCount + " guitar:" + guitarCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
