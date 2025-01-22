
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HousieTicketGenerator {

    private static final Logger LOGGER = Logger.getLogger(HousieTicketGenerator.class.getName());

    // Constants for ticket configuration
    private static final int MAX_COLUMNS = 9; // Number of columns in the ticket
    private static final int MAX_ROWS = 3; // Number of rows in the ticket
    private static final int MAX_NUMBERS_IN_ROW = 5; // Maximum numbers allowed in a single row
    private static final int MAX_NUMBERS_IN_COLUMN = 2; // Maximum numbers allowed in a single column

    public static void main(String[] args) {
        try {
            LOGGER.info("Starting Housie Ticket Generator");

            // Create an instance of the ticket generator
            HousieTicketGenerator generator = new HousieTicketGenerator();

            // Generate the ticket
            Map<Integer, List<Integer>> ticket = generator.generateTicket(MAX_ROWS, MAX_COLUMNS);

            // Print the generated ticket
            generator.printTicket(ticket);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while generating the ticket: {0}", e.getMessage());
        }
    }

    public Map<Integer, List<Integer>> generateTicket(int rows, int columns) {
        // Validate input parameters
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Rows and columns must be greater than zero.");
        }
        if (columns != MAX_COLUMNS) {
            throw new IllegalArgumentException("The number of columns must be " + MAX_COLUMNS);
        }
        if (rows != MAX_ROWS) {
            throw new IllegalArgumentException("The number of rows must be " + MAX_ROWS);
        }

        // Initialize the ticket map and column-wise numbers
        Map<Integer, List<Integer>> ticket = new HashMap<>();
        List<List<Integer>> columnNumbers = new ArrayList<>();

        // Generate numbers for each column
        for (int i = 0; i < MAX_COLUMNS; i++) {
            int start = i * 10 + 1;
            int end = (i + 1) * 10;
            List<Integer> numbers = new ArrayList<>();
            for (int j = start; j <= end; j++) {
                numbers.add(j);
            }
            Collections.shuffle(numbers);
            columnNumbers.add(numbers.subList(0, Math.min(MAX_NUMBERS_IN_COLUMN * MAX_ROWS, numbers.size())));
        }

        // Fill each row with numbers
        for (int row = 0; row < rows; row++) {
            List<Integer> rowValues = new ArrayList<>(Collections.nCopies(columns, 0));
            List<Integer> availableColumns = new ArrayList<>();
            for (int i = 0; i < columns; i++) {
                availableColumns.add(i);
            }
            Collections.shuffle(availableColumns);
            availableColumns = availableColumns.subList(0, MAX_NUMBERS_IN_ROW);
            availableColumns.sort(Integer::compare);

            for (int col : availableColumns) {
                List<Integer> column = columnNumbers.get(col);
                if (!column.isEmpty()) {
                    rowValues.set(col, column.remove(0));
                }
            }
            ticket.put(row, rowValues);
        }

        return ticket;
    }

    public void printTicket(Map<Integer, List<Integer>> ticket) {
        LOGGER.info("Generated Housie Ticket:");

        // Iterate through each row in the ticket and print its values
        for (Map.Entry<Integer, List<Integer>> entry : ticket.entrySet()) {
            for (int cell : entry.getValue()) {
                // Print each cell, using spaces for empty cells (zeros)
                System.out.print((cell == 0 ? "  " : String.format("%2d", cell)) + " ");
            }
            System.out.println(); // Move to the next row
        }
    }
}

