
package org.ds;

import org.ds.proto.BookProtos;
import com.google.protobuf.InvalidProtocolBufferException;

import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args)
            throws InvalidProtocolBufferException {

        // Create a Book object
        BookProtos.Book book = BookProtos.Book.newBuilder()
                .setTitle("Distributed Systems")
                .setAuthor(
                        BookProtos.Book.Author.newBuilder()
                                .setName("Andrew Tanenbaum")
                                .setCountry("USA")
                                .build()
                )
                .setIsbn("9780132392273")
                .setPublicationYear(2017)
                .setCategory(BookProtos.Book.Category.COMPUTER_SCIENCE)
                .addKeywords("Java")
                .addKeywords("Networking")
                .addKeywords("Distributed Systems")
                .build();

        // Serialize the Book object into bytes
        byte[] sentData = book.toByteArray();

        System.out.println("Sender Data (Binary):");
        printBinary(sentData);

        System.out.println("Protobuf Size: " + sentData.length + " bytes");

        // Simulate receiving the serialized data
        byte[] receivedData = sentData;

        System.out.println("\nReceiver Data (Binary):");
        printBinary(receivedData);

        System.out.println("Received Protobuf Size: "
                + receivedData.length + " bytes");

        // Deserialize the received data
        BookProtos.Book receivedBook =
                BookProtos.Book.parseFrom(receivedData);

        System.out.println("\nDecoded Book Data:");
        System.out.println(receivedBook);

        System.out.println("Objects are equal: "
                + book.equals(receivedBook));

        // JSON representation for size comparison
        String json = """
                {
                  "title": "Distributed Systems",
                  "author": {
                    "name": "Andrew Tanenbaum",
                    "country": "USA"
                  },
                  "isbn": "9780132392273",
                  "publication_year": 2017,
                  "category": "COMPUTER_SCIENCE",
                  "keywords": [
                    "Java",
                    "Networking",
                    "Distributed Systems"
                  ]
                }
                """;

        byte[] jsonData = json.getBytes(StandardCharsets.UTF_8);

        System.out.println("\nJSON Size: " + jsonData.length + " bytes");

        double reduction =
                (1.0 - (double) sentData.length / jsonData.length) * 100;

        System.out.printf("Size Reduction: %.2f%%%n", reduction);
    }

    // Convert each byte into its 8-bit binary representation
    private static void printBinary(byte[] data) {
        for (byte b : data) {
            System.out.print(
                    String.format("%8s", Integer.toBinaryString(b & 0xFF))
                            .replace(' ', '0') + " "
            );
        }
        System.out.println();
    }
}
