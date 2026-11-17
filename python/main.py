
import book_pb2

# Create a Book object
book = book_pb2.Book()

book.title = "Distributed Systems"
book.author.name = "Andrew Tanenbaum"
book.author.country = "USA"
book.isbn = "9780132392273"
book.publication_year = 2017
book.category = book_pb2.Book.COMPUTER_SCIENCE

book.keywords.extend([
    "Java",
    "Networking",
    "Distributed Systems"
])

# Serialize the Book object
sent_data = book.SerializeToString()

print("Sender Data (Binary):")
print(" ".join(format(byte, "08b") for byte in sent_data))
print("Protobuf Size:", len(sent_data), "bytes")

# Simulate receiving the serialized data
received_data = sent_data

# Deserialize the received data
received_book = book_pb2.Book()
received_book.ParseFromString(received_data)

print("\nReceiver Data (Binary):")
print(" ".join(format(byte, "08b") for byte in received_data))
print("Received Protobuf Size:", len(received_data), "bytes")

print("\nDecoded Book Data:")
print(received_book)

# Verify that the original and received objects are equal
print("Objects are equal:", book == received_book)
