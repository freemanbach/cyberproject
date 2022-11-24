import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Stego.ImageStego;

public class Stego {

    public static void main(String[] args) {

        String message = "";
        String output_file_name = "";
        String input_file_name = "";
        String coding = "";

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-m":
                if (i + 1 < args.length) {
                    message = args[i + 1];
                }
                break;

                case "-h":
                help();
                System.exit(0);
                break;

                case "-o":
                    if (i + 1 < args.length) {
                        output_file_name = args[i + 1] + ".png";
                    }
                break;

                case "-i":
                if (i + 1 < args.length) {
                    Pattern input_pattern = Pattern.compile("^(.+)/([^/]+).(png|jpg)$", Pattern.CASE_INSENSITIVE);
                    Matcher input_matcher = input_pattern.matcher(args[i + 1]);
                    boolean match_found = input_matcher.find();
                    if (match_found){

                        input_file_name = args[i + 1];
                    } else {
                        System.out.println("Not a image file");
                        System.exit(1);
                    }
                }
                break;

                case "-c":
                if (i + 1 < args.length) {
                    Pattern code_pattern = Pattern.compile("encode|decode", Pattern.CASE_INSENSITIVE);
                    Matcher code_matcher = code_pattern.matcher(args[i + 1]);
                    boolean match_found = code_matcher.find();
                    if (match_found) {
                        coding = args[i + 1];
                    } else {
                        System.out.println("Not a valid coding option");
                    }
                }
                break;

                default: break;
            }
        }

        try {
            ImageStego stego = new ImageStego();
            File input_file = new File(input_file_name);
            BufferedImage input_image = ImageIO.read(input_file);
            switch (coding) {
                case "encode":
                BufferedImage output_image = ImageIO.read(input_file);
                stego.encode(input_image, output_image, input_image.getWidth(), input_image.getHeight(), message, output_file_name);
                break;
                case "decode":
                String decoded_message = stego.decode(input_image, input_image.getWidth(), input_image.getHeight());
                System.out.println(decoded_message);
                break;
                default:
                break;
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void help() {
        String help_message = "usage:\n-m: \"<Message>\", If message is multiple words use quotations,\n-h help,\n-o output file name,\n-i input file,\n-c [encode | decode]\nExamples\njava Steno -i ../images/anitab/original.png -o encoded.png -m \"Hello World\" -c encode\njava Steno -i encoded.png -c decode";
        System.out.println(help_message);
    }
}