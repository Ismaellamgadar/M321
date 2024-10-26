import React from "react";
import { Button, Image } from "@nextui-org/react";
import { cn } from "@nextui-org/react";
import { useCart } from "@/context/cart";

export type ProductItem = {
  name: string;
  price: number | string;
  image: string;
  author: string;
  description: string;
};

export type ProductListItemProps = Omit<
  React.HTMLAttributes<HTMLDivElement>,
  "id"
> &
  ProductItem;

const ProductListItem = React.forwardRef<HTMLDivElement, ProductListItemProps>(
  ({ name, price, description, image, author, className, ...props }, ref) => {
    const { addToCart } = useCart();

    const handleAddToCart = () => {
      const product = { name, price, description, image, author };
      addToCart(product);
    };

    return (
      <div
        ref={ref}
        className={cn(
          "relative flex w-64 max-w-full flex-none scroll-ml-6 flex-col gap-3 rounded-large bg-content1 p-4 shadow-medium",
          className,
        )}
        {...props}
      >
        <div
          className={cn(
            "relative flex h-52 max-h-full w-full flex-col items-center justify-center overflow-visible rounded-medium bg-content2",
          )}
        >
          <Image
            removeWrapper
            alt={name}
            className={cn(
              "z-0 h-full max-h-full w-full max-w-[80%] overflow-visible object-contain object-center hover:scale-110",
            )}
            src={image}
          />
        </div>

        <div className="flex flex-col gap-3 px-1">
          <div className="flex items-center justify-between">
            <h3 className="text-medium font-medium text-default-700">{name}</h3>
            <p className="text-medium font-medium text-default-500">${price}</p>
          </div>

          <p className="text-small text-default-500">by {author}</p>

          {description && (
            <p className="text-small text-default-500">{description}</p>
          )}

          <Button
            fullWidth
            className="font-medium"
            color="primary"
            radius="lg"
            variant="solid"
            onClick={handleAddToCart}
          >
            Add to cart
          </Button>
        </div>
      </div>
    );
  },
);

ProductListItem.displayName = "ProductListItem";

export default ProductListItem;
