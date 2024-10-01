import DefaultLayout from "@/layouts/default";
import { useCart } from "@/context/cart";
import { Button, Image } from "@nextui-org/react";
import { cn } from "@nextui-org/react"; // for combining class names

const Cart = () => {
  const { cart, removeFromCart } = useCart();

  const handleRemoveFromCart = (item: any) => {
    removeFromCart(item);
  };

  return (
    <DefaultLayout>
      <div className="container mx-auto px-4 py-6">
        <h1 className="text-2xl font-bold mb-6">Your Cart</h1>

        {cart.length === 0 ? (
          <p>Your cart is empty.</p>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            {cart.map((item, index) => (
              <div
                key={index}
                className={cn(
                  "relative flex w-64 max-w-full flex-none scroll-ml-6 flex-col gap-3 rounded-large bg-content1 p-4 shadow-medium",
                )}
              >
                <div
                  className={cn(
                    "relative flex h-52 max-h-full w-full flex-col items-center justify-center overflow-visible rounded-medium bg-content2",
                  )}
                >
                  {/* Placeholder while loading */}
                  <Image
                    removeWrapper
                    alt={item.name}
                    className={cn(
                      "z-0 h-full max-h-full w-full max-w-[80%] overflow-visible object-contain object-center hover:scale-110",
                    )}
                    src={item.image}
                  />
                </div>

                <div className="flex flex-col gap-3 px-1">
                  <div className="flex items-center justify-between">
                    <h3 className="text-medium font-medium text-default-700">
                      {item.name}
                    </h3>
                    <p className="text-medium font-medium text-default-500">
                      ${item.price}
                    </p>
                  </div>

                  <p className="text-small text-default-500">
                    by {item.author}
                  </p>

                  <Button
                    fullWidth
                    className="font-medium"
                    color="danger"
                    radius="lg"
                    variant="solid"
                    onClick={() => handleRemoveFromCart(item)}
                  >
                    Remove
                  </Button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </DefaultLayout>
  );
};

export default Cart;
