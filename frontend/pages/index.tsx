import DefaultLayout from "@/layouts/default";
import ProductListItem from "@/components/product-list-item";

// Define the data for 10 books
const books = [
  {
    name: "The Great Gatsby",
    price: "15.99",
    image: "https://example.com/gatsby.jpg",
    author: "F. Scott Fitzgerald",
    description: "A novel about the American dream and the roaring twenties.",
  },
  {
    name: "1984",
    price: "12.99",
    image: "https://example.com/1984.jpg",
    author: "George Orwell",
    description: "A dystopian novel about totalitarianism and surveillance.",
  },
  {
    name: "To Kill a Mockingbird",
    price: "10.99",
    image: "https://example.com/mockingbird.jpg",
    author: "Harper Lee",
    description: "A novel about racial injustice in the American South.",
  },
  {
    name: "Pride and Prejudice",
    price: "9.99",
    image: "https://example.com/pride-prejudice.jpg",
    author: "Jane Austen",
    description:
      "A classic romance about manners and marriage in Regency England.",
  },
  {
    name: "The Catcher in the Rye",
    price: "14.99",
    image: "https://example.com/catcher.jpg",
    author: "J.D. Salinger",
    description: "A novel about teenage rebellion and alienation.",
  },
  {
    name: "Moby-Dick",
    price: "18.99",
    image: "https://example.com/moby-dick.jpg",
    author: "Herman Melville",
    description: "An epic tale of obsession and revenge.",
  },
  {
    name: "War and Peace",
    price: "20.99",
    image: "https://example.com/war-peace.jpg",
    author: "Leo Tolstoy",
    description: "A historical novel about the Napoleonic Wars.",
  },
  {
    name: "Crime and Punishment",
    price: "13.99",
    image: "https://example.com/crime.jpg",
    author: "Fyodor Dostoevsky",
    description: "A philosophical novel about guilt and redemption.",
  },
  {
    name: "The Hobbit",
    price: "16.99",
    image: "https://example.com/hobbit.jpg",
    author: "J.R.R. Tolkien",
    description: "A fantasy adventure set in Middle-earth.",
  },
  {
    name: "Brave New World",
    price: "11.99",
    image: "https://example.com/brave-new-world.jpg",
    author: "Aldous Huxley",
    description:
      "A dystopian novel about a technologically controlled society.",
  },
];

// This function ensures user is authenticated before accessing the page
export async function getServerSideProps(context: { req: any }) {
  const { req } = context;
  const token = req.cookies.authToken;

  if (!token) {
    return {
      redirect: {
        destination: "/login",
        permanent: false,
      },
    };
  }

  return {
    props: {},
  };
}

const Home = () => {
  return (
    <DefaultLayout>
      <div className="container mx-auto px-4 py-6">
        {/* Display the books in a responsive grid */}
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {books.map((book, index) => (
            <ProductListItem
              key={index}
              name={book.name}
              price={book.price}
              image={book.image}
              author={book.author}
              description={book.description}
            />
          ))}
        </div>
      </div>
    </DefaultLayout>
  );
};

export default Home;
