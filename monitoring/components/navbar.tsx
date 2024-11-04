import {
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  Link,
  Button,
  Badge,
} from "@nextui-org/react";
import { Icon } from "@iconify/react";
import { useRouter } from "next/router";
import { useCart } from "@/context/cart";

export default function BookstoreNavbar() {
  const router = useRouter();
  const { cartCount, cartAnimate } = useCart();

  const handleLogout = async () => {
    try {
      const response = await fetch("/api/auth/logout", {
        method: "POST",
      });
      if (response.ok) {
        await router.push("/login");
      } else {
        console.error("Failed to log out");
      }
    } catch (error) {
      console.error("An error occurred during logout", error);
    }
  };

  return (
    <Navbar isBordered>
      <NavbarBrand>
        <Icon icon="mdi:book-open-page-variant" width="30" height="30" />
        <p className="font-bold text-inherit ml-3">Bookstore</p>
      </NavbarBrand>

      <NavbarContent className="hidden sm:flex gap-4" justify="center">
        <NavbarItem>
          <Link color="foreground" href="/">
            Dashboard
          </Link>
        </NavbarItem>
      </NavbarContent>

      <NavbarContent justify="end">
        <>
          <NavbarItem>
            <Button onClick={handleLogout} color="danger">
              Logout
            </Button>
          </NavbarItem>
        </>
      </NavbarContent>
    </Navbar>
  );
}
