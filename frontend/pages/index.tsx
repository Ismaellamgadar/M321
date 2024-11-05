import DefaultLayout from "@/layouts/default";
import ProductListItem from "@/components/product-list-item";

const BACKEND_URL = "http://localhost:8080";

interface Book {
  name: string;
  price: string;
  image: string;
  author: string;
  description: string;
}

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

  const response = await fetch(BACKEND_URL + "/books", {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    return {
      props: {
        books: [],
      },
    };
  }

  const books = await response.json();

  return {
    props: {
      books,
    },
  };
}

const Home = ({ books }: { books: Book[] }) => {
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
