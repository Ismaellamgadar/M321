import DefaultLayout from "@/layouts/default";
import { Card, Spacer, CardBody } from "@nextui-org/react";
import { useMemo } from "react";
import { CardHeader } from "@nextui-org/card";
import { Grid } from "@mui/material";

interface Book {
  id: number;
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

  const response = await fetch("http://localhost:8080/books", {
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

const Dashboard = ({ books }: { books: Book[] }) => {
  // Calculate total books and average price
  const totalBooks = books.length;
  const averagePrice = useMemo(() => {
    const total = books.reduce((sum, book) => sum + parseFloat(book.price), 0);
    return (total / totalBooks).toFixed(2);
  }, [books]);

  return (
    <DefaultLayout>
      <div className="w-100 h-100 flex flex-col items-center justify-center">
        {JSON.stringify(books)}
      </div>
    </DefaultLayout>
  );
};

export default Dashboard;
