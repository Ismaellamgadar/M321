"use client";

import React, {
  createContext,
  useContext,
  useState,
  ReactNode,
  useEffect,
} from "react";

type CartItem = {
  name: string;
  price: number | string;
  image: string;
  author: string;
  description: string;
};

type CartContextType = {
  cart: CartItem[];
  cartCount: number;
  cartAnimate: boolean;
  addToCart: (item: CartItem) => void;
  removeFromCart: (item: CartItem) => void;
  clearCart: () => void;
};

const CartContext = createContext<CartContextType | undefined>(undefined);

export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error("useCart must be used within a CartProvider");
  }
  return context;
};

export const CartProvider = ({ children }: { children: ReactNode }) => {
  const [cart, setCart] = useState<CartItem[]>([]);
  const [cartAnimate, setCartAnimate] = useState(false);

  const cartCount = cart.length;

  const addToCart = (item: CartItem) => {
    setCart((prevCart) => [...prevCart, item]);
    triggerCartAnimation();
  };

  const removeFromCart = (item: CartItem) => {
    setCart((prevCart) =>
      prevCart.filter((cartItem) => cartItem.name !== item.name),
    );
  };

  const clearCart = () => {
    setCart([]);
  };

  const triggerCartAnimation = () => {
    setCartAnimate(true);
    setTimeout(() => setCartAnimate(false), 500); // Animation für 500ms
  };

  return (
    <CartContext.Provider
      value={{
        cart,
        cartCount,
        cartAnimate,
        addToCart,
        removeFromCart,
        clearCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};
