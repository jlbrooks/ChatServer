package edu.cmu.cs.cs214.chat.util;

/**
 * A simple utility class for logging.
 *
 * You may modify this class if you wish.
 */
public final class Log {

    /**
     * Prints an error log message to the console.
     * 
     * @param tag
     *            Tag of the caller
     * @param message
     *            Message to display
     */
    public static void e(String tag, String message) {
        System.err.format("%s: %s%n", tag, message);
        System.err.flush();
    }


    /**
     * Prints an error log message to the console.
     * 
     * @param tag
     *            Tag of the caller
     * @param message
     *            Message to display
     * @param throwable
     *            Throwable to show
     */
    public static void e(String tag, String message, Throwable throwable) {
        System.err.format("%s: %s%n", tag, message);
        System.err.flush();
        throwable.printStackTrace();
    }


    /**
     * Prints an information log message to the console.
     * 
     * @param tag
     *            Tag of the caller
     * @param message
     *            Message to display
     */
    public static void i(String tag, String message) {
        System.out.format("%s: %s%n", tag, message);
        System.out.flush();
    }


    private Log() {
    }

}
