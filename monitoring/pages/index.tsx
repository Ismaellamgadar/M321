import DefaultLayout from "@/layouts/default";
import { Card, Spacer, CardBody } from "@nextui-org/react";
import { useMemo } from "react";
import { Grid, Typography } from "@mui/material";

interface Book {
  id: number;
  name: string;
  price: string;
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
    return totalBooks > 0 ? (total / totalBooks).toFixed(2) : "0.00";
  }, [books]);

  return (
    <DefaultLayout>
      <div className="dashboard-container">
        <Grid
          container
          spacing={2}
          justifyContent="center"
          style={{ marginBottom: "2rem" }}
        >
          <Grid item xs={12} sm={6} md={3}>
            <Card>
              <CardBody>
                <Typography variant="h6" component="div" align="center">
                  Total Books
                </Typography>
                <Typography variant="h4" component="div" align="center">
                  {totalBooks}
                </Typography>
              </CardBody>
            </Card>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Card>
              <CardBody>
                <Typography variant="h6" component="div" align="center">
                  Average Price
                </Typography>
                <Typography variant="h4" component="div" align="center">
                  {averagePrice}
                </Typography>
              </CardBody>
            </Card>
          </Grid>
        </Grid>
      </div>
      <style>{`
        .dashboard-container {
          padding: 2rem;
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;
        }
      `}</style>
    </DefaultLayout>
  );
};

export default Dashboard;
